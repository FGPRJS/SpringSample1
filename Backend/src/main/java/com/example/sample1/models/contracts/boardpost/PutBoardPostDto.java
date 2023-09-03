package com.example.sample1.models.contracts.boardpost;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutBoardPostDto {
    public String password;
    public String title;
    public String content;
}
