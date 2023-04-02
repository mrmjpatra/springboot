package com.nt.processor;



import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nt.model.ExamResult;


@Component
public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult> {

	public ExamResultItemProcessor() {
		System.out.println("ExamResultItemProcessor.ExamResultItemProcessor()");
	}

	@Override
	public ExamResult process(ExamResult item) throws Exception {
		
		if (item.getPercentage() >= 90) {
			
			return item;
		} else {
			return null;
		}
	}
}
