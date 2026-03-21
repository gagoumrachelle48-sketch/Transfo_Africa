import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Commande } from '../../models/commande';
import { CommandeService } from '../../services/commande';

@Component({
  selector: 'app-commande-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './commande-list.html',
  styleUrl: './commande-list.scss'
})
export class CommandeListComponent implements OnInit {
  commandes: Commande[] = [];
  selectedStatut = '';

  constructor(private commandeService: CommandeService) {}
  ngOnInit() { this.load(); }
  load() { this.commandeService.getAll().subscribe(d => this.commandes = d); }

  updateStatut(id: number, statut: string) {
    this.commandeService.updateStatut(id, statut).subscribe(() => this.load());
  }

  delete(id: number) {
    if (confirm('Supprimer cette commande ?'))
      this.commandeService.delete(id).subscribe(() => this.load());
  }

  statutClass(s: string) {
    return { 'BROUILLON': 'gray', 'CONFIRME': 'blue', 'EN_COURS': 'amber', 'LIVRE': 'green', 'ANNULE': 'red' }[s] || 'gray';
  }
}
