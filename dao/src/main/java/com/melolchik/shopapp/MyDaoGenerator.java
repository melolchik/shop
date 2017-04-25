package com.melolchik.shopapp;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

/**
 * The type My dao generator.
 */
public class MyDaoGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";

    /**
     * Main.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.melolchik.shopapp.dao");
        schema.enableKeepSectionsByDefault();


        addTables(schema);


        new DaoGenerator().generateAll(schema, OUT_DIR);
    }

    /**
     * Create tables and the relationships between them
     */
    private static void addTables(Schema schema) {
        /* entities */
        addCountry(schema);
        Entity productEntity = addProduct(schema);
        addPurchase(schema,productEntity);

    }

    /**
     * Create user's Properties
     *
     * @return DBUser entity
     */
    private static Entity addCountry(Schema schema) {
        Entity entity = schema.addEntity("Country");
        entity.addLongProperty("countryId").notNull().unique().primaryKey();
        entity.addStringProperty("countryName").notNull();
        return entity;
    }

    private static Entity addProduct(Schema schema) {
        Entity entity = schema.addEntity("Product");
        entity.addLongProperty("productId").notNull().unique().primaryKey();
        entity.addStringProperty("productName").notNull();
        entity.addStringProperty("productImage");
        entity.addFloatProperty("productPrice");
        entity.addLongProperty("productCountry");
        entity.implementsInterface("android.os.Parcelable");
        return entity;
    }

    private static Entity addPurchase(Schema schema, Entity productEntity) {
        Entity entity = schema.addEntity("Purchase");
        entity.addIdProperty().primaryKey().autoincrement();
        entity.addLongProperty("purchaseId");
        entity.addFloatProperty("weight");
        Property productId = entity.addLongProperty("productId").notNull().getProperty();
        entity.addToOne(productEntity,productId);
        return entity;
    }

}
