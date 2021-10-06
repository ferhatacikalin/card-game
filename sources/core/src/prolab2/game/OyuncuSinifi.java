package prolab2.game;

public class OyuncuSinifi {

    int oyuncuID, Skor;
    String oyuncuAdi;
    boolean kartSectiMi=false;
    FutbolcuSinif secilenFutbolcu;
    BasketbolcuSinifi secilenBasketbolcu;
    boolean futbolcuSecmeli;
    
    public OyuncuSinifi() {
    }

    public OyuncuSinifi(int oyuncuID, int Skor, String oyuncuAdi) {
        this.oyuncuID = oyuncuID;
        this.Skor = Skor;
        this.oyuncuAdi = oyuncuAdi;
    }
    //bilgisayar ve kullanıcı için farklı durumlarda çalışacaktır.

    public void kartSec(boolean secimSirasiFutbolcu) {

    }

    //oyuncuların skorları gösterilecektir. 
    public int SkorGoster() {

        return Skor;
    }

    public int getOyuncuID() {
        return oyuncuID;
    }

    public void setOyuncuID(int oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public int getSkor() {
        return Skor;
    }

    public void setSkor(int Skor) {
        this.Skor = Skor;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }

    public boolean isKartSectiMi() {
        return kartSectiMi;
    }

    public void setKartSectiMi(boolean kartSectiMi) {
        this.kartSectiMi = kartSectiMi;
    }

    public FutbolcuSinif getSecilenFutbolcu() {
        return secilenFutbolcu;
    }

    public void setSecilenFutbolcu(FutbolcuSinif secilenFutbolcu) {
        this.secilenFutbolcu = secilenFutbolcu;
    }

    public BasketbolcuSinifi getSecilenBasketbolcu() {
        return secilenBasketbolcu;
    }

    public void setSecilenBasketbolcu(BasketbolcuSinifi secilenBasketbolcu) {
        this.secilenBasketbolcu = secilenBasketbolcu;
    }

    public boolean isFutbolcuSecmeli() {
        return futbolcuSecmeli;
    }

    public void setFutbolcuSecmeli(boolean futbolcuSecmeli) {
        this.futbolcuSecmeli = futbolcuSecmeli;
    }

}
