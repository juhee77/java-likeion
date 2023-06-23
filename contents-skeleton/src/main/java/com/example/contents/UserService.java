package com.example.contents;

import com.example.contents.dto.UserDto;
import com.example.contents.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository repository;

    // createUser
    @Transactional
    public UserDto createUser(UserDto dto) {
        if(repository.findByUsername(dto.getUsername()).isPresent())
            throw new RejectedExecutionException();

        UserEntity userEntity = new UserEntity();
        userEntity.setAvatar(dto.getAvatar());
        userEntity.setBio(dto.getBio());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhone(dto.getPhone());
        userEntity.setUsername(dto.getUsername());

        return UserDto.fromEntity(repository.save(userEntity));
    }

    // readUserByUsername
    public UserDto readUserByUsername(String username) {
        return UserDto.fromEntity(repository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    //readOne
    public UserDto readUserById(Long id) {
        return UserDto.fromEntity(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    // updateUser
    public UserDto updateUser(Long id, UserDto dto) {
        Optional<UserEntity> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);


        UserEntity user = optionalUser.get();
        user.setUsername(dto.getUsername());
        user.setBio(dto.getBio());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        return UserDto.fromEntity(user);
    }

    // updateUserAvatar
    @Transactional
    public UserDto updateUserAvatar(Long id, MultipartFile avatarImage) {


        // 1. 유저 존재 확인
        Optional<UserEntity> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        String format = getUserProfilePath(id, avatarImage);

        UserEntity userEntity = optionalUser.get();
        userEntity.setAvatar(format);
        return UserDto.fromEntity(repository.save(userEntity));
    }

    private static String getUserProfilePath(Long id, MultipartFile avatarImage) {
        // media/filename.png
        // media/<업로드 시각>.png
        // 2. 파일을 어디에 업로드 할건지
        // media/{userId}/profile.{파일 확장자}

        // 2-1. 폴더만 만드는 과정
        String profileDir = String.format("media/%d/", id);
        log.info(profileDir);
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 2-2. 확장자를 포함한 이미지 이름 만들기 (profile.{확장자})
        String originalFilename = avatarImage.getOriginalFilename();
        // queue.png => fileNameSplit = {"queue", "png"}
        String[] fileNameSplit = originalFilename.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length - 1];
        String profileFilename1 = "profile." + extension;
        log.info(profileFilename1);

        // 2-3. 폴더와 파일 경로를 포함한 이름 만들기
        String profilePath = profileDir + profileFilename1;
        log.info(profilePath);

        // 3. MultipartFile 을 저장하기
        try {
            Path path = Path.of(profilePath);
            avatarImage.transferTo(path);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String profileFilename = profileFilename1;

        // 4. UserEntity 업데이트
        // http://localhost:8080/static/1/profile.png
        String format = String.format("/static/%d/%s", id, profileFilename);
        log.info(format);
        return format;
    }

}
