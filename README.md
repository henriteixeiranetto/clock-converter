# Clock Converter

Projeto em Java que modela relógios em dois formatos — **24 horas (BR)** e **12 horas (US, AM/PM)** — com conversão automática entre os dois formatos.

O projeto usa recursos modernos do Java como **sealed classes** e **pattern matching em switch** para representar a hierarquia de relógios e realizar a conversão de forma segura em tempo de compilação.

## Estrutura

```
src/
├── Clock.java      # Classe abstrata selada (sealed), define hora/minuto/segundo e formatação
├── BRLClock.java    # Relógio no formato 24h
├── USClock.java     # Relógio no formato 12h (AM/PM)
└── Main.java        # Exemplo de uso
```

## Como funciona

`Clock` é uma classe abstrata `sealed` que só permite duas subclasses: `BRLClock` e `USClock`. Cada uma implementa seu próprio método `convert(Clock clock)`, que recebe um relógio de qualquer um dos dois formatos e retorna a hora convertida para o formato correspondente.

A conversão usa **pattern matching** para identificar o tipo do relógio recebido:

```java
switch (clock) {
    case USClock usClock -> // lógica de conversão US -> BR
    case BRLClock brlClock -> // lógica de conversão BR -> BR
}
```

Casos especiais tratados na conversão:
- **Meio-dia (12 PM)** e **meia-noite (12 AM)** são convertidos corretamente para `12` e `0`, respectivamente, no formato 24h.
- Horas fora do intervalo válido (ex: `25`) são normalizadas automaticamente (`25` vira `1`), assim como minutos e segundos acima de `59`.

## Exemplo de uso

```java
Clock brlClock = new BRLClock();
brlClock.setSecond(0);
brlClock.setMinute(0);
brlClock.setHour(25); // normalizado para 1

System.out.println(brlClock.getTime());
// 01:00:00

System.out.println(new USClock().convert(brlClock).getTime());
// 01:00:00 AM
```

## Requisitos

- Java 21+ (necessário para sealed classes e pattern matching em switch)

## Possíveis melhorias futuras

- Adicionar testes unitários (JUnit) cobrindo os casos de borda (meia-noite, meio-dia, overflow de hora)
- Suporte a fusos horários
- Validação de entrada com exceções customizadas
