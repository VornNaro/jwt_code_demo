package com.kshrd.btb_jwt_class_demo.service;

import com.kshrd.btb_jwt_class_demo.model.request.UserRequest;
import com.kshrd.btb_jwt_class_demo.model.response.UserResponse;
import com.kshrd.btb_jwt_class_demo.repository.UserRepository;
import com.kshrd.btb_jwt_class_demo.securityConfig.JwtAuthFilter;
import com.kshrd.btb_jwt_class_demo.securityConfig.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class AuthenticationService {

    private final UserServiceImp userServiceImp;
    private final JwtAuthFilter jwtAuthFilter;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public UserResponse authenticate(UserRequest userRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUserName(),
                        userRequest.getPassword()
                )
        );
        var user = userRepository.findUserByUserName(userRequest.getUserName());
        System.out.println("User Information: " + user);
        var jwtToken = jwtService.generateToken(user);
        return UserResponse.builder().token(jwtToken).build();
    }

}
