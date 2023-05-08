package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			System.out.println("Errore in CorsoDAO getTuttiICorsi");
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(Corso corso) {
		final String sql = "SELECT * "
				+ "FROM corso "
				+ "WHERE codins = ?";
		Corso c = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return c;
			

		} catch (SQLException e) {
			System.out.println("Errore in CorsoDAO getCorso");
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
		
		final String sql = "SELECT s.matricola,s.cognome, s.nome, s.CDS "
				+ "FROM studente= s, iscrizione= i "
				+ "WHERE i.codins  = ? AND s.matricola =i.matricola;";

		List<Studente> studentiIscritti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Studente s = new Studente(matricola, cognome, nome, cds);
				studentiIscritti.add(s);
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return studentiIscritti;
			

		} catch (SQLException e) {
			System.out.println("Errore in CorsoDAO getStudentiIscrittiAlCorso");
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		final String sql = "INSERT INTO iscrizione (matricola, codins) VALUES (?, ?);";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			st.executeUpdate();
			
			
			st.close();
			conn.close();
			
			return true;
			

		} catch (SQLException e) {
			System.out.println("Errore in CorsoDAO getStudentiIscrittiAlCorso");
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		// ritorna true se l'iscrizione e' avvenuta con success
	}

}
