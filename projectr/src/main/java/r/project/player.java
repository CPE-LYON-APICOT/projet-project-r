package r.project;
import java.util.Collection;
// import java.util.ArrayList;

// import java.util.stream.Stream;

public class player {
    private int pv;
    private Collection<carte> lstDeck;
    
    public player(int pId,Collection<carte> pLstDeck, int pPv)
    {
        this.pv=pPv;
        this.lstDeck=pLstDeck;

    }
    
    public int getPv(){
        return pv;
    }
    public Collection<carte> getLstDeck(){
        return lstDeck;
    }
    
}
