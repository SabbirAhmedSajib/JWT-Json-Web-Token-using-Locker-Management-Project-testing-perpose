package com.LockerService.DrawerRelease.Security;

import com.LockerService.DrawerRelease.Entity.Role;
import com.LockerService.DrawerRelease.Entity.User;
import com.LockerService.DrawerRelease.Repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepo.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("username not found with username or email:"+ usernameOrEmail));

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(),user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection< ?extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
