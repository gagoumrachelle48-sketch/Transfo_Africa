package com.transfafrica.facturation.controller;
import com.transfafrica.facturation.model.Facture;
import com.transfafrica.facturation.service.FacturationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/factures")
@CrossOrigin(origins = "*") @RequiredArgsConstructor
public class FactureController {
    private final FacturationService facturationService;

    @GetMapping public ResponseEntity<List<Facture>> getAll() { return ResponseEntity.ok(facturationService.findAll()); }
    @GetMapping("/dashboard") public ResponseEntity<Map<String, Object>> getDashboard() { return ResponseEntity.ok(facturationService.getDashboard()); }
    @GetMapping("/{id}") public ResponseEntity<Facture> getById(@PathVariable Long id) { return ResponseEntity.ok(facturationService.findById(id)); }
    @GetMapping("/search") public ResponseEntity<List<Facture>> search(@RequestParam String q) { return ResponseEntity.ok(facturationService.search(q)); }
    @GetMapping("/statut/{statut}") public ResponseEntity<List<Facture>> getByStatut(@PathVariable Facture.StatutFacture statut) { return ResponseEntity.ok(facturationService.findByStatut(statut)); }
    @PostMapping public ResponseEntity<Facture> create(@RequestBody Facture facture) { return ResponseEntity.status(HttpStatus.CREATED).body(facturationService.create(facture)); }
    @PatchMapping("/{id}/statut") public ResponseEntity<Facture> updateStatut(@PathVariable Long id, @RequestParam Facture.StatutFacture statut) { return ResponseEntity.ok(facturationService.updateStatut(id, statut)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { facturationService.delete(id); return ResponseEntity.noContent().build(); }
}
