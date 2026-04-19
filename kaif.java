# 📚 COMPLETE LEAVE MANAGEMENT SYSTEM PROJECT EXPLANATION
## Interview Guide - Everything You Need to Know

---

## TABLE OF CONTENTS
1. [Project Overview](#project-overview)
2. [Technology Stack & Why](#technology-stack--why)
3. [Project Structure](#project-structure)
4. [Complete Annotations Explanation](#complete-annotations-explanation)
5. [Entities & Database](#entities--database)
6. [Security Implementation](#security-implementation)
7. [Controllers & API Endpoints](#controllers--api-endpoints)
8. [Services & Business Logic](#services--business-logic)
9. [Repositories & Database Access](#repositories--database-access)
10. [Exception Handling](#exception-handling)
11. [Complete Request/Response Flow](#complete-request-response-flow)
12. [Design Patterns](#design-patterns)
13. [Interview Q&A](#interview-qa)

---

# PROJECT OVERVIEW

## What is this Project?

This is a **Spring Boot REST API** for managing employee leave requests in an organization.

**Real-world scenario:**
- Employees submit leave requests for vacation, sick leave, etc.
- Managers receive notifications and approve/reject requests
- System tracks leave balance and maintains audit logs
- Everything is secure with password encryption and JWT tokens

**Key Features:**
✅ User authentication & authorization
✅ Leave request management (create, view, approve, reject)
✅ Leave balance tracking
✅ Audit logging (complete trail of all actions)
✅ Role-based access control (RBAC)
✅ Pagination & filtering support

---

# TECHNOLOGY STACK & WHY

## Why These Technologies?

| Technology | Version | Why Used? |
|-----------|---------|-----------|
| **Java** | 17 | Mature, enterprise-grade, strong typing, better performance |
| **Spring Boot** | 3.2.2 | Rapid development, auto-configuration, huge ecosystem |
| **Spring Security** | 3.2.2 | Industry standard for authentication & authorization |
| **Spring Data JPA** | 3.2.2 | Reduces boilerplate, automatic query generation |
| **Hibernate** | 6.x | ORM (Object-Relational Mapping) - write Java code instead of SQL |
| **MySQL** | 8.0 | Reliable relational database, widely used |
| **JWT (JJWT)** | 0.12.3 | Stateless authentication, perfect for REST APIs |
| **Maven** | 3.6+ | Build automation, dependency management |
| **Lombok** | 3.1.1 | Reduces boilerplate (getters, setters, constructors) |
| **BCrypt** | Built-in | One-way password hashing, industry standard |

---

# PROJECT STRUCTURE

```
leave-management-backend/
├── pom.xml                    # Maven configuration (dependencies)
├── README.md                  # Project documentation
├── src/
│   ├── main/
│   │   ├── java/com/leavemgmt/
│   │   │   ├── LeaveManagementApplication.java    # Entry point (@SpringBootApplication)
│   │   │   ├── entity/                            # Database models
│   │   │   │   ├── User.java
│   │   │   │   ├── LeaveRequest.java
│   │   │   │   ├── LeaveBalance.java
│   │   │   │   ├── AuditLog.java
│   │   │   │   └── enums/UserRole.java
│   │   │   ├── dto/                              # Data Transfer Objects
│   │   │   │   ├── ApiResponse.java
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── LoginResponse.java
│   │   │   │   ├── SignupRequest.java
│   │   │   │   ├── UserProfile.java
│   │   │   │   └── LeaveRequestDto.java
│   │   │   ├── controller/                       # API endpoints
│   │   │   │   ├── AuthController.java           # /api/auth
│   │   │   │   ├── LeaveRequestController.java   # /api/leave-requests
│   │   │   │   └── UserController.java           # /api/users
│   │   │   ├── service/                          # Business logic
│   │   │   │   ├── interfaces/
│   │   │   │   │   ├── IAuthService.java
│   │   │   │   │   ├── ILeaveRequestService.java
│   │   │   │   │   └── IUserService.java
│   │   │   │   └── impl/
│   │   │   │       ├── AuthServiceImpl.java
│   │   │   │       ├── LeaveRequestServiceImpl.java
│   │   │   │       └── UserServiceImpl.java
│   │   │   ├── repository/                       # Database access
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── LeaveRequestRepository.java
│   │   │   │   └── LeaveBalanceRepository.java
│   │   │   ├── security/                         # Security components
│   │   │   │   ├── JwtTokenProvider.java         # Create/validate JWT
│   │   │   │   ├── JwtAuthenticationFilter.java  # Intercept requests
│   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   └── SecurityConstants.java
│   │   │   ├── config/                           # Configuration classes
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── JpaConfig.java
│   │   │   │   └── CorsConfig.java
│   │   │   ├── exception/                        # Exception classes
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── CustomException.java
│   │   │   │   ├── UserNotFoundException.java
│   │   │   │   ├── DuplicateEmailException.java
│   │   │   │   └── UnauthorizedException.java
│   │   │   └── aspect/                           # AOP (cross-cutting concerns)
│   │   └── resources/
│   │       ├── application.properties            # Configuration
│   │       └── data-mysql.sql                    # Initial data
│   └── test/                                     # Unit tests
├── target/                                       # Compiled code (auto-generated)
└── .gitignore
```

**Layered Architecture Diagram:**
```
┌─────────────────────────────────────────────────┐
│  Frontend (React/Angular/Vue)                   │
│  Makes HTTP Requests                            │
└──────────────────┬──────────────────────────────┘
                   │ HTTP/JSON
┌──────────────────▼──────────────────────────────┐
│  CONTROLLER LAYER                               │
│  @RestController - Receives requests            │
│  @RequestMapping - Routes to endpoints          │
│  Validation + Response formatting               │
└──────────────────┬──────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────┐
│  SERVICE LAYER                                  │
│  @Service - Business logic                      │
│  Data validation, calculations, workflows       │
│  Orchestrates repositories                      │
└──────────────────┬──────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────┐
│  REPOSITORY LAYER                               │
│  @Repository - Data access                      │
│  Queries database, saves/updates/deletes        │
│  Spring Data JPA handles SQL generation         │
└──────────────────┬──────────────────────────────┘
                   │ JDBC Queries
┌──────────────────▼──────────────────────────────┐
│  DATABASE LAYER                                 │
│  MySQL 8.0                                      │
│  Stores data in tables                          │
└─────────────────────────────────────────────────┘
```

---

# 🎯 UNDERSTANDING INTERFACES & KEY CONCEPTS

This section explains the most important concepts beginners struggle with, especially **WHY we use interfaces**.

## What is an Interface?

**Simple Definition:**
An interface is a **contract** - it defines what methods a class MUST have, but NOT how they work.

**Real-World Analogy:**
Think of it like a restaurant chef job description:
- **Job Description (Interface):** "A chef must know how to cook, season food, and manage kitchen"
- **Actual Chef (Implementation):** Different chefs implement these differently. One makes Indian food, another makes Chinese food.
- Both follow the contract (cook, season, manage), but each does it their own way.

**Code Example:**
```java
// INTERFACE - Contract/Blueprint
public interface Chef {
    void cook();           // Must have this method
    void seasonFood();     // Must have this method
    void manageKitchen();  // Must have this method
    // HOW? The interface doesn't say!
}

// IMPLEMENTATION 1 - Indian Chef
public class IndianChef implements Chef {
    @Override
    public void cook() {
        // Indian cooking style
    }
    
    @Override
    public void seasonFood() {
        // Use turmeric, ginger, garlic
    }
    
    @Override
    public void manageKitchen() {
        // Indian kitchen setup
    }
}

// IMPLEMENTATION 2 - Chinese Chef  
public class ChineseChef implements Chef {
    @Override
    public void cook() {
        // Chinese cooking style (wok, stir fry)
    }
    
    @Override
    public void seasonFood() {
        // Use soy sauce, ginger, garlic
    }
    
    @Override
    public void manageKitchen() {
        // Chinese kitchen setup
    }
}

// RESTAURANT DOESN'T CARE WHICH CHEF!
public class Restaurant {
    private Chef chef;  // Can be IndianChef or ChineseChef
    
    public Restaurant(Chef chef) {
        this.chef = chef;  // Flexible! Can swap chefs
    }
    
    public void startCooking() {
        chef.cook();           // Works with ANY chef
        chef.seasonFood();     // As long as they implement interface
        chef.manageKitchen();
    }
}

// Usage:
Restaurant restaurant1 = new Restaurant(new IndianChef());
Restaurant restaurant2 = new Restaurant(new ChineseChef());
// Both work the same way, but with different implementations!
```

---

## Why Use Interfaces? (The 4 Main Reasons)

### Reason 1: Loose Coupling (Independence)

**WITHOUT Interface (TIGHTLY COUPLED - BAD):**
```java
public class AuthController {
    // Hardcoded dependency on AuthServiceImpl
    private AuthServiceImpl authService = new AuthServiceImpl();
    
    public void login(String email, String password) {
        authService.login(email, password);
    }
}

// Problem: If we want to use AuthServiceV2, we must change this file!
// OR if we want to test with mock service, we can't!
```

**WITH Interface (LOOSELY COUPLED - GOOD):**
```java
// Interface - defines contract
public interface IAuthService {
    LoginResponse login(String email, String password);
}

// Multiple implementations - all follow the contract
public class AuthServiceImpl implements IAuthService {
    @Override
    public LoginResponse login(String email, String password) {
        // Implementation 1
    }
}

public class AuthServiceV2 implements IAuthService {
    @Override
    public LoginResponse login(String email, String password) {
        // Implementation 2 (improved version)
    }
}

// Controller depends on INTERFACE, not concrete class
public class AuthController {
    private final IAuthService authService;  // INTERFACE, not impl!
    
    public AuthController(IAuthService authService) {
        this.authService = authService;  // Can receive ANY implementation
    }
    
    public void login(String email, String password) {
        authService.login(email, password);
    }
}

// We can easily swap implementations without changing controller!
AuthController controller1 = new AuthController(new AuthServiceImpl());
AuthController controller2 = new AuthController(new AuthServiceV2());
AuthController controller3 = new AuthController(new MockAuthService());  // For testing!
// All work! Controller doesn't care!
```

**Why This Matters:**
- If business wants a better implementation, no code changes needed
- Easier to test (can pass mock implementations)
- Each class focuses on ONE job

---

### Reason 2: Easy Testing (Using Mocks)

**WITHOUT Interface (HARD TO TEST):**
```java
public class UserService {
    private UserRepository userRepository = new UserRepository();
    
    public User getUser(Long id) {
        return userRepository.findById(id);  // Talks to REAL DATABASE!
    }
}

@Test
public void testGetUser() {
    UserService service = new UserService();
    User user = service.getUser(1);  // This tries to hit REAL DATABASE!
    // Problem: Test is slow, needs database setup, brittle
}
```

**WITH Interface (EASY TO TEST):**
```java
public interface IUserRepository {
    User findById(Long id);
}

public class RealUserRepository implements IUserRepository {
    @Override
    public User findById(Long id) {
        // Actually queries database
    }
}

public class MockUserRepository implements IUserRepository {
    @Override
    public User findById(Long id) {
        // Returns fake test data, no database needed!
        return new User(1, "Test User", "test@test.com");
    }
}

public class UserService {
    private final IUserRepository userRepository;  // INTERFACE
    
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;  // Can be real or mock!
    }
    
    public User getUser(Long id) {
        return userRepository.findById(id);
    }
}

@Test
public void testGetUser() {
    // Pass MOCK instead of real repository
    IUserRepository mockRepo = new MockUserRepository();
    UserService service = new UserService(mockRepo);
    
    User user = service.getUser(1);
    assertEquals("Test User", user.getName());  // Fast! No database!
}
```

**Why This Matters:**
- Tests run fast (no database access)
- Tests are reliable (no network issues)
- Tests are isolated (don't affect real data)

---

### Reason 3: Multiple Implementations (Flexibility)

Imagine you want different payment processors:

```java
// INTERFACE - Contract
public interface IPaymentService {
    void processPayment(BigDecimal amount);
}

// Implementation 1 - PayPal
public class PayPalService implements IPaymentService {
    @Override
    public void processPayment(BigDecimal amount) {
        // PayPal API integration
    }
}

// Implementation 2 - Stripe
public class StripeService implements IPaymentService {
    @Override
    public void processPayment(BigDecimal amount) {
        // Stripe API integration
    }
}

// Implementation 3 - Razorpay (India-specific)
public class RazorpayService implements IPaymentService {
    @Override
    public void processPayment(BigDecimal amount) {
        // Razorpay API integration
    }
}

// Order Service works with ANY payment processor
public class OrderService {
    private final IPaymentService paymentService;
    
    public OrderService(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    public void completeOrder(BigDecimal amount) {
        paymentService.processPayment(amount);
        //Send email, update inventory, etc.
    }
}

// Different users can use different payment methods!
OrderService for_US_user = new OrderService(new PayPalService());
OrderService for_Europe_user = new OrderService(new StripeService());
OrderService for_India_user = new OrderService(new RazorpayService());
// All use same OrderService code!
```

**Why This Matters:**
- You can add new payment methods without changing OrderService
- Old code works with new implementations
- Follows Open-Closed Principle (SOLID)

---

### Reason 4: Dependency Inversion (SOLID Principle)

**Traditional Approach (BAD):**
```java
// High-level class depends on low-level class
public class ReportService {
    private PdfReporter pdfReporter = new PdfReporter();  // Concrete class!
    
    public void generateReport() {
        pdfReporter.generate();  // Tied to PDF only
    }
}

// Problem: If I want to generate Excel reports, need to rewrite ReportService
```

**With Interfaces (GOOD - Dependency Inversion):**
```java
// High-level class depends on INTERFACE, not concrete class
public interface IReporter {
    void generate();
}

public class PdfReporter implements IReporter {
    @Override
    public void generate() { /* PDF logic */ }
}

public class ExcelReporter implements IReporter {
    @Override
    public void generate() { /* Excel logic */ }
}

public class ReportService {
    private final IReporter reporter;  // Depends on interface
    
    public ReportService(IReporter reporter) {
        this.reporter = reporter;  // Can switch reporters
    }
    
    public void generateReport() {
        reporter.generate();  // Works with any reporter
    }
}

// Now I can generate PDF or Excel without changing ReportService!
ReportService pdfService = new ReportService(new PdfReporter());
ReportService excelService = new ReportService(new ExcelReporter());
```

---

## Your Project's Interfaces

**In Your Leave Management Project:**

```java
// 1. SERVICE INTERFACES
public interface IAuthService {
    LoginResponse login(LoginRequest request);
    void signup(SignupRequest request);
}

public interface ILeaveRequestService {
    LeaveRequestDto createLeaveRequest(LeaveRequestDto request);
    LeaveRequestDto approveLeaveRequest(Long id);
}

public interface IUserService {
    UserProfile getUserProfile(Long id);
}

// 2. REPOSITORY INTERFACES
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    Page<LeaveRequest> findByEmployeeId(Long employeeId, Pageable pageable);
}
```

**Why Your Project Uses Them:**
- ✅ Controllers depend on service interfaces, not implementations
- ✅ Tests can pass mock services
- ✅ Easy to swap implementations if business logic changes
- ✅ Clear contract - knows what methods must exist
- ✅ Follows SOLID principles

---

## How It All Fits Together: The Complete Picture

Let me show you exactly how your Leave Management project uses these components together:

```
REQUEST FLOW:
┌─────────────────────────────────────────────────┐
│  CLIENT: POST /api/auth/login                   │
│  WITH: { "email": "user@test.com" ... }         │
└──────────────┬──────────────────────────────────┘
               │ HTTP Request + JSON
┌──────────────▼──────────────────────────────────┐
│  @RestController (AuthController)               │
│  Receives HTTP request                          │
│  Converts JSON to LoginRequest object           │
│  @PostMapping("/login") marks method            │
│  @Valid triggers validation                     │
└──────────────┬──────────────────────────────────┘
               │ Calls method
┌──────────────▼──────────────────────────────────┐
│  @Service (AuthServiceImpl)                      │
│  Implements IAuthService interface              │
│  Contains business logic:                       │
│    1. Verify email exists                       │
│    2. Verify password matches                   │
│    3. Generate JWT token                        │
└──────────────┬──────────────────────────────────┘
               │ Gets data
┌──────────────▼──────────────────────────────────┐
│  @Repository (UserRepository)                   │
│  Extends JpaRepository<User, Long>              │
│  Spring auto-generates SQL:                     │
│    findByEmail() → SELECT * FROM users WHERE..  │
└──────────────┬──────────────────────────────────┘
               │ Query
┌──────────────▼──────────────────────────────────┐
│  DATABASE (MySQL)                               │
│  Returns user data from 'users' table           │
└──────────────┬──────────────────────────────────┘
               │ User data
┌──────────────▼──────────────────────────────────┐
│  Service returns LoginResponse                  │
│  (With JWT token)                               │
└──────────────┬──────────────────────────────────┘
               │ Java object
┌──────────────▼──────────────────────────────────┐
│  @RestController converts to JSON:              │
│  { "token": "eyJhbG...", "email": "..." }       │
└──────────────┬──────────────────────────────────┘
               │ HTTP Response
┌──────────────▼──────────────────────────────────┐
│  CLIENT receives JSON response                  │
│  STATUS: 200 OK                                 │
└─────────────────────────────────────────────────┘
```

**Key Points:**
1. **@RestController** = Entry point (HTTP handling)
2. **@Service** = Business logic (decision making)
3. **@Repository** = Data access (database queries)
4. **Interfaces** = Contracts between layers

Each layer depends on the **interface** above it, not the concrete implementation. This is why we use interfaces!

---

# COMPLETE ANNOTATIONS EXPLANATION

## Understanding Annotations

**What is an Annotation?**
- A marker that provides information about code but doesn't directly affect code operation
- Syntax: `@AnnotationName`
- Processed at compile time or runtime
- Tell Spring Framework how to manage your class

**Analogy:** Annotations are like labels on your code - they inform Spring what to do with that class.

---

## 1️⃣ CLASS-LEVEL ANNOTATIONS

### @SpringBootApplication
```java
@SpringBootApplication
public class LeaveManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeaveManagementApplication.class, args);
    }
}
```

**What it does:**
- Marks the entry point of Spring Boot application
- Combines 3 annotations: `@Configuration` + `@ComponentScan` + `@EnableAutoConfiguration`

**Why use it:**
- Without this, Spring doesn't know where to start
- Enables auto-configuration of Spring
- Scans classpath for components

**Interview explanation:**
> "This annotation marks the main class that Spring Boot looks for to start the application. It's a shortcut for three separate annotations: @Configuration (this class has bean definitions), @ComponentScan (scan packages for @Component, @Service, @Controller), and @EnableAutoConfiguration (auto-configure Spring based on JARs on classpath)."

---

### @Configuration
```java
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
```

**What it does:**
- Marks class as a source of bean definitions
- Methods annotated with `@Bean` return objects managed by Spring

**Why use it:**
- Centralize configuration
- Create custom beans programmatically
- Reusable across application

**Interview explanation:**
> "I use @Configuration to define beans that Spring should manage. Inside, I create methods with @Bean annotation that return configured objects. For example, I create a PasswordEncoder bean that BCrypt-encodes passwords. This way, whenever any class needs PasswordEncoder, Spring injects the same instance."

---

### @Entity
```java
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
```

**What it does:**
- Marks class as JPA entity (maps to database table)
- Creates table automatically if `ddl-auto=update` in application.properties

**Why use it:**
- ORM (Object-Relational Mapping) - write Java code instead of SQL
- Automatic SQL generation
- Type safety

**Interview explanation:**
> "The @Entity annotation tells Spring Data JPA that this is a database entity. It creates a table for this class automatically. Without it, this is just a regular Java class. I use @Table to specify table name and constraints. The @Id marks the primary key, and @Column defines properties of that field like nullable, unique, length constraints."

---

### @RestController
```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return loginService.authenticate(request);  // Returns Java object
    }
}

// Result for client:
// HTTP Response: {"token": "abc123...", "email": "user@example.com"}
```

**What it does (Simple):**
- Marks this class as an **API endpoint handler**
- Java objects → Automatically converted to JSON
- Handles HTTP requests from frontend

**Real-world analogy:**
Think of a hotel - @RestController is the front desk receptionist who takes requests from guests and responds with information.

**Before vs After Code:**
```java
// BEFORE @RestController (Repetitive - BAD)
@Controller
public class AuthController {
    @PostMapping("/login")
    @ResponseBody  // Need this!
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.authenticate(request);
    }
    
    @PostMapping("/signup")
    @ResponseBody  // Need this AGAIN!
    public SignupResponse signup(@RequestBody SignupRequest request) {
        return authService.register(request);
    }
}

// AFTER @RestController (Clean - GOOD)
@RestController  // Combines @Controller + @ResponseBody
public class AuthController {
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.authenticate(request);  // Already JSON
    }
    
    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody SignupRequest request) {
        return authService.register(request);  // Already JSON
    }
}
```

**Why use @RestController?**
1. ✅ **Less code** - Don't repeat @ResponseBody on every method
2. ✅ **Cleaner syntax** - Shows intent is REST API
3. ✅ **Auto-serialization** - Java object → JSON automatically

**Interview explanation:**
> "I use @RestController for REST APIs because it automatically converts my Java objects to JSON. Without it, I'd need @ResponseBody on every method, which is repetitive. @RestController tells Spring: 'This class handles HTTP requests and returns JSON responses.' Perfect for REST APIs."

---

### @Service (Business Logic Layer)
```java
@Service
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    
    @Override
    public LoginResponse login(LoginRequest request) {
        // Find user
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Invalid credentials");
        }
        
        // Generate token
        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new LoginResponse(token, user.getEmail());
    }
}
```

**What it does (Simple):**
- **Business logic container** - All complex operations live here
- Spring manages it - Available for injection
- Implements interface - Provides contract

**Real-world analogy:**
Restaurant kitchen: @RestController = Waiter (takes order from client), @Service = Kitchen (does the real work - cooking), @Repository = Storage/Pantry (gets ingredients)

**Before vs After Code:**
```java
// PROBLEM: Logic in Controller (TIGHT COUPLING - BAD)
@RestController
public class AuthController {
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // WRONG: Business logic crammed here!
        Optional<User> user = repository.findByEmail(request.getEmail());
        if (user.isEmpty()) throw new Exception("Not found");
        
        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new Exception("Wrong password");
        }
        
        String token = generateJWT(user.get().getEmail());
        return new LoginResponse(token);
    }
}
// Issues: Hard to reuse, hard to test, controller too complex

// SOLUTION: Move logic to Service (SEPARATION OF CONCERNS - GOOD)
@Service
public class AuthService implements IAuthService {
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UserNotFoundException());
        verifyPassword(request.getPassword(), user);
        String token = generateToken(user.getEmail());
        return new LoginResponse(token);
    }
}

@RestController
public class AuthController {
    private final IAuthService authService;  // Inject service
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);  // One line!
    }
}
```

**Why use @Service?**
1. ✅ **Separation** - HTTP handling vs business logic separate
2. ✅ **Reusability** - Multiple controllers can use same service
3. ✅ **Testability** - Easy to test logic isolated from HTTP
4. ✅ **Maintainability** - Business logic changes in one place

**Interview explanation:**
> "I use @Service to separate concerns. Controllers handle HTTP requests (routing, validation), services handle business logic (authentication, algorithms, workflows). Benefits: 1) Services are reusable - if two controllers need same logic, they use same service, 2) Easy to test - test just the logic without HTTP, 3) Maintainability - business logic changes in one place."

---

### @Repository (Data Access Layer)
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring auto-generates: SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
    
    // Spring auto-generates: SELECT * FROM users WHERE role = ?
    List<User> findByRole(UserRole role);
}
```

**What it does (Simple):**
- **Database access layer** - All data operations go here
- Auto-generates SQL from method names
- Handles CRUD (Create, Read, Update, Delete)

**Real-world analogy:**
Library system: Repository is the physical library storage (actual books), Service is the catalog system, Controller is the librarian taking requests.

**Before vs After Code:**
```java
// PROBLEM: SQL in Service (DATABASE DEPENDENT - BAD)
@Service
public class UserService {
    public User findUser(String email) {
        // Hardcoded SQL!
        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        
        // Manual mapping...
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
// Issues: SQL scattered, hard to test, database-dependent, verbose

// SOLUTION: Use Repository (DATABASE AGNOSTIC - GOOD)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // That's it!
}

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();  // Simple!
    }
}
```

**Why use @Repository?**
1. ✅ **No SQL** - Spring generates from method names
2. ✅ **Testable** - Pass mock repository in tests
3. ✅ **Database agnostic** - Switch databases without code changes
4. ✅ **Less code** - No ResultSet mapping, no PreparedStatement boilerplate

**Interview explanation:**
> "I use @Repository to separate data access from business logic. Spring Data JPA automatically generates SQL from method names. Benefits: 1) No SQL strings in code, 2) Easy to test - pass mock repository, 3) Database agnostic - if business switches from MySQL to PostgreSQL, I just change configuration, my code stays the same."

---

### @RequestMapping / @PostMapping / @GetMapping / @PatchMapping
```java
@PostMapping("/login")
public ResponseEntity<ApiResponse<LoginResponse>> login(
        @Valid @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(ApiResponse.success(response, "Login successful"));
}
```

**What it does:**
- `@PostMapping` = HTTP POST request handler
- `@GetMapping` = HTTP GET request handler
- `@PatchMapping` = HTTP PATCH (partial update) handler
- All extend `@RequestMapping` which specifies URL path

**Why use different methods?**
- **POST** = Create resource (not idempotent - side effects)
- **GET** = Retrieve resource (safe, idempotent)
- **PATCH** = Partial update (idempotent)
- **PUT** = Full replacement (idempotent)
- **DELETE** = Delete resource (idempotent)

**HTTP Method Meanings:**
```
POST   /api/leave-requests              → Create new leave request
GET    /api/leave-requests              → Get all leave requests
GET    /api/leave-requests/5            → Get specific leave request
PATCH  /api/leave-requests/5/approve    → Partially update (approve)
DELETE /api/leave-requests/5            → Delete leave request
```

**Interview explanation:**
> "I use different HTTP methods to indicate what action is being performed. POST creates a new resource, GET retrieves it, PATCH partially updates it. This follows REST principles. @PostMapping, @GetMapping, @PatchMapping are shortcuts for @RequestMapping with specific HTTP methods. They make code more readable and intentions clear."

---

## 2️⃣ FIELD-LEVEL ANNOTATIONS

### @Id
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

**What it does:**
- Marks field as primary key (unique identifier)
- `@GeneratedValue` = Auto-increment on each INSERT

**Why use it:**
- Every database table needs a unique identifier
- `IDENTITY` strategy = Database auto-increments (1, 2, 3...)
- Efficient queries using ID

**Interview explanation:**
> "The @Id annotation marks this field as the primary key - the unique identifier for each row. GeneratedValue with IDENTITY strategy means the database auto-increments this field. So first user gets ID=1, second gets ID=2, etc. This is the fastest way to uniquely identify records."

---

### @Column
```java
@Column(name = "email", nullable = false, unique = true, length = 100)
private String email;
```

**What it does:**
- Maps field to database column
- Defines column constraints

**Properties:**
- `name` = Column name in database
- `nullable = false` = Cannot be NULL (required field)
- `unique = true` = No duplicate values
- `length = 100` = Max 100 characters
- `updatable = false` = Cannot be updated after insert

**Interview explanation:**
> "The @Column annotation defines how this field appears in the database. I use nullable=false for required fields, unique=true to prevent duplicates (like email), and length to limit string size. For audit fields like createdAt, I use updatable=false so they can't be accidentally changed."

---

### @Enumerated
```java
@Enumerated(EnumType.STRING)
@Column(name = "role", nullable = false)
private UserRole role;

public enum UserRole {
    EMPLOYEE,
    MANAGER,
    ADMIN
}
```

**What it does:**
- Stores enum values in database
- `EnumType.STRING` = Stores as "EMPLOYEE", "MANAGER"
- `EnumType.ORDINAL` = Stores as 0, 1, 2 (not recommended)

**Why use STRING over ORDINAL:**
- STRING = More readable, change-safe
- If you add enum value in middle, ORDINAL breaks

**Interview explanation:**
> "I use @Enumerated with STRING type to store role as 'EMPLOYEE' or 'MANAGER' in database. This is more readable than storing as 0, 1 (which is ORDINAL). If I later add a new role, STRING won't break existing data, but ORDINAL would mix up the numbers."

---

### @Transient
```java
@Transient
private String verification Code;  // Not stored in database
```

**What it does:**
- Marks field as NOT part of database
- Won't create column, won't persist

**Why use it:**
- Fields needed temporarily, not permanently
- Calculated fields
- Helper fields for logic

---

## 3️⃣ RELATIONSHIP ANNOTATIONS

### @ManyToOne
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "employee_id", nullable = false)
private User employee;
```

**What it does:**
- Creates relationship: Many LeaveRequests → One User
- `@JoinColumn` specifies foreign key column
- `fetch = FetchType.LAZY` = Don't load related data unless accessed

**Why LAZY loading?**
- Performance optimization
- Don't load unnecessary data
- Only fetch when you call `leaveRequest.getEmployee()`

**Interview explanation:**
> "I use @ManyToOne to show that many leave requests belong to one employee. The @JoinColumn creates a foreign key column in database pointing to users table. LAZY loading means Spring doesn't automatically fetch employee data when loading leave request. This improves performance because sometimes you don't need employee info. You only fetch it when explicitly accessed."

---

### @OneToMany
```java
@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
private List<LeaveRequest> leaveRequests = new ArrayList<>();
```

**What it does:**
- Inverse side of @ManyToOne
- One User can have many LeaveRequests
- `cascade = CascadeType.ALL` = Delete leave requests when user deleted

**Interview explanation:**
> "This shows one user has many leave requests. Cascade means when I delete a user, all their leave requests are automatically deleted too. Orphan removal prevents orphaned records in database."

---

## 4️⃣ METHOD-LEVEL ANNOTATIONS

### @Override
```java
@Override
public LoginResponse login(LoginRequest loginRequest) {
    // Implement interface method
}
```

**What it does:**
- Tells compiler "this method overrides parent/interface method"
- Compiler errors if parent doesn't have this method

**Why use it:**
- Prevents mistakes (typos in method names)
- Code readability
- Maintenance aid

---

### @Bean
```java
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
```

**What it does:**
- Marks method as producing a bean
- Spring calls this method and registers returned object as bean

**Why use it:**
- Create beans programmatically instead of annotations
- Configure third-party libraries
- Conditional bean creation

**Interview explanation:**
> "I use @Bean to tell Spring to call this method and manage the returned object. Without it, even in @Configuration class, this method is just a regular method. So when any class needs PasswordEncoder, Spring provides the instance from this @Bean method."

---

### @PrePersist
```java
@PrePersist
protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
}
```

**What it does:**
- JPA lifecycle hook
- Runs BEFORE first INSERT into database
- Only runs once

**Why use it:**
- Auto-set created timestamp
- Initialize default values
- No need to manually set in every save

**Interview explanation:**
> "I use @PrePersist to automatically set the createdAt timestamp before saving a new entity to database. This runs once when entity is first persisted. Similarly, @PreUpdate runs before each UPDATE. This way, I don't need to manually set timestamps in service layer."

---

### @PreUpdate
```java
@PreUpdate
protected void onUpdate() {
    updatedAt = LocalDateTime.now();
}
```

**What it does:**
- JPA lifecycle hook
- Runs BEFORE UPDATE in database
- Runs every time entity is updated

---

## 5️⃣ VALIDATION ANNOTATIONS

### @Valid
```java
@PostMapping("/login")
public ResponseEntity<ApiResponse<LoginResponse>> login(
        @Valid @RequestBody LoginRequest loginRequest) {
    // loginRequest is validated before method is called
}
```

**What it does:**
- Triggers validation on the object
- Checks constraints defined by other @annotations
- Throws exception if validation fails

**Why use it:**
- Data integrity
- Prevents invalid data from reaching service layer
- Automatic HTTP 400 response on validation error

**Interview explanation:**
> "I use @Valid to validate request body before my controller method receives it. So if email format is wrong or password is too short, Spring rejects the request immediately with HTTP 400. I don't need to check these manually in my code."

---

### @NotNull, @NotBlank, @Email, @Min, @Max
```java
public class LoginRequest {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be 6-20 characters")
    private String password;

    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;
}
```

**What each does:**
- `@NotNull` = Field cannot be null
- `@NotBlank` = String cannot be empty or whitespace
- `@Email` = Must be valid email format
- `@Size` = String length constraints
- `@Min/@Max` = Numeric value range
- `@Pattern` = Regex pattern matching

**Interview explanation:**
> "I use validation annotations to define constraints on input data. When @Valid is triggered, these constraints are checked. For example, @Email validates email format, @NotBlank ensures field isn't empty. If validation fails, Spring automatically returns 400 with error message. This prevents invalid data from entering the system."

---

## 6️⃣ DEPENDENCY INJECTION ANNOTATIONS

### @RequiredArgsConstructor (Lombok)
```java
@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements ILeaveRequestService {
    
    private final UserRepository userRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final IAuditService auditService;
    
    // Lombok generates constructor with all three fields
    // Spring auto-calls this constructor
}
```

**What it does:**
- Lombok generates constructor with all final fields
- Spring automatically injected dependencies into constructor
- Called "Constructor Injection"

**Why use Constructor Injection?**
- Dependencies immutable (final)
- Easy to test (can pass mocks in constructor)
- Clear what dependencies are needed
- No null pointer exceptions

**Alternative (not recommended):**
```java
// Field injection - not recommended
@Service
public class LeaveRequestServiceImpl {
    @Autowired              // Don't use this
    private UserRepository userRepository;  // Could be null
}
```

**Interview explanation:**
> "I use @RequiredArgsConstructor for constructor-based dependency injection. Lombok generates a constructor that takes all final fields as parameters, and Spring automatically calls this constructor with dependencies. This is better than field injection because fields are immutable (final) and it's easier to test - I can pass mock objects."

---

### @Autowired (Alternative)
```java
@Service
public class AuthServiceImpl implements IAuthService {
    
    private final PasswordEncoder passwordEncoder;
    
    @Autowired  // Not needed if using constructor injection
    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
```

**What it does:**
- Marks dependency for auto-injection
- Spring finds bean of that type and injects it

**Why constructor injection is better:**
- Implicit with @RequiredArgsConstructor
- Immutable dependencies
- Better for testing

---

## 7️⃣ SECURITY ANNOTATIONS

### @PreAuthorize
```java
@PostMapping
@PreAuthorize("hasRole('EMPLOYEE')")
public ResponseEntity<ApiResponse<LeaveRequestDto>> createLeaveRequest(
        @Valid @RequestBody LeaveRequestDto dto) {
    // Only EMPLOYEE role can call
}

@PatchMapping("/{id}/approve")
@PreAuthorize("hasRole('MANAGER')")
public ResponseEntity<ApiResponse<LeaveRequestDto>> approveLeaveRequest(...) {
    // Only MANAGER role can call
}
```

**What it does:**
- Method-level authorization
- Checks if user has required roles
- Returns 403 if not authorized

**Why use it:**
- Declarative security (defined in code)
- Easy to understand permissions
- Consistent access control

**Common expressions:**
- `hasRole('ADMIN')` = Has ADMIN role
- `hasAnyRole('MANAGER', 'ADMIN')` = Has either role
- `isAuthenticated()` = User is logged in
- `hasPermission(...)` = Custom permission check

**Interview explanation:**
> "I use @PreAuthorize to enforce role-based access control. It runs before the controller method. So if user is EMPLOYEE but trying to approve leave (MANAGER only), Spring returns 403 Forbidden. This is declarative - the permission logic is visible in code."

---

## 8️⃣ FRAMEWORK ANNOTATIONS

### @Component, @Service, @Repository, @Controller
```java
// Generic component
@Component
public class EmailSender {
    // Utility class
}

// Service layer
@Service
public class LeaveRequestServiceImpl {
    // Business logic
}

// Repository layer
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Data access
}

// Controller layer
@RestController
public class AuthController {
    // API endpoints
}
```

**What they do:**
- All are Spring beans
- Differences are semantic (for clarity)
- All are managed by Spring

**Why differentiate them:**
- **@Component** = Generic utility
- **@Service** = Business logic
- **@Repository** = Data access (transaction management)
- **@Controller/@RestController** = HTTP handling

**Interview explanation:**
> "These are all @Component internally, but we use specific names for clarity. @Service identifies business logic classes, @Repository for data access (also adds transaction management), @RestController for API endpoints. This makes code structure clear and helps Spring provide specialized features."

---

### @Transactional
```java
@Service
@Transactional  // Wraps all methods in transaction
public class AuthServiceImpl implements IAuthService {
    
    @Override
    @Transactional(readOnly = true)  // Only SELECT queries
    public Page<LeaveRequestDto> getAllLeaveRequests(String status, Pageable pageable) {
        return leaveRequestRepository.findAll(pageable);
    }
    
    @Override
    @Transactional  // Can INSERT/UPDATE/DELETE
    public LeaveRequestDto createLeaveRequest(LeaveRequestDto dto) {
        LeaveRequest request = new LeaveRequest();
        // Set properties
        return leaveRequestRepository.save(request);
    }
}
```

**What it does:**
- Creates database transaction
- Either ALL changes succeed or ALL rollback
- Ensures data consistency

**Why use it:**
- Data integrity
- Automatic rollback on exception
- ACID properties (Atomicity, Consistency, Isolation, Durability)

**readOnly = true:**
- Optimization for read-only queries
- Spring knows it's SELECT only
- Can't accidentally UPDATE

**Interview explanation:**
> "I use @Transactional to wrap database operations in a transaction. If I'm creating a user and initializing leave balance, either both succeed or both fail - no partial updates. If exception occurs, Spring automatically rolls back all changes. readOnly=true is an optimization for queries that don't modify data."

---

### @Lazy
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "employee_id")
private User employee;
```

**What it does:**
- Delays loading until accessed
- Performance optimization

**Interview explanation:**
> "I use LAZY fetch type so when I load a LeaveRequest, the related User is not loaded from database immediately. Only when I call leaveRequest.getEmployee() does Spring fetch the user. This reduces database queries and improves performance."

---

## 9️⃣ EXCEPTION HANDLING ANNOTATIONS

### @ExceptionHandler
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUserNotFoundException(
            UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(e.getMessage()));
    }
    
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse<?>> handleDuplicateEmailException(
            DuplicateEmailException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(e.getMessage()));
    }
}
```

**What it does:**
- Catches specific exceptions thrown in controllers
- Converts them to HTTP responses
- Centralizes error handling

**Why use it:**
- Consistent error format
- Don't repeat error handling in every controller
- Proper HTTP status codes
- Professional error messages

**Interview explanation:**
> "I use @ExceptionHandler to centralize exception handling. When UserNotFoundException is thrown anywhere in the application, this method catches it and returns HTTP 404 with error message. Without this, I'd need try-catch in every controller method. @RestControllerAdvice makes this handler global across all controllers."

---

### @RestControllerAdvice
```java
@RestControllerAdvice
@Slf4j  // Lombok: Auto-generates Logger
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception e) {
        log.error("Unexpected error: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("An unexpected error occurred"));
    }
}
```

**What it does:**
- Makes @ExceptionHandler global (applies to all controllers)
- Equivalent to @ControllerAdvice + @ResponseBody

**Why use it:**
- Single place for error handling
- DRY principle (Don't Repeat Yourself)
- Consistent responses

**Interview explanation:**
> "I use @RestControllerAdvice to apply exception handlers globally across my entire application. Without it, @ExceptionHandler would only work in that specific class. This ensures all exceptions are handled consistently no matter which controller throws them."

---

---

# ENTITIES & DATABASE

## User Entity - Complete Explanation

```java
package com.leavemgmt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.leavemgmt.entity.enums.UserRole;
import java.time.LocalDateTime;

@Entity                    // Maps to database table
@Table(                    // Additional table configuration
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
        // Email column must have unique values across all rows
    }
)
@Data                      // Lombok: getters, setters, equals, hashCode, toString
@NoArgsConstructor        // Lombok: no-argument constructor
@AllArgsConstructor       // Lombok: constructor with all fields
public class User {

    @Id                    // Primary key
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
        // Auto-increment: 1, 2, 3...
    )
    private Long id;

    @Column(
        name = "name",      // Column name in database
        nullable = false,   // Cannot be NULL
        length = 100        // VARCHAR(100)
    )
    private String name;

    @Column(
        name = "email",
        nullable = false,
        unique = true,      // Email must be unique across all users
        length = 100
    )
    private String email;

    @Column(
        name = "password_hash",
        nullable = false
        // Never store plain passwords!
        // Always hash using BCrypt or similar
    )
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    // Stores "EMPLOYEE" or "MANAGER" in database
    // (not 0, 1 which is less readable)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "department", length = 100)
    private String department;

    @Column(
        name = "created_at",
        nullable = false,
        updatable = false  // Cannot be changed after creation
    )
    private LocalDateTime createdAt;

    @Column(
        name = "updated_at",
        nullable = false
    )
    private LocalDateTime updatedAt;

    // JPA Lifecycle Callbacks

    @PrePersist
    // This method runs BEFORE INSERT
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    // This method runs BEFORE UPDATE
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
```

**Why each field?**
- `id` = Unique identifier for each user
- `name` = Store user's name
- `email` = For login (must be unique)
- `passwordHash` = Never store plain passwords (security risk)
- `role` = Determine permissions (EMPLOYEE vs MANAGER)
- `department` = Track which department user belongs to
- `createdAt` = Audit trail (when was user created)
- `updatedAt` = Track modifications

**Database Table Created:**
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,          -- EMPLOYEE or MANAGER
    department VARCHAR(100),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT uk_users_email UNIQUE (email)
);
```

---

## LeaveRequest Entity - Complete Explanation

```java
@Entity
@Table(name = "leave_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many LeaveRequests belong to ONE employee
    @ManyToOne(fetch = FetchType.LAZY)
    // fetch = LAZY means Don't load employee unless accessed (optimization)
    @JoinColumn(
        name = "employee_id",
        nullable = false
        // Foreign key column pointing to users table
    )
    private User employee;

    // Manager who approves (can be null initially)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    // No nullable=false here because manager assigns AFTER pending request
    private User manager;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;  // 2024-12-20

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;    // 2024-12-22

    @Column(name = "reason", length = 500)
    private String reason;        // "Family vacation"

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LeaveRequestStatus status;
    // Possible values: PENDING, APPROVED, REJECTED

    @Column(name = "approval_comment", length = 500)
    private String approvalComment;  // Manager's reason if rejected

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = LeaveRequestStatus.PENDING;  // Default status
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Enum for allowed status values
    public enum LeaveRequestStatus {
        PENDING,    // Waiting for manager approval
        APPROVED,   // Manager approved
        REJECTED    // Manager rejected
    }
}
```

**Why these relationships?**
- `@ManyToOne` with `employee` = One user can have many leave requests
- `@ManyToOne` with `manager` = One manager can approve many leaves
- LAZY loading = Performance optimization (don't load user objects unless needed)

**Database Table Created:**
```sql
CREATE TABLE leave_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    manager_id BIGINT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason VARCHAR(500),
    status VARCHAR(20) NOT NULL,        -- PENDING, APPROVED, REJECTED
    approval_comment VARCHAR(500),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES users(id),
    FOREIGN KEY (manager_id) REFERENCES users(id)
);
```

---

## UserRole Enum - Complete Explanation

```java
package com.leavemgmt.entity.enums;

public enum UserRole {
    EMPLOYEE,   // Regular employee (can submit leave requests)
    MANAGER,    // Manager (can approve/reject leaves)
    ADMIN       // Admin (can do everything)
}
```

**Why use Enum?**
- Type safety (can't have invalid role like "XYZ")
- Compile-time checking
- Clear possible values
- Database integrity

**Interview explanation:**
> "I use enum for roles because there are only specific valid values (EMPLOYEE, MANAGER, ADMIN). If I used a String, someone could accidentally create role='INVALID'. Enum prevents this. Also, AuthenticationProvider can check hasRole('MANAGER') which is type-safe."

---

---

# SECURITY IMPLEMENTATION

## How Security Works in This Project

```
Client Request
    ↓
[JwtAuthenticationFilter] ← Intercepts EVERY request
    ↓
Extract JWT from Authorization header
    ↓
Validate JWT signature (Is it tampered with?)
    ↓
Extract email from JWT claims
    ↓
Load User details from database
    ↓
Store in SecurityContext (ThreadLocal)
    ↓
Request proceeds with authentication
    ↓
Controller checks @PreAuthorize permissions
    ↓
Response sent back to client
```

---

## JWT (JSON Web Token) - Complete Explanation

### What is JWT?

JWT is a token-based authentication mechanism for stateless APIs.

**Three parts of JWT (separated by dots):**
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
eyJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwiaWF0IjoxNjExMDAwMDAwfQ.
TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ

^                                              ^                                              ^
Header (Base64)                            Payload (Base64)                             Signature
```

**Header** (decoded):
```json
{
  "alg": "HS256",    // Algorithm: HMAC SHA-256
  "typ": "JWT"       // Type
}
```

**Payload** (decoded):
```json
{
  "sub": "john@example.com",        // Subject (who this token belongs to)
  "iat": 1611000000,                // Issued at (when created)
  "exp": 1611003600                 // Expiration time
}
```

**Signature** (generated):
```
HMAC(
  HS256,
  base64(header) + "." + base64(payload),
  secret_key
)
```

### Why JWT?

| Feature | Traditional Session | JWT |
|---------|-------------------|-----|
| Storage | Server-side (database/memory) | Client-side (browser) |
| Scalability | Hard (sync across servers) | Easy (stateless) |
| Scalability | Session replication needed | No server state needed |
| CSRF Vulnerable | Yes | No (stateless) |
| Bandwidth | Session ID only | Full claims in token |

**Use JWT when:**
- Building REST APIs
- Microservices architecture
- Mobile apps
- Stateless requirement

**Use Sessions when:**
- Traditional web apps
- Server-side rendering
- Need immediate revocation

---

## JwtTokenProvider - Complete Explanation

```java
package com.leavemgmt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
// Spring manages this as a singleton bean
public class JwtTokenProvider {

    private final SecretKey secretKey;
    // Secret key for signing tokens (keep this SAFE!)

    public JwtTokenProvider() {
        // Create encryption key
        this.secretKey = Keys.hmacShaKeyFor(
            SecurityConstants.JWT_SECRET.getBytes(StandardCharsets.UTF_8)
        );
        // Takes string like "my-super-secret-key-should-be-long..."
        // Converts to bytes
        // Creates HMAC key
    }

    /**
     * Generate JWT token for a user after successful login
     * @param email User's email (used as subject/identifier)
     * @return JWT token string
     */
    public String generateToken(String email) {
        Date now = new Date();
        // Current timestamp

        Date expiryDate = new Date(
            now.getTime() + SecurityConstants.JWT_EXPIRATION
        );
        // Token expires 24 hours from now
        // Example: JWT_EXPIRATION = 86400000 (milliseconds in 24 hours)

        return Jwts.builder()
                .subject(email)
                // Who this token belongs to (the user's email)
                
                .issuedAt(now)
                // When was this token created
                
                .expiration(expiryDate)
                // When will this token expire
                
                .signWith(secretKey, SignatureAlgorithm.HS256)
                // Sign using HMAC-SHA256 to prevent tampering
                // If someone changes payload, signature becomes invalid
                
                .compact();
                // Convert to string format "header.payload.signature"
    }

    /**
     * Extract email (subject) from JWT token
     * This runs AFTER validating the token
     * @param token JWT token string
     * @return Email stored in token
     * @throws Exception if token is invalid
     */
    public String getEmailFromToken(String token) {
        // Parse and verify token
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                // Verify using the same secret key
                // If token was modified, this throws exception
                
                .build()
                .parseClaimsJws(token)
                // Claims = decoded payload
                
                .getBody();

        return claims.getSubject();
        // Return the email (subject)
    }

    /**
     * Verify if JWT token is valid (not tampered, not expired)
     * @param token JWT token string
     * @return true if valid, false if invalid/expired
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    // Verify signature with same key
                    
                    .build()
                    .parseClaimsJws(token);
                    // This throws exception if:
                    // 1. Token is malformed
                    // 2. Signature is invalid (tampered)
                    // 3. Token is expired

            return true;
            // Token is valid
        } catch (Exception e) {
            // Any exception means token is invalid
            return false;
        }
    }
}
```

**Interview Q&A:**

**Q: Why sign the token?**
> "I sign the token using HMAC-SHA256 so that if anyone tries to modify the payload, the signature becomes invalid. When I validate the token later, I recalculate the signature and compare. If they don't match, the token is rejected. This prevents tampering."

**Q: Where should the secret key come from?**
> "The secret key should be stored in environment variables or configuration files, never hardcoded. It's typically read from SecurityConstants or application.yml. It should be at least 256 bits (32 characters) long for security."

**Q: Can JWT tokens be revoked?**
> "Not directly. JWT is stateless so there's no server-side list of valid tokens. To revoke, you either: 1) Wait for expiration, 2) Maintain a blacklist (defeats stateless purpose), or 3) Use shorter expiration times. That's why I set expiration to 24 hours."

---

## JwtAuthenticationFilter - Complete Explanation

```java
package com.leavemgmt.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // OncePerRequestFilter = Runs ONCE per HTTP request (not multiple times)

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    /**
     * This method runs for EVERY incoming HTTP request
     * It extracts JWT token, validates it, and sets authentication
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            // The incoming HTTP request
            
            HttpServletResponse response,
            // The HTTP response object (we don't modify it)
            
            FilterChain filterChain)
            // The chain of other filters to pass request to
            
            throws ServletException, IOException {

        try {
            // Step 1: Extract JWT from request
            String jwt = getJwtFromRequest(request);
            // Looks for "Authorization: Bearer <token>"

            // Step 2: Check if JWT exists AND is valid
            if (jwt != null && tokenProvider.validateToken(jwt)) {
                
                // Step 3: Extract email from token
                String email = tokenProvider.getEmailFromToken(jwt);

                // Step 4: Load user details from database
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                // This loads full user object with authorities/roles

                // Step 5: Create authentication token
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,           // Who is this (user object)
                                null,                  // Credentials (null because JWT auth)
                                userDetails.getAuthorities()  // User's roles/permissions
                        );

                // Step 6: Add request-specific details
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Details include: IP address, session ID, etc.

                // Step 7: Store authentication in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // SecurityContext is ThreadLocal
                // This makes authentication available to entire request
                
            }
            // If JWT is null or invalid, we don't set authentication
            // Unauthenticated request proceeds
            
        } catch (Exception e) {
            // If any error occurs during token parsing
            logger.error("Could not set user authentication in security context", e);
            // Log but don't fail - let request proceed
            // @PreAuthorize will reject if authentication required
        }

        // Pass request to next filter in chain (or controller if last filter)
        filterChain.doFilter(request, response);
    }

    /**
     * Extract JWT token from Authorization header
     * Format: "Authorization: Bearer eyJhbGc..."
     * @param request HTTP request
     * @return JWT token or null if not present
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        // AUTHORIZATION_HEADER = "Authorization"
        
        if (bearerToken != null && 
            bearerToken.startsWith(SecurityConstants.BEARER_PREFIX)) {
            // BEARER_PREFIX = "Bearer "
            
            // Remove "Bearer " prefix and return just the token
            return bearerToken.substring(SecurityConstants.BEARER_PREFIX.length());
        }
        return null;
    }
}
```

**Why this filter?**

Every REST API request needs:
1. Extract token from request
2. Validate token
3. Load user details
4. Set in SecurityContext so controller knows who's requesting

**Interview explanation:**
> "This filter runs before every request. It extracts the JWT token from the Authorization header, validates it's not expired or tampered, loads the user from database, and stores them in SecurityContext. This way, when the controller runs, it knows who the authenticated user is. If token is missing or invalid, the request has no authentication and @PreAuthorize rejects it."

---

## SecurityConfig - Complete Explanation

```java
package com.leavemgmt.config;

import com.leavemgmt.security.CustomUserDetailsService;
import com.leavemgmt.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// This class contains Spring Security configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Create password encoder bean
        // BCrypt with strength 10
        return new BCryptPasswordEncoder(10);
        
        // Why BCrypt?
        // - One-way hashing (can't decrypt)
        // - Salted hashing (same password different hash)
        // - Slow hashing (prevents brute force)
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // Create AuthenticationManager bean
        // This manages how users are authenticated
        
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        
        // Configure how to authenticate users
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                // Load user details from database
                
                .passwordEncoder(passwordEncoder());
                // Use BCrypt for password verification
        
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HTTP security for the entire application
        
        http
            .csrf(csrf -> csrf.disable())
            // CSRF = Cross-Site Request Forgery
            // Disable for REST APIs (stateless, no sessions)
            // CSRF only needed for session-based apps
            
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // STATELESS = Don't create server-side sessions
            // JWT handles authentication (client-side tokens)
            // Better for scalability (no session replication needed)
            
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/auth/**").permitAll()
                // /auth/login and /auth/signup accessible without authentication
                // Logical: you can't login if you need to be logged in
                
                .requestMatchers("/actuator/**").permitAll()
                // Health checks also accessible without auth
                // Health checks: GET /actuator/health
                
                .anyRequest().authenticated()
                // All other endpoints require authentication (JWT token)
            )
            
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );
            // Add JWT filter BEFORE the default auth filter
            // This way JWT is checked first
        
        return http.build();
    }
}

@EnableWebSecurity
// Enable Spring Security for this application
public class SecurityConfig {
    // Configuration here
}

@EnableMethodSecurity
// Enable @PreAuthorize, @PostAuthorize, @Secured annotations
// These protect individual methods
public class SecurityConfig {
    // Configuration here
}
```

**Security Flow:**

```
Request comes in
    ↓
[JwtAuthenticationFilter] runs FIRST
    ↓
Token validated, user authenticated
    ↓
SecurityContext has authentication
    ↓
[Spring Security check] 
    ↓
.anyRequest().authenticated() enforces authentication
    ↓
@PreAuthorize checks role
    ↓
Controller method runs (if authorized)
```

**Interview explanation:**
> "I use SecurityConfig to centralize all security settings. I disable CSRF because REST APIs are stateless. I set STATELESS session policy so no server-side sessions are created - JWT tokens handle authentication. I add JWT filter BEFORE default authentication so it runs first. @EnableMethodSecurity allows @PreAuthorize annotations on controller methods. BCrypt strength 10 provides good security/performance balance."

---

## BCrypt Password Hashing - Why It's Secure

```java
// Plain password
String password = "MyPassword123";

// Hashed (first time)
String hash1 = passwordEncoder.encode(password);
// Result: $2b$10$somehash...
// Never same twice due to salt

// Hashed (second time)
String hash2 = passwordEncoder.encode(password);
// Result: $2b$10$completelydifferent...

// Verification
if (passwordEncoder.matches(password, hash1)) {
    // True - same password
}

// Brute force attempt
String attemptedPassword = "MyPassword124";  // Guessing
if (passwordEncoder.matches(attemptedPassword, hash1)) {
    // False - different password
}
```

**Why BCrypt?**

1. **One-way hashing:** Can't decrypt (no decryption algorithm)
2. **Salted:** Each hash includes random salt, same password = different hashes
3. **Adaptive:** Strength parameter (10) makes it slower, prevents brute force
4. **Industry standard:** Used in production everywhere

**Interview explanation:**
> "I use BCrypt because it's a one-way hash - you can't decrypt the password. Even if someone steals the database, they can't get plain passwords. The strength parameter (10) determines how many iterations are done. Higher strength = slower hashing = more resistant to brute force attacks. The salt ensures even if two users have same password, hashes are different."

---

---

# CONTROLLERS & API ENDPOINTS

## AuthController - Complete Explanation

```java
package com.leavemgmt.controller;

import com.leavemgmt.dto.ApiResponse;
import com.leavemgmt.dto.LoginRequest;
import com.leavemgmt.dto.LoginResponse;
import com.leavemgmt.dto.SignupRequest;
import com.leavemgmt.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// This is a REST API controller (not MVC with HTML pages)
// Every method returns JSON, not HTML

@RequestMapping("/auth")
// Base URL for all endpoints in this controller
// POST /auth/login
// POST /auth/signup

@RequiredArgsConstructor
// Lombok: Create constructor with all final fields
// Used for dependency injection

@CrossOrigin(origins = "*", maxAge = 3600)
// Allow requests from ANY domain (frontend can call from different domain)
// maxAge = 3600 = Cache CORS preflight response 1 hour
public class AuthController {

    private final IAuthService authService;
    // Dependency injection - Spring provides this

    /**
     * Login endpoint - Authenticate user and return JWT token
     * POST /api/auth/login
     * Request body: { "email": "...", "password": "...", "role": "EMPLOYEE" }
     * Response: { "token": "jwt...", "userProfile": {...} }
     */
    @PostMapping("/login")
    // HTTP POST to /api/auth/login
    
    // Return type: ResponseEntity<ApiResponse<LoginResponse>>
    // ApiResponse is wrapper (success, message, data, timestamp)
    // LoginResponse is actual data (token, userProfile)
    
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest loginRequest
            // @Valid = Validate request against constraints
            // @RequestBody = Parse JSON request body into object
    ) {
        // Call service layer to authenticate
        LoginResponse response = authService.login(loginRequest);
        
        // Return 200 OK with response
        return ResponseEntity.ok(
            ApiResponse.success(response, "Login successful")
        );
    }

    /**
     * Signup endpoint - Register new user
     * POST /api/auth/signup
     * Request body: { "name": "...", "email": "...", "password": "...", "role": "EMPLOYEE" }
     * Response: { "token": "jwt...", "userProfile": {...} }
     */
    @PostMapping("/signup")
    // HTTP POST to /api/auth/signup
    
    public ResponseEntity<ApiResponse<LoginResponse>> signup(
            @Valid @RequestBody SignupRequest signupRequest
    ) {
        // Call service layer to create user
        LoginResponse response = authService.signup(signupRequest);
        
        // Return 201 CREATED with response
        // 201 = Resource successfully created
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Signup successful"));
    }
}
```

**Request/Response Examples:**

**Login Request:**
```http
POST /api/auth/login
Content-Type: application/json

{
    "email": "john@example.com",
    "password": "password123",
    "role": "EMPLOYEE"
}
```

**Login Response (200 OK):**
```json
{
    "success": true,
    "message": "Login successful",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwiaWF0IjoxNjAwMDAwMDAwLCJleHAiOjE2MDAwODY0MDB9.signature",
        "userProfile": {
            "id": 1,
            "name": "John Doe",
            "email": "john@example.com",
            "role": "EMPLOYEE",
            "department": "Engineering"
        }
    },
    "timestamp": "2024-04-19T10:30:00"
}
```

**Signup Error Response (409 CONFLICT - Email exists):**
```json
{
    "success": false,
    "message": "Email already registered",
    "data": null,
    "timestamp": "2024-04-19T10:30:00"
}
```

**Interview explanation:**
> "I use @RestController because I'm building REST APIs that return JSON. @PostMapping("/login") routes POST requests to /api/auth/login to this method. @Valid validates the request data before the method runs. If email format is wrong or password is missing, Spring returns 400 automatically. I return ResponseEntity with appropriate HTTP status and response body wrapped in ApiResponse for consistency."

---

## LeaveRequestController - Complete Explanation

```java
@RestController
@RequestMapping("/leave-requests")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class LeaveRequestController {

    private final ILeaveRequestService leaveRequestService;
    private final UserRepository userRepository;

    /**
     * Create leave request
     * POST /api/leave-requests
     * Only EMPLOYEE can call
     */
    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    // Only users with EMPLOYEE role can call
    // Returns 403 FORBIDDEN if MANAGER tries
    
    public ResponseEntity<ApiResponse<LeaveRequestDto>> createLeaveRequest(
            @Valid @RequestBody LeaveRequestDto leaveRequestDto) {
        
        LeaveRequestDto response = leaveRequestService.createLeaveRequest(leaveRequestDto);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Leave request created successfully"));
    }

    /**
     * Get specific leave request
     * GET /api/leave-requests/5
     * Both MANAGER and EMPLOYEE can call
     */
    @GetMapping("/{id}")
    // Path variable: {id} extracts from URL
    // Example: /api/leave-requests/5 → id=5
    
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE')")
    // Both MANAGER and EMPLOYEE can access
    
    public ResponseEntity<ApiResponse<LeaveRequestDto>> getLeaveRequest(
            @PathVariable Long id
            // Extract from URL path
    ) {
        LeaveRequestDto response = leaveRequestService.getLeaveRequestById(id);
        return ResponseEntity.ok(ApiResponse.success(response, "Leave request retrieved"));
    }

    /**
     * Get all leave requests with pagination and filtering
     * GET /api/leave-requests?status=PENDING&page=0&size=10
     * Both MANAGER and EMPLOYEE can call
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE')")
    
    public ResponseEntity<ApiResponse<Page<LeaveRequestDto>>> getAllLeaveRequests(
            @RequestParam(required = false) String status,
            // Optional query parameter: ?status=PENDING
            // If not provided, returns all statuses
            
            @RequestParam(required = false) Long employeeId,
            // Optional: Filter by specific employee
            
            @PageableDefault(
                size = 10,                              // 10 items per page
                sort = "createdAt",                     // Sort by createdAt
                direction = Sort.Direction.DESC        // Newest first
            )
            Pageable pageable
            // Pagination info: page number, size, sort
            // Supplied by Spring automatically from query string
    ) {

        Page<LeaveRequestDto> response;

        if (employeeId != null) {
            // If employeeId provided, get only that employee's leaves
            response = leaveRequestService.getLeaveRequestsByEmployee(employeeId, pageable);
        } else {
            // Otherwise, get all leaves (optionally filtered by status)
            response = leaveRequestService.getAllLeaveRequests(status, pageable);
        }

        return ResponseEntity.ok(ApiResponse.success(response, "Leave requests retrieved"));
    }

    /**
     * Approve leave request
     * PATCH /api/leave-requests/5/approve
     * Only MANAGER can call
     */
    @PatchMapping("/{id}/approve")
    // PATCH = Partial update
    // Updating just the status, not entire object
    
    @PreAuthorize("hasRole('MANAGER')")
    // Only MANAGER can approve leaves
    
    public ResponseEntity<ApiResponse<LeaveRequestDto>> approveLeaveRequest(
            @PathVariable Long id,
            @Valid @RequestBody LeaveApprovalDto approvalDto) {

        Long managerId = getCurrentUserId();
        // Get currently logged-in user ID from SecurityContext
        
        LeaveRequestDto response = leaveRequestService.approveLeaveRequest(
            id, 
            approvalDto, 
            managerId
        );
        
        return ResponseEntity.ok(ApiResponse.success(response, "Leave approved"));
    }

    /**
     * Reject leave request
     * PATCH /api/leave-requests/5/reject
     * Only MANAGER can call
     */
    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasRole('MANAGER')")
    
    public ResponseEntity<ApiResponse<LeaveRequestDto>> rejectLeaveRequest(
            @PathVariable Long id,
            @Valid @RequestBody LeaveApprovalDto approvalDto) {

        Long managerId = getCurrentUserId();
        LeaveRequestDto response = leaveRequestService.rejectLeaveRequest(
            id, 
            approvalDto, 
            managerId
        );
        
        return ResponseEntity.ok(ApiResponse.success(response, "Leave rejected"));
    }

    /**
     * Helper method - Get current logged-in user ID
     */
    private Long getCurrentUserId() {
        // Get authentication from SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Cast to UserDetails to get user ID
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        // Load full user object to get ID
        return userRepository.findByEmail(userDetails.getUsername()).getId();
    }
}
```

**API Usage Examples:**

```http
# Create leave request
POST /api/leave-requests
Authorization: Bearer eyJhbGc...
{
    "employeeId": 1,
    "startDate": "2024-12-20",
    "endDate": "2024-12-22",
    "reason": "Family vacation"
}
Response: 201 CREATED

# Get all leave requests (paginated)
GET /api/leave-requests?status=PENDING&page=0&size=10
Authorization: Bearer eyJhbGc...
Response: 200 OK with Page<LeaveRequestDto>

# Get specific leave request
GET /api/leave-requests/5
Authorization: Bearer eyJhbGc...
Response: 200 OK with LeaveRequestDto

# Approve leave request
PATCH /api/leave-requests/5/approve
Authorization: Bearer eyJhbGc...
{
    "comment": "Approved"
}
Response: 200 OK

# Reject leave request
PATCH /api/leave-requests/5/reject
Authorization: Bearer eyJhbGc...
{
    "comment": "Insufficient documentation"
}
Response: 200 OK

# Get user's leave requests
GET /api/leave-requests?employeeId=1
Authorization: Bearer eyJhbGc...
Response: 200 OK with Page<LeaveRequestDto>
```

**HTTP Status Codes:**
```
200 OK                  - Request successful, data returned
201 CREATED             - Resource successfully created
400 BAD REQUEST         - Invalid input data
401 UNAUTHORIZED        - Not authenticated (no token)
403 FORBIDDEN           - Authenticated but not authorized (wrong role)
404 NOT FOUND           - Resource doesn't exist
409 CONFLICT            - Email already exists (signup conflict)
500 INTERNAL ERROR      - Server error
```

**Interview explanation:**
> "@RestController makes this a REST endpoint handler. Each method maps to a specific HTTP endpoint. @PathVariable extracts from URL like /api/leave-requests/{id} where id is extracted. @RequestParam extracts from query string like ?status=PENDING. @PreAuthorize enforces role-based access - only MANAGER can approve leaves. Pagination with Pageable automatically handles page number, size, and sorting."

---

---

# SERVICES & BUSINESS LOGIC

## AuthServiceImpl - Complete Explanation

```java
package com.leavemgmt.service.impl;

import com.leavemgmt.dto.LoginRequest;
import com.leavemgmt.dto.LoginResponse;
import com.leavemgmt.dto.SignupRequest;
import com.leavemgmt.dto.UserProfile;
import com.leavemgmt.entity.LeaveBalance;
import com.leavemgmt.entity.User;
import com.leavemgmt.entity.enums.UserRole;
import com.leavemgmt.exception.*;
import com.leavemgmt.repository.LeaveBalanceRepository;
import com.leavemgmt.repository.UserRepository;
import com.leavemgmt.security.JwtTokenProvider;
import com.leavemgmt.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

@Service
// This class contains business logic for authentication
// Spring manages as singleton bean

@RequiredArgsConstructor
// Lombok generates constructor with all final fields
// Constructor injection for dependencies

@Transactional
// Wraps all methods in database transaction
// If exception thrown, automatic rollback
// If success, automatic commit

public class AuthServiceImpl implements IAuthService {
    // Implements IAuthService interface

    private final UserRepository userRepository;
    // Injected by Spring
    
    private final LeaveBalanceRepository leaveBalanceRepository;
    // Injected by Spring
    
    private final JwtTokenProvider jwtTokenProvider;
    // Injected by Spring
    
    private final PasswordEncoder passwordEncoder;
    // Injected by Spring (created in SecurityConfig)

    /**
     * Login - Authenticate user and return JWT token
     * Called by AuthController
     */
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // Step 1: Find user by email and role
        User user = userRepository.findByEmailAndRole(
                loginRequest.getEmail(),
                UserRole.valueOf(loginRequest.getRole())
        ).orElseThrow(
            () -> new UserNotFoundException("Invalid email or password")
        );
        // If user not found, throw exception with generic message
        // (don't reveal if email exists)

        // Step 2: Verify password
        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPasswordHash()
        )) {
            // matches() compares plain password with BCrypt hash
            // Returns true if same, false if different
            throw new UnauthorizedException("Invalid email or password");
        }

        // Step 3: Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getEmail());

        // Step 4: Create response with token and user profile
        UserProfile userProfile = mapUserToProfile(user);
        // Map User entity to UserProfile DTO
        // DTO doesn't include password_hash (security)

        return new LoginResponse(token, userProfile);
        // Return token + profile to client
    }

    /**
     * Signup - Register new user
     * Initialize leave balance for new user
     * Called by AuthController
     */
    @Override
    public LoginResponse signup(SignupRequest signupRequest) {
        // Step 1: Check if email already exists
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new DuplicateEmailException("Email already registered");
            // Prevent duplicate registrations
        }

        // Step 2: Create new User entity
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPasswordHash(
            passwordEncoder.encode(signupRequest.getPassword())
        );
        // encode() hashes the plain password using BCrypt
        // Never store plain passwords!
        
        user.setRole(UserRole.valueOf(signupRequest.getRole()));
        user.setDepartment(signupRequest.getDepartment());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Step 3: Save user to database
        User savedUser = userRepository.save(user);
        // JPA generates SQL INSERT automatically

        // Step 4: Initialize leave balance for user
        LeaveBalance leaveBalance = new LeaveBalance();
        leaveBalance.setUser(savedUser);
        leaveBalance.setBalance(new BigDecimal("18"));
        // 18 days of leave per year (company policy)
        
        leaveBalance.setYear(Year.now().getValue());
        leaveBalance.setCreatedAt(LocalDateTime.now());
        leaveBalance.setUpdatedAt(LocalDateTime.now());

        leaveBalanceRepository.save(leaveBalance);
        // JPA generates SQL INSERT automatically

        // Step 5: Generate JWT token
        String token = jwtTokenProvider.generateToken(savedUser.getEmail());

        // Step 6: Create response
        UserProfile userProfile = mapUserToProfile(savedUser);
        return new LoginResponse(token, userProfile);
    }

    /**
     * Helper method - Map User entity to UserProfile DTO
     * DTOs are used to control what data is sent to client
     */
    private UserProfile mapUserToProfile(User user) {
        UserProfile profile = new UserProfile();
        profile.setId(user.getId());
        profile.setName(user.getName());
        profile.setEmail(user.getEmail());
        profile.setRole(user.getRole().name());  // EMPLOYEE, MANAGER
        profile.setDepartment(user.getDepartment());
        return profile;
        // Password is NOT included in DTO (security)
    }
}
```

**Why this structure?**

```
AuthController.login()
    ↓ calls
AuthServiceImpl.login()
    ↓ calls
UserRepository.findByEmailAndRole()
    ↓ queries database
    ↓ returns User entity
    ↓
JwtTokenProvider.generateToken()
    ↓ generates JWT
    ↓
Return LoginResponse to controller
    ↓
Controller returns to client
```

**Interview explanation:**
> "The service layer contains business logic separated from HTTP concerns. I use @Service to mark this as service layer. @Transactional wraps all methods in database transactions - if exception occurs, all changes rollback automatically. In login, I verify email, check password using BCrypt.matches(), generate JWT token. In signup, I check for duplicates, hash password, save user, initialize leave balance, generate token. Using DTOs, I return only needed fields (not password). This separation of concerns makes code maintainable and testable."

---

## LeaveRequestServiceImpl - Complete Explanation

```java
@Service
@RequiredArgsConstructor
@Transactional
public class LeaveRequestServiceImpl implements ILeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;
    private final IAuditService auditService;
    private final ObjectMapper objectMapper;

    /**
     * Create new leave request
     * Called when employee submits leave request
     */
    @Override
    public LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto) {
        // Step 1: Find employee
        User employee = userRepository.findById(leaveRequestDto.getEmployeeId())
                .orElseThrow(() -> new UserNotFoundException("Employee not found"));
        // If employee not found in database, throw exception

        // Step 2: Create new leave request entity
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        leaveRequest.setStartDate(leaveRequestDto.getStartDate());
        leaveRequest.setEndDate(leaveRequestDto.getEndDate());
        leaveRequest.setReason(leaveRequestDto.getReason());
        leaveRequest.setStatus(LeaveRequest.LeaveRequestStatus.PENDING);
        // Automatically set by @PrePersist but explicit is clear
        
        leaveRequest.setCreatedAt(LocalDateTime.now());
        leaveRequest.setUpdatedAt(LocalDateTime.now());

        // Step 3: Save to database
        LeaveRequest savedRequest = leaveRequestRepository.save(leaveRequest);

        // Step 4: Log this action to audit table
        try {
            auditService.logAudit(
                "LeaveRequest",
                savedRequest.getId(),
                "CREATED",
                null,                                    // Old value
                objectMapper.writeValueAsString(savedRequest),  // New value
                employee.getId()
            );
        } catch (Exception e) {
            // Don't fail if audit fails, just log it
        }

        // Step 5: Return DTO to controller
        return mapLeaveRequestToDto(savedRequest);
    }

    /**
     * Get leave request by ID
     * Called when viewing specific leave request
     */
    @Override
    @Transactional(readOnly = true)
    // readOnly = true = Only SELECT queries allowed
    // Spring optimizes for read-only (no locks, etc.)
    public LeaveRequestDto getLeaveRequestById(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new CustomException("Leave request not found"));
        return mapLeaveRequestToDto(leaveRequest);
    }

    /**
     * Get all leave requests for a specific employee
     * Called when employee views their leaves
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LeaveRequestDto> getLeaveRequestsByEmployee(
            Long employeeId, 
            Pageable pageable) {
        
        // findByEmployeeId returns Page<LeaveRequest>
        // .map() transforms each LeaveRequest to LeaveRequestDto
        return leaveRequestRepository.findByEmployeeId(employeeId, pageable)
                .map(this::mapLeaveRequestToDto);
    }

    /**
     * Get all leave requests (optionally filtered by status)
     * Called when manager views all leaves to approve
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LeaveRequestDto> getAllLeaveRequests(String status, Pageable pageable) {
        if (status != null && !status.isEmpty()) {
            // Filter by status if provided
            return leaveRequestRepository.findByStatus(
                    LeaveRequest.LeaveRequestStatus.valueOf(status),
                    pageable
            ).map(this::mapLeaveRequestToDto);
        }
        // No filter, return all
        return leaveRequestRepository.findAll(pageable)
                .map(this::mapLeaveRequestToDto);
    }

    /**
     * Approve leave request
     * Called when manager clicks approve button
     */
    @Override
    public LeaveRequestDto approveLeaveRequest(
            Long id, 
            LeaveApprovalDto approvalDto, 
            Long managerId) {
        
        // Step 1: Find leave request
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new CustomException("Leave request not found"));

        // Step 2: Verify it's in PENDING status
        if (leaveRequest.getStatus() != LeaveRequest.LeaveRequestStatus.PENDING) {
            throw new CustomException("Can only approve pending requests");
            // Can't approve already approved or rejected
        }

        // Step 3: Load manager
        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new UserNotFoundException("Manager not found"));

        // Step 4: Update leave request
        leaveRequest.setManager(manager);
        leaveRequest.setStatus(LeaveRequest.LeaveRequestStatus.APPROVED);
        leaveRequest.setApprovalComment(approvalDto.getComment());

        LeaveRequest updatedRequest = leaveRequestRepository.save(leaveRequest);

        // Step 5: Log the approval
        try {
            auditService.logAudit(
                "LeaveRequest",
                updatedRequest.getId(),
                "APPROVED",
                null,
                objectMapper.writeValueAsString(updatedRequest),
                managerId
            );
        } catch (Exception e) {
            // Don't fail audit, just log
        }

        return mapLeaveRequestToDto(updatedRequest);
    }

    /**
     * Helper - Map LeaveRequest entity to LeaveRequestDto
     */
    private LeaveRequestDto mapLeaveRequestToDto(LeaveRequest leaveRequest) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setId(leaveRequest.getId());
        dto.setEmployeeId(leaveRequest.getEmployee().getId());
        dto.setEmployeeName(leaveRequest.getEmployee().getName());
        dto.setManagerId(leaveRequest.getManager() != null ? 
            leaveRequest.getManager().getId() : null);
        dto.setStartDate(leaveRequest.getStartDate());
        dto.setEndDate(leaveRequest.getEndDate());
        dto.setReason(leaveRequest.getReason());
        dto.setStatus(leaveRequest.getStatus().name());
        dto.setApprovalComment(leaveRequest.getApprovalComment());
        return dto;
    }
}
```

**Interview explanation:**
> "Service layer contains business logic and is transaction-aware. @Transactional wraps methods in transactions - if exception occurs, all changes rollback. readOnly=true optimizes read-only queries. When creating leave request, I audit the action. When approving, I verify status is PENDING before updating. I use DTOs to control what data is returned to client. This separation of concerns - controller handles HTTP, service handles business logic, repository handles database - makes code testable and maintainable."

---

---

# REPOSITORIES & DATABASE ACCESS

## Repository Pattern - Complete Explanation

```java
@Repository
// Spring-managed repository component
// Handles all database access for User entity
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository<Entity, PrimaryKeyType>
    
    // JpaRepository provides automatically:
    // - save()
    // - findById()
    // - findAll()
    // - delete()
    // - count()
    // And many more...

    /**
     * Custom query - Find user by email and role
     * Spring Data JPA generates SQL automatically
     * SELECT * FROM users WHERE email = ? AND role = ?
     */
    Optional<User> findByEmailAndRole(String email, UserRole role);
    // Optional = Value might not exist
    // If found, returns user wrapped in Optional
    // If not found, returns Optional.empty()

    /**
     * Check if email exists
     * Spring generates: SELECT COUNT(*) FROM users WHERE email = ?
     */
    boolean existsByEmail(String email);

    /**
     * Find user by email
     * SELECT * FROM users WHERE email = ?
     */
    Optional<User> findByEmail(String email);
}

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    
    /**
     * Find all leave requests for specific employee
     * Supports pagination: page number, size, sort
     * SELECT * FROM leave_requests WHERE employee_id = ? LIMIT ? OFFSET ?
     */
    Page<LeaveRequestDto> findByEmployeeId(Long employeeId, Pageable pageable);

    /**
     * Find leave requests by status
     * SELECT * FROM leave_requests WHERE status = ?
     */
    Page<LeaveRequest> findByStatus(
        LeaveRequest.LeaveRequestStatus status, 
        Pageable pageable
    );
}
```

**Why Repository Pattern?**

```
Without Repository Pattern (BAD):
┌─────────────────┐
│   Controller    │
└────────┬────────┘
         │ calls SQL directly (JDBC)
         ↓
┌─────────────────┐
│  SQL Queries    │  Hard-coded SQL (tight coupling)
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│   Database      │
└─────────────────┘

With Repository Pattern (GOOD):
┌─────────────────┐
│   Controller    │
└────────┬────────┘
         │ uses repository interface (abstraction)
         ↓
┌─────────────────┐
│   Repository    │  Abstracts database access
│   Interface     │  (loose coupling)
└────────┬────────┘
         │ JPA implements (generates SQL)
         ↓
┌─────────────────┐
│   JPA Layer     │  Auto-generates SQL
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│   Database      │
└─────────────────┘
```

**Benefits:**
1. **Loose coupling** - Controller doesn't know SQL
2. **Testability** - Can mock repository in unit tests
3. **Auto-generated SQL** - No manual SQL writing
4. **Consistency** - All queries use same interface
5. **Type safety** - SQL errors caught at compile time

**Interview explanation:**
> "I use the Repository pattern to abstract database access. Instead of controllers writing SQL queries, repositories handle all database operations. Spring Data JPA automatically generates SQL based on method names. For example, findByEmailAndRole() automatically generates 'SELECT * FROM users WHERE email = ? AND role = ?'. This provides loose coupling, testability, and reduces boilerplate."

---

---

# EXCEPTION HANDLING

## Custom Exceptions

```java
// Base exception
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
    
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}

// Specific exceptions
public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

public class DuplicateEmailException extends CustomException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
```

## GlobalExceptionHandler

```java
@RestControllerAdvice
// Apply exception handlers globally to ALL controllers
@Slf4j
// Lombok: Auto-generates logger field
public class GlobalExceptionHandler {

    /**
     * Handle UserNotFoundException
     * Returns 404 NOT FOUND
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUserNotFoundException(
            UserNotFoundException e) {
        log.error("User not found: {}", e.getMessage());
        // Log error for debugging
        
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                // 404 status code
                .body(ApiResponse.error(e.getMessage()));
    }

    /**
     * Handle DuplicateEmailException
     * Returns 409 CONFLICT
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse<?>> handleDuplicateEmailException(
            DuplicateEmailException e) {
        log.error("Duplicate email: {}", e.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                // 409 status code
                .body(ApiResponse.error(e.getMessage()));
    }

    /**
     * Handle UnauthorizedException
     * Returns 401 UNAUTHORIZED
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(
            UnauthorizedException e) {
        log.error("Unauthorized access: {}", e.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                // 401 status code
                .body(ApiResponse.error(e.getMessage()));
    }

    /**
     * Handle validation errors
     * Returns 400 BAD REQUEST with field-level errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(
            MethodArgumentNotValidException e) {
        log.error("Validation failed");
        
        Map<String, String> errors = new HashMap<>();
        // Collect all field validation errors
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                // 400 status code
                .body(new ApiResponse<>(false, "Validation failed", errors));
    }

    /**
     * Catch-all for any unexpected exception
     * Returns 500 INTERNAL SERVER ERROR
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception e) {
        log.error("Unexpected error: ", e);
        // Log full stack trace for debugging
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                // 500 status code
                .body(ApiResponse.error("An unexpected error occurred. Please try again later."));
        // Generic message (don't expose internal details)
    }
}
```

**Exception Response Examples:**

```json
// 400 BAD REQUEST - Validation error
{
    "success": false,
    "message": "Validation failed",
    "data": {
        "email": "Email should be valid",
        "password": "Size must be between 6 and 20"
    }
}

// 401 UNAUTHORIZED
{
    "success": false,
    "message": "Invalid email or password"
}

// 404 NOT FOUND
{
    "success": false,
    "message": "User not found"
}

// 409 CONFLICT
{
    "success": false,
    "message": "Email already registered"
}

// 500 INTERNAL SERVER ERROR
{
    "success": false,
    "message": "An unexpected error occurred. Please try again later."
}
```

**Interview explanation:**
> "I use @RestControllerAdvice for centralized exception handling across all controllers. When any exception is thrown, this handler catches it based on the exception type and returns appropriate HTTP status and error message. @ExceptionHandler(UserNotFoundException.class) specifically handles that exception type. Using exceptions makes error handling declarative and organized. GlobalExceptionHandler ensures consistent error responses throughout the API."

---

---

# COMPLETE REQUEST/RESPONSE FLOW

## Flow Diagram: Login Request

```
┌─────────────────────────────────────────────────────────────┐
│  CLIENT (React/Postman)                                      │
│  POST /api/auth/login                                        │
│  {                                                            │
│    "email": "john@example.com",                              │
│    "password": "password123",                                │
│    "role": "EMPLOYEE"                                        │
│  }                                                            │
└──────────┬────────────────────────────────────────────────────┘
           │ HTTP Request
┌──────────▼────────────────────────────────────────────────────┐
│  SPRING SECURITY                                              │
│  [JwtAuthenticationFilter]                                    │
│  - Check for Authorization header                             │
│  - This is public endpoint (/auth/login)                      │
│  - No JWT needed for login                                    │
│  - Request continues                                          │
└──────────┬────────────────────────────────────────────────────┘
           │
┌──────────▼────────────────────────────────────────────────────┐
│  [AuthController]                                             │
│  @PostMapping("/login")                                       │
│  1. Receives LoginRequest                                     │
│  2. @Valid validates input                                    │
│     - Email not blank                                         │
│     - Email valid format                                      │
│     - Password not blank                                      │
│  3. Calls authService.login(loginRequest)                     │
└──────────┬────────────────────────────────────────────────────┘
           │
┌──────────▼────────────────────────────────────────────────────┐
│  [AuthServiceImpl]                                             │
│  1. Query database: findByEmailAndRole()                       │
│     SELECT * FROM users WHERE email=? AND role=?             │
│  2. If user not found → throw UserNotFoundException           │
│  3. Compare passwords:                                         │
│     passwordEncoder.matches(plainPassword, hash)             │
│  4. If password wrong → throw UnauthorizedException          │
│  5. Generate JWT token:                                       │
│     jwtTokenProvider.generateToken(email)                    │
│     - Creates header, payload, signature                      │
│  6. Map User to UserProfile DTO                              │
│  7. Return LoginResponse(token, userProfile)                 │
└──────────┬────────────────────────────────────────────────────┘
           │
┌──────────▼────────────────────────────────────────────────────┐
│  [GlobalExceptionHandler]                                     │
│  - No exception thrown (success path)                         │
│  - Exception handlers not invoked                             │
└──────────┬────────────────────────────────────────────────────┘
           │
┌──────────▼────────────────────────────────────────────────────┐
│  [AuthController - Response]                                  │
│  ResponseEntity.ok(                                           │
│    ApiResponse.success(                                       │
│      loginResponse,                                           │
│      "Login successful"                                       │
│    )                                                          │
│  )                                                            │
│  Status: 200 OK                                               │
└──────────┬────────────────────────────────────────────────────┘
           │ HTTP Response
┌──────────▼────────────────────────────────────────────────────┐
│  CLIENT                                                       │
│  {                                                            │
│    "success": true,                                           │
│    "message": "Login successful",                             │
│    "data": {                                                  │
│      "token": "eyJhbGc...",                                   │
│      "userProfile": {                                         │
│        "id": 1,                                               │
│        "name": "John Doe",                                    │
│        "role": "EMPLOYEE"                                     │
│      }                                                        │
│    }                                                          │
│  }                                                            │
│                                                               │
│  Client stores token in localStorage                          │
│  Client adds to subsequent requests:                          │
│  Authorization: Bearer eyJhbGc...                             │
└───────────────────────────────────────────────────────────────┘
```

## Flow Diagram: Create Leave Request (Authenticated)

```
┌────────────────────────────────────────────────────────────┐
│  CLIENT                                                     │
│  POST /api/leave-requests                                  │
│  Authorization: Bearer eyJhbGc...                           │
│  {                                                          │
│    "employeeId": 1,                                         │
│    "startDate": "2024-12-20",                               │
│    "endDate": "2024-12-22",                                 │
│    "reason": "Vacation"                                     │
│  }                                                          │
└────────────┬─────────────────────────────────────────────────┘
             │ HTTP Request
┌────────────▼─────────────────────────────────────────────────┐
│  [CORS Filter]                                              │
│  Check if origin is allowed                                 │
│  @CrossOrigin(origins = "*")                                │
│  Allow from any domain                                      │
└────────────┬─────────────────────────────────────────────────┘
             │
┌────────────▼─────────────────────────────────────────────────┐
│  [JwtAuthenticationFilter - AUTHENTICATION]                 │
│  1. Extract header: "Authorization: Bearer eyJhbGc..."      │
│  2. Remove "Bearer " prefix                                  │
│  3. Call tokenProvider.validateToken(jwt)                   │
│     - Verify signature hasn't been tampered                  │
│     - Check if expired                                       │
│  4. Call tokenProvider.getEmailFromToken(jwt)               │
│     - Extract email from claims                              │
│  5. Load UserDetails from database (email)                  │
│  6. Create UsernamePasswordAuthenticationToken              │
│  7. Set in SecurityContextHolder                            │
│     - Now request knows who is authenticated                │
└────────────┬─────────────────────────────────────────────────┘
             │
┌────────────▼─────────────────────────────────────────────────┐
│  [LeaveRequestController - AUTHORIZATION]                   │
│  @PreAuthorize("hasRole('EMPLOYEE')")                        │
│  - Check if authenticated user has EMPLOYEE role             │
│  - If user is MANAGER, return 403 FORBIDDEN                  │
│  - If authentication missing, return 401 UNAUTHORIZED        │
│  - If authorized, proceed                                    │
│                                                              │
│  @Valid validation:                                          │
│  - Validate LeaveRequestDto fields                           │
│  - startDate must not be null                                │
│  - endDate must be after startDate                           │
│  - If validation fails, return 400 with errors               │
└────────────┬─────────────────────────────────────────────────┘
             │
┌────────────▼─────────────────────────────────────────────────┐
│  [LeaveRequestServiceImpl]                                    │
│  @Transactional wraps in database transaction                │
│                                                              │
│  1. Load employee from database:                             │
│     userRepository.findById(employeeId)                      │
│     - If not found, throw UserNotFoundException              │
│                                                              │
│  2. Create LeaveRequest entity:                              │
│     - Set all fields from DTO                                │
│     - Set status = PENDING                                   │
│                                                              │
│  3. Save to database:                                        │
│     leaveRequestRepository.save(leaveRequest)                │
│     - Generates SQL INSERT                                   │
│     - @PrePersist runs (sets createdAt, updatedAt)           │
│                                                              │
│  4. Log audit:                                               │
│     auditService.logAudit(...)                               │
│     - Records this action for compliance                     │
│                                                              │
│  5. Map to DTO:                                              │
│     mapLeaveRequestToDto(savedRequest)                       │
│     - Hide internal fields like password                     │
│                                                              │
│  6. Return LeaveRequestDto                                   │
│  (If exception, @Transactional rolls back all changes)       │
└────────────┬─────────────────────────────────────────────────┘
             │
┌────────────▼─────────────────────────────────────────────────┐
│  [GlobalExceptionHandler]                                    │
│  - No exception thrown (success path)                        │
└────────────┬─────────────────────────────────────────────────┘
             │
┌────────────▼─────────────────────────────────────────────────┐
│  [LeaveRequestController - Response]                         │
│  ResponseEntity.status(HttpStatus.CREATED)                  │
│    .body(ApiResponse.success(...))                           │
│  Status: 201 CREATED                                         │
└────────────┬─────────────────────────────────────────────────┘
             │ HTTP Response
┌────────────▼─────────────────────────────────────────────────┐
│  CLIENT                                                      │
│  {                                                           │
│    "success": true,                                          │
│    "message": "Leave request created successfully",          │
│    "data": {                                                 │
│      "id": 5,                                                │
│      "employeeId": 1,                                        │
│      "startDate": "2024-12-20",                              │
│      "endDate": "2024-12-22",                                │
│      "status": "PENDING"                                     │
│    }                                                         │
│  }                                                           │
│                                                              │
│  Leave request saved to database                             │
│  Audit log created                                           │
└────────────────────────────────────────────────────────────┘
```

---

# ANNOTATION QUICK REFERENCE & CHEAT SHEET

## Which Annotation to Use? (Decision Guide)

| I Want to... | Use This | Example |
|--------------|----------|---------|
| **Create a REST API controller** | `@RestController` | Handles HTTP requests, returns JSON |
| **Create business logic class** | `@Service` | Implement IAuthService |
| **Create data access class** | `@Repository` | Extend JpaRepository |
| **Map Java class to database table** | `@Entity` | User, LeaveRequest, etc. |
| **Make a field the primary key** | `@Id` + `@GeneratedValue` | user_id: 1, 2, 3... |
| **Define column properties** | `@Column` | @Column(nullable=false, unique=true) |
| **Store enum in database** | `@Enumerated` | UserRole.EMPLOYEE → "EMPLOYEE" |
| **Create one-to-many relationship** | `@OneToMany` | One user, many leave requests |
| **Create many-to-one relationship** | `@ManyToOne` | Many requests, one employee |
| **Validate input data** | `@Valid` + `@NotBlank @Email etc` | Validate LoginRequest before processing |
| **Auto-inject dependencies** | `@Autowired` or constructor | `private final AuthService authService;` |
| **Generate constructor** | `@RequiredArgsConstructor` | Lombok - creates constructor with finals |
| **Check user permissions** | `@PreAuthorize` | `@PreAuthorize("hasRole('MANAGER')")` |
| **Mark security config** | `@EnableWebSecurity` | SecurityConfig class |
| **Define bean programmatically** | `@Bean` in `@Configuration` | Create PasswordEncoder bean |
| **Auto-set timestamp before insert** | `@PrePersist` | Set createdAt automatically |
| **Auto-set timestamp before update** | `@PreUpdate` | Update updatedAt automatically |
| **Mark method override** | `@Override` | Implement interface method |

---

## Annotation by Layer (Where do they go?)

### PRESENTATION LAYER (Controllers)
```java
@RestController                    // Class-level
@RequestMapping("/api/auth")       // Class-level
public class AuthController {
    
    @PostMapping("/login")         // Method-level
    @PreAuthorize("hasRole('EMPLOYEE')")  // Method-level (optional)
    public LoginResponse login(
        @Valid @RequestBody        // Parameter-level
        LoginRequest request) {
        ...
    }
}
```

### BUSINESS LOGIC LAYER (Services)
```java
@Service                           // Class-level
public class AuthServiceImpl implements IAuthService {
    
    private final UserRepository userRepository;  // Injected
    
    @Override                      // Method-level
    public LoginResponse login(LoginRequest request) {
        ...
    }
}
```

### DATA ACCESS LAYER (Repositories)
```java
@Repository                        // Interface-level
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);  // No annotations needed
}
```

### ENTITY LAYER (Database Models)
```java
@Entity                            // Class-level
@Table(name = "users")            // Class-level
public class User {
    
    @Id                            // Field-level
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Field-level
    private Long id;
    
    @Column(nullable = false, unique = true)  // Field-level
    private String email;
    
    @Enumerated(EnumType.STRING)   // Field-level
    private UserRole role;
    
    @ManyToOne(fetch = FetchType.LAZY)  // Field-level
    @JoinColumn(name = "manager_id")    // Field-level
    private User manager;
    
    @PrePersist                    // Method-level
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate                     // Method-level
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
```

---

## Most Important Annotations to Remember

### Top 5 Annotations You'll Use Most:

1. **@RestController** - Your API endpoints go here
2. **@Service** - Your business logic goes here
3. **@Repository** - Your database queries go here
4. **@Entity** - Your database tables go here
5. **@Autowired / Constructor Injection** - How you get dependencies

### Why These Matter:
- Together they form the **layered architecture**
- Each has ONE specific job
- Clear separation makes code maintainable
- Easy to test
- Follows SOLID principles

---

# DESIGN PATTERNS

## 1. MVC Pattern (Model-View-Controller)

```
MODEL                      VIEW                         CONTROLLER
(Data)                  (Presentation)              (Logic/Routing)

Entity                  JSON Response               @RestController
LeaveRequest          {                           LeaveRequestController
User                    "id": 5,                  
LeaveBalance            "status": "PENDING"       @GetMapping("/{id}")
                      }                           @PostMapping
                                                  @PatchMapping
                                                  @PreAuthorize
```

## 2. Service Pattern (Business Logic Abstraction)

```
Controller (HTTP Layer)
    ↓
Service Interface (Abstraction)
    ↓
Service Implementation (Business Logic)
    ↓
Repository (Data Access)
    ↓
Database
```

**Benefit:** Easy to test, change implementation, swap repositories.

## 3. Repository Pattern (Data Access Abstraction)

```
Controller calls:
  userRepository.findByEmail()

Repository generates SQL:
  SELECT * FROM users WHERE email = ?

Database returns result
```

**Benefit:** Decouple from database, easy to mock in tests.

## 4. Dependency Injection (Inversion of Control)

```
// Without DI (BAD - tight coupling)
public class AuthController {
    private AuthService authService = new AuthServiceImpl();
    // Explicitly creates dependency
    // Hard to test (can't inject mock)
}

// With DI (GOOD - loose coupling)
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    // Spring injects (via constructor)
    // Easy to test (pass mock in constructor)
}
```

## 5. DTO Pattern (Data Transfer Object)

```
Entity (from database):
User {
    id, name, email, passwordHash, createdAt, updatedAt
}

DTO (to client):
UserProfile {
    id, name, email, role, department
    // Password NOT included (security)
    // Only needed fields sent
}
```

## 6. Builder Pattern (in Jwt)

```java
return Jwts.builder()
        .subject(email)
        .issuedAt(now)
        .expiration(expiryDate)
        .signWith(secretKey)
        .compact();
        
// Method chaining for fluent API
// Better than: new Token(email, now, expiryDate, secretKey)
```

## 7. Strategy Pattern (Authentication)

```
Different authentication strategies:
- JwtAuthenticationFilter (used here)
- Basic Authentication
- OAuth2
- SAML

Swappable implementations!
```

---

---

# INTERVIEW Q&A

## Core Concept Questions

### Q1: Why use Spring Boot instead of vanilla Spring Framework?

**Answer:**
> "Spring Boot eliminates boilerplate configuration. In vanilla Spring, you manually configure beans in XML/Java. Spring Boot auto-configures based on classpath JARs present. For example, if spring-boot-starter-web is on classpath, it auto-configures DispatcherServlet, view resolvers, etc. It's like going from 1000 lines of configuration to annotations. Also, Spring Boot provides embedded Tomcat, so no need to deploy WAR files to external servers."

---

### Q2: What is @SpringBootApplication and why is it needed?

**Answer:**
> "@SpringBootApplication is a shortcut combining @Configuration, @ComponentScan, and @EnableAutoConfiguration. Without this on main class, Spring doesn't know where to start the application. It's the entry point. @Configuration marks class as bean definitions, @ComponentScan scans packages for @Component/@Service/@Controller, @EnableAutoConfiguration configures Spring based on JARs."

---

### Q3: Explain the difference between @RestController and @Controller.

**Answer:**
> "@Controller is for MVC - returns view names (String) that get resolved to HTML pages. @RestController combines @Controller + @ResponseBody - every method return value is serialized to JSON. We use @RestController for REST APIs. Without @ResponseBody, you need to add it to every method, making code repetitive."

---

### Q4: Why do you use interfaces for services?

**Answer:**
> "Interfaces provide contracts - define what methods must be implemented. Benefits: 1) Loose coupling - depends on abstraction not implementation, 2) Easy testing - can create mock implementations, 3) Multiple implementations - can have different implementations of same interface, 4) Follows SOLID (Dependency Inversion)."

---

### Q5: What is dependency injection and why use it?

**Answer:**
> "Dependency injection means objects receive dependencies from outside (Spring) instead of creating them. Using @RequiredArgsConstructor and final fields, Spring injects dependencies via constructor. Benefits: 1) Testability - pass mock dependencies in tests, 2) Loose coupling - code doesn't know how to create dependencies, 3) Flexibility - easy to swap implementations, 4) Cleaner code - no manual instantiation."

---

### Q6: Why use @Transactional?

**Answer:**
> "@Transactional wraps method in database transaction. Either ALL database operations succeed and commit, or ALL rollback (none execute). This ensures data consistency. If exception occurs mid-method, Spring automatically rolls back all changes. Without this, you could have partial updates leaving database in inconsistent state."

---

### Q7: Explain JWT authentication flow.

**Answer:**
> "JWT authentication is stateless - no server-side session storage. Flow: 1) User login, server generates JWT token (contains email, issued time, expiration, signature), 2) Client stores token in localStorage, 3) Client sends token in Authorization header with subsequent requests, 4) JwtAuthenticationFilter extracts and validates token, 5) If valid, user authenticated. This is more scalable than sessions because no server state needed."

---

### Q8: Why sign JWT tokens?

**Answer:**
> "Signing ensures token integrity. We sign using HMAC-SHA256 with secret key. If someone modifies the payload, signature becomes invalid. When validating later, we recalculate signature and compare. If different, token is rejected. This prevents tampering - someone can't change claims without knowing secret key."

---

### Q9: Why use @PreAuthorize instead of checking roles manually in code?

**Answer:**
> "@PreAuthorize is declarative - permissions are visible in code (annotation on method). Advantages: 1) Cleaner code - no if/else checking roles, 2) Centralized - see all permissions at glance, 3) Automatic - Spring handles before method execution, 4) Consistent - no missed authorization checks. If @PreAuthorize check fails, Spring returns 403 Forbidden automatically."

---

### Q10: What happens if user tries to access endpoint they're not authorized for?

**Answer:**
> "Spring Security checks @PreAuthorize before controller method runs. If user's role doesn't match, Spring returns HTTP 403 FORBIDDEN with error message. The controller method never executes. This is layer-based security - checked in security layer, not application layer."

---

## Architecture Questions

### Q11: Explain your project architecture in layers.

**Answer:**
> "I use layered architecture:
> 1. **Presentation Layer** (@RestController) - Handles HTTP requests/responses, routing
> 2. **Business Logic Layer** (@Service) - contains business rules, calculations, workflows
> 3. **Data Access Layer** (@Repository) - interfaces with database, generates queries
> 4. **Entity Layer** (@Entity) - database models/tables
> 
> Benefits: Separation of concerns, easy to test each layer independently, scalability, maintainability."

---

### Q12: Why separate DTOs from entities?

**Answer:**
> "Entities represent database tables - include internal fields like passwordHash, timestamps. DTOs represent data sent to clients - only include needed fields. Advantages: 1) Security - don't expose sensitive fields like password, 2) Flexibility - client sees different data structure than database, 3) Decoupling - database schema changes don't affect API responses, 4) Control - explicitly define what's sent."

---

### Q13: How does pagination work?

**Answer:**
> "Pagination splits large result sets into pages. Using Pageable from Spring: @PageableDefault(size=10, sort="createdAt") creates Pageable with 10 items per page, sorted by createdAt. Client sends ?page=0&size=10&sort=createdAt,desc. Repository returns Page<T> with metadata: total count, current page, more data or not. This reduces database queries - don't load all 1 million records."

---

## Security Questions

### Q14: How do passwords get hacked even if encrypted?

**Answer:**
> "Encryption is reversible (if someone has key, they decrypt). We use hashing (one-way) not encryption. BCrypt is not reversible - can't decrypt to get original password. Attacks: 1) Brute force - try millions of passwords, 2) Dictionary - try common passwords, 3) Rainbow tables - pre-computed hashes. BCrypt mitigates by: 1) Adding salt (random, unique per hash), 2) Iterating multiple times (strength=10), 3) Being computationally slow. Strength=10 means 1024 iterations."

---

### Q15: Why disable CSRF for REST APIs?

**Answer:**
> "CSRF (Cross-Site Request Forgery) is attack where attacker tricks user into making unintended request on another site. CSRF protection uses tokens verified server-side, requires session storage. REST APIs are stateless (no sessions), use JWT tokens CLIENT stores. CSRF attack can't work here because: 1) No session to exploit, 2) Attacker can't get JWT from localStorage (SOP - Same Origin Policy), 3) JWT has signature."

---

### Q16: Why use STATELESS session policy?

**Answer:**
> "STATELESS means don't create server-side HTTP sessions. Traditional: User logs in, server creates session, stores session ID in cookie. Problem: doesn't scale - need to replicate sessions across servers. STATELESS with JWT: No server state needed, just validate token signature. Scalable because any server can validate JWT independently without shared session storage."

---

## Error Handling Questions

### Q17: Why use global exception handler instead of try-catch in every controller?

**Answer:**
> "Global exception handler centralizes error handling. Without it, you repeat try-catch in every controller method. With @RestControllerAdvice, one place handles all exceptions uniformly. Advantages: 1) DRY (Don't Repeat Yourself), 2) Consistent error responses, 3) Automatic HTTP status codes, 4) Proper logging, 5) Security - generic error messages to clients."

---

### Q18: Why throw exceptions instead of returning error codes?

**Answer:**
> "Exceptions are cleaner and more explicit. They stop execution immediately - can't accidentally continue after error. Returning error codes requires checking in every method. Exceptions force you to handle errors up the stack. Also, Spring handles exceptions automatically, converting to HTTP responses. Less code needed."

---

## Database Questions

### Q19: Why use Spring Data JPA instead of writing SQL?

**Answer:**
> "Spring Data JPA abstracts database access. Benefits: 1) No SQL - generates from method names (findByEmail generates SELECT), 2) Type-safe - SQL errors caught at compile time, 3) Database agnostic - switch database without code changes, 4) Less boilerplate - no manual ResultSet handling, 5) Consistency - all repositories use same interface."

---

### Q20: What's the difference between EAGER and LAZY loading?

**Answer:**
> "EAGER loads related data immediately (n+1 query problem). LAZY loads only when accessed. Example: Load 100 LeaveRequests. EAGER loads 100 employees too (101 queries). LAZY loads employees only when accessed. LAZY is usually better for performance. But if you always need data, EAGER is appropriate."

---

## Design Pattern Questions

### Q21: What design patterns do you use?

**Answer:**
> "1) **MVC** - Separate models, views, controllers
> 2) **Repository** - Abstraction for data access
> 3) **Service** - Business logic abstraction
> 4) **DTO** - Data transfer between layers
> 5) **Singleton** - Spring beans are singletons
> 6) **Dependency Injection** - Inversion of Control
> 7) **Builder** - Fluent API for JWT creation
> 8) **Strategy** - Pluggable auth implementations"

---

## Testing Questions

### Q22: How would you test the AuthService?

**Answer:**
> "Mock dependencies: UserRepository, PasswordEncoder, JwtTokenProvider. Test scenarios: 1) Successful login, 2) Invalid email, 3) Invalid password, 4) Successful signup, 5) Duplicate email. Example:
> ```
> @Test
> void testLoginSuccess() {
>   User mockUser = new User();
>   when(userRepository.findByEmailAndRole(...)).thenReturn(Optional.of(mockUser));
>   when(passwordEncoder.matches()).thenReturn(true);
>   
>   LoginResponse response = authService.login(request);
>   
>   assertNotNull(response.getToken());
> }
> ```"

---

## Troubleshooting Questions

### Q23: What happens if JWT token expires while user is using the app?

**Answer:**
> "JwtAuthenticationFilter validates token. If expired, validateToken() returns false. User is not authenticated. Next request needs fresh token. Client must handle this - either: 1) Refresh token endpoint (get new token with refresh token), 2) Re-login, 3) Show error to user. We don't have refresh tokens in this project, so user must login again."

---

### Q24: What if database is slow and queries timeout?

**Answer:**
> "Use QueryTimeoutRegistry or set query timeout. Add pagination - don't load all records. Add indexes on frequently searched columns (email, employee_id). Add caching for read-heavy data. Use readOnly=true for queries to allow Spring optimizations. Consider async processing for heavy operations."

---

---

## SUMMARY OF KEY CONCEPTS - CLEAR EXPLANATIONS

### 1. @SpringBootApplication
**What it does:**
- Marks the main class where your application starts
- Tells Spring Framework: "Start your magic here"

**Real-world analogy:** Like the front door of a house - if you don't mark it, how will visitors know where to enter?

**Why use it:**
- Without this, Spring doesn't know where to begin
- Automatically configures everything Spring needs
- Starts the embedded web server (Tomcat)

**Example:**
```java
@SpringBootApplication
public class LeaveManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeaveManagementApplication.class, args);
        // This starts the entire application
    }
}
```

---

### 2. @RestController
**What it does:**
- Marks a class as an API endpoint handler
- Every method in this class handles HTTP requests
- Automatically returns JSON responses (not HTML pages)

**Real-world analogy:** Like a receptionist at a hotel - receives guests (HTTP requests) and provides information (JSON responses)

**Why use it:**
- Clean syntax for REST APIs
- Automatically serializes Java objects to JSON
- Without this, you'd need @Controller + @ResponseBody on every method

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // Automatically returns JSON: { "id": 1, "name": "John", ... }
        return userService.getUserById(id);
    }
}
```

**Question: "Why @RestController and not @Controller?"**
Answer: "@Controller is for traditional web apps returning HTML. @RestController is for REST APIs returning JSON. @RestController = @Controller + @ResponseBody combined."

---

### 3. @Service
**What it does:**
- Marks a class as containing business logic
- Spring manages this class automatically
- All complex operations go here

**Real-world analogy:** Like the brain of the system - where all thinking and decision-making happens

**Why use it:**
- Separates business logic from HTTP handling
- Makes code organized and maintainable
- Easy to test (can mock it)

**Example:**
```java
@Service
public class UserService {
    public User loginUser(String email, String password) {
        // All business logic here:
        // 1. Find user in database
        // 2. Verify password using BCrypt
        // 3. Generate JWT token
        // 4. Return response
    }
}
```

**Question: "Why not put all logic in Controller?"**
Answer: "If you put logic in Controller, it becomes hard to test. Services can be tested independently. Also, multiple controllers can reuse same service."

---

### 4. @Repository
**What it does:**
- Marks a class responsible for database access
- Automatically generates SQL from method names
- All database operations go through this

**Real-world analogy:** Like a librarian - you ask for a book (data), the librarian finds it in the library (database) and gives it to you

**Why use it:**
- Abstracts database details away from business logic
- Easy to test (can use mock database)
- Can switch from MySQL to PostgreSQL without changing code

**Example:**
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring auto-generates: SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
    
    // Spring auto-generates: SELECT * FROM users WHERE role = ?
    List<User> findByRole(UserRole role);
}
```

**Question: "Why use Repository instead of writing SQL directly?"**
Answer: "Repositories abstract database access. If you hardcode SQL, changing databases is painful. Repositories are database-agnostic."

---

### 5. @Entity
**What it does:**
- Marks a class as a database table
- Java class = Database table
- Automatically creates SQL CREATE TABLE statement

**Real-world analogy:** Like a blueprint for a table in database - defines columns, their types, constraints

**Why use it:**
- Write Java code instead of SQL
- No manual SQL writing errors
- Easier to maintain and evolve

**Example:**
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment primary key
    private Long id;
    
    @Column(name = "email", nullable = false, unique = true)  // NOT NULL, UNIQUE constraint
    private String email;
    
    @Column(length = 100)  // VARCHAR(100)
    private String name;
}

