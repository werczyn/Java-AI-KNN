package klasyfikator_knn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Przetwornik {

    private List<String> klasyObiektow;
    private List<Obserwacje> listaObserwacji;

    public Przetwornik(String sciezkaPliku) {
        klasyObiektow = new ArrayList<>();
        listaObserwacji = new ArrayList<>();
        try {
            zaladujDane(sciezkaPliku);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Nie ma takiego pliku");
            System.exit(-3);
        }

    }

    private void zaladujDane(String sciezkaPliku) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(sciezkaPliku)));

        String linia;
        while ((linia = fileReader.readLine()) != null) {
            String[] tmpTab = linia.trim().split("\\s+");

            int liczbaAtrybutow = tmpTab.length;
            double[] atrybuty = new double[liczbaAtrybutow-1];

            for (int i = 0; i < tmpTab.length; i++) {
                String tmp = tmpTab[i];
                tmp = tmp.replace(',', '.');

                //Zamiana stringow na double
                if (i != tmpTab.length - 1) {
                    atrybuty[i] = Double.parseDouble(tmp);
                } else {
                    Obserwacje obserwacja = new Obserwacje(tmpTab[i],atrybuty);
                    listaObserwacji.add(obserwacja);
                    //dodawanie klas obiektow
                    if (!klasyObiektow.contains(tmpTab[i])) {
                        klasyObiektow.add(tmpTab[i]);
                    }
                }
            }
        }
        fileReader.close();
    }

    public List<Obserwacje> getListaObserwacji() {
        return listaObserwacji;
    }

    public List<String> getKlasyObiektow() {
        return klasyObiektow;
    }
}
