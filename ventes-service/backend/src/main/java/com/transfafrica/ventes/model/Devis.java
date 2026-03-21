package com.transfafrica.ventes.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "devis")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Devis {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero;

    private Long contactId;
    private String clientNom;

    @Enumerated(EnumType.STRING)
    private StatutDevis statut;

    private LocalDate dateDevis;
    private LocalDate dateValidite;
    private Double montantTotal;
    private String notes;
    private Long commandeId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (statut == null) statut = StatutDevis.BROUILLON;
        if (dateDevis == null) dateDevis = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }

    public enum StatutDevis { BROUILLON, ENVOYE, ACCEPTE, REFUSE, EXPIRE }
}
