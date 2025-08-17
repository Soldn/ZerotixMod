
# Zerotix Mod (Forge 1.16.5)

Функционал:
- Меняет заголовок окна на **Zerotix**.
- Экран загрузки (логотип Mojang) заменён на чёрный с надписью **Soldin.jar**.
- Кастомное главное меню: нет одиночной игры, есть кнопки **Зайти на Zerotix**, **Настройки**, **Выйти из игры**.
- Кнопка **Зайти на Zerotix** мгновенно подключает к серверу `mc.zerotix.fun`.
- Экран подключения минимальный: только кнопка **Отмена**.
- Кастомный экран кика/бана/вайтлиста с красивым выводом причины.

## Как собрать (локально)
1. Установи JDK 8 (или 11) и Gradle 6.8+.
2. В корне проекта выполни:
   ```bash
   gradle build
   ```
3. Готовый мод появится в `build/libs/zerotix-1.0.0.jar`. Клади его в папку `mods` Forge 1.16.5.

## Как собрать через GitHub Actions
1. Создай новый репозиторий и загрузи файлы из этого проекта.
2. Добавь workflow `.github/workflows/build.yml` со следующим содержимым:
   ```yaml
   name: build
   on: [push]
   jobs:
     build:
       runs-on: ubuntu-latest
       steps:
         - uses: actions/checkout@v4
         - uses: actions/setup-java@v4
           with:
             distribution: 'temurin'
             java-version: '8'
         - uses: gradle/actions/setup-gradle@v3
         - run: gradle build --stacktrace
         - uses: actions/upload-artifact@v4
           with:
             name: zerotix-jar
             path: build/libs/*.jar
   ```

После сборки скачай артефакт `zerotix-jar` в разделе Actions.

## Кастомизация
- IP сервера смотри в `ZerotixMainMenuScreen.java` (строка с `mc.zerotix.fun`).
- Картинку загрузки замени в `assets/minecraft/textures/gui/title/mojangstudios.png` (512x512 PNG).
