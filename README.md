# Web1-T3
## Sistema para compra e venda de pacotes turísticos

### Roteiro para execução
O SGBD utilizado foi o MySQL com o login `usuario: root` e a `senha: root`
O banco de dados é criado e populado apenas com o usuario administrador que possui o email: admin@email.com e senha: admin

Para executar a aplicação basta rodar: `mvn spring-boot:run`

#### REST API -- CRUD de clientes
```
POST http://localhost:8080/clientes:
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)

GET http://localhost:8080/clientes
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)

GET http://localhost:8080/clientes/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)

PUT http://localhost:8080/clientes/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)

DELETE http://localhost:8080/clientes/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)
```

#### REST API -- CRUD de agências de turismo
```
POST http://localhost:8080/agencias:
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)

GET http://localhost:8080/agencias
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)

GET http://localhost:8080/agencias/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)

PUT http://localhost:8080/agencias/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)

DELETE http://localhost:8080/agencias/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)
```

REST API -- Retorna a lista de pacotes turísticos
```
GET http://localhost:8080/pacotes
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)
```

REST API -- Retorna a lista de pacotes turísticos do cliente de id = {id}
```
GET http://localhost:8080/pacotes/clientes/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)
```

REST API -- Cria um novo pacote turístico na agência de id = {id}
```
POST http://localhost:8080/pacotes/agencias/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)
```

REST API -- Retorna a lista de pacotes turísticos da agência de id = {id}
```
GET http://localhost:8080/pacotes/agencias/{id}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)
```

REST API -- Retorna a lista de pacotes turístivos cujo destino = {nome}
```
GET http://localhost:8080/pacotes/destinos/{nome}
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Samuel (33%), Gabriel (33%) e Leonardo (33%)
```
