package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@SpringBootApplication
public class BootMvcProj07WishMsgAppViewResolverApplication {

    @Bean
    public ViewResolver createRBVResolver() {
    	ResourceBundleViewResolver resolver=new ResourceBundleViewResolver();
    	resolver.setBasename("com/nt/commons/views");
    	return resolver;
    	
    }
	
	public static void main(String[] args) {
		SpringApplication.run(BootMvcProj07WishMsgAppViewResolverApplication.class, args);
	}

}
