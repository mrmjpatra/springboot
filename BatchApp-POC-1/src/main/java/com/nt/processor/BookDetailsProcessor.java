package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BookDetailsProcessor implements ItemProcessor<String, String> {
	public BookDetailsProcessor() {
		System.out.println("BookDetailsProcessor.BookDetailsProcessor()");
	}
	
	@Override
	public String process(String item) throws Exception {
		String bookWithTitle=null;
		System.out.println("BookDetailsProcessor.process()");
		if (item.equalsIgnoreCase("CRJ")) {
			bookWithTitle=item+" by HK Sir";
		}else if (item.equalsIgnoreCase("TIJ")) {
			bookWithTitle=item+" by BE Sir";
		}else if (item.equalsIgnoreCase("HFJ")) {
			bookWithTitle=item+" by KS";
		}else if (item.equalsIgnoreCase("EJ")) {
			bookWithTitle=item+" by JB";
		}else if (item.equalsIgnoreCase("BBJ")) {
			bookWithTitle=item+" by RNR";
		}
		
		return bookWithTitle;
		
	}

}
