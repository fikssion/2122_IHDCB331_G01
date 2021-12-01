package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Escalade {


    private Escalade() {
    }

    public static int firstData(String firstInt){
        try{
            int intOK = Integer.parseInt(firstInt);
            return intOK;

        }catch (java.lang.NumberFormatException e ){
            return 0;
        }
    }

    /**
     * lecture du fichier texte.
     */
    public static void readTestFile(){
        try {
            File myObj = new File("C:\\Users\\JEREM\\IdeaProjects\\Grimpeur\\Tuple.txt");
            Scanner myReader = new Scanner(myObj);
            int nbrTest = firstData(myReader.next());
            int i =1;

            int nbrNoeudEnonce = 0; //init
            int nbrPrise = 0; //init

            myReader.nextLine();                            //élément vide qui fait tampon pour atteindre et assigner les valeurs utiles seulement

            while(i<=nbrTest){
                if(myReader.hasNext()) {//s'il y a bien des éléments dans le reste du fichier

                    System.out.println("--------------- Cas de test = "+i);
                    //Decoupe du String afin de s'assurer que chaque morceau est valide

                    String n =  myReader.nextLine();
                    Scanner read = new Scanner(n);
                    //Nombre de noeuds et prises
                    String next1 = read.next();
                    String next2 = read.next();
                    nbrNoeudEnonce = firstData(next1);//assignation et test
                    nbrPrise = firstData(next2); //assignation et test

                    System.out.println("n = "+nbrNoeudEnonce+ " & k = "+nbrPrise );//affichage pour que l'utilisateur comprenne que ça fonctionne

                    String tuple = myReader.nextLine();
                    System.out.println("tuple =  "+tuple);

                    Arborescence arborescence = ArborescenceUtils.treeBuilder(tuple,nbrPrise);

                    // Vérifie qu'il y ait bien une ligne suivante après le tuple, si c'est le cas, on la passe car ligne vide.
                    if(myReader.hasNextLine()){
                        myReader.nextLine();
                    }
                    i++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
