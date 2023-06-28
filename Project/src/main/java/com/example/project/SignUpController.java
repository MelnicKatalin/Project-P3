package com.example.project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button button_signup;
    @FXML
    private Button button_login;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_phone;

    private boolean verify(String phoneNR)
    {
        if(phoneNR.charAt(0)!='0')
        {
            return false;
        }
        int nr = 0;
        for(int i = 0; i < phoneNR.length(); i++)
        {
            if(Character.isDigit(phoneNR.charAt(i)) == true)
            {
                nr++;
            }
        }
        if(nr == 10)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && !tf_phone.getText().trim().isEmpty())
                {
                    if(verify(tf_phone.getText()) == true){
                        DBUtil.signUpUser(event, tf_username.getText(), tf_password.getText(), tf_phone.getText());
                    }
                    else
                    {
                        System.out.println("Invalid Phone Number.");
                    }
                } else {
                    System.out.println("Fill all the information.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information.");
                    alert.show();
                }
            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtil.changeScene(event,"log-in.fxml", "Log In", null, null, 1);
            }
        });
    }
}
