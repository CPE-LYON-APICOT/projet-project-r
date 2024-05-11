package r.project;


class carteMonstre extends carte {
    // Properties and methods specific to monster cards
    private int PV;    
    
    // Constructor
    public carteMonstre(String name, int cost, String desc, int attack, int pv, String lienImage, Faction faction) {
        super( name, cost,  desc, attack, lienImage, faction);
        this.PV = pv;
    }


    public int getPV() {
        return PV;
    }
    @Override
    public void setPV(int pv) {
        this.PV = pv;
    }
    @Override
    public void jouer() {
        // Code for playing a monster card
    }
    @Override
    public String afficher() {
            String details = "Nom: " + super.getNom() + "\n" +
            "Coût: " + super.getCout() + "\n" +
            "Description: " + super.getDescription() + "\n" +
            "Attaque: " + super.getAttaque() + "\n" +
            "Points de vie: " + getPV() + "\n" +
            "Faction: " + super.getFaction().GetNom() + "\n";
        return details;
    }
}