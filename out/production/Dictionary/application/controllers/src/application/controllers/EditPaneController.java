package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import application.dictionary.*;
import javafx.scene.text.Text;

public class EditPaneController {

    private Controller state;
    @FXML
    private TextField editPaneWord;
    @FXML
    private TextField editPaneTranslation;
    @FXML
    private TextField editPaneMessage;
    @FXML
    private Text textMessage;

    private String oldWord;
    private String oldTranslation;
    private String message;

    public void initData(Controller state){
        this.state = state;
        oldWord = new String();
        oldTranslation = new String();
        message = new String();
    }

    public void setData(Word s){
        editPaneWord.setText(s.getWord());
        editPaneTranslation.setText(s.getTranslation());

        oldWord = s.getWord();
        oldTranslation = s.getTranslation();
    }

    public void onSaveButton(ActionEvent actionEvent) {

        boolean wordChanged = oldWord != editPaneWord.getText();
        boolean translationChanged = oldTranslation != editPaneTranslation.getText();

        if(!wordChanged && !translationChanged){
            message = "No change was made";
        } else if(!wordChanged){
            message = "Translation of " + oldWord + " has been changed";
            state.dict.insert(new Word(oldWord,editPaneTranslation.getText()));
        } else if(!translationChanged){
            message = "Spelling of " + oldWord +" has been changed";
            state.dict.delete(oldWord);
            state.dict.insert(new Word(editPaneWord.getText(),editPaneTranslation.getText()));
        } else{
            message = "Spelling and translation of " + oldWord +" has been changed";
            state.dict.delete(oldWord);
            state.dict.insert(new Word(editPaneWord.getText(),editPaneTranslation.getText()));
        }

        oldWord = editPaneWord.getText();
        oldTranslation = editPaneTranslation.getText();
        editPaneMessage.setText(message);
    }

    public void handleBackButton(ActionEvent actionEvent) {
        state.showWordPane(oldWord);
    }
}