// Spring automatically creates:
// CREATE TABLE users (
//     id BIGINT AUTO_INCREMENT PRIMARY KEY,
//     email VARCHAR(100) NOT NULL UNIQUE,
//     name VARCHAR(100)
// );
```

**Question: "Why use @Entity instead of writing CREATE TABLE manually?"**
Answer: "@Entity is ORM (Object-Relational Mapping). Database changes map automatically to Java objects and vice versa. Less error-prone."

---

### 6. @Transactional
**What it does:**
- Wraps database operations in a transaction
- "All or Nothing" guarantee
- If error occurs, automatically undo all changes

**Real-world analogy:** Like a bank transfer - either money moves from account A to B successfully, or nothing happens. Can't have partial transfer.

**Why use it:**
- Ensures data consistency
- Prevents partial database updates
- ACID guarantees (Atomicity, Consistency, Isolation, Durability)

**Example:**
```java
@Transactional
public void transferMoney(Long fromAccount, Long toAccount, BigDecimal amount) {
    // Step 1: Deduct from account A
    accountRepository.deduct(fromAccount, amount);
    
    // Step 2: Add to account B
    accountRepository.add(toAccount, amount);
    
    // If Step 2 fails, Spring automatically rolls back Step 1
    // Result: No money lost, database stays consistent
}
```

**Question: "What if I don't use @Transactional?"**
Answer: "If Step 1 succeeds but Step 2 fails, Step 1 changes stay in database. Money disappears! That's why @Transactional is critical for data integrity."

---

### 7. @PreAuthorize
**What it does:**
- Checks if user has permission BEFORE method runs
- Enforces access control
- Returns 403 FORBIDDEN if not authorized

**Real-world analogy:** Like a security guard at a bank - checks your ID (role) before letting you enter the vault

**Why use it:**
- Declarative security (visible in code)
- No if/else checking roles inside methods
- Automatic HTTP 403 response if not authorized

**Example:**
```java
@RestController
public class LeaveController {
    
