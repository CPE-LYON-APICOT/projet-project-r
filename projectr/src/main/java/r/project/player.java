package r.project;

import java.util.Collection;

public class player {
    private int pv;
    private Collection<carte> lstDeck;
    private Hero hero;

    private static player instance;

    private player(int pPv, Collection<carte> pLstDeck, Hero pHero) {
        this.pv = pPv;
        this.lstDeck = pLstDeck;
        this.hero = pHero;
    }

    public static player getInstance(int pPv, Collection<carte> pLstDeck, Hero pHero) {
        if (instance == null) {
            instance = new player(pPv, pLstDeck, pHero);
        }
        return instance;
    }

    public Hero getHero() {
        return hero;
    }

    public int getPv() {
        return pv;
    }

    public void pertePv(int attaque) {
        this.pv = this.pv - attaque;
    }

    public Collection<carte> getLstDeck() {
        return lstDeck;
    }
}