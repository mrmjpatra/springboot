package com.nt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.service.IPurchaseOrder;

@SpringBootApplication
public class SpringBootMailApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(SpringBootMailApplication.class, args);
		IPurchaseOrder order=ctx.getBean("purchaseservice",IPurchaseOrder.class);
		
		try {
			String msg=order.purchase(new String[] {"tshirt","paint","trouser"},
									new double[] {5000,6000,1000},
									new String[] {"mrmjpatra@gmail.com","kritikumarb@gmail.com"});
			System.out.println(msg);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		((ConfigurableApplicationContext) ctx).close();
		
	}

}