    @PatchMapping("/approve")
    @PreAuthorize("hasRole('MANAGER')")  // Only managers can approve
    public void approveLeave(Long leaveId) {
        // This code runs ONLY if user has MANAGER role
        leaveService.approve(leaveId);
        
        // If user is EMPLOYEE, Spring automatically returns 403 FORBIDDEN
        // This method never executes
    }
}
```

**Question: "Why not check permissions manually inside the method?"**
Answer: "Manual checking is scattered. @PreAuthorize is centralized - you see all permissions at a glance. Also, Spring handles it automatically."

---

### 8. @Valid
**What it does:**
- Validates user input BEFORE method runs
- Checks constraints like email format, required fields, length
- Returns 400 BAD REQUEST if validation fails

**Real-world analogy:** Like checking a form before accepting it - making sure all required fields are filled correctly

**Why use it:**
- Prevents invalid data entering system
- Validation happens automatically (you don't need to code it)
- Returns meaningful error messages to client

**Example:**
```java
public class SignupRequest {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format invalid")
    private String email;
    
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password 6-20 characters")
    private String password;
}

@PostMapping("/signup")
public void signup(@Valid @RequestBody SignupRequest request) {
    // request is validated BEFORE this method is called
    // If email is blank or not valid format → Spring returns 400 with error
    // If method is reached → data is guaranteed valid
}
```

**Question: "What happens if validation fails?"**
Answer: "Spring returns HTTP 400 with error message. Method never executes. Prevents invalid data from entering your code."

---

### 9. @Component
**What it does:**
- Generic marker for any Spring-managed class
- Spring creates and manages instance
- Available for dependency injection

**Real-world analogy:** Like registering an employee in company system - now company manages them, can assign to different departments

**Why use it:**
- Makes class available for dependency injection
- Spring can pass it to any class that needs it
- Cleaner code (no manual instantiation)

**Example:**
```java
// WITHOUT @Component (BAD)
public class EmailService {
    public void sendEmail(String to, String body) {...}
}

