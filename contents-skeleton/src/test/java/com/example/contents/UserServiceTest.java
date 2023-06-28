package com.example.contents;

import static org.mockito.Mockito.when;

import com.example.contents.dto.UserDto;
import com.example.contents.entity.UserEntity;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // UserDto(id==null)를 입력받아 UserDto(id!=null)를 반환
    @Test
    @DisplayName("UserDto로 createUser")
    public void testCreateUser() {
        //given
        String username = "juhee";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        Long userId = 1L;
        UserEntity userEntityOut = new UserEntity();
        userEntityOut.setId(userId);
        userEntityOut.setUsername(username);

        //리파지토리에서 발생할일을 미리 가짜 객체로 매핑한다.
        when(userRepository.save(userEntity)).thenReturn(userEntityOut);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());//중복된 이름이 없다

        //when
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        UserDto result = userService.createUser(userDto);

        //then
        Assertions.assertEquals(username, result.getUsername());
    }

    @Test
    @DisplayName("UserDto로 updateUser")
    public void testMultiPartFile() {
        //given
        String username = "juhee";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        Long userId = 1L;
        UserEntity userEntityOut = new UserEntity();
        userEntityOut.setId(userId);
        userEntityOut.setUsername(username);

        //리파지토리에서 발생할일을 미리 가짜 객체로 매핑한다.
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntityOut));

        //when
        String modifyUserName = "modify";
        UserDto userDto = new UserDto();
        userDto.setUsername(modifyUserName);
        UserDto result = userService.updateUser(userId, userDto);

        //then
        Assertions.assertEquals(modifyUserName, result.getUsername());
    }
}