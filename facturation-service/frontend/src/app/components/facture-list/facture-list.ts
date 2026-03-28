import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Facture } from '../../models/facture';
import { FacturationService } from '../../services/facturation';

@Component({
  selector: 'app-facture-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './facture-list.html',
  styleUrl: './facture-list.scss'
})
export class FactureListComponent implements OnInit {
  factures: Facture[] = [];
  searchQuery = '';
  constructor(private facturationService: FacturationService) {}
  ngOnInit() { this.load(); }
  load() { this.facturationService.getAll().subscribe(d => this.factures = d); }
  onSearch() {
    if (this.searchQuery.trim()) this.facturationService.search(this.searchQuery).subscribe(d => this.factures = d);
    else this.load();
  }
  updateStatut(id: number, statut: string) { this.facturationService.updateStatut(id, statut).subscribe(() => this.load()); }
  delete(id: number) { if (confirm('Supprimer cette facture ?')) this.facturationService.delete(id).subscribe(() => this.load()); }
  statutClass(s: string) { return { 'BROUILLON': 'gray', 'ENVOYEE': 'blue', 'PAYEE': 'green', 'EN_RETARD': 'red', 'ANNULEE': 'gray' }[s] || 'gray'; }
}
