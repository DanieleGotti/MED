/*
 * This file is generated by jOOQ.
 */
package med_db.jooq.generated.tables;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import med_db.jooq.generated.DefaultSchema;
import med_db.jooq.generated.Keys;
import med_db.jooq.generated.tables.records.AssegnazionelettoRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Assegnazioneletto extends TableImpl<AssegnazionelettoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ASSEGNAZIONELETTO</code>
     */
    public static final Assegnazioneletto ASSEGNAZIONELETTO = new Assegnazioneletto();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AssegnazionelettoRecord> getRecordType() {
        return AssegnazionelettoRecord.class;
    }

    /**
     * The column <code>ASSEGNAZIONELETTO.CODICE_DEGENTE</code>.
     */
    public final TableField<AssegnazionelettoRecord, String> CODICE_DEGENTE = createField(DSL.name("CODICE_DEGENTE"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>ASSEGNAZIONELETTO.COUNT_DEGENTE</code>.
     */
    public final TableField<AssegnazionelettoRecord, Integer> COUNT_DEGENTE = createField(DSL.name("COUNT_DEGENTE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>ASSEGNAZIONELETTO.CODICE_REPARTO</code>.
     */
    public final TableField<AssegnazionelettoRecord, String> CODICE_REPARTO = createField(DSL.name("CODICE_REPARTO"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>ASSEGNAZIONELETTO.NOME_MODULO</code>.
     */
    public final TableField<AssegnazionelettoRecord, String> NOME_MODULO = createField(DSL.name("NOME_MODULO"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>ASSEGNAZIONELETTO.NUMERO_LETTO</code>.
     */
    public final TableField<AssegnazionelettoRecord, Integer> NUMERO_LETTO = createField(DSL.name("NUMERO_LETTO"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>ASSEGNAZIONELETTO.DATA_ASSEGNAZIONE</code>.
     */
    public final TableField<AssegnazionelettoRecord, LocalDate> DATA_ASSEGNAZIONE = createField(DSL.name("DATA_ASSEGNAZIONE"), SQLDataType.LOCALDATE, this, "");

    private Assegnazioneletto(Name alias, Table<AssegnazionelettoRecord> aliased) {
        this(alias, aliased, null);
    }

    private Assegnazioneletto(Name alias, Table<AssegnazionelettoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>ASSEGNAZIONELETTO</code> table reference
     */
    public Assegnazioneletto(String alias) {
        this(DSL.name(alias), ASSEGNAZIONELETTO);
    }

    /**
     * Create an aliased <code>ASSEGNAZIONELETTO</code> table reference
     */
    public Assegnazioneletto(Name alias) {
        this(alias, ASSEGNAZIONELETTO);
    }

    /**
     * Create a <code>ASSEGNAZIONELETTO</code> table reference
     */
    public Assegnazioneletto() {
        this(DSL.name("ASSEGNAZIONELETTO"), null);
    }

    public <O extends Record> Assegnazioneletto(Table<O> child, ForeignKey<O, AssegnazionelettoRecord> key) {
        super(child, key, ASSEGNAZIONELETTO);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<AssegnazionelettoRecord> getPrimaryKey() {
        return Keys.ASSEGNAZIONELETTO__PK_ASSEGNAZIONELETTO;
    }

    @Override
    public List<ForeignKey<AssegnazionelettoRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ASSEGNAZIONELETTO__FK_ASSEGNAZIONELETTO_PK_DEGENTE, Keys.ASSEGNAZIONELETTO__FK_ASSEGNAZIONELETTO_PK_LETTO);
    }

    private transient Degente _degente;
    private transient Letto _letto;

    /**
     * Get the implicit join path to the <code>DEGENTE</code> table.
     */
    public Degente degente() {
        if (_degente == null)
            _degente = new Degente(this, Keys.ASSEGNAZIONELETTO__FK_ASSEGNAZIONELETTO_PK_DEGENTE);

        return _degente;
    }

    /**
     * Get the implicit join path to the <code>LETTO</code> table.
     */
    public Letto letto() {
        if (_letto == null)
            _letto = new Letto(this, Keys.ASSEGNAZIONELETTO__FK_ASSEGNAZIONELETTO_PK_LETTO);

        return _letto;
    }

    @Override
    public Assegnazioneletto as(String alias) {
        return new Assegnazioneletto(DSL.name(alias), this);
    }

    @Override
    public Assegnazioneletto as(Name alias) {
        return new Assegnazioneletto(alias, this);
    }

    @Override
    public Assegnazioneletto as(Table<?> alias) {
        return new Assegnazioneletto(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Assegnazioneletto rename(String name) {
        return new Assegnazioneletto(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Assegnazioneletto rename(Name name) {
        return new Assegnazioneletto(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Assegnazioneletto rename(Table<?> name) {
        return new Assegnazioneletto(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, Integer, String, String, Integer, LocalDate> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super String, ? super Integer, ? super String, ? super String, ? super Integer, ? super LocalDate, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super String, ? super Integer, ? super String, ? super String, ? super Integer, ? super LocalDate, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
