export interface Devis {
  id?: number;
  numero?: string;
  contactId: number;
  clientNom: string;
  statut: 'BROUILLON' | 'ENVOYE' | 'ACCEPTE' | 'REFUSE' | 'EXPIRE';
  dateDevis?: string;
  dateValidite?: string;
  montantTotal?: number;
  notes?: string;
  createdAt?: string;
}
