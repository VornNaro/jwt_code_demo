package com.kshrd.btb_jwt_class_demo.service;

import com.kshrd.btb_jwt_class_demo.model.entity.UserInfo;
import com.kshrd.btb_jwt_class_demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userRepository.findUserByUserName(username);
        System.out.println(userInfo);
        if (userInfo == null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return userInfo;
    }
}
