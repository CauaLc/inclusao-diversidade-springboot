package br.com.fiap.inclusao_diversidade.exception;

// Exceção para dados duplicados (409)
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}