package com.ervingorospe.grab_user_service.model.DTO;

import com.ervingorospe.grab_user_service.model.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String email,
        User.Role role,
        LocalDateTime createdAt,
        Boolean active,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String contactNumber
) {
    // Constructor accepting only User (extracting details from User entity)
    public UserDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getUserRole(),
                user.getCreatedAt(),
                user.getActive(),
                user.getUserDetails().getFirstName(),
                user.getUserDetails().getLastName(),
                user.getUserDetails().getBirthDate(),
                user.getUserDetails().getContactNumber()
        );
    }
}
