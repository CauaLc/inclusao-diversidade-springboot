package br.com.fiap.inclusao_diversidade.exception;

// Exceção para regras de negócio (422)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}