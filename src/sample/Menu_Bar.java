package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu_Bar {                       //    该类是画板上部分的菜单栏的属性 ， 以及点击出现的事件

    private MenuBar menuBar;
    private MenuItem newfile,open,save,saveAs,help,undo,delete;
    private Menu filemenu,aboutmenu,editormenu;
    private MyPane myPane;

    Menu_Bar(MainStage stage){

        this.myPane = stage.getMyPane();
        menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());

        filemenu = new Menu("文件");
        aboutmenu = new Menu("关于");
        editormenu = new Menu("编辑");

        newfile = new MenuItem("新建");
        open = new MenuItem("打开");
        save = new MenuItem("保存");
        saveAs = new MenuItem("另存为 ……");
        help = new MenuItem("关于我们");
        undo = new MenuItem("撤销");

        filemenu.getItems().addAll(newfile, open, save, saveAs);
        aboutmenu.getItems().add(help);
        editormenu.getItems().addAll(undo);

        menuBar.getMenus().addAll(filemenu,editormenu,aboutmenu);

        newfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    stage.getMyPane().getCanvas().getChildren().clear();
                    stage.getMyPane().getNodes().clear();
            }
        });

        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter( "TXT","*.txt"));
                fc.setTitle("打开图片");
                File file = fc.showOpenDialog(null);
                String fileName = file.toString();
                try {
                    new OpenFile(myPane,fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser1 = new FileChooser();
                fileChooser1.setTitle("Save Your Masterpiece");
                File file = fileChooser1.showSaveDialog(stage);
                String fileName = file.toString();
                try {
                    new FileSaver(stage.getMyPane().getNames(),stage.getMyPane().getNodes(),fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        saveAs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser1 = new FileChooser();
                fileChooser1.setTitle("Save Your Masterpiece");
                File file = fileChooser1.showSaveDialog(stage);
                String fileName = file.toString();
                try {
                    new FileSaver(stage.getMyPane().getNames(),stage.getMyPane().getNodes(),fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        undo.setAccelerator(KeyCombination.keyCombination("CTRL+Z"));
        undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) throws IndexOutOfBoundsException{
               try {
                   ArrayList nodes = stage.getMyPane().getNodes();
                   stage.getMyPane().getCanvas().getChildren().remove(nodes.get(nodes.size() - 1));
                   nodes.remove(nodes.get(nodes.size() - 1));
               }
               catch(IndexOutOfBoundsException ex){

               }
            }
        });

        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("关于我们");
                alert.setContentText("产品名称 ： ** 画板\n" +
                        "版本号 ：1.0.0\n" + "作者 ：Aklice\n" + "完成时间 ：2020/12/14");
                alert.showAndWait();
            }
        });


    }

    public MenuBar getMenuBar(){
        return menuBar;
    }

}
