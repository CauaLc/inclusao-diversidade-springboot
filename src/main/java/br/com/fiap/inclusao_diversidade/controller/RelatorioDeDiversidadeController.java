package br.com.fiap.inclusao_diversidade.controller;

import br.com.fiap.inclusao_diversidade.model.RelatorioDeDiversidade;
import br.com.fiap.inclusao_diversidade.service.RelatorioDeDiversidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioDeDiversidadeController {

    private final RelatorioDeDiversidadeService relatorioService;

    public RelatorioDeDiversidadeController(RelatorioDeDiversidadeService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public ResponseEntity<List<RelatorioDeDiversidade>> listarTodos() {
        return ResponseEntity.ok(relatorioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDeDiversidade> buscarPorId(@PathVariable Long id) {
        return relatorioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RelatorioDeDiversidade> criar(@RequestBody RelatorioDeDiversidade relatorio) {
        return ResponseEntity.ok(relatorioService.salvar(relatorio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelatorioDeDiversidade> atualizar(@PathVariable Long id, @RequestBody RelatorioDeDiversidade relatorio) {
        return relatorioService.buscarPorId(id)
                .map(r -> {
                    relatorio.setId(id);
                    return ResponseEntity.ok(relatorioService.salvar(relatorio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

