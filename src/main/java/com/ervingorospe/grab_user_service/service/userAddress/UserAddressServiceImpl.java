package com.ervingorospe.grab_user_service.service.userAddress;

import com.ervingorospe.grab_user_service.handler.error.AddressNotFoundException;
import com.ervingorospe.grab_user_service.model.DTO.UserAddressDTO;
import com.ervingorospe.grab_user_service.model.entity.User;
import com.ervingorospe.grab_user_service.model.entity.UserAddress;
import com.ervingorospe.grab_user_service.repository.UserAddressRepo;
import com.ervingorospe.grab_user_service.service.user.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAddressServiceImpl implements UserAddressService{
    private final UserAddressRepo repository;
    private final UserServiceImpl userService;

    public UserAddressServiceImpl(UserAddressRepo repository, UserServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    @PreAuthorize("@securityService.isOwnerOfAddress(#id)")
    public UserAddressDTO updateAddress(UserAddressDTO addressDTO, String id) {
        UserAddress address = repository.findById(UUID.fromString(id)).orElseThrow(() -> new AddressNotFoundException("Address with ID: " + id + " does not exist"));

        address.setLabel(addressDTO.label());
        address.setStreet(addressDTO.street());
        address.setCity(addressDTO.city());
        address.setState(addressDTO.state());
        address.setPostalCode(addressDTO.postalCode());
        address.setCountry(addressDTO.country());
        address.setLatitude(addressDTO.latitude());
        address.setLongitude(addressDTO.longitude());

        return new UserAddressDTO(repository.save(address));
    }

    @Override
    @PreAuthorize("#id == authentication.principal.id.toString()")
    public UserAddressDTO saveAddress(UserAddressDTO addressDTO, String id) {
        User user = userService.findById(UUID.fromString(id));
        UserAddress newAddress = new UserAddress(addressDTO);
        newAddress.setUser(user);

        UserAddress savedAddress = repository.save(newAddress);
        return new UserAddressDTO(savedAddress);
    }
}
