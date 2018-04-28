/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moaretestingtogetscenesworking;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Qmppu842
 */
public class BaseScene {

    private SceneManager mgr;
    protected String buttonText;
    protected String nameOfScene;
    protected Scene thisScene;
    protected EventHandler<ActionEvent> moim;
    
    public BaseScene(SceneManager mgr) {
        this.mgr = mgr;
        buttonText = "Say 'Hello World'";
    }

    public BaseScene(SceneManager mgr, String buttonText) {
        this.mgr = mgr;
        this.buttonText = buttonText;
    }

    protected Scene getScene() {
        if (thisScene == null) {
            Button btn = new Button();
            btn.setText(buttonText);
            btn.setOnAction(moim);

            StackPane root = new StackPane();
            root.getChildren().add(btn);

            thisScene = new Scene(root, 300, 250);
        }
        return thisScene;
    }
    protected void setNameOfScene(String name){
    nameOfScene = name;
    }

}


//new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent event) {
//                    System.out.println("Hello World! " + nameOfScene);
//                }
//            }