<h1>Testausdokumentti</h1>

Ohjelmaa on testattu JUnitilla automatisoiduin testein sekä manuaalisesti. Käyttöliittymän toiminta on testattu ainoastaan manuaalisesti ja niitä ei ole laskettu testikattavuusraportteihin mukaan.

<h2>JUnit -testit</h2>

<h3>Sovelluslogiikka (domain)</h3>

<h3>DAO</h3>

Testiluokka generoi dao -luokkien testaamista varten testitietokannat, jotka poistetaan automaattisesti testiajon päätyttyä.

<h3>Konfiguraatiotiedoston käsitteleminen (setup)</h3>

<h3>Testauskattavuus</h3>

Käyttöliittymä ja main on jätetty testauskattavuuden ulkopuolelle. Main -luokan ainoa toiminnallisuus on käyttöliittymän käynnistäminen.

![alt text](images/jacoco.png)

<h2>Järjestelmätestaus</h2>

<h3>Asennus ja konfigurointi</h3>

Ladattu ja testattu Linux -ympäristössä fuksiläppärillä (Cubbli Linux), ssh -yhteydellä melkki.cs.helsinki.fi ja virtuaalityöasemassa VDI.

Sovellusta testatessa on käyty läpi sekä tilanne, jossa suorituskansiossa ei ole tarvittavia tietokantatiedostoja jolloin sovellus generoi ne, että tilanne jossa tietokantatiedostot ovat kansiossa valmiina.

<h3>Toiminnallisuudet</h3>

Kaikki määrittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi manuaalisesti ja automaattisin testein.

<h3>Käyttöliittymä</h3>

Käyttöliittymää on testattu manuaalisesti käymällä järjestelmällisesti läpi eri toiminnot ja syöttämällä niihin sekä sallittuja että tyhjiä syötteitä.

<h2>Sovellukseen jääneet ongelmat</h2>

Jos tietokannassa on alle 5 reseptiä, sovellus generoi tyhjän ruokalistan ja ilmoittaa ettei ruokalistaa voi generoida.
