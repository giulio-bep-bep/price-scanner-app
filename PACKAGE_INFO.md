# 📱 Price Scanner App - Complete Package

## 🎯 Project Overview

**Price Scanner** è un'applicazione Android completa per scansionare barcode di prodotti e confrontare i prezzi prima dell'acquisto.

### Versione: 1.0
### Linguaggio: Kotlin
### Target: Android 7.0+ (API 24)

---

## 📦 Contenuto del Progetto

### Codice Sorgente
```
├── app/src/main/
│   ├── kotlin/com/pricescanner/
│   │   ├── MainActivity.kt                 # Schermata principale
│   │   ├── scanner/
│   │   │   ├── ScannerActivity.kt         # Scansione barcode
│   │   │   └── BarcodeAnalyzer.kt         # ML Kit integration
│   │   ├── price/
│   │   │   ├── PriceInputActivity.kt      # Inserimento prezzo
│   │   │   └── PriceHistoryAdapter.kt     # RecyclerView
│   │   ├── comparison/
│   │   │   └── ComparisonActivity.kt      # Confronto prezzi
│   │   └── database/
│   │       ├── AppDatabase.kt             # Room setup
│   │       ├── Product.kt                 # Entity
│   │       ├── PriceRecord.kt             # Entity
│   │       └── PriceDao.kt                # DAO
│   └── res/
│       ├── layout/                        # 5 layout XML
│       └── values/                        # Stringhe
└── Tests
    ├── DatabaseTest.kt                    # Integrazione
    ├── ProductTest.kt                     # Unit test
    └── PriceRecordTest.kt                 # Unit test
```

---

## ✨ Funzionalità Principali

### 1️⃣ Scansione Barcode
- Supporta EAN-13, EAN-8, QR Code, Code 128 e altri
- Scansione real-time con ML Kit
- Anteprima fotocamera integrata

### 2️⃣ Inserimento Prezzo
- Inserimento manuale prezzo (obbligatorio)
- Nome prodotto (opzionale)
- Nome negozio (opzionale)
- Validazione automatica

### 3️⃣ Database Locale
- SQLite via Room
- Memorizzazione offline
- 2 tabelle: Products, Prices

### 4️⃣ Confronto Prezzi
- Prezzo medio automatico
- Prezzo minimo con negozio
- Prezzo massimo con negozio
- Cronologia completa

### 5️⃣ Cronologia
- Lista di tutti i prezzi registrati
- Ordinamento per data
- Info negozio e timestamp

---

## 🏗️ Architettura

### Stack Tecnologico
- **Linguaggio**: Kotlin 1.9.0
- **Target SDK**: 34 (Android 14)
- **Min SDK**: 24 (Android 7.0)
- **Database**: Room 2.6.0
- **Camera**: CameraX 1.3.0
- **ML Kit**: Barcode Scanning 17.2.0
- **Async**: Coroutines 1.7.1
- **UI**: Material Design

---

## 📊 Database Schema

### Tabella: products
| Campo | Tipo | Note |
|-------|------|------|
| barcode | String (PK) | Codice univoco |
| productName | String? | Nome prodotto |
| category | String? | Categoria |
| createdAt | Long | Timestamp |

### Tabella: prices
| Campo | Tipo | Note |
|-------|------|------|
| id | Int (PK) | Auto-increment |
| barcode | String (FK) | Riferimento prodotto |
| price | Double | Prezzo in € |
| store | String? | Nome negozio |
| date | Long | Timestamp |

---

## 🔒 Permessi Richiesti

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.INTERNET" />
```

---

## 📞 Support

**Repository GitHub:**
https://github.com/giulio-bep-bep/price-scanner-app

**Issues:**
https://github.com/giulio-bep-bep/price-scanner-app/issues

---

## 📄 Licenza

Apache License 2.0

---

**Versione**: 1.0  
**Data**: Settembre 2026  
**Sviluppatore**: @giulio-bep-bep  
**Status**: ✅ Production Ready
