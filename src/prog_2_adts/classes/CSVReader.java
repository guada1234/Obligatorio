package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.hash.HashImpl;
import prog_2_adts.src.uy.edu.um.adt.hash.InformacionInvalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class CSVReader {

    public static void main(String[] args) throws InformacionInvalida {
        String csvFile = "/Users/guadaluperial/Desktop/Spotify_canciones.csv";
        String line;
        String csvSplitBy = "\",\"";
        HashImpl<LocalDate,String> hashData = new HashImpl();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int i=0;
            br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println("Fila" + i);
                String[] fields = line.split(csvSplitBy);
                System.out.println(Arrays.toString(fields));
                System.out.println("AAAA");
                for (int j = 0; j< fields.length; j++) {
                    System.out.println(fields[j]);
                }
                System.out.println(fields[7]);
                LocalDate date = LocalDate.parse(fields[7]);
                String spotifyId = fields[0];
                String name = fields[1];
                String artista = fields[2];
                String daily_rank = fields[3];
                String daily_movement = fields[4];
                String country = fields[6];
                hashData.put(date,name);  //lo va agregando segun la fecha? o ponemos key como spotifyid?
                //que tenemos que guardar y como lo ponemos en el hash?
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
