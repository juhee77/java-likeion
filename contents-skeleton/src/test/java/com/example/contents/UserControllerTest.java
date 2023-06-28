package com.example.contents;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.contents.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    // Controller가 있을때 HTTP 요청이 보내졌다 가정해주는 객체이다.
    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() { //모든 테스트 이전에 한번씩 작동했으면 좋겠다.
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void createTest() throws Exception {
        //given
        UserDto requestUserDto = new UserDto();
        String username = "juhee";
        requestUserDto.setUsername(username);

        Long userid = 1L;
        UserDto responseDto = new UserDto();
        responseDto.setId(userid);
        responseDto.setUsername(username);

        when(userService.createUser(requestUserDto)).thenReturn(responseDto);

        //when
        // perform : HTTP 요청을 보낸것을 시뮬레이션 해서 controller test
        ResultActions perform = mockMvc.perform(post("/users")
                .content(JsonUtil.toJson(requestUserDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then 이거 위에다 넣어도 된다.(필터 체인 형식~~)
        perform.andExpectAll(
                status().is2xxSuccessful(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.username", is(username)),
                jsonPath("$.id", notNullValue())
        );

    }


}