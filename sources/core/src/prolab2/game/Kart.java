package prolab2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 *
 * @author Ferhat
 */
public class Kart extends Actor {

    Texture kartArkaPlanResmi;
    Texture image;
    BitmapFont fontbmp;
    BitmapFont fontsmall;
    String kartBaslik = "Belli Deðil";
    int x = 0, y = 0;
    int orgX;
    int orgY;
    FutbolcuSinif futbolcu;
    BasketbolcuSinifi basketbolcu;
    boolean futbolcuKarti = false;
    OyuncuSinifi oyuncu;
    boolean secildi;
    boolean gizli;

    public Kart(int x, int y) {
        this.x = x;
        this.y = y;
        orgX = x;
        orgY = y;
        kartArkaPlanResmi = new Texture("card.jpg");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("helvetica.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter ayar = new FreeTypeFontGenerator.FreeTypeFontParameter();
        ayar.size = 22;
        ayar.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "ÝiÐðÞþÖöÜüIý";
        fontbmp = generator.generateFont(ayar);
        ayar.size = 18;
        fontsmall = generator.generateFont(ayar);
        generator.dispose();
        fontbmp.setColor(Color.BLUE);
        fontsmall.setColor(Color.BROWN);
        setBounds(x, y, kartArkaPlanResmi.getWidth(), kartArkaPlanResmi.getHeight());

    }

    public Kart(int x, int y, FutbolcuSinif futbolcu, BilgisayarSinifi oyuncu) {
        this(x, y);
        kartBaslik = futbolcu.sporcuAdi;
        this.futbolcu = futbolcu;
        this.oyuncu = oyuncu;
        this.futbolcuKarti = true;
        this.gizli = true;

    }

    public Kart(int x, int y, BasketbolcuSinifi basketbolcu, BilgisayarSinifi oyuncu) {
        this(x, y);
        kartBaslik = basketbolcu.sporcuAdi;
        this.basketbolcu = basketbolcu;
        this.oyuncu = oyuncu;
        this.futbolcuKarti = false;
        this.gizli = true;

    }

    public Kart(int x, int y, FutbolcuSinif futbolcu, KullaniciSinifi oyuncu) {
        this(x, y);
        kartBaslik = futbolcu.sporcuAdi;
        this.futbolcu = futbolcu;
        this.oyuncu = oyuncu;
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((Kart) event.getTarget()).oyuncu.kartSectiMi = true;
                ((Kart) event.getTarget()).oyuncu.secilenFutbolcu = ((Kart) event.getTarget()).futbolcu;
                ((Kart) event.getTarget()).futbolcu.kartKullanildiMi = true;
                ((Kart) event.getTarget()).futbolcu.oyunda = true;

                return true;
            }
        });
        this.futbolcuKarti = true;
    }

    public Kart(int x, int y, BasketbolcuSinifi basketbolcu, KullaniciSinifi oyuncu) {
        this(x, y);
        kartBaslik = basketbolcu.sporcuAdi;
        this.basketbolcu = basketbolcu;
        this.oyuncu = oyuncu;
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((Kart) event.getTarget()).oyuncu.kartSectiMi = true;
                ((Kart) event.getTarget()).oyuncu.secilenBasketbolcu = ((Kart) event.getTarget()).basketbolcu;
                ((Kart) event.getTarget()).basketbolcu.kartKullanildiMi = true;
                ((Kart) event.getTarget()).basketbolcu.oyunda = true;
                return true;
            }
        });
        this.futbolcuKarti = false;
    }

    @Override
    public void draw(Batch batch, float alph) {

        batch.draw(kartArkaPlanResmi, x, y);

        if (gizli == true && secildi == false) {
            //nothing
        } else {
            fontbmp.draw(batch, kartBaslik, x + 10, y + 280);

            if (futbolcuKarti == true) {
                fontsmall.draw(batch, "Kaleciye Karþý: " + futbolcu.getKaleciKarsiKarsiya(), x + 10, y + 200);
                fontsmall.draw(batch, "Penaltý: " + futbolcu.getKaleciKarsiKarsiya(), x + 10, y + 170);
                fontsmall.draw(batch, "Serbest Atýþ: " + futbolcu.getKaleciKarsiKarsiya(), x + 10, y + 140);
                if (futbolcu.kartKullanildiMi == true) {
                    fontbmp.setColor(Color.RED);
                    fontbmp.draw(batch, "Kullanýldý", x + 100, y + 30);
                    gizli = false;
                }
            } else {
                fontsmall.draw(batch, "Ýkilik: " + basketbolcu.getIkilik(), x + 10, y + 200);
                fontsmall.draw(batch, "Üçlük: " + basketbolcu.getUcluk(), x + 10, y + 170);
                fontsmall.draw(batch, "Serbest Atýþ: " + basketbolcu.getSerbestAtis(), x + 10, y + 140);
                if (basketbolcu.kartKullanildiMi == true) {
                    fontbmp.setColor(Color.RED);
                    fontbmp.draw(batch, "Kullanýldý", x + 100, y + 30);
                    gizli = false;
                }

            }
        }
    }

    @Override
    public void act(float delta) {
        if (oyuncu.kartSectiMi == true) {
            setTouchable(Touchable.disabled);
        }
        if (oyuncu.kartSectiMi == false) {
            setTouchable(Touchable.enabled);
        }
        if (futbolcuKarti == false && oyuncu.futbolcuSecmeli == true) {
            setTouchable(Touchable.disabled);
        } else if (futbolcuKarti == true && oyuncu.futbolcuSecmeli == false) {
            setTouchable(Touchable.disabled);
        } else {
            if (oyuncu.kartSectiMi == false) {
                setTouchable(Touchable.enabled);

            }
        }
        if (futbolcuKarti) {
            secildi = futbolcu.oyunda;
            if (futbolcu.kartKullanildiMi == true) {
                setTouchable(Touchable.disabled);
            }

        } else {
            secildi = basketbolcu.oyunda;

        }

        if (secildi && orgY == 0) {
            x = 750;
            y = 390;
            setPosition(x, y);

        } else if (secildi && orgY == 780) {
            x = 980;
            y = 390;
            setPosition(x, y);
        } else {

            y = orgY;
            x = orgX;
            //setPosition(x, y);
        }

    }

    public Texture getKartArkaPlanResmi() {
        return kartArkaPlanResmi;
    }

    public void setKartArkaPlanResmi(Texture kartArkaPlanResmi) {
        this.kartArkaPlanResmi = kartArkaPlanResmi;
    }

    public String getKartBaslik() {
        return kartBaslik;
    }

    public void setKartBaslik(String kartBaslik) {
        this.kartBaslik = kartBaslik;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOrgX() {
        return orgX;
    }

    public void setOrgX(int orgX) {
        this.orgX = orgX;
    }

    public int getOrgY() {
        return orgY;
    }

    public void setOrgY(int orgY) {
        this.orgY = orgY;
    }

    public FutbolcuSinif getFutbolcu() {
        return futbolcu;
    }

    public void setFutbolcu(FutbolcuSinif futbolcu) {
        this.futbolcu = futbolcu;
    }

    public BasketbolcuSinifi getBasketbolcu() {
        return basketbolcu;
    }

    public void setBasketbolcu(BasketbolcuSinifi basketbolcu) {
        this.basketbolcu = basketbolcu;
    }

    public boolean isFutbolcuKarti() {
        return futbolcuKarti;
    }

    public void setFutbolcuKarti(boolean futbolcuKarti) {
        this.futbolcuKarti = futbolcuKarti;
    }

    public OyuncuSinifi getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(OyuncuSinifi oyuncu) {
        this.oyuncu = oyuncu;
    }

    public boolean isSecildi() {
        return secildi;
    }

    public void setSecildi(boolean secildi) {
        this.secildi = secildi;
    }

    public boolean isGizli() {
        return gizli;
    }

    public void setGizli(boolean gizli) {
        this.gizli = gizli;
    }

}
