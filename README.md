# Spring E-commerce

A small e-commerce prototype with a Spring Boot REST API and a React storefront. The backend provides product endpoints and accepts image-upload requests; the frontend provides catalog, product, cart, and product-entry screens.

> **Integration status:** The frontend and backend are not fully in sync yet. The frontend calls `/api/products` for catalog/search data, while the backend currently exposes `/api/product`. The frontend also expects search and update endpoints that the backend does not currently implement. See [Known limitations](#known-limitations).

## Technology

- **Backend:** Java 21, Spring Boot, Spring Web MVC, Spring Data JPA, Hibernate, Lombok
- **Database:** H2 in-memory database
- **Frontend:** React 18, Vite, React Router, Axios, Bootstrap

## Project layout

```text
.
├── src/
│   ├── main/java/com/purvish/ecom_proj/
│   │   ├── controller/   # Product REST endpoints
│   │   ├── model/        # JPA product entity
│   │   ├── repo/         # Spring Data repository
│   │   └── service/      # Product operations
│   ├── main/resources/   # Application properties and sample SQL
│   └── test/             # Spring application context test
└── ecom-frontend-4/      # React/Vite application
```

## Requirements

- Java 21
- Node.js and npm

Maven is not required separately; the repository includes the Maven Wrapper.

## Run locally

Start the backend from the repository root:

```powershell
.\mvnw.cmd spring-boot:run
```

The API listens on `http://localhost:8080`. Its configured H2 database is in memory, so database contents are not retained after the application stops. `src/main/resources/data1.sql` contains sample product inserts, but Spring Boot does not automatically run this nonstandard script filename with the current configuration.

In another terminal, start the frontend:

```powershell
cd ecom-frontend-4
npm install
npm run dev
```

Vite prints the local frontend URL when it starts. The frontend currently has its API base URL hard-coded to `http://localhost:8080/api`.

## Backend API

The implemented routes are prefixed with `/api`:

| Method | Route | Description |
|---|---|---|
| `GET` | `/api/product` | List products |
| `GET` | `/api/product/{id}` | Get a product by ID |
| `GET` | `/api/product/{productId}/image` | Return the image data stored for a product |
| `POST` | `/api/product` | Create a product with an image |
| `DELETE` | `/api/product/{id}` | Delete a product |

Product creation uses `multipart/form-data` with two parts:

- `product`: JSON for the product entity
- `imageFile`: the uploaded image file

The entity fields are `name`, `desc`, `brand`, `price`, `category`, `releaseDate`, `available`, and `quantity`. The entity has image metadata and image-byte fields. `releaseDate` is formatted as `dd-MM-yyyy` in JSON.

## Frontend features

The UI includes product listing and category filtering, product details, product creation/edit screens, a cart persisted in browser local storage, and a theme toggle. These screens are present in the frontend, but some rely on backend routes or field names that are not currently available or aligned.

## Known limitations

- The frontend requests `/api/products` and `/api/products/search`, while the backend currently only maps the singular `/api/product` routes listed above.
- The frontend submits product updates with `PUT /api/product/{id}`, but there is no mapped update endpoint in the backend.
- The frontend expects fields such as `description`, `productAvailable`, and `stockQuantity`; the backend entity uses `desc`, `available`, and `quantity`.
- Product creation records the uploaded image's name and content type but does not currently save its bytes, so the image endpoint cannot return images uploaded through this flow.
- There is no backend search endpoint or order/checkout API. The current checkout UI is not a complete order-processing flow.
- The current test suite contains a Spring application-context load test.

## Useful commands

Run backend tests:

```powershell
.\mvnw.cmd test
```

From `ecom-frontend-4`, build or lint the frontend:

```powershell
npm run build
npm run lint
```
