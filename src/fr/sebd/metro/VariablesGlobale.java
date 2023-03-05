package fr.sebd.metro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VariablesGlobale {

    // les temps de voyage entre chaque station sont de 2 minutes
    static final int TEMPS_TRAJET = 2;

    // le temps d'attente pour changer de ligne est de 5 minutes
    static final int TEMPS_ATTENTE = 5;

    public static final Map<String, String[]> LIGNES_METRO = new HashMap<>() {


        {
            put("A", new String[]{"A1", "A2", "JAVA", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "JAVASCRIPT", "A10", "A11", "A12", "A13"});
            put("B", new String[]{"B1", "B2", "B3", "B4", "JAVA", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "RUBY", "B17", "B18", "B19"});
            put("C", new String[]{"C1", "C2", "C3", "C4", "JAVASCRIPT", "C5", "C6", "C7", "C8", "HTML", "C9", "C10", "C11", "RUBY", "C12", "C13", "PYTHON", "C14"});
            put("D", new String[]{"D1", "D2", "D3", "PHP", "D4", "D5", "D6", "D7", "D8", "HTML", "D9", "D10", "D11", "D12", "D13"});
            put("E", new String[]{"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "PYTHON", "E10", "E11"});

        }};

    public static final Map<String, List<String>> STATION_COMMUNES = new HashMap<>() {
        {
            put( "JAVA", Arrays.asList("A","B") );
            put( "JAVASCRIPT", Arrays.asList("A","C") );
            put( "PHP", Arrays.asList("D","B") );
            put( "HTML", Arrays.asList("D","C") );
            put( "RUBY", Arrays.asList("B","C") );
            put( "PYTHON", Arrays.asList("E","C") );
        }};

    public static final Map<String, List<String>> STATION_LIGNES_COMMUNES = new HashMap<>() {
        {
            put( "A", Arrays.asList("JAVA","JAVASCRIPT") );
            put( "B", Arrays.asList("JAVA","PHP","RUBY") );
            put( "C", Arrays.asList("JAVASCRIPT","HTML", "RUBY", "PYTHON") );
            put( "D", Arrays.asList("PHP","HTML") );
            put( "E", Arrays.asList("PYTHON") );

        }};

}
