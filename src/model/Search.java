package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Search {
    // --- 1. PATTERN SINGLETON ---
    private static Search instance;

    // Constructeur PRIVÉ pour empêcher le 'new Search()'
    private Search() {}

    // Méthode publique pour récupérer l'unique instance
    public static Search getInstance() {
        if (instance == null) {
            instance = new Search();
        }
        return instance;
    }

    // --- 2. LOGIQUE D'INSPECTION D'UN FICHIER ---
    public List<String> inspect(String file, String toSearch) {
        List<String> results = new ArrayList<>();
        File f = new File(file);

        // On ne traite que les fichiers .java
        if (!f.getName().endsWith(".java")) {
            return results;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                // Utilisation de indexOf comme demandé dans le sujet
                if (line.indexOf(toSearch) > -1) {
                    results.add(f.getAbsolutePath() + "§" + lineNumber);
                }
              
                
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
