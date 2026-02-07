package com.yenmin.proton.setting.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageProperties {
	/**
	 * 
	 * This finds messages automatically found from src/main/resources (files named
	 * messages_*.properties)
	 * 
	 */
	@Bean(name= {"messageSource"})
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("messages");
		return messageSource;
	}

}
