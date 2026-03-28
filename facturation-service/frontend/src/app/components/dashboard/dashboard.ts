import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Dashboard } from '../../models/facture';
import { FacturationService } from '../../services/facturation';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class DashboardComponent implements OnInit {
  dashboard: Dashboard = { total: 0, totalPaye: 0, totalEnAttente: 0, brouillons: 0, enRetard: 0 };
  constructor(private facturationService: FacturationService) {}
  ngOnInit() { this.facturationService.getDashboard().subscribe(d => this.dashboard = d); }
}
