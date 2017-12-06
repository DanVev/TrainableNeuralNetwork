package NeuralNetwork;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class Sigmoid implements IActivationFunction {
    @Override
    public double activationFunction(double t) {
        return 1.0 / (1.0 + Math.exp(-t));
    }
}
