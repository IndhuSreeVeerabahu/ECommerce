# E-Commerce Web Application

A comprehensive, full-stack E-Commerce application built with Spring Boot, featuring user management, product catalog, shopping cart, order processing, and Razorpay payment integration.

## 🚀 Features

### 1. User Module
- **User Registration & Login**: Secure authentication with Spring Security
- **Role-based Access Control**: Customer and Admin roles with different permissions
- **Profile Management**: Users can update their personal information
- **Password Security**: BCrypt encryption for secure password storage

### 2. Product Module
- **Product Catalog**: Browse products with categories, search, and filtering
- **Admin Product Management**: Add, edit, delete products (Admin only)
- **Product Categories**: Organized product categorization
- **Stock Management**: Track product availability and stock levels
- **Image Support**: Product images with placeholder fallbacks

### 3. Cart Module
- **Shopping Cart**: Add/remove products with quantity management
- **Session Persistence**: Cart persists during user session
- **Real-time Updates**: Dynamic cart total calculation
- **Stock Validation**: Prevents adding more items than available

### 4. Order Module
- **Checkout Process**: Complete order creation from cart
- **Order Tracking**: View order history and status
- **Order Management**: Admin can update order status
- **Address Management**: Separate shipping and billing addresses

### 5. Payment Module
- **Razorpay Integration**: Secure payment processing
- **Test Payment System**: College prototype payment simulation
- **Payment Verification**: Server-side payment validation
- **Order Status Updates**: Automatic status updates after payment
- **Payment Security**: HMAC signature verification
- **Dual Mode**: Real payments + Test payments for demonstrations

## 🛠️ Technology Stack

### Backend
- **Spring Boot 3.5.5**: Main framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Data persistence
- **MySQL**: Database
- **Thymeleaf**: Server-side templating
- **Razorpay Java SDK**: Payment integration
- **Lombok**: Code generation
- **Validation**: Bean validation

### Frontend
- **Bootstrap 5**: Responsive UI framework
- **Font Awesome**: Icons
- **Custom CSS**: Enhanced styling
- **JavaScript**: Interactive features
- **Thymeleaf**: Template engine

### Development Tools
- **Maven**: Build tool
- **Spring Boot DevTools**: Development utilities
- **Swagger/OpenAPI**: API documentation

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd E_Commerce
```

### 2. Database Setup
1. Create a MySQL database named `ECommerceDB`
2. Update database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ECommerceDB?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Payment Configuration
**For Student Projects (Recommended):**
The application comes with Razorpay Test Mode integration that works out of the box. No additional setup required.

**Features:**
- ✅ **No Real Money**: Uses test card details
- ✅ **Real Integration**: Actual Razorpay SDK
- ✅ **Safe Testing**: Perfect for learning
- ✅ **Complete Flow**: End-to-end payment processing

**Test Card Details:**
```
Card Number: 4111 1111 1111 1111
Expiry: 12/25
CVV: 123
Name: Test User
```

**Note:** This uses Razorpay Test Mode - perfect for student projects and learning!

### 4. Run the Application
```bash
# Using Maven
mvn spring-boot:run

# Or using the provided batch file (Windows)
run-app.bat

# Or using the Maven wrapper
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`

## 👥 Default Users

### Admin User
- **Username**: admin
- **Password**: admin123
- **Role**: ADMIN

### Customer Registration
- Register new customers through the registration page
- Default role: CUSTOMER

## 📱 Application Structure

```
src/
├── main/
│   ├── java/com/example/E_Commerce/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── model/          # Entity models
│   │   ├── repository/     # Data access layer
│   │   ├── service/        # Business logic
│   │   └── ECommerceApplication.java
│   └── resources/
│       ├── static/         # CSS, JS, images
│       ├── templates/      # Thymeleaf templates
│       └── application.properties
└── test/                   # Test files
```

## 🔧 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/ECommerceDB
spring.datasource.username=root
spring.datasource.password=your_password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Security
spring.security.user.name=admin
spring.security.user.password=admin123
spring.security.user.roles=ADMIN

# Razorpay
razorpay.key.id=your_key_id
razorpay.key.secret=your_secret_key

# Server
server.port=8080
```

## 🎨 Frontend Features

### Responsive Design
- Mobile-first approach
- Bootstrap 5 grid system
- Custom CSS enhancements
- Smooth animations and transitions

### User Experience
- Loading states for forms
- Auto-hiding alerts
- Smooth scrolling
- Back-to-top button
- Cart item count animations

### Interactive Elements
- Product search and filtering
- Dynamic cart updates
- Form validation
- Confirmation dialogs

## 💳 Payment System

### Razorpay Test Mode (Student Project)
- **No Setup Required**: Works out of the box
- **Real Integration**: Actual Razorpay SDK
- **No Real Money**: Uses test card details
- **Safe Testing**: Perfect for learning and demonstrations

### Payment Methods Available
- **Credit/Debit Cards**: Visa, Mastercard, American Express
- **UPI**: Test UPI IDs
- **Net Banking**: Test bank accounts
- **Wallets**: Test wallet payments

### How to Test Payments
1. Complete checkout process
2. Click "Pay ₹X.XX" for real Razorpay test mode
3. Use test card details: `4111 1111 1111 1111`
4. Or click "Test Payment (Student Project)" for simulation
5. System updates order status accordingly

## 🔐 Security Features

- **Authentication**: Username/email and password login
- **Authorization**: Role-based access control
- **Password Encryption**: BCrypt hashing
- **CSRF Protection**: Enabled by default
- **Session Management**: Secure session handling
- **Payment Security**: Razorpay signature verification

## 📊 Database Schema

### Core Tables
- `users`: User accounts and profiles
- `products`: Product catalog
- `carts`: Shopping carts
- `cart_items`: Cart contents
- `orders`: Order information
- `order_items`: Order line items

### Relationships
- User → Cart (One-to-One)
- Cart → CartItems (One-to-Many)
- User → Orders (One-to-Many)
- Order → OrderItems (One-to-Many)
- Product → CartItems (One-to-Many)
- Product → OrderItems (One-to-Many)

## 🧪 Testing

### Running Tests
```bash
mvn test
```

### Test Coverage
- Unit tests for services
- Integration tests for repositories
- Security tests for authentication

## 🚀 Deployment

### Production Configuration
1. Update `application.properties` for production:
```properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
logging.level.com.example.E_Commerce=INFO
```

2. Build the application:
```bash
mvn clean package
```

3. Run the JAR file:
```bash
java -jar target/E_Commerce-0.0.1-SNAPSHOT.jar
```

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/E_Commerce-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📈 Performance Optimizations

- **Lazy Loading**: JPA lazy loading for relationships
- **Pagination**: Product listing with pagination
- **Caching**: Thymeleaf template caching
- **Database Indexing**: Optimized database queries
- **Static Resources**: CDN for Bootstrap and Font Awesome

## 🔧 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Check MySQL service is running
   - Verify database credentials
   - Ensure database exists

2. **Razorpay Payment Issues**
   - Verify API keys are correct
   - Check network connectivity
   - Ensure test mode is enabled for development

3. **Port Already in Use**
   - Change port in `application.properties`
   - Kill existing process on port 8080

### Logs
Check application logs for detailed error information:
```bash
tail -f logs/application.log
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Bootstrap team for the UI framework
- Razorpay for payment integration
- Font Awesome for icons

## 📞 Support

For support and questions:
- Create an issue in the repository
- Check the troubleshooting section
- Review the application logs

---

**Happy Shopping! 🛒**