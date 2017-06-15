<!---
  Quelques commandes utiles:

  1. Pour compiler le document, il faut que vous ayiez installé Pandoc et XeLaTeX (ou XeTeX).
      La commande pour compiler le document est la suivate:

      pandoc --latex-engine=xelatex --listings Rapport.md -o Rapport.pdf
-->

---
lang: fr

numbersections: true

papersize: a4

geometry: margin=2cm

header-includes:
    - \usepackage{etoolbox}
    - \usepackage{fancyhdr}
    - \usepackage[T1]{fontenc}
    - \usepackage{xcolor}
    - \usepackage{graphicx}
    - \usepackage{tikz}
    - \usepackage{hyperref}
    - \usepackage{caption}

    # Some beautiful colors.
    - \definecolor{pblue}{rgb}{0.13, 0.13, 1.0}
    - \definecolor{pgray}{rgb}{0.46, 0.45, 0.48}
    - \definecolor{pgreen}{rgb}{0.0, 0.5, 0.0}
    - \definecolor{pred}{rgb}{0.9, 0.0, 0.0}

    - \renewcommand{\ttdefault}{pcr}

    # 'fancyhdr' settings.
    - \pagestyle{fancy}
    - \fancyhead[CO,CE]{}
    - \fancyhead[LO,LE]{Reflexia}
    - \fancyhead[RO,RE]{HEIG-VD - GEN 2017}

    # Redefine TOC style.
    - \setcounter{tocdepth}{2}

    # 'listings' settings.
    - \lstset{breaklines = true}
    - \lstset{backgroundcolor = \color{black!10}}
    - \lstset{basicstyle = \ttfamily}
    - \lstset{breakatwhitespace = true}
    - \lstset{columns = fixed}
    - \lstset{commentstyle = \color{pgreen}}
    - \lstset{extendedchars = true}
    - \lstset{frame = trbl}
    - \lstset{frameround = none}
    - \lstset{framesep = 2pt}
    - \lstset{keywordstyle = \bfseries}
    - \lstset{keywordsprefix = {@}}                           # Java annotations.
    - \lstset{language = Java}
    - \lstset{numbers=left,xleftmargin=2em,xrightmargin=0.25em}
    - \lstset{numberstyle = \small\ttfamily}
    - \lstset{showstringspaces = false}
    - \lstset{stringstyle = \color{pred}}
    - \lstset{tabsize = 2}

    # 'listings' not page breaking.
    - \BeforeBeginEnvironment{lstlisting}{\begin{minipage}{\textwidth}}
    - \AfterEndEnvironment{lstlisting}{\end{minipage}}

    # Set links colors
    - \hypersetup{colorlinks,citecolor=black,filecolor=black,linkcolor=black,urlcolor=black}
---
\makeatletter
\renewcommand{\@maketitle}{%
\newpage
\null
\vfil
\begingroup
\let\footnote\thanks
\begin{center}
{\Huge\@title}\vskip1.5em
\null
\null
\includegraphics[width=8cm, height=8cm]{images/stopwatch.png}\vskip1.5em
\null
\null
{\LARGE Rapport}\vskip1.5em
{
    \large
    Groupe 16\vskip0.4em
    \begin{tabular}{ll}
        \underline{Chef de projet} & Ludovic Delafontaine \\
        \underline{Membres} & Luca Sivillica \\
        & Abass Ali Mahdavi \\
        & Denise Gemesio \\
        \\
        \underline{Professeur} & Eric Lefrançois \\
    \end{tabular}}\vskip1.5em
{\large\@date}
\end{center}
\endgroup
\vfil
}
\makeatother

\title{%
  Reflexia \vskip0.4em
  \large Un jeu de réflexes}

