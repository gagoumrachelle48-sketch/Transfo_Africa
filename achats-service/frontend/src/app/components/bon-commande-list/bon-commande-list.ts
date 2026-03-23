import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BonCommande } from '../../models/bon-commande';
import { AchatService } from '../../services/achat';

@Component({
  selector: 'app-bon-commande-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './bon-commande-list.html',
  styleUrl: './bon-commande-list.scss'
})
export class BonCommandeListComponent implements OnInit {
  bonsCommande: BonCommande[] = [];
  constructor(private achatService: AchatService) {}
  ngOnInit() { this.load(); }
  load() { this.achatService.getBonsCommande().subscribe(d => this.bonsCommande = d); }
  updateStatut(id: number, statut: string) { this.achatService.updateStatutBC(id, statut).subscribe(() => this.load()); }
  delete(id: number) { if (confirm('Supprimer ce bon de commande ?')) this.achatService.deleteBC(id).subscribe(() => this.load()); }
}
