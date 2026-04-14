package gui;

import javax.swing.*;
import java.awt.*;

public class Dialogue extends JDialog {
    private JTextArea textArea;

    public Dialogue(JFrame parent, String msg) {
        super(parent, true);
        this.setTitle("Information");
        this.setSize(400, 250);

        textArea = new JTextArea(msg);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        this.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton close = new JButton("Close");
        close.addActionListener(event -> this.dispose());
        this.getContentPane().add(close, BorderLayout.SOUTH);

        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
}
