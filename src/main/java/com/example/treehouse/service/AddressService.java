package com.example.treehouse.service;

import com.example.treehouse.dto.AddressRequest;

public interface AddressService {
    AddressResponse createAddress(AddressRequest addressRequest);
}
