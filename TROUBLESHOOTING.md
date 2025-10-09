# Troubleshooting Guide for E-Commerce Application

## Common Issues and Solutions

### 1. JAVA_HOME Not Set Error
**Error**: `JAVA_HOME not found in your environment`

**Solutions**:
- **Option 1**: Run `run-app.bat` - This script will automatically find and set JAVA_HOME
- **Option 2**: Set JAVA_HOME manually:
  ```cmd
  set JAVA_HOME=C:\Program Files\Java\jdk-21
  set PATH=%JAVA_HOME%\bin;%PATH%
  ```
- **Option 3**: Install Java JDK if not installed

### 2. Razorpay Import Errors
**Error**: `Cannot resolve RazorpayOrder` or similar import errors

**Solution**: The imports have been fixed. If you still see errors:
1. Clean and rebuild the project
2. Refresh your IDE
3. Check if Maven dependencies are downloaded

### 3. Maven Build Errors
**Error**: Various compilation errors

**Solutions**:
```cmd
# Clean and rebuild
mvn clean compile

# Download dependencies
mvn dependency:resolve

# Full clean build
mvn clean package
```

### 4. Database Connection Issues
**Error**: MySQL connection failed

**Solutions**:
1. Ensure MySQL is running
2. Create database: `ecommerce_db`
3. Update credentials in `application.properties`
4. Check MySQL port (default: 3306)

### 5. Razorpay Configuration
**Error**: Payment initialization failed

**Solutions**:
1. Get Razorpay API keys from dashboard
2. Update in `application.properties`:
   ```properties
   razorpay.key.id=your_key_id
   razorpay.key.secret=your_secret_key
   ```

## Quick Start Commands

### Using Maven Wrapper (Recommended)
```cmd
# Build and run
./mvnw spring-boot:run

# Clean build
./mvnw clean package

# Run tests
./mvnw test
```

### Using Installed Maven
```cmd
# Build and run
mvn spring-boot:run

# Clean build
mvn clean package

# Run tests
mvn test
```

### Using Batch File (Windows)
```cmd
# Automatic setup and run
run-app.bat
```

## IDE Setup

### IntelliJ IDEA
1. Open project as Maven project
2. Wait for dependencies to download
3. Set Project SDK to Java 17+
4. Refresh Maven project if needed

### Eclipse
1. Import as Maven project
2. Right-click project → Maven → Reload Projects
3. Set Java Build Path to Java 17+

### VS Code
1. Install Java Extension Pack
2. Open project folder
3. Wait for Maven dependencies to load

## Default Credentials
- **Admin**: username=`admin`, password=`admin123`
- **Database**: Create `ecommerce_db` in MySQL
- **Application**: http://localhost:8080

## Support
If you encounter issues not covered here:
1. Check the console output for specific error messages
2. Verify all prerequisites are installed
3. Ensure all configuration files are properly set up
