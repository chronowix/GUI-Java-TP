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

    private JTextField directoryField;
    private JTextField textField;
    private Stack<Input> history = new Stack<>();

    public MyApp() {
        super("My App");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuApp = new JMenu("Application");

        JMenuItem returnItem = new JMenuItem("Return");
        returnItem.setMnemonic('r');
        returnItem.addActionListener(event -> restoreLastInput());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('q');
        exitItem.addActionListener(event -> exit());

        menuApp.add(returnItem);
        menuApp.add(exitItem);
        menuBar.add(menuApp);
        this.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(new JLabel("Search directory: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        directoryField = new JTextField(20);
        panel.add(directoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Search text: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        textField = new JTextField(20);
        panel.add(textField, gbc);

        this.getContentPane().add(panel, BorderLayout.CENTER);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(event -> apply());
        this.getContentPane().add(applyButton, BorderLayout.SOUTH);
    }

    private void saveState(String rep, String txt) {
        history.add(new Input(rep, txt));
    }

    private void restoreLastInput() {
        if (!history.isEmpty()) {
            Input s = history.removeLast();
            directoryField.setText(s.getDirectoryName());
            textField.setText(s.getSearchText());
        } else {
            new Dialogue(this, "No history available!");
        }
    }

    private void exit() {
        this.dispose();
    }

    private void apply() {
        String directory = directoryField.getText();
        String text = textField.getText();

        if (directory.isEmpty() || text.isEmpty()) {
            new Dialogue(this, "Error: please fill both fields!");
            return;
        }

        saveState(directory, text);

        // Logic search using ForkJoinPool
        java.util.concurrent.ForkJoinPool pool = new java.util.concurrent.ForkJoinPool();
        model.SearchTask task = new model.SearchTask(directory, text);
        List<String> results = pool.invoke(task);

        // Output results to console
        System.out.println("--- Search Results (" + results.size() + " matches found) ---");
        results.forEach(System.out::println);

        // Export results
        exportResults(results);

        new Dialogue(this, "Search completed!\n" + results.size() + " matches found.\nResults exported.");
    }

    private void exportResults(List<String> results) {
        String fileName = "output.txt";
        if (FormatExport.class.isAnnotationPresent(Output.class)) {
            Output ann = FormatExport.class.getAnnotation(Output.class);
            fileName = ann.value();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            results.stream()
                .map(String::toLowerCase)
                .forEach(writer::println);
            System.out.println("Export successful in: " + fileName);
        } catch (IOException e) {
            new Dialogue(this, "Error during export: " + e.getMessage());
        }
    }
}
