package com.transfafrica.achats.repository;
import com.transfafrica.achats.model.BonCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BonCommandeRepository extends JpaRepository<BonCommande, Long> {
    List<BonCommande> findByStatut(BonCommande.StatutBC statut);
    List<BonCommande> findAllByOrderByCreatedAtDesc();
    List<BonCommande> findByFournisseurId(Long fournisseurId);
}
