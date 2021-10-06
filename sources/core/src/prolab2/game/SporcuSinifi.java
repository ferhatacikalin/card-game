package prolab2.game;

public class SporcuSinifi {

    String sporcuAdi;
    String sporcuTipi;
    boolean oyunda = false;
    boolean kartKullanildiMi = false;

    // Parametresiz Sporcu S�n�f� Constuctor
    public SporcuSinifi() {
    }

    //Parametreli sporcu s�n�f� constuctor
    public SporcuSinifi(String sporcuAdi, String sporcuTipi) {
        this.sporcuAdi = sporcuAdi;
        this.sporcuTipi = sporcuTipi;
    }

    // sporcuPuaniGoster() methodu tamamla.
    public void sporcuPuaniGoster() {

    }

    public boolean isOyunda() {
        return oyunda;
    }

    public void setOyunda(boolean oyunda) {
        this.oyunda = oyunda;
    }

    public boolean isKartKullanildiMi() {
        return kartKullanildiMi;
    }

    public void setKartKullanildiMi(boolean kartKullanildiMi) {
        this.kartKullanildiMi = kartKullanildiMi;
    }

    public String getSporcuAdi() {
        return sporcuAdi;
    }

    public void setSporcuAdi(String sporcuAdi) {
        this.sporcuAdi = sporcuAdi;
    }

    public String getSporcuTipi() {
        return sporcuTipi;
    }

    public void setSporcuTipi(String sporcuTipi) {
        this.sporcuTipi = sporcuTipi;
    }

}
