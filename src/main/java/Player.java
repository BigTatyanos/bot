//package appBot;
import java.util.*;

public class Player {
    private String name;
    private String id;
    public Map<Test, Hero> heroes;

    public Set<Hero> getHeroes(){
        return new HashSet<Hero>(heroes.values());
    }

    public Player(String name, String id){
        this.id = id;
        this.name = name;
        this.heroes = new HashMap<>();
    }

    public String getName(){
        return name;
    }
    public String getId() { return id; }
}
