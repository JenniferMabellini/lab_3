/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.DictionaryModel;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	DictionaryModel dr;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbBox"
    private ComboBox<String> cmbBox; // Value injected by FXMLLoader

    @FXML // fx:id="txtAreaSopra"
    private TextArea txtAreaSopra; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtAreaSotto"
    private TextArea txtAreaSotto; // Value injected by FXMLLoader

    @FXML // fx:id="lblErrori"
    private Label lblErrori; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="lblSotto"
    private Label lblSotto; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	txtAreaSopra.clear();
    	txtAreaSotto.clear();
    	dr.clear();

    }
    @FXML
    void doChooseLanguage(ActionEvent event) {
    	String language="";
    	language=cmbBox.getValue();
      dr.loadDictionary(language);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long time = System.nanoTime();
    	String input= txtAreaSopra.getText().toLowerCase().replaceAll("[ \\p{Punct}]", " ");
    	String inserite []=input.split(" ");
    	List <String> paroleInserite= new LinkedList <String>();
    	 for(int i=0; i<inserite.length; i++)
    	 {
    		 paroleInserite.add(inserite[i]);
    		 }
     	
     	
    	 List<RichWord> output;
    	 output= dr.spellCheck2(paroleInserite);
    	 long timefinale = System.nanoTime()-time;
    	 for(RichWord rw: output)
    	 {txtAreaSotto.appendText(rw.getParola()+"\n");
		
    	 } 
    	 
    	 lblErrori.setText("errori totali: "+output.size());
    	 lblSotto.setText("Spell check completed in "+timefinale+" nanosecondi.");
    	 }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbBox != null : "fx:id=\"cmbBox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtAreaSopra != null : "fx:id=\"txtAreaSopra\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtAreaSotto != null : "fx:id=\"txtAreaSotto\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblSotto != null : "fx:id=\"lblSotto\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        dr= new DictionaryModel();
        cmbBox.getItems().addAll("Italian","English");
    }
    public void setModel(DictionaryModel d){
    	this.dr=d;
    }
}

