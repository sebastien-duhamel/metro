package fr.sebd.metro.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import fr.sebd.metro.VariablesGlobale;
import fr.sebd.metro.bo.Trajet;

public class CalculTrajet implements VariablesGlobale {


    private List<String> lignesDepart = new ArrayList<>();
    private List<String> lignesArrivee = new ArrayList<>();


    public CalculTrajet() {
        super();

    }

    /**
     * Methode permattant d'obtenir la station de départ et la station d'arrivée
     *
     * @return une instance de la classe trajet
     */
    public Trajet acquisitionParcours() {

        Trajet trajet = new Trajet(null, null, null, null, 0, 0, 0, 0);
        Scanner scanner = new Scanner(System.in);

        while (lignesDepart.isEmpty() || lignesArrivee.isEmpty()) {

            // on demande la ligne et la station de départ
            System.out.print("Saisir la station de départ : ");
            trajet.setStationDepart(scanner.nextLine().toUpperCase());

            // on demande la ligne et la station d'arrivée
            System.out.print("Saisir la station d'arrivée : ");
            trajet.setStationArrivee(scanner.nextLine().toUpperCase());

            System.out.println();

            lignesDepart = findLinesStartOrStop(trajet.getStationDepart());

            lignesArrivee = findLinesStartOrStop(trajet.getStationArrivee());

            if( lignesDepart.size()>1 || lignesArrivee.size()>1){
                for ( String ligneDepart : lignesDepart){
                    for ( String ligneArrivee : lignesArrivee){
                        if ( ligneDepart.equals(ligneArrivee)) {
                            lignesDepart.clear();
                            lignesArrivee.clear();
                            lignesDepart.add(ligneDepart);
                            lignesArrivee.add(ligneArrivee);
                            break;
                        }
                    }
                }
            }

            System.out.println("La station " + trajet.getStationDepart() + " correspond " + (lignesDepart.size() > 1? "aux lignes : " : " à la ligne : ") + lignesDepart);
            System.out.println("La station " + trajet.getStationArrivee() + " correspond " + (lignesDepart.size() > 1? "aux lignes : " : " à la ligne : ") + lignesArrivee);
            System.out.println();

            if (lignesDepart.isEmpty() || lignesArrivee.isEmpty()) {
                System.out.println("Veuillez entrer des stations qui existent");
            }

        }
        scanner.close();
        return trajet;
    }

    /**
     * méthode permettant de definir la/les ligne de départ/arrivée possible(s)
     *
     * @param station
     * @return Liste de lignes possible
     */
    private List<String> findLinesStartOrStop(String station) {

        List<String> lignes = new ArrayList<>();

        for (Entry<String, String[]> entry : LIGNES_METRO.entrySet()) {
            String ligne = entry.getKey();
//           System.out.println("entry.getKey()" + entry.getKey() );
            String[] stations = entry.getValue();
//            System.out.println("entry.getValue()" + entry.getValue().length );
            for (String s : stations) {
                if (s.equals(station)) {
                    lignes.add(ligne);
                    break;
                }
            }
        }
        return lignes;

    }


    public Trajet trouverParcours(Trajet trajet) {
        //
        if (lignesDepart.size() == 1) {
            trajet.setLigneDepart(lignesDepart.get(0));
        }

        if (lignesArrivee.size() == 1) {
            trajet.setLigneArrivee(lignesArrivee.get(0));
        }



        //verification si le trajet est sur la même ligne
        if (trajet.getLigneDepart() != null && trajet.getLigneArrivee() != null && trajet.getLigneDepart().equals(trajet.getLigneArrivee())) {

            if (trajet.getStationDepart().equals(trajet.getStationArrivee())) {
                System.out.println("vous êtes déja dans la station de destination");
            } else {

                trajet.setNombreStationParcours(parcoursMemeLigne(trajet.getLigneDepart(), trajet.getStationDepart(), trajet.getStationArrivee()));
                System.out.println("Vous allez parcourir " + trajet.getNombreStationParcours() + " station(s)");

            }
        }

        //Si le parcours inclu un changement de ligne
        else {
            // trouver la ou les gares communes
            List<String> intersectionLigneDepart = STATION_LIGNES_COMMUNES.get(trajet.getLigneDepart());
            List<String> intersectionLigneArrivee = STATION_LIGNES_COMMUNES.get(trajet.getLigneArrivee());
            String stationCommune = "";

            //les lignes se croisent?
            if (intersectionLigneDepart != null || intersectionLigneArrivee != null) {
                for (String intersectionDepart : intersectionLigneDepart) {
                    for (String intersectionArrivee : intersectionLigneArrivee) {
                        if (intersectionDepart.equals(intersectionArrivee)) {
                            stationCommune = intersectionDepart;
                            break;
                        }
                    }
                }
            }

            //les lignes se croisent
            if (!stationCommune.equals("")) {

                trajet.setNombreStationParcours(parcoursMemeLigne(trajet.getLigneDepart(), trajet.getStationDepart(), stationCommune));
                trajet.setNombreChangementLignes(1);
                trajet.setNombreStationParcours(trajet.getNombreStationParcours() + parcoursMemeLigne(trajet.getLigneArrivee(), stationCommune, trajet.getStationArrivee()));

                //les lignes ne se croisent pas
            } else {

                System.out.println("pas de ligne commune\n");
                System.out.println("intersection ligne de depart : " + intersectionLigneDepart);
                System.out.println("intersection ligne d'arrivée : " + intersectionLigneArrivee + "\n");


                multipleChemin(trajet);


            }

        }

 //       System.out.println(trajet);
        return trajet;
    }

