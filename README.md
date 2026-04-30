# AutoVault
AutoVault is a Java Spring Boot–based backend system for managing dealer and vehicle inventory. It provides scalable REST APIs for handling vehicle data, dealer records, and stock operations, showcasing clean code, modular design, and real-world backend development practices.

## Tech Stack
- Spring Boot
- JPA / Hibernate
- H2 Database

## Features
- Multi-tenant architecture using X-Tenant-Id
- Dealer & Vehicle CRUD
- Pagination & sorting
- Advanced filtering
- Subscription-based query
- Admin analytics

## Headers
- X-Tenant-Id (Required)
- X-Role = GLOBAL_ADMIN (Admin APIs)

## Run
mvn spring-boot:run

## H2 Console
http://localhost:8080/h2-console

## Design Decisions
- Modular Monolith
- Tenant enforced at filter + DB query
- Admin count is GLOBAL (not tenant-scoped)

## Validation
- Email validation
- Required fields enforced

## Security
- Header-based role check

## Notes
- Cross-tenant access returns 403
- Missing tenant returns 400