# Instrukcja postawienia serwera aplikacyjnego

> Przed próbą wykonania poniższych kroków, należy pobrać cały serwer aplikacyjny
> z odpowiednią konfiguracją z podanego linku: 
> http:/eloelotubędzielink

Wykonaj poniższe kroki w celu konfiguracji aplikacji:
* sklonuj repozytorium na swój komputer
* otwórz projekt w IDE
* kliknij w zakładkę **Add configuration...** w celu dodania konfiguracji serwera GlassFish
* wybierz serwer aplikacyjny **GlassFish Local**
* w polu **Application Server** wskaż na katalog, gdzie przetrzymujesz pobrany z wyżej podanego linku serwer
* zmodyfikuj pole **URL** w taki sposób, aby wyglądało następująco: http://localhost:8080
* w polu **Server Domain** z listy rozwijanej wybierz wartość **domain1**
* przejdź do zakladki **Deployment** i przy użyciu plusika po prawej stronie, dodaj nowy **Artifact** o nazwie **PawTrello:war exploded**
* zaznacz checkbox **Use custom context root** i wpisz wartość /

Kolejnym krokiem jest utworzenie schematu bazy danych. W tym celu w **MySQL Workbench** należy utworzyć
nową bazę danych o nazwie **pawtrello**. Następnie przy użyciu pliku **create-db.sql** z katalogu resources/META-INF
skopiuj zawartość skryptu i wykonaj go na wcześniej utworzonym schemacie bazy danych.
Należy również ustawić w pliku **glassfish-resources.xml** dane autoryzacyjne do swojego schematu: 

```xml
<property name="user" value="root"/>
<property name="password" value="password"/>
```

Po tak przeprowadzonym procesie konfiguracji, można śmiało przystąpić do zbudowania aplikacji oraz uruchomienia serwera aplikacyjnego GlassFish.

# Przykładowe użycie usługi 
```sh
http://localhost:8080/api/auth/sign-up

body:
{
    "username": "testowy username",
    "password": "testowe haslo",
    "email: "testowy email"
}
```

# Stack technologiczny i narzędzia użyte do stworzenia aplikacji
* Java 8
* MySQL 
* EJB 3.1
* JAX-RS 
* GlassFish 5.0.0
* Hibernate 4.3.5
* JTA
* JPA
* Junit/Mockito
* Travis CI
* Trello
