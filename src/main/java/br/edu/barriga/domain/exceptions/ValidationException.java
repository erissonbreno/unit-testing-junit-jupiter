package br.edu.barriga.domain.exceptions;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 5762536219851363660L;

    public ValidationException(String message) {
        super(message);
    }
}