public class UserService {
    // Need to manually create:
    private EmailService emailService = new EmailService();  // Hard-coded!
    // Hard to test - can't pass mock
}

// WITH @Component (GOOD)
@Component
public class EmailService {
    public void sendEmail(String to, String body) {...}
}

@Service
public class UserService {
    private final EmailService emailService;  // Spring injects automatically
    
    public UserService(EmailService emailService) {  // Constructor injection
        this.emailService = emailService;  // Can pass mock in tests!
    }
}
```

---

### 10. JWT (JSON Web Token)
**What it does:**
- Creates a signed token containing user info
- Token is stored on CLIENT (not server)
- Stateless authentication

**Real-world analogy:** Like an ID card - you carry it, show it at checkpoints. Government doesn't need to look you up in database each time.

**Why use it:**
- Scalable (no server-side session storage)
- REST-friendly (stateless)
- Works across multiple servers

**How it works:**
```
User Login:
  Client → POST /login with email/password
  Server → Validates, creates JWT token
  Server → Returns token to client
  
Next Request:
  Client → Sends: Authorization: Bearer <token>
  Server → Validates token signature
  Server → No database lookup needed!
  
Token Structure:
  eyJhbGc...  .  eyJzdWI...  .  TJVA95OR...
  (header)       (payload)       (signature)
  
  Payload contains: email, issued-time, expiration
  Signature proves token hasn't been modified
