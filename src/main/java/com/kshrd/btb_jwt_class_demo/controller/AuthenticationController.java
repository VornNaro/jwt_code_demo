package com.kshrd.btb_jwt_class_demo.controller;

import com.kshrd.btb_jwt_class_demo.model.request.UserRequest;
import com.kshrd.btb_jwt_class_demo.model.response.UserResponse;
import com.kshrd.btb_jwt_class_demo.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    ResponseEntity<UserResponse> authenticateUser(
            @RequestBody UserRequest userRequest
            ){

       return ResponseEntity.ok(authenticationService.authenticate(userRequest));

    }

}
