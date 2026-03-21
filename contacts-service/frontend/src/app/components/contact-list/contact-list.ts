import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { Contact } from '../../models/contact';
import { ContactService } from '../../services/contact';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './contact-list.html',
  styleUrl: './contact-list.scss'
})
export class ContactListComponent implements OnInit {
  contacts: Contact[] = [];
  searchQuery = '';
  selectedType = '';

  constructor(private contactService: ContactService, private router: Router) {}

  ngOnInit() { this.loadContacts(); }

  loadContacts() {
    this.contactService.getAll().subscribe(data => this.contacts = data);
  }

  onSearch() {
    if (this.searchQuery.trim()) {
      this.contactService.search(this.searchQuery).subscribe(data => this.contacts = data);
    } else {
      this.loadContacts();
    }
  }

  onFilter() {
    if (this.selectedType) {
      this.contactService.getByType(this.selectedType).subscribe(data => this.contacts = data);
    } else {
      this.loadContacts();
    }
  }

  editContact(id: number) { this.router.navigate(['/contacts/edit', id]); }

  deleteContact(id: number) {
    if (confirm('Supprimer ce contact ?')) {
      this.contactService.delete(id).subscribe(() => this.loadContacts());
    }
  }
}
