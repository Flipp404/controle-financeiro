<div align="center">

# 💸 Controle Financeiro

**Descubra para onde seu dinheiro está indo, e o que dá para cortar.**

![Java](https://img.shields.io/badge/Java-Swing-orange?logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Licença](https://img.shields.io/badge/licença-a%20definir-lightgrey)

</div>

---

> 🚧 **Projeto em desenvolvimento.** Hoje ele é um protótipo funcional. Abaixo está o que já existe e para onde ele está indo.

## 🎯 A ideia

A maioria dos apps financeiros só mostra *quanto* você gastou. Este projeto quer responder outra pergunta: **onde** seu dinheiro está indo e **o que pode ser reduzido** para você juntar mais.

A meta é uma aplicação desktop com gastos organizados em **tabelas**, separados por **categorias** e apresentados em uma interface visual e fácil de entender.

## ✅ O que já funciona

- Definir a renda mensal
- Cadastrar gastos com descrição e valor
- Histórico de despesas na tela
- Balanço em tempo real: saldo restante ou déficit, com alerta visual quando fica no vermelho
- Validação básica de entrada (campos vazios, valores inválidos ou negativos)

## 🗺️ Roadmap

- [ ] Corrigir validação de valores especiais (`NaN`, `Infinity`)
- [ ] Trocar `double` por `BigDecimal` nos valores monetários
- [ ] Separar regra de negócio da interface (para permitir testes)
- [ ] Testes unitários com JUnit
- [ ] Categorias de gastos (alimentação, transporte, lazer...)
- [ ] Tabela de gastos (`JTable`) com remover/editar
- [ ] Resumo por categoria, com total e percentual
- [ ] Gráficos (pizza/barras)
- [ ] Insights: maior categoria de gasto e sugestões do que cortar
- [ ] Persistência dos dados (arquivo ou SQLite)
- [ ] Migrar para Maven/Gradle e organizar em pacotes
- [ ] Tema visual moderno (ex.: FlatLaf)

## 📸 Preview

> *Screenshot em breve. Vou adicionar assim que a interface estiver mais refinada.*

<!-- Quando tiver o print: salve em docs/screenshot.png e troque a linha acima por:
![Tela principal](docs/screenshot.png) -->

## 🚀 Como rodar

**Requisitos:** JDK 11 ou superior.

```bash
# 1. Clone o repositório
git clone https://github.com/Flipp404/controle-financeiro.git
cd controle-financeiro

# 2. Compile (os .class vão para a pasta out/)
javac -d out src/*.java

# 3. Execute
java -cp out TelaContador
```

Ou abra a pasta no IntelliJ IDEA e rode a classe `TelaContador`.

## 🗂️ Estrutura

```
controle-financeiro/
├── src/
│   ├── Gasto.java          # Modelo: descrição e valor de uma despesa
│   └── TelaContador.java   # Interface (Swing) e lógica de balanço
├── .gitignore
└── README.md
```

## 🧰 Tecnologias

- **Java** (JDK 11+)
- **Swing** para a interface gráfica

## 📚 Sobre o projeto

Este é um projeto de estudo. Estou usando ele para praticar Java, boas práticas e Git, evoluindo em commits pequenos. O histórico de commits mostra essa evolução passo a passo.

## 👤 Autor

**Felipe Lemos**
GitHub: [@Flipp404](https://github.com/Flipp404)
