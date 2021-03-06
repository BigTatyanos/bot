package appBot;
import java.util.*;

public class Player {
    private String name;
    public Map<Test, Hero> heroes;

    public Set<Hero> getHeroes(){
        return new HashSet<Hero>(heroes.values());
    }

    public Player(String name){
        this.name = name;
        this.heroes = new HashMap<>();
    }

    public String getName(){
        return name;
    }
}
