package lk.ijse.TheFlora.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static boolean checkFinalResult(ArrayList<String> regex, JFXButton button, JFXTextField... textFields){

        for (int i = 0; i < textFields.length; i++) {
            if (textFields[i].getText().equals("")|!regex(button,textFields[i],textFields[i].getText(),regex.get(i) )){
                textFields[i].requestFocus();
                textFields[i].setFocusColor(Paint.valueOf("red"));
                button.setDisable(true);
                return false;
            }else {
            }
        }
        return true;
    }
    public static boolean regex(JFXButton button, JFXTextField field, String type, String regex,String style){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(type);
        if (matcher.matches()){
            field.setFocusColor(Paint.valueOf("transparent"));
            field.setStyle("-fx-text-fill:"+style);
            button.setDisable(false);
        }else {
            button.setDisable(true);
            field.setStyle("-fx-text-fill: ReD");
        }
           return matcher.matches();
    }
    public static boolean regex(JFXButton button, JFXTextField textField, String type ,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(type);
        if (matcher.matches()){
            textField.requestFocus();
            button.setDisable(false);
        }else {
        }
        return matcher.matches();
    }
}
