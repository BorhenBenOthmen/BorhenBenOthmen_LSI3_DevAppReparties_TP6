package rmiServer;

import rmiService.ConversionImpl;
import rmiService.IConversion;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConversionServer {
    public static void main(String[] args) {
        try {
            // 1. Activer le RMI Registry sur le port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println(" RMI Registry démarré sur le port 1099.");

            // 2. Créer l'objet du service
            IConversion stub = new ConversionImpl();
            System.out.println(" Service ConversionImpl instancié.");

            // 3. Publier l’objet dans le registre
            registry.rebind("ConversionService", stub);
            System.out.println(" Service publié sous le nom 'ConversionService'.");

            System.out.println("\n Serveur RMI en attente de connexions...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
