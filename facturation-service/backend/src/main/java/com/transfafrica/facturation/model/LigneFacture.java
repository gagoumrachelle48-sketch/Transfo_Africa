package com.transfafrica.facturation.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "lignes_facture")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LigneFacture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facture_id")
    private Facture facture;

    private String designation;
    private Integer quantite;
    private Double prixUnitaire;
    private Double total;

    @PrePersist @PreUpdate
    protected void calcul() {
        if (quantite != null && prixUnitaire != null) total = quantite * prixUnitaire;
    }
}
