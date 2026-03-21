package com.transfafrica.ventes.controller;
import com.transfafrica.ventes.model.Commande;
import com.transfafrica.ventes.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeService commandeService;
    @GetMapping
    public ResponseEntity<List<Commande>> getAll() { return ResponseEntity.ok(commandeService.findAll()); }
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getById(@PathVariable Long id) { return ResponseEntity.ok(commandeService.findById(id)); }
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Commande>> getByStatut(@PathVariable Commande.StatutCommande statut) { return ResponseEntity.ok(commandeService.findByStatut(statut)); }
    @GetMapping("/contact/{contactId}")
    public ResponseEntity<List<Commande>> getByContact(@PathVariable Long contactId) { return ResponseEntity.ok(commandeService.findByContact(contactId)); }
    @PostMapping
    public ResponseEntity<Commande> create(@RequestBody Commande commande) { return ResponseEntity.status(HttpStatus.CREATED).body(commandeService.create(commande)); }
    @PatchMapping("/{id}/statut")
    public ResponseEntity<Commande> updateStatut(@PathVariable Long id, @RequestParam Commande.StatutCommande statut) { return ResponseEntity.ok(commandeService.updateStatut(id, statut)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { commandeService.delete(id); return ResponseEntity.noContent().build(); }
}
