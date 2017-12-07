import NeuralNetwork.*;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class Test {
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.addLayer(new NeuronLayer(2)).
                addLayer(new NeuronLayer(3, new Sigmoid())).
                addLayer(new NeuronLayer(4, new Sigmoid()));
        neuralNetwork.train(new Sample(new double[]{10, 20}, 2, 1, 3));
        System.out.println();
    }
}
