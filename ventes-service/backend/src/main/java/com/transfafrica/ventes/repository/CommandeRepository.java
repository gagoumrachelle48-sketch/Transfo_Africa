package com.transfafrica.ventes.repository;
import com.transfafrica.ventes.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByStatut(Commande.StatutCommande statut);
    List<Commande> findByContactId(Long contactId);
    Optional<Commande> findByNumero(String numero);
    List<Commande> findAllByOrderByCreatedAtDesc();
}
