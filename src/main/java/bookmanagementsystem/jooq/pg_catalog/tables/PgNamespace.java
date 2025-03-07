/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables;


import bookmanagementsystem.jooq.pg_catalog.Keys;
import bookmanagementsystem.jooq.pg_catalog.PgCatalog;
import bookmanagementsystem.jooq.pg_catalog.tables.records.PgNamespaceRecord;
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
public class PgNamespace extends TableImpl<PgNamespaceRecord> {

    /**
     * The reference instance of <code>pg_catalog.pg_namespace</code>
     */
    public static final PgNamespace PG_NAMESPACE = new PgNamespace();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>pg_catalog.pg_namespace.oid</code>.
     */
    public final TableField<PgNamespaceRecord, Long> OID = createField(DSL.name("oid"), SQLDataType.BIGINT.nullable(false), this, "");
    /**
     * The column <code>pg_catalog.pg_namespace.nspname</code>.
     */
    public final TableField<PgNamespaceRecord, String> NSPNAME = createField(DSL.name("nspname"), SQLDataType.VARCHAR.nullable(false), this, "");
    /**
     * The column <code>pg_catalog.pg_namespace.nspowner</code>.
     */
    public final TableField<PgNamespaceRecord, Long> NSPOWNER = createField(DSL.name("nspowner"), SQLDataType.BIGINT.nullable(false), this, "");
    /**
     * The column <code>pg_catalog.pg_namespace.nspacl</code>.
     */
    public final TableField<PgNamespaceRecord, String[]> NSPACL = createField(DSL.name("nspacl"), SQLDataType.VARCHAR.array(), this, "");

    private PgNamespace(Name alias, Table<PgNamespaceRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private PgNamespace(Name alias, Table<PgNamespaceRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_namespace</code> table reference
     */
    public PgNamespace(String alias) {
        this(DSL.name(alias), PG_NAMESPACE);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_namespace</code> table reference
     */
    public PgNamespace(Name alias) {
        this(alias, PG_NAMESPACE);
    }

    /**
     * Create a <code>pg_catalog.pg_namespace</code> table reference
     */
    public PgNamespace() {
        this(DSL.name("pg_namespace"), null);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PgNamespaceRecord> getRecordType() {
        return PgNamespaceRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : PgCatalog.PG_CATALOG;
    }

    @Override
    public UniqueKey<PgNamespaceRecord> getPrimaryKey() {
        return Keys.PG_NAMESPACE_OID_INDEX;
    }

    @Override
    public List<UniqueKey<PgNamespaceRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.PG_NAMESPACE_NSPNAME_INDEX);
    }

    @Override
    public PgNamespace as(String alias) {
        return new PgNamespace(DSL.name(alias), this);
    }

    @Override
    public PgNamespace as(Name alias) {
        return new PgNamespace(alias, this);
    }

    @Override
    public PgNamespace as(Table<?> alias) {
        return new PgNamespace(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PgNamespace rename(String name) {
        return new PgNamespace(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PgNamespace rename(Name name) {
        return new PgNamespace(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PgNamespace rename(Table<?> name) {
        return new PgNamespace(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgNamespace where(Condition condition) {
        return new PgNamespace(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgNamespace where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgNamespace where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgNamespace where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgNamespace where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgNamespace where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgNamespace where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgNamespace where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgNamespace whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgNamespace whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
