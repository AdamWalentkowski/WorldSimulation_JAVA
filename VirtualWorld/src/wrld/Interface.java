package wrld;

import org.Organism;

import java.io.Serializable;

public class Interface implements Serializable {

   public String CommentAboutSpawn(Organism new_organism) {

        String x="A "+new_organism.GetName()+" has been added\n";
       return x;
    }

   public String CommentAboutDeath(Organism prey, Organism killer) {

        String x="A " + prey.GetName() + " has been slain by a(n) " + killer.GetName() + "\n";
       return x;
    }

   public String CommentAboutEater(Organism food, Organism eater) {

        String x="A " + eater.GetName() + " has eaten a(n) " + food.GetName() + "\n";
       return x;
    }

   public String CommentAboutDeflection(Organism deflected) {

        String x="A Turtle has deflected the attack of a(n) " + deflected.GetName() + "\n";
       return x;
    }

   public String CommentAboutEscape(Organism attacker) {

        String x="An Antelope has escaped from the combat with a(n) " + attacker.GetName() + "\n";
       return x;
    }

   public String CommentAboutPower() {

        String x="A man used his special power, 3(+2) long jumps\n";
       return x;
    }

   public String CommentAboutCooldown() {

        String x="You cannot use man's special power until he cools down\n";
       return x;

    }
}
