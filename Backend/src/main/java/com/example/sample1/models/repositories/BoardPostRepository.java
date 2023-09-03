package com.example.sample1.models.repositories;

import com.example.sample1.models.domains.BoardPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardPostRepository
        extends CrudRepository<BoardPost, Long> {
    List<BoardPost> findBoardPostById(long id);
    List<BoardPost> findBoardPostsByIdGreaterThanEqual(long id);
    List<BoardPost> findBoardPostsByIdLessThanEqualOrderByIdDesc(long id);
}
