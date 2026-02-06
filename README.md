# DriveAid360 Server

Backend server for the DriveAid360 roadside assistance platform.

## Features

DriveAid360 provides comprehensive roadside assistance including:

- **Breakdown Assistance**: Connect drivers with nearby service providers (mechanics, towing, fuel delivery)
- **Live Tracking**: Real-time provider location tracking with ETA
- **Communication**: In-app chat and call functionality
- **SOS Emergency**: One-tap emergency assistance with offline SMS fallback
- **AI Voice Assistant**: Voice-activated commands for hands-free operation
- **AI Smart Features**: Breakdown diagnosis, predictive maintenance, risk assessment
- **Insurance Integration**: Claims processing and document management
- **Maps & Navigation**: Traffic, weather, accident hotspots, and geo-fencing
- **Payment Processing**: LankaQR and card payments with invoice generation
- **Community Features**: Forums, peer help, and reward points
- **Admin Portal**: Provider verification, emergency monitoring, and analytics

## Technology Stack

- Java 17
- Spring Boot 3.2.2
- Spring Security with JWT
- Spring Data JPA
- PostgreSQL / H2 (development)
- Maven
- OpenAPI/Swagger for API documentation

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+ (optional, H2 is used by default for development)

### Running Locally

1. Clone the repository
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```
3. Access the API documentation at: http://localhost:8080/api/v1/swagger-ui.html
4. Health check endpoint: http://localhost:8080/api/v1/health

### Using Docker Compose

Start PostgreSQL database:
```bash
docker-compose up -d postgres
```

### Configuration

Configuration files are located in `src/main/resources`:
- `application.properties` - Default configuration
- `application-dev.properties` - Development environment
- `application-prod.properties` - Production environment

### API Documentation

Interactive API documentation is available via Swagger UI:
- http://localhost:8080/api/v1/swagger-ui.html
- API docs JSON: http://localhost:8080/api/v1/api-docs

## Project Structure

```
src/main/java/com/driveaid360/server/
├── auth/               # Authentication and authorization
├── user/               # User management
├── vehicle/            # Vehicle management
├── provider/           # Service provider domain
├── breakdown/          # Breakdown assistance
├── tracking/           # Live tracking
├── sos/                # Emergency SOS
├── insurance/          # Insurance claims
├── payment/            # Payment processing
├── community/          # Community features
├── config/             # Application configuration
├── common/             # Common utilities and DTOs
└── DriveAid360Application.java
```

## API Endpoints

### Health Check
- `GET /api/v1/health` - System health status

### Future Endpoints (To be implemented)
- `/api/v1/auth/**` - Authentication
- `/api/v1/users/**` - User management
- `/api/v1/providers/**` - Provider management
- `/api/v1/breakdown/**` - Breakdown requests
- `/api/v1/tracking/**` - Live tracking
- `/api/v1/sos/**` - Emergency SOS
- `/api/v1/insurance/**` - Insurance claims
- `/api/v1/payments/**` - Payment processing
- `/api/v1/community/**` - Community features

## Development

### Building
```bash
mvn clean package
```

### Running Tests
```bash
mvn test
```

### Code Style
Follow standard Java conventions and Spring Boot best practices.

## License

MIT License - See LICENSE file for details

## Support

For support and questions, contact: support@driveaid360.com
