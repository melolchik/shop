package com.melolchik.shopapp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COUNTRY".
*/
public class CountryDao extends AbstractDao<Country, Long> {

    public static final String TABLENAME = "COUNTRY";

    /**
     * Properties of entity Country.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CountryId = new Property(0, long.class, "countryId", true, "COUNTRY_ID");
        public final static Property CountryName = new Property(1, String.class, "countryName", false, "COUNTRY_NAME");
    }


    public CountryDao(DaoConfig config) {
        super(config);
    }
    
    public CountryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COUNTRY\" (" + //
                "\"COUNTRY_ID\" INTEGER PRIMARY KEY NOT NULL UNIQUE ," + // 0: countryId
                "\"COUNTRY_NAME\" TEXT NOT NULL );"); // 1: countryName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COUNTRY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Country entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCountryId());
        stmt.bindString(2, entity.getCountryName());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Country entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCountryId());
        stmt.bindString(2, entity.getCountryName());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Country readEntity(Cursor cursor, int offset) {
        Country entity = new Country( //
            cursor.getLong(offset + 0), // countryId
            cursor.getString(offset + 1) // countryName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Country entity, int offset) {
        entity.setCountryId(cursor.getLong(offset + 0));
        entity.setCountryName(cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Country entity, long rowId) {
        entity.setCountryId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Country entity) {
        if(entity != null) {
            return entity.getCountryId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Country entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
