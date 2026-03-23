package com.transfafrica.achats.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "lignes_bc")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LigneBC {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bon_commande_id")
    private BonCommande bonCommande;
    private String designation;
    private Integer quantite;
    private Double prixUnitaire;
    private Double total;
    @PrePersist @PreUpdate
    protected void calcul() { if (quantite != null && prixUnitaire != null) total = quantite * prixUnitaire; }
}
