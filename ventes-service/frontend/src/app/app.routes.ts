import { Routes } from '@angular/router';
import { CommandeListComponent } from './components/commande-list/commande-list';
import { CommandeFormComponent } from './components/commande-form/commande-form';
import { DevisListComponent } from './components/devis-list/devis-list';

export const routes: Routes = [
  { path: '', redirectTo: '/commandes', pathMatch: 'full' },
  { path: 'commandes', component: CommandeListComponent },
  { path: 'commandes/new', component: CommandeFormComponent },
  { path: 'devis', component: DevisListComponent },
];
