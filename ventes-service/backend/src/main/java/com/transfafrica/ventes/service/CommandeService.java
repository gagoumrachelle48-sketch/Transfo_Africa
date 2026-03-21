package com.transfafrica.ventes.service;
import com.transfafrica.ventes.model.Commande;
import com.transfafrica.ventes.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;

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
        c.setStatut(statut);
        return commandeRepository.save(c);
    }

    public void delete(Long id) { commandeRepository.deleteById(id); }

    public List<Commande> findByStatut(Commande.StatutCommande statut) { return commandeRepository.findByStatut(statut); }

    public List<Commande> findByContact(Long contactId) { return commandeRepository.findByContactId(contactId); }
}
