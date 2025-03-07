/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.information_schema.tables;


import bookmanagementsystem.jooq.information_schema.Domains;
import bookmanagementsystem.jooq.information_schema.InformationSchema;
import bookmanagementsystem.jooq.information_schema.tables.records.SqlImplementationInfoRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import java.util.Collection;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class SqlImplementationInfo extends TableImpl<SqlImplementationInfoRecord> {

    /**
     * The reference instance of
     * <code>information_schema.sql_implementation_info</code>
     */
    public static final SqlImplementationInfo SQL_IMPLEMENTATION_INFO = new SqlImplementationInfo();
    private static final long serialVersionUID = 1L;
    /**
     * The column
     * <code>information_schema.sql_implementation_info.implementation_info_id</code>.
     */
    public final TableField<SqlImplementationInfoRecord, String> IMPLEMENTATION_INFO_ID = createField(DSL.name("implementation_info_id"), Domains.CHARACTER_DATA.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.sql_implementation_info.implementation_info_name</code>.
     */
    public final TableField<SqlImplementationInfoRecord, String> IMPLEMENTATION_INFO_NAME = createField(DSL.name("implementation_info_name"), Domains.CHARACTER_DATA.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.sql_implementation_info.integer_value</code>.
     */
    public final TableField<SqlImplementationInfoRecord, Integer> INTEGER_VALUE = createField(DSL.name("integer_value"), Domains.CARDINAL_NUMBER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.sql_implementation_info.character_value</code>.
     */
    public final TableField<SqlImplementationInfoRecord, String> CHARACTER_VALUE = createField(DSL.name("character_value"), Domains.CHARACTER_DATA.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.sql_implementation_info.comments</code>.
     */
    public final TableField<SqlImplementationInfoRecord, String> COMMENTS = createField(DSL.name("comments"), Domains.CHARACTER_DATA.getDataType(), this, "");

    private SqlImplementationInfo(Name alias, Table<SqlImplementationInfoRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SqlImplementationInfo(Name alias, Table<SqlImplementationInfoRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>information_schema.sql_implementation_info</code>
     * table reference
     */
    public SqlImplementationInfo(String alias) {
        this(DSL.name(alias), SQL_IMPLEMENTATION_INFO);
    }

    /**
     * Create an aliased <code>information_schema.sql_implementation_info</code>
     * table reference
     */
    public SqlImplementationInfo(Name alias) {
        this(alias, SQL_IMPLEMENTATION_INFO);
    }

    /**
     * Create a <code>information_schema.sql_implementation_info</code> table
     * reference
     */
    public SqlImplementationInfo() {
        this(DSL.name("sql_implementation_info"), null);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SqlImplementationInfoRecord> getRecordType() {
        return SqlImplementationInfoRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SqlImplementationInfo as(String alias) {
        return new SqlImplementationInfo(DSL.name(alias), this);
    }

    @Override
    public SqlImplementationInfo as(Name alias) {
        return new SqlImplementationInfo(alias, this);
    }

    @Override
    public SqlImplementationInfo as(Table<?> alias) {
        return new SqlImplementationInfo(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlImplementationInfo rename(String name) {
        return new SqlImplementationInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlImplementationInfo rename(Name name) {
        return new SqlImplementationInfo(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlImplementationInfo rename(Table<?> name) {
        return new SqlImplementationInfo(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SqlImplementationInfo where(Condition condition) {
        return new SqlImplementationInfo(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SqlImplementationInfo where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SqlImplementationInfo where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SqlImplementationInfo where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SqlImplementationInfo where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SqlImplementationInfo where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SqlImplementationInfo where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SqlImplementationInfo where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SqlImplementationInfo whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SqlImplementationInfo whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
