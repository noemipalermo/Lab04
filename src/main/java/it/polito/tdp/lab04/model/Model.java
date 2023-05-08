package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public Studente cercaStudente(int matricola) {
		return this.studenteDAO.cercaStudente(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return this.studenteDAO.getCorsiStudente(matricola);
	}

	public boolean cercaStudenteInCorso(int inputNumerico, String codins) {
		
		return this.studenteDAO.cercaStudenteInCorso(inputNumerico, codins);
	}
	
	public boolean  inscriviStudenteACorso(Studente studente, Corso corso) {
		return this.corsoDAO.inscriviStudenteACorso(studente, corso);
	}
	
	public Corso getCorso(Corso corso) {
		return this.corsoDAO.getCorso(corso);
	}
}
