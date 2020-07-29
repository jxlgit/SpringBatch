package com.jxl.batch.demo.map.moretable;

import java.util.List;
import java.util.Map;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

public class MyItemWriter implements ItemWriter<Map<String, Object>> {
    private StepExecution stepExecution;

    public void write(List<? extends Map<String, Object>> items) throws Exception {
        // ...

        ExecutionContext stepContext = this.stepExecution.getExecutionContext();
//        System.out.println(items.get(0).get("first_name"));
        stepContext.putString("queryKey", items.get(0).get("first_name") + "");
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}
