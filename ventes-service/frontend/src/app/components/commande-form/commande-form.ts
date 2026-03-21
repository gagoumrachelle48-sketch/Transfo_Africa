import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { Commande, LigneCommande } from '../../models/commande';
import { CommandeService } from '../../services/commande';

@Component({
  selector: 'app-commande-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './commande-form.html',
  styleUrl: './commande-form.scss'
})
export class CommandeFormComponent {
  commande: Commande = { contactId: 0, clientNom: '', statut: 'BROUILLON', lignes: [] };

  constructor(private commandeService: CommandeService, private router: Router) {}

  addLigne() {
    this.commande.lignes!.push({ designation: '', quantite: 1, prixUnitaire: 0 });
  }

  removeLigne(i: number) { this.commande.lignes!.splice(i, 1); }

  calcTotal(ligne: LigneCommande) { ligne.total = ligne.quantite * ligne.prixUnitaire; }

  get total() {
    return this.commande.lignes?.reduce((s, l) => s + (l.quantite * l.prixUnitaire || 0), 0) || 0;
  }

  save() {
    this.commande.montantTotal = this.total;
    this.commandeService.create(this.commande).subscribe(() => this.router.navigate(['/commandes']));
  }

  cancel() { this.router.navigate(['/commandes']); }
}
