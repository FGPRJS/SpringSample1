package com.example.sample1.controllers;

import com.example.sample1.models.contracts.boardpost.GetBoardPostDto;
import com.example.sample1.models.contracts.boardpost.PutBoardPostDto;
import com.example.sample1.models.domainmapper.BoardPostMapper;
import com.example.sample1.models.repositories.BoardPostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardPostController {

    private final BoardPostRepository _boardPostRepository;

    @Autowired
    public BoardPostController(
            BoardPostRepository boardPostRepository
    ){
        this._boardPostRepository = boardPostRepository;
    }


    @GetMapping(value = {"/boardpost"})
    public ResponseEntity<List<GetBoardPostDto>> GetBoardPostsById(@RequestParam long startId, @RequestParam long count){
        var targetPosts = this._boardPostRepository
                .findBoardPostsByIdLessThanEqualOrderByIdDesc(startId).stream()
                .limit(count)
                .map(BoardPostMapper.INSTANCE::toBoardPostDto);

        return new ResponseEntity<>(
                targetPosts.toList(),
                HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = {"/boardpost"})
    public ResponseEntity PutBoardPost(@RequestBody PutBoardPostDto targetBoardPost){
        var targetPost = BoardPostMapper.INSTANCE.toBoardPost(targetBoardPost);

        this._boardPostRepository
                .save(targetPost);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
