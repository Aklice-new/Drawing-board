package sample;

import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenFile {

    private Pane canvas;
    private ArrayList<Shape> nodes;
    private ArrayList<String> names;
    private String filename;
    private MyPane myPane;
    private String regex = "0x[0-9a-f]{8}|[0-9]\\d*.\\d*|0.\\d*[0-9]\\d*";

    public OpenFile(MyPane myPane,String filename) throws IOException {


        this.myPane = myPane;
        //System.out.println(myPane);
        this.canvas = myPane.getCanvas();
        this.nodes = myPane.getNodes();
        this.names = myPane.getNames();
        this.filename = filename;

        readFile();

    }

    private void readFile() throws IOException {
        File file = new File(filename);
        FileInputStream in = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(in);

        StringBuffer stringBuffer = new StringBuffer();

        while(reader.ready()){
            stringBuffer.append((char)reader.read());
        }

        reader.close();
        in.close();
        String []shapes = stringBuffer.toString().split("\\n");
        int cnt = 0;
        for(String v : shapes){

            Shape newShape = null;

            Pattern pattern = Pattern.compile("[a-zA-Z]*");
            Matcher matcher = pattern.matcher(v);

            if(matcher.find()) {

                switch (matcher.group(0)) {
                    case "Line":
                        newShape = getLine(v);
                        //System.out.println("直线的坐标" + newShape.getLayoutX() + "   "+ newShape.getLayoutY());
                        names.add("直线");
                        break;
                    case "Rectangle":
                        newShape = getRectangle(v);
                        //System.out.println("juxing 的坐标" + newShape.getLayoutX() + "   "+ newShape.getLayoutY());
                        names.add("矩形");
                        break;
                    case "Circle" :
                        newShape = getCircle(v);
                        names.add("圆形");
                        break;
                    case "Ellipse" :
                        newShape = getEllipse(v);
                        names.add("椭圆");
                        break;
                    case "Polygon":
                        newShape = getPolygon(v);
                        names.add("三角形");
                        break;
                }

            }
            nodes.add(newShape);
            canvas.getChildren().add(newShape);
            myPane.moveShape(newShape);
        }
    }

    public Shape getRectangle(String string){

        Rectangle rectangle = new Rectangle();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        String []num = new String[8];

        int cnt = 0;
        //System.out.println("rectangle");
        while(matcher.find()){
            num[cnt++] = string.substring(matcher.start(),matcher.end());      // 将每一个匹配到的数值先存起来;
            //System.out.println(string.substring(matcher.start(),matcher.end()));
           // System.out.println(num[cnt - 1]);
        }

        rectangle.setX(Double.parseDouble(num[0]));
        rectangle.setY(Double.parseDouble(num[1]));
        rectangle.setWidth(Double.parseDouble(num[2]));
        rectangle.setHeight(Double.parseDouble(num[3]));
        if(cnt == 7){
            rectangle.setFill(Paint.valueOf(num[4]));
            rectangle.setStroke(Paint.valueOf(num[5]));
            rectangle.setStrokeWidth(Double.parseDouble(num[6]));
        }
        else{
            rectangle.setFill(null);
            rectangle.setStroke(Paint.valueOf(num[4]));
            rectangle.setStrokeWidth(Double.parseDouble(num[5]));
        }

        return rectangle;
    }

    public Shape getLine(String string){

        Line line = new Line();

        Pattern pattern = Pattern.compile(regex);//([1-9]\d*.\d*)|(0.\d*[1-9]\d*)|(|[x0-9a-f]{10})
        Matcher matcher = pattern.matcher(string);
        String []num = new String[6];

        int cnt = 0;
        while(matcher.find()){
           num[cnt++] = string.substring(matcher.start(),matcher.end());      // 将每一个匹配到的数值先存起来;
        }

        line.setStartX(Double.parseDouble(num[0]));
        line.setStartY(Double.parseDouble(num[1]));
        line.setEndX(Double.parseDouble(num[2]));
        line.setEndY(Double.parseDouble(num[3]));
        line.setStroke(Paint.valueOf(num[4]));
        line.setStrokeWidth(Double.parseDouble(num[5]));

        return line;
    }

    public Shape getEllipse(String string){

        Ellipse ellipse = new Ellipse();

        Pattern pattern = Pattern.compile(regex);//([1-9]\d*.\d*)|(0.\d*[1-9]\d*)|(|[x0-9a-f]{10})
        Matcher matcher = pattern.matcher(string);
        String []num = new String[7];

        int cnt = 0;
        while(matcher.find()){
            num[cnt++] = string.substring(matcher.start(),matcher.end());      // 将每一个匹配到的数值先存起来;
        }

        ellipse.setCenterX(Double.parseDouble(num[0]));
        ellipse.setCenterY(Double.parseDouble(num[1]));
        ellipse.setRadiusX(Double.parseDouble(num[2]));
        ellipse.setRadiusY(Double.parseDouble(num[3]));
        if(cnt == 7){
            ellipse.setFill(Paint.valueOf(num[4]));
            ellipse.setStroke(Paint.valueOf(num[5]));
            ellipse.setStrokeWidth(Double.parseDouble(num[6]));
        }
        else {
            ellipse.setFill(null);
            ellipse.setStroke(Paint.valueOf(num[4]));
            ellipse.setStrokeWidth(Double.parseDouble(num[5]));
        }

        return ellipse;
    }

    public Shape getCircle(String string){

        Circle circle = new Circle();

        Pattern pattern = Pattern.compile(regex);//([1-9]\d*.\d*)|(0.\d*[1-9]\d*)|(|[x0-9a-f]{10})
        Matcher matcher = pattern.matcher(string);
        String []num = new String[7];

        int cnt = 0;
        while(matcher.find()){
            num[cnt++] = string.substring(matcher.start(),matcher.end());      // 将每一个匹配到的数值先存起来;
            //System.out.println(num[cnt - 1]);
        }

        circle.setCenterX(Double.parseDouble(num[0]));
        circle.setCenterY(Double.parseDouble(num[1]));
        circle.setRadius(Double.parseDouble(num[2]));
        if(cnt == 6){
            circle.setFill(Paint.valueOf(num[3]));
            circle.setStroke(Paint.valueOf(num[4]));
            circle.setStrokeWidth(Double.parseDouble(num[5]));
        }
        else {
            circle.setFill(null);
            circle.setStroke(Paint.valueOf(num[3]));
            circle.setStrokeWidth(Double.parseDouble(num[4]));
        }

        return circle;
    }

    public Shape getPolygon(String string){

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        String []num = new String[10];

        int cnt = 0;
        while(matcher.find()){
            num[cnt++] = string.substring(matcher.start(),matcher.end());      // 将每一个匹配到的数值先存起来;
        }

        Polygon polygon = new Polygon(Double.parseDouble(num[0]),Double.parseDouble(num[1]),
                                            Double.parseDouble(num[2]),Double.parseDouble(num[3]),
                                                    Double.parseDouble(num[4]),Double.parseDouble(num[5]));

        if(cnt == 9){
            polygon.setFill(Paint.valueOf(num[6]));
            polygon.setStroke(Paint.valueOf(num[7]));
            polygon.setStrokeWidth(Double.parseDouble(num[8]));
        }
        else {
            polygon.setFill(null);
            polygon.setStroke(Paint.valueOf(num[6]));
            polygon.setStrokeWidth(Double.parseDouble(num[7]));
        }

        return polygon;
    }
}
