package com.ervingorospe.grab_user_service.model.DTO;

import jakarta.validation.constraints.NotNull;

public record UserEmailRequestDTO(
        @NotNull(message = "Email can't be empty")
        String email
) {
}
