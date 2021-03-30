package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class MyPane {

    private double x1, y1;
    private double x2, y2;
    private double bx,by;
    private boolean isSelect;
    private Pane canvas;
    private ArrayList<String>names = new ArrayList<>();
    private ArrayList<Shape> nodes = new ArrayList<>();
    private NowShape shape;
    private Calculate calculate;
    private ToolsBar toolsBar;
    private Shape selectShape;           // 被选中的图形
    private Shape drawingShape;          // 正在被画的图形，为了实现即时的看到图形
    private Button delete;             // 删除按钮

    MyPane(NowShape shape, Calculate calculate, ToolsBar toolsBar) {

        isSelect = false;
        canvas = new Pane();
        canvas.setStyle("-fx-background-color: white;");
        canvas.setLayoutX(115);
        canvas.setLayoutY(50);
        canvas.setPrefSize(Size.CANVAS_LENGTH, Size.CANVAS_WIDE);
        this.shape = shape;
        this.toolsBar = toolsBar;
        this.calculate = calculate;
        this.delete = toolsBar.getDeleteButton();

        drawWithMouse();

    }

    public void drawWithMouse() {
        canvas.setOnMousePressed(mouseEvent -> {
            x1 = mouseEvent.getX();
            y1 = mouseEvent.getY();
            /*

            获取鼠标点击的起始位置

             */
            if(shape.getIsWriting()){
                String name = shape.getName();

                boolean isFill = shape.getIsFill();
                Color lineColor = shape.getLineColor();
                Color fillColor = shape.getFillColor();
            /*

            获取当前所有选择栏的属性，形成对应的形状;圆角矩形

             */
                Shape newShapeSuper;
                switch (name) {
                    case "直线": {        //定义一个直线
                        newShapeSuper = new Line();
                        ((Line)newShapeSuper).setStartX(x1);
                        ((Line)newShapeSuper).setStartY(y1);
                        ((Line)newShapeSuper).setEndX(x1);
                        ((Line)newShapeSuper).setEndY(y1);
                        names.add("line");
                        break;
                    }
                    case "矩形": {        //定义一个矩形，并将其加入到面板中
                        newShapeSuper = new Rectangle();
                        ((Rectangle) newShapeSuper).setX(x1);
                        ((Rectangle) newShapeSuper).setY(y1);
                        names.add("rectangle");
                        break;
                    }
                    case "圆形": {        //圆形
                        newShapeSuper = new Circle();//
                        names.add("circle");
                        break;
                    }
                    case "三角形": {       //
//                        double x = x2 - x1;
//                        double y = y2 - y1;
//                        double c1 = x / 2 - y * Math.sqrt(3) / 2 + x1;
//                        double c2 = y / 2 + x * Math.sqrt(3) / 2 + y1;        // 等边三角形
                        double x3 = x2 + 2 * (x1 - x2);
                        double y3 = y2;
                        newShapeSuper = new Polygon();
                        names.add("polygon");
                        break;
                    }
                    case "椭圆": {       //
                        newShapeSuper = new Ellipse();
                        names.add("ellipse");
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + name);
                }

                if(isFill)
                    newShapeSuper.setFill(fillColor);
                else
                    newShapeSuper.setFill(null);

                newShapeSuper.setStrokeWidth(shape.getStrokeWidth());
                newShapeSuper.setStroke(lineColor);

                nodes.add(newShapeSuper);
                drawingShape = newShapeSuper;
                canvas.getChildren().add(newShapeSuper);

                moveShape(newShapeSuper);

            }
        });
        canvas.setOnMouseDragged(mouseEvent -> {

            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();
            /*

            获取鼠标点击的离开位置

             */

            if ((x1 != x2 || y1 != y2) && shape.getIsWriting()) {
                double dis = getdistance(x1, y1, x2, y2);

                String name = shape.getName();
            /*

            获取当前所有选择栏的属性，形成对应的形状;圆角矩形

             */
                switch (name) {
                    case "直线": {        //定义一个直线
                        ((Line)drawingShape).setEndX(x2);
                        ((Line)drawingShape).setEndY(y2);
                        break;
                    }
                    case "矩形": {        //定义一个矩形，并将其加入到面板中

                        ((Rectangle)drawingShape).setX(Math.min(x1,x2));
                        ((Rectangle)drawingShape).setY(Math.min(y1,y2));
                        ((Rectangle)drawingShape).setWidth(Math.abs(x1 - x2));
                        ((Rectangle)drawingShape).setHeight(Math.abs(y1 - y2));
                        break;
                    }
                    case "圆形": {        //圆形
                        ((Circle)drawingShape).setCenterX((x1 + x2) / 2);
                        ((Circle)drawingShape).setCenterY((y1 + y2) / 2);
                        ((Circle)drawingShape).setRadius(dis / 2);
                        break;
                    }
                    case "三角形": {
//                        double x = x2 - x1;
//                        double y = y2 - y1;
//                        double c1 = x / 2 - y * Math.sqrt(3) / 2 + x1;
//                        double c2 = y / 2 + x * Math.sqrt(3) / 2 + y1;        // 等边三角形
                        double x3 = x2 + 2 * (x1 - x2);
                        double y3 = y2;

                        break;
                    }
                    case "椭圆": {

                        ((Ellipse)drawingShape).setCenterX((x1 + x2)/2);
                        ((Ellipse)drawingShape).setCenterY((y1 + y2)/2);
                        ((Ellipse)drawingShape).setRadiusX(Math.abs(x2 - x1)/2);
                        ((Ellipse)drawingShape).setRadiusY(Math.abs(y2 - y1)/2);
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + name);
                }

            }
        });

    }

    private double getdistance(double x1,double y1,double x2,double y2){

        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2) * (y1 - y2));

    }  // 获取两点间距离

    public Pane getCanvas() {
        return canvas;
    }                                // 获取画板

    public ArrayList<Shape> getNodes(){
        return nodes;
    }                      //

    public ArrayList<String> getNames(){
        return names;
    }

    public NowShape getShape() {
        return shape;
    }

    //  对每一个图形添加移动模块

    public void moveShape(Shape newShapeSuper) {

        newShapeSuper.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent1 -> {

            if(shape.getIsWriting())  return;

            ///System.out.println(shape.getIsWriting());
            bx = mouseEvent1.getX();
            by = mouseEvent1.getY();

            isSelect = true;
            selectShape = newShapeSuper;

            changeProperty(selectShape,toolsBar,delete);
            setTextField(selectShape,calculate);
        });

        newShapeSuper.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseEvent -> {
            Glow glow = new Glow();
            glow.setLevel(0.9);
            newShapeSuper.setEffect(glow);
        });

        newShapeSuper.addEventHandler(MouseEvent.MOUSE_EXITED,mouseEvent -> {

            newShapeSuper.setEffect(null);

        });

        newShapeSuper.addEventHandler(MouseEvent.MOUSE_DRAGGED,mouseEvent3 -> {

            if(shape.getIsWriting() || !isSelect) return ;

            double distanceX = mouseEvent3.getX() - bx;
            double distanceY = mouseEvent3.getY() - by;

            double x = newShapeSuper.getLayoutX() + distanceX;
            double y = newShapeSuper.getLayoutY() + distanceY;

            newShapeSuper.setLayoutX(x);
            newShapeSuper.setLayoutY(y);

        });

    }

    // 计算当前选中图形的面积和周长
    public void setTextField(Shape shape,Calculate calculate){
        if(shape instanceof Rectangle){
            calculate.setArea(((Rectangle) shape).getWidth() * ((Rectangle) shape).getWidth());
            calculate.setPerimeter(2 * (((Rectangle) shape).getWidth() + ((Rectangle) shape).getWidth()));
        }
        else if(shape instanceof Line){
            calculate.setArea(0.0);
            calculate.setPerimeter(getdistance(((Line) shape).getStartX(), ((Line) shape).getStartY(),
                    ((Line) shape).getEndX(),((Line) shape).getEndY()));
        }
        else if(shape instanceof Circle){
            calculate.setPerimeter(2 * Math.PI * ((Circle) shape).getRadius());
            calculate.setArea(Math.PI * Math.pow(((Circle) shape).getRadius(),2));
        }
        else if(shape instanceof Polygon){
            ObservableList<Double> points = ((Polygon) shape).getPoints();

            calculate.setPerimeter(
                    getdistance(points.get(0),points.get(1),points.get(2),points.get(3))+
                    getdistance(points.get(0),points.get(1),points.get(4),points.get(5)) +
                    getdistance(points.get(2),points.get(3),points.get(4),points.get(5)));

            calculate.setArea(points.get(0) * points.get(3) - points.get(0) * points.get(5) +
                                    points.get(2)*points.get(5) - points.get(2)*points.get(1) +
                                        points.get(4)*points.get(1) - points.get(4)*points.get(3));
        }
        else if(shape instanceof Ellipse){
            double a = ((Ellipse) shape).getRadiusX();
            double b = ((Ellipse) shape).getRadiusY();
            double c = Math.min(a,b);
            double d = Math.max(a,b);
            calculate.setPerimeter(2 * Math.PI *  d + 4 * (c - d));
            calculate.setArea(Math.PI * ((Ellipse) shape).getRadiusX() * ((Ellipse) shape).getRadiusY());
        }
    }

     // 修改当前选中的图形的属性
    private void changeProperty(Shape newShapeSuper,ToolsBar toolsBar,Button delete){

        CheckBox checkBox = toolsBar.getCheckBox();
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(shape.getIsWriting()) return;
                if(checkBox.isSelected())   {
                    newShapeSuper.setFill(shape.getFillColor());
                    shape.setFill(true);
                }
                else {
                    newShapeSuper.setFill(null);
                    shape.setFill(false);
                }
            }
        });

        // 删除选中图形
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(selectShape == null) return ;

                int index = 0;
                for(Node v : canvas.getChildren()){
                    if(v == newShapeSuper){
                        break;
                    }
                    index ++;
                }

                canvas.getChildren().remove(index);

                index = 0;
                for(Node v : nodes){
                    if(v == newShapeSuper)
                        break;
                    index ++;
                }
                nodes.remove(index);

            }
        });
        ColorPicker colorPickerFill = toolsBar.getColorPickerFill();
        colorPickerFill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(shape.getIsWriting()) return;
                if(shape.getIsFill())
                newShapeSuper.setFill(colorPickerFill.getValue());
            }
        });

        ColorPicker colorPickerLine = toolsBar.getColorPickerLine();
        colorPickerLine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(shape.getIsWriting()) return;
                newShapeSuper.setStroke(colorPickerLine.getValue());
            }
        });

        ScrollBar scrollBar = toolsBar.getScrollBar();

        scrollBar.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            if(shape.getIsWriting()) return;
            selectShape.setStrokeWidth(new_val.doubleValue());
        });

        ScrollBar bigScrollBar = toolsBar.getBigthScrollBar();

        bigScrollBar.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            if(shape.getIsWriting()) return;

            double res = 0;

            if(new_val.doubleValue() > 1)
                res = 1;
            else res = -1;
            if(selectShape instanceof Rectangle){
                ((Rectangle)selectShape).setHeight(res + ((Rectangle)selectShape).getHeight());
                ((Rectangle)selectShape).setWidth(res +  ((Rectangle)selectShape).getWidth());
            }
            if(selectShape instanceof Circle){
                ((Circle)selectShape).setRadius(((Circle)selectShape).getRadius() + res);
            }
            if(selectShape instanceof Ellipse){
                ((Ellipse)selectShape).setRadiusX(((Ellipse)selectShape).getRadiusX() + res);
                ((Ellipse)selectShape).setRadiusY(((Ellipse)selectShape).getRadiusY() + res);
            }

        });
    }

}
