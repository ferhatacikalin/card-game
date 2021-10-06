package prolab2.game;

import java.util.ArrayList;
import java.util.Random;

public class BilgisayarSinifi extends OyuncuSinifi {

    ArrayList<FutbolcuSinif> futbolcularim;
    ArrayList<BasketbolcuSinifi> basketbolcularim;



    public BilgisayarSinifi() {
    }

    public BilgisayarSinifi(int oyuncuID, int Skor, String oyuncuAdi) {
        super(oyuncuID, Skor, oyuncuAdi);
    }

    // Bilgisayara geri kalan kartlar dağıtılacaktır.
    @Override
    public void kartSec(boolean secimSirasiFutbolcu) {
        // method her çağrıldığında ikisini de seçsin
        Random rast = new Random();
        // 0 futbolcu 1 basketbolcu

        if (secimSirasiFutbolcu == true) {
            secilenFutbolcu = futbolcularim.get(rast.nextInt(futbolcularim.size()));
            while (secilenFutbolcu.kartKullanildiMi == true) {
                secilenFutbolcu = futbolcularim.get(rast.nextInt(futbolcularim.size()));
            }
        } else {
            secilenBasketbolcu = basketbolcularim.get(rast.nextInt(basketbolcularim.size()));
            while (secilenBasketbolcu.kartKullanildiMi == true) {
                secilenBasketbolcu = basketbolcularim.get(rast.nextInt(basketbolcularim.size()));

            }
        }

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
