// Провери дали има логнат потребител
const currentUser = localStorage.getItem('currentUser');
if (!currentUser) {
    window.location.href = 'login';
}

const messagesDiv = document.getElementById('messages');
const messageForm = document.getElementById('message-form');
const messageInput = document.getElementById('message-input');
const username = currentUser.username || 'Анонимен';

// Свържи се към socket.io сървъра
const socket = io();

// При изпращане на съобщение
messageForm.addEventListener('submit', function (e) {
    e.preventDefault();
    const text = messageInput.value.trim();
    if (!text) return;
    // socket.emit('chat message', { username, text });

    fetch('/message', {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Authorization': currentUser},
        body: JSON.stringify({
            "message": text,
        })
    }).then(r => r.json())
        .then(data => {
            console.log(data);
        })

    messageInput.value = '';
});

// При получаване на съобщение
// socket.on('chat message', function (msg) {
//     addMessage(msg);
// });

let lastMessageDate = null;
setInterval(() => {
    fetch('/message/get', {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Authorization': currentUser},
        body: JSON.stringify({
            "getOldMessages": lastMessageDate == null ? true : false,
            "currentDate": lastMessageDate
        })
    }).then(r => r.json())
        .then(data => {
            data.forEach(msg => {
                addMessage(msg);
            });
            lastMessageDate = data[data.length - 1].sentDate
        })
}, 1000);

// Добавяне на съобщение в DOM
function addMessage(msg) {
    const msgDiv = document.createElement('div');
    msgDiv.classList.add('message');
    if (msg.itsMe) msgDiv.classList.add('self');

    const userSpan = document.createElement('span');
    userSpan.className = 'username';
    if (msg.itsMe) userSpan.style.textAlign = 'right';
    userSpan.textContent = msg.senderUsername;

    const textSpan = document.createElement('span');
    textSpan.className = 'text';
    textSpan.textContent = msg.message;

    const dateSpan = document.createElement('span');
    dateSpan.className = 'date';
    if (msg.itsMe) dateSpan.style.textAlign = 'right';
    dateSpan.textContent = msg.sentDate;

    msgDiv.appendChild(userSpan);
    msgDiv.appendChild(textSpan);
    msgDiv.appendChild(dateSpan);
    messagesDiv.appendChild(msgDiv);

    // Скролирай надолу
    messagesDiv.scrollTop = messagesDiv.scrollHeight;
}

// (По желание) Може да добавиш съобщение "Добре дошъл" при влизане