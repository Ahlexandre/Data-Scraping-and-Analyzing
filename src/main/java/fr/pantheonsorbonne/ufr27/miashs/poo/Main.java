package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Application principale qui utilise le scraping d'informations sur les joueurs de basketball,
 * analyse les données extraites et affiche les attributs de chaque item ainsi que les résultats de l'analyse.
 *
 * Auteur : DU Alexandre et JELASSI Bader
 *
 * Annexes : Il y a un fichier pdf dans le zip pour présenter le code
 */

public class Main {
    public static void main(String ...args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        ItemsScrapper scrapper  = new ItemsScrapper();
        try{
            ArrayList<Item> items = scrapper.parseSource(ContentProxy.getCached());
            System.out.println("Les Attributs de chaque Item (" + items.size()+")");
            for(int i=0 ; i<items.size(); i++){
                System.out.println("Item " + i);
                for(Field field : Item.class.getDeclaredFields()){
                    field.setAccessible(true);
                    System.out.println(field.getName()+": "+Objects.toString(field.get(items.get(i))));
                }
                System.out.println("------------");
            }

            System.out.println("=======================");
            System.out.println("L'analyse des données collectées");
            ItemAnalyzer itemAnalyzer = new ItemAnalyzer(items);
            for(Method method : ItemAnalyzer.class.getDeclaredMethods()){
                System.out.println(method.getName()+ ": " + Objects.toString(method.invoke(itemAnalyzer)));
            }
        }
        catch(IOException e){
            System.out.println("un problème est survenu lors de la récupération du contenu fresh, utilisez le cache ou réessayez plus tard");
        }
    }

}