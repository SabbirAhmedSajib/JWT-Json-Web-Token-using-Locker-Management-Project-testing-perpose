package com.LockerService.DrawerRelease.Controller;


import com.LockerService.DrawerRelease.DTO.JwtAuthResponse;
import com.LockerService.DrawerRelease.DTO.LoginDto;
import com.LockerService.DrawerRelease.DTO.SignupDto;
import com.LockerService.DrawerRelease.Entity.Role;
import com.LockerService.DrawerRelease.Entity.User;
import com.LockerService.DrawerRelease.Repository.RoleRepo;
import com.LockerService.DrawerRelease.Repository.UserRepo;
import com.LockerService.DrawerRelease.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager  authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @PostMapping("/signup")
    private ResponseEntity<?> registerUser(@RequestBody SignupDto signupDto){

        //Add Check For User Name exist in DB or not
        if(userRepo.existsByUserName(signupDto.getUsername())){
            return new ResponseEntity("User Name is Already Taken!",HttpStatus.BAD_REQUEST);
        }

        // add check for email exist in DB or not
        if(userRepo.existsByEmail(signupDto.getEmail())){
            return new ResponseEntity<>("Email is already Taken!",HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signupDto.getName());
        user.setUserName(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));


        Role roles = roleRepo.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));

        userRepo.save(user);

        return new ResponseEntity<>("User Registered Successfully!",HttpStatus.OK);
    }


}
