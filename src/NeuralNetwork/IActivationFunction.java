package NeuralNetwork;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public interface IActivationFunction {
    double activationFunction(double t);

    double firstDerivative(double t);
}
