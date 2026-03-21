import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Devis } from '../models/devis';

@Injectable({ providedIn: 'root' })
export class DevisService {
  private apiUrl = 'http://192.168.1.132:8082/api/devis';
  constructor(private http: HttpClient) {}
  getAll(): Observable<Devis[]> { return this.http.get<Devis[]>(this.apiUrl); }
  getById(id: number): Observable<Devis> { return this.http.get<Devis>(`${this.apiUrl}/${id}`); }
  create(devis: Devis): Observable<Devis> { return this.http.post<Devis>(this.apiUrl, devis); }
  updateStatut(id: number, statut: string): Observable<Devis> {
    return this.http.patch<Devis>(`${this.apiUrl}/${id}/statut`, null, { params: new HttpParams().set('statut', statut) });
  }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
