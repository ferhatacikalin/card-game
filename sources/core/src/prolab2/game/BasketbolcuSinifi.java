package prolab2.game;

public class BasketbolcuSinifi extends SporcuSinifi {

    int ikilik, ucluk, serbestAtis;
    String basketbolcuAdi, basketbolcuTakim;


    //Parametresiz BasketbolcuS�n�f� Constuctor
    public BasketbolcuSinifi() {
    }

    //Parametreli BasketbolcuS�n�f� constuctor. Paremetreler : SporcuAdi ve SporcuTipi
    public BasketbolcuSinifi(String sporcuAdi, String sporcuTipi) {
        super(sporcuAdi, sporcuTipi);
        this.basketbolcuAdi = sporcuAdi;
        this.basketbolcuTakim = sporcuTipi;
    }

    //Parametreli BasketbolcuS�n�f� constuctor. Paremetreler : ikilik , ucluk ve serbestAtis
    public BasketbolcuSinifi(int ikilik, int ucluk, int serbestAtis) {
        this.ikilik = ikilik;
        this.ucluk = ucluk;
        this.serbestAtis = serbestAtis;
    }

    // her bir basketbolcu kartı için özelleştirilecektir.
    @Override
    public void sporcuPuaniGoster() {
        super.sporcuPuaniGoster();
    }

    //boolean veri tipinde kartKullanildiMi bilgisi tutulmaktad�r.
    public boolean kartKullanildiMi() {
        boolean result = true;
        return result;
    }

    public int getIkilik() {
        return ikilik;
    }

    public void setIkilik(int ikilik) {
        this.ikilik = ikilik;
    }

    public int getUcluk() {
        return ucluk;
    }

    public void setUcluk(int ucluk) {
        this.ucluk = ucluk;
    }

    public int getSerbestAtis() {
        return serbestAtis;
    }

    public void setSerbestAtis(int serbestAtis) {
        this.serbestAtis = serbestAtis;
    }

    public String getBasketbolcuAdi() {
        return basketbolcuAdi;
    }

    public void setBasketbolcuAdi(String basketbolcuAdi) {
        this.basketbolcuAdi = basketbolcuAdi;
    }

    public String getBasketbolcuTakim() {
        return basketbolcuTakim;
    }

    public void setBasketbolcuTakim(String basketbolcuTakim) {
        this.basketbolcuTakim = basketbolcuTakim;
    }

}
