package com.jxl.batch.demo.bean.moretable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.jxl.jpa.bean.ADInfomation;

public class AdinItemProcessor implements ItemProcessor<ADInfomation, ADInfomation> {

    private static final Logger log = LoggerFactory.getLogger(AdinItemProcessor.class);

    @Override
    public ADInfomation process(final ADInfomation adInfomation) throws Exception {

    	log.info("process:" + adInfomation);
//        log.info("process:" + adInfomation.getEncounter());
//        log.info("process:" + adInfomation.getCharges().getTypeZF());
//        log.info("process:" + adInfomation.getDiagnosis().size());
//        log.info("process:" + adInfomation.getProcedures().size());
        return adInfomation;
    }

}