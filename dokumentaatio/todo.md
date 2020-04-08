DL 21.4.2020!!

- viikon 5 deadlinen jälkeen tulee palautusrepositoriosta löytyä osan määrittelydokumentin käyttäjälle näkyvää toiminnallisuutta toteuttava toimiva versio harjoitustyöstä
- viikon 5 palautuksesta tulee saada enemmän kuin 0 pistettä
~~- palautusrepositorioistasi on GitHub-issuet saalittuna, ks. [ohje](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020/blob/master/tehtavat/harjoitustyo_viikko5.md#issueiden-salliminen)~~

Arvostelussa kiinnitetään huomiota seuraaviin seikkoihin

- Ohjelmasta pystyy tekemään suorituskelpoisen [jar](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020/blob/master/web/maven.md#jarin-generointi)-tiedoston komennolla _mvn package_ (0.5p)
- Projektista on tehty _github release_ (0.25p)
  - Release sisältää ohjelman uusimman version suorituskelpoisen jar-tiedoston
  - [Ohje](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020/blob/master/web/release.md) releasen tekemiseen
  - Releaseen on linkki projektin README:stä
- Ohjelma on kasvanut edellisestä viikosta (0.75p)
  - Ohjelman suoritettavissa oleva versio on kasvanut edellisestä viikosta _ja_ toteuttaa edellisen viikon versiota suuremman osan määrittelydokumentin toiminnallisuudesta eli jotain käyttäjälle näkyvää hyödyllistä toiminnallisuutta.
    Merkitse lisäksi tarkastusta varten määrittelydokumenttiin tällä viikolla tekemäsi toiminnallisuudet "tehty" merkinnällä.
- Testaus on edennyt (0.5p)
  - Sovellukselle tulee pystyä generoimaan testikattavuusraportti komennolla <code>mvn test jacoco:report</code>
  - Käyttöliittymän rakentava koodi [jätetään pois](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020/blob/master/web/maven.md#koodin-huomiotta-jättäminen-kattavuusraportissa) testikattavuusraportista
  - Sovelluksen testien rivikattavuuden tulee olla vähintään 40%
  - Testien tulee olla mielekkäitä, eli niiden on testattava jotain ohjelman kannalta merkityksellistä asiaa
- Koodin laatu (0.5p)
  - Sovelluslogiikka on eriytetty käyttöliittymästä
    - Vihjeitä [täällä](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020/blob/master/web/java.md) ja [referenssisovelluksessa](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/arkkitehtuuri.md)
  - Ohjelman [pakkausrakenne](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020/blob/master/web/koodin_laatuvaatimukset.md#5-pakkaukset) heijastaa ohjelman loogista rakennetta ja on nimennältään järkevä
  - Checkstyle on käytössä
    - Täydet pisteet Checkstylestä ainoastaan jos ohjelmassa on alle 5 Checkstyle-virhettä
    - Käyttöliittymän rakentavan koodin ei tarvitse olla Checkstyle-tarkastelun alla
  - Ohjelma ei sisällä suurta määrää toisteista koodia
- Ohjelman dokumentaatiossa on ainakin yksi sen jotain oleellista toiminnallisuutta kuvaava sekvenssikaavio (0.5p)
  - Mallia voi ottaa [referenssisovelluksesta](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/arkkitehtuuri.md#sovelluslogiikka)
  - Lisää kaavio [edellisellä viikolla](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020/blob/master/tehtavat/harjoitustyo_viikko4.md) tehtyyn dokumenttiin _arkkitehtuuri.md_
  - Tiedostoon _arkkitehtuuri.md_ tulee olla linkki repositorion README:stä [referenssisovelluksen](https://github.com/mluukkai/OtmTodoApp) tavoin

**Seuraavien kohtien puutteet vähentävät pisteitä**

- Koodin laatu
  - Pakkausrakenne ei ole järkevä (esim. kaikki koodi oletuspakkauksessa)
  - Sovelluslogiikkaa ei ole eriytetty riittävästi käyttöliittymästä
- Tuntikirjanpito on ajantasalla
  - **Tuntien summan tulee olla merkittynä**
  - Tuntikirjanpitoon ei merkitä laskareihin käytettyä aikaa
~~- Palautusrepositorioosi voi tehdä GitHub-issuieta, ks [ohje](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020/blob/master/tehtavat/harjoitustyo_viikko5.md#issueiden-salliminen)~~
- Repositorion README.md kunnossa
  - tiedosto on kurssin tämän vaiheen osalta relevantin sisällön suhteen samankaltainen kuin [referenssisovelluksen](https://github.com/mluukkai/OtmTodoApp) README.md, eli siellä on ainakin seuraavat
    ~~- harjoitustyön aiheen lyhyt kuvaus~~
    ~~- linkit vaatimusmäärittelyyn, arkkitehtuuridokumenttiin ja tuntikirjanpitoon~~
    - linkki releaseen
    ~~- ohjeet komentoriviltä suoritettaviin toimenpiteisiin (testaus, testiraportin suoritus, suoritettavan jarin generointi, checkstyletarkastuksen suorittaminen)~~

<h2>Lisäksi</h2>

~~- käyttäjätietokannan nimi config -tiedostosta~~

~~- mahdollisuus määritellä proteiinit ja lisukkeet config -tiedostossa~~

- testejä varten testitietokanta, User on tehty pitää vielä tehdä recipes
