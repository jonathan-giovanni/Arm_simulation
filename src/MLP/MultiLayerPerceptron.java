package MLP;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiLayerPerceptron {

    private List<Layer> layers;
    private List<float[][]> delta_w;
    private List<float[]> grad_ex;


    //constructor que recibe como parametro un arreglo con la configuracion de cantidad neuronas por capa
    public  MultiLayerPerceptron(int nn_neurons[]){
        Random rand=new Random();
        layers=new ArrayList<Layer>();

        for(int i=0;i<nn_neurons.length;i++)
            layers.add(new Layer(i==0?nn_neurons[i]:nn_neurons[i-1]
                    ,nn_neurons[i], rand));

        delta_w= new ArrayList<float[][]>();

        for(int i=0;i<nn_neurons.length;i++)
            delta_w.add(new float[layers.get(i).size()]
                    [layers.get(i).getWeights(0).length]);

        grad_ex=new ArrayList<float[]>();

        for(int i=0;i<nn_neurons.length;i++)
            grad_ex.add(new float[layers.get(i).size()]);


    }


    //funcion para evaluar que los resultados de salida sean iguales a los de ejemplo entrada
    public float[] evaluate(float[] inputs){
        assert(false);
        float outputs[]=new float[inputs.length];

        for(int i=0;i<layers.size();i++)
        {
            outputs=layers.get(i).evaluate(inputs);
            inputs=outputs;
        }
        return outputs;
    }

        //evalua el error en la evaluacion de los valores de salida deseados
    private float evaluateError(float nn_output[],float desired_output[]){
        float d[];

        if(desired_output.length != nn_output.length)
            d=Layer.addbias(desired_output);
        else
            d=desired_output;

        assert(nn_output.length == d.length);

        float e=0;

        for(int i=0;i<nn_output.length;i++)
            e+=(nn_output[i]-d[i])*(nn_output[i]-d[i]);

        return e;

    }

    public float evaluateQuadraticError(ArrayList<float[]> examples,
                                        ArrayList<float[]> results){
        assert(false);

        float e=0;

        for(int i=0;i<examples.size();i++)
        {
            e+=evaluateError(evaluate(examples.get(i)), results.get(i));
        }

        return e;
    }

    private void  evaluateGradients(float[] results){

        for(int c=layers.size()-1;c>=0;c--){
            for(int i=0;i<layers.get(c).size();i++){

                //if its output layer
                if(c== layers.size()-1){
                    grad_ex.get(c)[i] =2*(layers.get(c).getOutput(i)-results[0])
                            *layers.get(c).getActivationDerivative(i);
                }
                else{
                    float sum=0;
                    for(int k=1;k<layers.get(c+1).size();k++){
                        sum+=layers.get(c+1).getWeight(k, i)*grad_ex.get(c+1)[k];
                        grad_ex.get(c)[i]=layers.get(c).getActivationDerivative(i)*sum;

                    }

                }

            }
        }


    }


    private void resetWeightsDelta(){
        for(int c=0;c<layers.size();c++){
            for(int i=0;i<layers.get(c).size();i++){
                float[] weights=layers.get(c).getWeights(i);
                for(int j=0;j<weights.length;j++)
                    delta_w.get(c)[i][j]=0;
            }
        }
    }

    private void evaluateWeightsDelta(){

        for (int c = 1; c < layers.size(); ++c) {
            for (int i = 0; i < layers.get(c).size(); ++i) {
                float weights[] = layers.get(c).getWeights(i);
                for (int j = 0; j < weights.length; ++j)
                    delta_w.get(c)[i][j] += grad_ex.get(c)[i]
                            * layers.get(c-1).getOutput(j);
            }
        }

    }

    private void updateWeights(float learning_rate)
    {
        for (int c = 0; c < layers.size(); ++c) {
            for (int i = 0; i < layers.get(c).size(); ++i) {
                float weights[] = layers.get(c).getWeights(i);
                for (int j = 0; j < weights.length; ++j)
                    layers.get(c).setWeight(i, j, layers.get(c).getWeight(i, j)
                            - (learning_rate * delta_w.get(c)[i][j]));
            }
        }
    }

    private void batchBackPropagation(ArrayList<float[]> examples,
                                      ArrayList<float[]> results,
                                      float learning_rate,PrintWriter fout)
    {
        resetWeightsDelta();
        float[] output=new float[0];
        List<Float> outputList=new ArrayList<Float>();
        //Prints header

        for (int l = 0; l < examples.size(); ++l) {

            output =evaluate(examples.get(l));
            if(output.length!=0)
                outputList.add(output[1]);

            evaluateGradients(results.get(l));
            evaluateWeightsDelta();
        }

        fout.println("input x | intput y | output z");
        for(int i=0;i<examples.size();i++){
            fout.println(examples.get(i)[0]+"      "+examples.get(i)[1]+"      "+outputList.get(i));

        }

        updateWeights(learning_rate);

    }

    public void printlayer(){
        for(int i=0;i<layers.size();i++)
        {
            System.out.print("|  Layer "+i);
        }
        System.out.println("");
    }

    public void learn(ArrayList<float[]> examples,
                      ArrayList<float[]> results,
                      float learning_rate,PrintWriter fout)
    {
        assert(false);

        float e = Float.POSITIVE_INFINITY;

        while (e > 0.001f) {

            batchBackPropagation(examples, results, learning_rate,fout);

            e = evaluateQuadraticError(examples, results);
        }

    }

    public static void main(String args[]){
        // initialization
        ArrayList<float[]> ex = new ArrayList<float[]>();
        ArrayList<float[]> out = new ArrayList<float[]>();
        for (int i = 0; i < 4; ++i) {
            ex.add(new float[3]);
            out.add(new float[1]);
        }

        // fill the examples database
        ex.get(0)[0] = -1; ex.get(0)[1] = 1;  out.get(0)[0] = 1;
        ex.get(1)[0] = 1;  ex.get(1)[1] = 1;  out.get(1)[0] = -1;
        ex.get(2)[0] = 1;  ex.get(2)[1] = -1; out.get(2)[0] = 1;
        ex.get(3)[0] = -1; ex.get(3)[1] = -1; out.get(3)[0] = -1;

        int nn_neurons[] = {
                ex.get(0).length, 	// layer 1: input layer - 2 neurons
                ex.get(0).length * 3, 	// layer 2: hidden layer - 6 neurons
                1			// layer 3: output layer - 1 neuron
        };

        MultiLayerPerceptron mlp = new MultiLayerPerceptron(nn_neurons);

        try {
            PrintWriter fout = new PrintWriter(new FileWriter("plot.dat"));
            fout.println("#\tX\tY");

            for (int i = 0; i < 1; ++i) {
                mlp.learn(ex, out, 0.3f,fout);
                float error = mlp.evaluateQuadraticError(ex, out);
                System.out.println(i + " -> error : " + error);
                fout.println("\t" + i + "\t" + error);
            }

            fout.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }



}