/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.alien;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*; //per la mappa

public class FXMLController {
	//
	TreeMap<String, LinkedList<String>> dizionarioAlieno= new TreeMap<String, LinkedList<String>>();
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
    	
    	this.txtParola.clear();
    	this.txtTranslate.clear();
    	
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	
    	String array[];
    	array= this.txtParola.getText().toLowerCase().split(" ");
    	
    	//devo restituire la traduzione
    	if(array.length==1) {
    		
    		if(dizionarioAlieno.size()==0) {
    			this.txtTranslate.setText("Il dizionario è ancora vuoto");
    			return;
    		}
    		
    		String parolaAl= array[0];
    		String s=""; //per il risultato
    		if(parolaAl.contains("?")==false) {
    			//caso normale: stampo tutte le traduzioni per quella parola aliena
    			for(String str: dizionarioAlieno.get(parolaAl)) {
    				if(s!="")
    					s+="\n";
    				s+=str;
    			}
    			this.txtTranslate.setText(s);
    			
    		}else{
    			//es: ALI?NO
    			int i=0; //conto il numero di ?
    			for(char c: parolaAl.toCharArray())
    				if(c== '?')
    					i++;	
    			if(i>1)
    				this.txtTranslate.setText("Mettere un solo '?' ");
    			else {
    				int j= parolaAl.indexOf('?'); //ricavo l'indice del '?'
    				String prima= parolaAl.substring(0, j);
    				String dopo=parolaAl.substring(j+1);
    				//ora cerco tra le parole aliene che ho nel dizionario
    				for(String str: dizionarioAlieno.keySet()) {
    					//controllo che siano della stessa lunghezza
    					if(str.length()== parolaAl.length()) {
    						String a= str.substring(0, j);
    						String b=str.substring(j+1);
    						if(a.compareTo(prima)==0 && b.compareTo(dopo)==0) {
    			    				if(s!="")
    			    					s+="\n";
    			    				s+="Parola aliena: "+ str+ " traduzione: "+dizionarioAlieno.get(str);
    						}
    						this.txtTranslate.setText(s);
    					}
    				}
    			}
    		}
    	}	

    	//devo aggiungere la parola al dizionario
    	else if(array.length==2) {
    		
    		String parolaAliena= array[0];
    		String traduzione= array[1];
    		
    		for(char c: parolaAliena.toCharArray()) {
    			if(Character.isDigit(c))
    				checkNumero=true;
    		}
    		for(char c: traduzione.toCharArray()) {
    			if(Character.isDigit(c))
    				checkNumero2=true;
    		}
    		
    		if(!checkNumero && !checkNumero2) {
    			if(dizionarioAlieno.containsKey(parolaAliena)) {
    				dizionarioAlieno.get(parolaAliena).add(traduzione);
    			}else {
    				LinkedList<String> trad= new LinkedList<String>();
    				trad.add(traduzione);
    				dizionarioAlieno.put(parolaAliena, trad);
    			}
    			this.txtParola.clear();
    		}else {
    			this.txtTranslate.setText("Ammesse solo le lettere alfabetiche");
    		}
    		
    	}else {
    		this.txtTranslate.setText("Non viene rispettato il pattern <parolaAliena> <traduzione>");
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
