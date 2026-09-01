<a id="readme-top"></a>

Issues MIT License

<br /> <div align="center"> <h3 align="center">Spring Security JWT Authentication</h3> <p align="center"> A Spring Boot backend implementing JWT-based authentication, custom exception handling, and route-level authorization with Spring Security. <br /> <a href="#getting-started"><strong>Explore the docs »</strong></a> <br /> <br /> <a href="#api-endpoints">View Endpoints</a> &middot; <a href="#roadmap">Report Bug / Request Feature</a> </p> </div> <details> <summary>Table of Contents</summary> <ol> <li><a href="#about-the-project">About The Project</a></li> <li><a href="#built-with">Built With</a></li> <li> <a href="#getting-started">Getting Started</a> <ul> <li><a href="#prerequisites">Prerequisites</a></li> <li><a href="#installation">Installation</a></li> </ul> </li> <li><a href="#usage">Usage</a></li> <li><a href="#docker">Docker</a></li> <li><a href="#api-endpoints">API Endpoints</a></li> <li><a href="#project-structure">Project Structure</a></li> <li><a href="#roadmap">Roadmap</a></li> <li><a href="#contributing">Contributing</a></li> <li><a href="#license">License</a></li> </ol> </details>
About The Project

This project implements a JWT-based authentication and authorization system using Spring Boot and Spring Security. It handles user registration and login, issues signed JWTs, and secures endpoints using SecurityConfiguration with role-based requestMatchers. Custom exceptions (such as UserAlreadyExistsException) are used to produce clean, meaningful error responses instead of generic stack traces.

Key goals of this project:

Stateless authentication using JWT (no server-side sessions)
Clear separation of security config, exception handling, and business logic
A foundation that's easy to extend with roles, refresh tokens, and more granular access rules
<p align="right">(<a href="#readme-top">back to top</a>)</p>
Built With
Spring
Java
Maven
JWT
<p align="right">(<a href="#readme-top">back to top</a>)</p>
Getting Started

To get a local copy up and running, follow these steps.

Prerequisites
JDK 17 or higher
Maven 3.8+
An IDE (IntelliJ IDEA / VS Code recommended)
Installation
Clone the repo
sh
   git clone https://github.com/ManojShrestha10/JwtAuth.git
Navigate into the project directory
sh
   cd JwtAuth
Install dependencies
sh
   mvn clean install
Set your JWT secret and expiration in application.properties (or application.yml)
properties
   jwt.secret=your_secret_key_here
   jwt.expiration=3600000
Run the application
sh
   mvn spring-boot:run
<p align="right">(<a href="#readme-top">back to top</a>)</p>
Usage

Once the app is running (default: http://localhost:8080), you can register a user, log in to receive a JWT, and use that token in the Authorization: Bearer <token> header to access protected endpoints defined in SecurityConfiguration.

Example login response:

json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expiresIn": 3600000
}
<p align="right">(<a href="#readme-top">back to top</a>)</p>
Docker

This app is set up to run in a Docker container, so anyone can spin it up without installing Java or Maven locally.

Build the image
sh
docker build -t jwt-auth-app .
Run the container
sh
docker run -p 8080:8080 \
  -e JWT_SECRET=your_secret_key_here \
  -e JWT_EXPIRATION=3600000 \
  jwt-auth-app

The app will be available at http://localhost:8080.

Using Docker Compose (optional)

If you're running this alongside a database (e.g. PostgreSQL/MySQL), a docker-compose.yml makes it easier to bring everything up together:

sh
docker-compose up --build
<p align="right">(<a href="#readme-top">back to top</a>)</p>
API Endpoints
Method	Endpoint	Description	Auth Required
POST	/api/auth/register	Register a new user	No
POST	/api/auth/login	Authenticate and receive a JWT	No
GET	/api/admin/**	Admin-only resources	Yes (ROLE_ADMIN)
GET	/api/user/**	Authenticated user resources	Yes

Adjust this table to match your actual requestMatchers in SecurityConfiguration.

<p align="right">(<a href="#readme-top">back to top</a>)</p>
Project Structure
src/main/java/com/springsecurity/jwtauthentication/
├── config/
│   └── SecurityConfiguration.java
├── exception/
│   ├── UserAlreadyExistsException.java
│   └── GlobalExceptionHandler.java
├── controller/
├── service/
├── repository/
└── model/
<p align="right">(<a href="#readme-top">back to top</a>)</p>
Roadmap
 User registration with duplicate-user check
 Custom exception handling (UserAlreadyExistsException)
 Route-level authorization via requestMatchers
 Refresh token support
 Role-based access control (multiple roles)
 Global exception handler for all custom exceptions
 Unit and integration tests

See the open issues for a full list of proposed features and known issues.

<p align="right">(<a href="#readme-top">back to top</a>)</p>
Contributing

Contributions make the open-source community a great place to learn and build. Any contributions are greatly appreciated.

Fork the repo
Create your feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'feat: add AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request
<p align="right">(<a href="#readme-top">back to top</a>)</p>
License

Distributed under the MIT License. See LICENSE for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p> <!-- MARKDOWN LINKS & BADGES -->
