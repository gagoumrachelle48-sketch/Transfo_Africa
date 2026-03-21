package com.transfafrica.stocks.controller;
import com.transfafrica.stocks.model.*;
import com.transfafrica.stocks.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("/produits")
    public ResponseEntity<List<Produit>> getAll() { return ResponseEntity.ok(stockService.findAll()); }

    @GetMapping("/produits/{id}")
    public ResponseEntity<Produit> getById(@PathVariable Long id) { return ResponseEntity.ok(stockService.findById(id)); }

    @GetMapping("/produits/search")
    public ResponseEntity<List<Produit>> search(@RequestParam String q) { return ResponseEntity.ok(stockService.search(q)); }

    @GetMapping("/produits/alertes")
    public ResponseEntity<List<Produit>> getAlertes() { return ResponseEntity.ok(stockService.findEnAlerte()); }

    @GetMapping("/produits/ruptures")
    public ResponseEntity<List<Produit>> getRuptures() { return ResponseEntity.ok(stockService.findEnRupture()); }

    @PostMapping("/produits")
    public ResponseEntity<Produit> create(@RequestBody Produit produit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.create(produit));
    }

    @PutMapping("/produits/{id}")
    public ResponseEntity<Produit> update(@PathVariable Long id, @RequestBody Produit produit) {
        return ResponseEntity.ok(stockService.update(id, produit));
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stockService.delete(id); return ResponseEntity.noContent().build();
    }

    @PostMapping("/produits/{id}/mouvement")
    public ResponseEntity<Produit> mouvement(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        MouvementStock.TypeMouvement type = MouvementStock.TypeMouvement.valueOf((String) body.get("type"));
        int quantite = (Integer) body.get("quantite");
        String motif = (String) body.getOrDefault("motif", "");
        return ResponseEntity.ok(stockService.mouvementStock(id, type, quantite, motif));
    }

    @GetMapping("/produits/{id}/mouvements")
    public ResponseEntity<List<MouvementStock>> getMouvements(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getMouvements(id));
    }

    @GetMapping("/mouvements")
    public ResponseEntity<List<MouvementStock>> getAllMouvements() {
        return ResponseEntity.ok(stockService.getAllMouvements());
    }
}
