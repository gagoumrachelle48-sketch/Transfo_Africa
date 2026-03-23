package com.transfafrica.facturation.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name = "factures")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Facture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero;

    private Long contactId;
    private String clientNom;
    private String clientEmail;
    private String clientAdresse;

    @Enumerated(EnumType.STRING)
    private StatutFacture statut;

    @Enumerated(EnumType.STRING)
    private TypeFacture type;

    private LocalDate dateFacture;
    private LocalDate dateEcheance;
    private LocalDate datePaiement;

    private Double montantHT;
    private Double tauxTVA;
    private Double montantTVA;
    private Double montantTTC;

    private String notes;
    private String referenceCommande;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneFacture> lignes;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); updatedAt = LocalDateTime.now();
        if (statut == null) statut = StatutFacture.BROUILLON;
        if (type == null) type = TypeFacture.FACTURE;
        if (dateFacture == null) dateFacture = LocalDate.now();
        if (tauxTVA == null) tauxTVA = 20.0;
    }
    @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }

    public enum StatutFacture { BROUILLON, ENVOYEE, PAYEE, EN_RETARD, ANNULEE }
    public enum TypeFacture { FACTURE, AVOIR, PROFORMA }
}
