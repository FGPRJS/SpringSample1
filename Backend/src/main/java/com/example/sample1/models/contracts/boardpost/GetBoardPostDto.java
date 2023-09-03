package com.example.sample1.models.contracts.boardpost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBoardPostDto {
    public long id;
    public String title;
    public String content;
}
