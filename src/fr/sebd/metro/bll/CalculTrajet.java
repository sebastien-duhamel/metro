package fr.sebd.metro.bll;

import java.util.List;
import java.util.Scanner;

import fr.sebd.metro.VariablesGlobale;
import fr.sebd.metro.bo.Trajet;

public class CalculTrajet implements VariablesGlobale{



    public CalculTrajet() {
        super();

    }

    public Trajet aquisitionParcours(){

        Trajet trajet = new Trajet(null, null, null, null, 0, 0, 0, 0);
        Scanner scanner = new Scanner(System.in);

        while( verif(trajet.getLigneDepart(), trajet.getStationDepart()) || verif(trajet.getLigneArrivee(), trajet.getStationArrivee())) {

            // on demande la ligne et la station de départ
            System.out.print( "Veuillez entrer la ligne de départ : " );
            trajet.setLigneDepart( scanner.nextLine().toUpperCase() );
            System.out.print( "Veuillez entrer la station de départ : " );
            trajet.setStationDepart( scanner.nextLine().toUpperCase() );

            // on demande la ligne et la station d'arrivée
            System.out.print( "Veuillez entrer la ligne d'arrivée : " );
            trajet.setLigneArrivee( scanner.nextLine().toUpperCase() );
            System.out.print( "Veuillez entrer la station d'arrivée : " );
            trajet.setStationArrivee( scanner.nextLine().toUpperCase() );

            System.out.println("");

            if ( verif(trajet.getLigneDepart(), trajet.getStationDepart()) || verif(trajet.getLigneArrivee(), trajet.getStationArrivee())) {
                System.out.println("Veuillez entrez des lignes et stations qui existent");
            }

        }


        scanner.close();

        return trajet;
    }

    public boolean verif(String ligne, String station) {
        boolean ligneOK = false;
        boolean stationOK = false;
        System.out.println(ligne + "  " + station);

        if (LIGNES_METRO.containsKey(ligne)) {
            System.out.println("la ligne existe : " + ligne);
            ligneOK = true;

            for (int i = 0; i < LIGNES_METRO.get(ligne).length; i++) {
                System.out.println("boucle for : " + LIGNES_METRO.get(ligne)[i]);
                if ( LIGNES_METRO.get(ligne)[i].equals(station)) {
                    System.out.println("dans le if : " + LIGNES_METRO.get(ligne)[i].equals(station));
                    stationOK = true;
                    break;
                }
            }


        }

        return !ligneOK || !stationOK;
    }

    public Trajet trouverParcours(Trajet trajet) {


        //verification si le trajet est sur la même ligne
        if ( trajet.getLigneDepart().equals(trajet.getLigneArrivee())) {

            if ( trajet.getStationDepart().equals(trajet.getStationArrivee())) {
                System.out.println("vous êtes déja dans la station de destination");
            }else {
                int nombreStationParcourue ;
                int positionStationDepart = 0;
                int positionStationArrivee = 0;

                for (int i = 0; i < LIGNES_METRO.get(trajet.getLigneDepart()).length; i++) {
                    if ( trajet.getStationDepart().equals(LIGNES_METRO.get(trajet.getLigneDepart())[i])) {
                        System.out.println(i);
                        positionStationDepart = i+1;
                        break;
                    }
                }

                for (int j = 0; j < LIGNES_METRO.get(trajet.getLigneArrivee()).length; j++) {
                    if ( trajet.getStationArrivee().equals(LIGNES_METRO.get(trajet.getLigneArrivee())[j])) {
                        System.out.println(j);
                        positionStationArrivee = j+1;
                        break;
                    }
                }

                if ( positionStationDepart < positionStationArrivee ) nombreStationParcourue = positionStationArrivee - positionStationDepart;
                else nombreStationParcourue = positionStationDepart - positionStationArrivee;

                System.out.println("Vous allez parcourir " + nombreStationParcourue + " station(s)");
            }
        }

        //Si le parcours est inclu un changement de ligne
        else {
            // trouver la ou les gares communes
            List<String> intersectionLigneDepart =  STATION_LIGNES_COMMUNES.get(trajet.getLigneDepart()) ;
            List<String> intersectionLigneArrivee =  STATION_LIGNES_COMMUNES.get(trajet.getLigneArrivee()) ;
            String stationCommune = "";

            //les lignes se croisent?
            for (String intersectionDepart : intersectionLigneDepart) {
                for ( String intersectionArrivee : intersectionLigneArrivee) {
                    if (intersectionDepart.equals(intersectionArrivee)) {
                        stationCommune = intersectionDepart;
                        break;
                    }

                }
            }

            System.out.println("gare commune : " + stationCommune);
            int deStationDepartVersGareCommune = 0 ;
            int deGareCommuneVersStationArrivee = 0;
            int indexStationDeDepart = 0;
            int indexStationCommmune = 0;

            //obtenir index gare commune
            for (int i = 0; i < LIGNES_METRO.get(trajet.getLigneDepart()).length; i++) {
                if ( stationCommune.equals(LIGNES_METRO.get(trajet.getLigneDepart())[i])) {
                    System.out.println(i);
                    indexStationCommmune = i+1;
                    break;
                }
            }

            //obtenir index gare depart
            for (int i = 0; i < LIGNES_METRO.get(trajet.getLigneDepart()).length; i++) {
                if ( trajet.getStationDepart().equals(LIGNES_METRO.get(trajet.getLigneDepart())[i])) {
                    System.out.println(i);
                    indexStationDeDepart = i+1;
                    break;
                }
            }

            System.out.println("index gare commune : " + indexStationCommmune);
        }

        System.out.println(trajet);
        return trajet;
    }

    private int parcoursLigne(Trajet trajet, String StationCommune) {
        int nombreStationParcourue ;
        int positionPointDepart = 0;
        int positionPointArrivee = 0;

		/*int positionStationDepart = Arrays.binarySearch(LIGNES_METRO.get(trajet.getLigneDepart()),trajet.getStationDepart());
		System.out.println("postion de depart : "+ positionStationDepart);
		int positionStationArrivee = Arrays.binarySearch(LIGNES_METRO.get(trajet.getLigneArrivee()), trajet.getStationArrivee());
		System.out.println("postion de Arrivee : "+ positionStationArrivee);*/

        for (int i = 0; i < LIGNES_METRO.get(trajet.getLigneDepart()).length; i++) {
            if ( trajet.getStationDepart().equals(LIGNES_METRO.get(trajet.getLigneDepart())[i])) {
                System.out.println(i);
                positionPointDepart = i+1;
                break;
            }
        }

        for (int j = 0; j < LIGNES_METRO.get(trajet.getLigneArrivee()).length; j++) {
            if ( trajet.getStationArrivee().equals(LIGNES_METRO.get(trajet.getLigneArrivee())[j])) {
                System.out.println(j);
                positionPointArrivee = j+1;
                break;
            }
        }

        if ( positionPointDepart < positionPointArrivee ) nombreStationParcourue = positionPointArrivee - positionPointDepart;
        else nombreStationParcourue = positionPointDepart - positionPointArrivee;

        return nombreStationParcourue;

    }



}