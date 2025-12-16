# 📱 Toyor Ljanna – Alphabet Learning App

Toyor Ljanna is an educational Android application designed to help children learn **Arabic and French alphabets** in a fun, interactive, and child-friendly way.  
The app combines **listening, tracing, and writing** to make learning letters easy and enjoyable.

---

## ✨ Features

- 🏠 **Home Screen**
  - Choose between Arabic and French alphabets

- 🔤 **Alphabet List**
  - Display letters in a grid layout
  - Tap on a letter to hear its pronunciation

- ✏️ **Letter Tracing**
  - Children can trace letters using their finger
  - Buttons: Clear, Repeat Sound, Next Letter

- 📝 **Free Writing Area**
  - Large and spacious writing area
  - Clearly visible written letters for children

- 🔊 **Text-to-Speech (TTS)**
  - Arabic and French pronunciation
  - Offline support

- 🎨 **Child-Friendly Design**
  - Bright pastel colors
  - Rounded buttons
  - Large text and clear icons

---

## 🗂️ Project Structure

com.example.toyorljanna
│
├── data
│ ├── Letter.kt
│ ├── AlphabetResponse.kt
│ └── AlphabetRepository.kt
│
├── ui
│ ├── MainActivity.kt
│ ├── AlphabetActivity.kt
│ ├── TracingActivity.kt
│ └── WritingActivity.kt
│
├── utils
│ └── TTSHelper.kt
│
└── res
├── layout
├── drawable
├── values
└── assets (JSON alphabet data)





## 📦 Data Handling

- Alphabet data is stored locally in **JSON files**
- Data is parsed using **Gson**
- Simple data models for easy extension

---

## 🚀 Technologies Used

- **Kotlin**
- **Android SDK**
- **RecyclerView**
- **Text-to-Speech (TTS)**
- **Gson**
- **XML Layouts**

---

## ▶️ How to Run the App

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/toyor-ljanna.git
Open the project in Android Studio

Sync Gradle files

Run the app on an emulator or a real Android device

📱 APK Generation
Build → Build Bundle(s) / APK(s) → Build APK(s)



