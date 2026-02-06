# Security Policy

## Security Architecture

DriveAid360 Server implements a comprehensive security model designed for a production-ready REST API.

### Authentication & Authorization

**JWT-Based Authentication**
- Uses JSON Web Tokens (JWT) for stateless authentication
- Tokens are passed via `Authorization: Bearer <token>` headers
- No session cookies are used (stateless architecture)
- JWT library: `io.jsonwebtoken:jjwt` v0.12.3 (no known vulnerabilities)

**Role-Based Access Control (RBAC)**
- Supports multiple roles: Driver, Mechanic, Towing, Emergency Officer, Admin
- Permissions enforced at the endpoint level
- Will be implemented in Phase 2 (Epic S1)

### CSRF Protection

**Why CSRF is Disabled:**
CSRF (Cross-Site Request Forgery) protection is **intentionally disabled** for this API because:

1. **Stateless Architecture**: The API uses JWT tokens in Authorization headers, not cookies
2. **No Browser-Based Sessions**: Authentication doesn't rely on browser session cookies
3. **Standard Practice**: CSRF protection is not required for stateless REST APIs
4. **Industry Standard**: This follows Spring Security recommendations for JWT-based APIs

Reference: [Spring Security CSRF Documentation](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-when)

**Important Note:** If you add cookie-based authentication or traditional form login in the future, you MUST re-enable CSRF protection for those endpoints.

### Configuration Security

**Sensitive Data Management:**
1. **JWT Secret**: Externalized via `JWT_SECRET` environment variable
2. **Database Credentials**: Externalized via `DATABASE_*` environment variables
3. **Admin Credentials**: For development only, externalized via environment variables
4. **Never Committed**: `.env` file is excluded from version control

**Production Configuration:**
- Use `application-prod.properties` with `SPRING_PROFILES_ACTIVE=prod`
- All sensitive values MUST be provided via environment variables
- Swagger/OpenAPI documentation is disabled in production
- Health endpoint details are hidden in production

### CORS (Cross-Origin Resource Sharing)

**Current Configuration:**
- Development: Allows all origins (`*`) for local testing
- **Production TODO**: Must be restricted to specific domains:
  ```java
  configuration.setAllowedOrigins(List.of(
      "https://driveaid360.com",
      "https://app.driveaid360.com"
  ));
  ```

### Data Protection

**Sensitive Data Handling:**
1. **Passwords**: Encrypted using BCrypt (via `BCryptPasswordEncoder`)
2. **Audit Trail**: All entities track creation and modification timestamps
3. **Soft Deletes**: User data uses soft deletes for compliance (planned)

### Known Security Considerations

1. **CORS Wildcard**: Current CORS config allows all origins for development. **MUST** be restricted in production.
2. **H2 Console**: Enabled in development only. Automatically disabled when using PostgreSQL in production.
3. **Default Admin Credentials**: Present in development config only. Production uses JWT authentication exclusively.

## Reporting Security Vulnerabilities

If you discover a security vulnerability in DriveAid360, please report it to:
- Email: security@driveaid360.com
- Do not create public GitHub issues for security vulnerabilities

## Security Checklist for Deployment

Before deploying to production, ensure:

- [ ] `SPRING_PROFILES_ACTIVE=prod` is set
- [ ] `JWT_SECRET` is a strong, randomly generated key (minimum 256 bits)
- [ ] Database credentials are set via environment variables
- [ ] CORS allowed origins are restricted to your domains
- [ ] Swagger UI is disabled (`springdoc.swagger-ui.enabled=false`)
- [ ] Health endpoint details are hidden (`management.endpoint.health.show-details=never`)
- [ ] TLS/HTTPS is enabled at the reverse proxy/load balancer level
- [ ] Rate limiting is configured for authentication endpoints
- [ ] File upload size limits are appropriate for your use case

## Security Updates

- Regularly update dependencies using `mvn versions:display-dependency-updates`
- Monitor security advisories for Spring Boot and dependencies
- Subscribe to Spring Security announcements

## Compliance

DriveAid360 is designed to support:
- GDPR compliance (data protection, right to deletion)
- PCI DSS compliance (for payment processing)
- HIPAA considerations (for emergency medical data)

Specific compliance requirements will be documented as features are implemented.
