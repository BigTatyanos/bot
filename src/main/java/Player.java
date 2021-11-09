import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player {
    public Map<Test, Hero> heroes;
    private String name;
    private String id;

    public Player(String name, String id) {
        this.id = id;
        this.name = name;
        this.heroes = new HashMap<>();
    }

    public Set<Hero> getHeroes() {
        return new HashSet<>(heroes.values());
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
