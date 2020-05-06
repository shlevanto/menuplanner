<h1>Arkkitehtuurikuvaus</h1>

Sovellus koostuu kolmesta pakkaustasosta: käyttöliittymä (ui), sovelluslogiikka (domain) ja tietokantayhteyden käsittely (dao). Lisäksi on omana pakkaukseen eriytetty konfiguraatiotiedostoa käsittelevä toiminnallisuus (setup).

Pakkausten väliset suhteet ilmenevät pakkauskaaviosta.

<h2>Pakkauskaavio</h2>

![alt text](images/Menuplannerpack.png)

Ohjelman pakkauksissa on eriytetty toisistaan käyttöliitttymä, sovelluslogiikka ja tietokantayhteyttä käsittelevät DAO -luokat. Lisäksi ohjelman sisältää Main -luokan, joka käynnistää sovelluksen käyttöliittymän ja Setup -luokan, joka käsittelee konfiguraatiotiedoston.

<h2>Luokkakaavio</h2>

![alt text](images/MenuplannerUML.png)

<h2>Käyttöliittymä</h2>

Käyttöliittymä tarjoaa viisi erilaista näkymää:

- kirjautuminen
- uuden käyttäjän luominen
- päänäkymä
- reseptien lisääminen
- reseptien poistaminen

Näkymät on toteutettu erillisinä metodeina käyttäen Scene -olioita. Reseptien lisääminen ja poistaminen on toteutettu luomalla uusi Stage -olio, jotta näkymä avautuu päänäkymän oheen. Käyttöliittymä on toteutettu luokassa [GraphicUI](../Menuplanner/src/main/java/ohte/ui/GraphicUI).

Käyttöliittymä alustaa ohjelman tarvitsemat tiedot luokan [Setup](../Menuplanner/src/main/java/ohte/setup/Setup.java) avulla ja kutsu parametrien avulla pakkauksen [domain](../Menuplanner/src/main/java/ohte/domain) luokkia.

<h2>Tietokanta</h2>

Sovellus tallettaa tietoja kahteen tietokantaan. Käyttäjätietokanta (oletusarvona users.db) sisältää käyttäjätunnukset. Kullekin käyttäjälle luodaan oma reseptitietokanta, joka sisältää käyttäjäkohtaiset reseptit ja menun muodostamiseen käytettävät aikaleimat. Tietokantana on käytetty sqlite -tietokantaa ja sitä ohjataan sovelluksessa jdbc -ajurin versiolla 3.38.0.

![alt text](images/tietokannat.png)

<h2>Päätoiminnallisuudet</h2>

Ohjelman päätoiminnallisuudet on kuvattu sekvenssikaavioilla.

<h3>Käyttäjän lisääminen</h3>

![alt text](images/createUserSequence.png)

<h3>Käyttäjän kirjautuminen</h3>

![alt text](images/userLoginSequence.png)

<h3>Uuden reseptin tallentaminen</h3>

![alt text](images/Menuplanner_new_recipe_sequence.png)

<h3>Reseptin poistaminen</h3>

![alt text](images/delRecipeSequence.png)

<h3>Menun generoiminen</h3>

![alt text](images/generateMenuSequence.png)

<h2>Konfiguraatiotiedosto</h2>

Sovelluskansion tulee sisältää konfiguraatiotiedoston [config.properties](../Menuplanner/config.properties). Tiedosto on ladattavissa releasen yhteydessä.

Tiedostossa määritellään käyttäjätietokannan nimi, oletusarvoisesti uuden käyttäjän käyttöön tulevat pääraaka-aineet ja lisukkeet sekä uuden käyttäjän tietokantaan generoitavat oletusreseptit. Tiedosto tukee UTF-8 merkistöä, joten teksteissä voi esiintyä ääkkösiä.

Pääraaka-aineet ja lisukkeet on erotettu pilkuilla

<code>liha,kala,kasvis</code>

ja reseptit pilkuilla mutta siten että jokaista reseptiä kohden on oltava nimi, pääraakaine ja lisukkeet

<code>makaronilaatikko,liha,pasta</code>

<h2>Kehityskohteet</h2>

Käyttäjätietojen ja reseptien erottaminen omiin tietokantoihinsa ei todennäköisesti ole paras mahdollinen ratkaisu. Tietojen yhdistäminen samaan tietokantaan niin, että jokaiselle käyttäjälle näkyisivät vain hänen omat reseptinsä, olisi kuitenkin vaatinut useampia tietokantatauluja ja siksi päädyin yksinkertaisuuden vuoksi tähän ratkaisuun. Sovelluksen jatkokehityksessä tulisi yhdistää kaikki tiedot yhteen tietokantaaan jotta se ei generoisi suurta määrää erillisiä käyttäjäkohtaisia tietokantoja.

Konfiguraatiotiedostossa reseptit olisi kannattanut erottaa esim. puolipisteellä niin, ettei yksittäinen puuttuva pilkku johda virheellisesti luettuihin resepteihin, esim.

<code>makaronilaatikko,liha,pasta;kalakeitto,kala,keitto</code>

DAO -luokkiin on jäänyt resetitietojen päivittämisen osalta turhaa koodia, koska tässä toteutuksessa respeteistä päivittyvä vain date -kentät. Alkuperäisenä ajatuksena oli, että käyttäjä voisi muokata tarvittaessa reseptien kaikkia tietoja mutta tämä toiminnallisuus jätettiin toteuttamatta ja siirrettiin jatkokehitysideoihin.
