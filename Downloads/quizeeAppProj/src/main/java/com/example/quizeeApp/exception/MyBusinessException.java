package com.example.quizeeApp.exception;

public class MyBusinessException  extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public MyBusinessException(String message) {
        super(message);
    }

}
