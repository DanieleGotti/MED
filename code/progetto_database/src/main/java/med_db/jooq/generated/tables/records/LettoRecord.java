/*
 * This file is generated by jOOQ.
 */
package med_db.jooq.generated.tables.records;


import med_db.jooq.generated.tables.Letto;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LettoRecord extends UpdatableRecordImpl<LettoRecord> implements Record3<String, String, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LETTO.CODICE_REPARTO</code>.
     */
    public void setCodiceReparto(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>LETTO.CODICE_REPARTO</code>.
     */
    public String getCodiceReparto() {
        return (String) get(0);
    }

    /**
     * Setter for <code>LETTO.NOME_MODULO</code>.
     */
    public void setNomeModulo(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>LETTO.NOME_MODULO</code>.
     */
    public String getNomeModulo() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LETTO.NUMERO</code>.
     */
    public void setNumero(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>LETTO.NUMERO</code>.
     */
    public Integer getNumero() {
        return (Integer) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<String, String, Integer> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, String, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Letto.LETTO.CODICE_REPARTO;
    }

    @Override
    public Field<String> field2() {
        return Letto.LETTO.NOME_MODULO;
    }

    @Override
    public Field<Integer> field3() {
        return Letto.LETTO.NUMERO;
    }

    @Override
    public String component1() {
        return getCodiceReparto();
    }

    @Override
    public String component2() {
        return getNomeModulo();
    }

    @Override
    public Integer component3() {
        return getNumero();
    }

    @Override
    public String value1() {
        return getCodiceReparto();
    }

    @Override
    public String value2() {
        return getNomeModulo();
    }

    @Override
    public Integer value3() {
        return getNumero();
    }

    @Override
    public LettoRecord value1(String value) {
        setCodiceReparto(value);
        return this;
    }

    @Override
    public LettoRecord value2(String value) {
        setNomeModulo(value);
        return this;
    }

    @Override
    public LettoRecord value3(Integer value) {
        setNumero(value);
        return this;
    }

    @Override
    public LettoRecord values(String value1, String value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LettoRecord
     */
    public LettoRecord() {
        super(Letto.LETTO);
    }

    /**
     * Create a detached, initialised LettoRecord
     */
    public LettoRecord(String codiceReparto, String nomeModulo, Integer numero) {
        super(Letto.LETTO);

        setCodiceReparto(codiceReparto);
        setNomeModulo(nomeModulo);
        setNumero(numero);
        resetChangedOnNotNull();
    }
}
