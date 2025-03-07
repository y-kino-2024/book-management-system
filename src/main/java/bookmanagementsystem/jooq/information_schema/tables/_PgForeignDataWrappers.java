/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.information_schema.tables;


import bookmanagementsystem.jooq.information_schema.Domains;
import bookmanagementsystem.jooq.information_schema.InformationSchema;
import bookmanagementsystem.jooq.information_schema.tables.records._PgForeignDataWrappersRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.Collection;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class _PgForeignDataWrappers extends TableImpl<_PgForeignDataWrappersRecord> {

    /**
     * The reference instance of
     * <code>information_schema._pg_foreign_data_wrappers</code>
     */
    public static final _PgForeignDataWrappers _PG_FOREIGN_DATA_WRAPPERS = new _PgForeignDataWrappers();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>information_schema._pg_foreign_data_wrappers.oid</code>.
     */
    public final TableField<_PgForeignDataWrappersRecord, Long> OID = createField(DSL.name("oid"), SQLDataType.BIGINT, this, "");
    /**
     * The column
     * <code>information_schema._pg_foreign_data_wrappers.fdwowner</code>.
     */
    public final TableField<_PgForeignDataWrappersRecord, Long> FDWOWNER = createField(DSL.name("fdwowner"), SQLDataType.BIGINT, this, "");
    /**
     * The column
     * <code>information_schema._pg_foreign_data_wrappers.fdwoptions</code>.
     */
    public final TableField<_PgForeignDataWrappersRecord, String[]> FDWOPTIONS = createField(DSL.name("fdwoptions"), SQLDataType.CLOB.array(), this, "");
    /**
     * The column
     * <code>information_schema._pg_foreign_data_wrappers.foreign_data_wrapper_catalog</code>.
     */
    public final TableField<_PgForeignDataWrappersRecord, String> FOREIGN_DATA_WRAPPER_CATALOG = createField(DSL.name("foreign_data_wrapper_catalog"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema._pg_foreign_data_wrappers.foreign_data_wrapper_name</code>.
     */
    public final TableField<_PgForeignDataWrappersRecord, String> FOREIGN_DATA_WRAPPER_NAME = createField(DSL.name("foreign_data_wrapper_name"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema._pg_foreign_data_wrappers.authorization_identifier</code>.
     */
    public final TableField<_PgForeignDataWrappersRecord, String> AUTHORIZATION_IDENTIFIER = createField(DSL.name("authorization_identifier"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema._pg_foreign_data_wrappers.foreign_data_wrapper_language</code>.
     */
    public final TableField<_PgForeignDataWrappersRecord, String> FOREIGN_DATA_WRAPPER_LANGUAGE = createField(DSL.name("foreign_data_wrapper_language"), Domains.CHARACTER_DATA.getDataType(), this, "");

    private _PgForeignDataWrappers(Name alias, Table<_PgForeignDataWrappersRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private _PgForeignDataWrappers(Name alias, Table<_PgForeignDataWrappersRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
                create view "_pg_foreign_data_wrappers" as  SELECT w.oid,
                  w.fdwowner,
                  w.fdwoptions,
                  (current_database())::information_schema.sql_identifier AS foreign_data_wrapper_catalog,
                  (w.fdwname)::information_schema.sql_identifier AS foreign_data_wrapper_name,
                  (u.rolname)::information_schema.sql_identifier AS authorization_identifier,
                  ('c'::character varying)::information_schema.character_data AS foreign_data_wrapper_language
                 FROM pg_foreign_data_wrapper w,
                  pg_authid u
                WHERE ((u.oid = w.fdwowner) AND (pg_has_role(w.fdwowner, 'USAGE'::text) OR has_foreign_data_wrapper_privilege(w.oid, 'USAGE'::text)));
                """), where);
    }

    /**
     * Create an aliased
     * <code>information_schema._pg_foreign_data_wrappers</code> table reference
     */
    public _PgForeignDataWrappers(String alias) {
        this(DSL.name(alias), _PG_FOREIGN_DATA_WRAPPERS);
    }

    /**
     * Create an aliased
     * <code>information_schema._pg_foreign_data_wrappers</code> table reference
     */
    public _PgForeignDataWrappers(Name alias) {
        this(alias, _PG_FOREIGN_DATA_WRAPPERS);
    }

    /**
     * Create a <code>information_schema._pg_foreign_data_wrappers</code> table
     * reference
     */
    public _PgForeignDataWrappers() {
        this(DSL.name("_pg_foreign_data_wrappers"), null);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<_PgForeignDataWrappersRecord> getRecordType() {
        return _PgForeignDataWrappersRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public _PgForeignDataWrappers as(String alias) {
        return new _PgForeignDataWrappers(DSL.name(alias), this);
    }

    @Override
    public _PgForeignDataWrappers as(Name alias) {
        return new _PgForeignDataWrappers(alias, this);
    }

    @Override
    public _PgForeignDataWrappers as(Table<?> alias) {
        return new _PgForeignDataWrappers(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public _PgForeignDataWrappers rename(String name) {
        return new _PgForeignDataWrappers(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public _PgForeignDataWrappers rename(Name name) {
        return new _PgForeignDataWrappers(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public _PgForeignDataWrappers rename(Table<?> name) {
        return new _PgForeignDataWrappers(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _PgForeignDataWrappers where(Condition condition) {
        return new _PgForeignDataWrappers(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _PgForeignDataWrappers where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _PgForeignDataWrappers where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _PgForeignDataWrappers where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public _PgForeignDataWrappers where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public _PgForeignDataWrappers where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public _PgForeignDataWrappers where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public _PgForeignDataWrappers where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _PgForeignDataWrappers whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public _PgForeignDataWrappers whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
