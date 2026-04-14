package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SearchTask extends RecursiveTask<List<String>> {
    private final String path;
    private final String text;

    public SearchTask(String path, String text) {
        this.path = path;
        this.text = text;
    }

    @Override
    protected List<String> compute() {
        List<String> results = new ArrayList<>();
        File folder = new File(path);

        if (!folder.exists()) return results;

        if (folder.isFile()) {
            // Utilisation du Singleton pour inspecter le fichier
            return Search.getInstance().inspect(folder.getAbsolutePath(), text);
        }

        // Si c'est un dossier, on divise en sous-tâches (FORK)
        File[] children = folder.listFiles();
        if (children != null) {
            List<SearchTask> subTasks = new ArrayList<>();
            for (File child : children) {
                SearchTask subTask = new SearchTask(child.getAbsolutePath(), text);
                subTask.fork(); // Lance le travail en parallèle
                subTasks.add(subTask);
            }

            // On récupère les résultats de chaque ouvrier (JOIN)
            for (SearchTask subTask : subTasks) {
                results.addAll(subTask.join());
            }
        }

        return results;
    }
}
