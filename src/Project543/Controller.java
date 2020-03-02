package Project543;

import java.awt.event.WindowFocusListener;
import java.time.temporal.ValueRange;

public class Controller {
    //Member Variables
    public UI user_interface;
    public FunctionPoint function_point;

    //Member Functions
    //Constructors
    Controller()
    {
        //start UI with default settings
        //just the blank window with menus
        user_interface = new UI();
        function_point = new FunctionPoint();
    }

    Controller(ProjectStage stage)
    {
        //open with particular project stage? or should input be a UI object?
    }

    //Getters
    //mostly getting user input or values calculated using function point methods


    //Setters
    //mostly setting things like different stage, UI changes (based on user input), and
}