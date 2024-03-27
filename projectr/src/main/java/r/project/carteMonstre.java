package r.project;

class carteMonstre extends carte {
    // Properties and methods specific to monster cards
    private int PV;    
    
    // Constructor
    public carteMonstre(String name, int cost, String desc, int attack, int pv) {
        super( name, cost,  desc, attack);
        this.PV = pv;
    }


    public int getPV() {
        return PV;
    }
    public void setPV(int pv) {
        this.PV = pv;
    }
    
    // Implementing abstract methods
    public void jouer() {
        // Code for playing a monster card
    }
    
    public void afficher() {
        // Code for displaying a monster card
        
    }
}