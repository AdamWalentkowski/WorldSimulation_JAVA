package org;

import wrld.World;

import java.util.Random;

public class Turtle extends Animal {

    public Turtle (World w, int x, int y) {
        super(w, x, y);
        strength = 2;
        initiative = 1;
        symbol = 'T';
        name = "Turtle";
    }

    @Override
    public void TakeAction() {
        Random r = new Random();
        int rand = r.nextInt(4);
        if(rand < 1){
            boolean status = true;
            int[] new_pos_x = {pos_x};
            int[] new_pos_y = {pos_y};
            IncreaseAge(age);
            world.SetRandomDirection(new_pos_x, new_pos_y);
            if (world.IsOccupied(new_pos_x[0], new_pos_y[0])) {
                status = world.GetOrganism(new_pos_x[0], new_pos_y[0]).Collision(pos_x, pos_y);
            }

            if (status) {
                world.ClearAfterOrganism(pos_x, pos_y);
                this.pos_x = new_pos_x[0];
                this.pos_y = new_pos_y[0];
                world.MoveOrganism(this, pos_x, pos_y);
            }
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
        else if (!CompareStrength(world.GetOrganism(x, y))&& world.GetOrganism(x, y).GetStrength()<5) {
            world.SayAboutDeflection(world.GetOrganism(x, y));
            return false;
        }
	else if (!CompareStrength(world.GetOrganism(x, y))) {
            world.KillOrganism(this, world.GetOrganism(x, y));
            return true;
        }
        return false;
    }

    @Override
    public Animal GiveBirth(int pos_x, int pos_y) {
        Animal child = new Turtle(world, pos_x, pos_y);
        return child;
    }
}
