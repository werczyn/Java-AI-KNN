package klasyfikator_knn;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //pobieranie danych z Plikow:
        String sciezkaTrening = "data\\iris_training.txt";
        Przetwornik trening = new Przetwornik(sciezkaTrening);
        List<Obserwacje> treningoweObserwacje = trening.getListaObserwacji();


        String sciezkaTest = "data\\iris_test.txt";
        Przetwornik test = new Przetwornik(sciezkaTest);
        List<Obserwacje> testoweObserwacje = test.getListaObserwacji();
        //-------------------------------------------------------
        //pobieranie liczby k:
        Scanner scanner = new Scanner(System.in);
        int k = 0;
        try {
            System.out.print("Podaj liczbe naturalna k: ");
            k = scanner.nextInt();
            if (k <= 0 || k>treningoweObserwacje.size()){
                throw new Exception();
            }
        }catch (InputMismatchException ex){
            System.err.println("Podany argument nie jest liczba lub ma nieprawidlowy format");
            scanner.close();
            System.exit(-2);
        } catch (Exception e) {
            System.err.println("K powinno byc wieksze od 0 i mniejsze od liczby obserwacji: "+treningoweObserwacje.size());
            scanner.close();
            System.exit(-1);
        }
        //---------------------------------------------------------

        //klasyfikowanie:
        for (Obserwacje testowaObserwacja : testoweObserwacje) {
            Klasyfikator klasyfikator = new Klasyfikator(treningoweObserwacje, trening.getKlasyObiektow());
            klasyfikator.zaklasyfikuj(k, testowaObserwacja);
        }
        //-----------------------------------------------------

        //Wyswietlanie procenta prawidlowo zaklasyfikowanych:
        int liczbaUdanych = 0;
        for (Obserwacje obserwacje : testoweObserwacje) {
            if (obserwacje.czyPrawidlowoZaklasyfikowano()){
                liczbaUdanych++;
            }
        }
        double procent = liczbaUdanych*100;
        System.out.println("Liczba zaklasyfikowanych prawidlowo: "+liczbaUdanych+ "/" + testoweObserwacje.size());
        System.out.format("Procent zaklasyfikowanych prawidlowo: %.2f%%%n",procent/testoweObserwacje.size());
        //------------------------------------------------------------

        //pobieranie wektora obiektow od uzytkownika:
        boolean dziala=true;
        while(dziala){
            String napis = scanner.nextLine();
            if (napis.toLowerCase().equals("wprowadz")){
                int liczbaAtrybutow = treningoweObserwacje.get(0).liczbaAtrybutow();
                double[] atrybuty = new double[liczbaAtrybutow];
                for (int i=0; i<liczbaAtrybutow; i++){
                    System.out.println("Podaj atrybut numer "+(i+1)+": ");
                    try {
                        atrybuty[i] = scanner.nextDouble();
                    }catch (InputMismatchException ex) {
                        System.err.println("Podany argument nie jest liczba lub ma nieprawidlowy format");
                        scanner.close();
                        System.exit(-2);
                    }
                    System.out.println("Pobrano :"+ atrybuty[i]);
                }
                Obserwacje nowaObserwacja = new Obserwacje(atrybuty);
                Klasyfikator klasyfikator = new Klasyfikator(treningoweObserwacje, trening.getKlasyObiektow());
                klasyfikator.zaklasyfikuj(k,nowaObserwacja);
                System.out.println("----->"+nowaObserwacja.getZaklasyfikowano()+"<-----");
            }else if (napis.toLowerCase().equals("exit")){
                dziala = false;
                System.out.println("Zamykanie programu....");
            }else{
                System.out.println("Wpisz: \n\tWprowadz: aby wprowadzic wektor atrybutow\n\tEXIT: aby zakonczyc dzialanie programu");
            }
        }

        //------------------------------------------------------------------

        scanner.close();
    }


}
