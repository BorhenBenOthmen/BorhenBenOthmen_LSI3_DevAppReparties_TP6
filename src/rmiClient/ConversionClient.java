package rmiClient;
import rmiService.IConversion; import java.rmi.registry.LocateRegistry; import java.rmi.registry.Registry; import java.util.Scanner;
public class ConversionClient {
    public static void main(String[] args) {
        try {
            // 1. Se connecter au RMI Registry (port 1099)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            System.out.println("Connexion au Registry réussie.");

            // 2. Récupérer la référence distante de l'objet IConversion
            IConversion stub = (IConversion) registry.lookup("ConversionService");
            System.out.println("Objet distant trouvé : ConversionService");

            // 3. Obtenir le montant à convertir (argument ou saisie utilisateur)
            double montant;
            if (args.length > 0) {
                try {
                    montant = Double.parseDouble(args[0].replace(',', '.'));
                } catch (NumberFormatException nfe) {
                    System.err.println("Montant invalide en argument. Veuillez fournir un nombre (ex: 500 ou 500.75).");
                    return;
                }
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Entrez le montant à convertir : ");
                String input = scanner.nextLine().trim();
                try {
                    montant = Double.parseDouble(input.replace(',', '.'));
                } catch (NumberFormatException nfe) {
                    System.err.println("Montant invalide. Veuillez saisir un nombre (ex: 500 ou 500,75).");
                    return;
                }
                // Pas nécessaire de fermer le scanner ici, le programme se termine juste après
            }

            // 4. Appeler la méthode distante
            double resultat = stub.convertirMontant(montant);
            System.out.println("Résultat de la conversion pour " + montant + " : " + resultat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}