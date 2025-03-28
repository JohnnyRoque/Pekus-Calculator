# Pekus Calculator
Teste Pekus: Desenvolvedor Android

Este app foi escrito em Kotlin, seguindo as diretrizes do Material Design 3, usando Retrofit e Okhttp para o consumo da API.

### Principais componentes:

* Android Jetpack Components (ViewModel, Kotlin coroutines, Flow, Navigation)
* Jetpack Compose
* Software architecture - MVVM
* clean architecture
* App modularization
* Dependency injection - Koin
 
## Arquitetura

O app segue as recomendações estabelecidas no Guia de arquitetura de aplicativos, sendo divido em módulos:

Essa estratégia permite que o código seja isolado e mais fácil de ler, entender, testar e manter. Além do padrão MVVM  que é utilizado para separar a lógica de apresentação da lógica de negócios.

## Integração com Retrofit e OkHttp

O Pekus Calculator utiliza as bibliotecas Retrofit e OkHttp para comunicação com APIs REST, permitindo a integração com serviços externos de forma eficiente e segura.

Definição de Endpoints: O Retrofit é usado para criar interfaces de serviço que definem as requisições HTTP (como GET, POST, etc.).
Conversão Automática: Com suporte kotlin Serialization, os dados recebidos do servidor são automaticamente convertidos em objetos Kotlin, facilitando o uso dentro do app.
Implementação de Casos de Uso: Os casos de uso, como ClearMathListUseCase, DeleteMathUseCase, GetMathChartUseCase e InsertNewMathUseCase  utilizam os serviços do Retrofit para fazer chamadas à API e processar os dados retornados.

  
## Overview

O Pekus Calculator é um aplicativo projetado para  realizar operações aritiméticas onde os dados são armazenados em servidor. 

<img src="https://github.com/user-attachments/assets/084a376b-de04-4739-b2d6-4a561aaa3362" width="45%" />
<img src="https://github.com/user-attachments/assets/dafd9c4d-2937-4be4-ad92-578810de13ff" width="45%" />
<img src="https://github.com/user-attachments/assets/37b3bda3-7ece-402e-830e-a67144b158bb" width="45%" />








