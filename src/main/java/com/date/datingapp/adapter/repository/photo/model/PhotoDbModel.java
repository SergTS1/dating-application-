package com.date.datingapp.adapter.repository.photo.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "photo")
public class PhotoDbModel {

    @Id
    private String id;
    private String url;
    private String status;
}
