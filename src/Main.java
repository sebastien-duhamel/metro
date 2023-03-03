import java.util.*;

public class Main{

    // les temps de voyage entre chaque station sont de 2 minutes
    private static final int TEMPS_TRAJET = 2;

    // le temps d'attente pour changer de ligne est de 5 minutes
    private static final int TEMPS_ATTENTE = 5;

    // les lignes de métro
    /*private static final List<String[]> LIGNES_METRO5 = new ArrayList<String[]>() {{
        add(new String[]{"A1", "A2", "JAVA", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "JAVASCRIPT", "A10", "A11", "A12", "A13"});
        add(new String[]{"B1", "B2", "B3", "B4", "JAVA", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "RUBY", "B17", "B18", "B19"});
        add(new String[]{"C1", "C2", "C3", "C4", "JAVASCRIPT", "C5", "C6", "C7", "C8", "HTML", "C9", "C10", "C11", "RUBY", "C12", "C13", "PYTHON", "C14"});
        add(new String[]{"D1", "D2", "D3", "PHP", "D4", "D5", "D6", "D7", "D8", "HTML", "D9", "D10", "D11", "D12", "D13"});
        add(new String[]{"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "PYTHON", "E10", "E11"});
    }};*/

    static final Map<String, String[]> LIGNES_METRO = new HashMap<>() {{
        put("ligne A", new String[]{"A1", "A2", "JAVA", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "JAVASCRIPT", "A10", "A11", "A12", "A13"});
        put("Ligne B", new String[]{"B1", "B2", "B3", "B4", "JAVA", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "RUBY", "B17", "B18", "B19"});
        put("Ligne C", new String[]{"C1", "C2", "C3", "C4", "JAVASCRIPT", "C5", "C6", "C7", "C8", "HTML", "C9", "C10", "C11", "RUBY", "C12", "C13", "PYTHON", "C14"});
        put("Ligne D", new String[]{"D1", "D2", "D3", "PHP", "D4", "D5", "D6", "D7", "D8", "HTML", "D9", "D10", "D11", "D12", "D13"});
        put("Ligne E", new String[]{"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "PYTHON", "E10", "E11"});

    }};

    public static void main(String[] args) {



        // on crée un scanner pour récupérer les saisies de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // on demande la ligne et la station de départ
        System.out.print("Veuillez entrer la ligne de départ : ");
        String ligneDepart = "ligne "+ scanner.nextLine().toUpperCase();
        System.out.print("Veuillez entrer la station de départ : ");
        String stationDepart = scanner.nextLine().toUpperCase();

        // on demande la ligne et la station d'arrivée
        System.out.print("Veuillez entrer la ligne d'arrivée : ");
        String ligneArrivee = "ligne "+ scanner.nextLine().toUpperCase();
        System.out.print("Veuillez entrer la station d'arrivée : ");
        String stationArrivee = scanner.nextLine().toUpperCase();

        // on vérifie que la ligne et la station de départ existent
        int indexLigneDepart = -1;
        int indexStationDepart = -1;
        if ( LIGNES_METRO.containsKey(ligneDepart)) {
            indexLigneDepart=0;
            for (String stationLigne : LIGNES_METRO.get(ligneDepart)){
                if (stationLigne.equals(stationDepart)) {
                    indexStationDepart=0;
                    break;
                }
            }
        }

        // on vérifie que la ligne et la station d'arrivée existent
        int indexLigneArrivee = -1;
        int indexStationArrivee = -1;
        if ( LIGNES_METRO.containsKey(ligneArrivee)) {
            indexLigneArrivee=0;
            for (String stationLigne : LIGNES_METRO.get(ligneArrivee)){
                if (stationLigne.equals(stationArrivee)) {
                    indexStationArrivee=0;
                    break;
                }
            }
        }

        /*for (int i = 0; i < LIGNES_METRO.size(); i++) {
            System.out.println(i);
            String[] ligne = LIGNES_METRO.get(i);
            if (ligne[0].equals(ligneDepart)) {
                indexLigneDepart = i;
                for (int j = 0; j < ligne.length; j++) {
                    if (ligne[j].equals(stationDepart)) {
                        indexStationDepart = j;
                        break;
                    }
                }
                break;
            }
        }*/

        // on vérifie que la ligne et la station d'arrivée existent
       /* int indexLigneArrivee = -1;
        int indexStationArrivee = -1;
        for (int i = 0; i < LIGNES_METRO.size(); i++) {
            String[] ligne = LIGNES_METRO.get(i);
            if (ligne[0].equals(ligneArrivee)) {
                indexLigneArrivee = i;
                for (int j = 0; j < ligne.length; j++) {
                    if (ligne[j].equals(stationArrivee)) {
                        indexStationArrivee= j;
                        break;
                    }
                }
                break;
            }
        }*/

        System.out.println(indexLigneDepart);
        System.out.println(indexStationDepart);
        System.out.println(indexLigneArrivee);
        System.out.println(indexStationArrivee);
    }
}

