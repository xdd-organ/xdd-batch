package com.java.xdd.batch.configuration;

import com.java.xdd.batch.aggregator.HelloLineAggregator;
import com.java.xdd.batch.bean.DeviceCommand;
import com.java.xdd.batch.listener.JobCompletionListener;
import com.java.xdd.batch.mapper.HelloLineMapper;
import com.java.xdd.batch.processor.HelloItemProcessor;
import com.java.xdd.batch.processor.Processor;
import com.java.xdd.batch.reader.Reader;
import com.java.xdd.batch.writer.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobCompletionListener listener;
    @Autowired
    private HelloItemProcessor processor;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener)
                .flow(orderStep1()).end().build();
    }

    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<DeviceCommand, DeviceCommand> chunk(1)
                .reader(this.getRead()).processor(processor)
                .writer(this.getWrite()).build();
    }

    //配置写对象
    private FlatFileItemWriter<DeviceCommand> getWrite() {
        FlatFileItemWriter<DeviceCommand> write = new FlatFileItemWriter<>();
        write.setResource(new FileSystemResource("E:\\yidong\\xdd-batch\\src\\main\\resources\\batch-data1.csv"));
        write.setLineAggregator(new HelloLineAggregator());
        return write;
    }

    //配置读对象
    private FlatFileItemReader<DeviceCommand> getRead() {
        FlatFileItemReader<DeviceCommand> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("E:\\yidong\\xdd-batch\\src\\main\\resources\\batch-data.csv"));
        reader.setLineMapper(new HelloLineMapper());
        return reader;
    }

}