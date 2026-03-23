import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produit } from '../models/produit';
import { MouvementStock } from '../models/mouvement';

@Injectable({ providedIn: 'root' })
export class StockService {
  private api = 'http://192.168.1.132:8083/api/stocks';
  constructor(private http: HttpClient) {}
  getProduits(): Observable<Produit[]> { return this.http.get<Produit[]>(`${this.api}/produits`); }
  getProduit(id: number): Observable<Produit> { return this.http.get<Produit>(`${this.api}/produits/${id}`); }
  search(q: string): Observable<Produit[]> { return this.http.get<Produit[]>(`${this.api}/produits/search`, { params: new HttpParams().set('q', q) }); }
  getAlertes(): Observable<Produit[]> { return this.http.get<Produit[]>(`${this.api}/produits/alertes`); }
  getRuptures(): Observable<Produit[]> { return this.http.get<Produit[]>(`${this.api}/produits/ruptures`); }
  create(p: Produit): Observable<Produit> { return this.http.post<Produit>(`${this.api}/produits`, p); }
  update(id: number, p: Produit): Observable<Produit> { return this.http.put<Produit>(`${this.api}/produits/${id}`, p); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.api}/produits/${id}`); }
  mouvement(id: number, type: string, quantite: number, motif: string): Observable<Produit> {
    return this.http.post<Produit>(`${this.api}/produits/${id}/mouvement`, { type, quantite, motif });
  }
  getMouvements(id: number): Observable<MouvementStock[]> { return this.http.get<MouvementStock[]>(`${this.api}/produits/${id}/mouvements`); }
}
