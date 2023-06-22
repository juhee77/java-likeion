package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository repository;

    @Transactional
    public ArticleDto createArticle(ArticleDto dto) {
        return ArticleDto.fromEntity(repository.save(ArticleEntity.fromDto(dto)));
    }

    public ArticleDto readArticle(Long id) {
        return ArticleDto.fromEntity(repository.findById(id).orElseThrow(RuntimeException::new));
    }

    public ArticleDto readArticleOptional(Long id) {
        Optional<ArticleEntity> optionalArticle = repository.findById(id);
        // 채워 넣어 보기
        // optional 안에 Aritcle이 들어있으면
        if (optionalArticle.isPresent())
            // DTO로 전환 후 반환
            return ArticleDto.fromEntity(optionalArticle.get());
            // 아니면 404
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public List<ArticleDto> readArticleAll() {
        return repository.findAll().stream().map(ArticleDto::fromEntity).toList();
    }

    @Transactional
    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        ArticleEntity article = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        article.update(dto);
        return ArticleDto.fromEntity(article);
    }

    @Transactional
    public void deleteArticle(Long id) {
        //existById ->  이 메서드는 데이터베이스에 직접 접근하기 때문에 쿼리 성능에 영향을 받을 수 있습니다.
        //var.isPresent() -> Optional 객체의 값이 존재하는지 여부를 확인하는 메서드입니다. Optional은 값의 존재 여부를 나타내는 컨테이너 객체입니다. isPresent 메서드는 Optional 객체에 값이 있는지 확인하고, 결과는 true 또는 false로 반환됩니다. 이 메서드는 단순히 Optional 객체의 내부 값을 확인하므로, 데이터베이스 쿼리 등의 외부 작업에는 직접적인 영향을 주지 않습니다.
        repository.delete(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    //     아래 방식으로 리팩토링
    public List<ArticleDto> readArticlesPaged() {
        return repository.findTop20ByOrderByIdDesc().stream().map(ArticleDto::fromEntity).toList();
    }

    public List<ArticleDto> readArticleUnderIdPaged(Long id) {
        return repository.findTop20ByIdLessThanOrderByIdDesc(id).stream().map(ArticleDto::fromEntity).toList();
    }

//    public List<ArticleDto> readArticlesPaged() {
//        Pageable pageable = PageRequest.of(0, 20);
//        return repository.findAll(pageable).stream().map(ArticleDto::fromEntity).collect(Collectors.toList());
//    }

    public Page<ArticleEntity> readArticlesPageable() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<ArticleEntity> articleEntities = repository.findAll(pageable);
        return articleEntities;
    }

    public Page<ArticleDto> readArticlePaged(Integer page, Integer limit) {
        // PagingAndSortingRepository 메소드에 전달하는 용도
        // 조회하고 싶은 페이지의 정보를 담는 객체
        // 20개씩 데이터를 나눌때 0번 페이지를 달라고 요청하는 Pageable
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id").descending());
        Page<ArticleEntity> articleEntityPage = repository.findAll(pageable);

        // map: 전달받은 함수를 각 원소에 인자로 전달한 결과를 다시 모아서 Stream으로
        // Page.map: 전달받은 함수를 각 원소에 인자로 전달한 결과를 다시 모아서 Page로
        Page<ArticleDto> articleDtoPage = articleEntityPage.map(ArticleDto::fromEntity);
        return articleDtoPage;
    }


    public Page<ArticleDto> findAllByTitleContains(String query, Integer page) {
        return repository
                .findAllByTitleContains(query, PageRequest.of(page, 20, Sort.by("id").descending()))
                .map(ArticleDto::fromEntity);
    }

    public Page<ArticleDto> findAllByExample(String query, Integer page) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        ArticleEntity probe = new ArticleEntity();
        probe.setTitle(query); //query 생성
        probe.setContent(query);
        Example<ArticleEntity> example = Example.of(probe, matcher); //entity 생성

        Pageable pageable = PageRequest.of(page, 20);
        return repository.findAll(example, pageable).map(ArticleDto::fromEntity);
    }
}
