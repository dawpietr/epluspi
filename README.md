Serwisy
eureka
adres serwisu eureka:
http://localhost:8761/
gete
adres gdzie generujemy E:
http://localhost:8086/gete
adres gdzie generujemy losowa zmienna double:
http://localhost:8086/getrandom
getpi
adres gdzie generujemy Pi:
http://localhost:8087/getpi
adres gdzie generujemy losowa zmienna double:
http://localhost:8087/getrandom
getepi
adres wykonania clue naszego microservisu:
http://localhost:8085/getepluspi
zuul
dostp do powyszych adresow przez zuula:
localhost:8080/api/gete/gete
localhost:8080/api/gete/getrandom
localhost:8080/api/getpi/getrandom
localhost:8080/api/getpi/getpi
localhost:8080/api/getepi/getepluspi

hystrix
dziala lub nie w zaleznosci od wylosowanej liczby z random
localhost:8081 
dashboard tam wpisac adres localhost:8081/hystrix.stream
localhost:8081/hystrix

elk
kazdy z terminali nalezy uruchomic osobno z folderu glownego projektu
elasticsearch uruchomienie
terminal .elasticsearch/bin/elasticsearch
kibana
terminal ./kibana/bin/kibana
logstash
terminal ./logstash/bin/logstash -f ./logstash/conf/logstash.conf

10 serwisow

