package com.ervingorospe.grab_user_service.repository;

import com.ervingorospe.grab_user_service.model.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAddressRepo extends JpaRepository<UserAddress, UUID> {
    boolean existsByIdAndUserId(UUID uuid, UUID userId);
}
