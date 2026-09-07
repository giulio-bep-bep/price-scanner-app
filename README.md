# 📱 Price Scanner App

Un'applicazione Android per scansionare barcode di prodotti da supermercato e confrontare i prezzi prima dell'acquisto.

## 🎯 Funzionalità

- **Scansione Barcode**: Scansiona i codici EAN, QR code e altri formati usando ML Kit
- **Inserimento Prezzo**: Registra il prezzo manualmente o catturalo con la fotocamera
- **Database Locale**: Memorizza i prodotti e i prezzi nel dispositivo
- **Confronto Prezzi**: Visualizza il prezzo medio, minimo e massimo per ogni prodotto
- **Cronologia**: Accedi allo storico di tutti i prezzi registrati
- **Negozio**: Associa il prezzo al negozio dove è stato acquistato

## 📋 Requisiti

- Android 7.0+ (API 24)
- Fotocamera
- Permessi: CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE

## 🚀 Come Iniziare

1. **Clonare il repository**
```bash
git clone https://github.com/giulio-bep-bep/price-scanner-app.git
cd price-scanner-app
```

2. **Aprire in Android Studio**
- File → Open → Selezionare la cartella del progetto

3. **Compilare il progetto**
```bash
./gradlew build
```

4. **Creare l'APK**
```bash
./gradlew assembleRelease
```
L'APK sarà disponibile in `app/build/outputs/apk/release/app-release.apk`

5. **Eseguire l'app**
- Connettere un dispositivo Android oppure usare l'emulatore
- Click su "Run" in Android Studio

## 📖 Guida all'Utilizzo

### Scansionare un Prodotto
1. Tocca il pulsante "Avvia Scanner" dalla home
2. Inquadra il barcode del prodotto
3. L'app riconoscerà automaticamente il codice
4. Tocca "Conferma" per procedere

### Inserire il Prezzo
1. Inserisci il prezzo (campo obbligatorio)
2. (Opzionale) Inserisci il nome del prodotto
3. (Opzionale) Inserisci il nome del negozio
4. Tocca "Salva Prezzo"

### Visualizzare Confronti
- Dopo aver salvato un prezzo, vedrai automaticamente:
  - Prezzo medio di quel prodotto
  - Prezzo minimo (con negozio)
  - Prezzo massimo (con negozio)
  - Cronologia di tutti i prezzi registrati

## 🏗️ Architettura

### Stack Tecnologico
- **Linguaggio**: Kotlin
- **Database**: Room
- **Camera**: CameraX
- **ML Kit**: Google ML Kit Barcode Scanner
- **Async**: Coroutines

## 🤝 Contribuire

Le pull request sono benvenute!

## 📝 Licenza

Questo progetto è sotto licenza Apache 2.0.
