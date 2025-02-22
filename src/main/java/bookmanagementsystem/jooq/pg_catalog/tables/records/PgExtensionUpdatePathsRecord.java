/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables.records;


import bookmanagementsystem.jooq.pg_catalog.tables.PgExtensionUpdatePaths;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgExtensionUpdatePathsRecord extends TableRecordImpl<PgExtensionUpdatePathsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached PgExtensionUpdatePathsRecord
     */
    public PgExtensionUpdatePathsRecord() {
        super(PgExtensionUpdatePaths.PG_EXTENSION_UPDATE_PATHS);
    }

    /**
     * Create a detached, initialised PgExtensionUpdatePathsRecord
     */
    public PgExtensionUpdatePathsRecord(String source, String target, String path) {
        super(PgExtensionUpdatePaths.PG_EXTENSION_UPDATE_PATHS);

        setSource(source);
        setTarget(target);
        setPath(path);
        resetChangedOnNotNull();
    }

    /**
     * Getter for <code>pg_catalog.pg_extension_update_paths.source</code>.
     */
    public String getSource() {
        return (String) get(0);
    }

    /**
     * Setter for <code>pg_catalog.pg_extension_update_paths.source</code>.
     */
    public void setSource(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>pg_catalog.pg_extension_update_paths.target</code>.
     */
    public String getTarget() {
        return (String) get(1);
    }

    /**
     * Setter for <code>pg_catalog.pg_extension_update_paths.target</code>.
     */
    public void setTarget(String value) {
        set(1, value);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>pg_catalog.pg_extension_update_paths.path</code>.
     */
    public String getPath() {
        return (String) get(2);
    }

    /**
     * Setter for <code>pg_catalog.pg_extension_update_paths.path</code>.
     */
    public void setPath(String value) {
        set(2, value);
    }
}
