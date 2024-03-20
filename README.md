# CPE-LYON-APICOT-2024-3ICS-POO

Ce dépot contient les consignes pour le projet de POO de 3ICS.

## Objectifs

Au travers des différents cours, TD et TP, vous avez acquis de solides compétences en programmation orientée objet. Ce projet a pour objectif de vous permettre de mettre en pratique ces compétences.

Le sujet est libre compte tenu de votre sérieux et de votre maturité.

Le projet est à réaliser en binôme. Vous devrez me rendre un projet qui respecte les consignes ci-dessous.

Vous me présenterez le pitch de votre projet afin de valider le sujet.
A intervalle réguliers, je me comporterai comme un client dans le monde pro qui peut vous demander de modifier le projet en cours de route.

(**Plus vous codez de manière robuste, moins je vous parasite le sujet, plus vous ferez de choses mal conçues, plus je vous démontrerai que votre conception n'est pas adaptée et ce sera l'occasion de vous réexpliquer**)

N'hésitez pas à me poser des questions si vous avez des doutes sur la conception de votre projet, n'hésitez pas à piocher dans le cours des thématiques pour démontrez que vous avez compris les concepts.

## Consignes

Vous devez cependant respecter les contraintes suivantes :
- Vous utiliserez la programmation orientée objet
- Vous utiliserez git pour versionner votre code
- Vous ferez un commit très régulièrement (une fois par demi-journée de travail)
    - L'objectif est que je puisse suivre votre progression
- Vous devriez utiliser Spring pour gérer les dépendances
- Votre code comporte une interface graphique (Swing, JavaFX, autre)


Votre code comporte :
- Plusieurs classes, héritages, interfaces
- Il doit y avoir un héritage d'interface et vous devrez utiliser `instanceof`
- Une interface devra être implémentée par plus d'une classe qui ne soient pas dans une relation d'héritage (exemple Avion et Oiseau qui implémentent l'interface IVolant)
- Vous utiliserez des exceptions
- Vous utiliserez les patrons de conceptions suivants (au moins 4) :
    - Observer / Observable
    - Stratégie
    - Fabrique
    - Singleton
    - Décorateur
    - Builder

Il est obligatoire d'utiliser de l'injection de dépendance pour les classes qui en ont besoin.

**Vous utilisez obligatoirement l'architecture MVC**
    - Idéalement, vos classes "DTO" (en gros les classes qui représentent les données) ne doivent pas contenir de logique métier
    (Essayez, si vous faites un échiquier, de ne pas mettre de méthode `isMouvementValide` dans la classe `Piece` mais plutôt dans un service `EchiquierService` de manière à ce que si au dernier moment je vous dis de coder un mode de jeu ou les pièces se déplacent différemment, vous n'ayez pas à modifier la classe `Piece`)


## Exemple de projet

Vous pouvez coder un échiquier, un jeu de dames, un Uno, un Risk, ce que vous voulez tant que vous respectez les contraintes ci-dessus.
Vous pouvez aussi coder un projet qui n'a rien à voir avec les jeux de société, c'est juste un exemple.