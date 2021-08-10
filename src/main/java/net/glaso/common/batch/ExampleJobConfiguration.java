package net.glaso.common.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ExampleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job exampleJob() {
        return jobBuilderFactory.get("exampleJob")
                .start(exampleStep())
                .build();
    }

    @Bean
    public Step exampleStep() {
        return stepBuilderFactory.get("step1")
                .<String, Boolean>chunk(2)
                .reader(reader(null))
                .processor(processor(null))
                .writer(writer(null))
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<String> reader(@Value("#{jobParameters[requestDate]}") String requestDate) {
        log.info("exmapleJob - reader");
        Queue<String> items = new LinkedList<String>(Arrays.asList(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        ));

        return items::poll;
    }

    @Bean
    @StepScope
    public ItemProcessor<String, Boolean> processor(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return item -> {
            log.info("exmapleJob - processor");
            if (item instanceof  String) {
                return true;
            } else {
                return false;
            }
        };
    }

    @Bean
    @StepScope
    public ItemWriter<Boolean> writer(@Value("#{jobParameters[requestDate]}") String requestDate) {
        log.info("exmapleJob - writer");
        return items -> {
            for (boolean item : items) {
                System.out.println(item);
            }
        };
    }
}
