package r.project;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheAléatoire implements Pioche {


    @Override
    public void piocherCartes(ArrayList<carte> main, ArrayList<carte> paquet) {
        Collections.shuffle(paquet);
        if (main.size()< 5) {
         
            carte cartePiochee = paquet.get(0);
            paquet.remove(0);
            main.add(cartePiochee);

        
        }
    }
}
