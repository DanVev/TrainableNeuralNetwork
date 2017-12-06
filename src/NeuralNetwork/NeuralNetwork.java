package NeuralNetwork;

import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class NeuralNetwork {
    private List<INeuronLayer> layers = new ArrayList<>();
    private List<double[][]> weightsList = new ArrayList<>();
    private int length = 0;

    public NeuralNetwork addLayer(INeuronLayer layer) {
        layers.add(layer);
        length++;
        return this;
    }

    public NeuralNetwork initWeights() {
        for (int i = 0; i < length - 1; i++)
            weightsList.add(new double[layers.get(i).getLength()][layers.get(i + 1).getLength()]);
        return this;
    }

    public void train(Sample trainSample) {
        if (weightsList.size() == 0)
            initWeights();
        //backPropagation algorithm
        double[] trainSampleArray = trainSample.getPoints();
        double[] responses = forwardPropagation(trainSampleArray);

    }

    private double[] forwardPropagation(double[] signals) {
        for (int n = 0; n < length - 1; n++) {
            INeuronLayer firstLayer = layers.get(n);
            INeuronLayer secondLayer = layers.get(n + 1);
            double[][] weights = weightsList.get(n);
            //find signal for neurons in second layer
            for (int j = 0; j < secondLayer.getLength(); j++) {
                double sum = 0.0;
                double[] neurons = firstLayer.getNeurons();
                for (int i = 0; i < firstLayer.getLength(); i++)
                    sum += neurons[i] * weights[i][j];
                secondLayer.setSignal(j, sum);
            }
        }
        return layers.get(length - 1).getNeurons();
    }

    public Pair<Integer, Double> predict(Sample testSample) {
        if (weightsList.size() == 0)
            initWeights();
        double[] responses = forwardPropagation(testSample.getPoints());
        System.out.println(Arrays.toString(responses));
        return new Pair<>(0, 0.0);
    }
}
