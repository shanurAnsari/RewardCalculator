package com.customer.rewards.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer id;

    private String email;

    private String firstname;

    private String lastname;

}
