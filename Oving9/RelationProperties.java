package Oving9;

import java.util.ArrayList;

/**
 * Kode gitt til øvingen, med tomme metoder som har blitt fylt ut
 */

public class RelationProperties {
    /*
     * Assuming that a two column array containing the relation and a one column          * array containing the set the relation is on is given in each method.
     * No checks are performed.
     */


    public boolean isReflexive(char[][] relation, char[] set){
        int teller = 0; //Teller for å sjekke om det er likt antall
        for (int i = 0; i < set.length; i++) { //
            for (int j = 0; j < relation.length; j++) {
                char[] currentRel = relation[j];
                if (currentRel[0] == set[i] && currentRel[0] == currentRel[1]) {
                    teller++;
                    j = relation.length;
                }
            }
        }
        if (teller == set.length) {
            return true;
        }
        return false;
    }

    private boolean symmetricRelations(char[] rel1, char[] rel2) {
        if (rel1[0] == rel2[1] && rel1[1] == rel2[0]){
            return true;
        }
        return false;
    }

    public boolean isSymmetric(char[][] relation, char[] set){
        ArrayList<char[]> chars = new ArrayList<char[]>();
        for (int i = 0; i < relation.length; i++) {
            if (relation[i][0] != relation[i][1]) {
                chars.add(relation[i]);
            }
        }
        if (chars.size()%2 == 0) {
            int teller = 0;
            for (int i = 0; i < chars.size(); i++) {
                char[] currentRel = null;
                for (int j = i; j < chars.size(); j++) {
                    if (currentRel == null) {
                        currentRel = chars.get(i);
                    } else {
                        if (symmetricRelations(currentRel, chars.get(j))) {
                            teller++;
                        }
                    }
                }
            }

            if (teller == (chars.size()/2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTransitive(char[][] relation, char[] set){
        boolean transitive = false;
        for (int i = 0; i < relation.length; i++) {
            for (int j = 0; j < relation.length; j++) {
                // (a, b)  (b, c)  (a, c)
                char[] a = relation[i];// (a, b) = (a[0], a[1])
                char[] b = relation[j];// (b, c) = (b[0], b[1])

                if (a[1] == b[0]) {
                    for (int k = 0; k < relation.length; k++) {
                        if (a[0] == relation[k][0] && b[1] == relation[k][1]) {
                            transitive = true;
                        }
                    }
                    if (!transitive) {
                        return false;
                    }
                }
                transitive = false;
            }
        }
        return true;
    }

    public boolean isAntiSymmetric(char[][] relation, char[] set){
        for (int i = 0; i < relation.length; i++) {
            if (relation[i][0] != relation[i][1]) {
                for (int j = 0; j < relation.length; j++) {
                    if (relation[i][0] == relation[j][1] && relation[i][1] == relation[j][0]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isEquivalenceRelation(char[][] relation, char[] set){
        return isReflexive(relation, set) && isSymmetric(relation, set) && isTransitive(relation, set);
    }

    public boolean isPartialOrder(char[][] relation, char[] set){
        return isReflexive(relation, set) && isAntiSymmetric(relation, set) && isTransitive(relation, set);
    }
        public static void main(String[] args) {

            RelationProperties relProp = new RelationProperties();

            char[] setA = {'a','x','r','m','2','0'};
            char[][] rel1 = {{'a','a'},{'r','a'},{'a','2'},{'x','x'},{'r','2'},{'r','r'},{'m','m'},{'2','r'},{'0','0'},{'a','r'},{'2','2'},{'2','a'}};
            char[][] rel2 = {{'a','x'},{'r','2'},{'0','0'},{'m','2'}};


            System.out.println("Rel1 is reflexive: " + relProp.isReflexive(rel1, setA));
            System.out.println("Rel2 is reflexive: " + relProp.isReflexive(rel2, setA));
            System.out.println("Rel1 is symmetric: " + relProp.isSymmetric(rel1, setA));
            System.out.println("Rel2 is symmetric: " + relProp.isSymmetric(rel2, setA));
            System.out.println("Rel1 is transitive: " + relProp.isTransitive(rel1, setA));
            System.out.println("Rel2 is transitive: " + relProp.isTransitive(rel2, setA));
            System.out.println("Rel1 is antisymmetric: " + relProp.isAntiSymmetric(rel1, setA));
            System.out.println("Rel2 is antisymmetric: " + relProp.isAntiSymmetric(rel2, setA));
            System.out.println("Rel1 is an equivalence relation: " + relProp.isEquivalenceRelation(rel1, setA));
            System.out.println("Rel2 is an equivalence relation: " + relProp.isEquivalenceRelation(rel2, setA));
            System.out.println("Rel1 is a partial order: " + relProp.isPartialOrder(rel1, setA));
            System.out.println("Rel2 is a partial order: " + relProp.isPartialOrder(rel2, setA));


        /* skal gi følgende utskrift:
           Rel1 is reflexive: true
           Rel2 is reflexive: false
           Rel1 is symmetric: true
           Rel2 is symmetric: false
           Rel1 is transitive: true
           Rel2 is transitive: true
           Rel1 is antisymmetric: false
           Rel2 is antisymmetric: true
           Rel1 is an equivalence relation: true
           Rel2 is an equivalence relation: false
           Rel1 is a partial order: false
           Rel2 is a partial order: false
        */
    }
}