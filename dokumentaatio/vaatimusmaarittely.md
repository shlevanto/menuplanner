<h1>Vaatimusmärittely</h1>

<h2>Sovelluksen tarkoitus</h2>
Menuplanner auttaa arkiviikon ruokalistojen suunnittelussa. Sovellukseen on mahdollista tallettaa ruokien nimiä ja se generoi viikottaisen ruokalistan ruokien ominaisuuksien perusteella.
Jokaiselle ruoalle voidaan määritellä ominaisuuksiksi pääaraaka-aine (esim. liha, kala, kasvis) ja lisuke (esim. peruna, riisi, pasta, keitto). Sovellus generoi ruokalistat siten, että kaksi samaa pääraaka-ainetta tai lisuketta ei tule peräkkäisille päiville. Lisäksi sovellus pitää kirjaa siitä, mitä viimeisimmän viikon ruokalistalla on ollut jotta samat ruoat eivät toistu liian usein.

<h2>Käyttäjät</h2>
Käyttäjärooleja on vain yksi: normaali käyttäjä. Käyttäjä voi lisätä ja poistaa ruokia sekä lisätä uusia pääraaka-aine- ja lisukevaihtoehtoja.

<h2>Perusversion tarjoama toiminnallisuus</h2>

* käyttäjähallinta **tehty**
  * uuden käyttäjän luominen **tehty**
  * käyttäjän sisäänkirjautuminen **tehty**
* käyttäjä voi listata järjestelmässä olevat reseptit ja niiden tiedot **tehty**
* käyttäjä voi lisätä uusia reseptejä **tehty**
* käyttäjä voi lisätä uusia pääraaka-aineita ja lisukkeita **config.properties**
* käyttäjä voi generoida arkiviikon ruokalistan **tehty**
* graafinen käyttöliittymä, tehdään 1. releasen jälkeen **tehty**
* ruokalista voidaan tallettaa erilliseen tiedostoon
* käyttäjä voi muokata ruokalajin nimeä, pääraaka-ainetta tai lisuketta

<h2>Jatkokehitysideoita</h2>

* resepteihin voi merkata kuinka moneksi päiväksi ruoka riittää
* reseptitietojen liittäminen ruokiin
* ostoslistan generoiminen viikon ruokalistan perusteella
* lisätunnuksen lisääminen ruokiin, esim. ruoan tyyli (aasialainen, italialainen, kevyt), valmistumisen nopeus
* generoidun ruokalistan muokkaaminen ennen sen tulostamista
* mahdollisuus valita tietyt ruokalajit listalle valmiiksi ja antaa sovelluksen arpoa loput
