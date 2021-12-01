package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// pour partir d'une arborescence qui reprendra tout les noeuds en commencant par la racine.
public class Arborescence {
    private Noeud racine;
    private int taille=0;


    //Constructeur d'arbre.
    public Arborescence(Noeud racine) {
        this.racine = racine;
    }
    // Getter pour lire la taille de l'arborescence
    public int getTaille() {
        return taille;
    }

    public Noeud getRacine() {
        return racine;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setRacine(Noeud racine) {
        this.racine = racine;
    }

    // Ajouter un noeud à un autre noeud (ajout d'un enfant au noeud parent)
    public Noeud addNode(Noeud nParent, int id){
        Noeud nTemp = new Noeud(nParent,id);
        nParent.addChild(nTemp);

        //calcul taille : délocalisé, pour connaitre à chaque moment la taille de l'arbre lors d'un ajoute d'un oeud
        this.taille = calculTaille(nTemp, 0); //Hardcodé la taille. ou passer par une méthode qui définie la taille = 0.

        return nTemp;
    }

    // Ajouter un noeud à un autre noeud (ajout d'un enfant au noeud parent)
    public Noeud addNode(Noeud nParent, Noeud nEnfant){

        nParent.addChild(nEnfant);

        //calcul taille : délocalisé, pour connaitre à chaque moment la taille de l'arbre lors d'un ajoute d'un oeud
        this.taille = calculTaille(nEnfant, 0); //Hardcodé la taille. ou passer par une méthode qui définie la taille = 0.

        return nEnfant;
    }

    //"private" pour que la méthode ne soit jamais utilisée en dehors du contexte de la classe Arborescence
    private int calculTaille(Noeud n, int taille){

        if(n.getParent()== null){
            if(taille>this.taille)
                return taille;
            else
                return this.taille;
        }
        else{
            taille++;
            return calculTaille(n.getParent(), taille);
        }
    }

    /**
     * Prend en paramètre un noeud, le nombre de déplacement dans l'arbre, une arraylist pour stocker les noeuds ou
     * les chemins sont valides, et un nombre fixe de déplacement
     * @param n
     * @param trajets
     * @param nbrDeplac
     * @return
     */
    public ArrayList move (Noeud n, ArrayList<Noeud> trajets, int nbrDeplac){

        if(nbrDeplac>=this.taille){ //condition au cas ou les déplacements sont supérieur à la taille de l'arbre.

            if(n.isUsed()){
                trajets.clear(); // réinitialise l'arraylist si un chemin n'est pas valable.
                return new ArrayList();
            }

            else if(n.getParent()==null){ // condition pour vérifier si on est au noeud racine
                nbrDeplac--;

                if(nbrDeplac >0){ // s'il reste des déplacements, le chemin est impossible
                    return new ArrayList();
                }
                else { //sinon on ajoute le noeud racine à au trajet et on valide le chemin
                    if(nbrDeplac==0){
                        trajets.add((n));
                        n.setUsed(true);
                        return trajets;
                    }
                }
            }
            else {
                if(nbrDeplac>0){
                    trajets.add(n);
                    n.setUsed(true);
                    Noeud n_temp = n.getParent();
                    move(n_temp, trajets, --nbrDeplac);
                }
            }
        }else return new ArrayList();

        return trajets;
    }

    /**
     * Cette méthode renvoie tous les chemins possibles pour le Noeud de position @position dans l'arbre
     * @param position
     * @param noeuds
     * @param n
     * @param nbrDeplac
     * @return
     */
    public ArrayList<Noeud> moveParPosition(int position, ArrayList<Noeud> noeuds, Noeud n, int nbrDeplac){

        if(n.getPosition()==position){
            ArrayList<Noeud> move = new ArrayList<>(move(n, new ArrayList<Noeud>(), nbrDeplac));
            if(move.size()==nbrDeplac){
                noeuds.addAll(move);
            }

        }else{
            for(Noeud nTemp : n.getChildren()){
                moveParPosition(position, noeuds, nTemp,nbrDeplac);
            }
        }

        return noeuds;
    }

    public String printTree(){
        StringBuilder str = new StringBuilder();
        str.append(printTree(new StringBuilder(), this.racine));
        str.append("Taille de l'arborescence : " + this.taille);
        return str.toString();
    }

    private StringBuilder printTree(StringBuilder str, Noeud noeud){
        str.append(noeud.toString()).append("\n");
        for(Noeud nTemp : noeud.getChildren()){
            printTree(str,nTemp);
        }

        return str;
    }

    public static String readTestFile(){
        String data = "";
        try {
            String path = "G:\\My Drive\\NGen\\Projects\\arborescence\\src\\arbres.txt";
            path = path.replace("\\", "/");
            File fichier = new File(path);
            Scanner myReader = new Scanner(fichier);

            int cptLigne=1;

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
                return data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

}
