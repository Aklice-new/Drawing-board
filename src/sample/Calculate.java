package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class Calculate {

    private HBox hBox;
    private TextField area;
    private TextField perimeter;

    Calculate(){

        hBox = new HBox();
        Label areaLabel = new Label("面积");
        area = new TextField();
        Label perimeterLabel = new Label("周长");
        perimeter = new TextField();
        perimeter.setText("");

        hBox.getChildren().addAll(areaLabel,area,perimeterLabel,perimeter);

    }

    public void setArea(double num) {

        area.setText(num + "");

    }

    public void setPerimeter(double num) {

        perimeter.setText(String.valueOf(num));

    }

    public HBox getBox(){
        return hBox;
    }
}
