package com.transfafrica.achats.service;
import com.transfafrica.achats.model.*;
import com.transfafrica.achats.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class AchatService {
    private final FournisseurRepository fournisseurRepo;
    private final BonCommandeRepository bcRepo;

    public List<Fournisseur> findAllFournisseurs() { return fournisseurRepo.findAll(); }
    public Fournisseur findFournisseurById(Long id) { return fournisseurRepo.findById(id).orElseThrow(() -> new RuntimeException("Fournisseur non trouve")); }
    public Fournisseur createFournisseur(Fournisseur f) { return fournisseurRepo.save(f); }
    public Fournisseur updateFournisseur(Long id, Fournisseur f) {
        Fournisseur existing = findFournisseurById(id);
        existing.setNom(f.getNom()); existing.setContact(f.getContact());
        existing.setEmail(f.getEmail()); existing.setTelephone(f.getTelephone());
        existing.setAdresse(f.getAdresse()); existing.setPays(f.getPays());
        existing.setSiret(f.getSiret()); existing.setNotes(f.getNotes());
        existing.setStatut(f.getStatut());
        return fournisseurRepo.save(existing);
    }
    public void deleteFournisseur(Long id) { fournisseurRepo.deleteById(id); }
    public List<Fournisseur> searchFournisseurs(String q) { return fournisseurRepo.search(q); }

    public List<BonCommande> findAllBC() { return bcRepo.findAllByOrderByCreatedAtDesc(); }
    public BonCommande findBCById(Long id) { return bcRepo.findById(id).orElseThrow(() -> new RuntimeException("BC non trouve")); }
    public BonCommande createBC(BonCommande bc) {
        bc.setNumero("BC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (bc.getLignes() != null) bc.getLignes().forEach(l -> l.setBonCommande(bc));
        double total = bc.getLignes() == null ? 0 : bc.getLignes().stream().mapToDouble(l -> l.getTotal() != null ? l.getTotal() : 0).sum();
        bc.setMontantTotal(total);
        return bcRepo.save(bc);
    }
    public BonCommande updateStatutBC(Long id, BonCommande.StatutBC statut) {
        BonCommande bc = findBCById(id);
        bc.setStatut(statut);
        return bcRepo.save(bc);
    }
    public void deleteBC(Long id) { bcRepo.deleteById(id); }
    public List<BonCommande> findBCByStatut(BonCommande.StatutBC statut) { return bcRepo.findByStatut(statut); }
}
