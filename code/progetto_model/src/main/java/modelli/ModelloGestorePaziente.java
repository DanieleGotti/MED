package modelli;

/**
 * Modello che controlla la selezione di un paziente dalla tabella
 * definisce il valore delle stringhe di "ModelloGestoreVisualizzaDatiPaziente" in base alla selezione
 */
public class ModelloGestorePaziente {
	private String codice;
	private int count;
	private String cognome;
	private String nome;
	private String sesso;
	private String genere;
	private int eta;
	private String data;
	private String ora;
	private String urgenza;

	public ModelloGestoreVisualizzazioneDatiPaziente getModelloView() {
		return modelloView;
	}

	private String posizione;
	private String condizione;
	private Boolean selezionato = false;
	private ModelloGestoreVisualizzazioneDatiPaziente modelloView;
	
	public ModelloGestorePaziente(ModelloGestoreVisualizzazioneDatiPaziente m) {
		modelloView = m;
	}
	
	/**
	 * Aggiorna il valore delle stringhe nelle sezioni "In Pronto Soccorso", "Da Prendere in Carico"
	 * @param codice dovrebbe essere il codice fiscale
	 * @param count del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param cognome
	 * @param nome
	 * @param sesso ammessi valori "M" o "F"
	 * @param genere come il paziente si identifica 
	 * @param eta 
	 * @param data arrivo in struttura
	 * @param ora arrivo in struttura
	 * @param urgenza ammessi solo valori "verde", "giallo" e "rosso"
	 * @param posizione sezione ospedaliera in cui si trova il paziente
	 * @param condizione è il motivo per cui sono nella struttura 
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public void SelezionaPaziente(String codice, int count, String cognome, String nome, String sesso, String genere, int eta, String data, String ora, String urgenza, String posizione, String condizione) {
		this.codice = codice;
		this.count = count;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.genere = genere;
		this.eta = eta;
		this.data = data;
		this.ora = ora;
		this.urgenza = urgenza;
		this.posizione = posizione;
		this.condizione = condizione;
		selezionato = true;
		modelloView.setStringaAnagraficaPaziente("Paziente: " + nome + " " + cognome + " (Codice: " + codice + ")");
		modelloView.setStringaArrivoPaziente("    Data arrivo in struttura: " + data + ", alle: " + ora + " --- ID Ingresso: " + count);
		modelloView.setStringaCondizionePaziente("    Motivo: " + condizione);
	}
	
	/**
	 * Aggiorna il valore delle stringhe nella sezione "Visite / Interventi"
	 * @param codice dovrebbe essere il codice fiscale
	 * @param count del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param cognome
	 * @param nome
	 * @param sesso ammessi valori "M" o "F"
	 * @param genere come il paziente si identifica 
	 * @param eta 
	 * @param data prenotazione
	 * @param ora prenotazione
	 * @param urgenza ammessi solo valori "verde", "giallo" e "rosso"
	 * @param posizione sezione ospedaliera in cui si trova il paziente
	 * @param condizione è il motivo per cui sono nella struttura 
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public void SelezionaPazienteVisita(String codice, int count, String cognome, String nome, String sesso, String genere, int eta, String data, String ora, String urgenza, String posizione, String condizione) {
		this.codice = codice;
		this.count = count;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.genere = genere;
		this.eta = eta;
		this.data = data;
		this.ora = ora;
		this.urgenza = urgenza;
		this.posizione = posizione;
		this.condizione = condizione;
		selezionato = true;
		modelloView.setStringaAnagraficaPaziente("Paziente: " + nome + " " + cognome + " (Codice: " + codice + ")");
		modelloView.setStringaArrivoPaziente("    Data prenotazione: " + data + ", alle: " + ora + " --- ID Ingresso: " + count);
		modelloView.setStringaCondizionePaziente("    Motivo: " + condizione);
	}
	
	/**
	 * Aggiorna il valore delle stringhe nella sezione "Dimessi"
	 * @param codice dovrebbe essere il codice fiscale
	 * @param count del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param cognome
	 * @param nome
	 * @param sesso ammessi valori "M" o "F"
	 * @param genere come il paziente si identifica 
	 * @param eta 
	 * @param data dimissione
	 * @param ora dimissione
	 * @param urgenza ammessi solo valori "verde", "giallo" e "rosso"
	 * @param posizione sezione ospedaliera in cui si trova il paziente
	 * @param condizione è il motivo per cui sono nella struttura 
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public void SelezionaPazienteDimesso(String codice, int count, String cognome, String nome, String sesso, String genere, int eta, String data, String ora, String urgenza, String posizione, String condizione) {
		this.codice = codice;
		this.count = count;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.genere = genere;
		this.eta = eta;
		this.data = data;
		this.ora = ora;
		this.urgenza = urgenza;
		this.posizione = posizione;
		this.condizione = condizione;
		selezionato = true;
		modelloView.setStringaAnagraficaPaziente("Paziente: " + nome + " " + cognome + " (Codice: " + codice + ")");
		modelloView.setStringaArrivoPaziente("    Data dimissione: " + data + ", alle: " + ora + " --- ID Ingresso: " + count);
		modelloView.setStringaCondizionePaziente("    Motivo: " + condizione);
	}
	
	/**
	 * Aggiorna le stringhe a default quando nessuno è selezionato
	 */
	public void deselezionaPaziente() {
		selezionato = false;
		modelloView.setStringaAnagraficaPaziente("Selezionare un paziente");
		modelloView.setStringaArrivoPaziente("    Data arrivo in struttura: ...");
		modelloView.setStringaCondizionePaziente("    Motivo: ...");
	}
	
	/**
	 * Ritorna il paziente selezionato il tebella
	 */
	public boolean qualcunoSelezionato() {
		return selezionato;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getData() {
		return data;
	}
	
	public String getOra() {
		return ora;
	}
	
	public String getUrgenza() {
		return urgenza;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public int getCount() {
		return count;
	}

	public String getSesso() {
		return sesso;
	}

	public String getGenere() {
		return genere;
	}

	public int getEta() {
		return eta;
	}

	public String getPosizione() {
		return posizione;
	}

	public String getCondizione() {
		return condizione;
	}

	public Boolean getSelezionato() {
		return selezionato;
	}
}
