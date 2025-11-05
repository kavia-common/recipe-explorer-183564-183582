# recipe-explorer-183564-183582

Recipe Explorer backend (Spring Boot) provides REST APIs for:
- Authentication: register, login
- Recipes: CRUD
- Search: by title, ingredients, tags with pagination

How to run
- From recipe_backend: ./gradlew bootRun
- Swagger UI: /swagger-ui.html (or visit /docs to be redirected)
- Health: /health

Endpoints
- Auth
  - POST /api/auth/register
  - POST /api/auth/login
- Recipes
  - GET /api/recipes?page=0&size=10
  - GET /api/recipes/{id}
  - POST /api/recipes
  - PUT /api/recipes/{id}
  - DELETE /api/recipes/{id}
  - GET /api/recipes/search?title=&ingredients=csv&tags=csv&page=0&size=10

Notes
- The application uses in-memory repositories with seed data (2 users, 3 recipes).
- No external DB required. H2 is configured for future JPA readiness.
- Token returned by auth is a mock token. No JWT validation is enforced.

Environment variables
- None required currently. If introducing JWT in future:
  - JWT_SECRET
  - JWT_EXPIRES_IN
See .env.example for documentation.
