package com.transfafrica.achats.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "fournisseurs")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Fournisseur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    private String contact;
    private String email;
    private String telephone;
    private String adresse;
    private String pays;
    private String siret;
    private String notes;
    @Enumerated(EnumType.STRING)
    private StatutFournisseur statut;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); updatedAt = LocalDateTime.now(); if (statut == null) statut = StatutFournisseur.ACTIF; }
    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }
    public enum StatutFournisseur { ACTIF, INACTIF, BLACKLISTE }
}
