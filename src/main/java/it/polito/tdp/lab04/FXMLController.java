package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	private List<Corso> corsi;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private Button btnCompila;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;
    
    @FXML
    private Button btnCercaStudenteInCorso;

    @FXML
    private ComboBox<Corso> comboCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String inputMatricola = this.txtMatricola.getText();
    	int inputNumerico;
    	
    	try {
    		inputNumerico =  Integer.parseInt(inputMatricola);
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Inserisci un valore numerico");
    		return;
    	}
    	
    	Studente studenteTrovato = this.model.cercaStudente(inputNumerico);
    	List<Corso> corsiTrovati;
    	if(studenteTrovato==null) {
    		this.txtResult.setText("Studente non trovato nel database");
    		return;
    	}else {
    		corsiTrovati  = this.model.getCorsiStudente(inputNumerico);
    	}
    	
    	this.txtResult.clear();
    	if(corsiTrovati.size()!=0) {
    		for(Corso c: corsiTrovati)
    			this.txtResult.appendText(c.toString());
    	}
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	Corso inputCorso= this.comboCorsi.getValue();
    	List<Studente> studentiTrovati = new ArrayList<Studente>();
    	
    	if(inputCorso==(null)) {
    		this.txtResult.setText("Tutti gli studenti iscritti");
    	}else {
    	
	    	studentiTrovati = this.model.getStudentiIscrittiAlCorso(inputCorso);
	    	
	    	this.txtResult.clear();
	    	for(Studente s : studentiTrovati) {
	    		this.txtResult.appendText(s.toString());
	    	}
    	}
    		
    	
    	
    }

    @FXML
    void doCompila(ActionEvent event) {
    	String inputMatricola = this.txtMatricola.getText();
    	int inputNumerico;
    	
    	try {
    		inputNumerico =  Integer.parseInt(inputMatricola);
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Inserisci un valore numerico");
    		return;
    	}
    		
    	Studente resultStudente  = model.cercaStudente(inputNumerico);
    	
    	this.txtNome.setText(resultStudente.getNome());
    	this.txtCognome.setText(resultStudente.getCognome());
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	this.txtResult.clear();
    	String inputMatricola = this.txtMatricola.getText();
    	Corso inputCorso = comboCorsi.getValue();
    	int inputNumerico;
    	boolean success = false;
    	
    	
    	try {
    		inputNumerico =  Integer.parseInt(inputMatricola);
    	}catch(NumberFormatException e) {
    		this.txtResult.appendText("Inserisci un valore numerico");
    		return;
    	}
    	
    	Studente studente= this.model.cercaStudente(inputNumerico);
    	
    	if(inputCorso==null) {
    		this.txtResult.appendText("Seleziona un corso");
    		return;
    	}
    	
    	
    	if(studente == null) {
    		this.txtResult.appendText("Studente non trovato");
    		return;
    	}
    	
    	if(this.model.getCorso(inputCorso)==null) {
    		this.txtResult.appendText("Corso non trovato"); 
    		return;
    	}
    	
    	
		if(this.model.inscriviStudenteACorso(studente, inputCorso)) {
			this.txtResult.clear();
    		this.txtResult.setText("Iscrizione avvenuta con successo");
    	}else {
    		this.txtResult.appendText("Errore nell'iscrizione");
    	}

    }
    
    @FXML
    void doStudenteInCorso(ActionEvent event) {
    	Corso inputCorso = comboCorsi.getValue();
    	String inputMatricola = this.txtMatricola.getText();
    	
    	int inputNumerico;
    	boolean success = false;
    	
    	try {
    		inputNumerico =  Integer.parseInt(inputMatricola);
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Inserisci un valore numerico");
    		return;
    	}
    	
    	if(inputCorso==null) {
    		this.txtResult.appendText("Seleziona un corso");
    		return;
    	}
    	
    	if(this.model.getStudentiIscrittiAlCorso(inputCorso).size()==0) {
    		this.txtResult.setText("Il corso non ha iscritti");
    	}
    	
    	if(this.model.cercaStudente(inputNumerico)== null) {
    		this.txtResult.setText("Studente non trovato");
    	}
    	
    	// TODO gestione studente iscritto a nessun corso
    	
    	if(this.model.cercaStudenteInCorso(inputNumerico,inputCorso.getCodins())) {
    		this.txtResult.setText("Studente iscritto regolarmente al corso");
    	}else {
    		this.txtResult.setText("Lo studente non è iscritto al corso");
    	}
    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtMatricola.clear();
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.txtResult.clear();
    	this.comboCorsi.valueProperty().set(null);
    }

    @FXML
    void initialize() {
    	assert btnCercaStudenteInCorso != null : "fx:id=\"btnCercaStudenteInCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompila != null : "fx:id=\"btnCompila\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		setComboItems();
	}

	private void setComboItems() {
		corsi = model.getTuttiICorsi();
		this.comboCorsi.getItems().addAll(corsi);
	}

}
