package fr.sebd.metro.bo;

public class Trajet {

    private String ligneDepart;
    private String stationDepart;
    private String ligneArrivee;
    private String stationArrivee;
    private int nombreStationParcours = 0;
    private int nombreChangementLignes = 0;
    private int tempsParcours = 0;
    private int prix = 0;



    public Trajet() {
        super();
    }


    public Trajet(String ligneDepart, String stationDepart, String ligneArrivee, String stationArrivee,
                  int nombreStationParcours, int nombreChangementLignes, int tempsParcours, int prix) {
        super();
        this.ligneDepart = ligneDepart;
        this.stationDepart = stationDepart;
        this.ligneArrivee = ligneArrivee;
        this.stationArrivee = stationArrivee;
        this.nombreStationParcours = nombreStationParcours;
        this.nombreChangementLignes = nombreChangementLignes;
        this.tempsParcours = tempsParcours;
        this.prix = prix;
    }


    public String getLigneDepart() {
        return ligneDepart;
    }
    public void setLigneDepart(String ligneDepart) {
        this.ligneDepart = ligneDepart;
    }
    public String getStationDepart() {
        return stationDepart;
    }
    public void setStationDepart(String stationDepart) {
        this.stationDepart = stationDepart;
    }
    public String getLigneArrivee() {
        return ligneArrivee;
    }
    public void setLigneArrivee(String ligneArrivee) {
        this.ligneArrivee = ligneArrivee;
    }
    public String getStationArrivee() {
        return stationArrivee;
    }
    public void setStationArrivee(String stationArrivee) {
        this.stationArrivee = stationArrivee;
    }
    public int getNombreStationParcours() {
        return nombreStationParcours;
    }


    public void setNombreStationParcours(int nombreStationParcours) {
        this.nombreStationParcours = nombreStationParcours;
    }


    public int getNombreChangementLignes() {
        return nombreChangementLignes;
    }


    public void setNombreChangementLignes(int nombreChangementLignes) {
        this.nombreChangementLignes = nombreChangementLignes;
    }


    public int getTempsParcours() {
        return tempsParcours;
    }
    public void setTempsParcours(int tempsParcours) {
        this.tempsParcours = tempsParcours;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }


    @Override
    public String toString() {
        return "Trajet [ligneDepart=" + ligneDepart + ", stationDepart=" + stationDepart + ", ligneArrivee="
                + ligneArrivee + ", stationArrivee=" + stationArrivee + ", nombreStationParcours="
                + nombreStationParcours + ", nombreChangementLignes=" + nombreChangementLignes + ", tempsParcours="
                + tempsParcours + ", prix=" + prix + "]";
    }

}