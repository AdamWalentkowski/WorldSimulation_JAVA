package org;

import wrld.World;

public class Fox extends Animal {

    public Fox(World w, int x, int y) {
        super(w, x, y);
        strength = 3;
        initiative = 7;
        symbol = 'F';
        name = "Fox";
    }

    @Override
    public void TakeAction() {
        boolean status = false;
        int[] new_pos_x = {pos_x};
        int[] new_pos_y = {pos_y};
        IncreaseAge(age);
        world.SetRandomDirection(new_pos_x, new_pos_y);
        if (!world.IsOccupied(new_pos_x[0], new_pos_y[0])) {
            status = true;
        }
        if (world.IsOccupied(new_pos_x[0], new_pos_y[0])&&CompareStrength(world.GetOrganism(new_pos_x[0], new_pos_y[0]))) {
            status = world.GetOrganism(new_pos_x[0], new_pos_y[0]).Collision(pos_x, pos_y);
        }

        if (status) {
            world.ClearAfterOrganism(pos_x, pos_y);
            this.pos_x = new_pos_x[0];
            this.pos_y = new_pos_y[0];
            world.MoveOrganism(this, pos_x, pos_y);
        }
    }

    @Override
    public Animal GiveBirth(int pos_x, int pos_y) {
        Animal child = new Fox(world, pos_x, pos_y);
        return child;
    }
}
