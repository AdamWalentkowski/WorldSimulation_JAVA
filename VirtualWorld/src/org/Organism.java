package org;

import wrld.*;

import java.io.Serializable;

abstract public class Organism implements Serializable {

    protected World world;
    protected int pos_x;
    protected int pos_y;
    protected int strength;
    protected int initiative;
    protected int age;
    protected char symbol;
    protected  String name;
    protected boolean is_dead;

    Organism(World w, int x, int y){
        this.world=w;
        this.pos_x=x;
        this.pos_y=y;
        is_dead = false;
        age=0;
    }
    public int GetPosX() {
        return pos_x;
    }

    public int GetPosY() {
        return pos_y;
    }

    public int GetStrength() {
        return strength;
    }

    public int GetInitiative() {
        return initiative;
    }

    public int GetAge() {
        return age;
    }

    public char GetSymbol() {
        return symbol;
    }

    public boolean GetStatus() {
        return is_dead;
    }

    public  String GetName() {
        return name;
    }

    public void SetStatus(boolean is_dead) {
        this.is_dead = is_dead;
    }

    public void SetStrength(int strength) {
        this.strength = strength;
    }

    public void IncreaseAge(int age) {
        this.age=age+1;
    }

    public boolean CompareStrength(Organism attacker) {
        if (this.GetStrength() > attacker.GetStrength()) {
            return true;
        }
	else if (this.GetStrength() < attacker.GetStrength()) {
            return false;
        }
        return true;
    }
    public void SetDirection(int x){

    }
    abstract public boolean Collision(int x, int y);
    abstract public void TakeAction();
}
