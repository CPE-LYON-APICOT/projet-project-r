package r.project;

import java.util.ArrayList;

public class PiocheSimple implements Pioche{

    @Override
    public void piocherCartes(ArrayList<carte> main, ArrayList<carte> paquet) {
        if (main.size() < 5) {
            
            carte cartePiochee = paquet.get(0);
            paquet.remove(0);
            main.add(cartePiochee);
            
            
        }
    }
}
