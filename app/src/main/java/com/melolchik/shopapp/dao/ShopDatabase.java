package com.melolchik.shopapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by melolchik on 10.03.2016.
 */
public class ShopDatabase {
    protected static DaoMaster.DevOpenHelper sDaoHelper;
    protected static Lock sLock = new ReentrantLock();;
    private Database mDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static void init(Context context){
        sDaoHelper = new DaoMaster.DevOpenHelper(context, "shop.db");
    }
    public static boolean isInit(){
        return (sDaoHelper != null);
    }
    public ShopDatabase(){
        this(false);
    }

    public ShopDatabase(boolean useWritableDatabase){
        sLock.lock();
        mDatabase = useWritableDatabase ? sDaoHelper.getWritableDb() :sDaoHelper.getReadableDb();
        mDaoMaster = new DaoMaster(mDatabase);
        mDaoSession = mDaoMaster.newSession();
    }
    public void release(){
        if(mDaoSession != null){
            mDaoSession.clear();
            mDaoSession = null;
        }
        if(mDatabase != null){
            mDatabase.close();
            mDatabase = null;
        }

       /* if (sDaoHelper != null) {
            sDaoHelper.release();
        }*/

        sLock.unlock();
    }

    public void cleanAllTables(){
        DaoMaster.dropAllTables(sDaoHelper.getWritableDb(), true);
        DaoMaster.createAllTables(sDaoHelper.getWritableDb(), true);
    }

    public void createTablesIfNotExist(){
        DaoMaster.createAllTables(sDaoHelper.getWritableDb(), true);
    }

   /* public SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }*/

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public Database getDatabase() {
        return mDatabase;
    }
}
