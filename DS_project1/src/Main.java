import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application{
    private File selectFile;
    private DoFile doFile;
    private String souceFilePath;
    private String zipFilePath;

    public void start(Stage primaryStage){

        HBox hBox = new HBox();

        Button bt1 = new Button("压缩文件" );
        Button bt2 = new Button("解压文件");
        Button bt3 = new Button("退出");

        bt1.setMinWidth(150);
        bt1.setMinHeight(70);
        bt2.setMinWidth(150);
        bt2.setMinHeight(70);
        bt3.setMinWidth(150);
        bt3.setMinHeight(70);

        bt1.setOnMouseClicked(e -> btCompress());
        bt2.setOnMouseClicked(e -> {
            try {
                btUncompress();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        bt3.setOnMouseClicked(e -> {
            primaryStage.close();
        });

        hBox.getChildren().addAll(bt1,bt2,bt3);
        hBox.setPadding(new Insets(20,20,20,20));
        hBox.setSpacing(10);
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);

        primaryStage.setWidth(530);
        primaryStage.setHeight(190);

        primaryStage.setTitle("WIN CC");
        primaryStage.show();

    }

    public void btCompress(){
        Stage stage = new Stage();
        stage.setTitle("选择");

        Button btFile = new Button("选择文件");
        Button btDirectory = new Button("选择文件夹");

        btFile.setOnMouseClicked(e -> {
            stage.close();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择文件");
            selectFile = fileChooser.showOpenDialog(stage);
            if(selectFile != null){
                try {
                    path(selectFile.getAbsolutePath());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btDirectory.setOnMouseClicked(e -> {
            stage.close();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("选择文件夹");
            selectFile = directoryChooser.showDialog(stage);
            if(selectFile != null){
                //doFile = new DoFile(selectFile.getAbsolutePath());
                try {
                    path(selectFile.getAbsolutePath());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btFile.setMinWidth(100);
        btFile.setMinHeight(50);
        btDirectory.setMinWidth(100);
        btDirectory.setMinHeight(50);

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20,10,20,20));
        vBox.getChildren().addAll(btFile,btDirectory);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    public void btUncompress() throws Exception {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("解压文件");
        selectFile = fileChooser.showOpenDialog(stage);
        if(selectFile != null){
            doFile = new DoFile(selectFile.getAbsolutePath());
            doFile.doUncompress();
        }
    }

    public void path(String path) throws Exception {
        souceFilePath = path;
        int len = 0;
        int start = 0;
        for(int i = 0; i < path.length();i++){
            if(path.charAt(i) == '.'){
                len = i;
            } else if(path.charAt(i) == '\\'){
                start = i;
            }
        }
        zipFilePath = path.substring(0,len) + ".cczip";
        if(len == 0){
            zipFilePath = path  + ".cczip";
        }
        doFile = new DoFile(zipFilePath,souceFilePath);
        try {
            doFile.doCompress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
