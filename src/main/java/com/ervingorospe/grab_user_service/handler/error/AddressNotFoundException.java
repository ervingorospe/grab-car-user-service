package com.ervingorospe.grab_user_service.handler.error;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String message) {
        super(message);
    }
}
