# lukina-telegram-bot

Мини-банк.Представляет телеграм-бот, который предоставляет список возможных команд для операций со счетами.
Возвращает результат в текстовом виде.

## Архитектура
### Frontend (java/kotlin)
Клиентское приложение. Инициирует запросы пользователей.
### Middle layer (java/kotlin)
* Принимает запросы от tg-бота
* Выполняет валидацию и бизнес-логику
* Маршрутизирует запросы в backend
### Backend (java/kotlin)
Автоматизированная банковская система
* Обрабатывает транзакции
* Хранит клиентские данные

```plantuml
@startuml
actor Client
participant Frontend
participant Middle
participant Backend

Client -> Frontend: команда start
Frontend -> Client: список команд
Client -> Frontend: валидная команда
Frontend -> Middle: HTTP-запрос
alt Запрос валиден
    Middle -> Backend: HTTP-запрос
else Запрос невалиден
    Middle -> Frontend: Ошибка
end
Backend -> Middle: Данные
Middle -> Frontend: Обработанные данные
Frontend -> Client: Текстовый ответ
@enduml
```