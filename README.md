# Tech-Challenge

Desafio desenvolvido com Kubernets e Clean-Architecture para a fase02 do curso de Software Architecture da FIAP Pós Tech.

## Versão
2.2 [Dockerhub](https://hub.docker.com/repository/docker/pedrovcorsino/tech_challenge/tags "Go to Dockerhub")

## Saga Coreografado
[SAGA.md](https://github.com/PedroVCorsino/Tech-Challenge-Financeiro/blob/main/SAGA.md) 

## Índice
<a href="#tecnologias">Tecnologias</a> 
<a href="#autores">Autores</a>

## Tecnologias
<div style="display: inline_block"><br>
    <img align="center" alt="java" height="50" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg">    
    <img align="center" alt="jspring" height="40" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" />  
    <img align="center" alt="postgre" height="50" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original-wordmark.svg">
    <img align="center" alt="kubernets" height="50" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kubernetes/kubernetes-plain-wordmark.svg">  
</div>

## Banco de dados



## Documentação do sistema (DDD) utilizando a linguagem ubíqua.

### Domínios
- Subdomínio Principal:
    - Comida
- Subdomínio Genérico:
    - Lógica de pagamento integrada ao mercado pago.
- Subdomínio Suporte:
    - Gestão de estoque,
    - funcionários, clientes,
    - estratégias de marketing


### Contextos delimitados

- Pedido (Realização do pedido e pagamento) 
  ![image](https://github.com/PedroVCorsino/Tech-Challenge/assets/61948860/0c627219-8fb8-4bdc-b88a-3d0db6087973)

- Cozinha (Preparação e entrega do pedido)
  ![image](https://github.com/PedroVCorsino/Tech-Challenge/assets/61948860/823b0576-5524-4397-9411-6805505dfb85)

### Dicionário de linguagem ubíqua
- Identificação: Pode se identificar usando CPF, nome, e-mail ou não se identificar.
- Produto: Quaisquer itens vendidos pela lanchonete, divididos nas categorias Lanche, Acompanhamento, Bebida e Sobremesa.
- Combo: É uma oferta especial que combina um lanche, um acompanhamento, uma bebida e uma sobremesa por um preço promocional.
- Lanche: Refere-se ao item principal do cardápio, geralmente um sanduíche ou hambúrguer, ou uma opção de refeição vegana.
 - Acompanhamento: É uma opção adicional que pode ser escolhida juntamente com o lanche. Pode incluir batatas fritas, nuggets, onion rings, salada, ou outras opções de acompanhamentos.
- Bebida: São as opções líquidas disponíveis para serem consumidas junto com o lanche. Isso pode incluir refrigerantes, sucos, água, chás gelados, milkshakes, entre outros.
- Sobremesa: Refere-se a um item do cardápio que é servido após a refeição principal. Pode incluir opções como sorvetes, tortas, bolos, milkshakes especiais ou outras delícias doces.
- Categoria: Ou tipo, se refere a qual tipo de produto entre as opções lanche, acompanhamento, bebida e sobremesa.
---
  

## Autores
- [Diego Amorim](https://github.com/dieg0amorim)
- [Gabriela Ribeiro](https://github.com/gabsribeiro)
- [Luzivan Gois](https://github.com/luzivanmgois)
- [Pedro Vinicius](https://github.com/PedroVCorsino)
