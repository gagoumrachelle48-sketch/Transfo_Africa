# Transfo_Africa — Suite ERP modulaire pour PME

Plateforme de gestion d'entreprise composée de microservices indépendants et communicants.

## Modules
- contacts-service   : CRM partagé (clients + fournisseurs)
- finance-service    : Gestion financière (MoneyWise)
- ventes-service     : Commandes et devis
- stocks-service     : Inventaire et alertes
- achats-service     : Fournisseurs et bons de commande
- facturation-service: Factures PDF et paiements

## Stack technique
- Frontend  : Angular 17+
- Backend   : Spring Boot 3 (un par module)
- Database  : MariaDB (un schéma par module)
- DevOps    : Docker Compose + Ansible + GitHub Actions
