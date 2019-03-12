package com.java.xdd.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<String, String> {
    
    @Override
    public String process(String data) throws Exception {
        return data.toUpperCase();
    }
    
}