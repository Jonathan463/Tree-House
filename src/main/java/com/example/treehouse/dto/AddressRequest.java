package com.example.treehouse.dto;

import javax.validation.constraints.NotNull;

public class AddressRequest {
    @NotNull
    private String userName;

    @NotNull
    private String streetDetail;

    @NotNull
    private String state;

    @NotNull
    private String city;

    private String zipCode;

    @NotNull
    private String country;
}
