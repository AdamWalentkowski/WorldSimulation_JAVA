package org;

import wrld.*;

import java.util.Random;

public class Antelope extends Animal {

    public Antelope(World w, int x, int y) {
        super(w, x, y);
        strength = 4;
        initiative = 4;
        symbol = 'A';
        name = "Antelope";
    }

    @Override
    public void TakeAction() {
        boolean status = true;
        int[] new_pos_x = {pos_x};
        int[] new_pos_y = {pos_y};
        IncreaseAge(age);
        world.SetRandomDirection(new_pos_x, new_pos_y);
        int[] newer_pos_x = {new_pos_x[0]};
        int[] newer_pos_y = {new_pos_y[0]};
        Random r = new Random();
        int rand = r.nextInt(2);
        if (rand < 1) {
            do {
                world.SetRandomDirection(newer_pos_x, newer_pos_y);
            } while (newer_pos_x[0] == pos_x && newer_pos_y[0] == pos_y);
        }
        if (world.IsOccupied(newer_pos_x[0], newer_pos_y[0])) {
            status = world.GetOrganism(newer_pos_x[0], newer_pos_y[0]).Collision(pos_x, pos_y);
        }

        if (status) {
            world.ClearAfterOrganism(pos_x, pos_y);
            this.pos_x = newer_pos_x[0];
            this.pos_y = newer_pos_y[0];
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
        Random r = new Random();
        int rand = r.nextInt(2);
        if(rand<1){
            if (CompareStrength(world.GetOrganism(x, y))) {
                world.KillOrganism(world.GetOrganism(x, y), this);
                return false;
            }
            else {
                world.KillOrganism(this, world.GetOrganism(x, y));
                return true;
            }
        }
        else if (world.IsAnyAvailable(pos_x, pos_y)) {
            world.SayAboutEscape(world.GetOrganism(x, y));
            int[] temp_pos_x=new int[1];
            int[] temp_pos_y=new int[1];
            do {
                temp_pos_x[0] = pos_x;
                temp_pos_y[0] = pos_y;
                world.SetRandomDirection(temp_pos_x, temp_pos_y);
            } while (world.IsOccupied(temp_pos_x[0], temp_pos_y[0]));
            world.ClearAfterOrganism(pos_x, pos_y);
            this.pos_x = temp_pos_x[0];
            this.pos_y = temp_pos_y[0];
            world.MoveOrganism(this, pos_x, pos_y);
            return true;
        }
        world.SayAboutEscape(world.GetOrganism(x, y));
        return false;
    }

    @Override
    public Animal GiveBirth(int pos_x, int pos_y) {
        Animal child = new Antelope(world, pos_x, pos_y);
        return child;
    }
}
