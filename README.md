## Setup automated tests using Java and Maven ##

## Setup

#### 1. Zainstaluj Java development kit 11:
[JDK11 DOWNLOAD](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

#### 2. Nastęonie należy dodać Jave do zmiennych środowiskowych `PATH`:
[Instrukcja dla Windowsa](https://javatutorial.net/set-java-home-windows-10)

[Instrukcja dla Linuxa](https://www.cyberciti.biz/faq/linux-unix-set-java_home-path-variable/)

[Instrukcja dla MacOS](https://www.mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/)

#### 3. Instalujemy Mavena oraz również dodajemy go do zmiennych środowiskowych `PATH`:
[MAVEN DOWNLOAD](https://maven.apache.org/install.html)

#### 4. Używając https://chocolatey.org/ możemy zainstalować Google Chrome drivera potrzebnego do uruchamiania testów:
`choco install chromedriver`

W konsoli przy instalacji wyświetli nam się ścieżka, gdzie chromedriver.exe został zainstalowany. Alternatywnie możemy samodzielnie 
pobrać [CHROMEDRIVER](http://chromedriver.chromium.org/downloads) i dodać ścieżkę do zmiennej środowiskowej `PATH`, natomiast chocolatey powinien to zrobić za nas automatycznie.

#### 6.Możemy zweryfikować czy Java i Maven są poprawnie zainstalowane i skonfigurowane, wpisująć w lini komend:
`mvn --version` oraz `java --version`, powinniśmy ujrzeć aktualne ich wersje jak poniżej:
![IMAGE](https://i.imgur.com/ozT4gea.png)

#### 7. Teraz możemy spokojnie pobrać repozytorium naszych testów, przy użyciu:
`git clone git@github.com:rchowaniak/timecamp-serenity-bdd.git`

#### 8. Jeżeli wszystko poszło zgodnie z oczekiwaniami, przechodzimy do katalogu gdzie mamy repozytorium testów, otwieramy linie komend i wpisujemy:
`mvn clean verify`

Aktualnie w konsoli możemy zobaczyć testy odnośnie wykonywanych testów oraz na naszym komputerze uruchomi się przeglądarka, która automatycznie przeprowadzi
zaplanowane scenariusze testowe. W konsoli też zobaczymy informacje czy dany test zakończył się sukcesem czy fiaskiem.

Po wykonanych testach w folderze z repozytorium testów pojawi nam się dodatkowy folder o nazwie `target`. W tym folderze powinniśmy odnaleźć plik index.html, który
znajduje się pod ścieżką `/site/serenity/index.html`. 

Po jego uruchomieniu zobaczymy pełny raport wykonanych testów wraz ze zrzutami ekranu oraz historyjkami napisanymi w Gherkinie.
