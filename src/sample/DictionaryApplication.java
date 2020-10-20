
package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DictionaryApplication implements Initializable  {
    @FXML
    public Button search;

    @FXML
    public TextField imWord;
    @FXML
    public TextArea outWord;
    @FXML
    public ListView<String> listView;
    @FXML
    public TextArea exampleArea;
    //add word
    @FXML
    public Button add;
    @FXML
    public TextField addNewWord;
    @FXML
    public TextField meanWord;
    @FXML
    public TextField voidWord;
    @FXML
    public TextField typeWord;
    //edit
    @FXML
    public Button edit;
    @FXML
    public TextField oldWordtf;
    @FXML
    public TextField newWordtf;
    //delete
    @FXML
    public Button delete;
    @FXML
    public TextField delWord;
    //say
    @FXML
    public Button speak;
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement wordDicManagement = new DictionaryManagement();
        wordDicManagement.insertFromFile();
        listView.getItems().addAll(wordDicManagement.wordTarget());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setOnMouseClicked(eventListviewChoose -> {
            String wordSelect = listView.getSelectionModel().getSelectedItem();
            if (wordSelect != null && !wordSelect.equals("")) {
                Word word = wordDicManagement.dictionaryLookup(wordSelect);
                String wordOut = word.word_explain + "\n"
                        + word.word_pronounce + "\n"
                        + word.word_round;
                outWord.setText(wordOut);
                imWord.setText(word.word_target);
            }
         });

        search.setOnMouseClicked(eventButtonSearch -> {
            String word = imWord.getText();
            Word wordLookup = wordDicManagement.dictionaryLookup(word);
            String wordOut = wordLookup.word_explain + "\n"
                    + wordLookup.word_pronounce + "\n"
                    + wordLookup.word_round;
            outWord.setText(wordOut);
        });

        imWord.setOnKeyReleased(keyEvent -> {
            String wordTf = imWord.getText();
            List<String> listSearcher = wordDicManagement.dictionarySearcher(wordTf);
            listView.getItems().remove(0, listView.getItems().size());
            listView.getItems().addAll(listSearcher);

        });
        speak.setOnMouseClicked(e -> {
                Voice.speech(imWord.getText());
            });



        /*
        addword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String wordAdd = insertWord.getText();
                Label label = new Label();
                TextInputDialog addAleart = new TextInputDialog("Nhap du lieu");
                addAleart.setTitle("My Dictionary");
                addAleart.setHeaderText("Information");
                addAleart.setContentText(wordAdd + " có nghĩa là: ");
                addAleart.show();
/*
            String wordAdd2 = label.getText();
            Word wordAdded = new Word(wordAdd,wordAdd2,"","");
            try {
                addWord(wordAdded);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        });*/

        add.setOnMouseClicked(addEvent -> {
            String addnewWord = addNewWord.getText();
            String addvoidWord = voidWord.getText();
            String addtypeWord = typeWord.getText();
            String addmeanWord = meanWord.getText();
            Word addword = new Word(addnewWord,addvoidWord,addtypeWord,addmeanWord);
            try {
                wordDicManagement.addWord(addword);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Từ "+addnewWord+" có nghĩa là "+addmeanWord+" đã được thêm vào phần từ điển");
            alert.show();
        });

        edit.setOnMouseClicked(editEvent -> {
            String oldWord = oldWordtf.getText();
            String newWord = newWordtf.getText();
            try {
                wordDicManagement.editWord(oldWord,newWord);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Từ "+oldWord+" được sửa thành " + newWord);
            alert.show();
        });

        delete.setOnMouseClicked(deleteEvent -> {
            String deleteWord = delWord.getText();
            try {
                wordDicManagement.removeWord(deleteWord);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Từ " + deleteWord + " đã được xóa khỏi từ điển của bạn");
            alert.show();
        });
    }
}