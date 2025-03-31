package com.ervingorospe.grab_user_service.service.security;

import com.ervingorospe.grab_user_service.model.DTO.UserDTO;
import com.ervingorospe.grab_user_service.model.DTO.UserEmailRequestDTO;
import com.ervingorospe.grab_user_service.repository.UserAddressRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SecurityService {
    private final UserAddressRepo addressRepository;

    @Autowired
    public SecurityService(UserAddressRepo addressRepository) {
        this.addressRepository = addressRepository;
    }

    public boolean isOwnerOfAddress(String addressId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDTO userDTO) {
            UUID userId = userDTO.id();
//            System.out.println("Logged-in User ID: " + userId);

            // Check if the address belongs to the logged-in user
            return addressRepository.existsByIdAndUserId(UUID.fromString(addressId), userId);
        }

        throw new AccessDeniedException("Unauthorized");
    }

    public boolean isOwnerOfAccount(UserEmailRequestDTO userEmailRequestDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Authentication object: {}", principal);

        if (principal instanceof UserDTO userDTO) {
            return userDTO.email().equals(userEmailRequestDTO.email());
        }

        return true;
    }
}
