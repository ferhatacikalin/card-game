package prolab2.game;

import java.util.ArrayList;

public class KullaniciSinifi extends OyuncuSinifi {
    ArrayList<FutbolcuSinif> futbolcularim;
    ArrayList<BasketbolcuSinifi> basketbolcularim;
    public KullaniciSinifi() {
    }

    public KullaniciSinifi(int oyuncuID, int Skor, String oyuncuAdi) {
        super(oyuncuID, Skor, oyuncuAdi);
    }

    //Kullanýcý kendi isteðiyle kart seçecektir
    @Override
    public void kartSec(boolean secimSirasiFutbolcu) {
        super.kartSec(secimSirasiFutbolcu); 
        
    }

    public ArrayList<FutbolcuSinif> getFutbolcularim() {
        return futbolcularim;
    }

    public void setFutbolcularim(ArrayList<FutbolcuSinif> futbolcularim) {
        this.futbolcularim = futbolcularim;
    }

    public ArrayList<BasketbolcuSinifi> getBasketbolcularim() {
        return basketbolcularim;
    }

    public void setBasketbolcularim(ArrayList<BasketbolcuSinifi> basketbolcularim) {
        this.basketbolcularim = basketbolcularim;
    }

}
