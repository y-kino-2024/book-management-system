/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.information_schema.tables;


import bookmanagementsystem.jooq.information_schema.Domains;
import bookmanagementsystem.jooq.information_schema.InformationSchema;
import bookmanagementsystem.jooq.information_schema.tables.records.TriggeredUpdateColumnsRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import java.util.Collection;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class TriggeredUpdateColumns extends TableImpl<TriggeredUpdateColumnsRecord> {

    /**
     * The reference instance of
     * <code>information_schema.triggered_update_columns</code>
     */
    public static final TriggeredUpdateColumns TRIGGERED_UPDATE_COLUMNS = new TriggeredUpdateColumns();
    private static final long serialVersionUID = 1L;
    /**
     * The column
     * <code>information_schema.triggered_update_columns.trigger_catalog</code>.
     */
    public final TableField<TriggeredUpdateColumnsRecord, String> TRIGGER_CATALOG = createField(DSL.name("trigger_catalog"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.triggered_update_columns.trigger_schema</code>.
     */
    public final TableField<TriggeredUpdateColumnsRecord, String> TRIGGER_SCHEMA = createField(DSL.name("trigger_schema"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.triggered_update_columns.trigger_name</code>.
     */
    public final TableField<TriggeredUpdateColumnsRecord, String> TRIGGER_NAME = createField(DSL.name("trigger_name"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.triggered_update_columns.event_object_catalog</code>.
     */
    public final TableField<TriggeredUpdateColumnsRecord, String> EVENT_OBJECT_CATALOG = createField(DSL.name("event_object_catalog"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.triggered_update_columns.event_object_schema</code>.
     */
    public final TableField<TriggeredUpdateColumnsRecord, String> EVENT_OBJECT_SCHEMA = createField(DSL.name("event_object_schema"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.triggered_update_columns.event_object_table</code>.
     */
    public final TableField<TriggeredUpdateColumnsRecord, String> EVENT_OBJECT_TABLE = createField(DSL.name("event_object_table"), Domains.SQL_IDENTIFIER.getDataType(), this, "");
    /**
     * The column
     * <code>information_schema.triggered_update_columns.event_object_column</code>.
     */
    public final TableField<TriggeredUpdateColumnsRecord, String> EVENT_OBJECT_COLUMN = createField(DSL.name("event_object_column"), Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private TriggeredUpdateColumns(Name alias, Table<TriggeredUpdateColumnsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private TriggeredUpdateColumns(Name alias, Table<TriggeredUpdateColumnsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
                create view "triggered_update_columns" as  SELECT (current_database())::information_schema.sql_identifier AS trigger_catalog,
                  (n.nspname)::information_schema.sql_identifier AS trigger_schema,
                  (t.tgname)::information_schema.sql_identifier AS trigger_name,
                  (current_database())::information_schema.sql_identifier AS event_object_catalog,
                  (n.nspname)::information_schema.sql_identifier AS event_object_schema,
                  (c.relname)::information_schema.sql_identifier AS event_object_table,
                  (a.attname)::information_schema.sql_identifier AS event_object_column
                 FROM pg_namespace n,
                  pg_class c,
                  pg_trigger t,
                  ( SELECT ta0.tgoid,
                          (ta0.tgat).x AS tgattnum,
                          (ta0.tgat).n AS tgattpos
                         FROM ( SELECT pg_trigger.oid AS tgoid,
                                  information_schema._pg_expandarray(pg_trigger.tgattr) AS tgat
                                 FROM pg_trigger) ta0) ta,
                  pg_attribute a
                WHERE ((n.oid = c.relnamespace) AND (c.oid = t.tgrelid) AND (t.oid = ta.tgoid) AND ((a.attrelid = t.tgrelid) AND (a.attnum = ta.tgattnum)) AND (NOT t.tgisinternal) AND (NOT pg_is_other_temp_schema(n.oid)) AND (pg_has_role(c.relowner, 'USAGE'::text) OR has_column_privilege(c.oid, a.attnum, 'INSERT, UPDATE, REFERENCES'::text)));
                """), where);
    }

    /**
     * Create an aliased
     * <code>information_schema.triggered_update_columns</code> table reference
     */
    public TriggeredUpdateColumns(String alias) {
        this(DSL.name(alias), TRIGGERED_UPDATE_COLUMNS);
    }

    /**
     * Create an aliased
     * <code>information_schema.triggered_update_columns</code> table reference
     */
    public TriggeredUpdateColumns(Name alias) {
        this(alias, TRIGGERED_UPDATE_COLUMNS);
    }

    /**
     * Create a <code>information_schema.triggered_update_columns</code> table
     * reference
     */
    public TriggeredUpdateColumns() {
        this(DSL.name("triggered_update_columns"), null);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TriggeredUpdateColumnsRecord> getRecordType() {
        return TriggeredUpdateColumnsRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public TriggeredUpdateColumns as(String alias) {
        return new TriggeredUpdateColumns(DSL.name(alias), this);
    }

    @Override
    public TriggeredUpdateColumns as(Name alias) {
        return new TriggeredUpdateColumns(alias, this);
    }

    @Override
    public TriggeredUpdateColumns as(Table<?> alias) {
        return new TriggeredUpdateColumns(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TriggeredUpdateColumns rename(String name) {
        return new TriggeredUpdateColumns(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TriggeredUpdateColumns rename(Name name) {
        return new TriggeredUpdateColumns(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TriggeredUpdateColumns rename(Table<?> name) {
        return new TriggeredUpdateColumns(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TriggeredUpdateColumns where(Condition condition) {
        return new TriggeredUpdateColumns(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TriggeredUpdateColumns where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TriggeredUpdateColumns where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TriggeredUpdateColumns where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TriggeredUpdateColumns where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TriggeredUpdateColumns where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TriggeredUpdateColumns where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TriggeredUpdateColumns where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TriggeredUpdateColumns whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TriggeredUpdateColumns whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
