package com.transfafrica.ventes.model;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lignes_commande")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LigneCommande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @Column(nullable = false)
    private String designation;

    private Integer quantite;
    private Double prixUnitaire;
    private Double total;

    @PrePersist @PreUpdate
    protected void calcul() {
        if (quantite != null && prixUnitaire != null)
            total = quantite * prixUnitaire;
    }
}
