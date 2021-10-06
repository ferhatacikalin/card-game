package prolab2.game;

public class FutbolcuSinif extends SporcuSinifi {

    int penalti, serbestAtis, kaleciKarsiKarsiya;
    String futbolcuAdi, futbolcuTakim;
   

    //Parametresiz FutbolcuS�n�f Constuctor
    public FutbolcuSinif() {
    }

    //Parametreli FutbolcuS�n�f constuctor. Paremetreler :SporcuAdi ve SporcuTipi
    public FutbolcuSinif(String sporcuAdi, String sporcuTipi) {
        super(sporcuAdi, sporcuTipi);
        this.futbolcuAdi = sporcuAdi;
        this.futbolcuTakim = sporcuTipi;
    }

    //Parametreli FutbolcuS�n�f constuctor. Paremetreler : penalti , serbestAtis ve kaleciKarsiKarsiya
    public FutbolcuSinif(int penalti, int serbestAtis, int kaleciKarsiKarsiya) {
        this.penalti = penalti;
        this.serbestAtis = serbestAtis;
        this.kaleciKarsiKarsiya = kaleciKarsiKarsiya;
    }

    // Her bir futbolcu için özelleştirelecektir.
    @Override
    public void sporcuPuaniGoster() {
        super.sporcuPuaniGoster();
    }

    //boolean veri tipinde kartKullanildiMi bilgisi tutulmalıdır. 
    public boolean kartKullanildiMi() {
        boolean result = false;
        return result;
    }

    public int getPenalti() {
        return penalti;
    }

    public void setPenalti(int penalti) {
        this.penalti = penalti;
    }

    public int getSerbestAtis() {
        return serbestAtis;
    }

    public void setSerbestAtis(int serbestAtis) {
        this.serbestAtis = serbestAtis;
    }

    public int getKaleciKarsiKarsiya() {
        return kaleciKarsiKarsiya;
    }

    public void setKaleciKarsiKarsiya(int kaleciKarsiKarsiya) {
        this.kaleciKarsiKarsiya = kaleciKarsiKarsiya;
    }

    public String getFutbolcuAdi() {
        return futbolcuAdi;
    }

    public void setFutbolcuAdi(String futbolcuAdi) {
        this.futbolcuAdi = futbolcuAdi;
    }

    public String getFutbolcuTakim() {
        return futbolcuTakim;
    }

    public void setFutbolcuTakim(String futbolcuTakim) {
        this.futbolcuTakim = futbolcuTakim;
    }

}
