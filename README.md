# Java PMP2_07

```
PR07 die Javanisten
Author: Roman Schmidt, Stanislaw Brug
```

## Ticket to Hell
Die Auswahl des Tickets wurde damit realisiert, dass wir im Konstruktor der PayStation einen Enum 
vom Typ TicketType erwarten. Damit wird auch der Preis gewählt.

Die PayStation grüßt einen, spuckt den recht günstigen preis aus, nimmt aber keine Scheine.
Um den Münzeinwurf zu nutzen, steht die Methode "addCoin" zur Verfügung, die Enums vom Typ Coin 
erwartet.

Jeder Münzeinwurf wird in der Konsole dokumentiert und es wird der akutelle Status der PayStation
ausgespuckt, der sich mit Hilfe der PaymentStateMachine selbst reguliert.

Die PaymentStateMachine ist ein intelligenter Enum, der eigene Funktionen mit sich bringt,
wie das "betreten" des Statuses, wie auch auf den nächsten prüfen. Abhängig von Preis und der
bereits eingezahlten Coins bekommt man das Ticket, das Rückgeld, oder die eingezahlten Coins, wenn
man vorher auf Reset "drückt".

Der Einfachheitshalber wurden die Ausgaben direkt in die Console geleitet. Diese werden nicht
getestet und auch nicht für die Tests unterdrückt.

