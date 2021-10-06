package prolab2.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.Random;

public class TestSinifi extends ApplicationAdapter {

    Stage stage;
    Stage stage2;
    BitmapFont fontbmp;
    SpriteBatch batch;
    ShapeRenderer shape;
    BilgisayarSinifi bilgisayar;
    KullaniciSinifi kullanici;
    ArrayList<FutbolcuSinif> futbolcular = new ArrayList<>();
    ArrayList<BasketbolcuSinifi> basketbolcular = new ArrayList<>();
    ArrayList<FutbolcuSinif> kullanici_futbolcular = new ArrayList<>();
    ArrayList<BasketbolcuSinifi> kullanici_basketbolcular = new ArrayList<>();
    //0 futbolcu, 1 basketbolcu
    boolean siraFutbolcu = true;
    boolean sonrakineGec = true;
    boolean oyunSonu = false;
    boolean soneslestirme = false;
    int elsayisi = 0;
    String pozisyon = "";

    @Override
    public void create() {

        batch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("helvetica.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter ayar = new FreeTypeFontGenerator.FreeTypeFontParameter();
        ayar.size = 28;
        ayar.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "ÝiÐðÞþÖöÜüIý";
        fontbmp = generator.generateFont(ayar);
        generator.dispose();
        fontbmp.setColor(Color.RED);
        bilgisayar = new BilgisayarSinifi();
        kullanici = new KullaniciSinifi();
        bilgisayar.setOyuncuAdi("Bilgisayar");
        kullanici.setOyuncuAdi("Kullanýcý");
        bilgisayar.setOyuncuID(111);
        bilgisayar.setOyuncuID(112);
        kartlaridagit();
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
        ekranaCiz();
    }

    @Override
    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        Gdx.gl.glClearColor(0.75f, 0.90f, 0.33f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        skorCiz();
        pozisyonCiz();
        gameOverCheck();
        kullanici.futbolcuSecmeli = siraFutbolcu;
        bilgisayar.futbolcuSecmeli = siraFutbolcu;
        if (kullanici.kartSectiMi == false) {
            kartSecUyari();
        }
        if (bilgisayar.kartSectiMi == false && sonrakineGec == true && kullanici.kartSectiMi == true) {
            if (siraFutbolcu == true) {
                bilgisayar.kartSec(siraFutbolcu);
                bilgisayar.secilenFutbolcu.kartKullanildiMi = true;
                bilgisayar.secilenFutbolcu.oyunda = true;
                bilgisayar.kartSectiMi = true;
            } else {
                bilgisayar.kartSec(siraFutbolcu);
                bilgisayar.secilenBasketbolcu.kartKullanildiMi = true;
                bilgisayar.secilenBasketbolcu.oyunda = true;
                bilgisayar.kartSectiMi = true;

            }
        }
        if (bilgisayar.kartSectiMi == true && kullanici.kartSectiMi == true && sonrakineGec == true) {
            sonrakineGec = false;
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    System.out.println("Hesapla");
                    if (siraFutbolcu == true) {
                        bilgisayar.secilenFutbolcu.oyunda = false;
                        kullanici.secilenFutbolcu.oyunda = false;
                        futbolcuVfutbolcu();
                        elsayisi += 1;

                    } else {
                        bilgisayar.secilenBasketbolcu.oyunda = false;
                        kullanici.secilenBasketbolcu.oyunda = false;
                        basketbolcuVbasketbolcu();
                        elsayisi += 1;
                    }
                    if (elsayisi == 8 && bilgisayar.getSkor() != kullanici.getSkor()) {
                        oyunSonu = true;

                    } else if (elsayisi == 8 && bilgisayar.getSkor() == kullanici.getSkor()) {
                        if (siraFutbolcu == true) {
//                            bilgisayar.secilenFutbolcu.oyunda = true;
//                            kullanici.secilenFutbolcu.oyunda = true;
                            futbolcuVfutbolcu();
                        } else {
//                            bilgisayar.secilenBasketbolcu.oyunda = true;
//                            kullanici.secilenBasketbolcu.oyunda = true;
                            basketbolcuVbasketbolcu();

                        }
                        soneslestirme = true;

                    }
                    bilgisayar.kartSectiMi = false;
                    kullanici.kartSectiMi = false;
                    sonrakineGec = true;
                    siraFutbolcu = !siraFutbolcu;

                }
            }, 3);

        }

        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void kartlaridagit() {

        //8 Adet Futbolcu
        // [0]tipi , [1]adi, [2] kaleciKarsiKarsiya , [3] penalti , [4]serbestAtis
        String futbolculistesi[][] = {
            {"Futbolcu", "Lionel Messi", "98", "93", "98"},
            {"Futbolcu", "Cristiano Ronaldo", "99", "94", "97"},
            {"Futbolcu", "Neymar", "94", "91", "93"},
            {"Futbolcu", "Luis Suarez", "90", "87", "89"},
            {"Futbolcu", "Ronaldinho", "95", "87", "88"},
            {"Futbolcu", "Sergio Ramos", "84", "85", "89"},
            {"Futbolcu", "Semih Senturk", "85", "99", "97"},
            {"Futbolcu", "Eden Hazard", "97", "91", "81"},};

        // 8 Adet Basketbolcu
        // [0]tipi , [1]adi,[2] ikilik, [3] ucluk , [4]serbestAtis        
        String basketbolculistesi[][] = {
            {"Basketbolcu", "Michael Jordan", "99", "99", "99"},
            {"Basketbolcu", "LeBron James", "95", "93", "98"},
            {"Basketbolcu", "Kobe Bryant", "96", "94", "98"},
            {"Basketbolcu", "Stephen Curry", "90", "98", "93"},
            {"Basketbolcu", "James Harden", "91", "87", "91"},
            {"Basketbolcu", "Kawhi Leonard", "97", "91", "96"},
            {"Basketbolcu", "Carmelo Anthony", "92", "84", "86"},
            {"Basketbolcu", "Kevin Durant", "94", "94", "96"},};

        // futbolcu tip ve ad atama ,futbol ozellik atama
        for (int i = 0; i < futbolculistesi.length; i++) {
            futbolcular.add(i, new FutbolcuSinif());
            futbolcular.get(i).setSporcuTipi(futbolculistesi[i][0]);
            futbolcular.get(i).setSporcuAdi(futbolculistesi[i][1]);
            futbolcular.get(i).setKaleciKarsiKarsiya(Integer.parseInt(futbolculistesi[i][2]));
            futbolcular.get(i).setPenalti(Integer.parseInt(futbolculistesi[i][3]));
            futbolcular.get(i).setSerbestAtis(Integer.parseInt(futbolculistesi[i][4]));

        }

        //basket ad ve tipi atama, basket ozellik atama 
        for (int i = 0; i < basketbolculistesi.length; i++) {
            basketbolcular.add(i, new BasketbolcuSinifi());
            basketbolcular.get(i).setSporcuTipi(basketbolculistesi[i][0]);
            basketbolcular.get(i).setSporcuAdi(basketbolculistesi[i][1]);
            basketbolcular.get(i).setIkilik(Integer.parseInt(basketbolculistesi[i][2]));
            basketbolcular.get(i).setUcluk(Integer.parseInt(basketbolculistesi[i][3]));
            basketbolcular.get(i).setSerbestAtis(Integer.parseInt(basketbolculistesi[i][4]));
        }

        //kartlar array listelere atandÄ± bu listelerden kullanÄ±cÄ± iÃ§in kartlar seÃ§ilecek geriye kalanlar bilgisayarÄ±n kartlarÄ±
        Random rast = new Random();
        int rast_i;
        for (int i = 0; i < 4; i++) {

            rast_i = rast.nextInt(8 - i);
            System.out.println(rast_i);
            kullanici_futbolcular.add(futbolcular.get(rast_i));
            futbolcular.remove(rast_i);
            System.out.println(kullanici_futbolcular.get(i).sporcuAdi);
        }
        for (int i = 0; i < 4; i++) {

            rast_i = rast.nextInt(8 - i);
            System.out.println(rast_i);
            kullanici_basketbolcular.add(basketbolcular.get(rast_i));
            basketbolcular.remove(rast_i);
            System.out.println(kullanici_basketbolcular.get(i).sporcuAdi);
        }
        bilgisayar.basketbolcularim = basketbolcular;
        bilgisayar.futbolcularim = futbolcular;

    }

    public void ekranaCiz() {
        int cp = 180;
        for (int i = 0; i < 4; i++) {
            stage.addActor(new Kart(cp, 0, kullanici_futbolcular.get(i), kullanici));
            stage.addActor(new Kart(cp, 780, futbolcular.get(i), bilgisayar));
            cp += 195;

        }

        for (int i = 0; i < 4; i++) {
            stage.addActor(new Kart(cp, 0, kullanici_basketbolcular.get(i), kullanici));
            stage.addActor(new Kart(cp, 780, basketbolcular.get(i), bilgisayar));
            cp += 195;

        }
    }

    public void futbolcuVfutbolcu() {
        //[0] kaleciKarsiKarsiya , [1] penalti , [2]serbestAtis
        Random rast = new Random();
        int ozellik = rast.nextInt(3);
        if (ozellik == 0) {
            System.out.println("0");
            pozisyon = "Kaleciye Karþý";
            if (bilgisayar.secilenFutbolcu.getKaleciKarsiKarsiya() > kullanici.secilenFutbolcu.getKaleciKarsiKarsiya()) {
                bilgisayar.setSkor(bilgisayar.getSkor() + 10);
                System.out.println("B: " + bilgisayar.getSkor());
            } else {
                kullanici.setSkor(kullanici.getSkor() + 10);
                System.out.println("K: " + kullanici.getSkor());
            }
        }
        if (ozellik == 1) {
            System.out.println("1");
            pozisyon = "Penaltý";
            if (bilgisayar.secilenFutbolcu.getPenalti() > kullanici.secilenFutbolcu.getPenalti()) {
                bilgisayar.setSkor(bilgisayar.getSkor() + 10);
                System.out.println("B: " + bilgisayar.getSkor());
            } else {
                kullanici.setSkor(kullanici.getSkor() + 10);
                System.out.println("K: " + kullanici.getSkor());
            }
        }
        if (ozellik == 2) {
            System.out.println("2");
            pozisyon = "Serbest Atýþ";
            if (bilgisayar.secilenFutbolcu.getSerbestAtis() > kullanici.secilenFutbolcu.getSerbestAtis()) {
                bilgisayar.setSkor(bilgisayar.getSkor() + 10);
                System.out.println("B: " + bilgisayar.getSkor());
            } else {
                kullanici.setSkor(kullanici.getSkor() + 10);
                System.out.println("K: " + kullanici.getSkor());
            }
        }

    }

    public void basketbolcuVbasketbolcu() {
        //[0] ikilik, [1] ucluk , [2]serbestAtis
        Random rast = new Random();
        int ozellik = rast.nextInt(3);
        if (ozellik == 0) {
            System.out.println("0");
            pozisyon = "Ýkilik";
            if (bilgisayar.secilenBasketbolcu.getIkilik() > kullanici.secilenBasketbolcu.getIkilik()) {
                bilgisayar.setSkor(bilgisayar.getSkor() + 10);
                System.out.println("B: " + bilgisayar.getSkor());
            } else {
                kullanici.setSkor(kullanici.getSkor() + 10);
                System.out.println("K: " + kullanici.getSkor());
            }
        }
        if (ozellik == 1) {
            System.out.println("1");
            pozisyon = "Üçlük";
            if (bilgisayar.secilenBasketbolcu.getUcluk() > kullanici.secilenBasketbolcu.getUcluk()) {
                bilgisayar.setSkor(bilgisayar.getSkor() + 10);
                System.out.println("B: " + bilgisayar.getSkor());
            } else {
                kullanici.setSkor(kullanici.getSkor() + 10);
                System.out.println("K: " + kullanici.getSkor());
            }
        }
        if (ozellik == 2) {
            System.out.println("2");
            pozisyon = "Serbest Atýþ";
            if (bilgisayar.secilenBasketbolcu.getSerbestAtis() > kullanici.secilenBasketbolcu.getSerbestAtis()) {
                bilgisayar.setSkor(bilgisayar.getSkor() + 10);
                System.out.println("B: " + bilgisayar.getSkor());
            } else {
                kullanici.setSkor(kullanici.getSkor() + 10);
                System.out.println("K: " + kullanici.getSkor());
            }
        }
    }

    public void skorCiz() {
        batch.begin();
        fontbmp.setColor(Color.BROWN);
        fontbmp.draw(batch, bilgisayar.getOyuncuAdi() + " Puan: " + bilgisayar.getSkor(), 180, 770);
        fontbmp.draw(batch, kullanici.getOyuncuAdi() + " Puan: " + kullanici.getSkor(), 180, 330);
        batch.end();

    }

    public void gameOverCheck() {
        String kazanan="";
        if (oyunSonu == true || soneslestirme == true) {
            batch.begin();
            fontbmp.setColor(Color.BROWN);
            fontbmp.draw(batch, "Oyun Bitti!", 900, 540);
            if(kullanici.getSkor()>bilgisayar.getSkor()){
            kazanan=kullanici.getOyuncuAdi()+" Kazandý.";
            }
            else if(kullanici.getSkor()==bilgisayar.getSkor()){          
            kazanan="Berabere Bitti.";
            }
            else{
            kazanan=bilgisayar.getOyuncuAdi()+" Kazandý.";
            }
             fontbmp.draw(batch, kazanan, 860, 440);
            batch.end();
        }

    }

    public void pozisyonCiz() {
        batch.begin();
        fontbmp.setColor(Color.BROWN);
        fontbmp.draw(batch, "Pozisyon: " + pozisyon, 180, 360);
        batch.end();

    }

    public void kartSecUyari() {
        batch.begin();
        fontbmp.setColor(Color.RED);
        String metin = null;
        if (siraFutbolcu) {
            metin = "Futbolcu Seçin";
        } else {
            metin = "Basketbolcu Seçin";
        }
        if (oyunSonu) {
            metin = "";
        }
        if (soneslestirme) {
            metin = "Son Kartlar Tekrar Eþleþtirildi.";
        }
        fontbmp.draw(batch, metin, 840, 340);
        batch.end();

    }

    public BilgisayarSinifi getBilgisayar() {
        return bilgisayar;
    }

    public void setBilgisayar(BilgisayarSinifi bilgisayar) {
        this.bilgisayar = bilgisayar;
    }

    public KullaniciSinifi getKullanici() {
        return kullanici;
    }

    public void setKullanici(KullaniciSinifi kullanici) {
        this.kullanici = kullanici;
    }

    public ArrayList<FutbolcuSinif> getFutbolcular() {
        return futbolcular;
    }

    public void setFutbolcular(ArrayList<FutbolcuSinif> futbolcular) {
        this.futbolcular = futbolcular;
    }

    public ArrayList<BasketbolcuSinifi> getBasketbolcular() {
        return basketbolcular;
    }

    public void setBasketbolcular(ArrayList<BasketbolcuSinifi> basketbolcular) {
        this.basketbolcular = basketbolcular;
    }

    public ArrayList<FutbolcuSinif> getKullanici_futbolcular() {
        return kullanici_futbolcular;
    }

    public void setKullanici_futbolcular(ArrayList<FutbolcuSinif> kullanici_futbolcular) {
        this.kullanici_futbolcular = kullanici_futbolcular;
    }

    public ArrayList<BasketbolcuSinifi> getKullanici_basketbolcular() {
        return kullanici_basketbolcular;
    }

    public void setKullanici_basketbolcular(ArrayList<BasketbolcuSinifi> kullanici_basketbolcular) {
        this.kullanici_basketbolcular = kullanici_basketbolcular;
    }

    public boolean isSiraFutbolcu() {
        return siraFutbolcu;
    }

    public void setSiraFutbolcu(boolean siraFutbolcu) {
        this.siraFutbolcu = siraFutbolcu;
    }

    public boolean isSonrakineGec() {
        return sonrakineGec;
    }

    public void setSonrakineGec(boolean sonrakineGec) {
        this.sonrakineGec = sonrakineGec;
    }

    public boolean isOyunSonu() {
        return oyunSonu;
    }

    public void setOyunSonu(boolean oyunSonu) {
        this.oyunSonu = oyunSonu;
    }

    public boolean isSoneslestirme() {
        return soneslestirme;
    }

    public void setSoneslestirme(boolean soneslestirme) {
        this.soneslestirme = soneslestirme;
    }

    public int getElsayisi() {
        return elsayisi;
    }

    public void setElsayisi(int elsayisi) {
        this.elsayisi = elsayisi;
    }

}