\date{HEIG-VD - Semestre d'été 2017}

\maketitle

\begin{tikzpicture}[remember picture,overlay]
   \node[anchor=north east,inner sep=0.25cm] at (current page.north east)
              {\includegraphics[width=5cm]{images/heig-vd.png}};
\end{tikzpicture}

\newpage

\newpage

\tableofcontents

\listoffigures

\newpage


# Introduction
Durant la deuxième moitié du quatrième semestre des sections IE, IL et TS de l'HEIG-VD, nous devons effectuer un projet de Génie Logiciel par groupes de quatre personnes. Le but est de mettre en oeuvre les connaissances que nous avons acquises à propos de la méthodologie UP durant la première moitié du semestre à travers un projet complet. Nous devons prendre conscience des difficultés de planification d'un projet sur plusieurs itérations. Au terme du semestre, nous devons rendre un programme complet et fonctionnel, avec une documentaton adéquate et être capables de le présenter et le défendre.

Le projet dure huit semaines et toutes les séances de laboratoire du jeudi ainsi que certaines séances de cours théoriques seront destinées à compléter ce projet.

Dans le cadre du projet, l'équipe de programmation est composée du chef de projet Ludovic Delafontaine et des membres Luca Sivillica, Abass Ali Madhavi et Denise Gemesio.

Dans ce rapport, nous allons expliquer notre planification de travail à travers l'analyse, la conception du projet, l'implémentation du projet, la gestion du projet, l'état des lieux et faire une auto-critique de notre travail.

La dernière semaine de cours aura lieu un oral durant lequel nous aurons l'opportunité de présenter et défendre notre projet.

\newpage

# Analyse

## Règles du jeu
Le but du jeu est de cliquer le plus vite possible sur des images qui apparaîtront à la suite dans la surface interactive de jeu.
L'adminitrateur du jeu a le choix entre plusieurs modes de jeu, qui sont composés d'images bonus, malus et mystère (certaines fois bonus, certaines fois malus).

Les modes et leurs détails se trouvent dans le fichier `mode.xml` au sein de chaque dossier présent dans le dossier `modes` qui se trouve à la racine.
Ce qui suit peut être modifiable par l'administrateur :

- Le nom du mode
- Le nombre de points attribués à chaque image : bonus, malus et mystère
- Le nombre de points initiaux ainsi que le nombre de points à atteindre pour gagner
- L'activation ou désactivation des images bonus, malus et mystère
- Le temps, en millisecondes, qu'une image restera active
- Le temps maximum et minimum en millisecondes que l'image mettra pour apparaître, le temps final étant sélectionné aléatoirement

Concernant les joueurs, chaque joueur peut choisir un pseudo qui lui appartiendra uniquement lors de la partie en cours de lancement. L'identification est en effet inspirée des jeux d'arcade pour lesquels des personnes différentes peuvent choisir le même pseudo lors de parties différentes. Chaque joueur doit se connecter au serveur correspondant aux adresses que l'administrateur lui aura donné.

Afin de gagner, le joueur devra obtenir le nombre de points à atteindre défini par le mode de jeu. Dès qu'un joueur gagne, la partie se termine.

## Partage de responsabilités

### Serveur

* Configuration des modes de jeu
* Lancer la partie
* Attente de connexion des joueurs
* Visualiser le nombre de joueurs connectés afin que l'adminitrateur lance la partie dès qu'il le considère suffisant
* Enregistrer et permettre à chaque utilisateur de visualiser son propre score
* Mettre fin à la partie prématurément
* Dans la base de données, remise des scores des joueurs à zéro
* Modes de jeu tel qu'expliqué dans les règles du jeu

### Client

* Spécifier son pseudo
* Rejoindre une partie
* Jouer la partie
* Quitter la partie (note: la partie en cours n'est pas interrompue, le joueur est considéré comme ayant perdu)

## Diagramme d'activité

\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.7\textheight]{diagrammes_png/diagramme_d_activite.png}
  \captionof{figure}{Diagramme d'activité}
\end{minipage}

## Cas d'utilisation

### Diagramme général de contexte

\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.2\textheight]{diagrammes_png/use_cases.png}
  \captionof{figure}{Cas d'utilisation}
\end{minipage}

### Description des acteurs
#### Administrateur (Serveur)
L'administrateur s'occupe de configurer le serveur pour permettre aux joueurs de se connecter. De plus, celui-ci a des droits spéciaux comme arrêter une partie prématurément ou effacer la base de données des scores.

#### Joueur (Client)
Le joueur joue les parties que l'administrateur a configurées. Il clique sur les images apparaissant sur la surface de jeu jusqu'à ce qu'il gagne ou qu'un autre joueur gagne.

### Scénarios par cas d'utilisation

#### Configuration d'une partie

**Résumé**
Le serveur permet la configuration de la partie selon plusieurs options et envies. Différentes options sont disponibles et permettent d'activer ou désactiver des comportement de jeu. Une fois la configuration appliquée, les joueurs peuvent rejoindre la partie.

**Prérequis**
Le serveur doit être atteignable au sein du réseau local.

1. Le programme serveur est démarré et l'interface de configuration s'affiche
2. L'administrateur configure la partie
3. Une fois la configuration définie, l'administrateur autorise les joueurs à rejoindre la partie

#### Joindre une partie

**Résumé**
Permet de rejoindre la partie créée par le serveur. Les différents joueurs saisissent l'adresse IP ainsi que le port à utiliser et se connectent au serveur.

**Prérequis**
Un port doit être défini.

1. Ils saisissent le pseudo qu'ils souhaitent utiliser pour les identifier, l'adresse IP, le port du serveur et se connectent au serveur.

2. Le serveur envoie la configuration de la partie (règles du jeu et sprites) aux clients ainsi que le nombre de joueurs et leur pseudo

#### Lancement d'une partie

**Résumé**
Une fois le nombre de joueurs souhaité, l'administrateur lance la partie. La connexion de la part de futurs joueurs est bloquée jusqu'à la fin de la partie. Les joueurs sont notifiés du début de la partie.

**Prérequis**
Les joueurs doivent être prêts au début de la partie.

1. Le nombre de joueurs qui se connectent sont affichés sur la fenêtre de configuration de partie de l'administrateur
2. Quand l'administrateur considère le nombre de joueurs suffisant, il lance la partie. Cela clot les connexions et désactive l'interface de configuration.
3. Le serveur avertit les joueurs que la partie débute.

#### Jouer une partie

**Résumé**
Les manches démarrent successivement: le serveur envoie les différents objets aux joueurs qui doivent cliquer le plus rapidement sur les objets. Certains apportent des points, d'autres en retirent et d'autres sont mystères. Les scores se mettent à jour à chaque fin de manche pour tous les joueurs.

1. Après un temps aléatoire, un objet apparaît exactement au même moment et au même endroit de la mappe chez chaque joueur.
2. Dès qu'un joueur a cliqué sur l'objet, le serveur est notifié. Le joueur ayant été le plus rapide gagne (ou perd selon si c'est un objet malus) le point et les autres joueurs sont notifiés des nouveaux scores de chacun.
3. Le classement est modifié.
4. La partie de déroule jusqu'à ce qu'un des joueurs ait atteint le score défini par l'administrateur.
5. Une fois la partie terminée, les joueurs sont invités à rejouer une partie tout en pouvant voir les scores des autres joueurs et le meilleur score associés à leur pseudo pour le mode de jeu en cours.

#### Mettre fin à une partie

**Résumé**
Si l'administrateur souhaite abréger une partie avant sa fin, il peut décider de mettre fin à la partie. Le jeu se termine et les joueurs sont invités à recommencer une partie.

**Prérequis**
Une partie doit être en cours.

1. L'administrateur arrête la partie depuis la fenêtre de configuration.
2. Les joueurs sont notifiés de la fin de la partie.
3. Un résumé des scores s'affiche.
4. La fenêtre de configuration d'une partie redevient active et l'administrateur peut recommencer une partie.

#### Réinitialisation des scores

**Résumé**
Lorsque l'administrateur le souhaite, il peut décider d'effacer les scores stockés dans la base de données pour remettre à zéro les meilleurs scores. Il est possible de spécifier quels sont les scores à réinitialiser (selon une date).

1. Le programme serveur est démarré et l'interface de configuration s'affiche
2. L'administrateur accède à l'interface de réinitialisation des scores
3. L'administrateur décide quelles sont les données à effacer (plage de dates, joueurs, etc.)
4. L'administrateur valide sa requête et le serveur nettoie sa base de données
5. Un message permet de valider le bon déroulement de l'action

### Scénarios alternatifs significatifs

#### Pseudo déjà utilisé

**Résumé**
Le joueur remplit la fenêtre de connexion mais son pseudo a déjà été choisi par un autre joueur.

1. Le joueur entre un pseudo déjà entré par un autre joueur
2. Une fenêtre d'avertissement apparaît annonçant que le pseudo existe déjà
3. Le joueur ne peut pas joindre la partie
4. Le joueur peut relancer une connexion et se connecter avec un autre pseudo

#### Partie pleine

**Résumé**
Le joueur remplit la fenêtre de connexion mais la partie a déjà démarré.

1. Le joueur remplit la fenêtre de connexion et appuie sur *Se connecter*
2. Une fenêtre d'avertissement apparaît annonçant que la partie a déjà démarré
3. Le joueur ne peut pas joindre la partie
4. Le joueur peut attendre qu'une partie soit en phase de configuration

## Modèle de domaine

### Client
\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.3\textheight]{diagrammes_png/domaineFinalClient.png}
  \captionof{figure}{Modèle de domaine du côté client}
\end{minipage}

### Serveur
\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.25\textheight]{diagrammes_png/domaineFinalServeur.png}
  \captionof{figure}{Modèle de domaine du côté serveur}
\end{minipage}

### Commentaires et justification
La logique métier reste la même des deux côtés. La différence, c'est que c'est le serveur qui va générer les objets aléatoires, alors que le client va recevoir l'objet aléatoire et l'afficher sur l'interface.

## Base de données

### Modèle conceptuel

\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.3\textheight]{diagrammes_png/database.png}
  \captionof{figure}{Base de données}
