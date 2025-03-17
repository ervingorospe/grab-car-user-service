package com.ervingorospe.grab_user_service.service.user;

import com.ervingorospe.grab_user_service.model.DTO.UserDTO;
import com.ervingorospe.grab_user_service.model.DTO.UserProfileDTO;

public interface UserService {
    UserProfileDTO updateProfile(UserProfileDTO profile, String id);
    UserDTO findByEmail(String email);
    UserDTO findById(String id);
}
