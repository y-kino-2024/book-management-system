/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.quo_assignment;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in quo_assignment.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Sequences {

    /**
     * The sequence <code>quo_assignment.author_id_seq</code>
     */
    public static final Sequence<Long> AUTHOR_ID_SEQ = Internal.createSequence("author_id_seq", QuoAssignment.QUO_ASSIGNMENT, SQLDataType.BIGINT.nullable(false), null, null, null, 99999999, false, null);

    /**
     * The sequence <code>quo_assignment.book_id_seq</code>
     */
    public static final Sequence<Long> BOOK_ID_SEQ = Internal.createSequence("book_id_seq", QuoAssignment.QUO_ASSIGNMENT, SQLDataType.BIGINT.nullable(false), null, null, null, 99999999, false, null);
}
