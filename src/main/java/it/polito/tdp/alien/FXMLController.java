/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*; //per la mappa

public class FXMLController {
	//attributi
	TreeMap<String, String> dizionarioAlieno= new TreeMap<String, String>();
	boolean checkNumero= false;
	boolean checkNumero2= false;
	//
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnTranslate;

    @FXML
    private TextField txtParola;

    @FXML
    private TextArea txtTranslate;

    @FXML
    void doClear(ActionEvent event) {

    }

    @FXML
    void doTranslate(ActionEvent event) {
    	String array[];
    	array= this.txtParola.getText().toLowerCase().split(">");
    	
    	//devo restituire la traduzione
    	if(array.length==1) {
    		this.txtTranslate.setText(dizionarioAlieno.get(array[0].split("<")[1]));
    	}
    	
    	//devo aggiungere la parola al dizionario
    	else if(array.length==2) {
    		
    		String parolaAliena= array[0].split("<")[1]; //1 perchè alla posizione zero c'è lo spazio
    		String traduzione= array[1].split("<")[1];
    		
    		for(char c: parolaAliena.toCharArray()) {
				if(Character.isDigit(c))
					checkNumero=true;
			}
    		for(char c: traduzione.toCharArray()) {
				if(Character.isDigit(c))
					checkNumero2=true;
			}
    		
    		if(!checkNumero && !checkNumero2) {
    			dizionarioAlieno.put(parolaAliena, traduzione);
    			this.txtParola.clear();
    		}else {
    			this.txtTranslate.setText("Ammesse solo le lettere alfabetiche");
    		}
    		
    	}else {
    		this.txtTranslate.setText("Non viene rispettato il pattern");
    	}

    }

    @FXML
    void initialize() {
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTranslate != null : "fx:id=\"txtTranslate\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
