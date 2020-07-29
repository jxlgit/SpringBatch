package com.jxl.batch.demo.bean.moretable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.jxl.jpa.bean.People;

public class PeopleItemProcessor implements ItemProcessor<People, People> {

    private static final Logger log = LoggerFactory.getLogger(PeopleItemProcessor.class);

    @Override
    public People process(final People person) throws Exception {

        log.info("process:" + person);
        log.info("process:" + person.getPeopleDepts());

        return person;
    }

}