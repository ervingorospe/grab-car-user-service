package com.ervingorospe.grab_user_service.service.userDetails;

import com.ervingorospe.grab_user_service.model.DTO.UserProfileDTO;
import com.ervingorospe.grab_user_service.model.entity.User;
import com.ervingorospe.grab_user_service.model.entity.UserDetails;
import com.ervingorospe.grab_user_service.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService{
    private UserDetailsRepo repository;

    @Autowired
    public UserDetailServiceImpl(UserDetailsRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserProfileDTO update(UserDetails userDetails, UserProfileDTO profile) {
        userDetails.setFirstName(profile.firstName());
        userDetails.setLastName(profile.lastName());
        userDetails.setBirthDate(profile.birthDate());
        userDetails.setContactNumber(profile.contactNumber());

        return new UserProfileDTO(repository.save(userDetails));
    }
}
