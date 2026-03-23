export interface MouvementStock {
  id?: number;
  produit?: any;
  type: 'ENTREE' | 'SORTIE' | 'AJUSTEMENT' | 'INVENTAIRE';
  quantite: number;
  quantiteAvant?: number;
  quantiteApres?: number;
  motif?: string;
  createdAt?: string;
}
