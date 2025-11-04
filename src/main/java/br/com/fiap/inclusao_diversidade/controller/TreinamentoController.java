package br.com.fiap.inclusao_diversidade.controller;

import br.com.fiap.inclusao_diversidade.model.Treinamento;
import br.com.fiap.inclusao_diversidade.service.TreinamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treinamentos")
public class TreinamentoController {

    private final TreinamentoService treinamentoService;

    public TreinamentoController(TreinamentoService treinamentoService) {
        this.treinamentoService = treinamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Treinamento>> listarTodos() {
        return ResponseEntity.ok(treinamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treinamento> buscarPorId(@PathVariable Long id) {
        return treinamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Treinamento> criar(@Valid @RequestBody Treinamento treinamento) {
        return ResponseEntity.ok(treinamentoService.salvar(treinamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treinamento> atualizar(@PathVariable Long id,@Valid @RequestBody Treinamento treinamento) {
        return treinamentoService.buscarPorId(id)
                .map(t -> {
                    treinamento.setId(id);
                    return ResponseEntity.ok(treinamentoService.salvar(treinamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        treinamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
