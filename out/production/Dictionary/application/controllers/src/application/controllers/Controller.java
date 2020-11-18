package application.controllers;

import application.dictionary.Dictionary;
import application.dictionary.Word;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class Controller {
    @FXML
    private TextField searchField;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private AnchorPane wordPane;
    @FXML
    private AnchorPane editPane;
    @FXML
    private ListView<String> searchResult;
    @FXML
    private ImageView rightPaneLogo;
    @FXML
    private Button goButton;

    protected Dictionary dict;

    private WordPaneController wordPaneController;
    private EditPaneController editPaneController;



    public void handleGoButton(ActionEvent actionEvent) {
        String query = searchField.getText().toUpperCase();
        System.out.println("Showing result of : "+ query);

        ObservableList<String> items = FXCollections.observableArrayList();

        for(Word s : dict.dictionarySearcher(query))
            items.add(s.getWord());

        System.out.println(items.size()+" results found");

        if(items.isEmpty())
            items.add(query);
        else{
            //for(String s : items)
                //System.out.println(s);
            //System.out.println("End of results");
            //System.out.println(query + " exists in the data");
        }

        searchResult.setItems(items);
    }

    public void showEditPane(Word s){
        editPaneController.setData(s);
        rightPane.getChildren().setAll(editPane);
    }
    public void showWordPane(String s){
        wordPaneController.setWordLabel(s);
        rightPane.getChildren().setAll(wordPane);
    }

    public void handleWordChoose(MouseEvent mouseEvent) {
        if(searchResult.getSelectionModel().getSelectedItem() != null){
            showWordPane(searchResult.getSelectionModel().getSelectedItem());
        }
    }

    public void resetRightPane(){
        rightPane.getChildren().remove(wordPane);
    }

    public void initialize(){
        dict = new Dictionary();
        System.out.println("Dictionary initializing..");
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/resources/view/wordPane.fxml"));
            wordPane = fxmlLoader.load();
            wordPaneController = fxmlLoader.getController();
            wordPaneController.initData(this);
        } catch(IOException e){
            e.printStackTrace();
        }

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/resources/view/editPane.fxml"));
            editPane = fxmlLoader.load();
            editPaneController = fxmlLoader.getController();
            editPaneController.initData(this);
        } catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Dictionary initialized");
    }

    public void handleSave(ActionEvent actionEvent) {

    }

    public void handleAdd(ActionEvent actionEvent) {
        

    }
}
