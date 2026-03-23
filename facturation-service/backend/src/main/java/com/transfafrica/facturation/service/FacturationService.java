package com.transfafrica.facturation.service;
import com.transfafrica.facturation.model.*;
import com.transfafrica.facturation.repository.FactureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class FacturationService {
    private final FactureRepository factureRepository;

    public List<Facture> findAll() { return factureRepository.findAllByOrderByCreatedAtDesc(); }

    public Facture findById(Long id) {
        return factureRepository.findById(id).orElseThrow(() -> new RuntimeException("Facture non trouvee"));
    }

    public Facture create(Facture facture) {
        String prefix = facture.getType() == Facture.TypeFacture.AVOIR ? "AV" : facture.getType() == Facture.TypeFacture.PROFORMA ? "PF" : "FA";
        facture.setNumero(prefix + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (facture.getLignes() != null) facture.getLignes().forEach(l -> l.setFacture(facture));
        calcMontants(facture);
        return factureRepository.save(facture);
    }

    private void calcMontants(Facture f) {
        double ht = f.getLignes() == null ? 0 : f.getLignes().stream().mapToDouble(l -> l.getTotal() != null ? l.getTotal() : 0).sum();
        f.setMontantHT(ht);
        double tva = ht * (f.getTauxTVA() != null ? f.getTauxTVA() : 20.0) / 100;
        f.setMontantTVA(tva);
        f.setMontantTTC(ht + tva);
    }

    public Facture updateStatut(Long id, Facture.StatutFacture statut) {
        Facture f = findById(id);
        f.setStatut(statut);
        if (statut == Facture.StatutFacture.PAYEE) f.setDatePaiement(java.time.LocalDate.now());
        return factureRepository.save(f);
    }

    public void delete(Long id) { factureRepository.deleteById(id); }
    public List<Facture> findByStatut(Facture.StatutFacture statut) { return factureRepository.findByStatut(statut); }
    public List<Facture> search(String q) { return factureRepository.search(q); }

    public Map<String, Object> getDashboard() {
        return Map.of(
            "total", factureRepository.count(),
            "totalPaye", factureRepository.totalPaye() != null ? factureRepository.totalPaye() : 0,
            "totalEnAttente", factureRepository.totalEnAttente() != null ? factureRepository.totalEnAttente() : 0,
            "brouillons", factureRepository.findByStatut(Facture.StatutFacture.BROUILLON).size(),
            "enRetard", factureRepository.findByStatut(Facture.StatutFacture.EN_RETARD).size()
        );
    }
}
