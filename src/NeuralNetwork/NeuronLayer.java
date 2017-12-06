package NeuralNetwork;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class NeuronLayer implements INeuronLayer {
    private IActivationFunction activationFunction;
    private int length;
    private double[] neurons;

    public NeuronLayer(int length, IActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.length = length + 1;
        neurons = new double[length + 1];
        neurons[0] = 1;
    }

    public NeuronLayer(int length) {
        this.length = length + 1;
        neurons = new double[length + 1];
        neurons[0] = 1;
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

    public int getLength() {
        return length;
    }

    @Override
    public double[] getNeurons() {
        return neurons;
    }

    @Override
    public double[] getResponses() {
        double[] result = new double[neurons.length - 1];
        for (int i = 0; i < neurons.length - 1; i++)
            result[i] = neurons[i + 1];
        return result;
    }

    @Override
    public void setSignal(int position, double signal) {
        neurons[position] = signal;
    }

    @Override
    public INeuronLayer setNeurons(double[] neurons) {
        for (int i = 0; i < neurons.length; i++) {
            this.neurons[i + 1] = neurons[i];
        }
        return this;
    }
}
