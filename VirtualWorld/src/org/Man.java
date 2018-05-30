package org;

import wrld.World;

import java.util.Random;


public class Man extends Animal {

    private int power_alpha;
    private int power_beta;
    private int cooldown;
    private int direction;

    public Man(World w, int x, int y) {
        super(w, x, y);
        strength = 5;
        initiative = 4;
        symbol = 'M';
        name = "Man";
        power_alpha=0;
        power_beta=0;
        cooldown = 10;
        direction = -1;
    }

    @Override
    public void TakeAction() {
        boolean status = true;
        int[] new_pos_x = {pos_x};
        int[] new_pos_y = {pos_y};
        IncreaseAge(age);
        TakeStep(new_pos_x, new_pos_y);
        if (world.IsOccupied(new_pos_x[0], new_pos_y[0]) && this!=world.GetOrganism(new_pos_x[0], new_pos_y[0])) {
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
        if (CompareStrength(world.GetOrganism(x, y))) {
            world.KillOrganism(world.GetOrganism(x, y), this);
            return false;
        }
        else {
            world.KillOrganism(this, world.GetOrganism(x, y));
            return true;
        }
    }

   public void TakeStep(int[] x, int[] y) {
        int dx=0;
        int dy=0;
       Random r = new Random();
       int rand = r.nextInt(2);
        switch (direction) {
            case 0:
                dy--;
                if (power_alpha >= age) {
                    dy--;
                }
                else if (power_beta >= age) {
                    if (rand < 1) {
                        dy--;
                    }
                }
                break;
            case 1:
                dy++;
                if (power_alpha >= age) {
                    dy++;
                }
                else if (power_beta >= age) {
                    if (rand < 1) {
                        dy++;
                    }
                }
                break;
            case  3:
                dx++;
                if (power_alpha >= age) {
                    dx++;
                }
                else if (power_beta >= age) {
                    if (rand < 1) {
                        dx++;
                    }
                }
                break;
            case 2:
                dx--;
                if (power_alpha >= age) {
                    dx--;
                }
                else if (power_beta >= age) {
                    if (rand < 1) {
                        dx--;
                    }
                }
                break;
            case 4:
                if (cooldown >= 10) {
                    TurnOnThePower();
                }
                else {
                    world.SayAboutCooldown();
                }
                break;
            default: break;
        }
        this.direction=-1;
        if (world.IsOnMap(x[0]+dx, y[0]+dy)) {
            x[0] += dx;
            y[0] += dy;
        }
        cooldown++;
    }

    public void TurnOnThePower() {
        world.SayAboutPower();
        power_alpha = age+3;
        power_beta = power_alpha+2;
        cooldown = 0;
    }
    @Override
    public void SetDirection(int x){
        this.direction=x;
    }

    @Override
    public Animal GiveBirth(int pos_x, int pos_y) {
        Animal child = new Man(world, pos_x, pos_y);
        return child;
    }
}
