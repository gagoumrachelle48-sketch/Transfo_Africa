package com.transfafrica.achats.repository;
import com.transfafrica.achats.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    List<Fournisseur> findByStatut(Fournisseur.StatutFournisseur statut);
    @Query("SELECT f FROM Fournisseur f WHERE LOWER(f.nom) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(f.email) LIKE LOWER(CONCAT('%',:q,'%'))")
    List<Fournisseur> search(String q);
}
