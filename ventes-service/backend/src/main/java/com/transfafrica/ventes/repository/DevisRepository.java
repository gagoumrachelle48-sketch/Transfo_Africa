package com.transfafrica.ventes.repository;
import com.transfafrica.ventes.model.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
    List<Devis> findByStatut(Devis.StatutDevis statut);
    List<Devis> findByContactId(Long contactId);
    List<Devis> findAllByOrderByCreatedAtDesc();
}
