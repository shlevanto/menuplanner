<h1>Käyttöohje</h1>

Lataa Menuplanner jar -tiedosto ja config.properties konfiguraatiotedosto ![/releases](releases -kansiosta).

<h2>Konfigurointi</h2>

Ohjelma tarvitsee toimiakseen config.properties nimisen tiedoston, josta se luke käyttäjätietokannan nimen ja yksittäisten käyttäjien käyttöön tulevat pääraaka-aineet, lisukkeet ja oletusreseptit. Tiedoston muoto on

```
usersDataBase = users
proteins = liha,kala,kasvis jne.
sides = pasta,riisi,peruna jne.
recipes = makaronilaatikko,liha,pasta,kalakeitt,kala,keitto
```

<h2>Käynnistäminen</h2>

Ohjelma käynnistetään komennolla

<code>java -jar Menuplanner***.jar</code>

*** riippuu siitä minkä releasen haluat käynnistää.

<h2>Kirjautuminen</h2>

Sovelluksen käynnistyessä vaihtoehtoina on valita listasta olemassa oleva käyttäjä tai luoda uusi käyttäjä.

<img src="images/loginGUI.png">

Kirjautuminen tapahtuu valitsemalla pudotusvalikosta käyttäjä ja klikkaamalla 'Kirjaudu' -painiketta.

<h2>Uuden käyttäjän luominen</h2>

Uusi käyttäjä luodaan valitsemalla kirjautumisnäkymässä 'Luo uusi käyttäjä'. Avautuvassa ikkunassa kirjoitetaan uuden käyttäjän nimi ja valitaan 'Luo uusi käyttäjä'. Kannassa ei voi olla samannimisiä käyttäjiä, sovellus antaa virheilmoituksen jos uuden käyttäjän lisääminen ei onnistu.

<img src=imager/newUserGUI.png>

<h2>Tietokannassa olevien reseptien listaaminen</h2>

<h2>Uuden reseptin lisääminen</h2>

<h2>Reseptin poistaminen</h2>

<h2>Ruokalistan generoiminen</h2>
