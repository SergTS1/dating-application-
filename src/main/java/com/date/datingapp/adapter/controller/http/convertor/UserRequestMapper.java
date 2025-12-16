package com.date.datingapp.adapter.controller.http.convertor;


import com.date.datingapp.adapter.controller.http.request.CreateUserRequest;
import com.date.datingapp.boundary.model.CreateUserParam;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {

    public CreateUserParam toParam(CreateUserRequest request) {
        if (request == null || request.getData() == null || request.getData().getAttributes() == null) {
            return null;
        }
        return toCreateUserParams(request.getData().getAttributes());
    }

    private CreateUserParam toCreateUserParams(CreateUserRequest.Attributes attributes) {
        if (attributes == null) {
            return null;
        }

        CreateUserParam param = new CreateUserParam();
        param.setName(attributes.getName());
        param.setEmail(attributes.getEmail());
        param.setPassword(attributes.getPassword());
        param.setGender(attributes.getGender());
        param.setInterests(attributes.getInterests());

        return param;
    }
}
