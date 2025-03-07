/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.tables.records;


import bookmanagementsystem.jooq.pg_catalog.tables.PgRules;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgRulesRecord extends TableRecordImpl<PgRulesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached PgRulesRecord
     */
    public PgRulesRecord() {
        super(PgRules.PG_RULES);
    }

    /**
     * Create a detached, initialised PgRulesRecord
     */
    public PgRulesRecord(String schemaname, String tablename, String rulename, String definition) {
        super(PgRules.PG_RULES);

        setSchemaname(schemaname);
        setTablename(tablename);
        setRulename(rulename);
        setDefinition(definition);
        resetChangedOnNotNull();
    }

    /**
     * Getter for <code>pg_catalog.pg_rules.schemaname</code>.
     */
    public String getSchemaname() {
        return (String) get(0);
    }

    /**
     * Setter for <code>pg_catalog.pg_rules.schemaname</code>.
     */
    public void setSchemaname(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>pg_catalog.pg_rules.tablename</code>.
     */
    public String getTablename() {
        return (String) get(1);
    }

    /**
     * Setter for <code>pg_catalog.pg_rules.tablename</code>.
     */
    public void setTablename(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>pg_catalog.pg_rules.rulename</code>.
     */
    public String getRulename() {
        return (String) get(2);
    }

    /**
     * Setter for <code>pg_catalog.pg_rules.rulename</code>.
     */
    public void setRulename(String value) {
        set(2, value);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>pg_catalog.pg_rules.definition</code>.
     */
    public String getDefinition() {
        return (String) get(3);
    }

    /**
     * Setter for <code>pg_catalog.pg_rules.definition</code>.
     */
    public void setDefinition(String value) {
        set(3, value);
    }
}
