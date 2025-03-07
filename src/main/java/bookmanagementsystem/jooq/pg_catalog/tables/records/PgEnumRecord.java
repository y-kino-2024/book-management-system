/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables.records;


import bookmanagementsystem.jooq.pg_catalog.tables.PgEnum;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgEnumRecord extends UpdatableRecordImpl<PgEnumRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached PgEnumRecord
     */
    public PgEnumRecord() {
        super(PgEnum.PG_ENUM);
    }

    /**
     * Create a detached, initialised PgEnumRecord
     */
    public PgEnumRecord(Long oid, Long enumtypid, Float enumsortorder, String enumlabel) {
        super(PgEnum.PG_ENUM);

        setOid(oid);
        setEnumtypid(enumtypid);
        setEnumsortorder(enumsortorder);
        setEnumlabel(enumlabel);
        resetChangedOnNotNull();
    }

    /**
     * Getter for <code>pg_catalog.pg_enum.oid</code>.
     */
    public Long getOid() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>pg_catalog.pg_enum.oid</code>.
     */
    public void setOid(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>pg_catalog.pg_enum.enumtypid</code>.
     */
    public Long getEnumtypid() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>pg_catalog.pg_enum.enumtypid</code>.
     */
    public void setEnumtypid(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>pg_catalog.pg_enum.enumsortorder</code>.
     */
    public Float getEnumsortorder() {
        return (Float) get(2);
    }

    /**
     * Setter for <code>pg_catalog.pg_enum.enumsortorder</code>.
     */
    public void setEnumsortorder(Float value) {
        set(2, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>pg_catalog.pg_enum.enumlabel</code>.
     */
    public String getEnumlabel() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>pg_catalog.pg_enum.enumlabel</code>.
     */
    public void setEnumlabel(String value) {
        set(3, value);
    }

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }
}
