package prog_2_adts.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "/Users/camilapereira/Desktop/Spotify_canciones.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int i=0;
            while ((line = br.readLine()) != null) {

                String[] fields = line.split(csvSplitBy);
                i++;
                for (String field : fields) {
                    System.out.print(i+". "+field);
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
