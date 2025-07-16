package com.store.security.store_security.controller;

import com.store.security.store_security.dto.UserDto;
import com.store.security.store_security.service.IRegistrationService;
import com.store.security.store_security.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final IRegistrationService registrationService;

    private final IUserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserDto userDto) {
        UserDto result = registrationService.registrationUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> userAuth()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.findUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

}
