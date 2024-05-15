package r.project;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observer;


// import java.util.stream.Stream;

public class player implements IKeyObservable{
    private int pv;
    private Collection<carte> lstDeck;
    private Hero hero;
    private List<IKeyObserver> observers = new ArrayList<>();
    
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

    public void pertePv(int attaque){
        this.pv=this.pv-attaque;
    }
    public Collection<carte> getLstDeck(){
        return lstDeck;
    }

    @Override
    public void addObserver(IKeyObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IKeyObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(char key) {
        for (IKeyObserver observer : observers) {
            observer.update(key);
        }
    }
    // Méthode pour simuler la pression d'une touche
    public void pressKey(char key) {
        notifyObservers(key);
    }
    
}
