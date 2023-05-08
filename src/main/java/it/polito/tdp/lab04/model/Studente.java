package it.polito.tdp.lab04.model;

public class Studente {
	private Integer matricola;
	private String cognome;
	private String nome;
	private String cds;
	
	
	public Studente(Integer matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}


	public Integer getMatricola() {
		return matricola;
	}


	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCds() {
		return cds;
	}


	public void setCds(String cds) {
		this.cds = cds;
	}


	@Override
	public String toString() {
		return matricola + " " + nome + " " + cognome +"\n";
	}
	
	
}
