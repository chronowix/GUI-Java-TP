import gui.MyApp;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){
        // TODO: Lancer MyApp dans l'event dispatch thread
        SwingUtilities.invokeLater(() -> {
            new MyApp().setVisible(true);
        });
    }
}