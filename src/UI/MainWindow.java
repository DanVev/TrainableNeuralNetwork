package UI;

import NeuralNetwork.*;
import javafx.geometry.Point2D;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by Vasily Danilin on 05.12.2017.
 */
public class MainWindow {

    private JPanel paintingPanel;
    private JPanel rootPanel;
    private JButton clearButton;
    private JPanel SettingPanel;
    private JComboBox letterComboBox;
    private JButton trainButton;
    private JLabel accuracyNumberLabel;
    private JButton predictButton;
    private JLabel predictedLabel;
    private JLabel accuracyLabel;
    private JLabel letterLabel;
    private static NeuralNetwork network;
    //private  static final String[] letters = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л" ,"М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", ""};
    private static final char[] letters = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ".toCharArray();


    private MainWindow() {

        paintingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                ((PaintingPanel) paintingPanel).addPoint(new Point2D(e.getX(), e.getY()));
                paintingPanel.repaint();
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ((PaintingPanel) paintingPanel).clear();
                letterLabel.setText("");
                accuracyNumberLabel.setText("");
            }
        });
        trainButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                network.train(new Sample(((PaintingPanel) paintingPanel).getPoints(), 300, 400, letterComboBox.getSelectedIndex()));
            }
        });
        predictButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Pair<Integer, Double> result = network.predict(new Sample(((PaintingPanel) paintingPanel).getPoints(), 300, 400, letterComboBox.getSelectedIndex()));
                letterLabel.setText(String.valueOf(letters[result.getKey() + 1]));
                accuracyNumberLabel.setText(result.getValue().toString());
            }
        });
    }

    private void createUIComponents() {
        paintingPanel = new PaintingPanel();
        paintingPanel.setSize(300, 400);
        paintingPanel.setMinimumSize(new Dimension(300, 400));
        paintingPanel.setMaximumSize(new Dimension(300, 400));
        paintingPanel.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setSize(380, 400);
        frame.setResizable(false);
        frame.setContentPane(new MainWindow().rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        network = new NeuralNetwork();
        network.addLayer(new NeuronLayer(60 * 80)).
                addLayer(new NeuronLayer(60 * 80 * 5, new Sigmoid())).
                addLayer(new NeuronLayer(32, new Sigmoid()));
        Sample.setCompression(5);

    }
}
