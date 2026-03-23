import { Routes } from '@angular/router';
import { FournisseurListComponent } from './components/fournisseur-list/fournisseur-list';
import { FournisseurFormComponent } from './components/fournisseur-form/fournisseur-form';
import { BonCommandeListComponent } from './components/bon-commande-list/bon-commande-list';
import { BonCommandeFormComponent } from './components/bon-commande-form/bon-commande-form';

export const routes: Routes = [
  { path: '', redirectTo: '/fournisseurs', pathMatch: 'full' },
  { path: 'fournisseurs', component: FournisseurListComponent },
  { path: 'fournisseurs/new', component: FournisseurFormComponent },
  { path: 'fournisseurs/edit/:id', component: FournisseurFormComponent },
  { path: 'bons-commande', component: BonCommandeListComponent },
  { path: 'bons-commande/new', component: BonCommandeFormComponent },
];
