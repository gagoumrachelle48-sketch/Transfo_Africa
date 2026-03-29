package com.transfafrica.ventes.controller;
import com.transfafrica.ventes.model.Devis;
import com.transfafrica.ventes.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/devis")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DevisController {
    private final DevisService devisService;
    @GetMapping
    public ResponseEntity<List<Devis>> getAll() { return ResponseEntity.ok(devisService.findAll()); }
    @GetMapping("/{id}")
    public ResponseEntity<Devis> getById(@PathVariable Long id) { return ResponseEntity.ok(devisService.findById(id)); }
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Devis>> getByStatut(@PathVariable Devis.StatutDevis statut) { return ResponseEntity.ok(devisService.findByStatut(statut)); }
    @PostMapping
    public ResponseEntity<Devis> create(@RequestBody Devis devis) { return ResponseEntity.status(HttpStatus.CREATED).body(devisService.create(devis)); }
    @PatchMapping("/{id}/statut")
    public ResponseEntity<Devis> updateStatut(@PathVariable Long id, @RequestParam Devis.StatutDevis statut) { return ResponseEntity.ok(devisService.updateStatut(id, statut)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { devisService.delete(id); return ResponseEntity.noContent().build(); }
}
