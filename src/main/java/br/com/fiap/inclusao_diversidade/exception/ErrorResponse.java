package br.com.fiap.inclusao_diversidade.exception;

import java.time.LocalDateTime;

// Classe de resposta de erro padrão
class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse(int status, String message, LocalDateTime timestamp, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    // Getters
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getPath() { return path; }
}