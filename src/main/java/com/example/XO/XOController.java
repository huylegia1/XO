package com.example.XO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class XOController implements Initializable {
    @FXML
    private Label menu;
    @FXML
    private Label game;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button startButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    private int playerTurn = 0;
    ArrayList<Button> buttons;

    public void checkIfGameOver() {
        String line = "";
        for (int n=0; n<8; n++) {
            line = switch(n) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button1.getText() + button4.getText() + button7.getText();
                case 2 -> button1.getText() + button5.getText() + button9.getText();
                case 3 -> button2.getText() + button5.getText() + button8.getText();
                case 4 -> button3.getText() + button6.getText() + button9.getText();
                case 5 -> button3.getText() + button5.getText() + button7.getText();
                case 6 -> button4.getText() + button5.getText() + button6.getText();
                case 7 -> button7.getText() + button8.getText() + button9.getText();
                default -> null;
            };
            if (line.equals("XXX")) {
                statusLabel.setText(nameTextField.getText()+" won!");
                startButton.setText("RESTART");
                for (Button button : buttons) {
                    button.setDisable(true);
                }
            }
            else if (line.equals("OOO")) {
                statusLabel.setText("Opponent won!");
                startButton.setText("RESTART");
                for (Button button : buttons) {
                    button.setDisable(true);
                }
            }
            else {
                if (playerTurn==9) {
                    statusLabel.setText("Draw!");
                    startButton.setText("RESTART");
                    for (Button button : buttons) {
                        button.setDisable(true);
                    }
                }
            }
        }
    }
    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
            button.setText("X");
            playerTurn++;
        }
        else if (playerTurn % 2 != 0) {
            button.setText("O");
            playerTurn++;
        }
    }
    public void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameOver();
        });
    }
    public void resetButton(Button button) {
        button.setText("");
        button.setDisable(false);
    }
    public void startGame() {
        buttons = new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);
        startButton.setOnMouseClicked(mouseEvent -> {
            for (Button button:buttons) {
                button.setFocusTraversable(false);
                setupButton(button);
            }
            if (startButton.getText().equals("RESTART")) {
                statusLabel.setText("");
                playerTurn = 0;
                for (Button button : buttons) {
                   resetButton(button);
                }
           }
        });
    }
//    public void restartGame() {
//        startButton.setOnMouseClicked(mouseEvent -> {
//           if (startButton.getText().equals("RESTART")) {
//               statusLabel.setText("");
//               for (Button button : buttons) {
//                   resetButton(button);
//               }
//           }
//        });
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGame();
    }
}