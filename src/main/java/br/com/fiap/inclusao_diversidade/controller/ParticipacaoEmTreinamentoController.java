package br.com.fiap.inclusao_diversidade.controller;

import br.com.fiap.inclusao_diversidade.dto.ParticipacaoEmTreinamentoRequestDTO;
import br.com.fiap.inclusao_diversidade.dto.ParticipacaoEmTreinamentoResponseDTO;
import br.com.fiap.inclusao_diversidade.service.ParticipacaoEmTreinamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/participacoes")
public class ParticipacaoEmTreinamentoController {

    private final ParticipacaoEmTreinamentoService participacaoService;

    public ParticipacaoEmTreinamentoController(ParticipacaoEmTreinamentoService participacaoService) {
        this.participacaoService = participacaoService;
    }

    @GetMapping
    public ResponseEntity<List<ParticipacaoEmTreinamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(participacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipacaoEmTreinamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return participacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ParticipacaoEmTreinamentoResponseDTO> criar(@Valid @RequestBody ParticipacaoEmTreinamentoRequestDTO dto) {
        ParticipacaoEmTreinamentoResponseDTO salvo = participacaoService.salvar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(salvo.id()).toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipacaoEmTreinamentoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ParticipacaoEmTreinamentoRequestDTO dto) {
        return participacaoService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        participacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}