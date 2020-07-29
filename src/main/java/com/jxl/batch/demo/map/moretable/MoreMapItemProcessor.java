package com.jxl.batch.demo.map.moretable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.jxl.batch.demo.spring.PersonItemProcessor;

public class MoreMapItemProcessor implements ItemProcessor<Map<String, Object>, Map<String, Object>> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Map<String, Object> process(final Map<String, Object> mapData) throws Exception {

        log.info("Map:" + mapData.toString());

        return mapData;
    }

}