package r.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.Collectors;


@Component
public class DeckBuilder {
    private Stream<carte> stream;

    public DeckBuilder(ArrayList<carte> cartesInitiales){
        this.stream = cartesInitiales.stream();
    }
    public DeckBuilder filterByFaction(String faction){
        this.stream = this.stream.filter(carte -> true);
        return this;
    }
    public DeckBuilder filterByType(String type){
        this.stream = this.stream.filter(carte -> true);
        return this;
    }

    public Collection<carte> build(){
        // return this.stream.toList();
        return this.stream.collect(Collectors.toList());
        
    }

    

}

