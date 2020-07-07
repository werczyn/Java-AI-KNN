package klasyfikator_knn;

public class Obserwacje {

    private String atrybutDecyzyjny;
    private double[] atrybuty;
    private String zaklasyfikowano;

    public Obserwacje(String atrybutDecyzyjny, double[] atrybuty) {
        this.atrybutDecyzyjny = atrybutDecyzyjny;
        this.atrybuty = atrybuty;
    }

    public Obserwacje(double[] atrybuty){
        this.atrybuty = atrybuty;
    }

    public String getAtrybutDecyzyjny() {
        return atrybutDecyzyjny;
    }

    public double[] getAtrybuty() {
        return atrybuty;
    }

    public void setZaklasyfikowano(String zaklasyfikowano) {
        this.zaklasyfikowano = zaklasyfikowano;
    }

    public boolean czyPrawidlowoZaklasyfikowano(){
        if (zaklasyfikowano.equals(atrybutDecyzyjny))
            return true;
        else
            return false;
    }

    public String getZaklasyfikowano() {
        return zaklasyfikowano;
    }

    public int liczbaAtrybutow(){
        //liczba atrybutow bez decyzyjnego
        return atrybuty.length;
    }
}
