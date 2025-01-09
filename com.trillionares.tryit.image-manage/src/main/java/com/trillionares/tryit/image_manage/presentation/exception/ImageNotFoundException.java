package com.trillionares.tryit.image_manage.presentation.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
