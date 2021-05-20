package ru.itmo.blss.advertisement.quartz;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import ru.itmo.blss.advertisement.service.PostAdvertService;

@Component
@AllArgsConstructor
@Slf4j
public class PostAdvertJob implements Job {
    private final PostAdvertService postAdvertService;
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Fire job!");
        postAdvertService.execute();
    }
}
