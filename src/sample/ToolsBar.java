package sample;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ToolsBar {
    private VBox vBox;
    private ArrayList<Button> buttons = new ArrayList<>();    //        按键列表
    private ColorPicker colorPickerFill;
    private ColorPicker colorPickerLine;
    private CheckBox checkBox;
    private ScrollBar scrollBar;
    private ScrollBar bigthScrollBar;
    private  Button delete;

    ToolsBar(NowShape shape){
        vBox = new VBox();
        vBox.setPadding(new Insets(20,10,10,10));
        vBox.setSpacing(20);

        Button b;
        ImageView imageView;
        buttons.add(b = new Button("矩形"));
        imageView =  getImageView(new Image(Path.RECTANGLE));
        b.setGraphic(imageView);

        buttons.add(b = new Button("圆形"));
        imageView =  getImageView(new Image(Path.CIRCLE));
        b.setGraphic(imageView);

        buttons.add(b = new Button("椭圆"));
        imageView =  getImageView(new Image(Path.ELLIPSE));
        b.setGraphic(imageView);

        buttons.add(b = new Button("直线"));
        imageView =  getImageView(new Image(Path.LIEN));
        b.setGraphic(imageView);

        delete = new Button("删除");
        buttons.add(delete);

        colorPickerFill = new ColorPicker();                 //
        colorPickerFill.setValue(Color.BLACK);
        colorPickerLine = new ColorPicker();
        colorPickerLine.setValue(Color.WHITE);

        colorPickerFill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shape.setFillColor(colorPickerFill.getValue());
            }
        });

        colorPickerLine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shape.setLineColor(colorPickerLine.getValue());
            }
        });

        for(Button v : buttons){
            v.setMinSize(Size.BUTTON_LENGTH,Size.BUTTON_WIDE);
            v.setMaxSize(Size.BUTTON_LENGTH,Size.BUTTON_WIDE);
            vBox.getChildren().add(v);
            if(v.getText() == "删除") break;
            v.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    shape.setName(v.getText());
                }
            });

        }

        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.HORIZONTAL);
        scrollBar.setMin(0);
        scrollBar.setMax(10);
        scrollBar.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

            shape.setStrokeWidth(new_val.doubleValue());

        });

        bigthScrollBar = new ScrollBar();
        bigthScrollBar.setOrientation(Orientation.HORIZONTAL);
        bigthScrollBar.setMin(0);
        bigthScrollBar.setMax(2);
        bigthScrollBar.setValue(1);

        vBox.getChildren().addAll(new Label("画笔粗细"),scrollBar,new Label("大小"),bigthScrollBar);

        checkBox = new CheckBox("是否填充");           //
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shape.setFill(checkBox.isSelected());
            }
        });

        //       单选目前画图还是移动
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton("画图");
        button1.setToggleGroup(group);
        button1.setSelected(true);
        RadioButton button2 = new RadioButton("调整");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(button1.isSelected()) shape.setWriting(true);
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(button2.isSelected()) shape.setWriting(false);
            }
        });
        button2.setToggleGroup(group);
        hBox.getChildren().addAll(button1,button2);

        vBox.getChildren().addAll(checkBox,colorPickerFill,colorPickerLine,hBox);
    }
    
    private ImageView getImageView(Image curImg) {
        ImageView curImgV = new ImageView(curImg);
        curImgV.setFitHeight(Size.BUTTON_WIDE);
        curImgV.setFitWidth(Size.BUTTON_LENGTH - 60);
        return curImgV;
    }

    public VBox getvBox(){
        return vBox;
    }

    public ColorPicker getColorPickerFill(){
        return colorPickerFill;
    }

    public ColorPicker getColorPickerLine() {
        return colorPickerLine;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public ScrollBar getScrollBar() {
        return scrollBar;
    }

    public ScrollBar getBigthScrollBar() {
        return bigthScrollBar;
    }

    public Button getDeleteButton(){
        return delete;
    }
}
