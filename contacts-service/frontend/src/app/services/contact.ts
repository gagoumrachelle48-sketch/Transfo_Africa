import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contact } from '../models/contact';

@Injectable({ providedIn: 'root' })
export class ContactService {
  private apiUrl = 'http://192.168.1.132:8081/api/contacts';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.apiUrl);
  }

  getById(id: number): Observable<Contact> {
    return this.http.get<Contact>(`${this.apiUrl}/${id}`);
  }

  search(q: string): Observable<Contact[]> {
    return this.http.get<Contact[]>(`${this.apiUrl}/search`, { params: new HttpParams().set('q', q) });
  }

  getByType(type: string): Observable<Contact[]> {
    return this.http.get<Contact[]>(`${this.apiUrl}/type/${type}`);
  }

  create(contact: Contact): Observable<Contact> {
    return this.http.post<Contact>(this.apiUrl, contact);
  }

  update(id: number, contact: Contact): Observable<Contact> {
    return this.http.put<Contact>(`${this.apiUrl}/${id}`, contact);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
