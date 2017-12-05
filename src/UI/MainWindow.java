package UI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Vasily Danilin on 05.12.2017.
 */
public class MainWindow {

    private JComboBox letterComboBox;
    private JButton saveButton;
    private JPanel paintingPanel;
    private JPanel rootPanel;
    private JPanel SettingPanel;
    private JLabel letterLabel;


    public MainWindow() {
        paintingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);


            }
        });
    }

    private void createUIComponents() {
        paintingPanel = new PaintingPanel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
