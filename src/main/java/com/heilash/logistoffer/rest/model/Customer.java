package com.heilash.logistoffer.rest.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Customer {

    private Long id;
    @NotNull(message = "Field can't be empty")
    private String name;
}
