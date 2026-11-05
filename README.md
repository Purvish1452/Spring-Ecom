# Spring E-commerce

A small e-commerce prototype with a Spring Boot REST API and a React storefront. The backend provides product endpoints, including product image upload and retrieval; the frontend provides catalog, product, cart, and product-entry screens.

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
└── ecom-frontend-5/      # React/Vite application
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
cd ecom-frontend-5
npm install
npm run dev
```

Vite prints the local frontend URL when it starts. The frontend currently has its API base URL hard-coded to `http://localhost:8080/api`.

## Backend API

The implemented routes are prefixed with `/api`:

| Method | Route | Description |
|---|---|---|
| `GET` | `/api/product` or `/api/products` | List products |
| `GET` | `/api/product/{id}` | Get a product by ID |
| `GET` | `/api/product/{productId}/image` | Return the image data stored for a product |
| `GET` | `/api/products/search?keyword={text}` | Search product name, description, brand, or category |
| `POST` | `/api/product` or `/api/products` | Create a product with an image |
| `PUT` | `/api/product/{id}` | Update a product and optionally replace its image |
| `DELETE` | `/api/product/{id}` | Delete a product |

Product creation uses `multipart/form-data` with two parts:

- `product`: JSON for the product entity
- `imageFile`: the uploaded image file

The entity fields are `name`, `description`, `brand`, `price`, `category`, `releaseDate`, `productAvailable`, and `stockQuantity`. The backend accepts the legacy names `desc`, `available`, and `quantity` as aliases. Uploaded image metadata and bytes are stored with the product. `releaseDate` uses the `yyyy-MM-dd` format.

## Frontend features

The UI includes product listing and category filtering, product details, product creation/edit screens, a cart persisted in browser local storage, and a theme toggle. The backend allows requests from the local Vite development origins `http://localhost:5173` and `http://127.0.0.1:5173`.

## Known limitations

- Checkout is currently a frontend-only flow; the backend does not implement orders or payment processing.
- `src/main/resources/data1.sql` contains sample records, but this nonstandard filename is not automatically executed with the current configuration.
- The current test suite contains a Spring application-context load test.

## Useful commands

Run backend tests:

```powershell
.\mvnw.cmd test
```

From `ecom-frontend-5`, build or lint the frontend:

```powershell
npm run build
npm run lint
```
