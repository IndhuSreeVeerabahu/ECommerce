# Production Setup Guide

## 🚀 Production Deployment Checklist

### 1. Security Configuration

#### Update Application Properties
Create a production-specific `application-prod.properties`:

```properties
# Production Database Configuration
spring.datasource.url=jdbc:mysql://your-production-db-host:3306/ECommerceDB?useSSL=true&serverTimezone=UTC
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# JPA Configuration for Production
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=false

# Security Configuration
spring.security.user.name=${ADMIN_USERNAME}
spring.security.user.password=${ADMIN_PASSWORD}
spring.security.user.roles=ADMIN

# Real Razorpay Configuration
razorpay.key.id=${RAZORPAY_KEY_ID}
razorpay.key.secret=${RAZORPAY_KEY_SECRET}

# Server Configuration
server.port=8080
server.servlet.context-path=/
server.compression.enabled=true
server.compression.mime-types=text/html,text/xml,text/plain,text/css,text/javascript,application/javascript,application/json

# Logging Configuration
logging.level.com.example.E_Commerce=INFO
logging.level.org.springframework.security=WARN
logging.level.org.hibernate.SQL=WARN
logging.file.name=/var/log/ecommerce/application.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

# Thymeleaf Configuration
spring.thymeleaf.cache=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# Mail Configuration (for notifications)
spring.mail.host=${MAIL_HOST}
spring.mail.port=${MAIL_PORT}
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

#### Environment Variables
Set these environment variables on your production server:

```bash
# Database
export DB_USERNAME=your_production_db_user
export DB_PASSWORD=your_secure_db_password

# Admin Account
export ADMIN_USERNAME=admin
export ADMIN_PASSWORD=your_secure_admin_password

# Razorpay (Real Keys)
export RAZORPAY_KEY_ID=rzp_live_your_live_key_id
export RAZORPAY_KEY_SECRET=your_live_secret_key

# Mail Service
export MAIL_HOST=smtp.gmail.com
export MAIL_PORT=587
export MAIL_USERNAME=your_email@gmail.com
export MAIL_PASSWORD=your_app_password
```

### 2. Database Setup

#### Production Database Configuration
```sql
-- Create production database
CREATE DATABASE ECommerceDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Create production user
CREATE USER 'ecommerce_user'@'%' IDENTIFIED BY 'secure_password_here';
GRANT ALL PRIVILEGES ON ECommerceDB.* TO 'ecommerce_user'@'%';
FLUSH PRIVILEGES;

-- Create indexes for performance
USE ECommerceDB;

-- User table indexes
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);

-- Product table indexes
CREATE INDEX idx_products_category ON products(category);
CREATE INDEX idx_products_active ON products(active);
CREATE INDEX idx_products_price ON products(price);

-- Order table indexes
CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_created_at ON orders(created_at);

-- Cart table indexes
CREATE INDEX idx_carts_user_id ON carts(user_id);
```

### 3. Razorpay Production Setup

#### Get Live API Keys
1. **Upgrade Razorpay Account**: Convert test account to live
2. **Complete KYC**: Submit required documents
3. **Get Live Keys**: 
   - Go to Razorpay Dashboard → Settings → API Keys
   - Copy Live Key ID and Live Secret Key
4. **Update Configuration**: Use live keys in production

#### Webhook Configuration
Set up webhooks for payment notifications:

```java
// Add to PaymentService.java
@PostMapping("/webhook/razorpay")
public ResponseEntity<String> handleRazorpayWebhook(@RequestBody String payload, 
                                                   @RequestHeader("X-Razorpay-Signature") String signature) {
    try {
        // Verify webhook signature
        String expectedSignature = generateSignature(payload, razorpayKeySecret);
        if (!expectedSignature.equals(signature)) {
            return ResponseEntity.status(400).body("Invalid signature");
        }
        
        // Process webhook payload
        JSONObject webhookData = new JSONObject(payload);
        String event = webhookData.getString("event");
        
        if ("payment.captured".equals(event)) {
            // Handle successful payment
            JSONObject payment = webhookData.getJSONObject("payload").getJSONObject("payment");
            String paymentId = payment.getString("id");
            String orderId = payment.getString("order_id");
            
            // Update order status
            Order order = orderService.findByRazorpayOrderId(orderId);
            if (order != null) {
                orderService.updateRazorpayPaymentId(order.getId(), paymentId);
                orderService.updatePaymentStatus(order.getId(), PaymentStatus.COMPLETED);
            }
        }
        
        return ResponseEntity.ok("Webhook processed");
    } catch (Exception e) {
        logger.error("Webhook processing failed", e);
        return ResponseEntity.status(500).body("Webhook processing failed");
    }
}
```

### 4. Security Enhancements

#### Update SecurityConfig.java
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/home", "/products/**", "/register", "/login", 
                               "/css/**", "/js/**", "/images/**", "/api/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/cart/**", "/checkout/**").hasRole("CUSTOMER")
                .requestMatchers("/orders/**", "/profile/**").hasAnyRole("CUSTOMER", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry())
            )
            .csrf(csrf -> csrf.disable()) // Only for development
            .authenticationProvider(authenticationProvider());

        // Enable HTTPS in production
        if ("prod".equals(activeProfile)) {
            http.requiresChannel(channel -> channel.anyRequest().requiresSecure());
        }

        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
```

