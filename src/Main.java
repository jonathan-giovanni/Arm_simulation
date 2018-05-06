import gui.frmMain;
import kinematics.ForwardK;
import kinematics.InverseK;
import processing.core.PApplet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;


/**
            -Z  -X
             |  /
             | /
             |/
     -Y------/-------+Y
            /|
           / |
          /  |
        +X  +Z
 **/


public class Main {

    /**
     * Funcion main que inicializa las ventanas del brazo
     * @param args
     */
    public static void main(String[] args) {

        //TODO datos de prueba

        float[] coord_cartesian = new float[3];
        double L[] = new double[]{50,70};
        double Q[] = new double[]{0,0,0};

        ForwardK fk = new ForwardK(L);

        InverseK ik = new InverseK(L);
/*
        //posicion inicial
        coord_cartesian[0] = 0;
        coord_cartesian[1] = 50;
        coord_cartesian[2] = 0;
*/
        double angulos[];
        double cartesiano[];
        ArrayList<float[]> listCoord_cartesian = new ArrayList<float[]>();
        ArrayList<double[]> listAngulos = new ArrayList<double[]>();
        ArrayList<double[]> listCartesiano = new ArrayList<double[]>();
        ArrayList<float[]> listError = new ArrayList<float[]>();
        float[] copyCoordCartesian = new float[3];
        float[] error = new float[3];


        for (int i = 0; i < 77 ; i++) {

            coord_cartesian[0] = i;
            coord_cartesian[1] = 50;
            coord_cartesian[2] = i;
            //lista para guardar coordenadas cartesianas
            listCoord_cartesian.add(i,new float[]{coord_cartesian[0],coord_cartesian[1],coord_cartesian[2]});

            //devuelve angulos ik
            angulos = ik.getAngles(coord_cartesian);

            angulos[0] = angulos[0] * 180/Math.PI;
            angulos[1] = angulos[1] * 180/Math.PI;
            angulos[2] = angulos[2] * 180/Math.PI;

            //lista para guardar angulos
            listAngulos.add(i,new double[] {angulos[0],angulos[1],angulos[2]});
            cartesiano   = fk.getCartesian(new double[]{angulos[0],angulos[1],angulos[2]});

            //agrega
            listCartesiano.add(i,new double[]{cartesiano[0],cartesiano[1],cartesiano[2]});


            //calcula el error entre coordenadas input ik, y coordenadas output de fk.
            error[0] = (float) (coord_cartesian[0]-cartesiano[0]);
            error[1] = (float) (coord_cartesian[1]-cartesiano[1]);
            error[2] = (float) (coord_cartesian[2]-cartesiano[2]);

            //agregar las operaciones a la lista
            listError.add(i,new float[]{error[0],error[1],error[2]});

            System.out.println("fk  Px  "+cartesiano[1] + " Py " +cartesiano[2] + " Pz "+cartesiano[0] );
            /** Salidas en  angulos en radianes **/
            System.out.println("ik  Q[0]  "+angulos[0] + " Q[1] " +angulos[1] + " Q[2] "+angulos[2] );

        }

        //escribir en el archivo .dat ubicado en / del proyecto
        try

        {
            //escribe resultados en el archivo testFK.dat , se guarda en / del proyecto
            PrintWriter fout = new PrintWriter(new FileWriter("pruebaDomingo.txt"));
            fout.println("(IK)\tX\tY\tZ\tQ0\t Q1\t Q2\t *** \t(FK)\t X\t Y\t Z\t   Error\t X\t Y\t Z");



            for(int i=0;i<listCoord_cartesian.size();i++){
                fout.println("\t"+listCoord_cartesian.get(i)[0]+"\t"+ listCoord_cartesian.get(i)[1]+"\t"+listCoord_cartesian.get(i)[2]+"\t"+                                        //input coord_cartesian
                String.format("%.2f",(listAngulos.get(i)[0])) +"\t"+  String.format("%.2f",(listAngulos.get(i)[1]))+"\t"+  String.format("%.2f",(listAngulos.get(i)[2]))+"\t\t\t"+ //output ik: angles
                String.format("%.2f",listCartesiano.get(i)[0] )+"\t"+String.format("%.2f",listCartesiano.get(i)[1] )+"\t"+String.format("%.2f",listCartesiano.get(i)[2] )+"\t\t\t"+  //output fk: cartesianCoords
                String.format("%.2f",listError.get(i)[0])+"\t"+String.format("%.2f",listError.get(i)[1])+"\t"+String.format("%.2f",listError.get(i)[2])+"\t"                            //error entre coordenadas

                );

            }

            fout.close();
        } catch(
                IOException e)

        {
            e.printStackTrace();
        }

       // double cartesiano[] = fk.getCartesian(new double[]{angulos[0],angulos[1],angulos[2]});

/*          angulos[0] = angulos[0] * 180/Math.PI;
            angulos[1] = angulos[1] * 180/Math.PI;
            angulos[2] = angulos[2] * 180/Math.PI;
*/

        //cartesiano[1] -> x
        //cartesiano[2] -> y
        //cartesiano[0] -> z

      //  System.out.println("fk  Px  "+cartesiano[1] + " Py " +cartesiano[2] + " Pz "+cartesiano[0] );


       // frmMain pWindow = new frmMain();
        //PApplet.main(pWindow.getClass());

    }
}
