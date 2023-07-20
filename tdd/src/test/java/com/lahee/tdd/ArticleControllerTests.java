package com.lahee.tdd;

import com.lahee.tdd.controller.ArticleController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTests {
    @InjectMocks
    private ArticleController articleController;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .build();
    }

    @Test
    public void mockMvcIstNotNull() {
        assertNotNull(articleController);
        assertNotNull(mockMvc);
    }

    // 특정 URL에 Query Parameter 가 포함되어 있을 때
    // 응답으로 JSON을 반환한다.
    // 그리고 그 JSON으로 표현된 데이터에는
    // 게시글 데이터가 있으며, 그 제목이 Query Parameter로 전달한
    // 값을 포함한다.
    @Test
    public void queryArticle() throws Exception {
        // given
        String url = "/articles?title=test";

        // when
        ResultActions resultActions = mockMvc.perform(get(url));

        // then
        resultActions.andExpectAll(
                status().is2xxSuccessful(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$[0].title", containsString("test"))
        );
    }
}
