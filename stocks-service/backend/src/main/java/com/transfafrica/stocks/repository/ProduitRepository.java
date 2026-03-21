package com.transfafrica.stocks.repository;
import com.transfafrica.stocks.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> findByReference(String reference);
    List<Produit> findByCategorie(String categorie);
    List<Produit> findByStatut(Produit.StatutProduit statut);
    @Query("SELECT p FROM Produit p WHERE p.quantiteStock <= p.seuilAlerte AND p.quantiteStock > 0")
    List<Produit> findEnAlerte();
    @Query("SELECT p FROM Produit p WHERE p.quantiteStock = 0")
    List<Produit> findEnRupture();
    @Query("SELECT p FROM Produit p WHERE LOWER(p.nom) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(p.reference) LIKE LOWER(CONCAT('%',:q,'%'))")
    List<Produit> search(String q);
}
