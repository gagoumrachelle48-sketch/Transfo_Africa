import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard';
import { FactureListComponent } from './components/facture-list/facture-list';
import { FactureFormComponent } from './components/facture-form/facture-form';

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'factures', component: FactureListComponent },
  { path: 'factures/new', component: FactureFormComponent },
];
