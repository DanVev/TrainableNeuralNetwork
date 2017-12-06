package NeuralNetwork;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class NeuronLayer implements INeuronLayer {
    private IActivationFunction activationFunction;
    private int length;
    private double[] neurons;

    public NeuronLayer(IActivationFunction activationFunction, int length) {
        this.activationFunction = activationFunction;
        this.length = length;
        neurons = new double[length];
    }

    public NeuronLayer(int length) {
        this.length = length;
        neurons = new double[length];
    }

    @Override
    public IActivationFunction getActivationFunction() {
        return activationFunction;
    }

    @Override
    public void setLength(int l) {
        this.length = l;
    }

    @Override
    public double[] getNeurons() {
        return neurons;
    }

    public void setNeurons(double[] neurons) {
        this.neurons = neurons;
    }
}
