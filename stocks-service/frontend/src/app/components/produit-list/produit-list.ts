import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { Produit } from '../../models/produit';
import { StockFilterPipe } from '../../pipes/stock-filter.pipe';
import { StockService } from '../../services/stock';

@Component({
  selector: 'app-produit-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, StockFilterPipe],
  templateUrl: './produit-list.html',
  styleUrl: './produit-list.scss'
})
export class ProduitListComponent implements OnInit {
  produits: Produit[] = [];
  searchQuery = '';
  showMouvement = false;
  selectedProduit?: Produit;
  mouvement = { type: 'ENTREE', quantite: 1, motif: '' };

  constructor(private stockService: StockService, public router: Router) {}
  ngOnInit() { this.load(); }
  load() { this.stockService.getProduits().subscribe(d => this.produits = d); }

  onSearch() {
    if (this.searchQuery.trim()) this.stockService.search(this.searchQuery).subscribe(d => this.produits = d);
    else this.load();
  }

  openMouvement(p: Produit) { this.selectedProduit = p; this.showMouvement = true; }

  saveMouvement() {
    if (!this.selectedProduit?.id) return;
    this.stockService.mouvement(this.selectedProduit.id, this.mouvement.type, this.mouvement.quantite, this.mouvement.motif)
      .subscribe(() => { this.showMouvement = false; this.load(); });
  }

  delete(id: number) {
    if (confirm('Supprimer ce produit ?')) this.stockService.delete(id).subscribe(() => this.load());
  }

  stockClass(p: Produit) {
    if (p.quantiteStock === 0) return 'rupture';
    if (p.quantiteStock <= p.seuilAlerte) return 'alerte';
    return 'normal';
  }
}
