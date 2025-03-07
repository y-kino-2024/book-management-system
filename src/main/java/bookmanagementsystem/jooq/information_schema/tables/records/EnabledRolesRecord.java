/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.information_schema.tables.records;


import bookmanagementsystem.jooq.information_schema.tables.EnabledRoles;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class EnabledRolesRecord extends TableRecordImpl<EnabledRolesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached EnabledRolesRecord
     */
    public EnabledRolesRecord() {
        super(EnabledRoles.ENABLED_ROLES);
    }

    /**
     * Create a detached, initialised EnabledRolesRecord
     */
    public EnabledRolesRecord(String roleName) {
        super(EnabledRoles.ENABLED_ROLES);

        setRoleName(roleName);
        resetChangedOnNotNull();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>information_schema.enabled_roles.role_name</code>.
     */
    public String getRoleName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>information_schema.enabled_roles.role_name</code>.
     */
    public void setRoleName(String value) {
        set(0, value);
    }
}
