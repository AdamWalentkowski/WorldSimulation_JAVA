package wrld;

import org.*;

import java.io.Serializable;
import java.util.*;



public class World implements Serializable {

    protected static final int MAP_SIZE=20;
    protected LinkedList <Organism> organisms;
    private Organism[][] map;
    private Interface graphic = new Interface();
    protected LinkedList <String> comment_list;

    public World(){
        map = new Organism[MAP_SIZE][MAP_SIZE];
        organisms = new LinkedList<>();
        comment_list = new LinkedList<>();
        this.AddOrganism(new Wolf(this, 0, 0));
        this.AddOrganism(new Wolf(this, 4, 2));
        this.AddOrganism(new Antelope(this, 10, 11));
        this.AddOrganism(new Sheep(this, 13, 14));
        this.AddOrganism(new Fox(this, 11, 12));
        this.AddOrganism(new Fox(this, 15, 13));
        this.AddOrganism(new Sheep(this, 5, 14));
        this.AddOrganism(new Turtle(this, 17, 12));
        this.AddOrganism(new Grass(this, 1, 7));
        this.AddOrganism(new Guarana(this, 5, 19));
        this.AddOrganism(new Dandelion(this, 15, 12));
        this.AddOrganism(new Belladonna(this, 10, 12));
        this.AddOrganism(new Hogweed(this, 9, 9));
        this.AddOrganism(new Man(this, 6, 6));

    }
    public Organism GetOrganism(int x, int y) {
        return map[y][x];
    }

    public void DrawWorld(MainFrame screen) {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j]!=null) {
                    screen.ChangeColor(map[i][j].GetSymbol(), j, i);
                }
                else screen.ClearColor(j, i);
            }
        }
    }

    public void AddOrganism(Organism new_organism) {
        if (map[new_organism.GetPosX()][new_organism.GetPosY()]==null) {
            map[new_organism.GetPosY()][new_organism.GetPosX()] = new_organism;
            organisms.add(new_organism);
            comment_list.add(graphic.CommentAboutSpawn(new_organism));
        }
    }

    public void KillOrganism(Organism to_kill, Organism killer) {
        comment_list.add(graphic.CommentAboutDeath(to_kill, killer));
        to_kill.SetStatus(true);
        ClearAfterOrganism(to_kill.GetPosX(), to_kill.GetPosY());
    }

    public void EatPlant(Organism to_eat, Organism eater) {
        comment_list.add(graphic.CommentAboutEater(to_eat, eater));
        to_eat.SetStatus(true);
        ClearAfterOrganism(to_eat.GetPosX(), to_eat.GetPosY());
    }

    public void SayAboutDeflection(Organism deflected) {
       comment_list.add(graphic.CommentAboutDeflection(deflected));
    }

    public void SayAboutEscape(Organism attacker) {
       comment_list.add(graphic.CommentAboutEscape(attacker));
    }

    public void SayAboutPower() {
       comment_list.add(graphic.CommentAboutPower());
    }

    public void SayAboutCooldown() {
       comment_list.add(graphic.CommentAboutCooldown());
    }

    public boolean IsOnMap(int x, int y) {
        if (x < 0 || y < 0 || x > MAP_SIZE - 1 || y > MAP_SIZE - 1) return false;
        return true;
    }

    public boolean IsOccupied(int x, int y) {
        if (map[y][x] == null) return false;
        return true;
    }

    public boolean IsAnyAvailable(int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (IsOnMap(x + i-1, y + j-1)) {
                    if (!IsOccupied(x + i-1, y + j-1)) return true;
                }
            }
        }
        return false;
    }

    public void SetRandomDirection(int[] x, int[] y) {
        int dx;
        int dy;
        Random r = new Random();
        int rand;
        do {
            rand = r.nextInt(4);
            switch (rand)
            {
                case 0:
                    dx = 1;
                    dy = 0;
                    break;
                case 1:
                    dx = -1;
                    dy = 0;
                    break;
                case 2:
                    dx = 0;
                    dy = 1;
                    break;
                case 3:
                    dx = 0;
                    dy = -1;
                    break;
                default:
                    dx = 0;
                    dy = 0;
                    break;
            }
        } while (!IsOnMap(x[0] + dx, y[0] + dy));

        x[0] += dx;
        y[0] += dy;
    }

    public void MoveOrganism(Organism organism, int x, int y) {
        map[y][x] = organism;
    }

    public void ClearAfterOrganism(int x, int y) {
        map[y][x] = null;
    }

    public void SortVector() {
        Collections.sort(organisms, new Comparator<Organism>(){
            @Override
            public int compare(Organism o1, Organism o2){
                if(o1.GetInitiative() > o2.GetInitiative()){
                    return 1;
                }
                if(o1.GetInitiative() < o2.GetInitiative()){
                    return -1;
                }
                if(o1.GetInitiative() == o2.GetInitiative()){
                    if(o1.GetAge() > o2.GetAge()){
                        return 1;
                    }
                    else return -1;
                }
                return 0;
            }
        });
    }

    public void OrderedTurn() {
        int number_of_organisms = organisms.size();
        for (int i = 0; i < number_of_organisms; i++) {
            if (!organisms.get(i).GetStatus()) {
                organisms.get(i).TakeAction();
            }
        }
    }

    public void ClearVector() {
        for (int i = 0,  k = organisms.size(); i < k; i++, k = organisms.size()) {
            if (organisms.get(i).GetStatus()) {
                organisms.remove(i);
            }
        }
    }

    public void ClearComments() {
        comment_list.clear();
    }

    public void SetActionForHuman(int x){
        for (int i = 0; i < organisms.size(); i++) {
            if(organisms.get(i).GetSymbol()=='M'){
                organisms.get(i).SetDirection(x);
            }
        }

    }
}
