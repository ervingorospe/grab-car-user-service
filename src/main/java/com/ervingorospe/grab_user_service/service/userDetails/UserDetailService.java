package com.ervingorospe.grab_user_service.service.userDetails;

import com.ervingorospe.grab_user_service.model.DTO.UserProfileDTO;
import com.ervingorospe.grab_user_service.model.entity.User;
import com.ervingorospe.grab_user_service.model.entity.UserDetails;

public interface UserDetailService {
    UserProfileDTO update(UserDetails userDetails, UserProfileDTO profile);
}
