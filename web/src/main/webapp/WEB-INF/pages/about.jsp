<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div>

    <h2>Instrukcja użytkownika</h2>

    <p>Aplikacja Multimedia Data Base Viewer – MDBV udostępnia innowacyjną funkcjonalność nieznaną dotąd
        z innych programów klienckich do baz danych. Jest to pierwsza aplikacja pozwalająca na
        jednoczesny podgląd danych multimedialnych takich typów jak: obrazy, animacje, obiekty geometryczne,
        nagrania dźwiękowe, filmy.</p>

    <h4>Prezentacja interfejsu</h4>

    <p>Interfejs aplikacji MDBV można podzielić zasadniczo na trzy części:</p>

    <ul><ol>menu główne</ol>
    <ol>informacje o aktualnym połączeniu</ol>
    <ol>zawartość zależna od aktualnego kontekstu aplikacji</ol>
    <p>Poniższy zrzut z ekranu aplikacji rysunek 18 prezentuje rozkład elementów interfejsu.</p>

        <p><img src="/web/resources/images/about/rys_18.png" /></p>

    <p>Menu główne umożliwia poruszanie się pomiędzy głównymi funkcjonalnościami systemu.
        Ich funkcjonalność zależy jednak od stanu aplikacji. W przypadku braku aktywnego połączenia z bazą danych przyciski Query oraz DB Details przekierowują użytkownika na stronę dzięki której możemy takie połączenie utworzyć. W sytuacji gdy użytkownik posiada aktywne połączenie bazodanowe, przycisk Query przekierowuje go na stronę zaprezentowaną przez rysunek 19.

        <p><img src="/web/resources/images/about/rys_19.png" /></p>


    Po wprowadzeniu zapytania i naciśnięciu przycisku Go aplikacja wyświetla wynik zapytania lub zwraca komunikat błędu w oknie modalnym. Pole zapytania SQL można rozszerzyć w dolnym kierunku przy pomocy przycisku enter, dzięki tej funkcjonalności użytkownik ma możliwość konstrukcji złożonych zapytań. Po wykonaniu zapytania użytkownikowi prezentowane są nie tylko wyniki zapytania ale również sama kwerenda, tak aby umożliwić analizę zapytania oraz wyników na jednej stronie. Kolejną zakładkę w menu głównym stanowi odnośnik Connection dzięki któremu możemy wprowadzać dane potrzebne do połączenia z bazą danych. Stronę Connection prezentuje rysunek 18. Po zmianie typu bazy danych, aktualnie aplikacja obsługuje bazy MySQL oraz PostgreSQL, port bazy danych zostanie automatycznie ustawiony jest na domyślny dla wybranego typu bazy danych. W kolejnej zakładce DB Details użytkownik uzyskuje dostęp do przeglądania bazy danych w oparciu o posiadane prawa. W tym miejscu użytkownik będzie mógł sprawdzić dostępne bazy danych na danym serwerze, wyświetlić listę schematów dla wybranej bazy oraz wyświetlić listę tabel wchodzących w skład schematu bazy danych.

        <p><img src="/web/resources/images/about/rys_20.png" /></p>
        Rysunek 20 prezentuje stronę DB Details dla przykładowej bazy danych. Ostatnia zakładka About stanowi źródło informacji dla użytkowników i zawiera instrukcję obsługi aplikacji.
    W prawym rogu aplikacji znajduje się obszar odpowiadający za prezentację statusu aktualnego połączenia z bazą danych. W przypadku braku połączenia w obszarze tym wyświetlany jest komunikat No connection database. W sytuacji gdy aplikacja jest połączona z bazą danych, wyświetlany są parametry połączenia: adres serwera, użytkownik, typ bazy danych oraz nazwa bazy danych. Ostatnią część tej części aplikacji stanowi przycisk Disconnect służący do zakończenia połączenia z bazą danych.</p>

        <p>Centralna część aplikacji należy do najbardziej dynamicznych części systemu. Zawartość tego fragmentu zmienia się w zależność od kontekstu aplikacji i stanowi przestrzeń interakcji z użytkownikiem poprzez dynamiczne przyciski oraz formularze które stanowią jej część.</p>

    <h5>Połączenie z bazą danych</h5>

        <p>Aplikacja umożliwia zdefiniowanie parametrów połączenia z bazą danych w zakładce Connection menu głównego. Po wpisaniu parametrów aplikacja wykona próbę połączenia ze wskazaną bazą danych. W przypadku podania błędnych danych, np. nieprawidłowego hasła, wyświetlony zostanie komunikat o błędzie w oknie modalnym w prawym górnym rogu. Opisaną sytuację prezentuje rysunek 21.


        <p><img src="/web/resources/images/about/rys_21.png" /></p>
            Jeżeli zaś dane połączenie są prawidłowe, użytkownik zostanie przekierowany na stronę Query dzięki której będzie mógł wykonać zapytanie SQL na bazie danych.</p>


    <h5>Wykonanie zapytania na bazie danych</h5>

        <p>Wykonywanie zapytań SQL możliwe jest po kliknięciu na przycisk Query w głównym menu aplikacji przy aktywnym połączeniu z bazą danych. Aplikacja MDBV umożliwia jedynie wykonywanie zapytań SELECT. Interfejs aplikacji umożliwia wprowadzanie wielopoziomowych zapytań zawierających wiele linii. W przypadku wprowadzenia nieprawidłowej syntaktycznie kwerendy użytkownikowi zostanie wyświetlona wiadomość z serwera bazodanowego w oknie modalnym. Jeżeli zaś kwerenda była poprawna wynik zapytania zostanie wyświetlony w postaci tabeli.</p>

    <h5>Wyświetlenie szczegółów rekordu tabeli wyniku zapytania</h5>

        <p>Wykonanie zapytania na bazie danych skutkuje w MDBV wyświetleniem tabeli rekordów. Istnieje możliwość wyświetlenia szczegółów klikając na wybrany rekord.


        <p><img src="/web/resources/images/about/rys_23.png" /></p>

            Rysunek 23 prezentuje szczegóły jednego z rekordów. Widoczne jest rozróżnienie dla typów bazodanowych oraz typów multimedialnych. Dla tych pierwszych wyświetlany jest typ, nazwa pola oraz zawartość. Dla typu multimedialnego dodatkowo wyświetlana jest informacja o rozpoznanym typie multimedialnym oraz link do prezentacji danych. W przypadku nierozpoznanego typu danych aplikacja wyświetli jedynie początkowe bajty zawartości.</p>

    <h5>Wyświetlanie zawartości multimedialnej</h5>

        <p>W sytuacji gdy aplikacja rozpozna zawartość pola krotki jako typ multimedialny, użytkownik otrzymuje możliwość wyświetlenia tej zawartości. Prezentacja zawartości multimedialnej odbywa się w oknie modalnym.


        <p><img src="/web/resources/images/about/rys_24.png" /></p>

            Rysunek 24 przedstawia sposób udostępnienia filmu zapisanego w bazie danych. Dla multimedialnych zasobów bazy danych przypisane są odpowiednie odtwarzacze. W przypadku rysunku 24 jest to odtwarzacz wideo. Aktualna wersja aplikacji MDBV umożliwia także prezentację obrazów, animacji, obiektów geometrycznych oraz nagrań dźwiękowych. Sposób prezentacji pierwszych trzech typów jest do siebie mocno zbliżony i naśladuje typowe odtwarzacze dla tych formatów. Zawartość geometryczna jest zaś prezentowana na dwa sposoby. Pierwszy przedstawia obiekty geometryczne na mapie Świata pobieranej za pomocą biblioteki OpenLayer. Drugi zaś przedstawia obiekty geometryczne na białym tle. Wymogiem prawidłowego działania pierwszego rodzaju prezentacji jest dostęp do Internetu na maszynie na której znajduje się zainstalowana aplikacja. Interfejs zastosowany do prezentacji danych geometrycznych umożliwia przesuwanie mapy oraz przybliżanie i oddalanie. Przykładową prezentację obiektu geometrycznego na mapie prezentuje rysunek 25.

        <p><img src="/web/resources/images/about/rys_25.png" /></p>

        </p>





    <h5>Przeglądanie dostępnych baz danych</h5>

        <p>MDBV umożliwia przeglądanie dostępnych baz danych dla połączonego serwera. Po wybraniu bazy danych aplikacja wyświetla schematy przypisane do danej bazy. Kliknięcie na nazwę schematu, powoduje wyświetlenie tabel będących częścią schematu. Kliknięcie na konkretną tabelę, powoduje przekierowanie do części aplikacji odpowiadającej za wprowadzanie zapytań. Kwerenda zostanie automatycznie skonstruowana dla wybranej tabeli. Rysunek 20 prezentuje możliwości interfejsu związane z przeglądaniem dostępnych baz danych.</p>

</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>