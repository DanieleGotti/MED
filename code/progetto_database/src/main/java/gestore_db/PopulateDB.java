package gestore_db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilizzata per popolare con più rapidità il database a seguito di modifiche alla sua struttura
 */
public class PopulateDB {

	public static void main(String[] args) throws Exception{
		//file unico per la costruzione del DB, eseguire prima:
		//CreateDB.getIstanza().creaDB();
		//CreateDB.getIstanza().creaTable();
		
		inserisciDati();
		
	}
	
	/**
	 * Funzione per l'inserimento di tutti i dati iniziali di default
	 */
	public static void inserisciDati() {
		
		//svuotare prima il file system 
		File cartellaPersonale = new File("./../../personale");
		if (cartellaPersonale.exists()) {
			eliminaCartelle(cartellaPersonale);
		}
				
		File cartellaPazienti = new File("./../../pazienti");
		if (cartellaPazienti.exists()) {
			eliminaCartelle(cartellaPazienti);
		}
		 		
		//inserimento personale default
		InserimentoJooq.getIstanza().personale("m","Marco","Rossi","M","m","Cardiologo");
		InserimentoJooq.getIstanza().personale("m1","Giuseppe","Conti","M","m1","Ortopedico");
		InserimentoJooq.getIstanza().personale("i","Sara","Rota","I","i","Capo sala");
		InserimentoJooq.getIstanza().personale("o","Matteo","Bianchi","S","o","Informatico");
				
		//inserimento immagini personale
		inserisciFotoPersonaleDefault("m","../progetto_gui/src/main/resources/pazienti_default/marco_rossi.png");
		inserisciFotoPersonaleDefault("m1","../progetto_gui/src/main/resources/pazienti_default/giuseppe_conti.png");
		inserisciFotoPersonaleDefault("i","../progetto_gui/src/main/resources/pazienti_default/sara_rota.png");
		inserisciFotoPersonaleDefault("o","../progetto_gui/src/main/resources/pazienti_default/matteo_bianchi.png");
				
		//inserimento reparti e moduli di cardiologia
		InserimentoJooq.getIstanza().reparto("Re1","Cardiologia");
		InserimentoJooq.getIstanza().modulo("Re1","ModuloA");
		InserimentoJooq.getIstanza().modulo("Re1","ModuloB");
		InserimentoJooq.getIstanza().modulo("Re1","ModuloC");
				
		//inserimento dei letti nei moduli di cardiologia
		for(int i=1; i<=15; i++ ) {
			InserimentoJooq.getIstanza().letto("Re1","ModuloA",i);
			InserimentoJooq.getIstanza().letto("Re1","ModuloB",i);
			InserimentoJooq.getIstanza().letto("Re1","ModuloC",i);
		}
				
		//inserimento reparti e moduli di ortopedia
		InserimentoJooq.getIstanza().reparto("Re2","Ortopedia");
		InserimentoJooq.getIstanza().modulo("Re2","ModuloA");
		InserimentoJooq.getIstanza().modulo("Re2","ModuloB");
		InserimentoJooq.getIstanza().modulo("Re2","ModuloC");
				
		//inserimento dei letti nei moduli di ortopedia
		for(int i=1; i<=15; i++ ) {
			InserimentoJooq.getIstanza().letto("Re2","ModuloA",i);
			InserimentoJooq.getIstanza().letto("Re2","ModuloB",i);
			InserimentoJooq.getIstanza().letto("Re2","ModuloC",i);
		}
				
		//inserimento reparti e moduli di psichiatria
		InserimentoJooq.getIstanza().reparto("Re3","Psichiatria");
		InserimentoJooq.getIstanza().modulo("Re3","ModuloA");
		InserimentoJooq.getIstanza().modulo("Re3","ModuloB");
		InserimentoJooq.getIstanza().modulo("Re3","ModuloC");
			
		//inserimento dei letti nei moduli di psichiatria
		for(int i=1; i<=15; i++ ) {
			InserimentoJooq.getIstanza().letto("Re3","ModuloA",i);
			InserimentoJooq.getIstanza().letto("Re3","ModuloB",i);
			InserimentoJooq.getIstanza().letto("Re3","ModuloC",i);
		}	
				
		//inserimento reparti e moduli di medicina interna
		InserimentoJooq.getIstanza().reparto("Re4","Medicina Interna");
		InserimentoJooq.getIstanza().modulo("Re4","ModuloA");
		InserimentoJooq.getIstanza().modulo("Re4","ModuloB");
		InserimentoJooq.getIstanza().modulo("Re4","ModuloC");
				
		//inserimento dei letti nei moduli di medicina interna
		for(int i=1; i<=15; i++ ) {
			InserimentoJooq.getIstanza().letto("Re4","ModuloA",i);
			InserimentoJooq.getIstanza().letto("Re4","ModuloB",i);
			InserimentoJooq.getIstanza().letto("Re4","ModuloC",i);
		}	
				
		//inserimento degenti
		InserimentoJooq.getIstanza().degente("1",1,"Anonimo","Anonimo","M",null,0,LocalDate.now(),LocalTime.now(),"rosso","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D1",1,"Gabriele","Mazzoleni","M","Maschio",20,LocalDate.now().minusDays(2),LocalTime.parse("23:34",DateTimeFormatter.ofPattern("HH:mm")),"giallo","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D1",2,"Gabriele","Mazzoleni","M","Maschio",20,LocalDate.now(),LocalTime.parse("11:00",DateTimeFormatter.ofPattern("HH:mm")),"giallo","Visita Intervento");
		InserimentoJooq.getIstanza().degente("D1",3,"Gabriele","Mazzoleni","M","Maschio",20,LocalDate.now(),LocalTime.parse("17:30",DateTimeFormatter.ofPattern("HH:mm")),"giallo","Visita Intervento");
		InserimentoJooq.getIstanza().degente("D2",1,"Jacopo","Bellosi","M","Maschio",32,LocalDate.now().minusDays(1),LocalTime.parse("19:23",DateTimeFormatter.ofPattern("HH:mm")),"rosso","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D3",1,"Lara","Longhi","F","Femmina",30,LocalDate.now().minusDays(2), LocalTime.parse("18:55",DateTimeFormatter.ofPattern("HH:mm")),"verde","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D4",1,"Alessia","Lazzari","F","Femmina",18,LocalDate.now().minusDays(1),LocalTime.parse("22:43",DateTimeFormatter.ofPattern("HH:mm")),"giallo","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D4",2,"Alessia","Lazzari","F","Femmina",18,LocalDate.now(),LocalTime.parse("09:00",DateTimeFormatter.ofPattern("HH:mm")),"giallo","Visita Intervento");
		InserimentoJooq.getIstanza().degente("D5",1,"Giorgia","Mezzera","F","Femmina",40,LocalDate.now().minusDays(1),LocalTime.parse("20:36",DateTimeFormatter.ofPattern("HH:mm")),"rosso","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D5",2,"Giorgia","Mezzera","F","Femmina",40,LocalDate.now().minusDays(1),LocalTime.parse("21:00",DateTimeFormatter.ofPattern("HH:mm")),"rosso","Visita Intervento");
		InserimentoJooq.getIstanza().degente("D6",1,"Federica","Gervasoni","F","Femmina",25,LocalDate.now().minusDays(1),LocalTime.parse("17:13",DateTimeFormatter.ofPattern("HH:mm")),"giallo","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D7",1,"Daniele","Gotti","M","Maschio",19,LocalDate.now(),LocalTime.parse("10:38",DateTimeFormatter.ofPattern("HH:mm")),"verde","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D8",1,"Filippo","Bolis","M","Maschio",23,LocalDate.now(),LocalTime.parse("09:21",DateTimeFormatter.ofPattern("HH:mm")),"giallo","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D9",1,"Gabriele","Masinari","M","Maschio",50,LocalDate.now(),LocalTime.parse("12:39",DateTimeFormatter.ofPattern("HH:mm")),"verde","in Pronto Soccorso");
		InserimentoJooq.getIstanza().degente("D10",1,"Gabriele","Cederna","M","Maschio",89,LocalDate.now().minusDays(2),LocalTime.parse("11:01",DateTimeFormatter.ofPattern("HH:mm")),"rosso","Dimesso");
			
		//inserimento immagini pazienti
		inserisciFotoPazientiDefault("1",1,LocalDate.now(),"../progetto_gui/src/main/resources/fototessera_default.png");
		inserisciFotoPazientiDefault("D1",1,LocalDate.now().minusDays(2),"../progetto_gui/src/main/resources/pazienti_default/gabriele_mazzoleni.png");
		inserisciFotoPazientiDefault("D1",2,LocalDate.now(),"../progetto_gui/src/main/resources/pazienti_default/gabriele_mazzoleni.png");
		inserisciFotoPazientiDefault("D1",3,LocalDate.now(),"../progetto_gui/src/main/resources/pazienti_default/gabriele_mazzoleni.png");
		inserisciFotoPazientiDefault("D2",1,LocalDate.now().minusDays(1),"../progetto_gui/src/main/resources/pazienti_default/jacopo_bellosi.png");
		inserisciFotoPazientiDefault("D3",1,LocalDate.now().minusDays(2),"../progetto_gui/src/main/resources/pazienti_default/lara_longhi.png");
		inserisciFotoPazientiDefault("D4",1,LocalDate.now().minusDays(1),"../progetto_gui/src/main/resources/pazienti_default/alessia_lazzari.png");
		inserisciFotoPazientiDefault("D4",2,LocalDate.now(),"../progetto_gui/src/main/resources/pazienti_default/alessia_lazzari.png");
		inserisciFotoPazientiDefault("D5",1,LocalDate.now().minusDays(1),"../progetto_gui/src/main/resources/pazienti_default/giorgia_mezzera.png");
		inserisciFotoPazientiDefault("D5",2,LocalDate.now().minusDays(1),"../progetto_gui/src/main/resources/pazienti_default/giorgia_mezzera.png");
		inserisciFotoPazientiDefault("D6",1,LocalDate.now().minusDays(1),"../progetto_gui/src/main/resources/pazienti_default/federica_gervasoni.png");
		inserisciFotoPazientiDefault("D7",1,LocalDate.now(),"../progetto_gui/src/main/resources/pazienti_default/daniele_gotti.png");
		inserisciFotoPazientiDefault("D8",1,LocalDate.now(),"../progetto_gui/src/main/resources/pazienti_default/filippo_bolis.png");
		inserisciFotoPazientiDefault("D9",1,LocalDate.now(),"../progetto_gui/src/main/resources/pazienti_default/gabriele_masinari.png");
		inserisciFotoPazientiDefault("D10",1,LocalDate.now().minusDays(2),"../progetto_gui/src/main/resources/pazienti_default/gabriele_cederna.png");		
			
		//inserimento diarie mediche
		InserimentoJooq.getIstanza().diariaMed(1,"D1",1,"m1","Diabetico, iperteso, cardiopatico","Frattura ala iliaca destra, diastasi della sinfisi pubica e della frattura cotile","Ortopedia","Allettato, mobilizzazione in asse, terapia anti-trombo-embolica e anitidolorifica\n\nFARMACI:\nClexane 4000, 1 fl, ore 20:00\nPerfalgan, 1g, ore 08:00 - 16:00 - 24:00\nIbuprofene, 400mg, ore 06:00 - 12:00 - 18:00",LocalDate.now().minusDays(1),LocalTime.parse("00:12",DateTimeFormatter.ofPattern("HH:mm")),"Allergico al mezzo di contrasto e cortisone");
		InserimentoJooq.getIstanza().diariaMed(1,"D2",1,"m","Severa allergia alle nocciole", "Attacco allergico acuto dopo consumo di nocciole","Medicina Interna", "Deltacortene, Formistin", LocalDate.now().minusDays(1),LocalTime.parse("19:36",DateTimeFormatter.ofPattern("HH:mm")), "Allergia alle nocciole ed aspirine");
		InserimentoJooq.getIstanza().diariaMed(1,"D3",1,"m","Nessuno","Influenza severa","Medicina Interna","Brufen",LocalDate.now().minusDays(2),LocalTime.parse("19:26",DateTimeFormatter.ofPattern("HH:mm")),"Nessuna allergia");
		InserimentoJooq.getIstanza().diariaMed(1,"D4",1,"m","Disturbo d'ansia generalizzata","Severa ipertensione","Cardiologia","ACE-inibitori e diuretici",LocalDate.now().minusDays(1),LocalTime.parse("22:56",DateTimeFormatter.ofPattern("HH:mm")),"Nessuna allergia");
		InserimentoJooq.getIstanza().diariaMed(1,"D5",1,"m","Ipertensione, dislipidemia e tabagismo","Possibile infarto miocardico","Cardiologia","Intervento di impianto angioplastica coronarica",LocalDate.now().minusDays(1),LocalTime.parse("20:47",DateTimeFormatter.ofPattern("HH:mm")),"Nessuna allergia");
		InserimentoJooq.getIstanza().diariaMed(1,"D6",1,"m","Disturbo della personalità","Attacco di panico","Psichiatria","Ansiolitico",LocalDate.now().minusDays(1),LocalTime.parse("17:41", DateTimeFormatter.ofPattern("HH:mm")),"Intolleranza al lattosio");
		InserimentoJooq.getIstanza().diariaMed(1,"D7",1,"m1","Nessuno","Strappo muscolare","Ortopedia","EE: CPK, fisioterapia e antidolorifici",LocalDate.now(),LocalTime.parse("11:20",DateTimeFormatter.ofPattern("HH:mm")),"Allergia al polline");
		InserimentoJooq.getIstanza().diariaMed(1,"D8",1,"m","Asma","Attacco severo d'asma","Medicina Interna","Broncodilatatore: Ventolin",LocalDate.now(),LocalTime.parse("09:51",DateTimeFormatter.ofPattern("HH:mm")),"Nessuna allergia");
		
		//assegnazione dei letti
		InserimentoJooq.getIstanza().assegnazioneLetto("D1",1,"Re2","ModuloC",1,LocalDate.now().minusDays(1));
		InserimentoJooq.getIstanza().assegnazioneLetto("D2",1,"Re4","ModuloA",1,LocalDate.now().minusDays(1));
		InserimentoJooq.getIstanza().assegnazioneLetto("D3",1,"Re4","ModuloB",1,LocalDate.now().minusDays(2));
		InserimentoJooq.getIstanza().assegnazioneLetto("D4",1,"Re1","ModuloA",1,LocalDate.now().minusDays(1));
		InserimentoJooq.getIstanza().assegnazioneLetto("D5",1,"Re2","ModuloC",2,LocalDate.now().minusDays(1));
		InserimentoJooq.getIstanza().assegnazioneLetto("D6",1,"Re3","ModuloA",1,LocalDate.now().minusDays(1));
		
		//inserimento diarie infermieristiche
		InserimentoJooq.getIstanza().diariaInf(1,"D1",1,"i",LocalDate.now().minusDays(1),LocalTime.parse("08:10",DateTimeFormatter.ofPattern("HH:mm")),"Paziente dolente, si somministra Perfalgan delle ore 08:00 con poco beneficio, avvisato medico di guardia, si anticipa Ibuprofene 400mg delle ore 12:00, da valutare il beneficio ed eventuale terapia al bisogno",true,"Perfalgan"); 
		InserimentoJooq.getIstanza().diariaInf(2,"D1",1,"i",LocalDate.now().minusDays(1),LocalTime.parse("10:14",DateTimeFormatter.ofPattern("HH:mm")),"Sintomatologia dolorosa regredita",false,"Ibuprofene"); 
		InserimentoJooq.getIstanza().diariaInf(1,"D2",1,"i",LocalDate.now().minusDays(1),LocalTime.parse("21:02",DateTimeFormatter.ofPattern("HH:mm")),"Il paziente beneficia dalla somministrazione dei farmaci", false, "Continuare con antiallergenico e corticosteroide");
		InserimentoJooq.getIstanza().diariaInf(1,"D3",1,"i",LocalDate.now().minusDays(1),LocalTime.parse("10:20",DateTimeFormatter.ofPattern("HH:mm")),"Decorso clinico in peggioramento", true, "Aumentare posologia Brufen EV e consultare di nuovo il medico per eventuale inizio terapia antivirale");
		InserimentoJooq.getIstanza().diariaInf(1,"D4",1,"i",LocalDate.now().minusDays(1),LocalTime.parse("23:46",DateTimeFormatter.ofPattern("HH:mm")),"Il paziente beneficia dalla somministrazione dei farmaci", false, "Continuare terapia antiipertensiva");
		InserimentoJooq.getIstanza().diariaInf(2,"D4",1,"i",LocalDate.now(),LocalTime.parse("06:15",DateTimeFormatter.ofPattern("HH:mm")),"Il paziente ha rialzo pressorio improvviso", true, "Aumentare posologia e introdurre betabloccanti");
		InserimentoJooq.getIstanza().diariaInf(1,"D6",1,"i",LocalDate.now().minusDays(1),LocalTime.parse("22:24",DateTimeFormatter.ofPattern("HH:mm")),"Il paziente è stabile ed è in miglioramento", false, "Diminuire posologia ansiolitici");		
			
		//inserimento rilevazioni
		InserimentoJooq.getIstanza().rilevazione(1,"D1",1,"i",36.5,140,80,180,LocalDate.now().minusDays(1),LocalTime.parse("08:00",DateTimeFormatter.ofPattern("HH:mm")),78,4); 
		InserimentoJooq.getIstanza().rilevazione(1,"D2",1,"i",37.6,105,60,97,LocalDate.now().minusDays(1),LocalTime.parse("21:10",DateTimeFormatter.ofPattern("HH:mm")),130,1); 
		InserimentoJooq.getIstanza().rilevazione(1,"D3",1,"i",39,150,95,114,LocalDate.now().minusDays(1),LocalTime.parse("10:20",DateTimeFormatter.ofPattern("HH:mm")),130,6);
		InserimentoJooq.getIstanza().rilevazione(1,"D4",1,"i",37,140,94,120,LocalDate.now(),LocalTime.parse("00:02",DateTimeFormatter.ofPattern("HH:mm")),110,2); 
		InserimentoJooq.getIstanza().rilevazione(2,"D4",1,"i",37.2,170,110,110,LocalDate.now(),LocalTime.parse("06:24",DateTimeFormatter.ofPattern("HH:mm")),94,4);
		InserimentoJooq.getIstanza().rilevazione(1,"D6",1,"i",36.4,145,68,103,LocalDate.now().minusDays(1),LocalTime.parse("22:32",DateTimeFormatter.ofPattern("HH:mm")),140,0);
		
		//inserimento visite e interventi
		InserimentoJooq.getIstanza().visitaIntervento(1,"Visita","D1","m1",2,LocalDate.now(),LocalTime.parse("17:00",DateTimeFormatter.ofPattern("HH:mm")),"Visita anestesiologica pre-operatoria");	
		InserimentoJooq.getIstanza().visitaIntervento(2,"Intervento","D1","m1",3,LocalDate.now(),LocalTime.parse("18:00",DateTimeFormatter.ofPattern("HH:mm")),"Intervento di sintesi frattura del bacino");	
		InserimentoJooq.getIstanza().visitaIntervento(3,"Visita","D4","m",2,LocalDate.now(),LocalTime.parse("09:00",DateTimeFormatter.ofPattern("HH:mm")),"Visita cardiologica urgente");	
		InserimentoJooq.getIstanza().visitaIntervento(4,"Intervento","D5","m",2,LocalDate.now().minusDays(1),LocalTime.parse("21:00",DateTimeFormatter.ofPattern("HH:mm")),"Intervento di angioplastica coronarica");	
		
		//inserimento dimessi
		InserimentoJooq.getIstanza().dimesso(1,"D10","m",1,LocalDate.now().minusDays(2),LocalTime.parse("11:16",DateTimeFormatter.ofPattern("HH:mm")),"Decesso per embolia polmonare");
				
	}
	