```

**Question: "Why not use sessions like traditional web apps?"**
Answer: "Sessions store data server-side. If you have 10 servers, all must share session database. JWT is stored on client - each server validates independently. Scales better."

---

### 11. BCrypt
**What it does:**
- One-way hashing of passwords
- Same password never produces same hash twice (due to salt)
- Very slow by design (prevents brute force attacks)

**Real-world analogy:** Like burning a piece of paper - can't undo it. Even if someone steals database, they can't get original passwords.

**Why use it:**
- Passwords cannot be decrypted (one-way only)
- Even database breach doesn't expose passwords
- Salt prevents dictionary attacks

**Example:**
```java
// Password hashing
String plainPassword = "MySecurePassword123";
String hash1 = passwordEncoder.encode(plainPassword);
// Result: $2b$10$somehash...  (never same twice due to salt)

String hash2 = passwordEncoder.encode(plainPassword);
// Result: $2b$10$completelydifferent...  (stored differently!)

// Password verification (no decryption!)
if (passwordEncoder.matches("MySecurePassword123", hash1)) {
    // True - password matches
}

if (passwordEncoder.matches("WrongPassword", hash1)) {
    // False - doesn't match
}
```

**Question: "Why not use encryption for passwords?"**
Answer: "Encryption is reversible - if someone gets encryption key, they decrypt all passwords. Hashing is one-way - can't decrypt. BCrypt is perfect for passwords."

---

### 12. Dependency Injection
**What it does:**
- Spring provides objects to your class (instead of creating manually)
- Decouples dependencies
- Makes testing easy

**Real-world analogy:** Like having a personal assistant - instead of doing everything yourself, assistant brings you what you need

**Why use it:**
- Loose coupling (code doesn't depend on specific implementations)
- Easy testing (can pass mock objects)
- Flexible (can swap implementations without changing code)

**Example:**
```java
// WITHOUT Dependency Injection (TIGHT COUPLING - BAD)
@RestController
public class UserController {
    private UserService userService = new UserServiceImpl();  // Hard-coded!
    // If you want to change to UserServiceV2, must edit this class
    // Can't inject mock for testing
}

