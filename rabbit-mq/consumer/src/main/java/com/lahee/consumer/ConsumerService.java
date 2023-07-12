package com.lahee.consumer;

import com.google.gson.Gson;
import com.lahee.consumer.dto.JobPayload;
import com.lahee.consumer.entity.JobEntity;
import com.lahee.consumer.entity.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RabbitListener(queues = "boot.amqp.worker-queue")
@RequiredArgsConstructor
public class ConsumerService {
    private final JobRepository jobRepository;
    private final Gson gson;

    @RabbitHandler
    public void receive(String message) throws InterruptedException {
        JobPayload newJob = gson.fromJson(message, JobPayload.class);
        String jobId = newJob.getJobId();
        log.info("Received Job: {}", jobId);
        Optional<JobEntity> optionalJob = jobRepository.findByJobId(jobId);
        if (optionalJob.isEmpty())
            throw new AmqpRejectAndDontRequeueException(jobId);

        JobEntity jobEntity = optionalJob.get();
        jobEntity.setStatus("PROCESSING");
        jobEntity = jobRepository.save(jobEntity);
        // 처리하는데 시간이 걸림을 가정
        log.info("Start Processing Job: {}", jobId);
        TimeUnit.SECONDS.sleep(5);

        jobEntity.setStatus("DONE");//완료 처리
        jobEntity.setResultPath(String.format("/media/user-uploaded/raw/%s", newJob.getFilename()));
        jobRepository.save(jobEntity);
        log.info("Finished job: {}", jobId);
    }
}