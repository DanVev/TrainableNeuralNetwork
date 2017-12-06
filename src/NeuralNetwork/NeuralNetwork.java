package NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class NeuralNetwork {
    private List<INeuronLayer> layers = new ArrayList<>();
    List<double[]> weights = new ArrayList<>();
    private int length = 0;

    public NeuralNetwork addLayer(INeuronLayer layer) {
        layers.add(layer);
        length++;
        return this;
    }

    public void train() {
    }

    ;

    public void predict() {
    }

    ;
}
