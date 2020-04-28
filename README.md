<h1>Ohjelmistotekniikka harjoitustyö - Menuplanner</h1>

Menuplanner auttaa arkiviikon ruokalistojen suunnittelussa. Sovellukseen on mahdollista tallettaa ruokien nimiä ja se generoi viikottaisen ruokalistan ruokien ominaisuuksien perusteella.

Jokaiselle ruoalle voidaan määritellä ominaisuuksiksi pääraaka-aine (esim. liha, kala, kasvis) ja lisuke (esim. peruna, riisi, pasta, keitto). Sovellus generoi ruokalistat siten, että kaksi samaa lisuketta ei tule peräkkäisille päiville. Lisäksi sovellus pitää kirjaa siitä, mitä viimeisimmän viikon ruokalistalla on ollut jotta samat ruoat eivät toistu liian usein.

Sovelluksessa on käyttäjähallinta, siihen on mahdollista kirjautua eri käyttäjätunnuksilla joista jokaisella on oma reseptitietokantansa.

<h2>Release</h2>

[viikko 6, graafisella käyttöliittymällä](https://github.com/shlevanto/menuplanner/releases/tag/OhTe)

[viikko 5, tekstikäyttöliittymällä](https://github.com/shlevanto/menuplanner/releases/tag/vko5)

<h2>Dokumentaatio</h2>

[Käyttöohje](/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](/dokumentaatio/testausdokumentti.md)

[Tuntikirjanpito](/dokumentaatio/tuntikirjanpito.md)

<h2>Komentorivitoiminnot</h2>

<h3>Testaus</h3>

Testit suoritetaan komennolla

<code>mvn test </code>

Testikattavuusraportti luodaan komennolla

<code>mvn jacoco:report</code>

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

<h3>Sovelluksen käynnistäminen komentoriviltä</h3>

Komento

<code>mvn compile exec:java -Dexec.mainClass=ohte.main.Main</code>

rakentaa ja suorittaa sovelluksen komentoriviltä.

<h3>Suoritettavan tiedoston generoiminen</h3>

Suoritettavan jarin generointi tapahtuu komennolla

<code>mvn package</code>

Komento generoi hakemistoon target suoritettavan jar-tiedoston Menuplanner-1.0-SNAPSHOT.jar

<h3>Checkstyle</h3>

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

<code>mvn jxr:jxr checkstyle:checkstyle</code>

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html

<h3>JavaDoc</h3>

JavaDoc generoidaan komennolla

<code>mvn javadoc:javadoc</code>

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html
