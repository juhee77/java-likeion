package com.lahee.producer;

import com.lahee.producer.job.dto.JobRequest;
import com.lahee.producer.job.dto.JobStatus;
import com.lahee.producer.job.dto.JobTicket;
import com.lahee.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProducerController {
    private final ProducerService producerService;

    @PostMapping("/make-job")
    public JobTicket makeJob(@RequestBody JobRequest request){
        return producerService.send(request.getFilename());
    }

    @GetMapping("/get-job")
    public JobStatus getJob(@RequestParam("job") String jobId) {
        return producerService.getJobStatus(jobId);
    }

}
