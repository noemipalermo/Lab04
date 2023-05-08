package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente cercaStudente(int matricola) {
		
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola  = ?;";
		
		Studente studenteTrovato = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {

				studenteTrovato = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return studenteTrovato;
			

		} catch (SQLException e) {
			System.out.println("Errore in StudenteDAO cercaStudente");
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM studente= s, iscrizione= i, corso = c "
				+ "WHERE s.matricola =i.matricola AND c.codins = i.codins AND s.matricola = ?";
		
		List<Corso> corsiTrovati = new ArrayList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");


				Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsiTrovati.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return corsiTrovati;
			

		} catch (SQLException e) {
			System.out.println("Errore in StudenteDAO getCorsiStudente");
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

	public boolean cercaStudenteInCorso(int matricola, String codins) {
		String sql = "SELECT s.matricola "
				+ "FROM studente= s, iscrizione= i "
				+ "WHERE s.matricola =i.matricola AND  i.codins = ?  AND s.matricola = ? ";
		
		boolean trovato = false;
		List<Integer> studenteTrovato = new ArrayList<>();
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			st.setInt(2, matricola);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {
				;
				studenteTrovato.add(rs.getInt("matricola"));
			}
			
			st.close();
			rs.close();
			conn.close();
			
			if(studenteTrovato.size()!=0) {
				trovato= true;
			}
			return trovato;
			

		} catch (SQLException e) {
			System.out.println("Errore in StudenteDAO getCorsiStudente");
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}

	}

}
