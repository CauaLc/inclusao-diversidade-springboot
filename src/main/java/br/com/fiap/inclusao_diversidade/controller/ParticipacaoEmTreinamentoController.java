package br.com.fiap.inclusao_diversidade.controller;

import br.com.fiap.inclusao_diversidade.model.ParticipacaoEmTreinamento;
import br.com.fiap.inclusao_diversidade.service.ParticipacaoEmTreinamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participacoes")
public class ParticipacaoEmTreinamentoController {

    private final ParticipacaoEmTreinamentoService participacaoService;

    public ParticipacaoEmTreinamentoController(ParticipacaoEmTreinamentoService participacaoService) {
        this.participacaoService = participacaoService;
    }

    @GetMapping
    public ResponseEntity<List<ParticipacaoEmTreinamento>> listarTodos() {
        return ResponseEntity.ok(participacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipacaoEmTreinamento> buscarPorId(@PathVariable Long id) {
        return participacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ParticipacaoEmTreinamento> criar(@RequestBody ParticipacaoEmTreinamento participacao) {
        return ResponseEntity.ok(participacaoService.salvar(participacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipacaoEmTreinamento> atualizar(@PathVariable Long id, @RequestBody ParticipacaoEmTreinamento participacao) {
        return participacaoService.buscarPorId(id)
                .map(p -> {
                    participacao.setId(id);
                    return ResponseEntity.ok(participacaoService.salvar(participacao));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        participacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