\end{minipage}

#### Commentaires
La base de données servira à stocker les scores des différents joueurs pour chacun des modes auxquels il aurait joué.

\newpage

# Conception

## Protocole d'échange
Notre application communiquera à travers le réseau en utilisant le protocole suivant :

\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.6\textheight]{diagrammes_png/protocol.png}
  \captionof{figure}{Protocole}
\end{minipage}

## Base de données

### Modèle conceptuel

\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.1\textheight]{diagrammes_png/final_database.png}
  \captionof{figure}{Base de données finale}
\end{minipage}

Nous avons finalement décidé de réduire la base de données à une table. En effet, la base de données prévue au début aurait été plus utile dans le cas où nous aurions eu besoin d'informations de modes de jeu plus évolués ou d'un système de login pour les utilisateurs. Dans notre cas, tout ce qui nous intéresse se retrouve dans cette table: l'ID de l'instance, le pseudo du joueur, le mode de jeu joué durant le score atteint, le score et la date à laquelle le score a été atteint.

\newpage

# Implémentation

## Problèmes rencontrés

## Solutions

\newpage

# Gestion du projet

## Rôle des membres du groupe de développement

* Ludovic: Chef de projet, responsable de l'architecture
* Luca: Responsable du protocole applicatif et responsable de la communication réseau
* Abass: Expert Trello & documentation et responsable des définitions des modes de jeu
* Denise: Experte interface graphique et graphic designer

