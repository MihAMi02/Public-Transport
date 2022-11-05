package registrationSystem;


import model.Line;

import java.util.List;


public interface GetLine
{
    List<Line> showLine();

    Line getLine(String lineNumber);


}
