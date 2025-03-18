package com.ervingorospe.grab_user_service.controller;

import com.ervingorospe.grab_user_service.model.DTO.UserAddressDTO;
import com.ervingorospe.grab_user_service.model.DTO.UserProfileDTO;
import com.ervingorospe.grab_user_service.service.user.UserService;
import com.ervingorospe.grab_user_service.service.userAddress.UserAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserAddressService addressService;

//    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, UserAddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping("/test/{name}")
    public String testApiGateway(@PathVariable String name) {
        return name;
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(@RequestBody @Valid UserProfileDTO profileDTO, @PathVariable String id) {
//        log.info("Checking PreAuthorize: Route ID = {}, Principal = {}", id, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(profileDTO, id));
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<UserAddressDTO> updateUserAddress(@RequestBody @Valid UserAddressDTO addressDTO, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddress(addressDTO, id));
    }

    @PostMapping("/address/create/{id}")
    public ResponseEntity<UserAddressDTO> saveUserAddress(@RequestBody @Valid UserAddressDTO addressDTO, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddress(addressDTO, id));
    }
}
