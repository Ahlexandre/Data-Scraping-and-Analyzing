package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.Double;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

// Cette classe effectue diverses analyses statistiques sur une liste d'objets de type Item
public final class ItemAnalyzer {

  // La liste d'objets Item à analyser
  private ArrayList<Item> items = new ArrayList<>();

  // Constructeur permettant d'initialiser la liste d'items
  public ItemAnalyzer(ArrayList<Item> items) {
    this.items=items;
  }

  // Méthode pour calculer la moyenne du temps de jeu par match
  public Double getMoyenne_temps_jeu() {
    double sommeTempsJeu = 0.0;

    // Parcourt la liste d'items
    for (Item item : items) {
      // Ajoute le temps de jeu de chaque joueur s'il est n'est pas null
      if (item.getNb_minute_jouer_par_game() != null) {
        sommeTempsJeu += item.getNb_minute_jouer_par_game();
      }
    }

    // Calcule et retourne un double : la moyenne
    return sommeTempsJeu / items.size();
  }

  // Méthode pour trouver l'âge qui joue le plus de matchs
  public Double getAge_jouant_le_plus_game() {

    // Utiliser d'une map pour stocker le nombre de jeux par âge
    Map<Double, Integer> ageJeuxCount = new HashMap<>();

    // Parcourt la liste d'items
    for (Item item : items) {
      // On récupère l'âge pour chacun
      Double age = item.getAge();
      // On récupère le nombre de game jouer pour chacun
      Integer nbJeuxJoues = item.getNb_game_jouer();
      // On vérifie si l'âge et le nombre de jeux non null et positif
      if (age != null && nbJeuxJoues != null && nbJeuxJoues > 0) {
        // Maj du dico
        ageJeuxCount.put(age, ageJeuxCount.getOrDefault(age, 0) + nbJeuxJoues);
      }
    }
    // On va alors chercher le max dans notre dico(map)
    Double ageMaxJeux = null;
    int maxJeux = 0;

    for (Map.Entry<Double, Integer> entry : ageJeuxCount.entrySet()) {// Parcourir la carte pour trouver l'âge avec le nombre de jeux le plus élevé
      if (entry.getValue() > maxJeux) {
        // On prend la clé et sa valeur qu'on va mettre à jour si la condition if est verifiée
        maxJeux = entry.getValue();
        ageMaxJeux = entry.getKey();
      }
    }

    // On retourne une double : l'âge
    return ageMaxJeux;
  }


  // Méthode pour trouver les joueurs polyvalents en fonction de certains critères
  public List<List<String>> getPolyvalence() {
    List<List<String>> polyvalentPlayers = new ArrayList<>();

    // Parcourt la liste d'items pour trouver les joueurs polyvalents
    for (Item item : items) {
      if (item.getPoint_par_game() != null && item.getAssist_par_game() != null && item.getRebond_par_game() != null) {
        // Vérifie si le joueur répond aux critères de polyvalence :
        // - Marque + de 5 points
        // - Fais + de 2 assists
        // - Fais + de 2 rebonds
        if (item.getPoint_par_game() > 5 && item.getAssist_par_game() > 2 && item.getRebond_par_game() > 2) {
          // Création d'une liste
          ArrayList<String> playerInfo = new ArrayList<>();
          // Ajout du joueur polyvalent dans la liste
          playerInfo.add(item.getNom_joueur());
          // Ajout de la liste dans la liste polyvalentPlayers
          polyvalentPlayers.add(playerInfo);
        }
      }
    }

    // Retourne la liste de liste de chaque joueur polyvalent.
    return polyvalentPlayers;
  }

  // Méthode pour calculer la corrélation entre les points par partie et les tirs à trois points
  // via la formule de corrélation de Pearson (formule dans le pdf présentation)
  public Double getCorrelation_pts_par_game_trois_points() {
    // Initialisation des variables pour le calcul de la corrélation
    double sumPtsParGame = 0.0;
    double sumTroisPoints = 0.0;
    double sumPtsParGameTroisPoints = 0.0;
    double sumPtsParGameSquared = 0.0;
    double sumTroisPointsSquared = 0.0;

    // Parcourt la liste d'items pour calculer les sommes nécessaires à la corrélation
    for (Item item : items) {
      if (item.getPoint_par_game() != null && item.getTrois_points() != null) {
        double ptsParGame = item.getPoint_par_game();
        double troisPoints = item.getTrois_points();

        // Mises à jour des sommes
        sumPtsParGame += ptsParGame;
        sumTroisPoints += troisPoints;
        sumPtsParGameTroisPoints += ptsParGame * troisPoints;
        sumPtsParGameSquared += Math.pow(ptsParGame, 2);
        sumTroisPointsSquared += Math.pow(troisPoints, 2);
      }
    }

    // Calcul de la corrélation  (Formule de corrélation de Pearson)
    //Nombre total d'items
    int n = items.size();
    double numerator = (n * sumPtsParGameTroisPoints) - (sumPtsParGame * sumTroisPoints);
    double denominator1 = Math.sqrt((n * sumPtsParGameSquared) - Math.pow(sumPtsParGame, 2));
    double denominator2 = Math.sqrt((n * sumTroisPointsSquared) - Math.pow(sumTroisPoints, 2));

    // Retourne un double, valeur comprise entre 1 et -1
    return numerator / (denominator1 * denominator2);
  }

  // Méthode pour calculer la régression entre les steals et blocks par match
  // via la formule de régression (formule dans le pdf présentation)
  public String getRegression_steal_block_par_game() {

    // Initialisation des variables pour le calcul de la régression
    double sumBlocks = 0.0;
    double sumSteals = 0.0;
    double sumBlockscarre = 0.0;
    double sumBlocksSteals = 0.0;
    for (Item item : items) {

      // On récupère si les block et steals sont non null
      if (item.getSteal_par_game() != null && item.getBlock_par_game() != null) {
        double blocks = item.getBlock_par_game();
        double steals = item.getSteal_par_game();

        // Mises à jour des sommes
        sumBlocks += blocks;
        sumSteals += steals;
        sumBlockscarre += Math.pow(blocks, 2);
        sumBlocksSteals += blocks * steals;
      }
    }
    // Calcul de la régression (Formule de régression)
    int n = items.size();
    double a = (n * sumBlocksSteals - sumBlocks * sumSteals) / (n * sumBlockscarre - Math.pow(sumBlocks, 2));// on applique la formule de la régression
    double b = (sumSteals - a * sumBlocks) / n;

    // On retourne l'équation de la régression
    return a + " X + " + b;
  }

}
