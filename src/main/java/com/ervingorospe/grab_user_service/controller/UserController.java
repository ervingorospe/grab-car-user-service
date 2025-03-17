package com.ervingorospe.grab_user_service.controller;

import com.ervingorospe.grab_user_service.config.JwtFilter;
import com.ervingorospe.grab_user_service.model.DTO.UserProfileDTO;
import com.ervingorospe.grab_user_service.service.user.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

//    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/test/{name}")
    public String testApiGateway(@PathVariable String name) {
        return name;
    }

    @PostMapping("/profile/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(@RequestBody @Valid UserProfileDTO profileDTO, @PathVariable String id) {
//        log.info("Checking PreAuthorize: Route ID = {}, Principal = {}", id, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(profileDTO, id));
    }
}
