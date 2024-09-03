package modelli;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Modello della struttura dei dati dei degenti mostrati in tabella nelle varie sezioni del programma
 */
public class ModelloGestoreTabella {
	
	//per le tabelle dei pazienti
	private List<String> nomi = new ArrayList<>();
	private List<String> codice = new ArrayList<>();
	private List<String> cognomi = new ArrayList<>();
	private List<String> sesso = new ArrayList<>();
	private List<LocalDate> dateArrivo = new ArrayList<>();
	private List<LocalTime> oreArrivo = new ArrayList<>();
	private List<String> urgenza = new ArrayList<>();
	private List<String> reparto = new ArrayList<>();
    private List<String> modulo = new ArrayList<>();
    private List<Integer> numeroLetto = new ArrayList<>();
    private List<Integer> eta = new ArrayList<>();
    private List<Integer> count = new ArrayList<>();
    private List<String> genere = new ArrayList<>();
    private List<String> tipo = new ArrayList<>();
    private List<String> medicoVisita = new ArrayList<>();
    private List<String> motivoVisita = new ArrayList<>();
    private List<LocalDate> dataPrenotazione = new ArrayList<>();
    private List<LocalTime> oraPrenotazione = new ArrayList<>();  
    private List<String> medicoDimissione = new ArrayList<>();
    private List<String> motivoDimissione = new ArrayList<>();
    private List<LocalDate> dataDimissione = new ArrayList<>();
    private List<LocalTime> oraDimissione = new ArrayList<>();
	
    public void setTableNumeroLetto(List<Integer> numeroLetto) {
		this.numeroLetto = numeroLetto;
	}
    
    public void setTableEta(List<Integer> eta) {
		this.eta = eta;
	}
    
    public void setTableCount(List<Integer> count) {
		this.count = count;
	}
    
    public void setTableGenere(List<String> genere) {
		this.genere = genere;
	}
    
    public void setTableReparto(List<String> reparto) {
		this.reparto = reparto;
	}
	
    public void setTableModulo(List<String> modulo) {
		this.modulo = modulo;
	}
    
	public void setTableNomi(List<String> nomi) {
		this.nomi = nomi;
	}
	
	public void setTableCodice(List<String> codice) {
		this.codice = codice;
	}
	
	public void setTableCognomi(List<String> cognomi) {
		this.cognomi = cognomi;
	}
	
	public void setTableSesso(List<String> sesso) {
		this.sesso = sesso;
	}
	
	public void setTableDateArrivo(List<LocalDate> dateArrivo) {
		this.dateArrivo = dateArrivo;
	}
	
	public void setTableOreArrivo(List<LocalTime> oreArrivo) {
		this.oreArrivo = oreArrivo;
	}
	
	public void setTableUrgenza(List<String> urgenza) {
		this.urgenza = urgenza;
	}
	
	public void setTableTipo(List<String> tipo) {
		this.tipo = tipo;
	}

	public void setTableMedicoVisita(List<String> medicoVisita) {
		this.medicoVisita = medicoVisita;
	}

	public void setTableMotivoVisita(List<String> motivoVisita) {
		this.motivoVisita = motivoVisita;
	}

	public void setTableDataPrenotazione(List<LocalDate> dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public void setTableOraPrenotazione(List<LocalTime> oraPrenotazione) {
		this.oraPrenotazione = oraPrenotazione;
	}
	
	public void setTableMedicoDimissione(List<String> medicoDimissione) {
		this.medicoDimissione = medicoDimissione;
	}

	public void setTableMotivoDimissione(List<String> motivoDimissione) {
		this.motivoDimissione = motivoDimissione;
	}

	public void setTableDataDimissione(List<LocalDate> dataDimissione) {
		this.dataDimissione = dataDimissione;
	}

	public void setTableOraDimissione(List<LocalTime> oraDimissione) {
		this.oraDimissione = oraDimissione;
	}

	public List<String> getTableNomi(){
		return nomi;
	}
	
	public List<String> getTableCodice(){
		return codice;
	}
	
	public List<String> getTableCognomi(){
		return cognomi;
	}
	
	public List<String> getTableSesso(){
		return sesso;
	}
	
	public List<LocalDate> getTableDateArrivo(){
		return dateArrivo;
	}
	
	public List<LocalTime> getTableOraArrivo(){
		return oreArrivo;
	}
	
	public List<String> getTableUrgenza(){
		return urgenza;
	}
	
	public List<String> getTableReparto(){
		return reparto;
	}
	
	public List<String> getTableModulo(){
		return modulo;
	}
	
	public List<Integer> getTableNumeroLetto(){
		return numeroLetto;
	}
	
	public List<Integer> getTableCount(){
		return count;
	}
	
	public List<Integer> getTableEta(){
		return eta;
	}
	
	public List<String> getTableGenere(){
		return genere;
	}

	public List<String> getTableTipo() {
		return tipo;
	}

	public List<String> getTableMedicoVisita() {
		return medicoVisita;
	}

	public List<String> getTableMotivoVisita() {
		return motivoVisita;
	}

	public List<LocalDate> getTableDataPrenotazione() {
		return dataPrenotazione;
	}

	public List<LocalTime> getTableOraPrenotazione() {
		return oraPrenotazione;
	}
	

	public List<String> getTableMedicoDimissione() {
		return medicoDimissione;
	}

	public List<String> getTableMotivoDimissione() {
		return motivoDimissione;
	}

	public List<LocalDate> getTableDataDimissione() {
		return dataDimissione;
	}

	public List<LocalTime> getTableOraDimissione() {
		return oraDimissione;
	}
	
}
