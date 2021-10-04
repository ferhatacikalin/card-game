#include <SPI.h>
#include <SD.h>
int state8 = LOW , state9 = LOW, state10 = LOW, state11 = LOW, state12 = LOW, state13 = LOW, bitis = LOW;
int lastState8 = LOW, lastState9 = LOW, lastState10 = LOW, lastState11 = LOW, lastState12 = LOW, lastState13 = LOW, lastBitis = LOW;
int para = 0 , temp = 0, parahizmet = 0, temphzmet = 0;
long randomsayi ;
int yuz , elli , yirmi , on , bes;
int banknot_counts[] = {0, 0, 0, 0, 0};//bes on yirmi elli yuz
String hizmet_adlari[] = {"", "", "", ""};//kopukleme,yıkama,kurulama,cilalama
int hizmet_kalan[] = {0, 0, 0, 0};//kopukleme,yıkama,kurulama,cilalama
int hizmet_ucretleri[] = {0, 0, 0, 0};//kopukleme,yıkama,kurulama,cilalama,
File h_file;
String str_buff;
void dosyaesitle() {

  SD.remove("hizmet.txt");
  h_file = SD.open("hizmet.txt", FILE_WRITE);
  if (h_file) {
    h_file.println( String(banknot_counts[0]) + "," + String(banknot_counts[1]) + "," + String(banknot_counts[2]) + "," + String(banknot_counts[3]) + "," + String(banknot_counts[4]));
    h_file.println("1," + String(hizmet_adlari[0]) + "," + String(hizmet_kalan[0]) + "," + String(hizmet_ucretleri[0]) + " TL");
    h_file.println("2," + String(hizmet_adlari[1]) + "," + String(hizmet_kalan[1]) + "," + String(hizmet_ucretleri[1]) + " TL");
    h_file.println("3," + String(hizmet_adlari[2]) + "," + String(hizmet_kalan[2]) + "," + String(hizmet_ucretleri[2]) + " TL");
    h_file.println("4," + String(hizmet_adlari[3]) + "," + String(hizmet_kalan[3]) + "," + String(hizmet_ucretleri[3]) + " TL");
    h_file.close();
  }
  else {
    Serial.println("dosya esitleme hatasi");
  }

}
void dosyaokuma() {
  char temp;

  str_buff = "";
  Serial.println("SD card okunuyor...");

  if (!SD.begin()) {
    Serial.println("init fail!");
    while (1);
  }
  Serial.println("init done.");

  h_file = SD.open("hizmet.txt");
  if (h_file) {
    Serial.println("hizmet.txt:");
    int index = 0;
    while (h_file.available()) {
      temp = (char) h_file.read();
      if (temp == ',') {
        banknot_counts[index] = str_buff.toInt();

        str_buff = "";
        Serial.print(String(banknot_counts[index]) + ",");

        index++;
      }
      else {
        if (temp == '\n') {
          banknot_counts[index] = str_buff.toInt();
          str_buff = "";
          Serial.print(String(banknot_counts[index]) + ",");
          Serial.println(" ");

          index = 0;
          break;
        }
        else {
          str_buff += temp;
        }

      }

    }
    //banknot sayilari alındı
    int satir = 0;
    index = 0;
    while (h_file.available()) {
      str_buff = "";
      if (index == 0) {
        str_buff = h_file.readStringUntil(',');
        Serial.print(str_buff);
        Serial.print(" ");
        index++;

      }
      else if (index == 1) {
        str_buff = h_file.readStringUntil(',');
        hizmet_adlari[satir] = str_buff;
        Serial.print(hizmet_adlari[satir]);
        Serial.print(" ");
        index++;

      }
      else if (index == 2) {
        str_buff = h_file.readStringUntil(',');
        hizmet_kalan[satir] = str_buff.toInt();
        Serial.print(hizmet_kalan[satir]);
        Serial.print(" ");
        index++;

      }

      else if (index == 3) {
        str_buff = h_file.readStringUntil(' ');
        h_file.readStringUntil('\n');
        hizmet_ucretleri[satir] = str_buff.toInt();
        Serial.print(hizmet_ucretleri[satir]);
        Serial.println(" ");
        index = 0;
        satir++;
      }




    }
    //hizmetler alındı
    h_file.close();
  } else {
    Serial.println("error opening file");
  }






}
void parayukleme() {
  while (true) {
    if (state13 == HIGH && lastState13 == LOW) {

      banknot_counts[0]++;
      para =  5;
      temp = para + temp;
      Serial.print(temp); Serial.print(" TL para attiniz. "); Serial.println();
    } lastState13 = state13;
    state13 = digitalRead(5);

    if (state12 == HIGH && lastState12 == LOW) {

      banknot_counts[1]++;
      para = 10;
      temp = para + temp;
      Serial.print(temp); Serial.print(" TL para attiniz.  "); Serial.println();
    } lastState12 = state12;
    state12 = digitalRead(4);

    if (state11 == HIGH && lastState11 == LOW) {
      banknot_counts[2]++;
      para = 20;
      temp = para + temp;
      Serial.print(temp); Serial.print(" TL para attiniz.  "); Serial.println();
    } lastState11 = state11;
    state11 = digitalRead(3);

    if (state10 == HIGH && lastState10 == LOW) {
      banknot_counts[3]++;
      para = 50;
      temp = para + temp;
      Serial.print(temp); Serial.print(" TL para attiniz.  "); Serial.println();
    } lastState10 = state10;
    state10 = digitalRead(2);

    if (state9 == HIGH && lastState9 == LOW) {
      banknot_counts[4]++;
      para =  100;
      temp = para + temp;
      Serial.print(temp); Serial.print(" TL para attiniz.  "); Serial.println();
    } lastState9 = state9;
    state9 = digitalRead(9);

    if (state8 == LOW && lastState8 == HIGH) {
      Serial.print(" Para yukleme islemi bitmistir.  "); Serial.println();
      break;
    } lastState8 = state8;
    state8 = digitalRead(8);
  }
}