### 5. Performance Optimizations

#### Database Connection Pool
```properties
# Add to application-prod.properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.max-lifetime=1200000
spring.datasource.hikari.connection-timeout=20000
```

#### Caching Configuration
```java
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES));
        return cacheManager;
    }
}

// Add caching to services
@Service
public class ProductService {
    
    @Cacheable("products")
    public List<Product> getAllProducts() {
        return productRepository.findByActiveTrue();
    }
    
    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(Product product) {
        // Update logic
    }
}
```

### 6. Monitoring and Logging

#### Application Monitoring
```java
// Add to pom.xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

```properties
# Add to application-prod.properties
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=when-authorized
management.metrics.export.prometheus.enabled=true
```

### 7. Deployment Options

#### Option 1: Traditional Server Deployment
```bash
# Build the application
mvn clean package -Pprod

# Deploy to server
scp target/E_Commerce-0.0.1-SNAPSHOT.jar user@server:/opt/ecommerce/

# Run on server
java -jar -Dspring.profiles.active=prod E_Commerce-0.0.1-SNAPSHOT.jar
```

#### Option 2: Docker Deployment
```dockerfile
# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/E_Commerce-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]
```

```yaml
# docker-compose.yml
version: '3.8'
services:
  ecommerce:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - DB_USERNAME=ecommerce_user
      - DB_PASSWORD=secure_password
      - RAZORPAY_KEY_ID=rzp_live_your_key
      - RAZORPAY_KEY_SECRET=your_secret
    depends_on:
      - mysql

  mysql:
    image: mysql:8.0
    environment:
      - MYSQL_ROOT_PASSWORD=root_password
      - MYSQL_DATABASE=ECommerceDB
      - MYSQL_USER=ecommerce_user
      - MYSQL_PASSWORD=secure_password
    volumes:
      - mysql_data:/var/lib/mysql
    ports:
      - "3306:3306"

volumes:
  mysql_data:
```

#### Option 3: Cloud Deployment (AWS/Azure/GCP)
```yaml
# For AWS Elastic Beanstalk
# .ebextensions/01-environment.config
option_settings:
  aws:elasticbeanstalk:application:environment:
    SPRING_PROFILES_ACTIVE: prod
    DB_USERNAME: ecommerce_user
    DB_PASSWORD: secure_password
    RAZORPAY_KEY_ID: rzp_live_your_key
    RAZORPAY_KEY_SECRET: your_secret
```

### 8. SSL/HTTPS Configuration

#### Using Let's Encrypt (Free SSL)
```bash
# Install Certbot
sudo apt-get install certbot

# Get SSL certificate
sudo certbot certonly --standalone -d yourdomain.com

# Configure Spring Boot for HTTPS
```

```properties
# Add to application-prod.properties
server.ssl.key-store=/etc/letsencrypt/live/yourdomain.com/keystore.p12
server.ssl.key-store-password=your_password
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=tomcat
```

### 9. Backup Strategy

#### Database Backup
```bash
#!/bin/bash
# backup.sh
DATE=$(date +%Y%m%d_%H%M%S)
mysqldump -u ecommerce_user -p ECommerceDB > /backup/ecommerce_$DATE.sql
find /backup -name "ecommerce_*.sql" -mtime +7 -delete
```

#### Application Backup
```bash
#!/bin/bash
# app-backup.sh
DATE=$(date +%Y%m%d_%H%M%S)
tar -czf /backup/app_$DATE.tar.gz /opt/ecommerce/
find /backup -name "app_*.tar.gz" -mtime +7 -delete
```

### 10. Production Checklist

#### Pre-Deployment
- [ ] Update all hardcoded values to environment variables
- [ ] Remove test payment system
- [ ] Configure real Razorpay live keys
- [ ] Set up production database
- [ ] Configure SSL/HTTPS
- [ ] Set up monitoring and logging
- [ ] Configure backup strategy
- [ ] Test all functionality
- [ ] Security audit
- [ ] Performance testing

#### Post-Deployment
- [ ] Monitor application logs
- [ ] Check database performance
- [ ] Verify payment processing
- [ ] Test all user flows
- [ ] Monitor server resources
- [ ] Set up alerts
- [ ] Document deployment process

### 11. Environment-Specific Configurations

#### Development
```properties
# application-dev.properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
logging.level.com.example.E_Commerce=DEBUG
```

#### Staging
```properties
# application-staging.properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
logging.level.com.example.E_Commerce=INFO
```

#### Production
```properties
# application-prod.properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
logging.level.com.example.E_Commerce=WARN
```

### 12. Quick Production Setup Commands

```bash
# 1. Build for production
mvn clean package -Pprod

# 2. Set environment variables
export SPRING_PROFILES_ACTIVE=prod
export DB_USERNAME=your_db_user
export DB_PASSWORD=your_db_password
export RAZORPAY_KEY_ID=rzp_live_your_key
export RAZORPAY_KEY_SECRET=your_secret

# 3. Run application
java -jar target/E_Commerce-0.0.1-SNAPSHOT.jar

# 4. Or with Docker
docker-compose up -d
```

This production setup ensures your E-Commerce application is secure, scalable, and ready for real-world use!
