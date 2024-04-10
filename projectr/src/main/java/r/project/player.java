package r.project;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class player {
    private Deck deckJoueur;
    private int pv;
    private Stream<Deck> lstDeck;
    public player(int pId, Deck pDeckJoueur,int pPv,Stream<Deck> pLstDeck)
    {
        deckJoueur=pDeckJoueur;
        pv=pPv;
        lstDeck=pLstDeck;
    }
    public Deck getDeck(){
        return deckJoueur;
    }
    public int getPv(){
        return pv;
    }
    public Stream<Deck> getLstDeck(){
        return lstDeck;
    }
    
}