## Plan d'itérations initial

\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.3\textheight]{images/planIterInit.png}
  \captionof{figure}{Plan d'itérations initial}
\end{minipage}


## Suivi du projet

\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.5\textheight]{images/planIterFinal.png}
  \captionof{figure}{Plan d'itérations final}
\end{minipage}


### Itération 1
#### Bilan
Les objectifs prévus ont été réalisés.

#### Problèmes rencontrés
Pas de problèmes rencontrés.

#### Replanifications
Pas de replanification.

### Itération 2
#### Bilan
Les objectifs prévus ont été réalisés.

#### Problèmes rencontrés
Pas de problèmes rencontrés.

#### Replanifications
Pas de replanification.

### Itération 3
#### Bilan
L'essentiel du travail a été réalisé. Il reste quelques actions à finaliser du côté du protocole applicatif mais ça n'est pas bloquant.

#### Problèmes rencontrés
Pas de problèmes rencontrés.

#### Replanifications
Pas de replanification.

### Itération 4
#### Bilan
Les objectifs prévus ont été réalisés.

#### Problèmes rencontrés
Un membre de notre groupe est absent jusqu'à la fin du semestre. Au niveau de la conception, aucun problème n'a été rencontré.

#### Replanifications
De par l'absence d'Abass Madhavi, nous avons dû replanifier les itérations. Ainsi, l'itération 4 est devenue l'itération 6, l'itération 5 est devenue l'itération 4 et l'itération 6 est devenue l'itération 5.

### Itération 5
#### Bilan
Les objectifs prévus ont été réalisés.

#### Problèmes rencontrés
Pas de problèmes rencontrés.

#### Replanifications
Pas de replanification.

### Itération 6
#### Bilan
Les objectifs prévus ont été réalisés.

#### Problèmes rencontrés
Pas de problèmes rencontrés.

#### Replanifications
Pas de replanification.

 \newpage

## Stratégie de tests

### Résultats des tests

\begin{minipage}{\linewidth}
  \centering
  \includegraphics[height=0.4\textheight]{images/tests.png}
  \captionof{figure}{Plan d'itérations final}
\end{minipage}


## Stratégie d'intégration du code de chaque participant
Nous avons toujours travaillé sur la branche *master*: chacun de nous a constamment testé son code avant de le pousser sur la branche. S'il y avait des cas de conflit, ceux-ci ont été gérés comme il le fallait. En conclusion, nous n'avons donc pas rencontré de problèmes avec GIT.

# Etat des lieux

## Ce qui fonctionne
Nous avons pu développer un prototype fonctionnel qui comporte encore quelques bogues. Il est plus réactif que l'idée de base. Toutes les fonctionnalités de base discutées dans les cas d'utilisation ont été développées. 

## Ce qu'il resterait à développer
De ce que nous avons défini comme fonctions principales, il ne reste rien de plus à développer. Cependant, nous pourrions développer comme pensé au début des variantes de jeu, comme par exemple, inverser le sens de la souris, devoir résoudre un labyrithe avant d'atteindre l'objet, etc.

\newpage

# Autocritique

## Implémentation
Au niveau de l'implémentation, tout s'est passé sans problèmes majeurs. Nous avons également assez rapidement pu nous rattraper après que nous ayons appris l'absence à long terme de l'un des membres du groupe. En effet, nous avons dû replanifier les itérations, ce qui ne nous a pas pris beaucoup de temps.

A un niveau plus technique, l'implémentation des entités est bonne, le code est clair et propre et la documentation et les commentaires ont été rédigés au mieux.

Par rapport à l'utilisation de l'interface de connexion au jeu, nous aurions pu rendre cela plus facile, plutôt que de devoir entrer des adresses IP, ce qui n'est pas évident pour des personnes ne s'y connaissant pas en informatique.

## Gestion de projet
La gestion du projet a principalement été faite par Ludovic Delafontaine, le chef de groupe. Ludovic a su donner des tâches à chacun des membres de façon à ce que personne ne soit inactif durant tout le long du projet. Cela a permis de suivre les itérations comme il le fallait et modifier les tâches de l'itération comme il le fallait sans que quelqu'un se retrouve de côté.
La replanification a été inattendue, mais a été réalisée assez rapidement en une discussion de moins d'une heure. La stratégie de replanification utilisée a été satisfaisante pour le reste de l'exécution du projet.
En outre, la communication au sein du groupe était bonne et tout le monde était à l'écoute des autres. Cela a permis une bonne ambiance au sein du groupe qui nous a encouragé à exécuter un beau et bon programme.

## Plan d'itération
Au niveau du plan d'itération initial, nous n'avons pas été très précis lors de l'élaboration, ce qui n'a pas posé de problèmes, vu que semaine après semaine, nous nous sommes attribués des tâches afin de pouvoir compléter l'itération au mieux. A la fin de chaque itération, nous avons pu obtenir un résultat fonctionnel.

De plus, suite à la replanification du plan d'itération, nous n'avons pas ressenti de difficultés majeures. Nous avons pu nous rattraper comme il le fallait par rapport au fait qu'un membre du groupe manquait. 

## Améliorations possibles
Prévoir un but un peu moins ambitieux nous aurait permis de nous concentrer sur des détails quant au produit que nous avons finalement réalisé. 
De par la charge de travail extérieure que nous avons eu durant le semestre, nous n'avons malheureusement pas pu nous voir autant que nous voulions en face à face. Cela est un point qui nous semble très important et que nous améliorerons quoiqu'il arrive dans le futur, en espérant qu'un semestre de cet acabit ne se reproduise plus.

\newpage

# Conclusion
Nous avons pu implémenter un jeu fonctionnel, qui graphiquement nous plaît. La facilité de connexion reste difficile, cependant, nous imaginons facilement pouvoir y jouer lors de soirées avec des amis, ce qui est d'ailleurs prévu! Le jeu est finalement bien plus réactif que prévu, ce qui est une bonne nouvelle pour nous. Nous pensions nous retrouver face à des problèmes majeurs quant à la concurrence.
Grâce à ce projet, nous avons pu lier plusieurs notions acquises dans d'autres cours, telles que la sérialisation, la concurrence, l'orienté-objet, la gestion de projet, la sécurité et la communication client-serveur. Nous avons donc finalement pu approfondir des sujets importants pour ce semestre.
Nous pensons également que ce cours pourra nous être utile dans le futur, dans le cas de la gestion de projet et plus exactement dans une situation de stress comme celle que nous avons vécue ce semestre.