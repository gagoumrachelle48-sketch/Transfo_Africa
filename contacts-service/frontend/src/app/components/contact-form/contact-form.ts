import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { Contact } from '../../models/contact';
import { ContactService } from '../../services/contact';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './contact-form.html',
  styleUrl: './contact-form.scss'
})
export class ContactFormComponent implements OnInit {
  contact: Contact = { nom: '', prenom: '', type: 'CLIENT' };
  isEdit = false;
  id?: number;

  constructor(
    private contactService: ContactService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    if (this.id) {
      this.isEdit = true;
      this.contactService.getById(this.id).subscribe(c => this.contact = c);
    }
  }

  save() {
    if (this.isEdit && this.id) {
      this.contactService.update(this.id, this.contact).subscribe(() => this.router.navigate(['/contacts']));
    } else {
      this.contactService.create(this.contact).subscribe(() => this.router.navigate(['/contacts']));
    }
  }

  cancel() { this.router.navigate(['/contacts']); }
}
