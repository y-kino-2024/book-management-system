/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.routines;


import bookmanagementsystem.jooq.pg_catalog.PgCatalog;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgStatResetSingleFunctionCounters extends AbstractRoutine<java.lang.Void> {

    /**
     * The parameter
     * <code>pg_catalog.pg_stat_reset_single_function_counters._1</code>.
     */
    public static final Parameter<Long> _1 = Internal.createParameter("_1", SQLDataType.BIGINT, false, true);
    private static final long serialVersionUID = 1L;

    /**
     * Create a new routine call instance
     */
    public PgStatResetSingleFunctionCounters() {
        super("pg_stat_reset_single_function_counters", PgCatalog.PG_CATALOG);

        addInParameter(_1);
    }

    /**
     * Set the <code>_1</code> parameter IN value to the routine
     */
    public void set__1(Long value) {
        setValue(_1, value);
    }
}
