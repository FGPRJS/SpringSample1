package com.example.sample1.models.domainmapper;

import com.example.sample1.models.contracts.boardpost.GetBoardPostDto;
import com.example.sample1.models.contracts.boardpost.PutBoardPostDto;
import com.example.sample1.models.domains.BoardPost;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardPostMapper {
    BoardPostMapper INSTANCE = Mappers.getMapper(BoardPostMapper.class);

    GetBoardPostDto toBoardPostDto(BoardPost boardPost);
    BoardPost toBoardPost(PutBoardPostDto boardPost);
}
