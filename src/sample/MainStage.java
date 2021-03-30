package sample;


import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainStage extends Stage {

    private Group root;
    private BorderPane borderPane;
    private MyPane myPane;
    private NowShape shape;
    private Calculate calculate;

    MainStage(){

        setTitle("画板");
        setResizable(false);
        getIcons().add(new Image(Path.APP_LOGO));
        root = new Group();
        borderPane = new BorderPane();
        shape = new NowShape();
        calculate = new Calculate();


        ToolsBar toolsBar = new ToolsBar(shape);

        myPane = new MyPane(shape,getCalculate(),toolsBar);

        Menu_Bar menu_bar = new Menu_Bar(this);




        borderPane.setStyle("-fx-background-color: #F0F0F0;");

        Scene scene = new Scene(root, Size.Window_LENGTH, Size.Window_WIDE, Color.WHITE);



        VBox vbox = toolsBar.getvBox();
        HBox hbox = calculate.getBox();

        borderPane.setLeft(vbox);
        borderPane.setTop(menu_bar.getMenuBar());
        borderPane.setBottom((hbox));

        root.getChildren().addAll(borderPane,myPane.getCanvas());
        setScene(scene);
        show();
    }

    public MyPane getMyPane() {
        return myPane;
    }

    public Calculate getCalculate() {
        return calculate;
    }

}


