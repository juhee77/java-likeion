package com.example.contents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.contents.entity.UserEntity;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 저장 테스트")
    public void userSaveTest() {
        //given
        String username = "juhee";
        UserEntity user = new UserEntity();
        user.setUsername(username);

        //when
        UserEntity save = userRepository.save(user);

        //then
        assertNotNull(user.getId());
        assertEquals(username, save.getUsername()); //기대하는 값 , 테스트 하는 값
    }

    @Test
    @DisplayName("새 UserEntity 를 데이터베이스에 추가 실패")
    public void testSaveNewFail() {
        // given: username을 가지고 UserEntity를 먼저 save
        String username = "jeeho.dev";
        UserEntity userGiven = new UserEntity();
        userGiven.setUsername(username);
        userRepository.save(userGiven);

        // when: 같은 username을 가진 새 UserEntity save 시도
        UserEntity user = new UserEntity();
        user.setUsername(username);

        // when-then: 예외 발생
        assertThrows(Exception.class, () -> userRepository.save(user));
    }

    @Test
    @DisplayName("username으로 UserEntity 찾기")
    public void testFindByUsername() {
        // given: 검색할 UserEntity 미리 생성
        String username = "jeeho.dev";
        UserEntity userGiven = new UserEntity();
        userGiven.setUsername(username);
        userRepository.save(userGiven);

        // when: userRepository.findByUsername()
        Optional<UserEntity> optionalUser
                = userRepository.findByUsername(username);

        // then: Optional.isPresent(), username == username
        assertTrue(optionalUser.isPresent());
        assertEquals(username, optionalUser.get().getUsername());
    }
}