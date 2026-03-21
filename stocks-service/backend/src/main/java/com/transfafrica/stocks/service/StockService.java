package com.transfafrica.stocks.service;
import com.transfafrica.stocks.model.*;
import com.transfafrica.stocks.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final ProduitRepository produitRepository;
    private final MouvementStockRepository mouvementRepository;

    public List<Produit> findAll() { return produitRepository.findAll(); }
    public Produit findById(Long id) {
        return produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouve : " + id));
    }
    public Produit create(Produit produit) { return produitRepository.save(produit); }
    public Produit update(Long id, Produit updated) {
        Produit p = findById(id);
        p.setNom(updated.getNom()); p.setDescription(updated.getDescription());
        p.setCategorie(updated.getCategorie()); p.setPrixAchat(updated.getPrixAchat());
        p.setPrixVente(updated.getPrixVente()); p.setSeuilAlerte(updated.getSeuilAlerte());
        p.setUnite(updated.getUnite()); p.setFournisseur(updated.getFournisseur());
        p.setStatut(updated.getStatut());
        return produitRepository.save(p);
    }
    public void delete(Long id) { produitRepository.deleteById(id); }
    public List<Produit> findEnAlerte() { return produitRepository.findEnAlerte(); }
    public List<Produit> findEnRupture() { return produitRepository.findEnRupture(); }
    public List<Produit> search(String q) { return produitRepository.search(q); }

    @Transactional
    public Produit mouvementStock(Long produitId, MouvementStock.TypeMouvement type, int quantite, String motif) {
        Produit p = findById(produitId);
        int avant = p.getQuantiteStock();
        int apres = type == MouvementStock.TypeMouvement.ENTREE ? avant + quantite :
                    type == MouvementStock.TypeMouvement.SORTIE ? avant - quantite : quantite;
        if (apres < 0) throw new RuntimeException("Stock insuffisant");
        p.setQuantiteStock(apres);
        produitRepository.save(p);
        MouvementStock mv = MouvementStock.builder()
            .produit(p).type(type).quantite(quantite)
            .quantiteAvant(avant).quantiteApres(apres).motif(motif).build();
        mouvementRepository.save(mv);
        return p;
    }

    public List<MouvementStock> getMouvements(Long produitId) {
        return mouvementRepository.findByProduitIdOrderByCreatedAtDesc(produitId);
    }
    public List<MouvementStock> getAllMouvements() {
        return mouvementRepository.findAllByOrderByCreatedAtDesc();
    }
}
