package com.lahee.producer.service;

import com.google.gson.Gson;
import com.lahee.producer.job.JobEntity;
import com.lahee.producer.job.JobRepository;
import com.lahee.producer.job.dto.JobPayload;
import com.lahee.producer.job.dto.JobStatus;
import com.lahee.producer.job.dto.JobTicket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {
    private final JobRepository jobRepository; //지난주의 webtemplate, restTemplate과 같은
    private final RabbitTemplate rabbitTemplate;
    private final Queue jobQueue;
    //객체를 쉽게 JSON문자열로 직렬화 해주는 라이브러리 입니다.
    private final Gson gson;

    //filename을 인자로 받고
    //filename을 바탕으로 JSON으로 작헙화된 정보를
    //QUEUE에 적재한뒤에 사용자에게 JOBTicket을 반환 하는 메서드 입니다.
    public JobTicket send(String filename) {
        //jobid 발행
        String jobId = UUID.randomUUID().toString();
        JobTicket jobTicket = new JobTicket(jobId);

        //job payload 발행
        JobPayload jobPayload = new JobPayload(jobId, filename, String.format("/media/user-uploaded/raw/%s", filename));


        //job entity로 작업 내역 입력 기록
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(jobId);
        jobEntity.setStatus("IDLE");
        jobRepository.save(jobEntity);

        //메세지 브로커에게 메세지 전달하는 방법
        rabbitTemplate.convertAndSend(
                //어떤 큐에 적재할지에 대한 이름
                jobQueue.getName(),
                //메세지 보낼 문자열
                gson.toJson(jobPayload)
        );
        //사용자에게 추후 확인용 Job Ticket 전달
        log.info("Sent job : {}", jobTicket.getJobId());
        return jobTicket;
    }

    // 작업 상태 확인용 메소드
    public JobStatus getJobStatus(String jobId) {
        Optional<JobEntity> optionalJob
                = jobRepository.findByJobId(jobId);
        if (optionalJob.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return JobStatus.fromEntity(optionalJob.get());
    }

}
