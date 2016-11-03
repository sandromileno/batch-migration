package br.com.m4u.migration.config;

import br.com.m4u.migration.reload.model.ScheduledReload;
import br.com.m4u.migration.reload.processor.ScheduledReloadItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;


/**
 * Created by sandro on 03/11/16.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<ScheduledReload> reader() {
        FlatFileItemReader<ScheduledReload> reader = new FlatFileItemReader<ScheduledReload>();
        reader.setResource(new ClassPathResource("scheduled-reload-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<ScheduledReload>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "msisdn", "amount", "channel", "anniversary" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ScheduledReload>() {{
                setTargetType(ScheduledReload.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ScheduledReloadItemProcessor processor() {
        return new ScheduledReloadItemProcessor();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<ScheduledReload, ScheduledReload> chunk(10)
                .reader(reader())
                .processor(processor())
                .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}