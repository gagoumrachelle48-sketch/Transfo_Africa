import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Commande } from '../models/commande';

@Injectable({ providedIn: 'root' })
export class CommandeService {
  private apiUrl = 'http://192.168.1.132:8082/api/commandes';
  constructor(private http: HttpClient) {}
  getAll(): Observable<Commande[]> { return this.http.get<Commande[]>(this.apiUrl); }
  getById(id: number): Observable<Commande> { return this.http.get<Commande>(`${this.apiUrl}/${id}`); }
  getByStatut(statut: string): Observable<Commande[]> { return this.http.get<Commande[]>(`${this.apiUrl}/statut/${statut}`); }
  create(commande: Commande): Observable<Commande> { return this.http.post<Commande>(this.apiUrl, commande); }
  updateStatut(id: number, statut: string): Observable<Commande> {
    return this.http.patch<Commande>(`${this.apiUrl}/${id}/statut`, null, { params: new HttpParams().set('statut', statut) });
  }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
