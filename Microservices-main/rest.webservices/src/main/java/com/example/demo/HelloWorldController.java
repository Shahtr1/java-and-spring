package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path="/hello-world-i18n")
	public String helloWorldi18n() {
		return messageSource.getMessage("good.morning.message", null,"Default message", LocaleContextHolder.getLocale());
	}
}
