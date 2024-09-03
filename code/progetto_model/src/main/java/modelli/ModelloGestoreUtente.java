package modelli;

/**
 * Modello dei dati dell'utente utilizzatore del programma
 */
public class ModelloGestoreUtente {
	private String mansione;
	private String nome;
	private String cognome;
	private String utente;
	private String codice;
	private String specializzazione;
	
	public void setUtente(String mansione, String nome, String cognome, String utente, String codice, String specializzazione) {
		this.mansione = mansione;
		this.nome = nome;
		this.cognome = cognome;
		this.utente = utente;
		this.codice = codice;
		this.specializzazione = specializzazione;
	}
	
	public String getSpecializzazione() {
		return specializzazione;
	}

	public String getCodiceUtente() {
		return codice;
	}
	
	public String getMansioneUtente() {
		if(mansione.equals("M")) {
			return "Medico";
		}
		else if (mansione.equals("I")) {
			return "Infermiere";
		}
		else {
			return "Operatore";
		}
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getUtente() {
		return utente;
	}
	
}
