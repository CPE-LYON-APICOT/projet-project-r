package r.project;

public class player {
    private Deck deckJoueur;
    private int pv;
    public player(int pId, Deck pDeckJoueur,int pPv)
    {
        deckJoueur=pDeckJoueur;
        pv=pPv;
    }
    public Deck getDeck(){
        return deckJoueur;
    }
    public int getPv(){
        return pv;
    }
}
