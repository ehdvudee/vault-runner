package net.glaso.common.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class SimpleBatchJob {

    private final JobLauncher jobLauncher;
    private final Job simpleNextJob;
    private final Job exampleJob;

    @Scheduled(cron = "0 * * * * *")
    public void simpleNextJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("scheduler - simpleNextJob start");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatDate = dateFormat.format(new Date());

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", formatDate)
                .toJobParameters();

        jobLauncher.run(simpleNextJob, jobParameters);
    }

    @Scheduled(cron = "0/60 * * * * *")
    public void exampleJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info("scheduler - exampleJob start");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatDate = dateFormat.format(new Date());

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", formatDate)
                .toJobParameters();

        jobLauncher.run(exampleJob, jobParameters);
    }
}
