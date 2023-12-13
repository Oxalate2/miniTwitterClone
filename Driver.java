import javax.swing.SwingUtilities;
// Singleton Pattern Implementation
public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminControlPanel.getInstance().setVisible(true);
            }
        });
    }
}
