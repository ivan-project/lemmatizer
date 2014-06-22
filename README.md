Lematyzator (zmienione - do aktualizacji)
==========

Jak to działa
----------
Program przyjmuje na wejście dwa parametry. Pierwszy,  plik który chcemy zlematyzować oraz drugi, plik wyjściowy do którego zostanie zapisany zlematyzowany tekst.

W razie problemów z działaniem programu wszystkie błędy zapisywane są do pliku exceptions.log znajdującego się w katalogu logs.


Uruchamiania
----------
Aby uruchomić program należy wykonać polecenie:

    ./run.sh [in] [out]
    
Gdzie in i out to obowiązkowe parametry programu.


Struktura bazy danych
-----------
Baza w której znajduje się słownik trzymana jest w Mongo i nazywa się dict. Całość podzielona została na kolekcje, których nazwy są pojedynczymi literami alfabetu. Każde słowo trafia do odpowiednij kolekcji na podstawie litery, od której owo słowo się rozpoczyna. Np. :

    słowo       kolekcja
    --------------------
    kot         k
    agrafka     a
    most        m
    yeti        y
    
Struktura kolekcji słada się ze słowa pochodnego (word) oraz odpowiadającego mu rdzenia (core). Np.:

    {"word" : "kabacki", "core" : "kabacki" }
    {"word" : "kabackich", "core" : "kabacki" }
    {"word" : "kabackie", "core" : "kabacki" }
    {"word" : "kabackiego", "core" : "kabacki" }
    {"word" : "kabackiej", "core" : "kabacki" }
    {"word" : "kabackiemu", "core" : "kabacki" }
    {"word" : "kabackim", "core" : "kabacki" }
    {"word" : "kabackimi", "core" : "kabacki" }
    {"word" : "kabacku", "core" : "kabacki" }
    {"word" : "kabaczek", "core" : "kabaczek" }
    {"word" : "kabaczka", "core" : "kabaczek" }
    {"word" : "kabaczkach", "core" : "kabaczek" }
    {"word" : "kabaczkami", "core" : "kabaczek" }
    {"word" : "kabaczki", "core" : "kabaczek" }
    {"word" : "kabaczkiem", "core" : "kabaczek" }
    {"word" : "kabaczkom", "core" : "kabaczek" }
    {"word" : "kabaczkowi", "core" : "kabaczek" }
    {"word" : "kabaczków", "core" : "kabaczek" }
    {"word" : "kabaczku", "core" : "kabaczek" }
    

