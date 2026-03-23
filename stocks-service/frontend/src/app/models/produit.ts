export interface Produit {
  id?: number;
  reference: string;
  nom: string;
  description?: string;
  categorie?: string;
  prixAchat?: number;
  prixVente?: number;
  quantiteStock: number;
  seuilAlerte: number;
  unite?: string;
  fournisseur?: string;
  statut: 'ACTIF' | 'INACTIF' | 'DISCONTINUE';
  createdAt?: string;
}
