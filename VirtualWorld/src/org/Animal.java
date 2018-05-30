package org;
import wrld.*;

abstract public class Animal extends Organism {

    Animal(World w, int x, int y) {
        super(w, x, y);
    }

    @Override
    public void TakeAction() {
        boolean status = true;
        int[] new_pos_x = {pos_x};
        int[] new_pos_y = {pos_y};
        IncreaseAge(age);
        world.SetRandomDirection(new_pos_x, new_pos_y);
        if(world.IsOccupied(new_pos_x[0], new_pos_y[0])){
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
    public boolean Collision(int x, int y) {
        if (this.GetSymbol() == world.GetOrganism(x, y).GetSymbol()) {
            if (this.GetAge()>10 && world.GetOrganism(x, y).GetAge()>10) {
                Procreate();
            }
            return false;
        }
	else if (CompareStrength(world.GetOrganism(x, y))) {
            world.KillOrganism(world.GetOrganism(x, y), this);
            return false;
        }
        else if (!CompareStrength(world.GetOrganism(x, y))) {
            world.KillOrganism(this, world.GetOrganism(x, y));
            return true;
        }
        return false;
    }


    public void Procreate() {
        int[] new_x = {pos_x};
        int[] new_y = {pos_y};
        if (world.IsAnyAvailable(pos_x, pos_y)) {
            do {
                world.SetRandomDirection(new_x, new_y);
            } while (world.IsOccupied(new_x[0], new_y[0]));
            world.AddOrganism(GiveBirth(new_x[0], new_y[0]));
        }
    }

    abstract Animal GiveBirth(int x, int y);
}
