import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fournisseur } from '../models/fournisseur';
import { BonCommande } from '../models/bon-commande';

@Injectable({ providedIn: 'root' })
export class AchatService {
  private api = 'http://192.168.1.132:8084/api';
  constructor(private http: HttpClient) {}

  getFournisseurs(): Observable<Fournisseur[]> { return this.http.get<Fournisseur[]>(`${this.api}/fournisseurs`); }
  getFournisseur(id: number): Observable<Fournisseur> { return this.http.get<Fournisseur>(`${this.api}/fournisseurs/${id}`); }
  searchFournisseurs(q: string): Observable<Fournisseur[]> { return this.http.get<Fournisseur[]>(`${this.api}/fournisseurs/search`, { params: new HttpParams().set('q', q) }); }
  createFournisseur(f: Fournisseur): Observable<Fournisseur> { return this.http.post<Fournisseur>(`${this.api}/fournisseurs`, f); }
  updateFournisseur(id: number, f: Fournisseur): Observable<Fournisseur> { return this.http.put<Fournisseur>(`${this.api}/fournisseurs/${id}`, f); }
  deleteFournisseur(id: number): Observable<void> { return this.http.delete<void>(`${this.api}/fournisseurs/${id}`); }

  getBonsCommande(): Observable<BonCommande[]> { return this.http.get<BonCommande[]>(`${this.api}/bons-commande`); }
  getBonCommande(id: number): Observable<BonCommande> { return this.http.get<BonCommande>(`${this.api}/bons-commande/${id}`); }
  createBC(bc: BonCommande): Observable<BonCommande> { return this.http.post<BonCommande>(`${this.api}/bons-commande`, bc); }
  updateStatutBC(id: number, statut: string): Observable<BonCommande> {
    return this.http.patch<BonCommande>(`${this.api}/bons-commande/${id}/statut`, null, { params: new HttpParams().set('statut', statut) });
  }
  deleteBC(id: number): Observable<void> { return this.http.delete<void>(`${this.api}/bons-commande/${id}`); }
}
