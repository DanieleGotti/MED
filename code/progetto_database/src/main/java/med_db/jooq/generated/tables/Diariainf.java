/*
 * This file is generated by jOOQ.
 */
package med_db.jooq.generated.tables;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import med_db.jooq.generated.DefaultSchema;
import med_db.jooq.generated.Keys;
import med_db.jooq.generated.tables.records.DiariainfRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function9;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row9;
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
public class Diariainf extends TableImpl<DiariainfRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DIARIAINF</code>
     */
    public static final Diariainf DIARIAINF = new Diariainf();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DiariainfRecord> getRecordType() {
        return DiariainfRecord.class;
    }

    /**
     * The column <code>DIARIAINF.CODICE</code>.
     */
    public final TableField<DiariainfRecord, Integer> CODICE = createField(DSL.name("CODICE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>DIARIAINF.CODICE_DEGENTE</code>.
     */
    public final TableField<DiariainfRecord, String> CODICE_DEGENTE = createField(DSL.name("CODICE_DEGENTE"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>DIARIAINF.COUNT_DEGENTE</code>.
     */
    public final TableField<DiariainfRecord, Integer> COUNT_DEGENTE = createField(DSL.name("COUNT_DEGENTE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>DIARIAINF.CODICE_INFERMIERE</code>.
     */
    public final TableField<DiariainfRecord, String> CODICE_INFERMIERE = createField(DSL.name("CODICE_INFERMIERE"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>DIARIAINF.DATA</code>.
     */
    public final TableField<DiariainfRecord, LocalDate> DATA = createField(DSL.name("DATA"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>DIARIAINF.ORA</code>.
     */
    public final TableField<DiariainfRecord, LocalTime> ORA = createField(DSL.name("ORA"), SQLDataType.LOCALTIME, this, "");

    /**
     * The column <code>DIARIAINF.NOTE_PARTICOLARI</code>.
     */
    public final TableField<DiariainfRecord, String> NOTE_PARTICOLARI = createField(DSL.name("NOTE_PARTICOLARI"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>DIARIAINF.IMPORTANTE</code>.
     */
    public final TableField<DiariainfRecord, Boolean> IMPORTANTE = createField(DSL.name("IMPORTANTE"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>DIARIAINF.FARMACO</code>.
     */
    public final TableField<DiariainfRecord, String> FARMACO = createField(DSL.name("FARMACO"), SQLDataType.CLOB, this, "");

    private Diariainf(Name alias, Table<DiariainfRecord> aliased) {
        this(alias, aliased, null);
    }

    private Diariainf(Name alias, Table<DiariainfRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>DIARIAINF</code> table reference
     */
    public Diariainf(String alias) {
        this(DSL.name(alias), DIARIAINF);
    }

    /**
     * Create an aliased <code>DIARIAINF</code> table reference
     */
    public Diariainf(Name alias) {
        this(alias, DIARIAINF);
    }

    /**
     * Create a <code>DIARIAINF</code> table reference
     */
    public Diariainf() {
        this(DSL.name("DIARIAINF"), null);
    }

    public <O extends Record> Diariainf(Table<O> child, ForeignKey<O, DiariainfRecord> key) {
        super(child, key, DIARIAINF);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<DiariainfRecord> getPrimaryKey() {
        return Keys.DIARIAINF__PK_DIARIAINF;
    }

    @Override
    public List<ForeignKey<DiariainfRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DIARIAINF__FK_DIARIAINF_PK_DEGENTE);
    }

    private transient Degente _degente;

    /**
     * Get the implicit join path to the <code>DEGENTE</code> table.
     */
    public Degente degente() {
        if (_degente == null)
            _degente = new Degente(this, Keys.DIARIAINF__FK_DIARIAINF_PK_DEGENTE);

        return _degente;
    }

    @Override
    public Diariainf as(String alias) {
        return new Diariainf(DSL.name(alias), this);
    }

    @Override
    public Diariainf as(Name alias) {
        return new Diariainf(alias, this);
    }

    @Override
    public Diariainf as(Table<?> alias) {
        return new Diariainf(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Diariainf rename(String name) {
        return new Diariainf(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Diariainf rename(Name name) {
        return new Diariainf(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Diariainf rename(Table<?> name) {
        return new Diariainf(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, String, Integer, String, LocalDate, LocalTime, String, Boolean, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function9<? super Integer, ? super String, ? super Integer, ? super String, ? super LocalDate, ? super LocalTime, ? super String, ? super Boolean, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function9<? super Integer, ? super String, ? super Integer, ? super String, ? super LocalDate, ? super LocalTime, ? super String, ? super Boolean, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}