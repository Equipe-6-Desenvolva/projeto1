# QuartoLivre - Sistema de Reservas de Hotel

Este projeto é um sistema simples de reservas de hotel desenvolvido em Java. Ele permite o cadastro de clientes, quartos (comuns e VIP), reservas e avaliações, além de consultar e cancelar reservas.

## Conceitos de POO Utilizados
- Abstração: A classe Quartos é abstrata e serve como base para os tipos de quartos do sistema.
- Herança: As classes QuartoComum e QuartoVip herdam de Quartos.
- Polimorfismo: Métodos como isVip() e toString() são sobrescritos nas subclasses, permitindo tratar diferentes tipos de quartos de forma uniforme. O sistema manipula listas do tipo Quartos, podendo armazenar tanto QuartoComum quanto QuartoVip.
- Encapsulamento: Os atributos das classes (como em Reservas, Quartos, etc.) são privados e acessados por meio de métodos getters e setters.

## Funcionalidades
- Cadastro de clientes
- Cadastro de quartos comuns e VIP
- Cadastro e consulta de reservas
- Cancelamento de reservas
- Cadastro de avaliações
- Consulta de quartos disponíveis e todos os quartos

## Estrutura do Projeto
```
projeto1/
├── src/
│   ├── Main.java
│   ├── Models/
│   │   ├── Avaliacao.java
│   │   ├── Cliente.java
│   │   ├── Hotel.java
│   │   ├── QuartoComum.java
│   │   ├── Quartos.java
│   │   ├── QuartoVip.java
│   │   └── Reservas.java
│   ├── Services/
│   │   └── ReservasService.java
│   └── View/
│       ├── AvaliacaoConsole.java
│       └── ClienteConsole.java
└── projeto1.iml
```

## Como Executar
1. Abra o projeto em sua IDE Java.
2. Execute a classe `Main.java`.

## Observações
- O sistema é totalmente em modo console.
- O código foi desenvolvido com fins didáticos
- Não há persistência em banco de dados; todos os dados são mantidos em memória durante a execução.

## Melhorias Futuras
- Adicionar persistência em banco de dados.
- Implementar interface gráfica.
- Adicionar autenticação de usuários.

---

