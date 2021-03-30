package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileSaver {

    private ArrayList<String> names;
    private ArrayList<Shape> nodes;
    private String fileName;

    public FileSaver(ArrayList<String>newNames,ArrayList<Shape>newNodes,String fileName) throws IOException {
        this.names = newNames;
        this.nodes = newNodes;
        this.fileName = fileName;

        saveTOFile();
    }

    private void saveTOFile() throws IOException {
        File file = new File(fileName);
        System.out.println(fileName);
        if(!file.exists()){
            JOptionPane.showMessageDialog(null,"文件打开错误");
            return ;
        }
        FileOutputStream out = new FileOutputStream(file,false);
        OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0;i < nodes.size();i ++){

            if(nodes.size() == 0) break;
            stringBuffer.append((toString(nodes.get(i))));
            stringBuffer.append("\n");

        }
        writer.append(stringBuffer);
        writer.close();
        out.close();
    }

    public String toString(Shape shape){
        if(shape instanceof Rectangle){
            StringBuilder var1 = new StringBuilder("Rectangle[");
            String var2 = shape.getId();
            if (var2 != null) {
                var1.append("id=").append(var2).append(", ");
            }

            var1.append("x=").append(((Rectangle)shape).getX() + shape.getLayoutX());
            var1.append(", y=").append(((Rectangle)shape).getY() + shape.getLayoutY());
            var1.append(", width=").append(((Rectangle)shape).getWidth());
            var1.append(", height=").append(((Rectangle)shape).getHeight());
            var1.append(", fill=").append((shape).getFill());
            Paint var3 = (shape).getStroke();
            if (var3 != null) {
                var1.append(", stroke=").append(var3);
                var1.append(", strokeWidth=").append((shape).getStrokeWidth());
            }

            return var1.append("]").toString();
        }
        if(shape instanceof Circle){
            StringBuilder var1 = new StringBuilder("Circle[");
            String var2 = shape.getId();
            if (var2 != null) {
                var1.append("id=").append(var2).append(", ");
            }

            var1.append("centerX=").append(((Circle)shape).getCenterX() + shape.getLayoutX());
            var1.append(", centerY=").append(((Circle)shape).getCenterY() + shape.getLayoutY());
            var1.append(", radius=").append(((Circle)shape).getRadius());
            var1.append(", fill=").append(((Circle)shape).getFill());
            Paint var3 = ((Circle)shape).getStroke();
            if (var3 != null) {

                var1.append(", stroke=").append(var3);
                var1.append(", strokeWidth=").append(((Circle)shape).getStrokeWidth());
            }

            return var1.append("]").toString();

        }
        if(shape instanceof Ellipse){
            StringBuilder var1 = new StringBuilder("Ellipse[");
            String var2 = shape.getId();
            if (var2 != null) {
                var1.append("id=").append(var2).append(", ");
            }

            var1.append("centerX=").append(((Ellipse)shape).getCenterX() + shape.getLayoutX());
            var1.append(", centerY=").append(((Ellipse)shape).getCenterY() + shape.getLayoutY());
            var1.append(", radiusX=").append(((Ellipse)shape).getRadiusX());
            var1.append(", radiusY=").append(((Ellipse)shape).getRadiusY());
            var1.append(", fill=").append(((Ellipse)shape).getFill());
            Paint var3 = ((Ellipse)shape).getStroke();
            if (var3 != null) {
                var1.append(", stroke=").append(var3);
                var1.append(", strokeWidth=").append(((Ellipse)shape).getStrokeWidth());
            }

            return var1.append("]").toString();
        }
        if(shape instanceof Line){
            StringBuilder var1 = new StringBuilder("Line[");
            String var2 = shape.getId();
            if (var2 != null) {
                var1.append("id=").append(var2).append(", ");
            }

            var1.append("startX=").append(((Line)shape).getStartX());
            var1.append(", startY=").append(((Line)shape).getStartY());
            var1.append(", endX=").append(((Line)shape).getEndX());
            var1.append(", endY=").append(((Line)shape).getEndY());
            Paint var3 = ((Line)shape).getStroke();
            if (var3 != null) {
                var1.append(", stroke=").append(var3);
                var1.append(", strokeWidth=").append(((Line)shape).getStrokeWidth());
            }

            return var1.append("]").toString();

        }
        else return " ";
    }
}
