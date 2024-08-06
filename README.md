# BIN Insight

BIN Insight - приложение для получения информации о банковской карте по BIN.

## Стэк
- Kotlin
- Hilt
- Coroutines
- Jetpack Navigation
- Jetpack Compose
- Retrofit
- Room
- Clean architecture с паттерном MVVM

## Функционал
- Получение информации о банковской карте по BIN (страна, координаты, тип карты, данные банка (URL\*, телефон\*, город)) (\*при наличии данных).
- Нажатие на URL, телефон и координаты отправляет пользователя в приложения, способные обработать эти данные.
- Хранение истории запросов пользователя на устройстве с возможностью удаления.