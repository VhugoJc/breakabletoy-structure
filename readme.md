# Project Template: React Frontend & Spring Boot Backend

This repository serves as an initial structure for projects that combine a **React frontend** with a **Spring Boot backend**. It includes configurations for **Docker**, **CI/CD with GitHub Actions**, and **testing frameworks** to streamline development and deployment.

## Features


- **Frontend**: React.js
  - Modern JavaScript framework for building user interfaces.
  - Pre-configured with essential dependencies and scripts.
  
- **Backend**: Spring Boot
  - Robust Java framework for building RESTful APIs.
  - Includes basic setup for controllers, services, and repositories.


- **Docker**:
  - Dockerfiles for both frontend and backend.
  - Docker Compose for running the entire stack locally.

- **CI/CD**:
  - GitHub Actions workflows for automated testing, building, and deployment.

- **Testing**:
  - Frontend: Jest and React Testing Library.
  - Backend: JUnit and Mockito.

## Getting Started

### Prerequisites

- [Node.js](https://nodejs.org/) (for frontend)
- [Java 17+](https://adoptopenjdk.net/) (for backend)
- [Docker](https://www.docker.com/) and Docker Compose
- [Git](https://git-scm.com/)

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-name>
   ```
2. Build and run the project using Docker Compose:
   ```bash
   docker-compose up --build
   ```
3. Access the application:
- Frontend: `http://localhost:3000`
- Backend: `http://localhost:8080`

## Directory Structure
```bash
.
├── frontend/          # React application
├── backend/           # Spring Boot application
├── .github/workflows/ # CI/CD workflows
├── docker-compose.yml # Docker Compose configuration
└── README.md          # Project documentation
```
## CI/CD with GitHub Actions
This project includes GitHub Actions workflows for:
- Running tests on every push or pull request.
- Building and deploying Docker images.

### Testing
Run tests with jest:
```bash
cd frontend
npm test
```
### Backend
```bash
cd backend
./mvnw test
```

## Run Containers

1. **Create the `.env` File**:
   - Copy the `.env.example` file to `.env`:
     ```bash
     cp backend/.env.example backend/.env
     ```
   - Update the `.env` file with your environment variables (e.g., database URL, username, password).

2. **Run the Containers**:
   - Using **Podman Compose**:
     ```bash
     podman-compose up --build
     ```
   - Using **Docker Compose**:
     ```bash
     docker-compose up --build
     ```

3. **Access the Application**:
   - Frontend: [http://localhost:3000](http://localhost:3000)
   - Backend: [http://localhost:8080](http://localhost:8080)

4. **Stop the Containers**:
   - Using **Podman Compose**:
     ```bash
     podman-compose down
     ```
   - Using **Docker Compose**:
     ```bash
     docker-compose down
     ```
## Contributing
Feel free to fork this repository and submit pull requests. Contributions are welcome!

## License
This project is licensed under the MIT License. See the LICENSE file for detail

