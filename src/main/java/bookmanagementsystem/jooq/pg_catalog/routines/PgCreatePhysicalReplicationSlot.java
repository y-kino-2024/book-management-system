/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.pg_catalog.routines;


import bookmanagementsystem.jooq.pg_catalog.PgCatalog;
import org.jooq.Parameter;
import org.jooq.impl.*;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PgCreatePhysicalReplicationSlot extends AbstractRoutine<java.lang.Void> {

    /**
     * The parameter
     * <code>pg_catalog.pg_create_physical_replication_slot.slot_name</code>.
     */
    public static final Parameter<String> SLOT_NAME1 = Internal.createParameter("slot_name", SQLDataType.VARCHAR, false, false);
    /**
     * The parameter
     * <code>pg_catalog.pg_create_physical_replication_slot.immediately_reserve</code>.
     */
    public static final Parameter<Boolean> IMMEDIATELY_RESERVE = Internal.createParameter("immediately_reserve", SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), true, false);
    /**
     * The parameter
     * <code>pg_catalog.pg_create_physical_replication_slot.temporary</code>.
     */
    public static final Parameter<Boolean> TEMPORARY = Internal.createParameter("temporary", SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), true, false);
    /**
     * The parameter
     * <code>pg_catalog.pg_create_physical_replication_slot.slot_name</code>.
     */
    public static final Parameter<String> SLOT_NAME4 = Internal.createParameter("slot_name", SQLDataType.VARCHAR, false, false);
    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public static final Parameter<Object> LSN = Internal.createParameter("lsn", DefaultDataType.getDefaultDataType("\"pg_catalog\".\"pg_lsn\""), false, false);
    private static final long serialVersionUID = 1L;

    /**
     * Create a new routine call instance
     */
    public PgCreatePhysicalReplicationSlot() {
        super("pg_create_physical_replication_slot", PgCatalog.PG_CATALOG);

        addInOutParameter(SLOT_NAME1);
        addInParameter(IMMEDIATELY_RESERVE);
        addInParameter(TEMPORARY);
        addInOutParameter(SLOT_NAME4);
        addOutParameter(LSN);
    }

    /**
     * Set the <code>immediately_reserve</code> parameter IN value to the
     * routine
     */
    public void setImmediatelyReserve(Boolean value) {
        setValue(IMMEDIATELY_RESERVE, value);
    }

    /**
     * Set the <code>temporary</code> parameter IN value to the routine
     */
    public void setTemporary(Boolean value) {
        setValue(TEMPORARY, value);
    }

    /**
     * Get the <code>slot_name</code> parameter OUT value from the routine
     */
    public String getSlotName1() {
        return get(SLOT_NAME1);
    }

    /**
     * Set the <code>slot_name</code> parameter IN value to the routine
     */
    public void setSlotName1(String value) {
        setValue(SLOT_NAME1, value);
    }

    /**
     * Get the <code>slot_name</code> parameter OUT value from the routine
     */
    public String getSlotName4() {
        return get(SLOT_NAME4);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public Object getLsn() {
        return get(LSN);
    }
}
