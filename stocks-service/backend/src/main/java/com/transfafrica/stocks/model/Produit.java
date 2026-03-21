package com.transfafrica.stocks.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "produits")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false, unique = true)
    private String reference;

    @NotBlank @Column(nullable = false)
    private String nom;

    private String description;
    private String categorie;
    private Double prixAchat;
    private Double prixVente;

    @Column(nullable = false)
    private Integer quantiteStock;

    @Column(nullable = false)
    private Integer seuilAlerte;

    private String unite;
    private String fournisseur;

    @Enumerated(EnumType.STRING)
    private StatutProduit statut;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (statut == null) statut = StatutProduit.ACTIF;
        if (quantiteStock == null) quantiteStock = 0;
        if (seuilAlerte == null) seuilAlerte = 5;
    }

    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }

    public boolean isEnRupture() { return quantiteStock <= 0; }
    public boolean isEnAlerte() { return quantiteStock > 0 && quantiteStock <= seuilAlerte; }

    public enum StatutProduit { ACTIF, INACTIF, DISCONTINUE }
}
