package com.transfafrica.ventes.service;
import com.transfafrica.ventes.model.Devis;
import com.transfafrica.ventes.repository.DevisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class DevisService {
    private final DevisRepository devisRepository;

    public List<Devis> findAll() { return devisRepository.findAllByOrderByCreatedAtDesc(); }

    public Devis findById(Long id) {
        return devisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Devis non trouve : " + id));
    }

    public Devis create(Devis devis) {
        devis.setNumero("DEV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return devisRepository.save(devis);
    }

    public Devis updateStatut(Long id, Devis.StatutDevis statut) {
        Devis d = findById(id);
        d.setStatut(statut);
        return devisRepository.save(d);
    }

    public void delete(Long id) { devisRepository.deleteById(id); }

    public List<Devis> findByStatut(Devis.StatutDevis statut) { return devisRepository.findByStatut(statut); }
}
