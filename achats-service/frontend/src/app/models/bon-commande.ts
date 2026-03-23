export interface LigneBC {
  id?: number;
  designation: string;
  quantite: number;
  prixUnitaire: number;
  total?: number;
}
export interface BonCommande {
  id?: number;
  numero?: string;
  fournisseur?: any;
  statut: 'BROUILLON' | 'ENVOYE' | 'CONFIRME' | 'RECEPTIONNE' | 'ANNULE';
  dateCommande?: string;
  dateLivraisonPrevue?: string;
  montantTotal?: number;
  notes?: string;
  lignes?: LigneBC[];
  createdAt?: string;
}
