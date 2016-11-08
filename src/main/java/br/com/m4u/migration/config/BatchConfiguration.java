package br.com.m4u.migration.config;

import br.com.m4u.migration.reload.model.ScheduledReload;
import br.com.m4u.migration.reload.post.processor.ResponseProcessor;
import br.com.m4u.migration.reload.processor.ScheduledReloadItemProcessor;
import br.com.m4u.migration.reload.util.ScheduledReloadFieldSetMapper;
import br.com.m4u.migration.reload.util.ScheduledReloadLineMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
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

    @Value("${file_to_import}")
    private String inputFile;

    @Value("${result_file}")
    private String outputFile;

    @Bean
    public ItemReader<ScheduledReload> reader() {
        FlatFileItemReader<ScheduledReload> reader = new FlatFileItemReader<ScheduledReload>();
        reader.setResource(new FileSystemResource(inputFile));
        reader.setLineMapper(new ScheduledReloadLineMapper() {{
            setFieldSetMapper(new ScheduledReloadFieldSetMapper());
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "msisdn", "amount", "channel", "anniversary" });
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<ScheduledReload, ResponseProcessor> processor() {
        return new ScheduledReloadItemProcessor();
    }

    @Bean
    public ItemWriter<ResponseProcessor> writer() {
        FlatFileItemWriter<ResponseProcessor> writer = new FlatFileItemWriter<ResponseProcessor>();
        writer.setResource(new FileSystemResource(outputFile));
        DelimitedLineAggregator<ResponseProcessor> delLineAgg = new DelimitedLineAggregator<ResponseProcessor>();
        delLineAgg.setDelimiter(",");
        BeanWrapperFieldExtractor<ResponseProcessor> fieldExtractor = new BeanWrapperFieldExtractor<ResponseProcessor>();
        fieldExtractor.setNames(new String[] {"status", "responseBody"});
        delLineAgg.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(delLineAgg);
        return writer;
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
                .<ScheduledReload, ResponseProcessor> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}