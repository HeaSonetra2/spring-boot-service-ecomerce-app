## API Endpoint Documentation

All secured endpoints expect **JWT authentication**:

- **Header**
  - **Authorization**: `Bearer <your_jwt_token>`
  - **Content-Type**: `application/json`

---

## 1. Authentication

### 1.1 Login
- **Method**: `POST`
- **URL**: `/api/auth/login`
- **Headers**
  - `Content-Type: application/json`
- **Body**

```json
{
  "phoneNumber": "0900000000",
  "password": "123456"
}
```

- **Response**

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "jwt-token-here"
  }
}
```

---

### 1.2 Send Register OTP
- **Method**: `POST`
- **URL**: `/api/auth/register/send-otp`
- **Headers**
  - `Content-Type: application/json`
- **Body**

```json
{
  "phoneNumber": "0900000000"
}
```

- **Response**

```json
{
  "success": true,
  "message": "OTP sent successfully"
}
```

---

### 1.3 Verify Register OTP
- **Method**: `POST`
- **URL**: `/api/auth/register/verify-otp`
- **Headers**
  - `Content-Type: application/json`
- **Body**

```json
{
  "phoneNumber": "0900000000",
  "otp": "123456"
}
```

- **Response**

```json
{
  "success": true,
  "message": "OTP verified successfully"
}
```

---

### 1.4 Register
- **Method**: `POST`
- **URL**: `/api/auth/register`
- **Headers**
  - `Content-Type: application/json`
- **Body**

```json
{
  "phoneNumber": "0900000000",
  "password": "123456",
  "confirmPassword": "123456",
  "name": "John Doe",
  "gender": "MALE",
  "dob": "2000-01-01"
}
```

- **Response**

```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "phoneNumber": "0900000000"
  }
}
```

---

### 1.5 Send Forgot Password OTP
- **Method**: `POST`
- **URL**: `/api/auth/forgot-password/send-otp`
- **Headers**
  - `Content-Type: application/json`
- **Body**

```json
{
  "phoneNumber": "0900000000"
}
```

- **Response**

```json
{
  "success": true,
  "message": "OTP sent successfully"
}
```

---

### 1.6 Forgot Password
- **Method**: `POST`
- **URL**: `/api/auth/forgot-password`
- **Headers**
  - `Content-Type: application/json`
- **Body**

```json
{
  "phoneNumber": "0900000000",
  "otp": "123456",
  "newPassword": "new123456",
  "confirmPassword": "new123456"
}
```

- **Response**

```json
{
  "success": true,
  "message": "Password reset successfully"
}
```

---

## 2. Home (BFF)

### 2.1 Get Home Data
- **Method**: `GET`
- **URL**: `/api/v1/home`
- **Headers**
  - `Authorization: Bearer <token>`

- **Response**

```json
{
  "success": true,
  "message": "Home data loaded",
  "data": {
    "promotions": [
      {
        "id": 1,
        "title": "Summer Sale",
        "imageUrl": "https://...",
        "deepLinkUrl": "myapp://product/10",
        "expiryDate": "2026-07-21T12:00:00"
      }
    ],
    "feed": [
      {
        "id": 5,
        "authorName": "John",
        "authorAvatar": "https://...",
        "content": "New collection is here!",
        "mediaUrl": "https://...",
        "timestamp": "2026-07-21T10:00:00",
        "likesCount": 12,
        "likedByMe": false
      }
    ],
    "bestSelling": [
      {
        "id": 10,
        "name": "Sneakers",
        "price": 59.99,
        "discountPrice": 49.99,
        "rating": 4.5,
        "thumbnailUrl": "https://...",
        "stockStatus": "IN_STOCK"
      }
    ]
  }
}
```

---

## 3. Products

### 3.1 List Products
- **Method**: `GET`
- **URL**: `/api/v1/products`
- **Query Params** (optional)
  - `category`: string
  - `search`: string
  - `sort`: `priceAsc | priceDesc | rating`
- **Headers**
  - `Authorization: Bearer <token>`

- **Response (example)**

```json
{
  "success": true,
  "message": "Products loaded",
  "data": [
    {
      "id": 1,
      "name": "Sneakers",
      "price": 59.99,
      "description": "Comfortable running shoes",
      "rating": 4.5,
      "stockQuantity": 100,
      "images": [
        "https://...",
        "https://..."
      ],
      "attributes": {
        "size": "42",
        "color": "Black"
      },
      "reviews": [],
      "brandInfo": "Brand X"
    }
  ]
}
```

---

### 3.2 Get Product Detail
- **Method**: `GET`
- **URL**: `/api/v1/products/{id}`
- **Headers**
  - `Authorization: Bearer <token>`

- **Response (example)**

```json
{
  "success": true,
  "message": "Product loaded",
  "data": {
    "id": 1,
    "name": "Sneakers",
    "price": 59.99,
    "description": "Comfortable running shoes",
    "rating": 4.5,
    "stockQuantity": 100,
    "images": [
      "https://...",
      "https://..."
    ],
    "attributes": {
      "size": "42",
      "color": "Black"
    },
    "reviews": [],
    "brandInfo": "Brand X"
  }
}
```

---

## 4. Cart

### 4.1 Get My Cart
- **Method**: `GET`
- **URL**: `/api/v1/cart`
- **Headers**
  - `Authorization: Bearer <token>`

- **Response**

```json
{
  "success": true,
  "message": "Cart loaded",
  "data": {
    "cartTotal": 119.98,
    "deliveryFee": 5.00,
    "tax": 8.40,
    "items": [
      {
        "productId": 1,
        "name": "Sneakers",
        "quantity": 2,
        "unitPrice": 59.99,
        "subtotal": 119.98
      }
    ]
  }
}
```

---

### 4.2 Add/Update Cart Item
- **Method**: `POST`
- **URL**: `/api/v1/cart/items`
- **Headers**
  - `Authorization: Bearer <token>`
  - `Content-Type: application/json`
- **Body**

```json
{
  "productId": 1,
  "quantity": 2
}
```

- **Response**

```json
{
  "success": true,
  "message": "Cart updated",
  "data": {
    "cartTotal": 119.98,
    "deliveryFee": 5.00,
    "tax": 8.40,
    "items": [
      {
        "productId": 1,
        "name": "Sneakers",
        "quantity": 2,
        "unitPrice": 59.99,
        "subtotal": 119.98
      }
    ]
  }
}
```

---

### 4.3 Remove Cart Item
- **Method**: `DELETE`
- **URL**: `/api/v1/cart/items/{productId}`
- **Headers**
  - `Authorization: Bearer <token>`

- **Response**

```json
{
  "success": true,
  "message": "Cart updated",
  "data": {
    "cartTotal": 0,
    "deliveryFee": 0,
    "tax": 0,
    "items": []
  }
}
```

---

## 5. User Profile

### 5.1 Get My Profile
- **Method**: `GET`
- **URL**: `/api/v1/users/me`
- **Headers**
  - `Authorization: Bearer <token>`

- **Response (example)**

```json
{
  "success": true,
  "message": "Profile loaded",
  "data": {
    "id": 1,
    "phoneNumber": "0900000000",
    "name": "John Doe",
    "gender": "MALE",
    "dob": "2000-01-01",
    "email": "john@example.com",
    "defaultAddress": "123 Main St, City"
  }
}
```

---

## 6. Orders

### 6.1 Get My Orders
- **Method**: `GET`
- **URL**: `/api/v1/orders`
- **Headers**
  - `Authorization: Bearer <token>`

- **Response**

```json
{
  "success": true,
  "message": "Orders loaded",
  "data": [
    {
      "orderId": 1001,
      "status": "SHIPPED",
      "orderDate": "2026-07-20T09:30:00",
      "totalAmount": 129.99,
      "shippingAddress": "123 Main St, City"
    }
  ]
}
```

---

### 6.2 Get Order Detail
- **Method**: `GET`
- **URL**: `/api/v1/orders/{orderId}`
- **Headers**
  - `Authorization: Bearer <token>`

- **Response (example)**

```json
{
  "success": true,
  "message": "Order loaded",
  "data": {
    "orderId": 1001,
    "status": "SHIPPED",
    "orderDate": "2026-07-20T09:30:00",
    "totalAmount": 129.99,
    "shippingAddress": "123 Main St, City",
    "items": [
      {
        "productId": 1,
        "name": "Sneakers",
        "quantity": 2,
        "unitPrice": 59.99,
        "subtotal": 119.98
      }
    ],
    "trackingNumber": "TRK123456",
    "carrier": "DHL"
  }
}
```


