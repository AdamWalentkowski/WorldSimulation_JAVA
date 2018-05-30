package org;

import wrld.World;

public class Sheep extends Animal {

    public Sheep(World w, int x, int y) {
        super(w, x, y);
        strength = 4;
        initiative = 4;
        symbol = 'S';
        name = "Sheep";
    }

    @Override
    public Animal GiveBirth(int pos_x, int pos_y) {
        Animal child = new Sheep(world, pos_x, pos_y);
        return child;
    }
}