// WITH Dependency Injection (LOOSE COUPLING - GOOD)
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;  // Spring injects
    
    // Spring calls: new UserController(userServiceImpl_instance)
    // Can pass mock in tests: new UserController(mockUserService)
}
```

**Question: "Why inject instead of instantiating manually?"**
Answer: "Injected code is flexible. If business needs UserServiceV2 instead, change one line in config. Manual instantiation requires changing all files that use it."

---

### 13. DTO (Data Transfer Object)
**What it does:**
- Carries data between layers
- Hides sensitive fields from client
- Decouples database structure from API response

**Real-world analogy:** Like a courier package - only includes what recipient needs, not internal company details

**Why use it:**
- Security (don't send password_hash to client)
- Flexibility (API response structure independent of database)
- Control (decide exactly what data client receives)

**Example:**
```java
// ENTITY (Database structure)
@Entity
public class User {
    private Long id;
    private String name;
    private String email;
    private String passwordHash;  // SECRET! Never send to client
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

// DTO (What client receives)
@Data
public class UserProfile {
    private Long id;
    private String name;
    private String email;
    private String role;
    // passwordHash NOT included (security)
    // createdAt/updatedAt NOT included (unnecessary)
}

// In Controller
@GetMapping("/profile")
public UserProfile getProfile(Long userId) {
    User user = userService.getUserById(userId);
    
    // Map entity to DTO, excluding sensitive fields
    UserProfile dto = new UserProfile();
    dto.setId(user.getId());
    dto.setName(user.getName());
    dto.setEmail(user.getEmail());
    
    return dto;  // passwordHash never reaches client!
}
```

**Question: "Why not just return Entity directly?"**
Answer: "Entity might have passwords, internal IDs, timestamps. Client doesn't need these. DTO gives you control over what's exposed. Also, if database structure changes, API response doesn't have to change."

---

### 14. Layered Architecture
**What it does:**
- Separates code into different layers (presentation, business, data)
- Each layer has specific responsibility
- Layers communicate through interfaces

**Real-world analogy:** Like a restaurant with kitchen, waiters, cashier. Each has specific job. Customers don't go into kitchen.

**Why use it:**
- Maintainability (easy to find code, understand structure)
- Testability (test each layer independently)
- Scalability (can change one layer without affecting others)

**Diagram:**
```
┌─────────────────────────────┐
│  PRESENTATION LAYER         │
│  @RestController            │
│  Handles HTTP requests      │
│  Formats JSON responses     │
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│  BUSINESS LOGIC LAYER       │
│  @Service                   │
│  Complex calculations       │
│  Validations, workflows     │
│  Business rules             │
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│  DATA ACCESS LAYER          │
│  @Repository                │
│  Database queries           │
│  SQL generation             │
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│  DATABASE                   │
│  MySQL tables               │
│  Stores data                │
└─────────────────────────────┘
```

**Question: "Why layers instead of one big class?"**
Answer: "One big class becomes messy. If controller has SQL queries AND business logic AND HTTP handling, it's unmaintainable. Each layer focuses on one thing - easy to understand, test, change."

---

### 15. Repository Pattern
**What it does:**
- Abstracts database operations behind interface
- Repository handles all SQL/queries
- Business logic doesn't know about database

**Real-world analogy:** Like a data warehouse - you request data, warehouse staff fetch it. You don't worry about where it is stored or how.

**Why use it:**
- Switch databases without changing code
- Easy to test (can mock repository)
- Centralized database access logic

**Example:**
```java
// REPOSITORY INTERFACE (Abstraction)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

// BUSINESS LOGIC (Doesn't know about SQL)
@Service
public class AuthService {
    private final UserRepository userRepository;  // Injected abstraction
    
