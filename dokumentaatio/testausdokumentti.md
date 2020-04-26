<h1>Testausdokumentti</h1>

Ohjelmaa on testattu automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti. Käyttöliittymien toiminta on testattu ainoastaan manuaalisesti ja niitä ei ole laskettu testikattavuusraportteihin mukaan.

<h2>Yksikkö- ja integraatiotestaus</h2>

<h3>Sovelluslogiikka (domain)</h3>

<h3>DAO</h3>

<h3>Konfiguraatiotiedoston käsitteleminen (setup)</h3>

<h3>Testauskattavuus</h3>

<h2>Järjestelmätestaus</h2>

<h3>Asennus ja konfigurointi</h3>

Ladattu ja testattu Linux -ympäristössä.

Sovellusta on testattu sekä tilanteissa, joissa käyttäjät ja työt tallettavat tiedostot ovat olleet olemassa ja joissa niitä ei ole ollut jolloin ohjelma on luonut ne itse.

<h3>Toiminnallisuudet</h3>

Kaikki määrittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.

<h3>Käyttöliittymä</h3>

<h2>Sovellukseen jääneet ongelmat</h2>

Jos tietokannassa on alle 5 reseptiä, sovellus generoi tyhjän ruokalistan.

