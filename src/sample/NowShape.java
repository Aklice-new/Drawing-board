package sample;


import javafx.scene.paint.Color;

public class NowShape {          //   这个类提供的当前面板上所有关于画图的控件的信息

    private String name;                   //   所画图形名称
    private boolean isFill;                 // 是否填充
    private double strokeWidth;             // 图形边框
    private Color lineColor;                // 边线颜色
    private Color fillColor;                // 填充颜色
    private boolean isWriting;              // 目前画板状态     true : 画图中   false  : 移动中

    NowShape(){
        this.name = "矩形";
        isFill = false;
        strokeWidth = 1;
        lineColor = Color.web("0x0000FF",1);
        fillColor = Color.web("0xcccccfff",1);
        isWriting = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFill(boolean fill) {
        isFill = fill;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public String getName() {
        return name;
    }

    public boolean getIsWriting() {
        return isWriting;
    }

    public void setWriting(boolean writing) {
        isWriting = writing;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public boolean getIsFill(){
        return isFill;
    }

}
