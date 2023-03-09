package fr.sebd.metro;

import fr.sebd.metro.bll.CalculTrajet;
import fr.sebd.metro.bo.Trajet;

public class Metro implements VariablesGlobale{

    public static void main(String[] args)  {

        Trajet trajet ;
        CalculTrajet calculTrajet = new CalculTrajet();

        trajet = calculTrajet.acquisitionParcours();

        trajet = calculTrajet.trouverParcours(trajet);

        trajet.setTempsParcours(trajet.getNombreStationParcours()*TEMPS_TRAJET + trajet.getNombreChangementLignes() * TEMPS_ATTENTE);

        System.out.println();
        if ( trajet.getTempsParcours() > 0){
            System.out.println("votre temps de parcours pour votre déplacements sera de " + trajet.getTempsParcours() + " min,");
            System.out.println("vous parcourerez " + trajet.getNombreStationParcours() + " station(s) avec " + trajet.getNombreChangementLignes() + " changement de ligne(s)");
        }


    }


}