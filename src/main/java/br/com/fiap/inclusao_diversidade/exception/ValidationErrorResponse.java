package br.com.fiap.inclusao_diversidade.exception;

import java.time.LocalDateTime;
import java.util.Map;

// Classe de resposta de erro de validação
class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> errors;

    public ValidationErrorResponse(int status, String message, LocalDateTime timestamp,
                                   String path, Map<String, String> errors) {
        super(status, message, timestamp, path);
        this.errors = errors;
    }

    public Map<String, String> getErrors() { return errors; }
}