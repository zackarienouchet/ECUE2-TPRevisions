package net.lecnam.ecue2.revisions;

import java.time.LocalDate;

public abstract class CompteBancaire {

    // Attributs
    private String numeroCompte;
    private double solde;
    private Client titulaire;
    private LocalDate dateOuverture;

    // Constructeur pour un nouveau compte
    public CompteBancaire(String numeroCompte, Client titulaire) {
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.dateOuverture = LocalDate.now();
        this.solde = 0;
    }

    // Constructeur pour recréer un compte existant
    public CompteBancaire(String numeroCompte, Client titulaire, LocalDate dateOuverture, double solde) {
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.dateOuverture = dateOuverture;
        this.solde = solde;
    }

    // Getters
    public String getNumeroCompte() {
        return numeroCompte;
    }

    public double getSolde() {
        return solde;
    }

    public Client getTitulaire() {
        return titulaire;
    }

    public LocalDate getDateOuverture() {
        return dateOuverture;
    }

    // Déposer de l'argent
    public void deposer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à zéro.");
        }

        solde += montant;
    }

    // Débiter le compte
    // protected permet aux classes filles d'utiliser cette méthode
    // sans la rendre accessible à toutes les classes extérieures.
    protected void debiter(double montant) {
        solde -= montant;
    }

    // Retrait : chaque type de compte définit ses propres règles
    public abstract void retirer(double montant) throws SoldeInsuffisantException;

    // Calcul des intérêts : chaque type de compte définit son calcul
    public abstract double calculerInterets();

    // Appliquer les intérêts au solde
    public void appliquerInterets() {
        solde += calculerInterets();
    }

    // toString()
    @Override
    public String toString() {
        return "Compte bancaire : " + numeroCompte
                + "\nTitulaire : " + titulaire
                + "\nSolde : " + solde + " €"
                + "\nDate d'ouverture : " + dateOuverture;
    }

}