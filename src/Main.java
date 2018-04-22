import gui.Window;
import gui.frmMain;
import processing.core.PApplet;

public class Main {

    /**
     * Funcion main que inicializa las ventanas del brazo
     * @param args
     */
    public static void main(String[] args) {

        frmMain pWindow = new frmMain();
        PApplet.main(pWindow.getClass());
    }
}
