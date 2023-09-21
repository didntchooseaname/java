// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Tableau();
        //Temp();
        //comp();
        //comp2();
        tool();

    }

    public static void Tableau(){
        double T[] = {5.0, 5.5, 3.0, 7.0, 8.0, 4.0, 6.0, 9.0, 3.3, 5.0};

        for (double nombre:T)
            System.out.print(nombre+" ");

        double somme = 0.0;
        for(double i:T)
        {
            somme += i;
        }

        System.out.print("\nSomme = "+somme);

        double moyenne = somme / T.length;

        System.out.print("\nMoyenne = "+moyenne);
    }

    public static void Temp() {

        Scanner scanner = new Scanner(System.in);

        int plusProcheDeZero = Integer.MAX_VALUE; // Initialiser avec une valeur maximale pour le comparer.

        for (int i = 0; i < 6; i++) {
            System.out.print("Entrez un nombre entier : ");

            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                try {
                    int nombre = Integer.parseInt(input);

                    // Comparer avec zéro et mettre à jour le nombre le plus proche.
                    if (Math.abs(nombre) < Math.abs(plusProcheDeZero)) {
                        plusProcheDeZero = nombre;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Seuls les entiers sont autorisés. Fin du programme.");
                    break;
                }
            }
        }

        System.out.println("L'entier le plus proche de zéro est : " + plusProcheDeZero);

        scanner.close();

        /*

1. Tout d'abord, le programme commence par importer la classe `Scanner` qui permet de lire l'entrée utilisateur.

2. Ensuite, une variable `plusProcheDeZero` est initialisée avec `Integer.MAX_VALUE`. Cette valeur est utilisée pour suivre le nombre le plus proche de zéro au fur et à mesure que l'utilisateur entre des nombres.

3. Une boucle `for` est utilisée pour demander à l'utilisateur d'entrer 6 nombres entiers. La boucle s'exécute 6 fois (car `i` va de 0 à 5).

4. À l'intérieur de la boucle, le programme affiche "Entrez un nombre entier : " pour indiquer à l'utilisateur de fournir un nombre.

5. Il y a une vérification pour s'assurer que l'entrée de l'utilisateur est bien une ligne de texte à l'aide de `scanner.hasNextLine()`. Cela permet à l'utilisateur d'entrer une ligne de texte, et non pas seulement un entier.

6. Si une ligne de texte est présente, le programme lit cette ligne avec `scanner.nextLine()` et la stocke dans la variable `input`.

7. Ensuite, il tente de convertir `input` en un entier en utilisant `Integer.parseInt(input)`. Si `input` peut être converti en un entier, il est stocké dans la variable `nombre`. Sinon, si une exception `NumberFormatException` est levée, cela signifie que l'utilisateur n'a pas entré un entier.

8. Si `input` ne peut pas être converti en un entier, le code affiche "Seuls les entiers sont autorisés. Fin du programme." et se termine grâce à l'instruction `break`.

9. Si `input` est converti avec succès en un entier, le code vérifie si la valeur absolue de `nombre` est plus petite que la valeur absolue de `plusProcheDeZero`. Si c'est le cas, cela signifie que `nombre` est plus proche de zéro que la valeur actuellement enregistrée dans `plusProcheDeZero`, alors `plusProcheDeZero` est mis à jour avec la valeur de `nombre`.

10. Une fois que l'utilisateur a entré 6 nombres (ou que l'entrée n'est pas un entier), la boucle se termine.

11. Enfin, le programme imprime "L'entier le plus proche de zéro est : " suivi de la valeur de `plusProcheDeZero`, qui est l'entier le plus proche de zéro parmi ceux que l'utilisateur a entrés.

En résumé, ce code permet à l'utilisateur d'entrer des lignes de texte, tente de les convertir en entiers, et trouve l'entier le plus proche de zéro parmi ceux entrés. Si l'utilisateur entre autre chose qu'un entier, le programme s'arrête en affichant un message d'erreur.
         */

    }

    public static void comp(){

        String cheminImage1 = "C:\\Users\\ajavelier\\Pictures\\visage1.jpg";
        String cheminImage2 = "C:\\Users\\ajavelier\\Pictures\\visage2.jpg";

            try {
                // Charger les images en tant que fichiers d'images
                BufferedImage image1 = ImageIO.read(new File(cheminImage1));
                BufferedImage image2 = ImageIO.read(new File(cheminImage2));

                if (image1 == null || image2 == null) {
                    System.err.println("Impossible de charger les images.");
                    return;
                }

                int largeur = image1.getWidth();
                int hauteur = image1.getHeight();

                // Créer une matrice de pixels pour stocker les différences
                BufferedImage imageDifference = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);

                // Seuil pour détecter le mouvement
                int seuil = 30;

                // Parcourir chaque pixel des deux images pour détecter les différences
                for (int y = 0; y < hauteur; y++) {
                    for (int x = 0; x < largeur; x++) {
                        int pixel1 = image1.getRGB(x, y);
                        int pixel2 = image2.getRGB(x, y);

                        // Comparer la différence entre les valeurs des composantes RGB
                        int diff = Math.abs((pixel1 & 0xFF) - (pixel2 & 0xFF)) +
                                Math.abs(((pixel1 >> 8) & 0xFF) - ((pixel2 >> 8) & 0xFF)) +
                                Math.abs(((pixel1 >> 16) & 0xFF) - ((pixel2 >> 16) & 0xFF));

                        // Si la différence est supérieure au seuil, marquer le pixel comme différent
                        if (diff > seuil) {
                            imageDifference.setRGB(x, y, 0xFF0000); // Rouge pour les pixels différents
                        } else {
                            imageDifference.setRGB(x, y, 0xFFFFFF); // Blanc pour les pixels similaires
                        }
                    }
                }

                // Enregistrer l'image de différence
                String cheminImageDifference = "C:\\Users\\ajavelier\\Pictures\\image_difference.jpg";
                File fichierImageDifference = new File(cheminImageDifference);
                ImageIO.write(imageDifference, "jpg", fichierImageDifference);

                System.out.println("L'image de différence a été enregistrée avec succès.");

            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
1. Importations de bibliothèques : Le code commence par importer les classes nécessaires pour travailler avec des images, notamment `ImageIO` pour lire et écrire des images, `BufferedImage` pour stocker les images en mémoire, `File` pour gérer les fichiers, et `IOException` pour gérer les exceptions liées aux fichiers.

2. Définition des chemins d'accès aux images : Vous spécifiez les chemins d'accès vers les deux images que vous souhaitez comparer.

3. Chargement des images : Les images spécifiées dans les chemins sont chargées en tant que fichiers d'images dans des objets `BufferedImage`. Ces objets représentent les images en mémoire pour que vous puissiez les manipuler.

4. Vérification des images : Le code vérifie si les images ont été correctement chargées. Si l'une des images ne peut pas être chargée, il affiche un message d'erreur et termine le programme.

5. Préparation de la matrice de pixels de différence : Une nouvelle image vide appelée `imageDifference` est créée avec les mêmes dimensions que les images originales. Cette image sera utilisée pour afficher les différences détectées.

6. Seuil de détection : Un seuil est défini pour déterminer quand un pixel est considéré comme différent. Dans cet exemple, le seuil est défini à 30, ce qui signifie que si la différence entre les valeurs des composantes RVB de deux pixels est supérieure à 30, le pixel est considéré comme différent.

7. Comparaison pixel par pixel : Le code parcourt chaque pixel des deux images en utilisant deux boucles `for` imbriquées, une pour la largeur et l'autre pour la hauteur de l'image. Pour chaque paire de pixels correspondants dans les deux images, il compare les valeurs des composantes Rouge, Vert et Bleu (RVB).

8. Calcul de la différence : Le code calcule la différence entre les valeurs RVB des pixels en calculant la somme des différences absolues pour chaque composante. Si cette différence dépasse le seuil défini, le pixel est considéré comme différent.

9. Marquage des pixels différents : Si un pixel est considéré comme différent, il est marqué en rouge dans l'image de différence (`imageDifference`) en utilisant la valeur RVB correspondante (0xFF0000). Sinon, il est marqué en blanc (0xFFFFFF).

10. Enregistrement de l'image de différence : L'image de différence est enregistrée sous forme de fichier JPG à l'emplacement spécifié dans `cheminImageDifference`.

11. Affichage d'un message de réussite : Enfin, le code affiche un message indiquant que l'image de différence a été enregistrée avec succès.

En résumé, ce code charge deux images, les compare pixel par pixel pour détecter les différences en utilisant un seuil, crée une nouvelle image montrant ces différences, et enregistre cette image de différence. Assurez-vous de remplacer les chemins vers vos propres images et d'ajuster le seuil en fonction de vos besoins.
             */

        }

    public static void comp2(){

        String cheminImage1 = "C:\\Users\\ajavelier\\Pictures\\visage1.jpg";
        String cheminImage2 = "C:\\Users\\ajavelier\\Pictures\\visage2.jpg";

                try {
                    // Charger les images en tant que fichiers d'images
                    BufferedImage image1 = ImageIO.read(new File(cheminImage1));
                    BufferedImage image2 = ImageIO.read(new File(cheminImage2));

                    if (image1 == null || image2 == null) {
                        System.err.println("Impossible de charger les images.");
                        return;
                    }

                    int largeur = image1.getWidth();
                    int hauteur = image1.getHeight();

                    // Seuil pour détecter le mouvement
                    int seuil = 30;

                    // Parcourir chaque pixel des deux images pour détecter les différences
                    for (int y = 0; y < hauteur; y++) {
                        for (int x = 0; x < largeur; x++) {
                            int pixel1 = image1.getRGB(x, y);
                            int pixel2 = image2.getRGB(x, y);

                            // Comparer la différence entre les valeurs des composantes RVB
                            int diff = Math.abs((pixel1 & 0xFF) - (pixel2 & 0xFF)) +
                                    Math.abs(((pixel1 >> 8) & 0xFF) - ((pixel2 >> 8) & 0xFF)) +
                                    Math.abs(((pixel1 >> 16) & 0xFF) - ((pixel2 >> 16) & 0xFF));

                            // Si la différence est supérieure au seuil, afficher les coordonnées et sortir de la boucle
                            if (diff > seuil) {
                                System.out.println("Première différence détectée à la position (x, y) : (" + x + ", " + y + ")");
                                return;
                            }
                        }
                    }

                    // Si aucune différence n'est détectée
                    System.out.println("Aucune différence détectée.");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    public static void tool(){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Tapez 'help' pour voir les commandes disponibles.");

        while (true) {
            System.out.print("> ");
            try {
                String input = reader.readLine();
                String[] inputTokens = input.split("\\s+");

                if (inputTokens.length == 0) {
                    continue;
                }

                String command = inputTokens[0];

                switch (command) {
                    case "scan":
                        if (inputTokens.length < 2) {
                            System.out.println("Utilisation : scan <répertoire>");
                            break;
                        }
                        String directoryPath = inputTokens[1];
                        scanDirectory(new File(directoryPath));
                        break;
                    case "copy":
                        if (inputTokens.length < 3) {
                            System.out.println("Utilisation : copy <nomDuFichier> <cheminDestination>");
                            break;
                        }
                        String sourceFilePath = inputTokens[1];
                        String destinationPath = inputTokens[2];
                        copyFile(sourceFilePath, destinationPath);
                        break;
                    case "show":
                        if (inputTokens.length < 2) {
                            System.out.println("Utilisation : show <nomDuFichier>");
                            break;
                        }
                        String filePathToShow = inputTokens[1];
                        showFileContent(filePathToShow);
                        break;
                    case "delete":
                        if (inputTokens.length < 2) {
                            System.out.println("Utilisation : delete <nomDuFichier>");
                            break;
                        }
                        String filePathToDelete = inputTokens[1];
                        deleteFile(filePathToDelete);
                        break;
                    case "stat":
                        if (inputTokens.length < 2) {
                            System.out.println("Utilisation : stat <cheminRépertoire>");
                            break;
                        }
                        String directoryToStat = inputTokens[1];
                        statDirectory(new File(directoryToStat));
                        break;
                    case "help":
                        System.out.println("Commandes disponibles :");
                        System.out.println("   scan <répertoire>                       | Compte les fichiers dans le répertoire et affiche la mémoire utilisée.");
                        System.out.println("   copy <nomDuFichier> <cheminDestination> | Copie un fichier vers un répertoire spécifié.");
                        System.out.println("   show <nomDuFichier>                     | Affiche le contenu d'un fichier dans la console.");
                        System.out.println("   delete <nomDuFichier>                   | Supprime un fichier spécifié.");
                        System.out.println("   stat <cheminRépertoire>                 | Affiche le nom et la taille du plus gros fichier dans le répertoire spécifié.");
                        System.out.println("   help                                    | Affiche les commandes disponibles.");
                        System.out.println("   exit                                    | Quitter le programme.");
                        break;
                    case "exit":
                        System.out.println("Au revoir !");
                        System.exit(0);
                    default:
                        System.out.println("Commande non reconnue. Tapez 'help' pour voir les commandes disponibles.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void scanDirectory(File directory) {
        if (directory.isDirectory()) {
            int fileCount = countFiles(directory);
            System.out.println("Nombre de fichiers dans " + directory.getAbsolutePath() + " : " + fileCount);

            double memoryUsage = (double) directory.getTotalSpace() - directory.getFreeSpace();
            double totalMemory = (double) directory.getTotalSpace();
            double percentageMemoryUsage = (memoryUsage / totalMemory) * 100;

            System.out.println("Pourcentage de mémoire utilisé : " + percentageMemoryUsage + "%");
        } else {
            System.out.println("Le chemin spécifié n'est pas un répertoire valide.");
        }
    }

    private static int countFiles(File directory) {
        int count = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    count++;
                } else if (file.isDirectory()) {
                    count += countFiles(file);
                }
            }
        }
        return count;
    }

    private static void copyFile(String sourceFilePath, String destinationPath) {
        File sourceFile = new File(sourceFilePath);
        File destinationFile = new File(destinationPath);

        try {
            InputStream inputStream = new FileInputStream(sourceFile);
            OutputStream outputStream = new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("Fichier copié avec succès vers " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la copie du fichier.");
        }
    }

    private static void showFileContent(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'affichage du contenu du fichier.");
        }
    }

    private static void deleteFile(String filePath) {
        File fileToDelete = new File(filePath);
        if (fileToDelete.delete()) {
            System.out.println("Fichier supprimé avec succès : " + filePath);
        } else {
            System.err.println("Impossible de supprimer le fichier : " + filePath);
        }
    }

    private static void statDirectory(File directory) {
        if (directory.isDirectory()) {
            File largestFile = findLargestFile(directory);
            if (largestFile != null) {
                System.out.println("Nom du plus gros fichier : " + largestFile.getName());
                System.out.println("Taille du plus gros fichier : " + largestFile.length() + " octets");
                System.out.println("Chemin du plus gros fichier : " + largestFile.getAbsolutePath());
            } else {
                System.out.println("Le répertoire est vide.");
            }
        } else {
            System.out.println("Le chemin spécifié n'est pas un répertoire valide.");
        }
    }

    private static File findLargestFile(File directory) {
        File[] files = directory.listFiles();
        if (files != null && files.length > 0) {
            File largestFile = files[0];
            for (File file : files) {
                if (file.isFile() && file.length() > largestFile.length()) {
                    largestFile = file;
                }
            }
            return largestFile;
        }
        return null;
    }

    /*
1. **Initialisation** : Au début du programme, une instance de `BufferedReader` est créée pour lire les entrées de l'utilisateur depuis la console. Un message de bienvenue est affiché pour indiquer que l'outil en ligne de commande est prêt à être utilisé.

2. **Boucle d'Écoute en Continu** : Le programme entre dans une boucle `while (true)` qui écoute en permanence les commandes de l'utilisateur. Cette boucle assure que l'outil reste actif jusqu'à ce que l'utilisateur décide de le quitter.

3. **Saisie de l'Utilisateur** : À chaque itération de la boucle, le programme affiche l'invite `> ` pour inciter l'utilisateur à entrer une commande. L'entrée de l'utilisateur est lue à l'aide du `BufferedReader`.

4. **Analyse de la Commande** : L'entrée de l'utilisateur est analysée et divisée en "jetons" (ou tokens) en utilisant `input.split("\\s+")`. Le premier jeton est extrait pour identifier la commande à exécuter, tandis que les jetons suivants peuvent contenir des arguments pour cette commande.

5. **Interprétation de la Commande** : Une structure `switch` est utilisée pour interpréter la commande entrée par l'utilisateur. En fonction de la commande, le programme exécute une action spécifique.

   - **"scan"** : La commande "scan" demande au programme d'analyser récursivement un répertoire spécifié par l'utilisateur. Cette action est gérée par la fonction `scanDirectory`, qui compte le nombre de fichiers dans le répertoire et calcule le pourcentage de mémoire utilisé par ce répertoire.

   - **"copy"** : La commande "copy" permet à l'utilisateur de copier un fichier depuis un emplacement source vers un emplacement de destination. La fonction `copyFile` gère cette opération de copie.

   - **"show"** : La commande "show" affiche le contenu d'un fichier spécifié par l'utilisateur dans la console. La fonction `showFileContent` gère cette opération.

   - **"delete"** : La commande "delete" permet à l'utilisateur de supprimer un fichier spécifié. La fonction `deleteFile` s'occupe de cette opération.

   - **"stat"** : La commande "stat" demande au programme de trouver le fichier le plus volumineux dans un répertoire donné et d'afficher son nom, sa taille et son chemin. La fonction `statDirectory` gère cette tâche.

   - **"help"** : La commande "help" affiche la liste des commandes disponibles ainsi que leurs descriptions.

   - **"exit"** : Lorsque l'utilisateur entre "exit", le programme affiche un message de départ et se termine en utilisant `System.exit(0)`.

6. **Affichage des Résultats** : Après avoir exécuté une commande, le programme affiche des résultats ou des messages informatifs pour indiquer à l'utilisateur le résultat de l'opération.

7. **Répétition** : La boucle continue à écouter et à répondre aux commandes de l'utilisateur jusqu'à ce que l'utilisateur décide de quitter en entrant "exit".

8. **Fin du Programme** : Lorsque l'utilisateur entre "exit" et que le programme se termine, un message de départ est affiché, et le programme se ferme.
     */

}
