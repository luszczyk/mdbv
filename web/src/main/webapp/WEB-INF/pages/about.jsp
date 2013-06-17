<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div>

    <h2>Instrukcja u&#380;ytkownika</h2>

    <p>Aplikacja Multimedia Data Base Viewer &#8211; MDBV udost&#281;pnia innowacyjn&#261; funkcjonalno&#347;&#263; nieznan&#261; dot&#261;d
        z innych program&#243;w klienckich do baz danych. Jest to pierwsza aplikacja pozwalaj&#261;ca na
        jednoczesny podgl&#261;d danych multimedialnych takich typ&#243;w jak: obrazy, animacje, obiekty geometryczne,
        nagrania d&#378;wi&#281;kowe, filmy.</p>

    <h4>Prezentacja interfejsu</h4>

    <p>Interfejs aplikacji MDBV mo&#380;na podzieli&#263; zasadniczo na trzy cz&#281;&#347;ci:</p>

    <ul><ol>menu g&#322;&#243;wne</ol>
        <ol>informacje o aktualnym po&#322;&#261;czeniu</ol>
        <ol>zawarto&#347;&#263; zale&#380;na od aktualnego kontekstu aplikacji</ol>
        <p>Poni&#380;szy zrzut z ekranu aplikacji rysunek 18 prezentuje rozk&#322;ad element&#243;w interfejsu.</p>

        <p><img src="/web/resources/images/about/rys_18.png" /></p>

        <p>Menu g&#322;&#243;wne umo&#380;liwia poruszanie si&#281; pomi&#281;dzy g&#322;&#243;wnymi funkcjonalno&#347;ciami systemu.
            Ich funkcjonalno&#347;&#263; zale&#380;y jednak od stanu aplikacji. W przypadku braku aktywnego po&#322;&#261;czenia z baz&#261; danych przyciski Query oraz DB Details przekierowuj&#261; u&#380;ytkownika na stron&#281; dzi&#281;ki kt&#243;rej mo&#380;emy takie po&#322;&#261;czenie utworzy&#263;. W sytuacji gdy u&#380;ytkownik posiada aktywne po&#322;&#261;czenie bazodanowe, przycisk Query przekierowuje go na stron&#281; zaprezentowan&#261; przez rysunek 19.

        <p><img src="/web/resources/images/about/rys_19.png" /></p>


        Po wprowadzeniu zapytania i naci&#347;ni&#281;ciu przycisku Go aplikacja wy&#347;wietla wynik zapytania lub zwraca komunikat b&#322;&#281;du w oknie modalnym. Pole zapytania SQL mo&#380;na rozszerzy&#263; w dolnym kierunku przy pomocy przycisku enter, dzi&#281;ki tej funkcjonalno&#347;ci u&#380;ytkownik ma mo&#380;liwo&#347;&#263; konstrukcji z&#322;o&#380;onych zapyta&#324;. Po wykonaniu zapytania u&#380;ytkownikowi prezentowane s&#261; nie tylko wyniki zapytania ale r&#243;wnie&#380; sama kwerenda, tak aby umo&#380;liwi&#263; analiz&#281; zapytania oraz wynik&#243;w na jednej stronie. Kolejn&#261; zak&#322;adk&#281; w menu g&#322;&#243;wnym stanowi odno&#347;nik Connection dzi&#281;ki kt&#243;remu mo&#380;emy wprowadza&#263; dane potrzebne do po&#322;&#261;czenia z baz&#261; danych. Stron&#281; Connection prezentuje rysunek 18. Po zmianie typu bazy danych, aktualnie aplikacja obs&#322;uguje bazy MySQL oraz PostgreSQL, port bazy danych zostanie automatycznie ustawiony jest na domy&#347;lny dla wybranego typu bazy danych. W kolejnej zak&#322;adce DB Details u&#380;ytkownik uzyskuje dost&#281;p do przegl&#261;dania bazy danych w oparciu o posiadane prawa. W tym miejscu u&#380;ytkownik b&#281;dzie m&#243;g&#322; sprawdzi&#263; dost&#281;pne bazy danych na danym serwerze, wy&#347;wietli&#263; list&#281; schemat&#243;w dla wybranej bazy oraz wy&#347;wietli&#263; list&#281; tabel wchodz&#261;cych w sk&#322;ad schematu bazy danych.

        <p><img src="/web/resources/images/about/rys_20.png" /></p>
        Rysunek 20 prezentuje stron&#281; DB Details dla przyk&#322;adowej bazy danych. Ostatnia zak&#322;adka About stanowi &#378;r&#243;d&#322;o informacji dla u&#380;ytkownik&#243;w i zawiera instrukcj&#281; obs&#322;ugi aplikacji.
        W prawym rogu aplikacji znajduje si&#281; obszar odpowiadaj&#261;cy za prezentacj&#281; statusu aktualnego po&#322;&#261;czenia z baz&#261; danych. W przypadku braku po&#322;&#261;czenia w obszarze tym wy&#347;wietlany jest komunikat No connection database. W sytuacji gdy aplikacja jest po&#322;&#261;czona z baz&#261; danych, wy&#347;wietlany s&#261; parametry po&#322;&#261;czenia: adres serwera, u&#380;ytkownik, typ bazy danych oraz nazwa bazy danych. Ostatni&#261; cz&#281;&#347;&#263; tej cz&#281;&#347;ci aplikacji stanowi przycisk Disconnect s&#322;u&#380;&#261;cy do zako&#324;czenia po&#322;&#261;czenia z baz&#261; danych.</p>

        <p>Centralna cz&#281;&#347;&#263; aplikacji nale&#380;y do najbardziej dynamicznych cz&#281;&#347;ci systemu. Zawarto&#347;&#263; tego fragmentu zmienia si&#281; w zale&#380;no&#347;&#263; od kontekstu aplikacji i stanowi przestrze&#324; interakcji z u&#380;ytkownikiem poprzez dynamiczne przyciski oraz formularze kt&#243;re stanowi&#261; jej cz&#281;&#347;&#263;.</p>

        <h5>Po&#322;&#261;czenie z baz&#261; danych</h5>

        <p>Aplikacja umo&#380;liwia zdefiniowanie parametr&#243;w po&#322;&#261;czenia z baz&#261; danych w zak&#322;adce Connection menu g&#322;&#243;wnego. Po wpisaniu parametr&#243;w aplikacja wykona pr&#243;b&#281; po&#322;&#261;czenia ze wskazan&#261; baz&#261; danych. W przypadku podania b&#322;&#281;dnych danych, np. nieprawid&#322;owego has&#322;a, wy&#347;wietlony zostanie komunikat o b&#322;&#281;dzie w oknie modalnym w prawym g&#243;rnym rogu. Opisan&#261; sytuacj&#281; prezentuje rysunek 21.


        <p><img src="/web/resources/images/about/rys_21.png" /></p>
        Je&#380;eli za&#347; dane po&#322;&#261;czenie s&#261; prawid&#322;owe, u&#380;ytkownik zostanie przekierowany na stron&#281; Query dzi&#281;ki kt&#243;rej b&#281;dzie m&#243;g&#322; wykona&#263; zapytanie SQL na bazie danych.</p>


        <h5>Wykonanie zapytania na bazie danych</h5>

        <p>Wykonywanie zapyta&#324; SQL mo&#380;liwe jest po klikni&#281;ciu na przycisk Query w g&#322;&#243;wnym menu aplikacji przy aktywnym po&#322;&#261;czeniu z baz&#261; danych. Aplikacja MDBV umo&#380;liwia jedynie wykonywanie zapyta&#324; SELECT. Interfejs aplikacji umo&#380;liwia wprowadzanie wielopoziomowych zapyta&#324; zawieraj&#261;cych wiele linii. W przypadku wprowadzenia nieprawid&#322;owej syntaktycznie kwerendy u&#380;ytkownikowi zostanie wy&#347;wietlona wiadomo&#347;&#263; z serwera bazodanowego w oknie modalnym. Je&#380;eli za&#347; kwerenda by&#322;a poprawna wynik zapytania zostanie wy&#347;wietlony w postaci tabeli.</p>

        <h5>Wy&#347;wietlenie szczeg&#243;&#322;&#243;w rekordu tabeli wyniku zapytania</h5>

        <p>Wykonanie zapytania na bazie danych skutkuje w MDBV wy&#347;wietleniem tabeli rekord&#243;w. Istnieje mo&#380;liwo&#347;&#263; wy&#347;wietlenia szczeg&#243;&#322;&#243;w klikaj&#261;c na wybrany rekord.


        <p><img src="/web/resources/images/about/rys_23.png" /></p>

        Rysunek 23 prezentuje szczeg&#243;&#322;y jednego z rekord&#243;w. Widoczne jest rozr&#243;&#380;nienie dla typ&#243;w bazodanowych oraz typ&#243;w multimedialnych. Dla tych pierwszych wy&#347;wietlany jest typ, nazwa pola oraz zawarto&#347;&#263;. Dla typu multimedialnego dodatkowo wy&#347;wietlana jest informacja o rozpoznanym typie multimedialnym oraz link do prezentacji danych. W przypadku nierozpoznanego typu danych aplikacja wy&#347;wietli jedynie pocz&#261;tkowe bajty zawarto&#347;ci.</p>

        <h5>Wy&#347;wietlanie zawarto&#347;ci multimedialnej</h5>

        <p>W sytuacji gdy aplikacja rozpozna zawarto&#347;&#263; pola krotki jako typ multimedialny, u&#380;ytkownik otrzymuje mo&#380;liwo&#347;&#263; wy&#347;wietlenia tej zawarto&#347;ci. Prezentacja zawarto&#347;ci multimedialnej odbywa si&#281; w oknie modalnym.


        <p><img src="/web/resources/images/about/rys_24.png" /></p>

        Rysunek 24 przedstawia spos&#243;b udost&#281;pnienia filmu zapisanego w bazie danych. Dla multimedialnych zasob&#243;w bazy danych przypisane s&#261; odpowiednie odtwarzacze. W przypadku rysunku 24 jest to odtwarzacz wideo. Aktualna wersja aplikacji MDBV umo&#380;liwia tak&#380;e prezentacj&#281; obraz&#243;w, animacji, obiekt&#243;w geometrycznych oraz nagra&#324; d&#378;wi&#281;kowych. Spos&#243;b prezentacji pierwszych trzech typ&#243;w jest do siebie mocno zbli&#380;ony i na&#347;laduje typowe odtwarzacze dla tych format&#243;w. Zawarto&#347;&#263; geometryczna jest za&#347; prezentowana na dwa sposoby. Pierwszy przedstawia obiekty geometryczne na mapie &#346;wiata pobieranej za pomoc&#261; biblioteki OpenLayer. Drugi za&#347; przedstawia obiekty geometryczne na bia&#322;ym tle. Wymogiem prawid&#322;owego dzia&#322;ania pierwszego rodzaju prezentacji jest dost&#281;p do Internetu na maszynie na kt&#243;rej znajduje si&#281; zainstalowana aplikacja. Interfejs zastosowany do prezentacji danych geometrycznych umo&#380;liwia przesuwanie mapy oraz przybli&#380;anie i oddalanie. Przyk&#322;adow&#261; prezentacj&#281; obiektu geometrycznego na mapie prezentuje rysunek 25.

        <p><img src="/web/resources/images/about/rys_25.png" /></p>

        </p>





        <h5>Przegl&#261;danie dost&#281;pnych baz danych</h5>

        <p>MDBV umo&#380;liwia przegl&#261;danie dost&#281;pnych baz danych dla po&#322;&#261;czonego serwera. Po wybraniu bazy danych aplikacja wy&#347;wietla schematy przypisane do danej bazy. Klikni&#281;cie na nazw&#281; schematu, powoduje wy&#347;wietlenie tabel b&#281;d&#261;cych cz&#281;&#347;ci&#261; schematu. Klikni&#281;cie na konkretn&#261; tabel&#281;, powoduje przekierowanie do cz&#281;&#347;ci aplikacji odpowiadaj&#261;cej za wprowadzanie zapyta&#324;. Kwerenda zostanie automatycznie skonstruowana dla wybranej tabeli. Rysunek 20 prezentuje mo&#380;liwo&#347;ci interfejsu zwi&#261;zane z przegl&#261;daniem dost&#281;pnych baz danych.</p>

</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>