package MLP;

import java.util.ArrayList;
import java.util.List;


public class Layer {

    private int n_neurons;
    private int prev_n_neurons;
    private List<Neuron> neurons;
    private float[] outputs;

    public Layer(int prev_n_neuron,int n_neuron,java.util.Random rand){
        n_neurons=n_neuron+1;
        prev_n_neurons=prev_n_neuron+1;

        neurons= new ArrayList<Neuron>();
        outputs=new float[n_neurons];

        for(int i=0;i<n_neurons;++i)
            neurons.add(new Neuron(prev_n_neurons, rand));
    }


    public static float[] addbias(float[] in){
        float out[] =new float[in.length+1];
        for(int i=0;i<in.length;i++)
            out[i+1]=in[i];
        out[0]=1.0f;

        return out;
    }

    public float[] evaluate(float in[]){
        float inputs[];

        if(in.length  != getWeights(0).length)
            inputs=addbias(in);
        else
            inputs=in;

        assert(getWeights(0).length == inputs.length);

        for(int i=0;i<n_neurons;i++)
            outputs[i]=neurons.get(i).activate(inputs);

        outputs[0]=1.0f;

        return outputs;
    }


    public int size(){
        return n_neurons;
    }

    public float getOutput(int i){
        return outputs[i];
    }

    public float getActivationDerivative(int i){
        return neurons.get(i).getActivationDerivative();
    }

    public float[] getWeights(int i){
        return  neurons.get(i).getSynapticWeights();
    }

    public float getWeight(int i,int j){
        return neurons.get(i).getSynapticWeights(j);
    }

    public void setWeight(int i,int j,float v){
        neurons.get(i).setSynapticWeights(j, v);
    }
}