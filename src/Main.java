import jdk.jshell.spi.ExecutionControl;
import view.View;


public class Main {
    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
        View view = View.getInstance();
        view.mainMenu();
    }
}





