package com.transfafrica.achats.controller;
import com.transfafrica.achats.model.BonCommande;
import com.transfafrica.achats.service.AchatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/bons-commande")
@CrossOrigin(origins = "*") @RequiredArgsConstructor
public class BonCommandeController {
    private final AchatService achatService;
    @GetMapping public ResponseEntity<List<BonCommande>> getAll() { return ResponseEntity.ok(achatService.findAllBC()); }
    @GetMapping("/{id}") public ResponseEntity<BonCommande> getById(@PathVariable Long id) { return ResponseEntity.ok(achatService.findBCById(id)); }
    @GetMapping("/statut/{statut}") public ResponseEntity<List<BonCommande>> getByStatut(@PathVariable BonCommande.StatutBC statut) { return ResponseEntity.ok(achatService.findBCByStatut(statut)); }
    @PostMapping public ResponseEntity<BonCommande> create(@RequestBody BonCommande bc) { return ResponseEntity.status(HttpStatus.CREATED).body(achatService.createBC(bc)); }
    @PatchMapping("/{id}/statut") public ResponseEntity<BonCommande> updateStatut(@PathVariable Long id, @RequestParam BonCommande.StatutBC statut) { return ResponseEntity.ok(achatService.updateStatutBC(id, statut)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { achatService.deleteBC(id); return ResponseEntity.noContent().build(); }
}
