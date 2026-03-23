import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { Produit } from '../../models/produit';
import { StockService } from '../../services/stock';

@Component({
  selector: 'app-produit-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './produit-form.html',
  styleUrl: './produit-form.scss'
})
export class ProduitFormComponent implements OnInit {
  produit: Produit = { reference: '', nom: '', quantiteStock: 0, seuilAlerte: 5, statut: 'ACTIF' };
  isEdit = false; id?: number;

  constructor(private stockService: StockService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    if (this.id) { this.isEdit = true; this.stockService.getProduit(this.id).subscribe(p => this.produit = p); }
  }

  save() {
    if (this.isEdit && this.id) this.stockService.update(this.id, this.produit).subscribe(() => this.router.navigate(['/produits']));
    else this.stockService.create(this.produit).subscribe(() => this.router.navigate(['/produits']));
  }

  cancel() { this.router.navigate(['/produits']); }
}
