package kinematics;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ForwardK {
    private double Q[];
    private double L[];

    public ForwardK(double[] l) {
        L = l;
    }
    public double[] getCartesian(double [] q){
        Q = q;



        //ordenando
        //x->y
        //y->z
        //z->x
        //aqui opera solo con radianes




        double x = (L[0]*cos(-PI/2 +Q[1] )+L[1]*cos(PI/2 -Q[1]+Q[2] ))*sin(Q[0]);//ok

        double y = (L[0]*cos(  PI/2) +L[1]*cos( Q[1]+Q[2] )*cos(Q[0] + PI));//ok

        double z =  L[0]*sin(PI/2 + Q[1] )+L[1]*sin(PI/2- Q[1]+Q[2] );//ok


        //if(angle==Angle.DEGREES) for(int i=0;i<Q.length;i++) Q[i] = Q[i]*(180/Math.PI);

        double cartesian[] = new double[3];
        cartesian[0] = x;
        cartesian[1] = y;
        cartesian[2] = z;



        return cartesian;
    }



}
