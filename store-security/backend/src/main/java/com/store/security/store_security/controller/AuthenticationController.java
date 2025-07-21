package com.store.security.store_security.controller;

import com.store.security.store_security.dto.UserDto;
import com.store.security.store_security.service.IRegistrationService;
import com.store.security.store_security.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication management",
        description = "REST API for authentication management"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final IRegistrationService registrationService;

    private final IUserService userService;

    @Operation(
            summary = "Registration user",
            description = "REST API to registration user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 : CREATED"
    )
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserDto userDto) {
        UserDto result = registrationService.registrationUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @Operation(
            summary = "Authentication user with basic auth",
            description = "REST API to authentication user with basic auth"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 : OK"
    )
    @GetMapping("/user")
    public ResponseEntity<UserDto> userAuth()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.findUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

}
