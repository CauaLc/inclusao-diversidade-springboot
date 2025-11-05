package br.com.fiap.inclusao_diversidade.controller;

import br.com.fiap.inclusao_diversidade.DTO.ColaboradorRequestDTO;
import br.com.fiap.inclusao_diversidade.DTO.ColaboradorResponseDTO;
import br.com.fiap.inclusao_diversidade.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(colaboradorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponseDTO> buscarPorId(@PathVariable Long id) {
        return colaboradorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ColaboradorResponseDTO> criar(@Valid @RequestBody ColaboradorRequestDTO dto) {
        ColaboradorResponseDTO salvo = colaboradorService.salvar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(salvo.id()).toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ColaboradorRequestDTO dto) {
        return colaboradorService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        colaboradorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}