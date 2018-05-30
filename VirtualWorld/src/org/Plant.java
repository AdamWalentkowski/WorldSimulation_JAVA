package org;

import wrld.*;

import java.util.Random;

abstract public class Plant extends Organism{

    Plant(World w, int x, int y) {
        super(w, x, y);
    }

    @Override
    public void TakeAction() {
        IncreaseAge(age);
        Spread();
    }

    @Override
    public boolean Collision(int x, int y) {
        if (CompareStrength(world.GetOrganism(x, y))) {
            world.KillOrganism(world.GetOrganism(x, y), this);
            return false;
        }
        else if ((world.GetOrganism(x, y).GetSymbol() >= 'A') && (world.GetOrganism(x, y).GetSymbol() <= 'Z') && (!CompareStrength(world.GetOrganism(x, y)))) {
            world.EatPlant(this, world.GetOrganism(x, y));
            return false;
        }
	else if (!CompareStrength(world.GetOrganism(x, y))) {
            world.KillOrganism(this, world.GetOrganism(x, y));
            return true;
        }
        return false;
    }

    public void Spread() {
        Random r = new Random();
        int rand = r.nextInt(100);
        if (rand < 2) {
            int[] new_x = {pos_x};
            int[] new_y = {pos_y};
            if (world.IsAnyAvailable(pos_x, pos_y)) {
                do {
                    world.SetRandomDirection(new_x, new_y);
                } while (world.IsOccupied(new_x[0], new_y[0]));
                world.AddOrganism(Grow(new_x[0], new_y[0]));
            }
        }
    }

    abstract Plant Grow(int x, int y);
}