void hizmetler() {
  int state8 = LOW , lastState8 = LOW;
  Serial.print("1.Kopukleme"); Serial.println();
  Serial.print("2.Yikama"); Serial.println();
  Serial.print("3.Kurulama"); Serial.println();
  Serial.print("4.Cilalama"); Serial.println();
  Serial.print("6.Bitis "); Serial.println();
  String uyari = "Uzgunuz bu hizmeti simdilik veremiyoruz!";
  while (true)  {
    if (state13 == HIGH && lastState13 == LOW) {
      if (hizmet_kalan[0] > 0) {
        parahizmet =  hizmet_ucretleri[0];
        hizmet_kalan[0] -= 1;
        temphzmet = parahizmet + temphzmet;
        Serial.print(" hizmetlerin toplam bedeli = "); Serial.print(temphzmet); Serial.println();
      }
      else {

        Serial.println(uyari);
      }

    } lastState13 = state13;
    state13 = digitalRead(5);

    if (state12 == HIGH && lastState12 == LOW) {
      if (hizmet_kalan[1] > 0) {
        parahizmet =  hizmet_ucretleri[1];
        hizmet_kalan[1] -= 1;
        temphzmet = parahizmet + temphzmet;
        Serial.print(" hizmetlerin toplam bedeli = "); Serial.print(temphzmet); Serial.println();
      }
      else {

        Serial.println(uyari);
      }
    } lastState12 = state12;
    state12 = digitalRead(4);

    if (state11 == HIGH && lastState11 == LOW) {
      if (hizmet_kalan[2] > 0) {
        parahizmet =  hizmet_ucretleri[2];
        hizmet_kalan[2] -= 1;
        temphzmet = parahizmet + temphzmet;
        Serial.print(" hizmetlerin toplam bedeli = "); Serial.print(temphzmet); Serial.println();
      }
      else {

        Serial.println(uyari);
      }
    } lastState11 = state11;
    state11 = digitalRead(3);

    if (state10 == HIGH && lastState10 == LOW) {
      if (hizmet_kalan[3] > 0) {
        parahizmet =  hizmet_ucretleri[3];
        hizmet_kalan[3] -= 1;
        temphzmet = parahizmet + temphzmet;
        Serial.print(" hizmetlerin toplam bedeli = "); Serial.print(temphzmet); Serial.println();
      }
      else {

        Serial.println(uyari);
      }
    } lastState10 = state10;
    state10 = digitalRead(2);

    if (state8 == LOW && lastState8 == HIGH) {
      Serial.print(" Hizmet Secim Islemi Bitmistir...  "); Serial.println();
      break;
    } lastState8 = state8;
    state8 = digitalRead(8);
  }
}

