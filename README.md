# PSTokenLab

Código para a solução do desafio técnico do processo seletivo para estagiário em mobile.

## Descrição do Desafio

O pessoal da Tokenlab gosta muito de filmes e decidiu construir uma API REST que disponibiliza informações sobre 20 filmes que estão entre os mais bem avaliados pelos usuários do TMDB.
O desafio consiste em elaborar um aplicativo (Android ou iOS) que obtenha os dados desta API, apresentando-os de forma simples e acessível, seguindo os padrões de usabilidade da plataforma. Preze pela qualidade e não pela velocidade, utilize o tempo que lhe foi dado da melhor forma possível.


## Características do aplicativo

* Projeto desenvolvido com Kotlin para Android.
* Mantém o usúario informado de que a aplicação está baixando/processando os dados através de uma Progress Bar.
* Utiliza o padrão de projeto MVP.
* Realiza o tratamento de erros, informa ao usuário que os dados não puderam ser atualizados quando está sem conexão ou não obteve a esperada da API
* Realiza armazenamento local dos dados após ter realizado o download das informações da API, para serem exibidos quando não houver conexão 

## Bibliotecas

* Retrofit2 - Realiza as requisições para a API (REST)
* Picasso (Square) - Realiza download e cache de imagens
* Paper db - Armazenamento de dados

## Informações da API

* Para obter a listagem dos filmes, basta realizar uma requisição HTTP utilizando o método GET no seguinte endpoint: https://desafio-mobile.nyc3.digitaloceanspaces.com/movies
* Para obter informações detalhadas de um determinado filme, basta adicionar o ID do mesmo ao final do endpoint anterior. Então se quisermos, por exemplo, obter os detalhes do filme de ID 240, basta realizar um GET no seguinte endpoint: https://desafio-mobile.nyc3.digitaloceanspaces.com/movies/240

## Referências

* https://medium.com/collabcode/consumindo-api-rest-no-android-com-retrofit-em-kotlin-parte-1-5e752ab8a877
* https://medium.com/@crossphd/android-image-loading-from-a-string-url-6c8290b82c5e
* https://drive.google.com/drive/folders/1g7NJoITL5NZJ9vuVZdmLMaoJRYFKtdQU?fbclid=IwAR1BH8OeYbG4ByGjqLCpiHqan_SKoVPYNuY8EO6KO1-1T39Y6FNpIsAIaT8
* https://drive.google.com/drive/folders/1l8SLCX5INqa2gaBkE7NIBmW6w1jXcNzf?fbclid=IwAR1BH8OeYbG4ByGjqLCpiHqan_SKoVPYNuY8EO6KO1-1T39Y6FNpIsAIaT8
