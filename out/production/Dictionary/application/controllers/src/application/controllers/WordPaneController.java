package application.controllers;

import application.dictionary.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class WordPaneController {
    @FXML
    private Label wordLabel;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;
    @FXML
    private Label meaningField;

    private Controller state;

    public void setWordLabel(String wordLabel){
        this.wordLabel.setText(wordLabel);
        TrieNode it = state.dict.stringSearch(wordLabel);
        if(it == null || !it.existWord()){
            meaningField.setText("No translation");
        } else{
            meaningField.setText(it.getWord().getTranslation());
        }
    }

    public Label getWordLabel() {
        return wordLabel;
    }

    public void handleEditButton(ActionEvent actionEvent) {
        String meaning = new String();

        if(!meaningField.hasProperties()){
            meaning = "No translation";
        } else{
            meaning = meaningField.getText();
        }

        state.showEditPane(new Word(wordLabel.getText(),meaning));
    }
    public void handleDeleteButton(ActionEvent actionEvent) {
        editButton.setVisible(true);
    }
    public void handleBackButton(ActionEvent actionEvent){
        System.out.println("back");
        state.resetRightPane();
    }

    public void initData(Controller state){
        this.state = state;
    }
}
