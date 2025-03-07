/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables;


import bookmanagementsystem.jooq.pg_catalog.PgCatalog;
import bookmanagementsystem.jooq.pg_catalog.tables.records.PgStatBgwriterRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.OffsetDateTime;
import java.util.Collection;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgStatBgwriter extends TableImpl<PgStatBgwriterRecord> {

    /**
     * The reference instance of <code>pg_catalog.pg_stat_bgwriter</code>
     */
    public static final PgStatBgwriter PG_STAT_BGWRITER = new PgStatBgwriter();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>pg_catalog.pg_stat_bgwriter.buffers_clean</code>.
     */
    public final TableField<PgStatBgwriterRecord, Long> BUFFERS_CLEAN = createField(DSL.name("buffers_clean"), SQLDataType.BIGINT, this, "");
    /**
     * The column <code>pg_catalog.pg_stat_bgwriter.maxwritten_clean</code>.
     */
    public final TableField<PgStatBgwriterRecord, Long> MAXWRITTEN_CLEAN = createField(DSL.name("maxwritten_clean"), SQLDataType.BIGINT, this, "");
    /**
     * The column <code>pg_catalog.pg_stat_bgwriter.buffers_alloc</code>.
     */
    public final TableField<PgStatBgwriterRecord, Long> BUFFERS_ALLOC = createField(DSL.name("buffers_alloc"), SQLDataType.BIGINT, this, "");
    /**
     * The column <code>pg_catalog.pg_stat_bgwriter.stats_reset</code>.
     */
    public final TableField<PgStatBgwriterRecord, OffsetDateTime> STATS_RESET = createField(DSL.name("stats_reset"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    private PgStatBgwriter(Name alias, Table<PgStatBgwriterRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private PgStatBgwriter(Name alias, Table<PgStatBgwriterRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
                create view "pg_stat_bgwriter" as  SELECT pg_stat_get_bgwriter_buf_written_clean() AS buffers_clean,
                pg_stat_get_bgwriter_maxwritten_clean() AS maxwritten_clean,
                pg_stat_get_buf_alloc() AS buffers_alloc,
                pg_stat_get_bgwriter_stat_reset_time() AS stats_reset;
                """), where);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_stat_bgwriter</code> table
     * reference
     */
    public PgStatBgwriter(String alias) {
        this(DSL.name(alias), PG_STAT_BGWRITER);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_stat_bgwriter</code> table
     * reference
     */
    public PgStatBgwriter(Name alias) {
        this(alias, PG_STAT_BGWRITER);
    }

    /**
     * Create a <code>pg_catalog.pg_stat_bgwriter</code> table reference
     */
    public PgStatBgwriter() {
        this(DSL.name("pg_stat_bgwriter"), null);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PgStatBgwriterRecord> getRecordType() {
        return PgStatBgwriterRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : PgCatalog.PG_CATALOG;
    }

    @Override
    public PgStatBgwriter as(String alias) {
        return new PgStatBgwriter(DSL.name(alias), this);
    }

    @Override
    public PgStatBgwriter as(Name alias) {
        return new PgStatBgwriter(alias, this);
    }

    @Override
    public PgStatBgwriter as(Table<?> alias) {
        return new PgStatBgwriter(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PgStatBgwriter rename(String name) {
        return new PgStatBgwriter(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PgStatBgwriter rename(Name name) {
        return new PgStatBgwriter(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PgStatBgwriter rename(Table<?> name) {
        return new PgStatBgwriter(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgStatBgwriter where(Condition condition) {
        return new PgStatBgwriter(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgStatBgwriter where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgStatBgwriter where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgStatBgwriter where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgStatBgwriter where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgStatBgwriter where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgStatBgwriter where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public PgStatBgwriter where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgStatBgwriter whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public PgStatBgwriter whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
