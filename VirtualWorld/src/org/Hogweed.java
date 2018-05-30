package org;

import wrld.World;

public class Hogweed extends Plant {
    public Hogweed(World w, int x, int y) {
        super(w, x, y);
        strength = 10;
        initiative = 0;
        symbol = 'h';
        name = "Hogweed";
    }

    @Override
    public void TakeAction() {
        IncreaseAge(age);
        Mayhem();
        Spread();
    }

    @Override
    public boolean Collision(int x, int y) {
        world.KillOrganism(world.GetOrganism(x, y), this);
        return false;
    }

    public void Mayhem() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (world.IsOccupied(pos_x + i - 1, pos_y + j - 1) && world.GetOrganism(pos_x + i - 1, pos_y + j - 1).GetSymbol() >= 'A' && world.GetOrganism(pos_x + i - 1, pos_y + j - 1).GetSymbol() <= 'Z') {
                    world.KillOrganism(world.GetOrganism(pos_x + i - 1, pos_y + j - 1), this);
                }
            }
        }
    }

    @Override
    public Plant Grow(int pos_x, int pos_y) {
        Plant child = new Hogweed(world, pos_x, pos_y);
        return child;
    }
}
