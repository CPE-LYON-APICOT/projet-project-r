package r.project;

class carteSort extends carte {
    // Properties and methods specific to spell cards
    
    // Constructor
    public carteSort(String name, int cost, String desc, int attack, String lienImage, Faction faction) {
        super( name, cost,  desc, attack,lienImage, faction);
        // Additional initialization code for spell cards
    }
    @Override
    // Implementing abstract methods
    public void jouer() {
        // Code for playing a spell card
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
}
