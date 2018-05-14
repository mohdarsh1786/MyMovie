package com.example.hppc.mymovie.User;

/**
 * Created by HP PC on 25-04-2018.
 */
public class EdgeItem extends AbstractItem {

    public EdgeItem(String label) {
        super(label);
    }



    @Override
    public int getType() {

        return TYPE_EDGE;
    }

}