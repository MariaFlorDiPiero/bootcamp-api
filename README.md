BOOTCAMP APT - GERENCIAMENTO DE ALUNOS E CURSOS
Java
Maven

API simples para gerenciamento de alunos e cursos de um bootcamp, desenvolvida em Java puro (sem frameworks) como parte de um projeto acadêmico.

📋 Funcionalidades
Alunos

POST /alunos - Cadastrar novo aluno

GET /alunos - Listar todos os alunos

Cursos

POST /cursos - Criar novo curso

GET /cursos - Listar todos os cursos

🚀 Como Executar
Pré-requisitos
Java 17+

Maven 3.9+

Instalação
Clone o repositório:

bash
Copy
git clone https://github.com/MariaFlorDiPiero/bootcamp-api.git
cd bootcamp-api
Compile o projeto:

bash
Copy
mvn clean package
Execute a aplicação:

bash
Copy
java -jar target/bootcamp-api-1.0-SNAPSHOT.jar
A API estará disponível em http://localhost:8080

📚 Estrutura do Projeto
Copy
bootcamp-api/
├── src/
│   ├── main/
│   │   ├── java/com/bootcamp/api/
│   │   │   ├── Main.java          # Ponto de entrada
│   │   │   ├── model/             # Modelos de dados
│   │   │   ├── service/           # Lógica de negócio
│   │   │   └── handler/           # Manipulação de requests HTTP
│   │   └── resources/
├── target/                        # Gerado durante o build
├── pom.xml                        # Configuração Maven
└── README.md                      # Este arquivo
🛠️ Tecnologias Utilizadas
Java 17 - Linguagem principal

Maven - Gerenciamento de dependências e build

HTTP Server nativo do Java - Para criar a API REST

JSON manual - Serialização/deserialização

📝 Exemplos de Uso
Cadastrar Aluno
bash
Copy
curl -X POST -H "Content-Type: application/json" -d '{
    "nome": "Maria Flor",
    "email": "maria@email.com",
    "matricula": "2024001"
}' http://localhost:8080/alunos
Listar Cursos
bash
Copy
curl http://localhost:8080/cursos
🤝 Contribuição
Contribuições são bem-vindas! Siga estes passos:

Faça um fork do projeto

Crie um branch (git checkout -b feature/nova-feature)

Commit suas mudanças (git commit -m 'Adiciona nova feature')

Push para o branch (git push origin feature/nova-feature)

Abra um Pull Request

📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

Desenvolvido com ❤️ por Maria Flor Di Piero
