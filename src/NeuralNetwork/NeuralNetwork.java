package NeuralNetwork;

import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class NeuralNetwork {
    private List<INeuronLayer> layers = new ArrayList<>();
    private List<double[][]> weightsList = new ArrayList<>();
    private int length = 0;
    private boolean isRandomly = true;
    private double trainingSpeed = 0.05;

    public NeuralNetwork() {
    }

    public NeuralNetwork(boolean isRandomly, double trainingSpeed) {
        this.isRandomly = isRandomly;
        this.trainingSpeed = trainingSpeed;
    }

    public double getTrainingSpeed() {
        return trainingSpeed;
    }

    public void setTrainingSpeed(double trainingSpeed) {
        this.trainingSpeed = trainingSpeed;
    }

    public NeuralNetwork addLayer(INeuronLayer layer) {
        layers.add(layer);
        length++;
        return this;
    }

    public NeuralNetwork setRandomly(boolean b) {
        this.isRandomly = b;
        return this;
    }

    public NeuralNetwork initWeights() {
        for (int i = 0; i < length - 1; i++) {
            double[][] e = new double[layers.get(i).getLength()][layers.get(i + 1).getLength() - 1];
            if (isRandomly)
                for (int j = 0; j < e.length; j++) {
                    e[j] = new Random().doubles((long) e[j].length, 0.0, 1.0).toArray();
                }
            weightsList.add(e);
        }
        //for test only
        //weightsList.set(0, new double[][]{{1, 2, 3}, {2, 3, 4}, {3, 4, 5}});
        //weightsList.set(1, new double[][]{{1, 2, 3, 4}, {2, 3, 4, 5,}, {3, 4, 5, 6}, {4, 5, 6, 7}});
        return this;
    }

    public void train(Sample trainSample) {
        if (weightsList.size() == 0)
            initWeights();
        //backPropagation algorithm
        double[] trainSampleArray = trainSample.getPoints();
        double[] responses = forwardPropagation(trainSampleArray);
        int outputLength = layers.get(this.length - 1).getLength();
        double[] correctAnswers = new double[outputLength];
        correctAnswers[trainSample.getClassNumber() - 1] = 1;
        //calculate deltas for the last layer
        double[] lastLayerDelta = new double[outputLength];
        for (int i = 0; i < outputLength; i++)
            lastLayerDelta[i] = layers.get(length - 1).getActivationFunction().firstDerivative(responses[i]) * (correctAnswers[i] - responses[i]);
        List<double[]> layerDeltas = new ArrayList<>();
        layerDeltas.add(lastLayerDelta);
        //for each layer, staring with layer before the last
        for (int i = length - 2; i >= 0; i--) {
            double[] delta = new double[layers.get(i).getLength()];
            // for each neuron in i-layer
            for (int j = 0; j < layers.get(i).getLength(); j++) {
                double sum = 0.0;
                //find delta as sum of errors of the next layer
                for (int k = 0; k < layers.get(i + 1).getLength(); k++)
                    sum += weightsList.get(i)[j][k] * layerDeltas.get(length - 2 - i)[k];
                delta[j] = layers.get(i).getActivationFunction().firstDerivative(layers.get(i).getNeurons()[j]) * sum;
            }
            layerDeltas.add(delta);
        }
        //change weights
        //for each weights between layers
        for (int l = 0; l < weightsList.size(); l++) {
            double[][] weights = weightsList.get(l);
            for (int i = 0; i < weights.length; i++) {
                for (int j = 0; j < weights[i].length; j++) {
                    weights[i][j] += trainingSpeed * layers.get(l).getNeurons()[i] * layerDeltas.get(weightsList.size() - 1 - l)[j];
                }
            }
        }

    }

    private double[] forwardPropagation(double[] signals) {
        layers.get(0).setNeurons(signals);
        for (int n = 0; n < length - 1; n++) {
            INeuronLayer firstLayer = layers.get(n);
            INeuronLayer secondLayer = layers.get(n + 1);
            double[][] weights = weightsList.get(n);
            //find signal for neurons in second layer
            for (int j = 1; j < secondLayer.getLength(); j++) {
                double sum = 0.0;
                double[] neurons = firstLayer.getNeurons();
                for (int i = 0; i < firstLayer.getLength(); i++)
                    sum += neurons[i] * weights[i][j - 1];
                secondLayer.setSignal(j, secondLayer.getActivationFunction().activationFunction(sum));
            }
        }
        return layers.get(length - 1).getResponses();
    }

    public Pair<Integer, Double> predict(Sample testSample) {
        if (weightsList.size() == 0)
            initWeights();
        double[] responses = forwardPropagation(testSample.getPoints());
        System.out.println(Arrays.toString(responses));
        return new Pair<>(0, 0.0);
    }
}
