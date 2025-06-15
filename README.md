# BigChat Brasil API

Bem-vindo à **API REST BigChat Brasil**, uma solução desenvolvida para gerenciamento de clientes, conversas, mensagens e filas de envio.

Base URL:

```
http://localhost:8080/api
```

---

## 📁 Clientes

### ➕ Criar Cliente

**POST** `/clients`

**Headers:**

```
Content-Type: application/json
```

**Body (JSON):**

```json
{
  "name": "Empresa ABC Ltda 2",
  "documentId": "12345678000199",
  "documentType": "CNPJ",
  "planType": "prepaid",
  "initialBalance": 1000.00,
  "limit": 0
}
```

**Response:**

```
Status: 201 CREATED
```

---

### 🔍 Obter Cliente por ID

**GET** `/clients/:id`

**Response:**

```
Status: 200 OK
Body:
{
  "id": "uuid",
  "name": "Empresa ABC Ltda 2",
  "documentId": "12345678000199",
  "documentType": "CNPJ",
  "planType": "prepaid",
  "initialBalance": 1000.00,
  "limit": 0
}
```

---

### 💰 Atualizar Saldo do Cliente

**POST** `/clients/:id/balance`

**Headers:**

```
Content-Type: application/json
```

**Body (JSON):**

```json
{
  "amount": 500.00
}
```

**Response:**

```
Status: 200 OK
```

---

## 💬 Conversas

### ➕ Criar Conversa

**POST** `/conversations`

**Headers:**

```
Content-Type: application/json
```

**Body (JSON):**

```json
{
  "clientId": "uuid-do-cliente",
  "recipientId": "uuid-do-destinatario"
}
```

**Response:**

```
Status: 201 CREATED
```

---

### 📄 Listar Conversas

**GET** `/conversations`

**Response:**

```
Status: 200 OK
```

---

### 🔍 Obter Conversa por ID

**GET** `/conversations/:id`

**Response:**

```
Status: 200 OK
```

---

### 📥 Obter Mensagens da Conversa

**GET** `/conversations/:id/messages`

**Response:**

```
Status: 200 OK
```

---

## ✉️ Mensagens

### ➕ Enviar Mensagem

**POST** `/messages`

**Headers:**

```
Content-Type: application/json
```

**Body (JSON):**

```json
{
  "chatId": "uuid-da-conversa",
  "recipientId": "uuid-do-destinatario",
  "content": "Olá, como vai?",
  "priority": "normal"
}
```

**Response:**

```
Status: 201 CREATED
```

---

### 📄 Listar Mensagens

**GET** `/messages`

**Response:**

```
Status: 200 OK
```

---

### 🔍 Obter Mensagem por ID

**GET** `/messages/:id`

**Response:**

```
Status: 200 OK
```

---

## ⏳ Fila

### 📊 Verificar Status da Fila

**GET** `/queue/status`

**Response:**

```
Status: 200 OK
Body:
{
  "status": "OK",
  "messagesInQueue": 5
}
```

---

## 🔧 Variáveis de Ambiente

A coleção usa a seguinte variável:

```
{{base_url}} = http://localhost:8080/api
```

---

## 🧪 Considerações

- Todos os endpoints `POST` devem ser enviados com `Content-Type: application/json`.
- Os IDs são do tipo UUID.
- Clientes têm saldo inicial, limite de crédito e plano configuráveis.
- Mensagens têm prioridade e são associadas a conversas.

---

## 🔮 Testes via Postman

Importe o arquivo `BigChat Brasil API.postman_collection.json` no Postman para testar todos os endpoints com facilidade. Configure a variável `base_url` no ambiente Postman para apontar para sua API local ou remota.

---

Para dúvidas ou melhorias, sinta-se à vontade para abrir um issue ou PR.

> Desenvolvido como parte do desafio proposto para o sistema de mensagens da BigChat Brasil.

