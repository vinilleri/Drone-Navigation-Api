# Drone Navigation API

API REST desenvolvida em Java com Spring Boot para navegação autônoma de um drone utilizando grafos e o algoritmo de Busca em Largura (Breadth-First Search - BFS).

A aplicação recebe a posição atual do drone, o destino e as informações do ambiente coletadas pelos sensores, constrói dinamicamente um grafo representando o mapa conhecido e calcula a próxima direção de movimento por meio do menor caminho disponível.

## Tecnologias

- Java 21
- Spring Boot
- Maven
- Grafos
- Busca em Largura (BFS)
- ESP32

## Como executar

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

## Endpoint

```
POST /droneapi/navegacao/controlar-drone
```

## Exemplo de requisição

```json
{
  "x": 0,
  "y": 0,
  "destino": {
    "x": 3,
    "y": 2
  },
  "adjacentes": [
    {
      "x": 1,
      "y": 0,
      "tipo": "LIVRE"
    }
  ]
}
```

## Resposta

```text
LESTE
```