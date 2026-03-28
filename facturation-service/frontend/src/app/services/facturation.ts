import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Facture, Dashboard } from '../models/facture';

@Injectable({ providedIn: 'root' })
export class FacturationService {
  private api = 'http://192.168.1.132:8085/api/factures';
  constructor(private http: HttpClient) {}
  getAll(): Observable<Facture[]> { return this.http.get<Facture[]>(this.api); }
  getById(id: number): Observable<Facture> { return this.http.get<Facture>(`${this.api}/${id}`); }
  getDashboard(): Observable<Dashboard> { return this.http.get<Dashboard>(`${this.api}/dashboard`); }
  search(q: string): Observable<Facture[]> { return this.http.get<Facture[]>(`${this.api}/search`, { params: new HttpParams().set('q', q) }); }
  getByStatut(statut: string): Observable<Facture[]> { return this.http.get<Facture[]>(`${this.api}/statut/${statut}`); }
  create(f: Facture): Observable<Facture> { return this.http.post<Facture>(this.api, f); }
  updateStatut(id: number, statut: string): Observable<Facture> {
    return this.http.patch<Facture>(`${this.api}/${id}/statut`, null, { params: new HttpParams().set('statut', statut) });
  }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.api}/${id}`); }
}
