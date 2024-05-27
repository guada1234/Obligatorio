package prog_2_adts.classes;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) throws IOException {
        String filePath = "../../../../Desktop/Spotify_canciones.csv";
        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT)) {
            List<CSVRecord> records = parser.getRecords();
            for (CSVRecord record : records) {
                // process each record here
                System.out.println(String.join(",", record));
            }
        }
        }
    }


