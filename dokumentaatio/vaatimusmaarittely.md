# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on antaa opiskelijalle mahdollisuus luoda itselleen lukujärjestyksiä eri opetusperiodeille ja kirjoittaa ylös periodikohtaisia muistiinpanoja. 

## Käyttäjät

Sovelluksella on ainoastaan yksi käyttäjä kerrallaan, opiskelija itse. Käyttäjiä voi kuitenkin olla useampia, sillä jokainen käyttäjä tekee itselleen salasanasuojatun käyttäjätunnuksen. Käyttäjien tiedot tallennetaan tietokantaan, jonka avulla hallinnoidaan sitä, kuka on kirjautuneena, mikä periodi näkymässä on kulloinkin esillä ja mitä tietoja lukujärjestyksessä ja muistiinpanoissa on tallennettuna.

## Käyttöliittymä

<img src="https://raw.githubusercontent.com/ratilmii/otm-harjoitustyo/master/dokumentaatio/Kuvat/opintovahtilogin.png">
<img src="https://raw.githubusercontent.com/ratilmii/otm-harjoitustyo/master/dokumentaatio/Kuvat/opintovahtiscene.png">

## Tämänhetkisen version toiminnallisuuksia

### Ennen kirjautumista

- Käyttäjätunnuksen luominen 
- Salasanan kryptaaminen tietokantaan
- kirjautuminen

### Kirjautumisen jälkeen

- Lukujärjestyksen luominen neljälle eri opiskeluperiodille
- periodikohtaiseen "muistilappuun" kirjoittaminen
- näkymä tallennetaan tietokantaan automaattisesti 200ms välein, käyttäjän ei itse tarvitse huolehtia tallentamisesta. Pelkkä kirjoittaminen riittää. Tällä pyrittiin simuloimaan fyysisen lukujärjestyksen helppoutta
- ohjelma myös kertoo alalaidassa, kuka kulloinkin on kirjautuneena

## Mahdollinen v2.0 

- GUI:n täydellinen uudistaminen
- tekstikenttien sijaan vapaasti siirreltäviä UI-elementtejä, joiden väriä voi vaihtaa
- kalenteri ja hälytysten asettaminen 
- tietokantatoiminnallisuuden hiominen ja mm. DAO:jen hyödyntäminen
