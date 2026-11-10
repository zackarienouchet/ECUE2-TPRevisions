package net.lecnam.ecue2.revisions;

import java.time.LocalDate;

public class Exec {

    public static void main(String[] args) {

        //exercice1();
        //exercice2();
        exercice3();

    }

    private static void exercice1() {

        Client client1 = new Client("Dupont", "Jean", "001");
        Client client2 = new Client("Martin", "Paul", "002");
        Client client3 = new Client("Durand", "Julie", "001");

        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);

        System.out.println("client1 == client2 : " + client1.equals(client2));
        System.out.println("client1 == client3 : " + client1.equals(client3));
    }

    private static void exercice2() {

        try {
            throw new SoldeInsuffisantException("Solde insuffisant", 50.0);

        } catch (SoldeInsuffisantException e) {
            System.out.println(e.getMessage());
            System.out.println("Montant manquant : " + e.getMontantManquant());
        }

    }

    private static void exercice3() {

        Client client = new Client("Dupont", "Jean", "001");

        // Création d'un compte anonyme pour tester la classe abstraite
        CompteBancaire compte = new CompteBancaire("C001", client) {

            @Override
            public void retirer(double montant) throws SoldeInsuffisantException {
                if (montant > getSolde()) {
                    throw new SoldeInsuffisantException("Solde insuffisant", montant - getSolde());
                }

                debiter(montant);
            }

            @Override
            public double calculerInterets() {
                return 0;
            }
        };

        // Affichage du compte
        System.out.println(compte);

        // Dépôt
        compte.deposer(500);
        System.out.println("Après dépôt : " + compte.getSolde() + " €");

        // Application des intérêts
        compte.appliquerInterets();
        System.out.println("Après intérêts : " + compte.getSolde() + " €");

        // Retrait
        try {
            compte.retirer(100);
            System.out.println("Après retrait : " + compte.getSolde() + " €");

        } catch (SoldeInsuffisantException e) {
            System.out.println(e.getMessage());
        }

    }

}