package com.transfafrica.facturation.model;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "lignes_facture")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LigneFacture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
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
