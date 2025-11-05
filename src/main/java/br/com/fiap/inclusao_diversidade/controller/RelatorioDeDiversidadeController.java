package br.com.fiap.inclusao_diversidade.controller;

import br.com.fiap.inclusao_diversidade.DTO.RelatorioDeDiversidadeRequestDTO;
import br.com.fiap.inclusao_diversidade.DTO.RelatorioDeDiversidadeResponseDTO;
import br.com.fiap.inclusao_diversidade.service.RelatorioDeDiversidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioDeDiversidadeController {

    private final RelatorioDeDiversidadeService relatorioService;

    public RelatorioDeDiversidadeController(RelatorioDeDiversidadeService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public ResponseEntity<List<RelatorioDeDiversidadeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(relatorioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDeDiversidadeResponseDTO> buscarPorId(@PathVariable Long id) {
        return relatorioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RelatorioDeDiversidadeResponseDTO> criar(@Valid @RequestBody RelatorioDeDiversidadeRequestDTO dto) {
        RelatorioDeDiversidadeResponseDTO salvo = relatorioService.salvar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(salvo.id()).toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelatorioDeDiversidadeResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody RelatorioDeDiversidadeRequestDTO dto) {
        return relatorioService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}