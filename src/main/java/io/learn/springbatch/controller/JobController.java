package io.learn.springbatch.controller;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobRegistry jobRegistry;

    @GetMapping("/launch")
    public void launchJob(){
        JobParameters jobParameters = new JobParametersBuilder().addLong("statAt", System.currentTimeMillis()).toJobParameters();
        try{
            jobLauncher.run(jobRegistry.getJob("Import customer"), jobParameters);
        } catch (JobInstanceAlreadyCompleteException | NoSuchJobException | JobExecutionAlreadyRunningException | JobParametersInvalidException | JobRestartException e) {
            e.printStackTrace();
        }
    }
}
