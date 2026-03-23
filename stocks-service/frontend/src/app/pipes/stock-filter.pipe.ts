import { Pipe, PipeTransform } from '@angular/core';
import { Produit } from '../models/produit';

@Pipe({ name: 'stockFilter', standalone: true })
export class StockFilterPipe implements PipeTransform {
  transform(produits: Produit[], type: string): number {
    if (type === 'alerte') return produits.filter(p => p.quantiteStock > 0 && p.quantiteStock <= p.seuilAlerte).length;
    if (type === 'rupture') return produits.filter(p => p.quantiteStock === 0).length;
    return produits.length;
  }
}
