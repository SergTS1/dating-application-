package com.date.datingapp.boundary.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CreateUserParam {

    String name;
    String gender;
    String interests;
    String email;
    String password;

}
