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


# Analyse (Denise)

## Règles du jeu
Le but du jeu est de cliquer le plus vite possible sur des images qui apparaîtront à la suite dans la surface interactive de jeu. 
Plusieurs modes de jeu à choix seront disponibles 
Les images seront des bonus, des malus ou des images mystère (certaines fois bonus, certaines fois malus). Les images rapportent ou font perdre le même nombre de points.

## Partage de responsabilités
** A COPIER **

### Serveur
** A COPIER fonctionnalités **

### Client
** A COPIER fonctionnalités **

## Diagramme d'activité (Denise)

## Cas d'utilisation
** A COPIER **

### Diagramme général de contexte
** IMAGE A COPIER **

### Description des acteurs (Luca)

### Scénarios par cas d'utilisation
** A COPIER depuis Trello **

### Scénarios alternatifs significatifs (Denise)
(Pseudo déjà utilisé ou jeu plein)

## Modèle de domaine (Ludo)
** A COPIER + modifier **

### Client

### Serveur

## Base de données

### Modèle conceptuel ou structure (XML, Json)
** A COPIER **

#### Commentaires


# Conception

## Protocole d'échange
** A COPIER **

### Serveur (Denise)

#### Commentaires

### Client (Denise)

#### Commentaires

## Base de données (Denise)

### Modèle conceptuel (Denise)
(une table sans types)
SCORES
id autogénéré int
player : String
mode : String
score : int
date : Date


# implémentation (Luca)

## Problèmes rencontrés


# Gestion du projet

## Rôle des membres du groupe de développement
** A COPIER **

## Plan d'itérations initial
** A COPIER depuis Trello **
Pour chaque itération :
- Objectifs
- Durée, dates
- Qui fait quoi
- Charge de travail estimée, en heures

## Suivi du projet
** A COPIER depuis Trello **
Pour chaque itération :
- Bilan
- Problèmes rencontrés
- Replanifications

### Synthèse Trello (Denise)

## Stratégie de tests (Ludo/Denise)

### Résultats des tests (Ludo/Denise)

## Stratégie d'intégration du code de chaque participant (Git) (Denise)


# Etat des lieux (Ludo/Denise)

## Ce qui fonctionne
(résultats des tests)

## Ce qu'il resterait à développer
(proposer une planification)


# Auto-critique (Ludo/Denise/Luca)

## Implémentation

## Gestion de projet

## Plan d'itération

## Améliorations possibles


# Conclusion (Ludo/Denise/Luca)