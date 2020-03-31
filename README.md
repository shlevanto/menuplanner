<h1>Ohjelmistotekniikka harjoitustyö - Menuplanner</h1>
Tämä on Ohjelmistotekniikka -kurssin harjoitustyö.

Menuplanner auttaa arkiviikon ruokalistojen suunnittelussa. Sovellukseen on mahdollista tallettaa ruokien nimiä ja se generoi viikottaisen ruokalistan ruokien ominaisuuksien perusteella.

Jokaiselle ruoalle voidaan määritellä ominaisuuksiksi pääaraaka-aine (esim. liha, kala, kasvis) ja lisuke (esim. peruna, riisi, pasta, keitto). Sovellus generoi ruokalistat siten, että kaksi samaa pääraaka-ainetta tai lisuketta ei tule peräkkäisille päiville. Lisäksi sovellus pitää kirjaa siitä, mitä viimeisimmän viikon ruokalistalla on ollut jotta samat ruoat eivät toistu liian usein.

Sovelluksessa on käyttäjähallinta, siihen on mahdollista kirjautua eri käyttäjätunnuksilla joista jokaisella on oma reseptitietokantansa.

<h1>Dokumentaatio</h1>

Käyttöohje

[vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)

Arkkitehtuurikuvaus

Testausdokumentti

[tuntikirjanpito](/dokumentaatio/tuntikirjanpito.md)

Komentorivitoiminnot
Testaus
Testit suoritetaan komennolla

''' mvn test
Testikattavuusraportti luodaan komennolla

''' mvn jacoco:report
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

Suoritettavan jarin generointi
Komento

''' mvn package
generoi hakemistoon target suoritettavan jar-tiedoston Menuplanner-1.0-SNAPSHOT.jar

[//]: # JavaDoc
[//]: # JavaDocJavaDoc generoidaan komennolla

[//]: # JavaDoc''' mvn javadoc:javadoc
[//]: # JavaDocJavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html

[//]: # JavaDocCheckstyle
[//]: # JavaDocTiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

[//]: # JavaDoc ''' mvn jxr:jxr checkstyle:checkstyle
[//]: # JavaDoc Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
