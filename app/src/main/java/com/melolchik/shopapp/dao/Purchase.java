package com.melolchik.shopapp.dao;

import android.database.Cursor;

import org.greenrobot.greendao.annotation.*;

import com.melolchik.shopapp.dao.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import com.melolchik.common.util.AppLogger;

import java.util.Collections;
import java.util.List;
// KEEP INCLUDES END

/**
 * Entity mapped to table "PURCHASE".
 */
@Entity(active = true)
public class Purchase {

    @Id(autoincrement = true)
    private Long id;
    private Long purchaseId;
    private Float weight;
    private long productId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient PurchaseDao myDao;

    @ToOne(joinProperty = "productId")
    private Product product;

    @Generated
    private transient Long product__resolvedKey;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Purchase() {
    }

    public Purchase(Long id) {
        this.id = id;
    }

    @Generated
    public Purchase(Long id, Long purchaseId, Float weight, long productId) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.weight = weight;
        this.productId = productId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPurchaseDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public Product getProduct() {
        long __key = this.productId;
        if (product__resolvedKey == null || !product__resolvedKey.equals(__key)) {
            __throwIfDetached();
            ProductDao targetDao = daoSession.getProductDao();
            Product productNew = targetDao.load(__key);
            synchronized (this) {
                product = productNew;
            	product__resolvedKey = __key;
            }
        }
        return product;
    }

    @Generated
    public void setProduct(Product product) {
        if (product == null) {
            throw new DaoException("To-one property 'productId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.product = product;
            productId = product.getProductId();
            product__resolvedKey = productId;
        }
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

    // KEEP METHODS - put your custom methods here

    public void insertOrReplace() {
        log("insert " + this);
        ShopDatabase database = new ShopDatabase(true);

        try {
            DaoSession daoSession = database.getDaoSession();
            PurchaseDao dao = daoSession.getPurchaseDao();
            dao.insertOrReplaceInTx(this);
            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
            log("insert ex = " + e.getMessage());
        } finally {
            database.release();
        }
    }

    public static List<Purchase> getList() {
        List<Purchase> list = null;
        ShopDatabase database = new ShopDatabase(false);
        try {
            DaoSession daoSession = database.getDaoSession();
            PurchaseDao dao = daoSession.getPurchaseDao();
           // list = dao.queryDeep("","");
            Cursor cursor = database.getDatabase().rawQuery(dao.getSelectDeep(), new String[]{});
            list = dao.loadDeepAllAndCloseCursor(cursor);
            //list = dao.queryBuilder().build().list();
            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.release();
        }

        return list != null ? list : Collections.<Purchase>emptyList();
    }

    public static void deleteAll() {
        ShopDatabase database = new ShopDatabase(true);
        try {
            DaoSession daoSession = database.getDaoSession();
            PurchaseDao dao = daoSession.getPurchaseDao();
            dao.deleteAll();
            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.release();
        }

    }

    @Override
    public String toString() {
        return "" + getWeight();
    }

    protected static void log(String message) {
        AppLogger.log(Country.class.getCanonicalName() + " " + message);
    }
    // KEEP METHODS END

}
