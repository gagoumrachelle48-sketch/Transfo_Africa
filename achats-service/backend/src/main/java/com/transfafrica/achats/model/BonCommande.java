package com.transfafrica.achats.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name = "bons_commande")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BonCommande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String numero;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
    @Enumerated(EnumType.STRING)
    private StatutBC statut;
    private LocalDate dateCommande;
    private LocalDate dateLivraisonPrevue;
    private Double montantTotal;
    private String notes;
    @OneToMany(mappedBy = "bonCommande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneBC> lignes;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); updatedAt = LocalDateTime.now();
        if (statut == null) statut = StatutBC.BROUILLON;
        if (dateCommande == null) dateCommande = LocalDate.now();
    }
    @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }
    public enum StatutBC { BROUILLON, ENVOYE, CONFIRME, RECEPTIONNE, ANNULE }
}
