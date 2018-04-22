package kinematics;


import static java.lang.Math.*;

public class InverseK {

    private double Long_arm[];

    public InverseK(double[] long_arm) {
        Long_arm = long_arm;
    }

    public double[] getAngles(float coord[]) {
        /*
        coord[0] -> X
        coord[1] -> Y
        coord[2] -> Z
        */

        double Q[] = new double[3]; //guarda los resultados

        /** Entradas en X , Y , Z    **/
        System.out.println("\nX " + coord[0]+ " Y "+ coord[1] + " Z "+ coord[2]);

        float L     = (float) sqrt(coord[1]*coord[1]+coord[0]*coord[0]);
        float dia   = (float) sqrt(coord[2]*coord[2]+L*L);

        /** Ecuaciones de IK        **/
        float alpha = (float) ( PI/2-(atan2(L, coord[2])+acos((Long_arm[1]*Long_arm[1]-Long_arm[0]*Long_arm[0]-dia*dia)/(-2*Long_arm[0]*dia))));
        float beta  = (float) (-PI+acos((dia*dia-Long_arm[1]*Long_arm[1]-Long_arm[0]*Long_arm[0])/(-2*Long_arm[0]*Long_arm[1])));
        float gamma = (float) atan2(coord[1], coord[0]);

        Q[0] = gamma;
        Q[1] = alpha;
        Q[2] = beta;

        /** Salidas en de angulos en radianes **/
        System.out.println("Q[0]  "+Q[0] + " Q[1] " +Q[1] + " Q[2] "+Q[2] );

        return Q;
    }
}