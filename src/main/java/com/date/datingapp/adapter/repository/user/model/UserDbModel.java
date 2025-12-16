package com.date.datingapp.adapter.repository.user.model;

import com.date.datingapp.adapter.repository.photo.model.PhotoDbModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@Document(collection = "users")
public class UserDbModel {

    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String name;
    private String gender;
    private String interests;
    private List<PhotoDbModel> photos;
    private String verificationStatus;
    private String premiumStatus;
}
