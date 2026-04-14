package model;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Search extends RecursiveTask<List<String>> {
    // TODO: Implémenter le pattern Singleton (instance unique)

    // Attributs pour Fork/Join
    private String path;
    private String text;

    // TODO: Constructeur privé ou spécifique pour Fork/Join

    public List<String> inspect(String file, String toSearch){
        // TODO: Vérifier si le fichier finit par .java
        // TODO: Lire ligne par ligne, utiliser indexOf
        // TODO: Retourner liste de "nomFichier§numLigne"
        return null;
    }
    @Override
    protected List<String> compute(){
        // TODO: Implémenter la logique Fork/Join ici pour la parallélisation
        return null;
    }
}
