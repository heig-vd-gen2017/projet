# HEIG-VD - GEN 2017 - Jeu de réflexe en multi-joueurs {#heig-vd---pro-2017---cahier-des-charges}

Abass Mahdavi, Denise Gemesio, Luca Sivillica & Ludovic Delafontaine

## But du jeu

Le jeu se compose d'une surface interactive où il est possible de déplacer la souris et cliquer dessus. Le but est de tester les réflexes de différents joueurs connectés en réseau avec plusieurs modes décrits ci-dessous. L'architecture du jeu est de type client-serveur où le serveur gère la communication entre les différents clients, les scores de chacun ainsi que les spécificités des différents modes de jeu.

## Fonctionnalités

* Rejoindre un salon de jeu
* Voir le score des différents joueurs
* Enregistrer le score du meilleur joueur

## Modes proposés

* First to click: Des objets apparaissent après un temps aléatoire sur la surface et le premier joueur qui arrive à cliquer dessus gagne. Plusieurs options peuvent être activées pour ce mode:
  * Objets avec bonus
  * Objets avec malus
  * Objets mystères \(soit bonus soit malus\)
  * Les objets bougent
* Memory: Des objets apparaissent dans un ordre définis et ils faut se souvenir pour refaire dans le même ordre le plus rapidement possible
* Find your path: Tous les joueurs commencent au même endroit et doivent traverser un labyrinthe le plus rapidement possible sans toucher les bords.
* Can't escape: Des objets se déplacent sur la surface et il faut les éviter. Plusieurs options peuvent être activées pour ce mode:
  * Les objets changent de couleurs
* Inverted: La direction de la souris est inversée: aller vers le haut va vers le bas, aller à gauche va sur la droite, etc.



