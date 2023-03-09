package fr.sebd.metro;

import fr.sebd.metro.bll.CalculTrajet;
import fr.sebd.metro.bo.Trajet;

public class Metro{

    public static void main(String[] args) {

        Trajet trajet ;
        CalculTrajet calculTrajet = new CalculTrajet();

        trajet = calculTrajet.acquisitionParcours();

        trajet = calculTrajet.trouverParcours(trajet);



    }


}