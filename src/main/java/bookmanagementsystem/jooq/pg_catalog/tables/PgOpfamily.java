/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables;


import bookmanagementsystem.jooq.pg_catalog.Keys;
import bookmanagementsystem.jooq.pg_catalog.PgCatalog;
import bookmanagementsystem.jooq.pg_catalog.tables.records.PgOpfamilyRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgOpfamily extends TableImpl<PgOpfamilyRecord> {

    /**
     * The reference instance of <code>pg_catalog.pg_opfamily</code>
     */
    public static final PgOpfamily PG_OPFAMILY = new PgOpfamily();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>pg_catalog.pg_opfamily.oid</code>.
     */
    public final TableField<PgOpfamilyRecord, Long> OID = createField(DSL.name("oid"), SQLDataType.BIGINT.nullable(false), this, "");
    /**
     * The column <code>pg_catalog.pg_opfamily.opfmethod</code>.
     */
    public final TableField<PgOpfamilyRecord, Long> OPFMETHOD = createField(DSL.name("opfmethod"), SQLDataType.BIGINT.nullable(false), this, "");
    /**
     * The column <code>pg_catalog.pg_opfamily.opfname</code>.
     */
    public final TableField<PgOpfamilyRecord, String> OPFNAME = createField(DSL.name("opfname"), SQLDataType.VARCHAR.nullable(false), this, "");
    /**
     * The column <code>pg_catalog.pg_opfamily.opfnamespace</code>.
     */
    public final TableField<PgOpfamilyRecord, Long> OPFNAMESPACE = createField(DSL.name("opfnamespace"), SQLDataType.BIGINT.nullable(false), this, "");
    /**
     * The column <code>pg_catalog.pg_opfamily.opfowner</code>.
     */
    public final TableField<PgOpfamilyRecord, Long> OPFOWNER = createField(DSL.name("opfowner"), SQLDataType.BIGINT.nullable(false), this, "");

    private PgOpfamily(Name alias, Table<PgOpfamilyRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private PgOpfamily(Name alias, Table<PgOpfamilyRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_opfamily</code> table reference
     */
    public PgOpfamily(String alias) {
        this(DSL.name(alias), PG_OPFAMILY);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_opfamily</code> table reference
     */
    public PgOpfamily(Name alias) {
        this(alias, PG_OPFAMILY);
    }

    /**
     * Create a <code>pg_catalog.pg_opfamily</code> table reference
     */
    public PgOpfamily() {
        this(DSL.name("pg_opfamily"), null);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PgOpfamilyRecord> getRecordType() {
        return PgOpfamilyRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : PgCatalog.PG_CATALOG;
    }

    @Override
    public UniqueKey<PgOpfamilyRecord> getPrimaryKey() {
        return Keys.PG_OPFAMILY_OID_INDEX;
    }

    @Override
    public List<UniqueKey<PgOpfamilyRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.PG_OPFAMILY_AM_NAME_NSP_INDEX);
    }

    @Override
    public PgOpfamily as(String alias) {
        return new PgOpfamily(DSL.name(alias), this);
    }

    @Override
    public PgOpfamily as(Name alias) {
        return new PgOpfamily(alias, this);
    }

    @Override
    public PgOpfamily as(Table<?> alias) {
        return new PgOpfamily(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PgOpfamily rename(String name) {
        return new PgOpfamily(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PgOpfamily rename(Name name) {
        return new PgOpfamily(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PgOpfamily rename(Table<?> name) {
        return new PgOpfamily(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgOpfamily where(Condition condition) {
        return new PgOpfamily(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgOpfamily where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgOpfamily where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgOpfamily where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgOpfamily where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgOpfamily where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgOpfamily where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgOpfamily where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgOpfamily whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgOpfamily whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
