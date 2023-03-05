package fr.sebd.metro;

import fr.sebd.metro.bll.CalculTrajet;
import fr.sebd.metro.bo.Trajet;

public class Metro{


    public static void main(String[] args) {

        Trajet trajet ;
        CalculTrajet calculTrajet = new CalculTrajet();

        trajet = calculTrajet.aquisitionParcours();

        trajet = calculTrajet.trouverParcours(trajet);

        // on crée un scanner pour récupérer les saisies de l'utilisateur

       /* int indexLigneDepart = -1;
        int indexStationDepart = -1;
        int indexLigneArrivee = -1;
        int indexStationArrivee = -1;*/

    }


}