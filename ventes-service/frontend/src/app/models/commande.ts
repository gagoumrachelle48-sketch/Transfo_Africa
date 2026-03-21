export interface LigneCommande {
  id?: number;
  designation: string;
  quantite: number;
  prixUnitaire: number;
  total?: number;
}

export interface Commande {
  id?: number;
  numero?: string;
  contactId: number;
  clientNom: string;
  statut: 'BROUILLON' | 'CONFIRME' | 'EN_COURS' | 'LIVRE' | 'ANNULE';
  dateCommande?: string;
  dateLivraison?: string;
  montantTotal?: number;
  notes?: string;
  lignes?: LigneCommande[];
  createdAt?: string;
}
