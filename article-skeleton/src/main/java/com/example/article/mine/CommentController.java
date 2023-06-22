//package com.example.article.miny;
//
//import com.example.article.dto.CommentDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@Slf4j
//@RequestMapping("/articles/{articleId}/comments")
//@RequiredArgsConstructor
//public class CommentController {
//    private final CommentService commentService;
//
//    //POST /articles/{articleId}/comments : 게시글에 댓글 추가
//    @PostMapping
//    public CommentDto createComment(@PathVariable("articleId") Long articleId, @RequestBody CommentDto commentDto) {
//        return commentService.createComment(articleId, commentDto);
//    }
//
//    //GET /articles/{articleId}/comments : 게시글의 전체 댓글 조회
//    @GetMapping
//    public List<CommentDto> readCommentByArticle(@PathVariable("articleId") Long articleId) {
//        return commentService.readCommentsByArticle(articleId);
//    }
//
//    //PUT /articles/{articleId}/comments/{commentId} : 댓글 수정
//    @PutMapping("/{commentId}")
//    public CommentDto updateComment(@PathVariable("articleId") Long articleId, @PathVariable("commentId") Long commentId, @RequestBody CommentDto commentDto) {
//        return commentService.updateComment(commentId, commentDto);
//    }
//
//    //DELETE /articles/{articleId}/comments/{commentId} : 댓글 삭제
//    @DeleteMapping("/{commentId}")
//    public void deleteComment(@PathVariable("articleId") Long articleId, @PathVariable("commentId") Long commentId) {
//        commentService.deleteComment(commentId);
//    }
//
//}
