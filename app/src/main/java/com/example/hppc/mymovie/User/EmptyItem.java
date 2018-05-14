package com.example.hppc.mymovie.User;

/**
 * Created by HP PC on 25-04-2018.
 */
public class EmptyItem extends AbstractItem {

    public EmptyItem(String label) {
        super(label);
    }


    @Override
    public int getType() {
        return TYPE_EMPTY;
    }

}
