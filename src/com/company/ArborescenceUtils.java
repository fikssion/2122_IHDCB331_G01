package com.company;


import java.util.ArrayList;

/**
 * Classe utilitaire pour la création, manipulation et autres traitements sur une arborescence
 */
public class ArborescenceUtils {

    /**
     * Constructeur privé pour ne pas pouvoir l'instancier
     */
    private ArborescenceUtils(){}

    /**
     *
     * @param str
     * @param prise
     * @return
     */

    public static Arborescence treeBuilder(String str, int prise){

        //On clean le string en enlevant tous les espaces inutiles
        String escapedStr = str.replaceAll("\\s+", "");

        Arborescence arborescence = treeBuilder(escapedStr, null, null);

        int cptNoeud=0;
        for(int i = prise ; i>=0 ;i--){
            ArrayList <Noeud> arrayList = arborescence.moveParPosition(i, new ArrayList(), arborescence.getRacine(), prise);

            for(Noeud nTemp: arrayList){
                System.out.println(" ---- " + nTemp.getId());
                cptNoeud++;
            }
        }
        System.out.println("Nombre de solution : " +cptNoeud/prise);

        return arborescence;
    }

    private static Arborescence treeBuilder(String str, Noeud currentNode, Noeud root){

        if(str.length() < 1)
            return new Arborescence(root);
        else {
            if(str.substring(0,1).equals("}")){
                currentNode = currentNode.getParent();

            }else{
                try{
                    int parsedInt = Integer.parseInt(str.substring(0, 1));
                    Noeud foundNode = new Noeud(currentNode, parsedInt);

                    //Si currentNode est null, ca veut dire qu'on a la racine et qu'on va la sauver pour la passer en argument
                    if(currentNode == null){
                        root = foundNode;
                    }
                    // Sinon on a trouvé un nouveau noeud qu'on ajoute au parent
                    else{
                        currentNode.addChild(foundNode);
                    }

                    // On check si le token suivant est une virgule juste après un int, ce qui signifie qu'on descend d'un niveau
                    if(str.substring(1, 2).equals(",")){
                        currentNode = foundNode;
                    }
                }catch(NumberFormatException nfe){
                    //do nothing
                }
            }

            return treeBuilder(str.substring(1), currentNode, root);
        }
    }
}
