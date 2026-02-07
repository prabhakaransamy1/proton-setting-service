package com.yenmin.proton.setting.pojo;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yenmin.proton.setting.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {
	private String status;
	private String message;
	private Object result;
	private User user;

	public MessageResponse() {
		super();
	}

	public MessageResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public MessageResponse(String status, String message, Object result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}
	
	public MessageResponse(String status, Object result) {
		super();
		this.status = status;
		this.result = result;
	}
	
	public ResponseEntity<Object> success(String code, Object[] args, Object result) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("messages");
		this.status = messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale());
		this.message = messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
		this.result = result;
		return ResponseEntity.ok(this);
	}

	public ResponseEntity<Object> success(String code, Object[] args) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("messages");
		this.status = messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale());
		this.message = messageSource.getMessage(code, args, LocaleContextHolder.getLocale());

		return ResponseEntity.ok(this);
	}

	public ResponseEntity<Object> failure(String code, Object[] args) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("messages");
		this.status = messageSource.getMessage("code.error", null, LocaleContextHolder.getLocale());
		this.message = messageSource.getMessage(code, args, LocaleContextHolder.getLocale());

		return ResponseEntity.badRequest().body(this);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
