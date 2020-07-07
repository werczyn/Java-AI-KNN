package klasyfikator_knn;

import java.util.*;
import java.util.stream.Collectors;

public class Klasyfikator {

    private List<Obserwacje> nauczoneObserwacje;
    private List<Dystans> odleglosci;
    private List<String> klasyObiektow;

    public Klasyfikator(List<Obserwacje> nauczoneObserwacje, List<String> klasyObiektow) {
        this.nauczoneObserwacje = nauczoneObserwacje;
        odleglosci = new ArrayList<>();
        this.klasyObiektow = klasyObiektow;
    }

    public void zaklasyfikuj(int k,Obserwacje obserwacja){

        Dystans dystans = null;
        for (Obserwacje nauczona : nauczoneObserwacje) {
            dystans = new Dystans(nauczona,obserwacja);
            dystans.obliczDystans();
            //odleglosci.add(dystans.getDystans());
            odleglosci.add(dystans);
        }

        int[] wyniki = new int[klasyObiektow.size()];
        List<Dystans> posortowanaLista = odleglosci.stream().sorted(Comparator.comparing(Dystans::getDystans)).collect(Collectors.toList());
        posortowanaLista.subList(0,k).forEach(obiekt-> {
            for (int i=0; i<klasyObiektow.size();i++){
                if (klasyObiektow.get(i).equals(obiekt.getZaklasyfikowanie())){
                   wyniki[i]++;
                   return;
                }
            }
        });

        //zaklasyfikowanie
        obserwacja.setZaklasyfikowano(klasyObiektow.get(findMaxIndex(wyniki)));


       /* //tu testuje
        System.out.println(klasyObiektow);
        System.out.println("index: "+ findMaxIndex(wyniki));
        for (int i : wyniki) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("Zaklasyfikowano do: "+ obserwacja.getZaklasyfikowano()+" a powinno byc: "+obserwacja.getAtrybutDecyzyjny());
        System.out.println();*/

    }



    public void wyswietlWyniki(Obserwacje obserwacja){
        if (obserwacja.czyPrawidlowoZaklasyfikowano()){
            System.out.println("UDALO SIE: ");
        }else {
            System.out.println("\t\t\t\t\tNIE: ");
        }

    }

    int findMaxIndex(int [] arr) {
        int max = arr[0];
        int maxIdx = 0;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }else if (arr[i]==max){
                //jesli pseudolosowa liczba bedzie wieksza od 1 zamienia index maksymalnej a jesli nie index zostaje
                double random = Math.random()*2;
                if (random>1){
                    max = arr[i];
                    maxIdx = i;
                }
            }
        }
        return maxIdx;
    }


}
