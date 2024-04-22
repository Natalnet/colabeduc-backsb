# Documentação das rotas e oque retornam

Este documento descreve os endpoints para as APIs `Descricao` e `Usuario`.

## API Descricao

### Endpoints

- **GET /api/descricoes/{idDescricao}**
  - Recupera uma descrição pelo seu ID.
  - **Parâmetros:**
    - `idDescricao` (parâmetro de caminho) - O ID da descrição.
  - **Retorna:** Um objeto JSON representando a descrição.

- **GET /api/descricoes/**
  - Recupera todas as descrições.
  - **Retorna:** Um array JSON contendo todas as descrições.

- **POST /api/descricoes**
  - Cria uma nova descrição.
  - **Corpo da Requisição:** 
    ```
        {
            "descricao": "Descrição da nova descrição"
        }
    ```
  - **Resposta:** Sem conteúdo.

- **PUT /api/descricoes**
  - Atualiza uma descrição existente.
  - **Corpo da Requisição:** 
  ```
    {
        "idDescricao": 1,
        "descricao": "Nova descrição atualizada"
    }
  ```
  - **Resposta:** Sem conteúdo.

- **DELETE /api/descricoes/{idDescricao}**
  - Exclui uma descrição pelo seu ID.
  - **Parâmetros:**
    - `idDescricao` (parâmetro de caminho) - O ID da descrição.
  - **Resposta:** Sem conteúdo.

## API Usuario

### Endpoints
- **GET /api/usuarios/{idUsuario}**
  - Recupera um usuário pelo seu ID. (Restrito aos papéis: ADMIN, GESTOR)
  - **Parâmetros:**
    - `idUsuario` (parâmetro de caminho) - O ID do usuário.
  - **Retorna:** Um objeto JSON representando o usuário.

- **GET /api/usuarios/**
  - Recupera todos os usuários. (Restrito aos papéis: ADMIN, GESTOR)
  - **Retorna:** Um array JSON contendo todos os usuários.

- **POST /api/usuarios**
  - Cria um novo usuário. (Restrito aos papéis: ADMIN, GESTOR)
  - **Corpo da Requisição:** 
  ```
      {
        "nome": "Nome do Novo Usuário",
        "username": "novousuario",
        "password": "novasenha123",
        "email": "novousuario@example.com"
    }
  ```
  - **Resposta:** Sem conteúdo.

- **PUT /api/usuarios**
  - Atualiza um usuário existente. (Restrito aos papéis: ADMIN, GESTOR)
  - **Corpo da Requisição:** 
  ```
    {
        "idUsuario": 1,
        "nome": "Novo Nome do Usuário"
    }
  ```
  - **Resposta:** Sem conteúdo.

- **DELETE /api/usuarios/{idUsuario}**
  - Exclui um usuário pelo seu ID. (Restrito aos papéis: ADMIN, GESTOR)
  - **Parâmetros:**
    - `idUsuario` (parâmetro de caminho) - O ID do usuário.
  - **Resposta:** Sem conteúdo.

- **POST /api/usuarios/recuperar-senha**
  - Inicia o processo de recuperação de senha enviando um token de redefinição para o email do usuário.
  - **Corpo da Requisição:** 
  ```
    {
        "email": "usuario@example.com"
    }
  ```
  - **Resposta:** Sem conteúdo.

- **POST /api/usuarios/resetar-senha**
  - Reseta a senha do usuário usando o token de redefinição fornecido.
  - **Corpo da Requisição:** 
  ```
    {
        "token": "Token_de_recuperacao",
        "novaSenha": "novasenha123"
    }
  ```
  - **Resposta:** Sem conteúdo.

# Erros

| Rota                                  | Código de Status | Mensagem de Erro                     | Descrição                                                                                                     |
|---------------------------------------|------------------|--------------------------------------|---------------------------------------------------------------------------------------------------------------|
| GET /api/descricoes/{idDescricao}    | 404              | Descrição não encontrada             | Indica que a descrição associada ao ID fornecido não foi encontrada.                                         |
| POST /api/descricoes                  | 400              | JSON inválido                        | Indica que o JSON enviado na requisição é inválido ou está ausente.                                          |
| PUT /api/descricoes                   | 404              | Descrição não encontrada             | Indica que a descrição associada ao ID fornecido não foi encontrada.                                         |
| DELETE /api/descricoes/{idDescricao} | 404              | Descrição não encontrada             | Indica que a descrição associada ao ID fornecido não foi encontrada.                                         |
| GET /api/usuarios/{idUsuario}        | 401              | Usuário não autorizado ou não encontrado | Indica que o usuário não está autorizado a acessar o recurso ou que o usuário associado ao ID fornecido não foi encontrado. |
| POST /api/usuarios                    | 400              | JSON inválido                        | Indica que o JSON enviado na requisição é inválido ou está ausente.                                          |
| PUT /api/usuarios                     | 404              | Usuário não encontrado               | Indica que o usuário associado ao ID fornecido não foi encontrado.                                            |
| DELETE /api/usuarios/{idUsuario}     | 404              | Usuário não encontrado               | Indica que o usuário associado ao ID fornecido não foi encontrado.                                            |
| POST /api/usuarios/recuperar-senha   | 404              | Email não encontrado                 | Indica que o email fornecido para recuperação de senha não está associado a nenhum usuário.                   |
| POST /api/usuarios/resetar-senha     | 400              | Token inválido ou expirado           | Indica que o token de recuperação de senha fornecido é inválido ou expirou.                                  |
