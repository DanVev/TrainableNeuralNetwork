import NeuralNetwork.*;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class Test {
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.addLayer(new NeuronLayer(3)).
                addLayer(new NeuronLayer(4)).
                addLayer(new NeuronLayer(5));
        neuralNetwork.predict(new Sample(new double[]{1, 2, 3}, 3, 1));
    }
}
