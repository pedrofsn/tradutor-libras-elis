## tradutor-libras-elis
Trabalho de Conclusão de Curso em Sistemas de Informação pela Universidade Federal de Goiás - UFG.

## ELiS Mobile
O ELiS Mobile é um aplicativo Android para dispositivos móveis. Ele contém a funcionalidade de tradução de palavras e tem como entrada termos em português ou em ELiS. Para que o aplicativo seja executado, é necessário que o dispositivo esteja com o sistema operacional Android na versão 2.3.3 ou superior e de acesso à internet, para que as consultas à base de dados possam ser realizadas através de um servidor na nuvem.

## Publicação
[Link](https://drive.google.com/file/d/0B_OCeC6ob9ZNam9FQUJ1dUF3M0k/view?usp=sharing) do PDF do artigo publicado nos Anais da V Escola Regional de Informática de Mato Grosso - Barra do Garças/MT

## Pessoas envolvidas no trabalho
Aluno: Pedro Francisco de Sousa Neto

Orientador: Marcelo Ricardo Quinta

Coorientadora: Mariângela Estelita Barros

## Versão
- 1.2.0:
```
> Refatoramento dos códigos usando ES6
> Inclusão de Async/Await
> Separação das camadas (Routes/Models/Controllers)
> Adicionado .env para configurações sensíveis
> Separação do App/Mongo, para uso de Testes
```

## Modo de Usar:
```
0.
> Para uso de outro servidor Mongodb, renomeie .env-default para .env e faça as modificações necessárias.

1. 
yarn install OU
npm install

2. 
yarn start OU 
npm start OU 
node index.js
```

## Contribuidores
- [Renato Siqueira](https://github.com/RenatoSiqueira)