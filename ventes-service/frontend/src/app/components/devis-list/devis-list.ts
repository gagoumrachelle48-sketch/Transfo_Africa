import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Devis } from '../../models/devis';
import { DevisService } from '../../services/devis';

@Component({
  selector: 'app-devis-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './devis-list.html',
  styleUrl: './devis-list.scss'
})
export class DevisListComponent implements OnInit {
  devisList: Devis[] = [];
  constructor(private devisService: DevisService) {}
  ngOnInit() { this.load(); }
  load() { this.devisService.getAll().subscribe(d => this.devisList = d); }
  updateStatut(id: number, statut: string) {
    this.devisService.updateStatut(id, statut).subscribe(() => this.load());
  }
  delete(id: number) {
    if (confirm('Supprimer ce devis ?')) this.devisService.delete(id).subscribe(() => this.load());
  }
}
