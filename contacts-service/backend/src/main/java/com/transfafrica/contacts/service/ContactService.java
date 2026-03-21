package com.transfafrica.contacts.service;
import com.transfafrica.contacts.model.Contact;
import com.transfafrica.contacts.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    public List<Contact> findAll() { return contactRepository.findAll(); }
    public Contact findById(Long id) {
        return contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact non trouve : " + id));
    }
    public Contact create(Contact contact) {
        if (contact.getEmail() != null && contactRepository.existsByEmail(contact.getEmail()))
            throw new RuntimeException("Email deja utilise : " + contact.getEmail());
        return contactRepository.save(contact);
    }
    public Contact update(Long id, Contact updated) {
        Contact existing = findById(id);
        existing.setNom(updated.getNom());
        existing.setPrenom(updated.getPrenom());
        existing.setEmail(updated.getEmail());
        existing.setTelephone(updated.getTelephone());
        existing.setEntreprise(updated.getEntreprise());
        existing.setAdresse(updated.getAdresse());
        existing.setType(updated.getType());
        existing.setNotes(updated.getNotes());
        return contactRepository.save(existing);
    }
    public void delete(Long id) { contactRepository.deleteById(id); }
    public List<Contact> search(String query) { return contactRepository.search(query); }
    public List<Contact> findByType(Contact.TypeContact type) { return contactRepository.findByType(type); }
}
