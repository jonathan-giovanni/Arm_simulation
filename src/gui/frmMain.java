package gui;

import kinematics.InverseK;

import javax.swing.*;
public class frmMain extends Window {

    InverseK ik2;
    public JFrame frame;
    public frmMain ventana;

    private JPanel panel1;
    private JButton btnMinusX;
    private JButton btnMinusZ;
    private JButton btnMinusY;
    private JButton btnPlusX;
    private JButton btnPlusY;
    private JButton btnPlusZ;
    private JTextField txtCoordinateX;
    private JTextField txtCoordinateY;
    private JTextField txtCoordinateZ;

    /**
     * Constructor del formulario
     * Donde se cargan los botones y entradas de Texto
     */
    public frmMain() {


        txtCoordinateX.setText(""+coord_cartesian[0]);
        txtCoordinateY.setText(""+coord_cartesian[1]);
        txtCoordinateZ.setText(""+coord_cartesian[2]);


        txtCoordinateX.addActionListener(a -> {
            coord_cartesian[0] = Float.parseFloat(txtCoordinateX.getText());
            println(" Coordenada  X " + txtCoordinateX.getText());
        });
        txtCoordinateY.addActionListener(a -> {
            coord_cartesian[1] = Float.parseFloat(txtCoordinateY.getText());
            println(" Coordenada Y " + txtCoordinateY.getText());
        });
        txtCoordinateZ.addActionListener(a -> {
            coord_cartesian[2] = Float.parseFloat(txtCoordinateZ.getText());
            println(" Coordenada Z " + txtCoordinateZ.getText());
        });


        btnPlusX.addActionListener(a -> {
            coord_cartesian[0]++;
            txtCoordinateX.setText(String.valueOf(coord_cartesian[0] ));
            applyIK();
        });
        btnMinusX.addActionListener(a -> {
            coord_cartesian[0]--;
            txtCoordinateX.setText(String.valueOf(coord_cartesian[0] ));
            applyIK();
        });

        btnPlusY.addActionListener(a -> {
            coord_cartesian[1]++;
            txtCoordinateY.setText(String.valueOf(coord_cartesian[1] ));
            applyIK();
        });

        btnMinusY.addActionListener(a -> {
            coord_cartesian[1]--;
            txtCoordinateY.setText(String.valueOf(coord_cartesian[1] ));
            applyIK();
        });

        btnPlusZ.addActionListener(a -> {
            coord_cartesian[2]++;
            txtCoordinateZ.setText(String.valueOf(coord_cartesian[2] ));
            applyIK();
        });

        btnMinusZ.addActionListener(a -> {
            coord_cartesian[2]--;
            txtCoordinateZ.setText(String.valueOf(coord_cartesian[2] ));
            applyIK();
        });
    }

    /**
     * Se inicializa el formulario y se agrega el panel
     * Ademas se hace visible
     */
    @Override
    public void settings() {
        super.settings();
        //ik2 = new InverseK(arm.getL());
        JFrame frame = new JFrame("frmMain");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new frmMain().panel1);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void draw() {
        super.draw();
    }

    void applyIK(){
        /*
        if(arm!=null) {
            //ForwardK fk2 = new ForwardK(arm.getL());
            //Cartesian c = fk2.getCartesian(angles, Angle.DEGREES);
            Cartesian c = coord_cartesian;
            InverseK ik2 = new InverseK(arm.getL());

            arm.setAngles( ik2.getAngles(c,Angle.DEGREES), Angle.DEGREES);


            //y->z
            //z->y
            //translate((float)c.getX() ,-(float)c.getZ() , (float)c.getY() );
            //sphere(2);
        }
        System.out.println("Angulos de ik : q1 -> "+angles[0]+" q2 -> "+angles[1]+" q3-> "+angles[2]);
        */
    }

}

