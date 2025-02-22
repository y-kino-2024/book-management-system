/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables.records;


import bookmanagementsystem.jooq.pg_catalog.tables.PgReplicationOrigin;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgReplicationOriginRecord extends UpdatableRecordImpl<PgReplicationOriginRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached PgReplicationOriginRecord
     */
    public PgReplicationOriginRecord() {
        super(PgReplicationOrigin.PG_REPLICATION_ORIGIN);
    }

    /**
     * Create a detached, initialised PgReplicationOriginRecord
     */
    public PgReplicationOriginRecord(Long roident, String roname) {
        super(PgReplicationOrigin.PG_REPLICATION_ORIGIN);

        setRoident(roident);
        setRoname(roname);
        resetChangedOnNotNull();
    }

    /**
     * Getter for <code>pg_catalog.pg_replication_origin.roident</code>.
     */
    public Long getRoident() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>pg_catalog.pg_replication_origin.roident</code>.
     */
    public void setRoident(Long value) {
        set(0, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>pg_catalog.pg_replication_origin.roname</code>.
     */
    public String getRoname() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>pg_catalog.pg_replication_origin.roname</code>.
     */
    public void setRoname(String value) {
        set(1, value);
    }

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }
}
