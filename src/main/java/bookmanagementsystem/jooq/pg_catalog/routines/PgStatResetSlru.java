/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.routines;


import bookmanagementsystem.jooq.pg_catalog.PgCatalog;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgStatResetSlru extends AbstractRoutine<java.lang.Void> {

    /**
     * The parameter <code>pg_catalog.pg_stat_reset_slru.target</code>.
     */
    public static final Parameter<String> TARGET = Internal.createParameter("target", SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL::text"), SQLDataType.CLOB)), true, false);
    private static final long serialVersionUID = 1L;

    /**
     * Create a new routine call instance
     */
    public PgStatResetSlru() {
        super("pg_stat_reset_slru", PgCatalog.PG_CATALOG);

        addInParameter(TARGET);
    }

    /**
     * Set the <code>target</code> parameter IN value to the routine
     */
    public void setTarget(String value) {
        setValue(TARGET, value);
    }
}
