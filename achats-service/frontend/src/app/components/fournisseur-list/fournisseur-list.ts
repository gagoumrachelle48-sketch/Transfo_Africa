import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { Fournisseur } from '../../models/fournisseur';
import { AchatService } from '../../services/achat';

@Component({
  selector: 'app-fournisseur-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './fournisseur-list.html',
  styleUrl: './fournisseur-list.scss'
})
export class FournisseurListComponent implements OnInit {
  fournisseurs: Fournisseur[] = [];
  searchQuery = '';
  constructor(private achatService: AchatService, public router: Router) {}
  ngOnInit() { this.load(); }
  load() { this.achatService.getFournisseurs().subscribe(d => this.fournisseurs = d); }
  onSearch() {
    if (this.searchQuery.trim()) this.achatService.searchFournisseurs(this.searchQuery).subscribe(d => this.fournisseurs = d);
    else this.load();
  }
  delete(id: number) {
    if (confirm('Supprimer ce fournisseur ?')) this.achatService.deleteFournisseur(id).subscribe(() => this.load());
  }
}
