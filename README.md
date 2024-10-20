# NoteApp
>Simple note-taking app for android user.

## Description

The application is very practical, following functionality:
1. Create new notes.
2. View a list of all created notes.
3. Edit existing note.
4. Note deletion.
5. View created note

## Getting Started

### Dependencies

```
implementation(libs.androidx.core.ktx)
```
Это библиотека расширений для AndroidX Core, которая упрощает работу с основными классами Android, используя синтаксис Kotlin. Позволяет писать более лаконичный и читабельный код, благодаря использованию расширений и функций высшего порядка.

```
implementation(libs.androidx.appcompat)
```
Библиотека, обеспечивающая совместимость с более старыми версиями Android и предоставляющая современные компоненты пользовательского интерфейса. Используется для обеспечения единообразного пользовательского интерфейса на разных версиях Android, а также для поддержки новых элементов интерфейса на старых устройствах.

```
implementation(libs.material)
```
Библиотека для работы с компонентами Material Design. Позволяет использовать современные элементы интерфейса, соответствующие рекомендациям Material Design, улучшая пользовательский опыт.

```
implementation(libs.androidx.activity)
```
Библиотека, обеспечивающая базовые функции для работы с Activity.Позволяет использовать новые функции жизненного цикла и управления состоянием для Activity.

```
implementation(libs.androidx.constraintlayout)
```
Библиотека для создания сложных макетов с помощью ограничений (constraints). Упрощает процесс создания адаптивных и отзывчивых интерфейсов, позволяя минимизировать количество вложенных View.

```
implementation(libs.androidx.room.ktx)
implementation(libs.androidx.room.runtime)
kapt(libs.androidx.room.compiler)
```
Room — это библиотека для работы с базами данных SQLite, которая использует аннотации и предоставляет абстракцию над SQLite. Обеспечивает безопасный и удобный способ работы с базами данных в Android, поддерживает реактивные потоки данных через LiveData и Flow. Использование KTX упрощает синтаксис работы с Room.

```
implementation(libs.androidx.lifecycle.viewmodel.ktx)
implementation(libs.androidx.lifecycle.livedata.ktx)
```
Библиотеки для работы с жизненным циклом компонентов Android и для управления состоянием.
Аргументация: ViewModel позволяет сохранять данные при изменении конфигурации (например, поворот экрана), а LiveData обеспечивает автоматическое обновление пользовательского интерфейса при изменении данных.

```
androidTestImplementation(libs.androidx.espresso.core)
```
Библиотека для автоматизированного тестирования пользовательского интерфейса. Обеспечивает простое и мощное API для взаимодействия с пользовательским интерфейсом, позволяя легко писать тесты для проверки поведения приложения.

### :computer: Installing

1. Требования
Java Development Kit (JDK): Убедитесь, что у вас установлена версия JDK 11 или выше.
Android Studio: Скачайте и установите последнюю версию Android Studio.
SDK: Убедитесь, что у вас установлены необходимые Android SDK (API Level) в Android Studio.

2. Клонирование репозитория
Откройте терминал и выполните команду:
```
git clone https://github.com/ваш-репозиторий.git
cd ваш-репозиторий
```

3. Импорт проекта в Android Studio
Запустите Android Studio.
Выберите File -> Open... и выберите папку с клонированным репозиторием.
Подождите, пока Gradle синхронизирует проект.

4. Настройка зависимостей
Убедитесь, что все зависимости указаны в файле build.gradle (как в корне проекта, так и в модулях). Для этого откройте файл и проверьте наличие следующих зависимостей:
```
dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity-ktx:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.room:room-ktx:2.5.1")
    implementation("androidx.room:room-runtime:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
```
5. Сборка проекта
В меню Android Studio выберите Build -> Make Project или нажмите Ctrl + F9 (Windows) или Command + F9 (Mac).
Убедитесь, что сборка прошла успешно и нет ошибок.

6. Запуск приложения
Подключите устройство или запустите эмулятор.
В меню Android Studio выберите Run -> Run 'app' или нажмите Shift + F10.
Выберите устройство для установки и запуска приложения.

7. Проблемы и решение
Если возникают проблемы со сборкой, проверьте логи Gradle в нижней части окна Android Studio.
Убедитесь, что все зависимости указаны правильно и что у вас установлены соответствующие версии плагинов.

### :iphone: Executing program

* How to run the program
* Step-by-step bullets
```
code blocks for commands
```

## :page_facing_up: License

This project is licensed under the MIT License - see the LICENSE.md file for details
