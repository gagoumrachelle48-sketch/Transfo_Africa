import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { Facture, LigneFacture } from '../../models/facture';
import { FacturationService } from '../../services/facturation';

@Component({
  selector: 'app-facture-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './facture-form.html',
  styleUrl: './facture-form.scss'
})
export class FactureFormComponent {
  facture: Facture = { clientNom: '', statut: 'BROUILLON', type: 'FACTURE', tauxTVA: 20, lignes: [] };
  constructor(private facturationService: FacturationService, private router: Router) {}
  addLigne() { this.facture.lignes!.push({ designation: '', quantite: 1, prixUnitaire: 0 }); }
  removeLigne(i: number) { this.facture.lignes!.splice(i, 1); }
  calcLigne(l: LigneFacture) { l.total = l.quantite * l.prixUnitaire; }
  get totalHT() { return this.facture.lignes?.reduce((s, l) => s + (l.quantite * l.prixUnitaire || 0), 0) || 0; }
  get totalTVA() { return this.totalHT * (this.facture.tauxTVA || 20) / 100; }
  get totalTTC() { return this.totalHT + this.totalTVA; }
  save() {
    this.facture.montantHT = this.totalHT;
    this.facture.montantTVA = this.totalTVA;
    this.facture.montantTTC = this.totalTTC;
    this.facturationService.create(this.facture).subscribe(() => this.router.navigate(['/factures']));
  }
  cancel() { this.router.navigate(['/factures']); }
}
