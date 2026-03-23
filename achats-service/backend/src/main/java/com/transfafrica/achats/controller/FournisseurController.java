package com.transfafrica.achats.controller;
import com.transfafrica.achats.model.Fournisseur;
import com.transfafrica.achats.service.AchatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/fournisseurs")
@CrossOrigin(origins = "*") @RequiredArgsConstructor
public class FournisseurController {
    private final AchatService achatService;
    @GetMapping public ResponseEntity<List<Fournisseur>> getAll() { return ResponseEntity.ok(achatService.findAllFournisseurs()); }
    @GetMapping("/{id}") public ResponseEntity<Fournisseur> getById(@PathVariable Long id) { return ResponseEntity.ok(achatService.findFournisseurById(id)); }
    @GetMapping("/search") public ResponseEntity<List<Fournisseur>> search(@RequestParam String q) { return ResponseEntity.ok(achatService.searchFournisseurs(q)); }
    @PostMapping public ResponseEntity<Fournisseur> create(@RequestBody Fournisseur f) { return ResponseEntity.status(HttpStatus.CREATED).body(achatService.createFournisseur(f)); }
    @PutMapping("/{id}") public ResponseEntity<Fournisseur> update(@PathVariable Long id, @RequestBody Fournisseur f) { return ResponseEntity.ok(achatService.updateFournisseur(id, f)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { achatService.deleteFournisseur(id); return ResponseEntity.noContent().build(); }
}
