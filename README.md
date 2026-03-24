# Travel Risk API

A REST API that evaluates the safety and geopolitical risk of flights. Users provide a departure and arrival airport (or a flight number), and the system calculates a risk score based on government travel advisories and recent news. Trips can be saved per user, and weekly email digests summarize any risk changes.

## Features

- Flight risk evaluation by airport codes or flight number
- Layover support in risk calculation
- Country-level risk based on US State Department travel advisories
- News aggregation per trip country
- User registration and JWT-based authentication
- Full CRUD for saved trips
- Risk history tracking per trip
- Weekly email digest via Spring Scheduler

## Tech Stack

Java 21, Spring Boot, Spring Security, Spring Data JPA, PostgreSQL, Gradle, Swagger (springdoc), Spring Mail

## Prerequisites

- Java 21
- PostgreSQL database
- A [NewsAPI](https://newsapi.org) key (free tier, localhost only)
- A [FlightLabs](https://www.flightlabs.com) key for flight number lookup
- A Mailtrap (or SMTP) account for email

## Setup

**1. Clone the repo**

```
git clone https://github.com/your-username/travel-risk-api.git
cd travel-risk-api
```

**2. Configure secrets**

Create `src/main/resources/application-secrets.properties` (this file is gitignored):

```properties
spring.mail.username=YOUR_MAILTRAP_USERNAME
spring.mail.password=YOUR_MAILTRAP_PASSWORD
newsapi.key=YOUR_NEWSAPI_KEY
flightlabs.key=YOUR_FLIGHTLABS_KEY
jwt.secret=YOUR_JWT_SECRET
```

**3. Set environment variables**

The app expects these variables (or a `.env` / Railway config):

```
DATABASE_URL=jdbc:postgresql://localhost:5432/your_db
PGUSER=your_db_user
PGPASSWORD=your_db_password
PORT=8080
```

**4. Run**

```
./gradlew bootRun
```

The server starts at `http://localhost:8080`.

Swagger UI: `http://localhost:8080/swagger-ui.html`

API docs: `http://localhost:8080/api-docs`

## API Overview

**Auth**
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register a new user |
| POST | /auth/login | Login and receive a JWT |

**Trips**
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /trips | Create a trip |
| GET | /trips | List all trips |
| GET | /trips/{id} | Get a trip |
| PUT | /trips/{id} | Update a trip |
| DELETE | /trips/{id} | Delete a trip |
| GET | /trips/{id}/risk-history | Get historical risk evaluations |

**Risk**
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /risk/evaluate | Evaluate risk by airport codes |
| POST | /risk/flight-number | Evaluate risk by flight number |
| GET | /risk/country/{countryCode} | Get country-level risk |

**News**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /news/{tripId} | Get travel news for a trip's countries |

## Limitations

- Advisory data is from the US State Department and is geared toward American travelers. The US has no self-advisory, so domestic routes default to Level 1.
- Advisory data is cached at startup. A server restart is required to pick up updated advisories.
- NewsAPI free tier only allows requests from localhost. Calls from a deployed server will fail silently and return no articles. Switching to a different provider (GNews, Currents API, etc.) is needed for production use.
- Flight number lookup uses the FlightLabs routes endpoint, which returns repeated historical routes rather than live flights.
- Flight lists must be provided in chronological order. Mixed transport between airports is not supported.
- Risk score is based on the worst-case country in the route, not a weighted average.
- JWT tokens cannot be invalidated server-side. Logout is handled client-side by discarding the token.

## Deployment

The API is deployed on Railway. Set the environment variables listed above in your Railway service config. `DATABASE_URL` should be a valid JDBC URL in the format `jdbc:postgresql://host:port/db`.

Live API docs: https://travel-risk-project-production.up.railway.app/api-docs
