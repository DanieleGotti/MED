/*
 * This file is generated by jOOQ.
 */
package med_db.jooq.generated.tables.records;


import java.time.LocalDate;

import med_db.jooq.generated.tables.Assegnazioneletto;

import org.jooq.Field;
import org.jooq.Record5;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AssegnazionelettoRecord extends UpdatableRecordImpl<AssegnazionelettoRecord> implements Record6<String, Integer, String, String, Integer, LocalDate> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ASSEGNAZIONELETTO.CODICE_DEGENTE</code>.
     */
    public void setCodiceDegente(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>ASSEGNAZIONELETTO.CODICE_DEGENTE</code>.
     */
    public String getCodiceDegente() {
        return (String) get(0);
    }

    /**
     * Setter for <code>ASSEGNAZIONELETTO.COUNT_DEGENTE</code>.
     */
    public void setCountDegente(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>ASSEGNAZIONELETTO.COUNT_DEGENTE</code>.
     */
    public Integer getCountDegente() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>ASSEGNAZIONELETTO.CODICE_REPARTO</code>.
     */
    public void setCodiceReparto(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ASSEGNAZIONELETTO.CODICE_REPARTO</code>.
     */
    public String getCodiceReparto() {
        return (String) get(2);
    }

    /**
     * Setter for <code>ASSEGNAZIONELETTO.NOME_MODULO</code>.
     */
    public void setNomeModulo(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>ASSEGNAZIONELETTO.NOME_MODULO</code>.
     */
    public String getNomeModulo() {
        return (String) get(3);
    }

    /**
     * Setter for <code>ASSEGNAZIONELETTO.NUMERO_LETTO</code>.
     */
    public void setNumeroLetto(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>ASSEGNAZIONELETTO.NUMERO_LETTO</code>.
     */
    public Integer getNumeroLetto() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>ASSEGNAZIONELETTO.DATA_ASSEGNAZIONE</code>.
     */
    public void setDataAssegnazione(LocalDate value) {
        set(5, value);
    }

    /**
     * Getter for <code>ASSEGNAZIONELETTO.DATA_ASSEGNAZIONE</code>.
     */
    public LocalDate getDataAssegnazione() {
        return (LocalDate) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record5<String, String, Integer, String, Integer> key() {
        return (Record5) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, Integer, String, String, Integer, LocalDate> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<String, Integer, String, String, Integer, LocalDate> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE;
    }

    @Override
    public Field<Integer> field2() {
        return Assegnazioneletto.ASSEGNAZIONELETTO.COUNT_DEGENTE;
    }

    @Override
    public Field<String> field3() {
        return Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO;
    }

    @Override
    public Field<String> field4() {
        return Assegnazioneletto.ASSEGNAZIONELETTO.NOME_MODULO;
    }

    @Override
    public Field<Integer> field5() {
        return Assegnazioneletto.ASSEGNAZIONELETTO.NUMERO_LETTO;
    }

    @Override
    public Field<LocalDate> field6() {
        return Assegnazioneletto.ASSEGNAZIONELETTO.DATA_ASSEGNAZIONE;
    }

    @Override
    public String component1() {
        return getCodiceDegente();
    }

    @Override
    public Integer component2() {
        return getCountDegente();
    }

    @Override
    public String component3() {
        return getCodiceReparto();
    }

    @Override
    public String component4() {
        return getNomeModulo();
    }

    @Override
    public Integer component5() {
        return getNumeroLetto();
    }

    @Override
    public LocalDate component6() {
        return getDataAssegnazione();
    }

    @Override
    public String value1() {
        return getCodiceDegente();
    }

    @Override
    public Integer value2() {
        return getCountDegente();
    }

    @Override
    public String value3() {
        return getCodiceReparto();
    }

    @Override
    public String value4() {
        return getNomeModulo();
    }

    @Override
    public Integer value5() {
        return getNumeroLetto();
    }

    @Override
    public LocalDate value6() {
        return getDataAssegnazione();
    }

    @Override
    public AssegnazionelettoRecord value1(String value) {
        setCodiceDegente(value);
        return this;
    }

    @Override
    public AssegnazionelettoRecord value2(Integer value) {
        setCountDegente(value);
        return this;
    }

    @Override
    public AssegnazionelettoRecord value3(String value) {
        setCodiceReparto(value);
        return this;
    }

    @Override
    public AssegnazionelettoRecord value4(String value) {
        setNomeModulo(value);
        return this;
    }

    @Override
    public AssegnazionelettoRecord value5(Integer value) {
        setNumeroLetto(value);
        return this;
    }

    @Override
    public AssegnazionelettoRecord value6(LocalDate value) {
        setDataAssegnazione(value);
        return this;
    }

    @Override
    public AssegnazionelettoRecord values(String value1, Integer value2, String value3, String value4, Integer value5, LocalDate value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AssegnazionelettoRecord
     */
    public AssegnazionelettoRecord() {
        super(Assegnazioneletto.ASSEGNAZIONELETTO);
    }

    /**
     * Create a detached, initialised AssegnazionelettoRecord
     */
    public AssegnazionelettoRecord(String codiceDegente, Integer countDegente, String codiceReparto, String nomeModulo, Integer numeroLetto, LocalDate dataAssegnazione) {
        super(Assegnazioneletto.ASSEGNAZIONELETTO);

        setCodiceDegente(codiceDegente);
        setCountDegente(countDegente);
        setCodiceReparto(codiceReparto);
        setNomeModulo(nomeModulo);
        setNumeroLetto(numeroLetto);
        setDataAssegnazione(dataAssegnazione);
        resetChangedOnNotNull();
    }
}