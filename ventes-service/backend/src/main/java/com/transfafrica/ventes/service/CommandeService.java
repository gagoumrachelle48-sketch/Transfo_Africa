package com.transfafrica.ventes.service;

import com.transfafrica.ventes.model.Commande;
import com.transfafrica.ventes.model.LigneCommande;
import com.transfafrica.ventes.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommandeService {

    private static final Logger logger = LoggerFactory.getLogger(CommandeService.class);

    private final CommandeRepository commandeRepository;
    private final RestTemplate restTemplate;

    private static final String FACTURATION_URL = "http://facturation-backend:8085/api/factures";
    private static final String STOCKS_URL = "http://stocks-backend:8083/api/stocks/produits";

    public List<Commande> findAll() { return commandeRepository.findAllByOrderByCreatedAtDesc(); }

    public Commande findById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvee : " + id));
    }

    public Commande create(Commande commande) {
        commande.setNumero("CMD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (commande.getLignes() != null)
            commande.getLignes().forEach(l -> l.setCommande(commande));
        double total = commande.getLignes() == null ? 0 :
                commande.getLignes().stream().mapToDouble(l -> l.getTotal() != null ? l.getTotal() : 0).sum();
        commande.setMontantTotal(total);
        return commandeRepository.save(commande);
    }

    public Commande updateStatut(Long id, Commande.StatutCommande statut) {
        Commande c = findById(id);
        Commande.StatutCommande ancienStatut = c.getStatut();
        c.setStatut(statut);
        Commande saved = commandeRepository.save(c);

        if (statut == Commande.StatutCommande.CONFIRME && ancienStatut != Commande.StatutCommande.CONFIRME) {
            logger.info("Commande {} validee - integration inter-modules", c.getNumero());
            creerFactureAutomatique(saved);
            decrementerStocks(saved);
        }

        return saved;
    }

    private void creerFactureAutomatique(Commande commande) {
        try {
            Map<String, Object> facture = new HashMap<>();
            facture.put("numero", "FAC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            facture.put("contactId", commande.getContactId());
            facture.put("clientNom", commande.getClientNom());
            facture.put("referenceCommande", commande.getNumero());
            facture.put("montantHT", commande.getMontantTotal());
            facture.put("tauxTVA", 20.0);
            facture.put("montantTVA", commande.getMontantTotal() * 0.20);
            facture.put("montantTTC", commande.getMontantTotal() * 1.20);
            facture.put("dateFacture", LocalDate.now().toString());
            facture.put("dateEcheance", LocalDate.now().plusDays(30).toString());
            facture.put("statut", "BROUILLON");
            facture.put("type", "FACTURE");
            facture.put("notes", "Facture auto depuis commande " + commande.getNumero());

            if (commande.getLignes() != null) {
                List<Map<String, Object>> lignesFacture = new ArrayList<>();
                for (LigneCommande lc : commande.getLignes()) {
                    Map<String, Object> ligne = new HashMap<>();
                    ligne.put("designation", lc.getDesignation());
                    ligne.put("quantite", lc.getQuantite());
                    ligne.put("prixUnitaire", lc.getPrixUnitaire());
                    ligne.put("total", lc.getTotal());
                    lignesFacture.add(ligne);
                }
                facture.put("lignes", lignesFacture);
            }

            restTemplate.postForObject(FACTURATION_URL, facture, Object.class);
            logger.info("Facture creee pour commande {}", commande.getNumero());
        } catch (Exception e) {
            logger.error("Erreur creation facture pour {} : {}", commande.getNumero(), e.getMessage());
        }
    }

    private void decrementerStocks(Commande commande) {
        if (commande.getLignes() == null) return;
        for (LigneCommande ligne : commande.getLignes()) {
            try {
                String searchUrl = STOCKS_URL + "/search?q=" + ligne.getDesignation();
                Object[] produits = restTemplate.getForObject(searchUrl, Object[].class);
                if (produits != null && produits.length > 0) {
                    Map<String, Object> produit = (Map<String, Object>) produits[0];
                    Object produitId = produit.get("id");
                    Map<String, Object> mouvement = new HashMap<>();
                    mouvement.put("type", "SORTIE");
                    mouvement.put("quantite", ligne.getQuantite());
                    mouvement.put("motif", "Vente - Commande " + commande.getNumero());
                    restTemplate.postForObject(STOCKS_URL + "/" + produitId + "/mouvement", mouvement, Object.class);
                    logger.info("Stock decremente pour {} (qte: {})", ligne.getDesignation(), ligne.getQuantite());
                } else {
                    logger.warn("Produit non trouve en stock : {}", ligne.getDesignation());
                }
            } catch (Exception e) {
                logger.error("Erreur stock pour {} : {}", ligne.getDesignation(), e.getMessage());
            }
        }
    }

    public void delete(Long id) { commandeRepository.deleteById(id); }
    public List<Commande> findByStatut(Commande.StatutCommande statut) { return commandeRepository.findByStatut(statut); }
    public List<Commande> findByContact(Long contactId) { return commandeRepository.findByContactId(contactId); }
}
