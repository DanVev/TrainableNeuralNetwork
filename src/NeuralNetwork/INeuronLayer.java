package NeuralNetwork;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public interface INeuronLayer {
    IActivationFunction getActivationFunction();

    void setLength(int l);

    int getLength();

    void setSignal(int position, double signal);

    INeuronLayer setNeurons(double[] neurons);

    double[] getNeurons();

    double[] getResponses();
}
