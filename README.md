# NoteApp
>Simple note-taking app for android user.

## Description

The application is very practical, following functionality:
1. Create new notes.
2. View a list of all created notes.
3. Edit existing note.
4. Note Deletion.
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

### Installing

* How/where to download your program
* Any modifications needed to be made to files/folders

### Executing program

* How to run the program
* Step-by-step bullets
```
code blocks for commands
```

## License

This project is licensed under the MIT License - see the LICENSE.md file for details
