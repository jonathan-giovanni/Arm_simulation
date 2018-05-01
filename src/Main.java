import gui.frmMain;
import kinematics.ForwardK;
import kinematics.InverseK;
import processing.core.PApplet;


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

        float coord_cartesian[] = new float[] {0,0,120};
        double L[] = new double[]{50,70};
        double Q[] = new double[]{0,0,0};

        ForwardK fk = new ForwardK(L);

        InverseK ik = new InverseK(L);

        double angulos[]  = ik.getAngles(coord_cartesian);



        double cartesiano[] = fk.getCartesian(new double[]{0,0,0});




        angulos[0] = angulos[0] * 180/Math.PI;
        angulos[1] = angulos[1] * 180/Math.PI;
        angulos[2] = angulos[2] * 180/Math.PI;

        /** Salidas en de angulos en radianes **/
        System.out.println("ik  Q[0]  "+angulos[0] + " Q[1] " +angulos[1] + " Q[2] "+angulos[2] );
        System.out.println("fk  C[0]  "+cartesiano[0] + " C[1] " +cartesiano[1] + " C[2] "+cartesiano[2] );


        //frmMain pWindow = new frmMain();
        //PApplet.main(pWindow.getClass());

    }
}