    ;

    /**
     * Methode permettant de calculer le nombre de stations parcourues lorsque
     * la station de départ et d'arrivée se trouvent sur la meme ligne
     *
     * @param ligne
     * @param stationDepart
     * @param stationArrivee
     * @return int nombreStationParcourue
     */
    private int parcoursMemeLigne(String ligne, String stationDepart, String stationArrivee) {
        int nombreStationParcourue ;
        int positionPointDepart = 0;
        int positionPointArrivee = 0;


        for (int i = 0; i < LIGNES_METRO.get(ligne).length; i++) {
            if (stationDepart.equals(LIGNES_METRO.get(ligne)[i])) {
                positionPointDepart = i + 1;
                break;
            }
        }
        ;

        for (int j = 0; j < LIGNES_METRO.get(ligne).length; j++) {
            if (stationArrivee.equals(LIGNES_METRO.get(ligne)[j])) {
                positionPointArrivee = j + 1;
                break;
            }
        }

        if (positionPointDepart < positionPointArrivee)
            nombreStationParcourue = positionPointArrivee - positionPointDepart;
        else nombreStationParcourue = positionPointDepart - positionPointArrivee;

        return nombreStationParcourue;

    }

    private void multipleChemin(Trajet trajet) {
        int[] chemins;
        List<String> lignesEmprunte = new ArrayList<>();
        List<String> lignesPossiblesParNoeud = new ArrayList<>();
        String noeudDepart = "JAVA";
        String noeudArrivee = "PYTHON";
        String curseur = "";
        String depart = noeudDepart;
        String ancienNoeud = "";

        //definir les noeuds de départ et d'arrivée

        //construction d'un tableau de ligne
        for (Entry<String, String[]> entry : LIGNES_METRO.entrySet()) {
            String ligne = entry.getKey();

            lignesEmprunte.add(ligne);
        }
        System.out.println("noeud de départ : " + noeudDepart);
        System.out.println("noeud d'arrivee : " + noeudArrivee);
        List<String> noeuds = NOEUDS.get(depart);
        List<String> noeudsSuivant ;
        System.out.println("noeuds commun au depart, je peux aller du noeud de départ : " + noeudDepart + " vers les noeuds :" + noeuds +
                " par les lignes " + lignesDepart);


        for (String noeud : noeuds) {
            System.out.println("*****************pour le noeud**************** : " + noeud);
            // exemple je dois trouver la ligne qui contient le noeudDepart java et le premier noeud PHP je connais la ligne de depart
            int nombresStations = 0;
            String noeudAdjacent;

            for (String ligne : lignesDepart) {
                System.out.println("sur la ligne " + ligne);

                if (STATION_LIGNES_COMMUNES.get(ligne).contains(noeudDepart) && STATION_LIGNES_COMMUNES.get(ligne).contains(noeud)) {
                    nombresStations= parcoursMemeLigne(ligne, depart, noeud);
                    System.out.println(nombresStations);
                    noeudAdjacent = noeud;
                    System.out.println("voici le noeud adjacent "+ noeudAdjacent);
                    ancienNoeud = depart;
                    //depart = noeud;
                }
            }

        }

    }

}