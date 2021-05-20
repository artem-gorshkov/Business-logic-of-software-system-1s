package ru.itmo.blss.advertisement.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.blss.advertisement.quartz.PostAdvertJob;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
@Slf4j
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail() {

        return newJob().ofType(PostAdvertJob.class).storeDurably().withIdentity(JobKey.jobKey("Qrtz_Job_Detail")).withDescription("Invoke Advert Job service...").build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {

        int frequencyInSec = 10;
        log.info("Configuring trigger to fire every {} seconds", frequencyInSec);

        return newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey("Qrtz_Trigger")).withDescription("Advert trigger").withSchedule(simpleSchedule().withIntervalInSeconds(frequencyInSec).repeatForever()).build();
    }
}
