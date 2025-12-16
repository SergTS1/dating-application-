package com.date.datingapp.boundary.model;

import lombok.Data;

@Data
public class CreateUserParam {

    private String name;
    private String gender;
    private String interests;
    private String email;
    private String password;

}
