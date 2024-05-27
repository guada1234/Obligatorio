package prog_2_adts.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "/Users/guadaluperial/Desktop/Spotify_canciones.csv";  // replace with your CSV file path
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int i=0;
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] fields = line.split(csvSplitBy);
                i++;

                // Print each field (for demonstration purposes)
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
