package UI;

import javafx.geometry.Point2D;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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
    private JButton clearButton;


    private MainWindow() {

        paintingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                ((PaintingPanel) paintingPanel).addPoint(new Point2D(e.getX(), e.getY()));
                System.out.println("add point");
                paintingPanel.repaint();
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ((PaintingPanel) paintingPanel).clear();
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
