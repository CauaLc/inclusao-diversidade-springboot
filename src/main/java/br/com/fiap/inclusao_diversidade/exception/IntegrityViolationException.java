package br.com.fiap.inclusao_diversidade.exception;

// Exceção para violação de integridade referencial (409)
public class IntegrityViolationException extends RuntimeException {
    public IntegrityViolationException(String message) {
        super(message);
    }

    public IntegrityViolationException() {
        super("Não é possível realizar esta operação pois existem registros relacionados.");
    }
}
