# Käyttöohje

## Sovelluksen käynnistäminen

Sovelluksen käynnistäminen pitäisi onnistua yksinkertaisesti tuplaklikkaamalla jar-tiedostoa. Ohjelma luo itse tarvitsemansa tietokannan users.db.

## Tunnuksen luominen

Sovelluksessa on vain kaksi näkymää, joisa ensimmäinen hoitaa sekä kirjautumisen että uuden tunnuksen luomisen. Luodaksesi uuden tunnuksen, kirjoita haluamasi käyttäjätunnus ja salasana niille osoitettuihin kenttiin ja klikkaa "luo tunnus". 

<img src = "https://raw.githubusercontent.com/ratilmii/otm-harjoitustyo/master/dokumentaatio/Kuvat/opintovahtilogin.png">

Mikäli tunnus on jo olemassa, antaa ohjelma virheilmoituksen punaisella tekstillä:

<img src="https://raw.githubusercontent.com/ratilmii/otm-harjoitustyo/master/dokumentaatio/Kuvat/opintovahtiloginerror1.png">

## Kirjautuminen

Samoin kuin tunnusta luodessa käyttäjä kirjoittaa käyttäjänimensä ja salasanansa kirjautumisikkunan kenttiin, mutta tällä kertaa klikataan "kirjaudu sisään". Mikäli salasana on väärin, tai käyttäjää ei ole olemassa, ohjelma antaa virheilmoituksen:

<img src = "https://raw.githubusercontent.com/ratilmii/otm-harjoitustyo/master/dokumentaatio/Kuvat/opintovahtiloginerror2.png">
<img src = "https://raw.githubusercontent.com/ratilmii/otm-harjoitustyo/master/dokumentaatio/Kuvat/opintovahtiloginerror3.png">

## Ohjelman käyttäminen

Kun käyttäjä on joko luonut uuden tunnuksen tai kirjautunut jo olemassaolevalla, avautuu ohjelman varsinainen näkymä. 

Mikäli käyttäjä on uusi, ovat kaikki tekstikentät tyhjiä ja oletusperiodina on "1". Jos taas käyttäjä on jo tallentanut lukujärjestyksiä tai muistiinpanoja, avaa ohjelma viimeisimmän tallennetun näkymän. Ohjelma tallentaa tiedot 200ms välein automaattisesti, joten käyttäjän ei tarvitse tehdä muuta kuin kirjoittaa.

<img src = "https://raw.githubusercontent.com/ratilmii/otm-harjoitustyo/master/dokumentaatio/Kuvat/opintovahtiscenetips.png"> 

### Kuvan korostetut alueet

1. Lukujärjestys. Jokainen ruutu on oma tekstikenttänsä ja jokainen lukujärjestys on yksilöllinen periodilleen.
2. periodin valitsemiseen käytettävä valikko. Näyttää nykyisen periodin numeron.
3. periodikohtaiset satunnaiset muistiinpanot.
4. Tämänhetkinen käyttäjä.
