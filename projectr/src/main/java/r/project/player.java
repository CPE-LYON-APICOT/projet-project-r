package r.project;
import java.util.Collection;
// import java.util.ArrayList;

// import java.util.stream.Stream;

public class player {
    private int pv;
    private Collection<carte> lstDeck;
    private Hero hero;
    
    public player(int pPv,Collection<carte> pLstDeck, Hero pHero)
    {
        this.pv=pPv;
        this.lstDeck=pLstDeck;
        this.hero=pHero;
    }
    public Hero getHero(){
        return hero;
    }
    
    public int getPv(){
        return pv;
    }
    public Collection<carte> getLstDeck(){
        return lstDeck;
    }
    
}
