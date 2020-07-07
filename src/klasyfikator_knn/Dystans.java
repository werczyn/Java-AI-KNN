package klasyfikator_knn;


public class Dystans {

    private Obserwacje obserwacjaTreningowa;
    private Obserwacje obserwacja;
    private double dystans;


    public Dystans(Obserwacje obserwacjaTreningowa, Obserwacje obserwacja){
        dystans = 0;
        this.obserwacjaTreningowa = obserwacjaTreningowa;
        this.obserwacja = obserwacja;
    }


   public void obliczDystans(){
       double odleglosc=0;
       for (int i = 0; i< obserwacjaTreningowa.getAtrybuty().length; i++){
           odleglosc += Math.pow(obserwacja.getAtrybuty()[i]- obserwacjaTreningowa.getAtrybuty()[i],2);
       }
       dystans = Math.sqrt(odleglosc);
   }

    public double getDystans() {
        return dystans;
    }

    public String getZaklasyfikowanie(){
        return obserwacjaTreningowa.getAtrybutDecyzyjny();
    }

    public String toString(){
        return getZaklasyfikowanie()+"  "+getDystans();
    }

}
