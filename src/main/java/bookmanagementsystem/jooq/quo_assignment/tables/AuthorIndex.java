/*
 * This file is generated by jOOQ.
 */
package bookmanagementsystem.jooq.quo_assignment.tables;


import bookmanagementsystem.jooq.quo_assignment.Keys;
import bookmanagementsystem.jooq.quo_assignment.QuoAssignment;
import bookmanagementsystem.jooq.quo_assignment.tables.AuthorsInfo.AuthorsInfoPath;
import bookmanagementsystem.jooq.quo_assignment.tables.BooksInfo.BooksInfoPath;
import bookmanagementsystem.jooq.quo_assignment.tables.records.AuthorIndexRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class AuthorIndex extends TableImpl<AuthorIndexRecord> {

    /**
     * The reference instance of <code>quo_assignment.author_index</code>
     */
    public static final AuthorIndex AUTHOR_INDEX = new AuthorIndex();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>quo_assignment.author_index.book_id</code>.
     */
    public final TableField<AuthorIndexRecord, Integer> BOOK_ID = createField(DSL.name("book_id"), SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>quo_assignment.author_index.author_id</code>.
     */
    public final TableField<AuthorIndexRecord, Integer> AUTHOR_ID = createField(DSL.name("author_id"), SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>quo_assignment.author_index.created_by</code>.
     */
    public final TableField<AuthorIndexRecord, String> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.VARCHAR(256), this, "");
    /**
     * The column <code>quo_assignment.author_index.created_at</code>.
     */
    public final TableField<AuthorIndexRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6), this, "");
    /**
     * The column <code>quo_assignment.author_index.updated_by</code>.
     */
    public final TableField<AuthorIndexRecord, String> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.VARCHAR(256), this, "");
    /**
     * The column <code>quo_assignment.author_index.updated_at</code>.
     */
    public final TableField<AuthorIndexRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("updated_at"), SQLDataType.LOCALDATETIME(6), this, "");
    /**
     * The column <code>quo_assignment.author_index.delete_flg</code>.
     */
    public final TableField<AuthorIndexRecord, String> DELETE_FLG = createField(DSL.name("delete_flg"), SQLDataType.VARCHAR(1), this, "");
    private transient AuthorsInfoPath _authorsInfo;
    private transient BooksInfoPath _booksInfo;

    private AuthorIndex(Name alias, Table<AuthorIndexRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private AuthorIndex(Name alias, Table<AuthorIndexRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>quo_assignment.author_index</code> table
     * reference
     */
    public AuthorIndex(String alias) {
        this(DSL.name(alias), AUTHOR_INDEX);
    }

    /**
     * Create an aliased <code>quo_assignment.author_index</code> table
     * reference
     */
    public AuthorIndex(Name alias) {
        this(alias, AUTHOR_INDEX);
    }

    /**
     * Create a <code>quo_assignment.author_index</code> table reference
     */
    public AuthorIndex() {
        this(DSL.name("author_index"), null);
    }

    public <O extends Record> AuthorIndex(Table<O> path, ForeignKey<O, AuthorIndexRecord> childPath, InverseForeignKey<O, AuthorIndexRecord> parentPath) {
        super(path, childPath, parentPath, AUTHOR_INDEX);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthorIndexRecord> getRecordType() {
        return AuthorIndexRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : QuoAssignment.QUO_ASSIGNMENT;
    }

    @Override
    public List<ForeignKey<AuthorIndexRecord, ?>> getReferences() {
        return Arrays.asList(Keys.AUTHOR_INDEX__AUTHOR_INDEX_AUTHOR_ID_FKEY, Keys.AUTHOR_INDEX__AUTHOR_INDEX_BOOK_ID_FKEY);
    }

    /**
     * Get the implicit join path to the
     * <code>quo_assignment.authors_info</code> table.
     */
    public AuthorsInfoPath authorsInfo() {
        if (_authorsInfo == null)
            _authorsInfo = new AuthorsInfoPath(this, Keys.AUTHOR_INDEX__AUTHOR_INDEX_AUTHOR_ID_FKEY, null);

        return _authorsInfo;
    }

    /**
     * Get the implicit join path to the <code>quo_assignment.books_info</code>
     * table.
     */
    public BooksInfoPath booksInfo() {
        if (_booksInfo == null)
            _booksInfo = new BooksInfoPath(this, Keys.AUTHOR_INDEX__AUTHOR_INDEX_BOOK_ID_FKEY, null);

        return _booksInfo;
    }

    @Override
    public AuthorIndex as(String alias) {
        return new AuthorIndex(DSL.name(alias), this);
    }

    @Override
    public AuthorIndex as(Name alias) {
        return new AuthorIndex(alias, this);
    }

    @Override
    public AuthorIndex as(Table<?> alias) {
        return new AuthorIndex(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AuthorIndex rename(String name) {
        return new AuthorIndex(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AuthorIndex rename(Name name) {
        return new AuthorIndex(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AuthorIndex rename(Table<?> name) {
        return new AuthorIndex(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AuthorIndex where(Condition condition) {
        return new AuthorIndex(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AuthorIndex where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AuthorIndex where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AuthorIndex where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public AuthorIndex where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public AuthorIndex where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public AuthorIndex where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public AuthorIndex where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AuthorIndex whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public AuthorIndex whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class AuthorIndexPath extends AuthorIndex implements Path<AuthorIndexRecord> {

        private static final long serialVersionUID = 1L;

        public <O extends Record> AuthorIndexPath(Table<O> path, ForeignKey<O, AuthorIndexRecord> childPath, InverseForeignKey<O, AuthorIndexRecord> parentPath) {
            super(path, childPath, parentPath);
        }

        private AuthorIndexPath(Name alias, Table<AuthorIndexRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public AuthorIndexPath as(String alias) {
            return new AuthorIndexPath(DSL.name(alias), this);
        }

        @Override
        public AuthorIndexPath as(Name alias) {
            return new AuthorIndexPath(alias, this);
        }

        @Override
        public AuthorIndexPath as(Table<?> alias) {
            return new AuthorIndexPath(alias.getQualifiedName(), this);
        }
    }
}
