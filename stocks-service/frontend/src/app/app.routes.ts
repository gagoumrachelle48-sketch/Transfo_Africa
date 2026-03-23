import { Routes } from '@angular/router';
import { ProduitListComponent } from './components/produit-list/produit-list';
import { ProduitFormComponent } from './components/produit-form/produit-form';

export const routes: Routes = [
  { path: '', redirectTo: '/produits', pathMatch: 'full' },
  { path: 'produits', component: ProduitListComponent },
  { path: 'produits/new', component: ProduitFormComponent },
  { path: 'produits/edit/:id', component: ProduitFormComponent },
  { path: 'alertes', component: ProduitListComponent },
];
