package com.lahee.file.controller;

import com.lahee.file.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping(value = "/multipart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto multiPart(@RequestParam("name") String name, @RequestParam("photo") MultipartFile multipartFile) throws IOException {
//        Path uploadTo = Path.of("filename.png");
//        multipartFile.transferTo(uploadTo);

//        File file = new File("./filename.png");
//        try (OutputStream outputStream = new FileOutputStream(file)) {
//            byte[] bytes = multipartFile.getBytes();
//            outputStream.write(bytes);
//        }

        //사용자가 받기 위한 처리를 하는 방법
        // 저장할 경로를 생성한다
        Files.createDirectories(Path.of("media"));
        // 저장할 파일이름을 경로를 포함해 지정한다.
        LocalDateTime now = LocalDateTime.now();
//        UUID.randomUUID()
        Path path = Path.of(String.format("media/%s.png", now));
        // 저장한다.
        multipartFile.transferTo(path);

//
//        // 저장할 파일 이름
//        File file = new File("./media/filename.png");
//// 파일에 저장하기 위한 OutputStream
//        try (OutputStream outputStream = new FileOutputStream(file)){
//            // byte[] 데이터를 받는다.
//            byte[] fileBytes = multipartFile.getBytes();
//            // 여기에서 추가작업
//
//            // OutputStream에 MultipartFile의 byte[]를 저장한다.
//            outputStream.write(fileBytes);
//        }

        return new ResponseDto(String.format("/static/%s.png", now));
        //파일 확인 URL을 사용자에게 제공할 수 있다.
    }
}
