package r.project;

class carteSort extends carte {
    
    public carteSort(String name, int cost, String desc, int attack, String lienImage, Faction faction) {
        super( name, cost,  desc, attack,lienImage, faction);
    }
    @Override
    public void jouer() {
    }
    @Override
    public String afficher() {
            String details = "Nom: " + super.getNom() + "\n" +
            "Coût: " + super.getCout() + "\n" +
            "Description: " + super.getDescription() + "\n" +
            "Attaque: " + super.getAttaque() + "\n"  + "\n" +
            "Faction: " + super.getFaction().GetNom() + "\n";
        return details;
    }

    @Override
    public int getPV() {
        return 0;
    }
    @Override
    public void setPV(int pv) {

    }
}
