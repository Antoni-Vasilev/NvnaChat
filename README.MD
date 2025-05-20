# Tetris Game

This project is a simple implementation of the classic Tetris game using HTML, CSS, and JavaScript.

## Project Structure

- `index.html`: Contains the HTML structure for the Tetris game, including the canvas element and links to external CSS and JavaScript files.
- `style.css`: Contains styles for the Tetris game, including layout, colors, and visual elements.
- `main.js`: Implements the game logic, handling user input, game mechanics, and rendering on the canvas.

## How to Run the Game

1. Clone the repository or download the project files.
2. Open `index.html` in a web browser.
3. Use the arrow keys to control the Tetris pieces.

## Game Controls

- **Left Arrow**: Move the piece left
- **Right Arrow**: Move the piece right
- **Down Arrow**: Move the piece down faster
- **Up Arrow**: Rotate the piece

Enjoy playing Tetris!




# NVNA Chat Room

## Описание

Това е уеб базирано чат приложение с регистрация и вход, реализирано с Node.js, Express, Socket.IO и съхранение на потребителите в JSON файл на сървъра. Всеки потребител трябва да се регистрира или логне, за да участва в глобалния чат. Всички съобщения се предават в реално време между всички свързани потребители в локалната мрежа.

---

## Структура на проекта

```
NVNACHAT2/
│
├── index.html         # Страница за вход и регистрация
├── chat.html          # Чат интерфейс
├── style.css          # Модерни стилове за login и чат
├── login.js           # Логика за вход и регистрация (клиент)
├── chat.js            # Логика за чат (клиент)
└── server             # Връзката с базата данни
```

---

## Как работи приложението

- **Регистрация и вход**: Потребителите се регистрират или логват чрез формата на `index.html`. Данните се изпращат към Node.js сървъра, който ги записва/проверява в `users.json`.
- **Чат**: След успешен вход, потребителят влиза в `chat.html`, където може да изпраща и получава съобщения в реално време чрез Socket.IO.
- **Съхранение на потребители**: Всички регистрации се пазят в един централен JSON файл на сървъра (`server/users.json`), така че всички устройства и браузъри работят с един и същ списък с потребители.
- **Сигурност**: Ако някой се опита да отвори директно `chat.html` без да е логнат, ще бъде пренасочен към login страницата.

---

## Файлове и тяхната роля

- **index.html**  
  Страница за вход и регистрация. Включва стилове и login.js.

- **chat.html**  
  Интерфейс за чат. Включва стилове, chat.js и Socket.IO от CDN.

- **style.css**  
  Модерен тъмен дизайн, заоблени елементи, responsive за мобилни устройства.

- **login.js**
    - Валидация на формата.
    - Изпраща заявка към `/api/register` или `/api/login`.
    - При успех записва текущия потребител в localStorage и пренасочва към чата.

- **chat.js**
    - Проверява дали има логнат потребител.
    - Свързва се със Socket.IO сървъра.
    - Изпраща и получава съобщения в реално време.
    - Показва съобщенията с име и текст.

- **server users**
    - Списък с всички регистрирани потребители във формат:
      ```json
      [
        {
          "email": "user@example.com",
          "username": "UserName",
          "password": "password"
        }
      ]
      ```

---

## Важно

- **Паролите се пазят в plain text** (само за учебни цели, не е сигурно за реални приложения).
- **Всеки потребител трябва да се регистрира или логне, за да влезе в чата.**
- **Всички регистрации са валидни за всички устройства, защото се пазят на сървъра.**

---

## Примерни потребители (users.json)

```json
[
  {
    "email": "service3832@gmail.com",
    "username": "SerkanKemal",
    "password": "12345678"
  },
  {
    "email": "Georgi@gmail.com",
    "username": "GeorgiGeorgiev",
    "password": "12345678"
  }
]
```

---

## Автор

- Проект за учебни цели – NVNA Chat Room