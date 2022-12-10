import Verkehrbestrieb_exceptions.VerkehrsbetriebException;
import jdk.jshell.spi.ExecutionControl;
import lombok.SneakyThrows;
import view.View;

import java.time.LocalDate;


public class Main
{

    public static void main(String[] args) throws VerkehrsbetriebException
    {
        View view = View.getInstance();
        view.mainMenu();


    }
}





