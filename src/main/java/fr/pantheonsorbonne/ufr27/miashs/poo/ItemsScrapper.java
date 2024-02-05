
package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.util.ArrayList;

// Cette classe sert à extraire des informations d'une source HTML (scrapped0.txt)
public final class ItemsScrapper {

  // Méthode pour extraire une information spécifique d'une balise <td> en utilisant le nom de la colonne
  // prend en paramètre itemContent qui correspond à une ligne du tableau, et le nom de la colonne
  private String extraireInfo(String itemContent, String columnName) {
    // Un paterne "d'extraction" est visible lorsqu'on ouvre le document scrapped0.txt
    String startTag = "<td class=\"" + columnName + "\">";
    String endTag = "</td>";

    // Trouver l'indice de début de la balise <td>
    int startIndex = itemContent.indexOf(startTag);
    if (startIndex != -1) {
      startIndex += startTag.length();
      // Trouver l'indice de fin de la balise </td>
      int endIndex = itemContent.indexOf(endTag, startIndex);
      if (endIndex != -1) {
        // Retourne le contenu entre les balises <td> et </td>
        return itemContent.substring(startIndex, endIndex);
      }
    }
    // Retourner null si l'information n'a pas été trouvée
    return null;
  }

  // Méthode pour analyser la source HTML et extraire les informations de chaque joueur
  // pageSource étant la chaîne de caractère qui contient tout le document HTML
  ArrayList<Item> parseSource(String pageSource) {
    ArrayList<Item> itemList = new ArrayList<>();
    int startIndex = 0;
    int endIndex;

    while ((startIndex = pageSource.indexOf("<tr", startIndex)) != -1) {
      endIndex = pageSource.indexOf("</tr>", startIndex);
      if (endIndex != -1) {

        // Extraire le contenu de la balise <tr> (la ligne du tableau) pour un joueur
        String itemContent = pageSource.substring(startIndex, endIndex);

        // Extraire différentes informations du joueur à l'aide de la méthode extraireInfo (codé ci-dessous)
        // Utilisation des méthodes parseInt ou ParseDouble pour pouvoir convertir la chaîne en Int ou Double
        String nomJoueur = extraireInfo(itemContent, "column-2");
        Double age = parseDouble(extraireInfo(itemContent, "column-5"));
        Double pointsParGame = parseDouble(extraireInfo(itemContent, "column-18"));
        Double rebondsParGame = parseDouble(extraireInfo(itemContent, "column-19"));
        Double assistsParGame = parseDouble(extraireInfo(itemContent, "column-20"));
        Double stealsParGame = parseDouble(extraireInfo(itemContent, "column-21"));
        Double blocksParGame = parseDouble(extraireInfo(itemContent, "column-22"));
        String poste = extraireInfo(itemContent, "column-4");
        String equipe = extraireInfo(itemContent, "column-3");
        Integer nbMatchJoues = parseInt(extraireInfo(itemContent, "column-6"));
        Double minutesParJeu = parseDouble(extraireInfo(itemContent, "column-7"));
        Integer troisPoints = parseInt(extraireInfo(itemContent, "column-14"));


        // Créer un objet Item et définit ses propriétés
        Item item = new Item();
        item.setNom_joueur(nomJoueur);
        item.setAge(age);
        item.setPoint_par_game(pointsParGame);
        item.setRebond_par_game(rebondsParGame);
        item.setAssist_par_game(assistsParGame);
        item.setSteal_par_game(stealsParGame);
        item.setBlock_par_game(blocksParGame);
        item.setPoste(poste);
        item.setTeam(equipe);
        item.setNb_game_jouer(nbMatchJoues);
        item.setNb_minute_jouer_par_game(minutesParJeu);
        item.setTrois_points(troisPoints);

        // Ajouter l'objet Item à la liste des éléments
        itemList.add(item);

        // Mise à jour de l'indice de départ pour la prochaine itération (prochain joueur)
        startIndex = endIndex + 1;
      }
    }
    // Retourner la liste d'objets Item contenant les informations extraites
    return itemList;
  }

  // Création de deux méthodes permettant de gérer les exceptions car les valeurs de la première ligne du tableau ne sont
  //pas de valeurs numériques
  // Méthode pour convertir une chaîne en Double en gérant les exceptions
  private Double parseDouble(String value) {
    try {
      // Si la valeur n'est pas null alors elle convertit la chaîne de caractère en Double sinon renvoie null
      return value != null ? Double.parseDouble(value) : null;
    } catch (NumberFormatException e) {
      // En cas d'erreur de type : NumberFormatException, elle renvoie null
      return null;
    }
  }
  // Méthode pour convertir une chaîne en Integer en gérant les exceptions
  private Integer parseInt(String value) {
    try {
      // Si la valeur n'est pas null alors elle convertit la chaîne de caractère en Int sinon renvoie null
      return value != null ? Integer.parseInt(value) : null;
    } catch (NumberFormatException e) {
      // En cas d'erreur de type : NumberFormatException, elle renvoie null
      return null;
    }
  }
}
