# Pitch du projet

## TODO : Décrivez votre projet
Montrez qu'il mobilise des techniques de POO avancée

Comment allez-vous utiliser les patrons de conception ?
    - Observer / Observable : lorsque une carte arrive a 50 d'attaque et attaque avec 
    - Stratégie : methode de pioche aléatoire ou dans l'ordre que l'utilisateur a mis les cartes dans le deck
    - Fabrique : 
    - Singleton : 
    - Décorateur :
    - Builder : possibiliter de crée des decks (possibliter)

Comment allez-vous utiliser l'architecture MVC ?

Modèle: nos classes
Vue: partie graphique
Controleur: interaction entre les classes et controler les reactions et interfaces




deck composé de 30 carte prédéfini: joueur choisit un héro qui donnera des avantages sur le deck

chaque cartes sera sous une des 3 factions du jeux: il auront un coût en mana, de la vie, des dégats, un effet (potentielle), une description de la carte


Jeu en 1 vs 1: chaque joueur a un maximum de 20 pv.  Il perd des pv lorsqu'une carte l’attaque et si seulement il n’y a pas de carte pour le côté adverse. Sur le plateau, chaque personne ne peut pas mettre plus de 4 cartes.
2 styles de carte: unité et sort

 2 piles: pioche et défausse

On pioche une carte par tour
Carte maximum dans la main 8, commence avec 4 cartes. Si le max est dépassé, la carte piochée part dans la défausse. Chaque carte vaincu part dans la défausse.
Quand la pioche est vide, on mélange la défausse.  



Dans le jeu, nous avons des cartes, chaque carte appartient à un deck.
