package org;

import wrld.*;

public class Grass extends Plant {

    public Grass(World w, int x, int y) {
        super(w, x, y);
        strength = 0;
        initiative = 0;
        symbol = 'g';
        name = "Grass";
    }

    @Override
    public Plant Grow(int pos_x, int pos_y) {
        Plant child = new Grass(world, pos_x, pos_y);
        return child;
    }


}
