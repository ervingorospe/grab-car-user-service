package com.ervingorospe.grab_user_service.model.DTO;

import com.ervingorospe.grab_user_service.model.entity.UserDetails;
import com.ervingorospe.grab_user_service.validate.birthdate.MinAge;
import com.ervingorospe.grab_user_service.validate.phoneNumber.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserProfileDTO(
        @NotNull(message = "Firstname can't be empty")
        @JsonProperty("first_name")
        String firstName,

        @NotNull(message = "Lastname can't be empty")
        @JsonProperty("last_name")
        String lastName,

        @NotNull(message = "Birthdate can't be empty")
        @MinAge(message = "User must be 10 years old and above")
        @JsonProperty("birth_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate birthDate,

        @NotNull(message = "Provide a Contact Number")
        @PhoneNumber(message = "Invalid Phone Number")
        @JsonProperty("contact_number")
        String contactNumber
) {
    public UserProfileDTO(UserDetails userDetails) {
        this(
            userDetails.getFirstName(),
            userDetails.getLastName(),
            userDetails.getBirthDate(),
            userDetails.getContactNumber()
        );
    }
}
