# atividade-sistemas

Aplicação cliente/servidor em Java que simula um cofre digital usando sockets TCP e uma thread por cliente.

## Compilar

```bash
javac -d out src/*.java
```

## Executar

Em um terminal, iniciar o servidor:

```bash
java -cp out Servidor
```

Em outro terminal, iniciar um cliente:

```bash
java -cp out Cliente
```

Também é possível informar porta no servidor:

```bash
java -cp out Servidor 5000
```

E host/porta no cliente:

```bash
java -cp out Cliente localhost 5000
```
