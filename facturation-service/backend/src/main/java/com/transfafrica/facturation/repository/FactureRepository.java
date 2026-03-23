package com.transfafrica.facturation.repository;
import com.transfafrica.facturation.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByStatut(Facture.StatutFacture statut);
    List<Facture> findByContactId(Long contactId);
    List<Facture> findAllByOrderByCreatedAtDesc();
    @Query("SELECT f FROM Facture f WHERE LOWER(f.clientNom) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(f.numero) LIKE LOWER(CONCAT('%',:q,'%'))")
    List<Facture> search(String q);
    @Query("SELECT SUM(f.montantTTC) FROM Facture f WHERE f.statut = 'PAYEE'")
    Double totalPaye();
    @Query("SELECT SUM(f.montantTTC) FROM Facture f WHERE f.statut IN ('ENVOYEE','EN_RETARD')")
    Double totalEnAttente();
}
