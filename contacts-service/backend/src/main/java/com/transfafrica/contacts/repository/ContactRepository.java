package com.transfafrica.contacts.repository;
import com.transfafrica.contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByType(Contact.TypeContact type);
    @Query("SELECT c FROM Contact c WHERE " +
           "LOWER(c.nom) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(c.prenom) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(c.entreprise) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Contact> search(String query);
    boolean existsByEmail(String email);
}
