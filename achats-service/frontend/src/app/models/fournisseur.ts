export interface Fournisseur {
  id?: number;
  nom: string;
  contact?: string;
  email?: string;
  telephone?: string;
  adresse?: string;
  pays?: string;
  siret?: string;
  notes?: string;
  statut: 'ACTIF' | 'INACTIF' | 'BLACKLISTE';
  createdAt?: string;
}
