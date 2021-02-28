package appBot;
import java.util.*;

public class Player {
    private String name;
    public Map<Test, Hero> heroes;

    public Set<Hero> getHeroes(){
        return new HashSet<Hero>(heroes.values());
    }
}
