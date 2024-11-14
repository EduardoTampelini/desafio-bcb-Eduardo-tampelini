# REST API Big chat Brasil

Bem-vindo à API REST Big chat Brasil! Esta API foi projetada para execultar as funções basicas solicitadas no desafio  proposto (https://github.com/fhgomes/fhgomes/blob/master/entrevistas/teste-backend.md).


# REST API

Segue as rotas da API e exemplos de retornos

## listar os clientes

### Request

`GET /client/`

    http://localhost:8080/client/

### Response
  
    Status: 200 
    body:{
        "id": "49063d6b-971b-48dd-a163-4e012f73e820",
        "name": "ab",
        "email": "av@gmail.com",
        "cpf": "000000000",
        "phone": "0000000",
        "cnpj": "00000000000",
        "nameCompany": "teste ltda"
    }

## Cadastrar clientes

### Request

`POST /client/`

     http://localhost:8080/client/
     body:{
        "email": "",
        "name": "",
        "cpf":"",
        "phone":"",
        "cnpj": "",
        "nameCompany":""
     }

### Response

    
    Status: 201 
    body:{
       "id": "7258fa04-0461-462c-8838-21791a683be3",
      "name": "ab",
      "email": "ezafalon0@gmail.com",
      "cpf": "03119830062",
      "phone": "44997345070",
      "cnpj": "16501555000238",
      "nameCompany": "teste ltda"
    }

## Consultar client  

### Request

`GET /client/:id`

        http://localhost:8080/client/:id

### Response

     Status: 200 
     body:{
        "id": "49063d6b-971b-48dd-a163-4e012f73e820",
        "name": "ab",
        "email": "av@gmail.com",
        "cpf": "000000000",
        "phone": "0000000",
        "cnpj": "00000000000",
        "nameCompany": "teste ltda"
    }  
    
    Não pode ter o mesmo cliente com o mesmo email então caso tente cadastrar ele vai retornar 400

## Consultar financeiro cliente

### Request

`GET /fin/:id`

            http://localhost:8080/fin/:id

### Response

    Status: 200
    body: {
      "id": "42c05a58-4d9e-4450-8676-651d3f951b10",
      "limitCredit": 10,
      "credit": 0,
      "valuePlan": 10.00,
      "client": {
          "id": "c6d993ee-07c1-4963-916d-cf40f8a361ca",
          "name": "ab",
          "email": "ab@gmail.com",
          "cpf": "000000",
          "phone": "00000",
          "cnpj": "000000",
          "nameCompany": "teste ltda"
      }
    }

## Cadastrar financeiro client

### Request

`POST /fin/`

    http://localhost:8080/fin/
    body:{
      limitCredit": 10,
      "credit": 0,
      "valuePlan": 10.00,
      "client": {
          "id": "c6d993ee-07c1-4963-916d-cf40f8a361ca",
          "name": "ab",
          "email": "ab@gmail.com",
          "cpf": "000000",
          "phone": "00000",
          "cnpj": "000000",
          "nameCompany": "teste ltda"
      }
    }

### Response

    Status: 201
    body: {
      "id": "42c05a58-4d9e-4450-8676-651d3f951b10",
      "limitCredit": 10,
      "credit": 0,
      "valuePlan": 10.00,
      "client": {
          "id": "c6d993ee-07c1-4963-916d-cf40f8a361ca",
          "name": "ab",
          "email": "ab@gmail.com",
          "cpf": "000000",
          "phone": "00000",
          "cnpj": "000000",
          "nameCompany": "teste ltda"
      }
    }

## adicionar crédito cliente

### Request

`POST /fin/credit/:id`

        http://localhost:8080/fin/credit/:id
        body: 5 (valor que deseja tem que ser um integer)

### Response

    
    Status: 200 
    body:(valor de crédito do cliente)

    Caso tenha chegado no limite de crédito ou valor 0 ele vai retonar status 400 e informar que o valor é invalido

## Saldo do cliente

### Request

`GET /fin/balance/:id`

        http://localhost:8080/fin/balance/:id
        
### Response

    Status: 200 
    body:(valor do saldo)

## Alterar limite do cliente

### Request

`PUT /fin/limitClient/:id`

    http://localhost:8080/fin/limitClient/:id
    body: 5 (valor que deseja tem que ser um integer)

### Response

    Status: 200 
    body:(valor do limite)

## Alterar Plano do cliente

### Request

`PUT /fin/plan/:id`

     http://localhost:8080/fin/plan/1
     body:{
      "limitcred": 10,
      "cred": 0,
      "value":10.00,
     

### Response

    
    Status: 200 OK
    body: Alteração feita
    

## Enviar SMS

### Request

`POST /sms/`

    http://localhost:8080/sms/
    body:{
      "phone": "449999999",
      "isWhatsApp": false/true,
      "text":"testeeeeee",
    }

### Response

   
    Status: 201
    body:{
      "id":"49063d6b-971b-48dd-a163-651d3f951b10"
      "phone": "449999999",
      "isWhatsApp": false/true,
      "text":"testeeeeee",
    }
    

