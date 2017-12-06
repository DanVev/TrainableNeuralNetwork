package NeuralNetwork;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public interface INeuronLayer {
    IActivationFunction getActivationFunction();

    void setLength(int l);

    double[] getNeurons();
}
