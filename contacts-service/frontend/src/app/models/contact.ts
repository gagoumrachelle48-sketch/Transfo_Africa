export interface Contact {
  id?: number;
  nom: string;
  prenom: string;
  email?: string;
  telephone?: string;
  entreprise?: string;
  adresse?: string;
  type: 'CLIENT' | 'FOURNISSEUR' | 'PROSPECT' | 'PARTENAIRE';
  notes?: string;
  createdAt?: string;
  updatedAt?: string;
}
