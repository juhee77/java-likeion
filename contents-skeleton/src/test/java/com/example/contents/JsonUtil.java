package com.example.contents;

import com.fasterxml.jackson.annotation.JsonInclude; //잭슨을 사용한다.
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtil {
    //요청을 보내는 단계에서의 주어진 객체를 JSON 형식으로 직렬화하는 메서드
    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
