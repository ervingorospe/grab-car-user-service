package com.ervingorospe.grab_user_service.service.user;

import com.ervingorospe.grab_user_service.handler.error.UserNotFoundException;
import com.ervingorospe.grab_user_service.model.DTO.UserDTO;
import com.ervingorospe.grab_user_service.model.DTO.UserProfileDTO;
import com.ervingorospe.grab_user_service.model.entity.User;
import com.ervingorospe.grab_user_service.repository.UserRepo;
import com.ervingorospe.grab_user_service.service.userDetails.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo repository;
    private final UserDetailService userDetailService;

    @Autowired
    public UserServiceImpl(UserRepo repository, UserDetailService userDetailService) {
        this.repository = repository;
        this.userDetailService = userDetailService;
    }

    @Override
    @PreAuthorize("#id == authentication.principal.id.toString()")
    public UserProfileDTO updateProfile(UserProfileDTO profile, String id) {
        User user = repository.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        return userDetailService.update(user.getUserDetails(), profile);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with EMAIL: " + email));

        return new UserDTO(user);
    }
}
