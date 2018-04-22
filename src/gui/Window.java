package gui;

import arm.Arm;
import kinematics.InverseK;
import processing.core.PApplet;
import processing.core.PVector;


/***
 *         RANGO DE ANGULOS VALIDOS SOBRE LOS EJES
 *          ________________________________________
 *          q1 = {0,360}  -> vuelta completa
 *          q2 = {40,-90} -> negativos hacia arriba
 *          q3 = {-90,90} -> negativos hacia abajo
 * */


public class Window extends PApplet {

    float size;
    PVector origin;
    float rX,rY,zoom;
    public static float coord_cartesian[] = new float[] {-17,98,-31};
    float millisOld, gTime, gSpeed = 8;
    public static boolean test = false;

    static double angles[];

    static Arm arm;
    InverseK ik;

    /**
     * Funcion de configuracion que se carga antes de Setup
     */
    @Override
    public void settings() {
        size(800, 800, P3D);
    }


    /**
     * Funcion de configuracion de parametros
     * en la ventana previamente creada en settings
     */
    @Override
    public void setup() {
        strokeWeight(1);
        smooth();
        textMode(SHAPE);
        origin  = new PVector(width / 2, height / 2,0);
        size    = 2000;
        zoom    = 1.5f;
        rX      = -0.51f;
        rY      = -0.65f;
        arm     = new Arm(this);
        ik      = new InverseK(arm.getL());
        angles  = new double[]{0,0,0};
    }

    /**
     * Dibuja los elementos que estan dentro de la ventana
     * Entre ellos el brazo, los ejes, etc
     */
    @Override
    public void draw() {
        /** preparacion de la ventana **/
        background(255);
        lights();
        directionalLight(40, 90, 100, 1, 40, 40);

        translate(origin.x,origin.y);
        scale(zoom);


        /** entrada del usuario **/
        userInput();
        /** ejes X Y Z   **/
        drawAxes();
        /** aplicar ik **/
        writePos();

        /** escala de los objetos**/
        scale(-1.2f);

        /** esfera que muestra la posicion en coord X Y Z **/
        pushMatrix();
            noStroke();
            fill(250, 100, 1);
            translate(-coord_cartesian[1] ,-coord_cartesian[2] -11,-coord_cartesian[0] );
            sphere(2);
        popMatrix();



        pushMatrix();
            arm.drawArm();
        popMatrix();
    }

    /**
     * Funcion que maneja la entrada del usuario
     * Como arrastrar el mouse y aplicar zoom
     */
    private void userInput(){
        if(mousePressed){
            rX   -= (mouseY - pmouseY) * 0.002f;//map(mouseY,0,height,-PI,PI);
            rY   -= (mouseX - pmouseX) * 0.002f;// map(mouseX,0,width,PI,-PI);
        }
        rotateX(rX);
        rotateY(rY);

        if(keyPressed){
            if(keyCode == UP){
                zoom += 0.01f;
            }
            if(keyCode == DOWN){
                zoom -= 0.01f;
            }
        }
    }

    /**
     * Dibuja los ejes X Y Z
     */
    private void drawAxes() {
        float margin = 90;

        //X rojo
        text("+X",margin,-2,0);
        text("-X",-margin,-2,0);
        stroke(210, 0, 0);
        line(-size,0,0,size,0,0);

        //Y verde
        text("-Z",2,margin,0);
        text("+Z",2,-margin,0);
        stroke(0, 210, 0);
        line(0,-size,0,0,size,0);

        //Z azul
        text("+Y",5,0,margin);
        text("-Y",5,0,-margin);
        stroke(0, 0, 210);
        line(0, 0, -size,0,0, size);
    }


    /**
     * establece tiempo de retardo entre cada coordenada
     */
    void setTime(){
        gTime += ((float)millis()/1000 - millisOld)*(gSpeed/4);
        if(gTime >= 4)  gTime = 0;
        millisOld = (float)millis()/1000;
    }

    /**
     * Aplica cinematica inversa
     */
    void writePos(){
        arm.setAngles(ik.getAngles(coord_cartesian));
        //setTime();
        //coord_cartesian[0] = sin(gTime*PI/2)*20;      //X
        //coord_cartesian[1] = 90 + sin(gTime*PI)*10;   //Y
        //coord_cartesian[2] = -40 + sin(gTime*PI)*10;  //Z
    }
}
