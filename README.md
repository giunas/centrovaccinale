# centrovaccinale
Web Application per un centro vaccinale


Il database era hostato su un servizio di hosting, quindi è necessario effettuare l'import del file sql. Sarà necessario modificare il campo nel file Java SQLhelp (DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sql11420508");).

Il database ha un account per il medico le cui credenziali sono:

EMAIL: email@email.com
PASSWORD: prova

e un account per il funzionario dell'ASP le cui credenziali sono:

EMAIL: femail@email.com
PASSWORD: fprova


Ogni volta che si crea una prenotazione, per accedere all'area per la gestione delle prenotazioni si inserisce codice fiscale e codice di prenotazione ricevuto.
Nel sistema sono presenti due prenotazioni:

CODICE PRENOTAZIONE: 66ea0c8122c115f4bb7240365fcd748a3a65375a
CF: NSAGPP97A21G273P
DATA: 21/06/2021

CODICE PRENOTAZIONE: 71b2357823e4ad8d75d9df34ce1e9a97a4be9841
CF: VRDNDR73A12G273T
DATA: 30/06/2021


N.B. Per far funzionare la validazione lato server per i report xml del funzionario dell'ASP è necessario che il file report.xsd sia copiato nel CLASSPATH.