void paraustu(int odenecektutar) {
  if (odenecektutar < 0)
  {
    Serial.print("Kasada yeterli para yoktur."); Serial.println();

  } else {
    yuz = odenecektutar / 100; odenecektutar = odenecektutar - (100 * yuz);
    elli = odenecektutar / 50; odenecektutar = odenecektutar - (50 * elli);
    yirmi = odenecektutar / 20; odenecektutar = odenecektutar - (20 * yirmi);
    on = odenecektutar / 10; odenecektutar = odenecektutar - (10 * on);
    bes = odenecektutar / 5; odenecektutar = odenecektutar - (5 * bes);
    if ( banknot_counts[4] != 0 && yuz > 0)
    {
      Serial.print(yuz); Serial.print( " adet 100 TL "); Serial.println();
    }
    if ( banknot_counts[3] != 0 && elli > 0)
    {
      Serial.print(elli); Serial.print(" adet 50 TL "); Serial.println();
    }
    if (  banknot_counts[2] != 0 && yirmi > 0)
    {
      Serial.print(yirmi); Serial.print( " adet 20 TL "); Serial.println();
    }
    if ( banknot_counts[1] != 0 && on > 0)
    {
      Serial.print(on); Serial.print(" adet 10 TL "); Serial.println();
    }
    if (  banknot_counts[0] != 0 && bes > 0)
    {
      Serial.print(bes); Serial.print(" adet 5 TL "); Serial.println();
    } if (( banknot_counts[4] != 0 && yuz > 0) || ( banknot_counts[3] != 0 && elli > 0) || (  banknot_counts[2] != 0 && yirmi > 0) || ( banknot_counts[1] != 0 && on > 0) || (  banknot_counts[0] != 0 && bes > 0))
    {
      Serial.print("seklinde paraniz iade edilmistir."); Serial.println();
    }
  }
}

void parasikisma() {
  randomSeed(millis());

  randomsayi = random(1, 5);
  if (randomsayi == 2)
  {
    //tuşlanan hizmetler kalan hizmet miktarından düşmeyecektir. YAPILMADI
    Serial.println("Para sikisti Paraniz tamami geri iade ediliyor...");
    Serial.print(temp); Serial.print(" TL paraniz iade edilmistir.");
    temp = 0; temphzmet = 0;
    digitalWrite(7, HIGH);
    //    Serial.println(randomsayi);

  } else
  {

    if (temp > temphzmet) {
      Serial.println("Para sikismasi olmadi para ustu iade ediliyor...");
      paraustu(temp - temphzmet);
      dosyaesitle();
      temp = 0; temphzmet = 0;
      digitalWrite(6, HIGH);
    }
    else {

      Serial.println("Yeteri Kadar Para Atilmadi...");
      Serial.print(temp); Serial.print(" TL paraniz iade edilmistir.");
      temp = 0; temphzmet = 0;
      digitalWrite(7, HIGH);

    }

  }
}

void setup() {
  Serial.begin(9600);
  pinMode(8, INPUT); pinMode(9, INPUT); pinMode(5, INPUT); pinMode(4, INPUT); pinMode(3, INPUT); pinMode(2, INPUT);
  pinMode(7, OUTPUT); pinMode(6, OUTPUT);
  state8 = digitalRead(8);
  state9 = digitalRead(9);
  //  state10 = digitalRead(10);
  //  state11 = digitalRead(11);
  //  state12 = digitalRead(12);
  //  state13 = digitalRead(13);

  state10 = digitalRead(2);
  state11 = digitalRead(3);
  state12 = digitalRead(4);
  state13 = digitalRead(5);

  dosyaokuma();
  Serial.println("Para Atiniz");
  parayukleme(); delay(800);
  hizmetler(); delay(80);
  parasikisma(); delay(800);

}

void loop() {




}
