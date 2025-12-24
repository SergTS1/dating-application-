package com.date.datingapp.it;

import com.date.datingapp.adapter.controller.http.request.CreateUserRequest;
import com.date.datingapp.adapter.controller.http.response.CreateUserResponse;
import com.date.datingapp.adapter.controller.http.response.GetUserResponse;
import com.date.datingapp.base.BaseMongoIT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
class UserControllerIT extends BaseMongoIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_user() throws Exception {
        //given
        CreateUserRequest.Attributes attributes = new CreateUserRequest.Attributes();
        attributes.setEmail("test@mail.com");
        attributes.setPassword("Password123");
        attributes.setName("Sergey");
        attributes.setGender("male");
        attributes.setInterests("soccer");

        CreateUserRequest.UserData data = new CreateUserRequest.UserData();
        data.setAttributes(attributes);

        CreateUserRequest request = new CreateUserRequest();
        request.setData(data);

        //when
        String createResponseJson = mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        CreateUserResponse createResponse = objectMapper.readValue(createResponseJson, CreateUserResponse.class);

        UUID userUuid = createResponse.getData().getUuid();
        assertThat(userUuid).isNotNull();

    }

    @Test
    void should_get_user_by_uuid() throws Exception {
        //given
        CreateUserRequest.Attributes attributes = new CreateUserRequest.Attributes();
        attributes.setEmail("test+" + UUID.randomUUID() + "@mail.com");
        attributes.setPassword("Password123");
        attributes.setName("Sergey");
        attributes.setGender("MALE");
        attributes.setInterests("soccer");

        CreateUserRequest.UserData data = new CreateUserRequest.UserData();
        data.setAttributes(attributes);

        CreateUserRequest request = new CreateUserRequest();
        request.setData(data);

        String createResponseJson = mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CreateUserResponse createResponse = objectMapper.readValue(createResponseJson, CreateUserResponse.class);
        UUID userUuid = createResponse.getData().getUuid();

        //when
        String getResponseJson = mockMvc.perform(get("/api/v1/user/{uuid}", userUuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        GetUserResponse getUserResponse = objectMapper.readValue(getResponseJson, GetUserResponse.class);

        assertThat(getUserResponse.getData().getUuid()).isEqualTo(userUuid);
        assertThat(getUserResponse.getData().getAttributes().getEmail()).isEqualTo(attributes.getEmail());
        assertThat(getUserResponse.getData().getAttributes().getName()).isEqualTo(attributes.getName());
        assertThat(getUserResponse.getData().getAttributes().getGender()).isEqualTo(attributes.getGender());
        assertThat(getUserResponse.getData().getAttributes().getInterests()).isEqualTo(attributes.getInterests());
    }
}
