package com.yenmin.proton.setting.Exception;

import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import com.mongodb.MongoSocketException;
import com.yenmin.proton.setting.pojo.MessageResponse;

@Component
@ControllerAdvice
public abstract class ExceptionController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ResourceBundleMessageSource messageSource;

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(Exception e) {
		log.error(" handleException Exception" + e.getLocalizedMessage(), e);
		return failure(e.getMessage());
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(HttpMessageConversionException e) {
		log.error(" handleException Exception" + e.getLocalizedMessage(), e);
		return failure("Required Input is not Valid");
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(MissingServletRequestParameterException e) {
		log.error(" handleException Exception" + e.getLocalizedMessage(), e);
		return failure("Required Input is not Valid");
	}

	
	@ExceptionHandler(ForbiddenException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public MessageResponse handleException(ForbiddenException e) {
		log.error(" handleException Exception" + e.getLocalizedMessage(), e);

		return failure(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(MethodArgumentNotValidException exception) {

		String message = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		return failure(message);
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(AuthenticationException exception) {

		return failure(exception.getMessage());
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(MaxUploadSizeExceededException e) {

		log.error(" handleException Exception" + e.getLocalizedMessage(), e);
		return failure("Maximum upload size exceeded,Please limit to 2MB");
	}

	@ExceptionHandler(SizeException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(SizeException e) {
		log.error(" handleException Exception" + e.getLocalizedMessage(), e);
		MessageResponse failure = failure(e.getMessage());
		return failure;
	}

	@ExceptionHandler(FileUploadException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(FileUploadException e) {
		log.error(" handleException Exception" + e.getLocalizedMessage(), e);
		return failure(e.getMessage());
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MessageResponse handleException(MultipartException e) {
		log.error(" handleException Exception" + e.getLocalizedMessage(), e);
		return failure(e.getMessage());
	}

	@ExceptionHandler(MongoSocketException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
	public MessageResponse handleException(MongoSocketException e) {
		log.error("handleException MongoSocketException" + e.getLocalizedMessage(), e);
		return failure(messageSource.getMessage("code.wrong", null, LocaleContextHolder.getLocale()));
	}


	protected MessageResponse response() {
		return new MessageResponse();
	}

	protected MessageResponse failure(String message) {
		MessageResponse response = new MessageResponse();
		response.setStatus(messageSource.getMessage("code.error", null, LocaleContextHolder.getLocale()));
		response.setMessage(message != null ? message
				: messageSource.getMessage("code.techerror", null, LocaleContextHolder.getLocale()));

		return response;
	}

}
