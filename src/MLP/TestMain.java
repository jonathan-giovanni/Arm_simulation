package MLP;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TestMain {

    public static void main(String[] args) {


    // initialization
    ArrayList<float[]> ex = new ArrayList<float[]>();
    ArrayList<float[]> out = new ArrayList<float[]>();

    //TODO. área para pruebas
        for(int i = 0; i< 4; ++i)

    {
        ex.add(new float[3]);
        out.add(new float[1]);
    }

    // llena la lista con los ejemplos de entrenamiento
        ex.get(0)[0]=1; ex.get(0)[1]=1;  out.get(0)[0]=1;
        ex.get(1)[0]=1;  ex.get(1)[1]=0;  out.get(1)[0]=1;
        ex.get(2)[0]=0;  ex.get(2)[1]=1; out.get(2)[0]=1;
        ex.get(3)[0]=0; ex.get(3)[1]=0; out.get(3)[0]=-0;

    int nn_neurons[] = {
            ex.get(0).length,    // layer 1: input layer - 2 neurons
            ex.get(0).length * 3,    // layer 2: hidden layer - 6 neurons
            1            // layer 3: output layer - 1 neuron
    };

    MultiLayerPerceptron mlp = new MultiLayerPerceptron(nn_neurons);

        try

    {
        //escribe resultados en el archivo arm2.dat , se guarda en / del proyecto
        PrintWriter fout = new PrintWriter(new FileWriter("arm2.dat"));
        fout.println("#\tX\tY");

        for (int i = 0; i < 1; ++i) {
            mlp.learn(ex, out, 0.3f, fout);
            float error = mlp.evaluateQuadraticError(ex, out);
            System.out.println(i + " -> error : " + error);
            fout.println("\t" + i + "\t" + error);
        }

        fout.close();
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }
}


}
