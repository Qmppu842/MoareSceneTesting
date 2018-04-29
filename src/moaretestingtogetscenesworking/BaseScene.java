/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moaretestingtogetscenesworking;

import java.awt.event.ActionListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Qmppu842
 */
public abstract class BaseScene {

    protected SceneManager mgr;
    protected String buttonText;
    protected String nameOfScene;
    protected Scene thisScene;
    protected EventHandler<ActionEvent> moim;
    protected ActionListener ads;
    protected BorderPane allThings;
    protected FlowPane footer;

    public BaseScene(SceneManager mgr) {
        this.mgr = mgr;
        allThings = new BorderPane();
        buttonText = "Say 'Hello World'";

        footer = new FlowPane();
        allThings.setBottom(footer);
    }

    public BaseScene(SceneManager mgr, String buttonText) {
        this.mgr = mgr;
        this.buttonText = buttonText;
    }

    protected Scene getScene() {
        if (thisScene == null && allThings == null) {
            Button btn = new Button();
            btn.setText(buttonText);
            btn.setOnAction(moim);

            StackPane root = new StackPane();
            root.getChildren().add(btn);

            thisScene = new Scene(root, 300, 250);
        } else if (thisScene == null) {
            footer.getChildren().add(backButton());
            thisScene = new Scene(allThings, 300, 250);
        }
        return thisScene;
    }

    protected void setNameOfScene(String name) {
        nameOfScene = name;
    }

    protected Button backButton() {
        EventHandler<ActionEvent> backAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mgr.setScene(1);
            }
        };

        return coreButtonMaker("Back to menu.", backAction);
    }

    protected Button coreButtonMaker(String text, EventHandler<ActionEvent> action) {
        Button btn = new Button();
        btn.setText(text);
        btn.setOnAction(action);
        return btn;
    }
}
