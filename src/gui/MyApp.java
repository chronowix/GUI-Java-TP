package gui;

import model.Input;
import javax.swing.*;
import java.util.Stack;

public class MyApp extends JFrame {
    //composants
    // TODO: JTextField pour répertoire et texte recherché
    // TODO: Stack<Saisie> pour l'historique (Mémento)

    public MyApp(){
        super("My App");
        // TODO: Initialiser l'interface (Menu, Labels, Fields, Bouton)
        // TODO: Utiliser des lambdas pour les actions (retournerEnArriere, quitter, appliquer)
    }

    private void returnBack(){
        // TODO: Utiliser la Stack pour restaurer la saisie précédente
    }

    private void exit(){
        // TODO: Fermer l'appli
    }

    private void apply(){
        // TODO: Sauvegarder l'état actuel dans la Stack (Memento)
        // TODO: Appeler Recherche.getInstance().rechercher(...)
        // TODO: Gérer l'affichage console et l'export avec FormatExport et les Streams
    }

    private void export(java.util.List<String> results){
        // TODO: Utiliser les Streams pour passer en minuscules
        // TODO: Utiliser la réflexion pour lire l'annotation @Output sur FormatExport
        // TODO: Écrire dans le fichier (par défaut "out.txt" ou celui de l'annotation)

    }
}
