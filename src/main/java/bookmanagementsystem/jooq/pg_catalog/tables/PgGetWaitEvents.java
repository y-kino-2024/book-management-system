/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables;


import bookmanagementsystem.jooq.pg_catalog.PgCatalog;
import bookmanagementsystem.jooq.pg_catalog.tables.records.PgGetWaitEventsRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgGetWaitEvents extends TableImpl<PgGetWaitEventsRecord> {

    /**
     * The reference instance of <code>pg_catalog.pg_get_wait_events</code>
     */
    public static final PgGetWaitEvents PG_GET_WAIT_EVENTS = new PgGetWaitEvents();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>pg_catalog.pg_get_wait_events.type</code>.
     */
    public final TableField<PgGetWaitEventsRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.CLOB, this, "");
    /**
     * The column <code>pg_catalog.pg_get_wait_events.name</code>.
     */
    public final TableField<PgGetWaitEventsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CLOB, this, "");
    /**
     * The column <code>pg_catalog.pg_get_wait_events.description</code>.
     */
    public final TableField<PgGetWaitEventsRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    private PgGetWaitEvents(Name alias, Table<PgGetWaitEventsRecord> aliased) {
        this(alias, aliased, new Field[]{
        });
    }

    private PgGetWaitEvents(Name alias, Table<PgGetWaitEventsRecord> aliased, Field<?>[] parameters) {
        this(alias, aliased, parameters, null);
    }

    private PgGetWaitEvents(Name alias, Table<PgGetWaitEventsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.function(), where);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_get_wait_events</code> table
     * reference
     */
    public PgGetWaitEvents(String alias) {
        this(DSL.name(alias), PG_GET_WAIT_EVENTS);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_get_wait_events</code> table
     * reference
     */
    public PgGetWaitEvents(Name alias) {
        this(alias, PG_GET_WAIT_EVENTS);
    }

    /**
     * Create a <code>pg_catalog.pg_get_wait_events</code> table reference
     */
    public PgGetWaitEvents() {
        this(DSL.name("pg_get_wait_events"), null);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PgGetWaitEventsRecord> getRecordType() {
        return PgGetWaitEventsRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : PgCatalog.PG_CATALOG;
    }

    @Override
    public PgGetWaitEvents as(String alias) {
        return new PgGetWaitEvents(DSL.name(alias), this, parameters);
    }

    @Override
    public PgGetWaitEvents as(Name alias) {
        return new PgGetWaitEvents(alias, this, parameters);
    }

    @Override
    public PgGetWaitEvents as(Table<?> alias) {
        return new PgGetWaitEvents(alias.getQualifiedName(), this, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public PgGetWaitEvents rename(String name) {
        return new PgGetWaitEvents(DSL.name(name), null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public PgGetWaitEvents rename(Name name) {
        return new PgGetWaitEvents(name, null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public PgGetWaitEvents rename(Table<?> name) {
        return new PgGetWaitEvents(name.getQualifiedName(), null, parameters);
    }

    /**
     * Call this table-valued function
     */
    public PgGetWaitEvents call() {
        PgGetWaitEvents result = new PgGetWaitEvents(DSL.name("pg_get_wait_events"), null, new Field[]{});

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }
}
