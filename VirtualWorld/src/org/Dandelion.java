package org;

import wrld.World;

public class Dandelion extends Plant {

    public Dandelion (World w, int x, int y) {
        super(w, x, y);
        strength = 0;
        initiative = 0;
        symbol = 'd';
        name = "Dandelion";
    }

    public void TakeAction() {
        IncreaseAge(age);
        for (int i = 0; i < 3; i++) {
            Spread();
        }
    }
    @Override
    public Plant Grow(int pos_x, int pos_y) {
        Plant child = new Dandelion(world, pos_x, pos_y);
        return child;
    }
}
