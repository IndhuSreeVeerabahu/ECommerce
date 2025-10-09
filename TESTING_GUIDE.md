# 🧪 E-Commerce Application Testing Guide

This guide covers comprehensive testing strategies for the E-Commerce application, including unit tests, integration tests, and end-to-end tests.

## 📋 Table of Contents

1. [Testing Overview](#testing-overview)
2. [Test Types](#test-types)
3. [Running Tests](#running-tests)
4. [Test Coverage](#test-coverage)
5. [Test Structure](#test-structure)
6. [Best Practices](#best-practices)

---

## 🎯 Testing Overview

The E-Commerce application uses a comprehensive testing strategy with:

- **JUnit 5** - Modern testing framework
- **Mockito** - Mocking framework for unit tests
- **Spring Boot Test** - Integration testing support
- **TestContainers** - Container-based testing
- **JaCoCo** - Code coverage reporting
- **H2 Database** - In-memory database for tests

---

## 🧪 Test Types

### 1. Unit Tests

**Purpose**: Test individual components in isolation

**Location**: `src/test/java/com/example/E_Commerce/service/`, `src/test/java/com/example/E_Commerce/controller/`

**Examples**:
- `AdminControllerTest.java` - Tests admin controller endpoints
- `OrderServiceTest.java` - Tests order business logic
- `ProductServiceTest.java` - Tests product management

**Key Features**:
- ✅ Mocked dependencies using Mockito
- ✅ Isolated testing of business logic
- ✅ Fast execution
- ✅ Comprehensive edge case coverage

### 2. Integration Tests

**Purpose**: Test component interactions with database

**Location**: `src/test/java/com/example/E_Commerce/integration/`

**Examples**:
- `OrderWorkflowIntegrationTest.java` - Tests complete order workflows

**Key Features**:
- ✅ Real database interactions (H2 in-memory)
- ✅ Transaction rollback after each test
- ✅ End-to-end workflow testing
- ✅ Data persistence validation

### 3. End-to-End Tests

**Purpose**: Test complete user workflows through the application

**Location**: `src/test/java/com/example/E_Commerce/e2e/`

**Examples**:
- `ECommerceE2ETest.java` - Tests complete user journeys

**Key Features**:
- ✅ Full application context
- ✅ HTTP request/response testing
- ✅ Security testing
- ✅ UI workflow validation

---

## 🚀 Running Tests

### Prerequisites

1. **Java 17+** installed
2. **Maven** installed
3. **Docker** (for TestContainers - optional)

### Command Line Options

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test Types
```bash
# Unit tests only
mvn test -Dtest="*Test"

# Integration tests only
mvn test -Dtest="*IntegrationTest"

# End-to-end tests only
mvn test -Dtest="*E2ETest"
```

#### Run Tests with Coverage
```bash
mvn test jacoco:report
```

#### Run Tests in Specific Package
```bash
mvn test -Dtest="com.example.E_Commerce.service.*Test"
```

#### Run Individual Test Class
```bash
mvn test -Dtest="AdminControllerTest"
```

#### Run Individual Test Method
```bash
mvn test -Dtest="AdminControllerTest#testDashboard"
```

### Using the Test Runner Script

For Windows users, use the provided batch script:

```bash
run-tests.bat
```

This script will:
1. Run all unit tests
2. Run integration tests
3. Run end-to-end tests
4. Generate coverage reports

---

## 📊 Test Coverage

### Coverage Reports

After running tests with coverage, reports are generated in:
- **HTML Report**: `target/site/jacoco/index.html`
- **XML Report**: `target/site/jacoco/jacoco.xml`
- **CSV Report**: `target/site/jacoco/jacoco.csv`

### Coverage Targets

| Component | Target Coverage |
|-----------|----------------|
| Controllers | 90%+ |
| Services | 95%+ |
| Repositories | 85%+ |
| Overall | 90%+ |

### Viewing Coverage

1. Run tests with coverage: `mvn test jacoco:report`
2. Open `target/site/jacoco/index.html` in your browser
3. Navigate through packages to see detailed coverage

---

## 🏗️ Test Structure

### Test Organization

```
src/test/java/com/example/E_Commerce/
├── controller/           # Controller unit tests
│   ├── AdminControllerTest.java
│   ├── OrderControllerTest.java
│   └── ProductControllerTest.java
├── service/             # Service unit tests
│   ├── OrderServiceTest.java
│   ├── ProductServiceTest.java
│   └── UserServiceTest.java
├── repository/          # Repository tests
│   ├── OrderRepositoryTest.java
│   └── ProductRepositoryTest.java
├── integration/         # Integration tests
│   ├── OrderWorkflowIntegrationTest.java
│   └── UserWorkflowIntegrationTest.java
├── e2e/                # End-to-end tests
│   ├── ECommerceE2ETest.java
│   └── AdminWorkflowE2ETest.java
└── config/             # Test configuration
    └── TestConfig.java
```

### Test Resources

```
src/test/resources/
├── application-test.properties  # Test configuration
├── data/                       # Test data files
│   ├── test-products.json
│   └── test-users.json
└── sql/                        # Test SQL scripts
    ├── schema.sql
    └── test-data.sql
```

---

## 🎯 Test Scenarios Covered

### 1. User Management
- ✅ User registration
- ✅ User login/logout
- ✅ Profile management
- ✅ Role-based access control

### 2. Product Management
- ✅ Product CRUD operations
- ✅ Product search and filtering
- ✅ Stock management
- ✅ Category management

### 3. Order Management
- ✅ Order creation
- ✅ Order status updates
- ✅ Order cancellation
- ✅ Order history

### 4. Cart Management
- ✅ Add/remove items
- ✅ Update quantities
- ✅ Cart persistence
- ✅ Cart clearing

### 5. Payment Processing
- ✅ Payment initiation
- ✅ Payment status updates
- ✅ Payment failure handling
- ✅ Refund processing

### 6. Admin Operations
- ✅ Dashboard functionality
- ✅ User management
- ✅ Product management
- ✅ Order management
- ✅ Report generation
- ✅ Export functionality

### 7. Security Testing
- ✅ Authentication
- ✅ Authorization
- ✅ CSRF protection
- ✅ Input validation

---

## 🔧 Test Configuration

### Test Profiles

The application uses different profiles for testing:

- **`test`** - Default test profile with H2 database
- **`integration-test`** - Integration test profile
- **`e2e-test`** - End-to-end test profile

### Database Configuration

Tests use H2 in-memory database for fast execution:

```properties
# Test database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop
```

### Security Configuration

Test security is configured to allow:
- Mock users with different roles
- CSRF disabled for testing
- Simplified authentication

---

## 📝 Best Practices

### 1. Test Naming
- Use descriptive test method names
- Follow the pattern: `test[MethodName]_[Scenario]_[ExpectedResult]`
- Example: `testCreateOrder_WithValidData_ShouldReturnOrder()`

### 2. Test Structure
- Follow AAA pattern: Arrange, Act, Assert
- Keep tests focused and simple
- One assertion per test when possible

### 3. Mocking
- Mock external dependencies
- Use `@Mock` and `@InjectMocks` annotations
- Verify interactions when necessary

### 4. Test Data
- Use builders for complex test data
- Create reusable test fixtures
- Keep test data minimal and focused

### 5. Assertions
- Use specific assertions
- Include meaningful error messages
- Test both positive and negative scenarios

### 6. Test Isolation
- Each test should be independent
- Use `@Transactional` for database tests
- Clean up after each test

---

## 🐛 Troubleshooting

### Common Issues

#### 1. Test Database Issues
```bash
# Clear test database
mvn clean test
```

#### 2. Port Conflicts
```bash
# Use different port for tests
mvn test -Dserver.port=8081
```

#### 3. Memory Issues
```bash
# Increase heap size
mvn test -Dtest.jvm.args="-Xmx2g"
```

#### 4. TestContainers Issues
```bash
# Skip TestContainers tests
mvn test -Dtestcontainers.skip=true
```

### Debug Mode

Run tests in debug mode:
```bash
mvn test -Dmaven.surefire.debug
```

---

## 📈 Continuous Integration

### GitHub Actions Example

```yaml
name: Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Run tests
        run: mvn test jacoco:report
      - name: Upload coverage
        uses: codecov/codecov-action@v1
```

---

## 🎉 Conclusion

This comprehensive testing strategy ensures:

- ✅ **High Code Quality** - 90%+ test coverage
- ✅ **Reliable Deployments** - All scenarios tested
- ✅ **Fast Feedback** - Quick test execution
- ✅ **Maintainable Code** - Well-structured tests
- ✅ **Confident Refactoring** - Tests catch regressions

The testing framework provides a solid foundation for maintaining and extending the E-Commerce application with confidence.

---

## 📚 Additional Resources

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [TestContainers Documentation](https://www.testcontainers.org/)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
