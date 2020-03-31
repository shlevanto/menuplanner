<h1>Ohjelmistotekniikka harjoitustyö - Menuplanner</h1>

Menuplanner auttaa arkiviikon ruokalistojen suunnittelussa. Sovellukseen on mahdollista tallettaa ruokien nimiä ja se generoi viikottaisen ruokalistan ruokien ominaisuuksien perusteella.

Jokaiselle ruoalle voidaan määritellä ominaisuuksiksi pääaraaka-aine (esim. liha, kala, kasvis) ja lisuke (esim. peruna, riisi, pasta, keitto). Sovellus generoi ruokalistat siten, että kaksi samaa pääraaka-ainetta tai lisuketta ei tule peräkkäisille päiville. Lisäksi sovellus pitää kirjaa siitä, mitä viimeisimmän viikon ruokalistalla on ollut jotta samat ruoat eivät toistu liian usein.

Sovelluksessa on käyttäjähallinta, siihen on mahdollista kirjautua eri käyttäjätunnuksilla joista jokaisella on oma reseptitietokantansa.

<h2>Dokumentaatio</h2>

Käyttöohje

[Vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)

Arkkitehtuurikuvaus

Testausdokumentti

[Tuntikirjanpito](/dokumentaatio/tuntikirjanpito.md)

<h2>Komentorivitoiminnot</h2>

<h3>Testaus</h3>

Testit suoritetaan komennolla

``` mvn test ```

Testikattavuusraportti luodaan komennolla

``` mvn jacoco:report ```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

<h3>Suoritettavan tiedoston generoiminen</h3>

Suoritettavan jarin generointi tapahtuu komennolla

```mvn package```

Komento generoi hakemistoon target suoritettavan jar-tiedoston Menuplanner-1.0-SNAPSHOT.jar

<!---
kommentti
--->
