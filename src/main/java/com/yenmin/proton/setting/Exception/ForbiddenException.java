package com.yenmin.proton.setting.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	public ForbiddenException() {
		super();
	}

	public ForbiddenException(String string) {
		super(string);
	}

	public String getMessage() {
		String message = super.getMessage();
		if (message == null) {
			return getExtendedNPEMessage();
		}
		return message;
	}

	private native String getExtendedNPEMessage();

	private static final long serialVersionUID = 1L;
}
