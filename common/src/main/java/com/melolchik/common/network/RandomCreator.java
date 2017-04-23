package com.melolchik.common.network;

/**
 * Created by melolchik on 07.04.2017.
 */

public interface RandomCreator<ItemType> {

    ItemType createRandomItem();
    ItemType createRandomItem(int position);
}
