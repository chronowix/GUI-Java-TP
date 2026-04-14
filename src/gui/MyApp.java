package gui;

import model.Input;
import model.FormatExport;
import annotation.Output;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Stack;

public class MyApp extends JFrame {

    private JTextField champRepertoire;
    private JTextField champTexte;
    private Stack<Input> historique = new Stack<>();

    public MyApp() {
        super("Mon application");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuApp = new JMenu("Application");

        JMenuItem itemRetour = new JMenuItem("Retour");
        itemRetour.setMnemonic('r');
        itemRetour.addActionListener(event -> restaurerDerniereSaisie());

        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemQuitter.setMnemonic('q');
        itemQuitter.addActionListener(event -> quitter());

        menuApp.add(itemRetour);
        menuApp.add(itemQuitter);
        menuBar.add(menuApp);
        this.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(new JLabel("Répertoire recherche : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        champRepertoire = new JTextField(20);
        panel.add(champRepertoire, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Texte recherché : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        champTexte = new JTextField(20);
        panel.add(champTexte, gbc);

        this.getContentPane().add(panel, BorderLayout.CENTER);

        JButton boutonAppliquer = new JButton("Appliquer");
        boutonAppliquer.addActionListener(event -> appliquer());
        this.getContentPane().add(boutonAppliquer, BorderLayout.SOUTH);
    }

    private void sauvegarderEtat(String rep, String txt) {
        historique.add(new Input(rep, txt));
    }

    private void restaurerDerniereSaisie() {
        if (!historique.isEmpty()) {
            Input s = historique.removeLast();
            champRepertoire.setText(s.getNomRepertoire());
            champTexte.setText(s.getTexteRecherche());
        } else {
            new Dialogue(this, "Aucun historique disponible !");
        }
    }

    private void quitter() {
        this.dispose();
    }

    private void appliquer() {
        String repertoire = champRepertoire.getText();
        String texte = champTexte.getText();

        if (repertoire.isEmpty() || texte.isEmpty()) {
            new Dialogue(this, "Erreur : veuillez remplir les deux champs !");
            return;
        }

        sauvegarderEtat(repertoire, texte);

        // TODO: List<String> resultats = Recherche.getInstance().rechercher(repertoire, texte);
        // TODO: resultats.forEach(System.out::println);
        // TODO: exporterResultats(resultats);

        new Dialogue(this, "Recherche lancée !\nRépertoire : " + repertoire + "\nTexte : " + texte);
    }

    private void exporterResultats(List<String> resultats) {
        String nomFichier = "out.txt";
        if (FormatExport.class.isAnnotationPresent(Output.class)) {
            Output ann = FormatExport.class.getAnnotation(Output.class);
            nomFichier = ann.value();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
            resultats.stream()
                .map(String::toLowerCase)
                .forEach(writer::println);
            System.out.println("Export réussi dans : " + nomFichier);
        } catch (IOException e) {
            new Dialogue(this, "Erreur lors de l'export : " + e.getMessage());
        }
    }
}
