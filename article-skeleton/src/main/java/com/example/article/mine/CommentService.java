//package com.example.article.miny;
//
//import com.example.article.ArticleRepository;
//import com.example.article.entity.CommentEntity;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.example.article.miny.CommentDto.fromEntity;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//@Slf4j
//public class CommentService {
//    private final ArticleRepository articleRepository;
//    private final CommentRepository commentRepository;
//
//    //POST /articles/{articleId}/comments : 게시글에 댓글 추가
//    @Transactional
//    public CommentDto createComment(Long articleId, CommentDto commentDto) {
//        checkArticleValidation(articleId);
//
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setArticleId(articleId);
//        commentEntity.setWriter(commentDto.getWriter());
//        commentEntity.setContent(commentDto.getContent());
//        return fromEntity(commentRepository.save(commentEntity));
//    }
//
//    //- GET /articles/{articleId}/comments : 게시글의 전체 댓글 조회
//
//    public List<CommentDto> readCommentsByArticle(Long articleId) {
//        checkArticleValidation(articleId);
//        return commentRepository.findAllByArticleId(articleId).stream().map(CommentDto::fromEntity).toList();
//    }
//    //- PUT /articles/{articleId}/comments/{commentId} : 댓글 수정
//
//    @Transactional
//    public CommentDto updateComment(Long commentId, CommentDto commentDto) {
//        CommentEntity updatedComment = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        updatedComment.setWriter(commentDto.getWriter());
//        updatedComment.setContent(commentDto.getContent());
//        return fromEntity(updatedComment);
//    }
//    //- DELETE /articles/{articleId}/comments/{commentId} : 댓글 삭제
//
//    @Transactional
//    public void deleteComment(Long commentId) {
//        Optional<CommentEntity> find = commentRepository.findById(commentId);
//        log.info("{}", find);
//        if (!find.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);//찾는 객체가 없는 경우
//        }
//        commentRepository.delete(find.get());
//    }
//
//    private void checkArticleValidation(Long articleId) {
//        if (!articleRepository.existsById(articleId)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }
//
//}
