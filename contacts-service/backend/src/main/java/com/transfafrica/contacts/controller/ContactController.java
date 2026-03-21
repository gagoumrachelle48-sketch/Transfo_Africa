package com.transfafrica.contacts.controller;
import com.transfafrica.contacts.model.Contact;
import com.transfafrica.contacts.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    @GetMapping
    public ResponseEntity<List<Contact>> getAll() { return ResponseEntity.ok(contactService.findAll()); }
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id) { return ResponseEntity.ok(contactService.findById(id)); }
    @GetMapping("/search")
    public ResponseEntity<List<Contact>> search(@RequestParam String q) { return ResponseEntity.ok(contactService.search(q)); }
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Contact>> getByType(@PathVariable Contact.TypeContact type) { return ResponseEntity.ok(contactService.findByType(type)); }
    @PostMapping
    public ResponseEntity<Contact> create(@Valid @RequestBody Contact contact) { return ResponseEntity.status(HttpStatus.CREATED).body(contactService.create(contact)); }
    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable Long id, @Valid @RequestBody Contact contact) { return ResponseEntity.ok(contactService.update(id, contact)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { contactService.delete(id); return ResponseEntity.noContent().build(); }
}
