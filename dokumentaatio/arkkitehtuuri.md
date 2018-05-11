# Arkkitehtuurikuvaus

## Käyttöliittymä

Ohjelma sisältää Java FXML:llä toteutetun käyttöliittymän jossa on kaksi erillistä näkymää

- kirjautuminen / uuden käyttäjän luominen
- varsinainen lukujärjestysnäkymä

Molemmat ovat oma Scene-olionsa ja vain toinen on näkyvissä sovelluksen Stagessa kerrallaan. Käyttöli

## Tietojen tallennus

Luokka Databases.java hoitaa kaiken tietokantoihin liittyvän toiminnallisuuden. State.java-taas hoitaa sen muistamisen, kuka käyttäjä on kirjautuneena ja mikä periodi on aktiivisena/näkyvänä.

## Puutteet

DAO:jen hyödyntäminen jäi tekemättä ajanpuutteen vuoksi, mutta mahdollisessa v2.0:ssa se on tarkoitus ottaa huomioon.

