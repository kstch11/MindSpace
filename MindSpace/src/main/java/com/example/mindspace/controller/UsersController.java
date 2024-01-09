package com.example.mindspace.controller;

import com.example.mindspace.api.UserResponse;
import com.example.mindspace.security.CurrentUser;
import com.example.mindspace.security.UserPrincipal;
import com.example.mindspace.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserServiceImpl userService;

    /**
     * Retrieves the profile information of the currently authenticated user.
     *
     * @param userPrincipal The principal of the currently authenticated user.
     * @return ResponseEntity containing the user's profile information and HTTP status code.
     */
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userService.getCurrentUser(userPrincipal), HttpStatus.OK);
    }
}