	/**
	 * La funzione elimina sottocartelle e file contenuti in una cartella
	 * @param directory percoso con la cartella da eliminare
	 */
	private static void eliminaCartelle(File directory) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
	        if (files != null) {
	        	for (File file : files) {
	        		 eliminaCartelle(file);
	            }
	        }
	    }
	    directory.delete();
	 }
	
	/**
	 * La funzione inserisce la foto del dipendente nella sua cartella
	 * @param codice identificativo del personale
	 * @param percorsoFoto delle immagini di default del personale
	 */
	public static void inserisciFotoPersonaleDefault(String codice, String percorsoFoto) {
		File sottoCartella = new File("./../../personale/id_" + codice);
		if (!sottoCartella.exists()) {
			sottoCartella.mkdirs();
		}
		
		Path sourcePath = Paths.get(percorsoFoto);
        Path destinationPath = Paths.get(sottoCartella.toString() + "/Fototessera.png");

	    try {
	    	Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * La funzione inserisce la foto del degente nella sua cartella
	 * @param codice identificativo del degente
	 * @param count del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param data di accesso
	 * @param percorsoFoto delle immagini di default del personale
	 */
	public static void inserisciFotoPazientiDefault(String codice, Integer count, LocalDate data, String percorsoFoto) {
		File sottoCartella = new File("./../../pazienti/id_" + codice + "/dati_anagrafici/count_" + count + "__data__" + data);
        if (!sottoCartella.exists()) {
            sottoCartella.mkdirs();
        }
        
        Path sourcePath = Paths.get(percorsoFoto);
        Path destinationPath = Paths.get(sottoCartella.toString() + "/Fototessera.png");
        
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
