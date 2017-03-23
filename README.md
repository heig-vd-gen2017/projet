# HEIG-VD - GEN 2017 - Jeu de réflexe en multijoueur

Abass Mahdavi, Denise Gemesio, Luca Sivillica & Ludovic Delafontaine

## But du jeu
Le jeu se compose d'une surface interactive sur laquelle il est possible de déplacer la souris et cliquer. Le but est de tester les réflexes de différents joueurs connectés en réseau au jeu avec plusieurs modes décrits ci-dessous. L'architecture du jeu est de type client-serveur. Le serveur gère la communication entre les différents clients, les scores de chacun ainsi que les spécificités des différents modes de jeu.

## Fonctionnalités importantes
* Rejoindre une partie
* Voir le score des différents joueurs pendant le jeu
* Enregistrer le score des joueurs
* Modes de jeu
    * First to click: un objet apparaît après un temps aléatoire sur la surface et le premier joueur qui arrive à cliquer sur l'objet gagne. Plusieurs options peuvent être activées pour ce mode:
        * Objets avec bonus
        * Objets avec malus
        * Objets mystère \(soit bonus soit malus\)
    
## Fonctionnalités optionnelles
* Modes de jeu
    * Memory: des objets apparaissent dans un ordre défini une première fois, la seconde fois qu'ils apparaissent dans le même ordre, il faut s'en souvenir pour cliquer le plus rapidement possible.
    * Find your path: tous les joueurs commencent au même endroit et doivent traverser un labyrinthe le plus rapidement possible sans toucher les bords.
    * Can't escape: des objets se déplacent sur la surface et il faut les éviter. Plusieurs options peuvent être activées pour ce mode:
        * Les objets changent de couleur
* Inverted: la direction de la souris est inversée: aller vers le haut va vers le bas, aller vers la gauche va vers la droite, etc.
* Ajout de sonorités

## Contraintes techniques
* Etre connecté à un réseau
* Etre conscient de la latence sur le réseau entre les clients et serveurs
* Réduire au maximum les communications entre le client et le serveur pour avoir le moins de latence possible
* Envoyer l'information aux joueurs en même temps

## Contraintes de jeu 
* Avoir un pseudo
* Un nombre défini de joueurs à respecter
* 

## Priorités de développement
* Protocole de communication 
    * Connexion client-serveur
    * Transmission des règles du jeu
    * 
* Base de données
* 
