package com.example.article;


import com.example.article.dto.CommentDto;
import com.example.article.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public CommentDto createComment(Long articleId, CommentDto dto) {
        // articleId를 ID로 가진 ArticleEntity 가 존재 하는지?
        if (!articleRepository.existsById(articleId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);  // 자유롭게 상황대처

        CommentEntity newComment = new CommentEntity();
        newComment.setWriter(dto.getWriter());
        newComment.setContent(dto.getContent());
        newComment.setArticleId(articleId);
        newComment = commentRepository.save(newComment);
        return CommentDto.fromEntity(newComment);
    }

    //게시글 댓글 전체 조회
    public List<CommentDto> readCommentByArticle(Long articleId) {
//        //for-each 이용방식
//        List<CommentEntity> commentEntities = commentRepository.findAllByArticleId(articleId);
//        List<CommentDto> commentDtoList = new ArrayList<>();
//        for (CommentEntity commentEntity : commentEntities) {
//            commentDtoList.add(CommentDto.fromEntity(commentEntity));
//        }
//        return commentDtoList;
//
        //stream 이용 방식
        return commentRepository.findAllByArticleId(articleId).stream().map(CommentDto::fromEntity).toList();
    }


    //게시글 댓글 수정
    //수정 하고자 하는 댓글이 저장한 게시글에 있는지 확인할 목적으로 articleId로 받는다.
    public CommentDto updateComment(Long articleId, Long commentId, CommentDto dto) {
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);

        if (!optionalComment.isPresent()) { //존재 하지 않는경우 NOT_FOUND 예외를 던진다.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        CommentEntity comment = optionalComment.get();
        //대상 댓글의 게시글이 URL의 path로 들어온 게시글의 id와 일치하는지 확인한다.
        if (!articleId.equals(comment.getArticleId())) {
            //다른경우 요청 자체가 잘못된것임을 보내준다.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        comment.setContent(dto.getContent());
        comment.setWriter(dto.getWriter());
        return CommentDto.fromEntity(commentRepository.save(comment));
    }


    //게시글 댓글 삭제
    public void deleteComment(Long articleId, Long commentId) {
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);

        if (!optionalComment.isPresent()) { //존재 하지 않는경우 NOT_FOUND 예외를 던진다.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        CommentEntity comment = optionalComment.get();
        //대상 댓글의 게시글이 URL의 path로 들어온 게시글의 id와 일치하는지 확인한다.
        if (!articleId.equals(comment.getArticleId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentRepository.deleteById(commentId);
    }
}