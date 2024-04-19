package r.project;
import java.util.stream.Stream;

import r.project.DeckBuilder.Cards;
import r.project.DeckBuilder.Carte;

public class Deck {
    private Stream<Carte> lstCartes;
    private Hero hero;
    public Deck(Cards cartesInitiales,Hero pHero){
        this.lstCartes = cartesInitiales.list.stream();
        hero=pHero;
        /*DebuffObservable.getInstance().addObserver((e,f)->{
            lstCartes.findFirst().get().
        });  #ToDO debuff*/
            
    }
    public Stream<Carte> getLstCarte(){
        return lstCartes;
    }
    public Hero getHero(){
        return hero;
    }
    public void setHero(Hero pHero){
        hero=pHero;
    }
    
}
