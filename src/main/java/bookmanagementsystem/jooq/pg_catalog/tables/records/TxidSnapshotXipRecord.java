/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables.records;


import bookmanagementsystem.jooq.pg_catalog.tables.TxidSnapshotXip;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class TxidSnapshotXipRecord extends TableRecordImpl<TxidSnapshotXipRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached TxidSnapshotXipRecord
     */
    public TxidSnapshotXipRecord() {
        super(TxidSnapshotXip.TXID_SNAPSHOT_XIP);
    }

    /**
     * Create a detached, initialised TxidSnapshotXipRecord
     */
    public TxidSnapshotXipRecord(Long txidSnapshotXip) {
        super(TxidSnapshotXip.TXID_SNAPSHOT_XIP);

        setTxidSnapshotXip(txidSnapshotXip);
        resetChangedOnNotNull();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>pg_catalog.txid_snapshot_xip.txid_snapshot_xip</code>.
     */
    public Long getTxidSnapshotXip() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>pg_catalog.txid_snapshot_xip.txid_snapshot_xip</code>.
     */
    public void setTxidSnapshotXip(Long value) {
        set(0, value);
    }
}
