package br.com.fiap.inclusao_diversidade.controller;

import br.com.fiap.inclusao_diversidade.DTO.TreinamentoRequestDTO;
import br.com.fiap.inclusao_diversidade.DTO.TreinamentoResponseDTO;
import br.com.fiap.inclusao_diversidade.exception.ResourceNotFoundException;
import br.com.fiap.inclusao_diversidade.service.TreinamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/treinamentos")
public class TreinamentoController {

    private final TreinamentoService treinamentoService;

    public TreinamentoController(TreinamentoService treinamentoService) {
        this.treinamentoService = treinamentoService;
    }

    @GetMapping
    public ResponseEntity<List<TreinamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(treinamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return treinamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Treinamento", id));
    }

    @PostMapping
    public ResponseEntity<TreinamentoResponseDTO> criar(@Valid @RequestBody TreinamentoRequestDTO dto) {
        TreinamentoResponseDTO salvo = treinamentoService.salvar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(salvo.id()).toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinamentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TreinamentoRequestDTO dto) {
        return treinamentoService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Treinamento", id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        treinamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}