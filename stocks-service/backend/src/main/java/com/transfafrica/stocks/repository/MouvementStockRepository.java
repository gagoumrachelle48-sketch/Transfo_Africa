package com.transfafrica.stocks.repository;
import com.transfafrica.stocks.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock, Long> {
    List<MouvementStock> findByProduitIdOrderByCreatedAtDesc(Long produitId);
    List<MouvementStock> findAllByOrderByCreatedAtDesc();
}
