/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.routines;


import bookmanagementsystem.jooq.pg_catalog.PgCatalog;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

import java.time.OffsetDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class TransactionTimestamp extends AbstractRoutine<OffsetDateTime> {

    /**
     * The parameter <code>pg_catalog.transaction_timestamp.RETURN_VALUE</code>.
     */
    public static final Parameter<OffsetDateTime> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", SQLDataType.TIMESTAMPWITHTIMEZONE(6), false, false);
    private static final long serialVersionUID = 1L;

    /**
     * Create a new routine call instance
     */
    public TransactionTimestamp() {
        super("transaction_timestamp", PgCatalog.PG_CATALOG, SQLDataType.TIMESTAMPWITHTIMEZONE(6));

        setReturnParameter(RETURN_VALUE);
    }
}