    public void login(String email, String password) {
        User user = userRepository.findByEmail(email);  // No SQL here!
        // Just call the method, repository handles the rest
    }
}

// If you want to change from MySQL to PostgreSQL:
// Just change the repository implementation - AuthService doesn't change!
```

**Question: "Why not have service call database directly?"**
Answer: "If service has SQL, changing database is hard. Repository abstracts SQL. Also, you can mock repository in tests - don't need real database."

---

## QUICK REFERENCE

**When to use what:**

| Situation | Use This |
|-----------|----------|
| "I need to handle HTTP requests" | @RestController |
| "I need business logic" | @Service |
| "I need database access" | @Repository |
| "I need database mapping" | @Entity |
| "I need to wrap in transaction" | @Transactional |
| "I need to check user permission" | @PreAuthorize |
| "I need to validate user input" | @Valid |
| "I need authentication" | JWT + BCrypt |
| "I need to send data to client" | DTO |
| "I need managed object" | @Component |

---

**Remember:**
- @RestController = Where client talks to your API
- @Service = Where thinking happens (business logic)
- @Repository = Where data comes from (database)
- DTO = What client sees (filtered data)
- JWT = How client stays logged in (token)
- BCrypt = How passwords stay secret (hashing)
- Layers = How code stays organized (separation)

---

## MUST-REMEMBER INTERVIEW POINTS

✅ **Always explain WHY** - not just WHAT
✅ **Talk about security** - passwords, JWT, roles
✅ **Mention SOLID principles** - shows maturity
✅ **Discuss scalability** - stateless, pagination, indexes
✅ **Use correct terminology** - Entity, DTO, Repository, Service
✅ **Explain error handling** - exceptions, status codes
✅ **Talk about testing** - how would you test this
✅ **Database optimization** - lazy loading, pagination, indexes
✅ **Code organization** - layered architecture, separation of concerns
✅ **Real-world scenarios** - what happens when X fails

---

**Good luck with your interview! You've got this! 🚀**

