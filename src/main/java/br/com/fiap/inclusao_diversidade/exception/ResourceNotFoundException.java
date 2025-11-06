package br.com.fiap.inclusao_diversidade.exception;

// Exceção para recurso não encontrado (404)
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String resourceName, Long id) {
    super(String.format("%s com ID %d não foi encontrado(a)", resourceName, id));
  }
}