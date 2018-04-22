package arm;


import processing.core.PApplet;
import processing.core.PShape;


public class Arm {

    PApplet context;
    PShape base, shoulder, upArm, loArm, end;
    double L[];
    double Q[];

    /**
     * Constructor de Arm se cargan los .obj
     * y se establece la longitud del brazo
     * @param pApplet
     */
    public Arm(PApplet pApplet){
        context     = pApplet;

        //pos [0] -> F
        //pos [1] -> T
        L = new double[]{50,70};
        Q = new double[]{0,0,0};

        base        = context.loadShape("r5.obj");
        shoulder    = context.loadShape("r1.obj");
        upArm       = context.loadShape("r2.obj");
        loArm       = context.loadShape("r3.obj");
        end         = context.loadShape("r4.obj");

        shoulder.disableStyle();
        upArm.disableStyle();
        loArm.disableStyle();
    }

    /**
     * Establece los angulos en radianes al brazo
     * @param q
     */
    public void setAngles(double q[]){
        Q = q;
    }

    /**
     * dibuja el brazo con cada uno de sus elementos
     */
    public void drawArm(){
        /**     base no rotatoria   **/
        context.fill(255, 200, 10,100);
        context.translate(0,-40,0);
        context.shape(base);
        /**     base rotatoria      **/
        context.translate(0, 4, 0);
        context.rotateY((float) Q[0]);//gamma
        context.shape(shoulder);
        /**     antebrazo           **/
        context.fill(60, 200, 130,100);
        context.translate(0, 25, 0);
        context.rotateY(context.PI);
        context.rotateX((float) (Q[1]));//alpha
        context.shape(upArm);
        /**      brazo               **/
        context.translate(0, 0, 50);
        context.rotateY(context.PI);
        context.rotateX((float) (Q[2]  ));//beta
        context.shape(loArm);
        /**     orientacion         **/
        context.fill(140, 200, 100,100);
        context.translate(0, 0, -50);
        context.rotateY(context.PI);
        context.shape(end);
    }

    /**
     * Retorna la longitud del brazo
     * @return L
     */
    public double[] getL(){return L;}

    /**
     * Retorna los angulos en radianes
     * @return Q
     */
    public double[] getQ(){return Q;}
}

