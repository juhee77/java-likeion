package com.example.article;


import com.example.article.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/articles/{articleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    // POST /articles/{articleId}/comments
    @PostMapping
    public CommentDto create(
            @PathVariable("articleId") Long articleId,
            @RequestBody CommentDto dto
    ) {
        return service.createComment(articleId, dto);
    }

    //게시글 댓글 전체 조회
    //GET /articles/{articleId}/comments : 게시글의 전체 댓글 조회
    @GetMapping
    public List<CommentDto> readCommentByArticle(@PathVariable("articleId") Long articleId) {
        return service.readCommentByArticle(articleId);
    }

    ///게시글 댓글 수정
    //PUT /articles/{articleId}/comments/{commentId} : 댓글 수정
    @PutMapping("/{commentId}")
    public CommentDto updateComment(@PathVariable("articleId") Long articleId,
                                    @PathVariable("commentId") Long commentId,
                                    @RequestBody CommentDto commentDto) {
        return service.updateComment(articleId, commentId, commentDto);
    }


    //게시글 댓글 삭제
    //DELETE /articles/{articleId}/comments/{commentId} : 댓글 삭제


}