/*
 * This file is generated by jOOQ.
 */
package med_db.jooq.generated.tables.records;


import java.time.LocalDate;
import java.time.LocalTime;

import med_db.jooq.generated.tables.Rilevazione;

import org.jooq.Field;
import org.jooq.Record12;
import org.jooq.Record3;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RilevazioneRecord extends UpdatableRecordImpl<RilevazioneRecord> implements Record12<Integer, String, Integer, String, Double, Integer, Integer, Integer, LocalDate, LocalTime, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>RILEVAZIONE.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>RILEVAZIONE.CODICE_DEGENTE</code>.
     */
    public void setCodiceDegente(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.CODICE_DEGENTE</code>.
     */
    public String getCodiceDegente() {
        return (String) get(1);
    }

    /**
     * Setter for <code>RILEVAZIONE.COUNT_DEGENTE</code>.
     */
    public void setCountDegente(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.COUNT_DEGENTE</code>.
     */
    public Integer getCountDegente() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>RILEVAZIONE.CODICE_INFERMIERE</code>.
     */
    public void setCodiceInfermiere(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.CODICE_INFERMIERE</code>.
     */
    public String getCodiceInfermiere() {
        return (String) get(3);
    }

    /**
     * Setter for <code>RILEVAZIONE.TEMPERATURA</code>.
     */
    public void setTemperatura(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.TEMPERATURA</code>.
     */
    public Double getTemperatura() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>RILEVAZIONE.PRESSIONE_MAX</code>.
     */
    public void setPressioneMax(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.PRESSIONE_MAX</code>.
     */
    public Integer getPressioneMax() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>RILEVAZIONE.PRESSIONE_MIN</code>.
     */
    public void setPressioneMin(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.PRESSIONE_MIN</code>.
     */
    public Integer getPressioneMin() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>RILEVAZIONE.GLICEMIA</code>.
     */
    public void setGlicemia(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.GLICEMIA</code>.
     */
    public Integer getGlicemia() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>RILEVAZIONE.DATA</code>.
     */
    public void setData(LocalDate value) {
        set(8, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.DATA</code>.
     */
    public LocalDate getData() {
        return (LocalDate) get(8);
    }

    /**
     * Setter for <code>RILEVAZIONE.ORA</code>.
     */
    public void setOra(LocalTime value) {
        set(9, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.ORA</code>.
     */
    public LocalTime getOra() {
        return (LocalTime) get(9);
    }

    /**
     * Setter for <code>RILEVAZIONE.FREQ_CARD</code>.
     */
    public void setFreqCard(Integer value) {
        set(10, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.FREQ_CARD</code>.
     */
    public Integer getFreqCard() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>RILEVAZIONE.DOLORE</code>.
     */
    public void setDolore(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>RILEVAZIONE.DOLORE</code>.
     */
    public Integer getDolore() {
        return (Integer) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<Integer, String, Integer> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<Integer, String, Integer, String, Double, Integer, Integer, Integer, LocalDate, LocalTime, Integer, Integer> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<Integer, String, Integer, String, Double, Integer, Integer, Integer, LocalDate, LocalTime, Integer, Integer> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Rilevazione.RILEVAZIONE.ID;
    }

    @Override
    public Field<String> field2() {
        return Rilevazione.RILEVAZIONE.CODICE_DEGENTE;
    }

    @Override
    public Field<Integer> field3() {
        return Rilevazione.RILEVAZIONE.COUNT_DEGENTE;
    }

    @Override
    public Field<String> field4() {
        return Rilevazione.RILEVAZIONE.CODICE_INFERMIERE;
    }

    @Override
    public Field<Double> field5() {
        return Rilevazione.RILEVAZIONE.TEMPERATURA;
    }

    @Override
    public Field<Integer> field6() {
        return Rilevazione.RILEVAZIONE.PRESSIONE_MAX;
    }

    @Override
    public Field<Integer> field7() {
        return Rilevazione.RILEVAZIONE.PRESSIONE_MIN;
    }

    @Override
    public Field<Integer> field8() {
        return Rilevazione.RILEVAZIONE.GLICEMIA;
    }

    @Override
    public Field<LocalDate> field9() {
        return Rilevazione.RILEVAZIONE.DATA;
    }

    @Override
    public Field<LocalTime> field10() {
        return Rilevazione.RILEVAZIONE.ORA;
    }

    @Override
    public Field<Integer> field11() {
        return Rilevazione.RILEVAZIONE.FREQ_CARD;
    }

    @Override
    public Field<Integer> field12() {
        return Rilevazione.RILEVAZIONE.DOLORE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getCodiceDegente();
    }

    @Override
    public Integer component3() {
        return getCountDegente();
    }

    @Override
    public String component4() {
        return getCodiceInfermiere();
    }

    @Override
    public Double component5() {
        return getTemperatura();
    }

    @Override
    public Integer component6() {
        return getPressioneMax();
    }

    @Override
    public Integer component7() {
        return getPressioneMin();
    }

    @Override
    public Integer component8() {
        return getGlicemia();
    }

    @Override
    public LocalDate component9() {
        return getData();
    }

    @Override
    public LocalTime component10() {
        return getOra();
    }

    @Override
    public Integer component11() {
        return getFreqCard();
    }

    @Override
    public Integer component12() {
        return getDolore();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getCodiceDegente();
    }

    @Override
    public Integer value3() {
        return getCountDegente();
    }

    @Override
    public String value4() {
        return getCodiceInfermiere();
    }

    @Override
    public Double value5() {
        return getTemperatura();
    }

    @Override
    public Integer value6() {
        return getPressioneMax();
    }

    @Override
    public Integer value7() {
        return getPressioneMin();
    }

    @Override
    public Integer value8() {
        return getGlicemia();
    }

    @Override
    public LocalDate value9() {
        return getData();
    }

    @Override
    public LocalTime value10() {
        return getOra();
    }

    @Override
    public Integer value11() {
        return getFreqCard();
    }

    @Override
    public Integer value12() {
        return getDolore();
    }

    @Override
    public RilevazioneRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public RilevazioneRecord value2(String value) {
        setCodiceDegente(value);
        return this;
    }

    @Override
    public RilevazioneRecord value3(Integer value) {
        setCountDegente(value);
        return this;
    }

    @Override
    public RilevazioneRecord value4(String value) {
        setCodiceInfermiere(value);
        return this;
    }

    @Override
    public RilevazioneRecord value5(Double value) {
        setTemperatura(value);
        return this;
    }

    @Override
    public RilevazioneRecord value6(Integer value) {
        setPressioneMax(value);
        return this;
    }

    @Override
    public RilevazioneRecord value7(Integer value) {
        setPressioneMin(value);
        return this;
    }

    @Override
    public RilevazioneRecord value8(Integer value) {
        setGlicemia(value);
        return this;
    }

    @Override
    public RilevazioneRecord value9(LocalDate value) {
        setData(value);
        return this;
    }

    @Override
    public RilevazioneRecord value10(LocalTime value) {
        setOra(value);
        return this;
    }

    @Override
    public RilevazioneRecord value11(Integer value) {
        setFreqCard(value);
        return this;
    }

    @Override
    public RilevazioneRecord value12(Integer value) {
        setDolore(value);
        return this;
    }

    @Override
    public RilevazioneRecord values(Integer value1, String value2, Integer value3, String value4, Double value5, Integer value6, Integer value7, Integer value8, LocalDate value9, LocalTime value10, Integer value11, Integer value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RilevazioneRecord
     */
    public RilevazioneRecord() {
        super(Rilevazione.RILEVAZIONE);
    }

    /**
     * Create a detached, initialised RilevazioneRecord
     */
    public RilevazioneRecord(Integer id, String codiceDegente, Integer countDegente, String codiceInfermiere, Double temperatura, Integer pressioneMax, Integer pressioneMin, Integer glicemia, LocalDate data, LocalTime ora, Integer freqCard, Integer dolore) {
        super(Rilevazione.RILEVAZIONE);

        setId(id);
        setCodiceDegente(codiceDegente);
        setCountDegente(countDegente);
        setCodiceInfermiere(codiceInfermiere);
        setTemperatura(temperatura);
        setPressioneMax(pressioneMax);
        setPressioneMin(pressioneMin);
        setGlicemia(glicemia);
        setData(data);
        setOra(ora);
        setFreqCard(freqCard);
        setDolore(dolore);
        resetChangedOnNotNull();
    }
}
