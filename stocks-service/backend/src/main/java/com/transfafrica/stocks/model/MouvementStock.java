package com.transfafrica.stocks.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mouvements_stock")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MouvementStock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMouvement type;

    @Column(nullable = false)
    private Integer quantite;

    private Integer quantiteAvant;
    private Integer quantiteApres;
    private String motif;
    private String reference;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    public enum TypeMouvement { ENTREE, SORTIE, AJUSTEMENT, INVENTAIRE }
}
