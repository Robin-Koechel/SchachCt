​​​​​Schritt 1: Starten
	-Programm starten
	-Auswählen, ob man über seinen localhost spielen will oder ob man sich auf einen anderen Host verbinden will

Schritt 2: Menü
	-Datei
		-"Neues Spiel" startet unabhängig vom Modus ein neues Spiel
		-"IP anzeigen" zeigt die Ip Adresse des PCs an
		-"setze IP" verbindet die Datenbank erneut
		-"Refresh DB" aktualisiert den derzeitigen Spielstand(nur Online)
		-"DB zurücksetzen" setzt die Datenbank auf ihre Anfangskonfiguration zurück
		-"Beenden" schließt das Fenster

	-Spielmodi
		-"Player vs. Player" lässt einen lokal, in einem Fenster, abwechselnd schwarz gegen weiß spielen(Schwarz fängt an)
		-"Player vs. AI" ! Funktioniert noch nicht!
		-"Online" lässt einen gegen einen anderen Spieler, an einem anderen PC spielen. Bei Fehlern gerne die DB zurücksetzen. Bevor man online spielen kann, muss in die settings.json die richtige IP eingetragen werden und XAMPP muss richtig konfiguriert sein

	-Links
		-"GitHub" öffnet einen Browser und leitet einen zu dem GitHub Repo von diesem Projekt

Schritt 3: Oberfläche
	Die Oberfläche ist sehr simpel und intuitiv gestaltet.
	Möchte man eine Figur bewegen, so muss man die Figur anklicken und im nächsten
	Schritt das Zielfeld bestimmen(auch durch Anklicken). Wenn alles stimmt und keine
	Fehler auftreten, wird die Figur an die gewünschte Position bewegt. Tritt ein Fehler auf,
	kann man es noch einmal probieren.
	Wenn man die Figur an eine nicht gewünschte Position bewegt hat, kann man diese in dem Zug
	nicht mehr verändern(Gesetzt ist Gesetzt).
	Wenn man online spielt aktualisiert sich das Spielfeld nicht automatisch!
	Man muss also vor jedem zu über ->Datei->Refresh DB das Spielfeld aktualisieren.

	In der setting.json Datei kann man zusätzlich auch noch Farbe des Spielfeldes nach seinen eigenen
	Präferenzen verändern.
