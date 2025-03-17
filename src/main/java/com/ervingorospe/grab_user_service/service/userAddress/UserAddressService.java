package com.ervingorospe.grab_user_service.service.userAddress;

import com.ervingorospe.grab_user_service.model.DTO.UserAddressDTO;

public interface UserAddressService {
    UserAddressDTO updateAddress(UserAddressDTO addressDTO, String id);
}
