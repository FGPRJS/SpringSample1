package com.example.sample1.models.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BoardPost {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long id;

    public String password;
    public String title;
    public String content;
}
