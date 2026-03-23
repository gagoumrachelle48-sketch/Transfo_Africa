import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { BonCommande, LigneBC } from '../../models/bon-commande';
import { Fournisseur } from '../../models/fournisseur';
import { AchatService } from '../../services/achat';

@Component({
  selector: 'app-bon-commande-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './bon-commande-form.html',
  styleUrl: './bon-commande-form.scss'
})
export class BonCommandeFormComponent implements OnInit {
  bc: BonCommande = { statut: 'BROUILLON', lignes: [] };
  fournisseurs: Fournisseur[] = [];
  selectedFournisseurId?: number;

  constructor(private achatService: AchatService, private router: Router) {}

  ngOnInit() { this.achatService.getFournisseurs().subscribe(f => this.fournisseurs = f); }

  addLigne() { this.bc.lignes!.push({ designation: '', quantite: 1, prixUnitaire: 0 }); }
  removeLigne(i: number) { this.bc.lignes!.splice(i, 1); }
  calcTotal(l: LigneBC) { l.total = l.quantite * l.prixUnitaire; }
  get total() { return this.bc.lignes?.reduce((s, l) => s + (l.quantite * l.prixUnitaire || 0), 0) || 0; }

  save() {
    if (this.selectedFournisseurId) this.bc.fournisseur = { id: this.selectedFournisseurId };
    this.bc.montantTotal = this.total;
    this.achatService.createBC(this.bc).subscribe(() => this.router.navigate(['/bons-commande']));
  }
  cancel() { this.router.navigate(['/bons-commande']); }
}
