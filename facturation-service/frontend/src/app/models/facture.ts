export interface LigneFacture {
  id?: number;
  designation: string;
  quantite: number;
  prixUnitaire: number;
  total?: number;
}
export interface Facture {
  id?: number;
  numero?: string;
  contactId?: number;
  clientNom: string;
  clientEmail?: string;
  clientAdresse?: string;
  statut: 'BROUILLON' | 'ENVOYEE' | 'PAYEE' | 'EN_RETARD' | 'ANNULEE';
  type: 'FACTURE' | 'AVOIR' | 'PROFORMA';
  dateFacture?: string;
  dateEcheance?: string;
  datePaiement?: string;
  montantHT?: number;
  tauxTVA?: number;
  montantTVA?: number;
  montantTTC?: number;
  notes?: string;
  referenceCommande?: string;
  lignes?: LigneFacture[];
  createdAt?: string;
}
export interface Dashboard {
  total: number;
  totalPaye: number;
  totalEnAttente: number;
  brouillons: number;
  enRetard: number;
}
