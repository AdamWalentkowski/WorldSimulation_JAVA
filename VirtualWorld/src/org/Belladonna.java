package org;

import wrld.World;

public class Belladonna extends Plant {

    public Belladonna(World w, int x, int y) {
        super(w,x,y);
        strength = 99;
        initiative = 0;
        symbol = 'b';
        name = "Belladonna";
    }

    @Override
    public boolean Collision(int x, int y) {
        world.KillOrganism(world.GetOrganism(x, y), this);
        return false;
    }

    @Override
    public Plant Grow(int pos_x, int pos_y) {
        Plant child = new Belladonna(world, pos_x, pos_y);
        return child;
    }
}
