import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { Fournisseur } from '../../models/fournisseur';
import { AchatService } from '../../services/achat';

@Component({
  selector: 'app-fournisseur-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './fournisseur-form.html',
  styleUrl: './fournisseur-form.scss'
})
export class FournisseurFormComponent implements OnInit {
  fournisseur: Fournisseur = { nom: '', statut: 'ACTIF' };
  isEdit = false; id?: number;
  constructor(private achatService: AchatService, private router: Router, private route: ActivatedRoute) {}
  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    if (this.id) { this.isEdit = true; this.achatService.getFournisseur(this.id).subscribe(f => this.fournisseur = f); }
  }
  save() {
    if (this.isEdit && this.id) this.achatService.updateFournisseur(this.id, this.fournisseur).subscribe(() => this.router.navigate(['/fournisseurs']));
    else this.achatService.createFournisseur(this.fournisseur).subscribe(() => this.router.navigate(['/fournisseurs']));
  }
  cancel() { this.router.navigate(['/fournisseurs']); }
}
