package r.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

@Component
public class DeckBuilder {
    private Stream<Carte> stream;

    public DeckBuilder(Cards cartesInitiales){
        this.stream = cartesInitiales.list.stream();
    }
    public DeckBuilder filterByFaction(String faction){
        this.stream = this.stream.filter(carte -> true);
        return this;
    }
    public DeckBuilder filterByType(String type){
        this.stream = this.stream.filter(carte -> true);
        return this;
    }

    public Collection<Carte> build(){
        return this.stream.toList();
    }

    @Component
    public static class Cards {
        public final ArrayList<Carte> list;
    
        public Cards(){
            this.list = new ArrayList<Carte>();
        }
    }

    public static class Carte{
        
    }
}

