package com.melolchik.shopapp.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.melolchik.shopapp.dao.Country;
import com.melolchik.shopapp.dao.Product;
import com.melolchik.shopapp.dao.Purchase;

import com.melolchik.shopapp.dao.CountryDao;
import com.melolchik.shopapp.dao.ProductDao;
import com.melolchik.shopapp.dao.PurchaseDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig countryDaoConfig;
    private final DaoConfig productDaoConfig;
    private final DaoConfig purchaseDaoConfig;

    private final CountryDao countryDao;
    private final ProductDao productDao;
    private final PurchaseDao purchaseDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        countryDaoConfig = daoConfigMap.get(CountryDao.class).clone();
        countryDaoConfig.initIdentityScope(type);

        productDaoConfig = daoConfigMap.get(ProductDao.class).clone();
        productDaoConfig.initIdentityScope(type);

        purchaseDaoConfig = daoConfigMap.get(PurchaseDao.class).clone();
        purchaseDaoConfig.initIdentityScope(type);

        countryDao = new CountryDao(countryDaoConfig, this);
        productDao = new ProductDao(productDaoConfig, this);
        purchaseDao = new PurchaseDao(purchaseDaoConfig, this);

        registerDao(Country.class, countryDao);
        registerDao(Product.class, productDao);
        registerDao(Purchase.class, purchaseDao);
    }
    
    public void clear() {
        countryDaoConfig.clearIdentityScope();
        productDaoConfig.clearIdentityScope();
        purchaseDaoConfig.clearIdentityScope();
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public PurchaseDao getPurchaseDao() {
        return purchaseDao;
    }

}
