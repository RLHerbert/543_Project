package Project543;

import javafx.stage.Stage;

public class ProjectStage extends Stage {
    //Member Variables
    public static final int DEF_WINDOW_HEIGHT = 775;
    public static final int DEF_WINDOW_WIDTH = 800;
    public static final int DEF_X_POS = 100;
    public static final int DEF_Y_POS = 100;

    //Member Methods

    //Constructors

    //Getters

    //Setters
    void setStageSizePos(int height, int width, int x_pos, int y_pos){
        this.setHeight(height);
        this.setWidth(width);
        this.setX(x_pos);
        this.setY(y_pos);
    }

    //Misc. Methods
}
