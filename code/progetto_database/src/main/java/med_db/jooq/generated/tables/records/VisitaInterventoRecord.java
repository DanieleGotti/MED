/*
 * This file is generated by jOOQ.
 */
package med_db.jooq.generated.tables.records;


import java.time.LocalDate;
import java.time.LocalTime;

import med_db.jooq.generated.tables.VisitaIntervento;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VisitaInterventoRecord extends UpdatableRecordImpl<VisitaInterventoRecord> implements Record8<Integer, String, String, Integer, String, String, LocalDate, LocalTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>VISITA_INTERVENTO.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>VISITA_INTERVENTO.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>VISITA_INTERVENTO.TIPO</code>.
     */
    public void setTipo(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>VISITA_INTERVENTO.TIPO</code>.
     */
    public String getTipo() {
        return (String) get(1);
    }

    /**
     * Setter for <code>VISITA_INTERVENTO.CODICE_DEGENTE</code>.
     */
    public void setCodiceDegente(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>VISITA_INTERVENTO.CODICE_DEGENTE</code>.
     */
    public String getCodiceDegente() {
        return (String) get(2);
    }

    /**
     * Setter for <code>VISITA_INTERVENTO.COUNT_DEGENTE</code>.
     */
    public void setCountDegente(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>VISITA_INTERVENTO.COUNT_DEGENTE</code>.
     */
    public Integer getCountDegente() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>VISITA_INTERVENTO.CODICE_MEDICO</code>.
     */
    public void setCodiceMedico(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>VISITA_INTERVENTO.CODICE_MEDICO</code>.
     */
    public String getCodiceMedico() {
        return (String) get(4);
    }

    /**
     * Setter for <code>VISITA_INTERVENTO.MOTIVO</code>.
     */
    public void setMotivo(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>VISITA_INTERVENTO.MOTIVO</code>.
     */
    public String getMotivo() {
        return (String) get(5);
    }

    /**
     * Setter for <code>VISITA_INTERVENTO.DATA_PRENOTAZIONE</code>.
     */
    public void setDataPrenotazione(LocalDate value) {
        set(6, value);
    }

    /**
     * Getter for <code>VISITA_INTERVENTO.DATA_PRENOTAZIONE</code>.
     */
    public LocalDate getDataPrenotazione() {
        return (LocalDate) get(6);
    }

    /**
     * Setter for <code>VISITA_INTERVENTO.ORA_PRENOTAZIONE</code>.
     */
    public void setOraPrenotazione(LocalTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>VISITA_INTERVENTO.ORA_PRENOTAZIONE</code>.
     */
    public LocalTime getOraPrenotazione() {
        return (LocalTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, String, String, Integer, String, String, LocalDate, LocalTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Integer, String, String, Integer, String, String, LocalDate, LocalTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return VisitaIntervento.VISITA_INTERVENTO.ID;
    }

    @Override
    public Field<String> field2() {
        return VisitaIntervento.VISITA_INTERVENTO.TIPO;
    }

    @Override
    public Field<String> field3() {
        return VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE;
    }

    @Override
    public Field<Integer> field4() {
        return VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE;
    }

    @Override
    public Field<String> field5() {
        return VisitaIntervento.VISITA_INTERVENTO.CODICE_MEDICO;
    }

    @Override
    public Field<String> field6() {
        return VisitaIntervento.VISITA_INTERVENTO.MOTIVO;
    }

    @Override
    public Field<LocalDate> field7() {
        return VisitaIntervento.VISITA_INTERVENTO.DATA_PRENOTAZIONE;
    }

    @Override
    public Field<LocalTime> field8() {
        return VisitaIntervento.VISITA_INTERVENTO.ORA_PRENOTAZIONE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTipo();
    }

    @Override
    public String component3() {
        return getCodiceDegente();
    }

    @Override
    public Integer component4() {
        return getCountDegente();
    }

    @Override
    public String component5() {
        return getCodiceMedico();
    }

    @Override
    public String component6() {
        return getMotivo();
    }

    @Override
    public LocalDate component7() {
        return getDataPrenotazione();
    }

    @Override
    public LocalTime component8() {
        return getOraPrenotazione();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTipo();
    }

    @Override
    public String value3() {
        return getCodiceDegente();
    }

    @Override
    public Integer value4() {
        return getCountDegente();
    }

    @Override
    public String value5() {
        return getCodiceMedico();
    }

    @Override
    public String value6() {
        return getMotivo();
    }

    @Override
    public LocalDate value7() {
        return getDataPrenotazione();
    }

    @Override
    public LocalTime value8() {
        return getOraPrenotazione();
    }

    @Override
    public VisitaInterventoRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public VisitaInterventoRecord value2(String value) {
        setTipo(value);
        return this;
    }

    @Override
    public VisitaInterventoRecord value3(String value) {
        setCodiceDegente(value);
        return this;
    }

    @Override
    public VisitaInterventoRecord value4(Integer value) {
        setCountDegente(value);
        return this;
    }

    @Override
    public VisitaInterventoRecord value5(String value) {
        setCodiceMedico(value);
        return this;
    }

    @Override
    public VisitaInterventoRecord value6(String value) {
        setMotivo(value);
        return this;
    }

    @Override
    public VisitaInterventoRecord value7(LocalDate value) {
        setDataPrenotazione(value);
        return this;
    }

    @Override
    public VisitaInterventoRecord value8(LocalTime value) {
        setOraPrenotazione(value);
        return this;
    }

    @Override
    public VisitaInterventoRecord values(Integer value1, String value2, String value3, Integer value4, String value5, String value6, LocalDate value7, LocalTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VisitaInterventoRecord
     */
    public VisitaInterventoRecord() {
        super(VisitaIntervento.VISITA_INTERVENTO);
    }

    /**
     * Create a detached, initialised VisitaInterventoRecord
     */
    public VisitaInterventoRecord(Integer id, String tipo, String codiceDegente, Integer countDegente, String codiceMedico, String motivo, LocalDate dataPrenotazione, LocalTime oraPrenotazione) {
        super(VisitaIntervento.VISITA_INTERVENTO);

        setId(id);
        setTipo(tipo);
        setCodiceDegente(codiceDegente);
        setCountDegente(countDegente);
        setCodiceMedico(codiceMedico);
        setMotivo(motivo);
        setDataPrenotazione(dataPrenotazione);
        setOraPrenotazione(oraPrenotazione);
        resetChangedOnNotNull();
    }
}
