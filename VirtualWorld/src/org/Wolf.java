package org;

import wrld.*;

public class Wolf extends Animal {

    public Wolf(World w, int x, int y){
        super(w, x, y);
        strength = 9;
        initiative = 5;
        symbol = 'W';
        name = "Wolf";
    }

    @Override
    public Animal GiveBirth(int pos_x, int pos_y) {
        Animal child = new Wolf(world, pos_x, pos_y);
        return child;
    }
}
