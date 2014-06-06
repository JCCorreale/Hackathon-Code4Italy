Hackathon-Code4Italy
====================

Estrazione dei contenuti dagli atti della Camera dei Deputati

[Project Home Page](http://code4italy.altervista.org/) (italian)

Progetto *"Text Mining: Estrazione dei contenuti dagli atti della Camera dei Deputati"*.

di
_Jean Claude Correale_
_Thierry Spetebroot_
_Alessandro Colace_

## Informazioni sul Progetto:

Il progetto consta di due parti: un frontend web ed una applicazione Java. Il frontend web funge da visualizzatore dei dati prodotti dal motore di Text Mining implementato in Java, e costituisce un esempio di applicazione che può essere realizzata applicando questo tipo di tecniche. Al momento non vi è una comunicazione diretta fra l’applicazione Java e il sito web. I dati generati sono stati caricati manualmente utilizzando il protocollo FTP.

Licenza: [CC-BY](http://creativecommons.org/licenses/by/2.0/)
URL Web: [link](http://code4italy.altervista.org/)

## Istruzioni:

La documentazione del progetto è disponibile nel frontend web al seguente [link]( http://www.code4italy.altervista.org/info/index.html).

L'eseguibile del software di Data Mining (*TextMining.jar*) è disponibile nella cartella it.camera.hackathon.textmining. L'esecuzione richiede l'accesso ad Internet per interrogare l'endpoint SPARQL e genera file JSON nella cartella it.camera.hackathon.textmining/output/json. I dati presenti nei file generati possono essere visualizzati accedendo al frontend web (cartella web/deploy).

## Abstract:

Il software sviluppato analizza una collezione di documenti (in particolare Atti della Camera dei Deputati) al fine di individuare gli argomenti trattati da ciascuno di questi. Utilizzando questa informazione in combinazione agli OpenData della camera, è potenzialmente possibile effettuare interessanti ricerche, mettendo in risalto le tematiche più affrontate in una legislatura, in un certo periodo di tempo o in relazione ad un particolare deputato o senatore.

Il software processa i dati applicando tecniche di information retrieval come stemming, individuazione e disambiguazione dei sinonimi e utilizza indici standard come Term Frequency (TF) e Inverse Document Frequency (IDF). E' utilizzato un algoritmo di clustering non supervisionato per evidenziare l'andamento nel tempo delle tematiche trattate.
