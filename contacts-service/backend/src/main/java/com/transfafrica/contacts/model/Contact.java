package com.transfafrica.contacts.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "contacts")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Contact {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable = false)
    private String nom;
    @NotBlank @Column(nullable = false)
    private String prenom;
    @Email @Column(unique = true)
    private String email;
    private String telephone;
    private String entreprise;
    private String adresse;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private TypeContact type;
    private String notes;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); updatedAt = LocalDateTime.now(); }
    @PreUpdate
    protected void onUpdate() { updatedAt = LocalDateTime.now(); }
    public enum TypeContact { CLIENT, FOURNISSEUR, PROSPECT, PARTENAIRE }
}
