/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * LEAVE MANAGEMENT SYSTEM - COMPLETE DETAILED EXPLANATION
 * ═══════════════════════════════════════════════════════════════════════════════
 * 
 * This file contains comprehensive documentation for interview preparation.
 * Study this file to understand the entire Leave Management System.
 * 
 * Author: Interview Prep Guide
 * Date: April 19, 2026
 * ═══════════════════════════════════════════════════════════════════════════════
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 1: APPLICATION STARTUP (main.ts)
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * APPLICATION BOOTSTRAP EXPLANATION
 * 
 * What is bootstrapApplication?
 * ──────────────────────────────
 * bootstrapApplication() is a FUNCTION that STARTS the entire Angular app
 * It's like turning on a car engine:
 * 1. Check all tools (providers) ✓
 * 2. Load main component (AppComponent) ✓
 * 3. Display it in browser ✓
 * 4. Listen to user actions ✓
 * 5. Update screen when data changes ✓
 * 
 * MAIN.TS CODE BREAKDOWN:
 * ──────────────────────
 * 
 * import { bootstrapApplication } from '@angular/platform-browser';
 * └─ IMPORT = "Bring in a tool from a package"
 * └─ bootstrapApplication = Function that STARTS the entire Angular app
 * └─ @angular/platform-browser = Package that handles browser stuff
 * 
 * import { provideRouter } from '@angular/router';
 * └─ provideRouter = Tool that enables page navigation
 * └─ Example: going from home → login → dashboard (WITHOUT page reload)
 * 
 * import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
 * └─ provideHttpClient = Tool that allows making API calls
 * └─ withInterceptorsFromDi = Adds extra features:
 *    1. Add token to every request (Authorization header)
 *    2. Handle errors automatically
 * 
 * import { importProvidersFrom } from '@angular/core';
 * └─ importProvidersFrom = Function to import multiple tools at once
 * 
 * import { AppComponent } from './app/app.component';
 * └─ AppComponent = The main/root component
 * └─ Like the main container that holds all pages inside
 * 
 * import { routes } from './app/app.routes';
 * └─ routes = Definition of all pages/URLs in the app
 * └─ Example: /login, /dashboard, /apply-leave, etc.
 * 
 * import { FormsModule, ReactiveFormsModule } from '@angular/forms';
 * └─ FormsModule = Tool for simple forms
 * └─ ReactiveFormsModule = Tool for advanced forms (with validation)
 * 
 * import { BrowserModule } from '@angular/platform-browser';
 * └─ BrowserModule = Brings browser capabilities to Angular
 * 
 * 
 * BOOTSTRAP FUNCTION CODE:
 * ──────────────────────
 * 
 * bootstrapApplication(AppComponent, {
 * └─ bootstrapApplication() = FUNCTION that STARTS the app
 * └─ AppComponent = This is the MAIN component (the root)
 * └─ {} = Configuration object with providers
 * 
 *   providers: [
 *   └─ providers: [] = List of all tools/services we want to use
 *   └─ Like a toolbox where we keep all our tools
 *   └─ Every component can use these tools
 * 
 *     provideRouter(routes),
 *     └─ provideRouter(routes) = "Enable routing with these routes"
 *     └─ routes = List of all URLs/pages (/login, /dashboard, etc.)
 *     └─ This lets us navigate from one page to another WITHOUT reloading! ⚡
 * 
 *     provideHttpClient(withInterceptorsFromDi()),
 *     └─ provideHttpClient() = "Enable HTTP calls to backend API"
 *     └─ withInterceptorsFromDi() = Before sending any HTTP request:
 *        1. Add token to request header
 *        2. Handle errors automatically
 *     └─ Like a security checkpoint 🔐
 * 
 *     importProvidersFrom(
 *     └─ importProvidersFrom() = Import multiple modules at once
 *     └─ Like importing multiple tools together
 * 
 *       BrowserModule,
 *       └─ BrowserModule = Enables browser features
 *       └─ Working with HTML elements, events, etc.
 * 
 *       FormsModule,
 *       └─ FormsModule = Enables simple form creation
 * 
 *       ReactiveFormsModule
 *       └─ ReactiveFormsModule = Enables advanced form validation
 *       └─ Email format validation, password matching, etc.
 *     )
 *   ]
 * }).catch(err => console.error(err));
 * └─ .catch() = If something goes wrong, show error in console
 * └─ Like a safety net - if app fails to start, show error
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 2: ROUTING SYSTEM (app.routes.ts)
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * WHAT ARE ROUTES?
 * ────────────────
 * Routes are a MAP of all PAGES in your app
 * Think of it like a restaurant menu:
 * - Path: '/login'                    → What shows in URL bar
 * - component: LoginComponent          → Which page/component to display
 * - canActivate: [AuthGuard]          → Security check
 * 
 * 
 * ROUTING FLOW DIAGRAM:
 * ────────────────────
 * 
 * Route 1: HOME PAGE
 * {
 *   path: '',  ← EMPTY path means DEFAULT/HOME
 *              ← When user goes to http://localhost:4200/ → Shows this
 *   component: HomeComponent,  ← Display this component
 *   canActivate: []  ← No guards needed (public page, anyone can see)
 * }
 * 
 * 
 * Route 2: LOGIN PAGE
 * {
 *   path: 'login',  ← When user goes to http://localhost:4200/login
 *   component: LoginComponent,
 *   canActivate: []  ← Public - no login needed to see login page!
 * }
 * 
 * 
 * Route 3: SIGNUP PAGE
 * {
 *   path: 'signup',
 *   component: SignupComponent,
 *   canActivate: []  ← Public
 * }
 * 
 * 
 * Route 4: DASHBOARD (PROTECTED WITH GUARD)
 * {
 *   path: 'dashboard',
 *   component: DashboardComponent,
 *   canActivate: [AuthGuard]
 *   └─ canActivate: [AuthGuard] = "Before showing this page, run AuthGuard"
 *   └─ AuthGuard checks: "Is user logged in?"
 *   └─ If YES → Show dashboard ✓
 *   └─ If NO → Redirect to login ✗
 * }
 * 
 * 
 * Route 5: APPLY LEAVE
 * {
 *   path: 'apply-leave',
 *   component: ApplyLeaveComponent,
 *   canActivate: [AuthGuard]  ← Protected - must be logged in
 * }
 * 
 * 
 * WHAT IS canActivate?
 * ───────────────────
 * canActivate = "Guards" - security checkpoints
 * Like a bouncer at a nightclub:
 * "Do you have a ticket (token)?" → Yes? Come in! No? Go away!
 * 
 * 
 * HOW NAVIGATION WORKS:
 * ────────────────────
 * User clicks: <a routerLink="/dashboard">Go to Dashboard</a>
 *     ↓
 * Angular sees routerLink="/dashboard"
 *     ↓
 * Angular looks in routes array for path: 'dashboard'
 *     ↓
 * Finds it! Checks canActivate: [AuthGuard]
 *     ↓
 * AuthGuard runs:
 *   - Is user logged in? (token in localStorage?)
 *   - If YES ✓ → Load DashboardComponent
 *   - If NO ✗ → Redirect to /login
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 3: AUTHENTICATION SERVICE (auth.service.ts)
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * AUTH SERVICE - HANDLES LOGIN, SIGNUP, AND TOKEN MANAGEMENT
 * ──────────────────────────────────────────────────────────
 * 
 * @Injectable({
 *   providedIn: 'root'
 * })
 * └─ IMPORTANT: Makes this service available EVERYWHERE
 * └─ If you don't add this, you have to manually provide it
 * └─ providedIn: 'root' = Register in root of app
 * 
 * 
 * INSTANCE VARIABLES (Store data in the service):
 * ──────────────────────────────────────────────
 * 
 * private apiUrl = environment.apiUrl;
 * └─ private = Only this service can access this
 * └─ apiUrl = Backend server URL (like http://localhost:8080/api)
 * └─ environment.apiUrl = Get URL from environment file
 * 
 * 
 * private currentUserSubject = new BehaviorSubject<User | null>(
 *   this.getCurrentUser()
 * );
 * └─ BehaviorSubject = A special variable that can be "watched"
 * └─ When it changes, all components watching it get notified!
 * └─ <User | null> = Can be User object OR null
 * └─ this.getCurrentUser() = Get user from localStorage on app start
 * 
 * 
 * currentUser$ = this.currentUserSubject.asObservable();
 * └─ $ at end = Convention for OBSERVABLES (thing you can subscribe to)
 * └─ asObservable() = Convert BehaviorSubject to Observable
 * └─ Why? To prevent outsiders from directly changing it
 * └─ Only this service can update currentUserSubject
 * └─ But everyone can WATCH via currentUser$
 * 
 * 
 * CONSTRUCTOR:
 * ───────────
 * 
 * constructor(private http: HttpClient) {}
 * └─ private http: HttpClient = Inject HTTP tool
 * └─ Injection = "Give me this tool in the constructor"
 * └─ Now we can use this.http.post(), this.http.get(), etc.
 * 
 * 
 * METHOD 1: SIGNUP
 * ───────────────
 * 
 * signup(data: any): Observable<AuthResponse> {
 * └─ data: any = { name, email, password, role, managerEmail }
 * └─ Observable<AuthResponse> = Returns a promise-like thing
 *                                that eventually gives AuthResponse
 * 
 *   return this.http.post<AuthResponse>(
 *   └─ this.http.post() = Make a POST request (sending data to backend)
 *   └─ <AuthResponse> = The response will be of type AuthResponse
 *                        TypeScript will check: "Is response in correct format?"
 * 
 *     `${this.apiUrl}/auth/signup`,
 *     └─ ${} = JavaScript string interpolation
 *     └─ If apiUrl = 'http://localhost:8080/api'
 *     └─ Then this becomes: 'http://localhost:8080/api/auth/signup'
 * 
 *     data
 *     └─ data = { name: 'John', email: 'john@gmail.com', ... }
 *     └─ This is sent to backend in request body
 *   );
 * }
 * 
 * 
 * METHOD 2: LOGIN
 * ──────────────
 * 
 * login(data: any): Observable<AuthResponse> {
 * └─ data = { email: 'john@gmail.com', password: '123456' }
 * 
 *   return this.http.post<AuthResponse>(
 *     `${this.apiUrl}/auth/login`,
 *     data
 *   );
 * }
 * └─ Backend returns: { token: 'jwt123...', user: { id, name, role } }
 * 
 * 
 * METHOD 3: SAVE AUTH DATA
 * ─────────────────────────
 * 
 * saveAuthData(response: AuthResponse): void {
 * └─ response = { token: 'jwt123...', user: { ... } }
 * └─ void = This method doesn't return anything
 * 
 *   // Save token to localStorage
 *   localStorage.setItem('authToken', response.token);
 *   └─ localStorage = Browser's storage (stays even after refresh)
 *   └─ setItem(key, value) = Store token with key 'authToken'
 * 
 *   // Save user to localStorage
 *   localStorage.setItem('currentUser', JSON.stringify(response.user));
 *   └─ JSON.stringify() = Convert object to text
 *   └─ Why? localStorage can only store text, not objects!
 *   └─ Example: { name: 'John' } → '{"name":"John"}'
 * 
 *   // Update the BehaviorSubject
 *   this.currentUserSubject.next(response.user);
 *   └─ next() = "Update the value and notify all watchers"
 *   └─ All components watching currentUser$ get notified: "User changed!"
 * }
 * 
 * 
 * METHOD 4: GET CURRENT USER
 * ───────────────────────────
 * 
 * getCurrentUser(): User | null {
 * └─ User | null = Returns User object OR null (if not logged in)
 * 
 *   const userJson = localStorage.getItem('currentUser');
 *   └─ localStorage.getItem() = Retrieve stored value
 *   └─ If not found, returns null
 * 
 *   if (!userJson) {
 *   └─ if (!userJson) = If nothing stored, user not logged in
 *     return null;
 *   }
 * 
 *   try {
 *   └─ try = Try to do this, if error occurs, do catch block
 *     return JSON.parse(userJson);
 *     └─ JSON.parse() = Convert text back to object
 *     └─ '{"name":"John"}' → { name: 'John' }
 *   } catch (e) {
 *   └─ catch (e) = If JSON.parse fails (corrupted data)
 *     return null;
 *   }
 * }
 * 
 * 
 * METHOD 5: GET TOKEN
 * ──────────────────
 * 
 * getToken(): string | null {
 * └─ Returns the JWT token (or null if not logged in)
 * 
 *   return localStorage.getItem('authToken');
 *   └─ Get token from browser storage
 * }
 * 
 * 
 * METHOD 6: IS AUTHENTICATED?
 * ───────────────────────────
 * 
 * isAuthenticated(): boolean {
 * └─ boolean = true or false only
 * 
 *   return !!this.getToken();
 *   └─ !! = Double NOT operator (converts to boolean)
 *   └─ If token exists → true (user logged in)
 *   └─ If token is null → false (user not logged in)
 *   └─ Example: !!null = false, !!'hello' = true
 * }
 * 
 * 
 * METHOD 7: IS MANAGER?
 * ────────────────────
 * 
 * isManager(): boolean {
 *   const user = this.getCurrentUser();
 *   └─ Get current user
 * 
 *   return user?.role === 'MANAGER';
 *   └─ user?.role = Optional chaining
 *   └─ If user exists → return user.role
 *   └─ If user is null → return undefined (won't crash!)
 *   └─ If role is 'MANAGER' → return true, else false
 * }
 * 
 * 
 * METHOD 8: IS EMPLOYEE?
 * ─────────────────────
 * 
 * isEmployee(): boolean {
 *   const user = this.getCurrentUser();
 *   return user?.role === 'EMPLOYEE';
 * }
 * 
 * 
 * METHOD 9: LOGOUT
 * ────────────────
 * 
 * logout(): void {
 *   // Remove token
 *   localStorage.removeItem('authToken');
 *   └─ removeItem() = Delete from browser storage
 * 
 *   // Remove user
 *   localStorage.removeItem('currentUser');
 * 
 *   // Update BehaviorSubject
 *   this.currentUserSubject.next(null);
 *   └─ Set user to null and notify all watchers
 *   └─ All components watching currentUser$ see user is now null
 * }
 */

/**
 * HOW SIGNUP/LOGIN FLOW WORKS:
 * ────────────────────────────
 * 
 * USER FILLS FORM:
 *   Name: "John Doe"
 *   Email: "john@gmail.com"
 *   Password: "123456"
 *   Role: "EMPLOYEE"
 * 
 *       ↓
 * 
 * COMPONENT CALLS:
 *   this.authService.signup(formData)
 * 
 *       ↓
 * 
 * HTTP POST SENT TO BACKEND:
 *   POST http://localhost:8080/api/auth/signup
 *   Body: { name: "John", email: "john@gmail.com", ... }
 * 
 *       ↓
 * 
 * BACKEND RESPONDS:
 *   { token: "eyJhbGc...", user: { id: 1, name: "John", role: "EMPLOYEE" } }
 * 
 *       ↓
 * 
 * COMPONENT RECEIVES (subscribe):
 *   next: (response) => {
 *     // response = backend's response object
 *     this.authService.saveAuthData(response);
 *     // Save to localStorage + update BehaviorSubject
 *     this.router.navigate(['/dashboard']);
 *     // Redirect to dashboard
 *   }
 * 
 *       ↓
 * 
 * RESULT:
 *   ✓ Token stored in browser
 *   ✓ User stored in browser
 *   ✓ Logged in!
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 4: AUTH GUARD (SECURITY)
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * WHAT IS A GUARD?
 * ────────────────
 * Guards are BOUNCERS that check permission before letting users in
 * They run BEFORE loading a route
 * 
 * Example:
 * - User tries to access /dashboard
 * - AuthGuard runs
 * - Guard checks: "Do you have a token?"
 * - If YES → Allow access ✓
 * - If NO → Redirect to /login ✗
 * 
 * 
 * AUTH GUARD CODE:
 * ────────────────
 * 
 * @Injectable({
 *   providedIn: 'root'
 * })
 * export class AuthGuard implements CanActivate {
 * └─ CanActivate = Interface that has one job: Allow or block route?
 * 
 *   constructor(
 *     private authService: AuthService,
 *     └─ authService = Check token
 * 
 *     private router: Router,
 *     └─ router = Redirect user
 * 
 *     private logger: LoggerService
 *     └─ logger = Log events (for debugging)
 *   ) {}
 * 
 * 
 *   canActivate(
 *     route: ActivatedRouteSnapshot,
 *     state: RouterStateSnapshot
 *   ): boolean {
 *   └─ canActivate() = Method that runs BEFORE loading the route
 *   └─ Parameters:
 *      route = Current route information
 *      state = URL information (like state.url = '/dashboard')
 *   └─ Return: boolean (true = allow, false = block)
 * 
 *     console.log("AuthGuard running for:", state.url);
 *     └─ Log which URL user is trying to access
 * 
 *     console.log("Token:", this.authService.getToken());
 *     └─ Log if token exists
 * 
 *     console.log("Authenticated:", this.authService.isAuthenticated());
 *     └─ Log if authenticated
 * 
 * 
 *     // THE MAIN CHECK
 *     if (this.authService.isAuthenticated()) {
 *     └─ Is user logged in? (has valid token?)
 * 
 *       return true;
 *       └─ YES → Allow access to route ✓
 *       └─ User can see dashboard, apply-leave, etc.
 *     }
 * 
 * 
 *     // USER NOT AUTHENTICATED
 *     this.router.navigate(['/login']);
 *     └─ NO → Redirect to login page
 *     └─ User sees: "Please login first"
 * 
 *     return false;
 *     └─ Block access to route ✗
 *   }
 * }
 * 
 * 
 * GUARD EXECUTION FLOW:
 * ────────────────────
 * USER TRIES: router.navigate(['/dashboard'])
 *     ↓
 * ROUTER CHECKS: Is there a canActivate guard?
 *     ↓
 * YES → RUN: AuthGuard.canActivate()
 *     ↓
 * GUARD CHECKS: Is user authenticated?
 *     ↓
 * ANSWER: YES? Return true → Load dashboard ✓
 * ANSWER: NO? Return false → Redirect to login ✗
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 5: LOGIN COMPONENT
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * LOGIN COMPONENT - THE LOGIN PAGE
 * ────────────────────────────────
 * 
 * @Component({
 *   selector: 'app-login',
 *   └─ selector = HTML tag name for this component
 *   └─ You can use: <app-login></app-login>
 * 
 *   templateUrl: './login.component.html',
 *   └─ templateUrl = Which HTML file to display
 *   └─ The form (input fields, buttons) are in login.component.html
 * 
 *   styleUrls: ['./login.component.css'],
 *   └─ styleUrls = Which CSS files to use for styling
 *   └─ Array because you can have multiple CSS files
 * 
 *   standalone: true,
 *   └─ standalone: true = This component doesn't need a module
 *   └─ Modern Angular pattern (no NgModule needed)
 * 
 *   imports: [
 *     CommonModule,           ← For *ngIf, *ngFor
 *     ReactiveFormsModule,    ← For form validation
 *     RouterModule            ← For routerLink navigation
 *   ]
 * })
 * export class LoginComponent {
 * └─ "export" = This component can be imported elsewhere
 * └─ In other files: import { LoginComponent } from './login.component'
 * 
 * 
 * INSTANCE VARIABLES:
 * ──────────────────
 * 
 * loginForm: FormGroup;
 * └─ FormGroup = A group of form controls
 * └─ Think of it like a container holding all form fields
 * └─ Example: { email, password }
 * 
 * loading = false;
 * └─ loading = Is API call happening? (for showing spinner)
 * └─ false = No, not loading
 * 
 * error = '';
 * └─ error = Error message to show user
 * └─ Empty string = No error
 * 
 * returnUrl: string = '';
 * └─ returnUrl = If user was redirected to login, go back there after login
 * └─ Example: User tries /dashboard (not logged in)
 *            → Redirected to /login?returnUrl=/dashboard
 *            → After login, go back to /dashboard
 * 
 * userType: string = 'employee';
 * └─ userType = Are we showing "Employee Login" or "Manager Login"?
 * └─ Changes the page title and styling
 * 
 * 
 * CONSTRUCTOR:
 * ───────────
 * 
 * constructor(
 *   private formBuilder: FormBuilder,
 *   └─ FormBuilder = Tool to easily create forms
 *   └─ Instead of manually creating form controls
 * 
 *   private authService: AuthService,
 *   └─ AuthService = Make login API call
 * 
 *   private router: Router,
 *   └─ Router = Navigate to other pages
 * 
 *   private route: ActivatedRoute,
 *   └─ ActivatedRoute = Get current URL parameters
 *   └─ Example: ?userType=manager
 * 
 *   private logger: LoggerService
 *   └─ LoggerService = Log events for debugging
 * ) {
 *   // Build the form IMMEDIATELY
 *   this.loginForm = this.formBuilder.group({
 *   └─ group({}) = Create a form with these fields
 * 
 *     email: ['', [Validators.required, Validators.email]],
 *     └─ email field:
 *        Initial value: '' (empty)
 *        Validators: required (can't be empty) + email (must be valid email)
 * 
 *     password: ['', [Validators.required, Validators.minLength(6)]]
 *     └─ password field:
 *        Initial value: '' (empty)
 *        Validators: required + minLength(6) (at least 6 characters)
 *   });
 * 
 *   // Check if returnUrl in URL
 *   this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/dashboard';
 *   └─ queryParams['returnUrl'] = Get ?returnUrl=value from URL
 *   └─ || '/dashboard' = If not in URL, default to dashboard
 * }
 * 
 * 
 * GETTER METHODS:
 * ──────────────
 * 
 * get email() {
 * └─ Quick way to access form control
 * └─ Instead of: this.loginForm.get('email')
 * └─ We can do: this.email
 * 
 *   return this.loginForm.get('email');
 *   └─ get('email') = Get the 'email' field from form
 * }
 * 
 * get password() {
 *   return this.loginForm.get('password');
 * }
 * 
 * 
 * SUBMIT FORM METHOD:
 * ──────────────────
 * 
 * onSubmit(): void {
 * └─ void = This method doesn't return anything
 * 
 *   // Step 1: Check if form is valid
 *   if (!this.loginForm.valid) {
 *   └─ !valid = Form is NOT valid (has errors)
 * 
 *     this.error = 'Please fill all fields correctly';
 *     return;
 *     └─ Stop here, don't make API call
 *   }
 * 
 * 
 *   // Step 2: Show loading spinner
 *   this.loading = true;
 *   └─ Set loading = true
 *   └─ HTML shows: <spinner /> (CSS will show loading animation)
 * 
 * 
 *   // Step 3: Extract values from form
 *   const credentials = this.loginForm.value;
 *   └─ credentials = { email: 'john@gmail.com', password: '123456' }
 * 
 * 
 *   // Step 4: Call AuthService.login()
 *   this.authService.login(credentials).subscribe({
 *   └─ subscribe() = "When you get response, do this:"
 *   └─ Like ordering food and waiting for it
 * 
 *     next: (response) => {
 *     └─ next: = When successful, run this function
 *     └─ response = { token: '...', user: { ... } }
 * 
 *       this.logger.info('Login successful');
 *       └─ Log success event
 * 
 *       this.authService.saveAuthData(response);
 *       └─ Save token + user to localStorage
 *       └─ Update BehaviorSubject (notify all watchers)
 * 
 *       this.router.navigate([this.returnUrl]);
 *       └─ Navigate to returnUrl or /dashboard
 *       └─ 🎉 User is now logged in!
 *     },
 * 
 *     error: (error) => {
 *     └─ error: = When API fails, run this function
 *     └─ error = Error object from backend
 * 
 *       this.loading = false;
 *       └─ Stop showing spinner
 * 
 *       this.error = error.message || 'Login failed';
 *       └─ Show error message
 * 
 *       this.logger.error('Login error:', error);
 *       └─ Log error for debugging
 *     }
 *   });
 * }
 */

/**
 * LOGIN FORM FLOW DIAGRAM:
 * ───────────────────────
 * 
 * ┌─────────────────────────────────────┐
 * │      USER OPENS LOGIN PAGE          │
 * └─────────────────────────────────────┘
 *               ↓
 * ┌─────────────────────────────────────┐
 * │   1. Form loads with empty fields   │
 * │      Validation rules: email        │
 * │      - Must not be empty            │
 * │      - Must be valid email          │
 * └─────────────────────────────────────┘
 *               ↓
 * ┌─────────────────────────────────────┐
 * │  2. User fills: email + password    │
 * │     Validators run in real-time:    │
 * │     - Email format OK? ✓            │
 * │     - Password 6+ chars? ✓          │
 * │     - Button becomes active ✓       │
 * └─────────────────────────────────────┘
 *               ↓
 * ┌─────────────────────────────────────┐
 * │  3. User clicks "Login" button      │
 * │     onSubmit() runs                 │
 * └─────────────────────────────────────┘
 *               ↓
 * ┌─────────────────────────────────────┐
 * │  4. Check: Is form valid?           │
 * │     If NO → Show error, stop ✗      │
 * │     If YES → Continue ✓             │
 * └─────────────────────────────────────┘
 *               ↓
 * ┌─────────────────────────────────────┐
 * │  5. loading = true (show spinner)   │
 * └─────────────────────────────────────┘
 *               ↓
 * ┌─────────────────────────────────────┐
 * │  6. POST to: /api/auth/login        │
 * │     Body: { email, password }       │
 * └─────────────────────────────────────┘
 *               ↓
 *     ← Waiting for backend response →
 *               ↓
 * ┌─────────────────────────────────────┐
 * │  7. Backend checks credentials      │
 * │     Good? → return { token, user }  │
 * │     Bad? → return error             │
 * └─────────────────────────────────────┘
 *               ↓
 *      ┌─ SUCCESS ──┬─ ERROR ─┐
 *      ↓            ↓
 * ┌─────────────┐  ┌─────────────┐
 * │ Save token  │  │ loading=false│
 * │ Save user   │  │ Show error  │
 * │ loading=false│  │ Stay on page│
 * │ Navigate to │  └─────────────┘
 * │ /dashboard  │
 * └─────────────┘
 *      ↓
 * ┌─────────────────────────────────────┐
 * │    USER SEES DASHBOARD ✓            │
 * └─────────────────────────────────────┘
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 6: DASHBOARD COMPONENT
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * DASHBOARD - SHOWS USER'S LEAVE SUMMARY
 * ──────────────────────────────────────
 * 
 * @Component({
 *   selector: 'app-dashboard',
 *   standalone: true,
 *   imports: [CommonModule, DatePipe, NgClass],
 *   └─ DatePipe = Tool to format dates (Dec 25, 2026)
 *   └─ NgClass = Tool to conditionally add CSS classes
 * 
 *   templateUrl: './dashboard.component.html'
 * })
 * export class DashboardComponent implements OnInit {
 * └─ OnInit = Interface that requires ngOnInit() method
 * └─ ngOnInit() runs AFTER component loads (automatically)
 * 
 * 
 * DATA VARIABLES:
 * ──────────────
 * 
 * leaves: Leave[] = [];
 * └─ leaves = Array of leave objects
 * └─ [] = Empty array initially, fills when component loads
 * 
 * loading = false;
 * └─ Is page loading?
 * 
 * error = '';
 * └─ Error message
 * 
 * currentUser: any;
 * └─ Current logged-in user
 * 
 * 
 * CONSTRUCTOR:
 * ───────────
 * 
 * constructor(
 *   private leaveService: LeaveService,
 *   └─ Make API calls for leave data
 * 
 *   private authService: AuthService,
 *   └─ Get current user info
 * 
 *   private managerService: ManagerService
 *   └─ Get manager-specific data (pending leaves)
 * ) {}
 * 
 * 
 * NG ON INIT METHOD:
 * ────────────────
 * 
 * ngOnInit() {
 * └─ This runs AUTOMATICALLY when component loads
 * 
 *   // Get current user from authentication service
 *   const user = this.authService.getCurrentUser();
 *   └─ user = { id: 1, name: 'John', role: 'EMPLOYEE' }
 * 
 *   if (!user) {
 *   └─ If no user (not logged in)
 *     return;
 *     └─ Stop here, don't try to load anything
 *     └─ Actually, this shouldn't happen because AuthGuard protects this route
 *   }
 * 
 *   this.currentUser = user;
 *   └─ Store user in component
 * 
 *   this.loading = true;
 *   └─ Show loading spinner
 * 
 * 
 *   // IF EMPLOYEE
 *   if (user.role === 'EMPLOYEE') {
 *   └─ Is this an employee login?
 * 
 *     this.leaveService.getEmployeeLeaves(user.id).subscribe({
 *     └─ Get all leaves for this employee
 * 
 *       next: (data) => {
 *       └─ data = Array of Leave objects
 * 
 *         this.leaves = data;
 *         └─ [
 *            { leaveId: 1, startDate: '2026-03-26', status: 'PENDING' },
 *            { leaveId: 2, startDate: '2026-04-01', status: 'APPROVED' }
 *           ]
 * 
 *         this.loading = false;
 *         └─ Hide spinner
 *       },
 * 
 *       error: (err) => {
 *       └─ If API fails
 * 
 *         this.error = 'Failed to load leaves';
 *         this.loading = false;
 *       }
 *     });
 *   }
 * 
 * 
 *   // IF MANAGER
 *   if (user.role === 'MANAGER') {
 *   └─ Is this a manager login?
 * 
 *     this.managerService.getPendingLeaves(user.id).subscribe({
 *     └─ Get all PENDING leaves from team members
 * 
 *       next: (data) => {
 *       └─ data = Array of pending leaves (status = 'PENDING')
 * 
 *         this.leaves = data;
 *         └─ Shows only leaves waiting for approval
 * 
 *         this.loading = false;
 *       },
 * 
 *       error: (err) => {
 *         this.error = 'Failed to load pending leaves';
 *         this.loading = false;
 *       }
 *     });
 *   }
 * }
 * 
 * 
 * COUNT METHODS:
 * ──────────────
 * 
 * getPendingCount(): number {
 * └─ Count how many leaves are PENDING
 * 
 *   return this.leaves.filter(
 *   └─ filter() = Keep only items that match condition
 * 
 *     (leave) => leave.status === 'PENDING'
 *     └─ Keep only leaves where status is 'PENDING'
 * 
 *   ).length;
 *   └─ .length = How many items in filtered array?
 * }
 * 
 * getApprovedCount(): number {
 * └─ Count how many leaves are APPROVED
 * 
 *   return this.leaves.filter(
 *     (leave) => leave.status === 'APPROVED'
 *   ).length;
 * }
 * 
 * getRejectedCount(): number {
 * └─ Count how many leaves are REJECTED
 * 
 *   return this.leaves.filter(
 *     (leave) => leave.status === 'REJECTED'
 *   ).length;
 * }
 */

/**
 * DASHBOARD DISPLAY:
 * ──────────────────
 * 
 * ┌──────────────────────────────────────┐
 * │  WELCOME, JOHN! 👋                   │
 * ├──────────────────────────────────────┤
 * │  📊 LEAVE SUMMARY                    │
 * │  ┌─────────────┬───────────┬──────┐ │
 * │  │ PENDING: 2  │ APPROVED:5│ REJ:1│ │
 * │  └─────────────┴───────────┴──────┘ │
 * ├──────────────────────────────────────┤
 * │  YOUR LEAVES:                        │
 * │  1. 26-Mar to 27-Mar [PENDING] ⏳    │
 * │  2. 01-Apr to 02-Apr [APPROVED] ✅  │
 * │  3. 10-Apr to 10-Apr [REJECTED] ❌  │
 * └──────────────────────────────────────┘
 * 
 * For MANAGERS, it shows:
 * ┌──────────────────────────────────────┐
 * │  TEAM LEAVE APPROVALS                │
 * │  ┌──────────────────────────────────┐│
 * │  │ John Doe: 26-Mar to 27-Mar [⏳] ││
 * │  │ Jane Doe: 01-Apr to 02-Apr [⏳] ││
 * │  │ Action: [Approve] [Reject]       ││
 * │  └──────────────────────────────────┘│
 * └──────────────────────────────────────┘
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 7: APPLY LEAVE COMPONENT
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * APPLY LEAVE - WHERE EMPLOYEES REQUEST LEAVE
 * ────────────────────────────────────────────
 * 
 * @Component({
 *   selector: 'app-apply-leave',
 *   templateUrl: './apply-leave.component.html',
 *   styleUrls: ['./apply-leave.component.css'],
 *   standalone: true,
 *   imports: [CommonModule, ReactiveFormsModule]
 * })
 * export class ApplyLeaveComponent implements OnInit {
 * 
 * 
 * FORM & UI VARIABLES:
 * ───────────────────
 * 
 * leaveForm: FormGroup;
 * └─ Form with fields: startDate, endDate, reason
 * 
 * loading = false;
 * └─ Is API call in progress?
 * 
 * error = '';
 * └─ Error message
 * 
 * success = '';
 * └─ Success message
 * 
 * 
 * CONSTRUCTOR:
 * ───────────
 * 
 * constructor(
 *   private formBuilder: FormBuilder,
 *   └─ Build form
 * 
 *   private leaveService: LeaveService,
 *   └─ Make API call to apply leave
 * 
 *   private authService: AuthService,
 *   └─ Get current user ID
 * 
 *   private holidayService: HolidayService,
 *   └─ Check for holidays (don't count them as leave days)
 * 
 *   private router: Router,
 *   └─ Redirect after successful submission
 * 
 *   private logger: LoggerService
 *   └─ Log events
 * ) {
 *   // Create the form
 *   this.leaveForm = this.formBuilder.group({
 *   └─ group() = Create form controls
 * 
 *     startDate: ['', Validators.required],
 *     └─ startDate: Initial value = '', Must be filled
 * 
 *     endDate: ['', Validators.required],
 *     └─ endDate: Initial value = '', Must be filled
 * 
 *     reason: ['', [Validators.required, Validators.minLength(10)]]
 *     └─ reason: Initial value = '', Must be filled, Minimum 10 characters
 *     └─ So you can't write "leave" (only 5 chars), must be "I need leave for..."
 *   });
 * }
 * 
 * 
 * NG ON INIT:
 * ──────────
 * 
 * ngOnInit(): void {
 *   // Load holidays when component loads
 *   this.holidayService.loadHolidays().subscribe({
 *   └─ Get all company holidays from backend
 * 
 *     next: (data) => {
 *     └─ data = Array of Holiday objects
 *     └─ Example: [{ date: '2026-12-25', name: 'Christmas' }]
 * 
 *       this.holidayService.setHolidays(data);
 *       └─ Store holidays in service
 *       └─ Now when calculating leave days, we can exclude holidays
 *     },
 * 
 *     error: (err) => {
 *     └─ If loading holidays fails
 * 
 *       this.error = 'Could not load holidays';
 *       └─ Still allow user to apply, just might count holidays as leave days
 *     }
 *   });
 * }
 * 
 * 
 * GETTER METHODS:
 * ──────────────
 * 
 * get startDate() {
 *   return this.leaveForm.get('startDate');
 * }
 * 
 * get endDate() {
 *   return this.leaveForm.get('endDate');
 * }
 * 
 * get reason() {
 *   return this.leaveForm.get('reason');
 * }
 * 
 * 
 * ON SUBMIT METHOD:
 * ────────────────
 * 
 * onSubmit(): void {
 * └─ When user clicks "Apply Leave" button
 * 
 *   // Step 1: Validate form
 *   if (!this.leaveForm.valid) {
 *   └─ If form has errors
 * 
 *     this.error = 'Please fill all fields correctly';
 *     return;
 *   }
 * 
 * 
 *   // Step 2: Get values
 *   const start = new Date(this.leaveForm.value.startDate);
 *   └─ Convert string to Date object
 * 
 *   const end = new Date(this.leaveForm.value.endDate);
 * 
 *   // Step 3: Validate dates
 *   if (start > end) {
 *   └─ If start date is after end date
 * 
 *     this.error = 'Start date cannot be after end date';
 *     return;
 *   }
 * 
 * 
 *   // Step 4: Calculate working days
 *   const leaveDays = this.calculateWorkingDays(start, end);
 *   └─ Count only weekdays (Mon-Fri) excluding holidays
 * 
 *   if (leaveDays === 0) {
 *   └─ If no working days (like all weekends)
 * 
 *     this.error = 'Selected period has no working days';
 *     return;
 *   }
 * 
 * 
 *   // Step 5: Get current user
 *   const user = this.authService.getCurrentUser();
 *   if (!user) {
 *     this.error = 'User not found';
 *     return;
 *   }
 * 
 * 
 *   // Step 6: Prepare request
 *   const leaveRequest: LeaveRequest = {
 *   └─ Create object with required fields
 * 
 *     employeeId: user.id,
 *     └─ Current user's ID
 * 
 *     startDate: this.leaveForm.value.startDate,
 *     └─ '2026-03-26'
 * 
 *     endDate: this.leaveForm.value.endDate,
 *     └─ '2026-03-27'
 * 
 *     reason: this.leaveForm.value.reason
 *     └─ 'Going on vacation'
 *   };
 * 
 * 
 *   // Step 7: Show loading
 *   this.loading = true;
 * 
 * 
 *   // Step 8: Make API call
 *   this.leaveService.applyLeave(leaveRequest).subscribe({
 *   └─ Send leave request to backend
 * 
 *     next: (response) => {
 *     └─ response = { leaveId: 123, status: 'PENDING' }
 * 
 *       this.logger.info('Leave applied successfully');
 * 
 *       this.success = 'Leave applied successfully!';
 *       └─ Show success message
 * 
 *       this.leaveForm.reset();
 *       └─ Clear all form fields
 * 
 *       setTimeout(() => {
 *       └─ Wait 2 seconds then redirect
 * 
 *         this.router.navigate(['/dashboard']);
 *         └─ Go back to dashboard
 *       }, 2000);
 *     },
 * 
 *     error: (err) => {
 *     └─ Backend rejected request
 * 
 *       this.loading = false;
 * 
 *       this.error = err.error?.message || 'Failed to apply leave';
 *       └─ error.error?.message = Error message from backend
 *       └─ || 'Failed to apply leave' = Fallback message if not provided
 *     }
 *   });
 * }
 * 
 * 
 * CALCULATE WORKING DAYS METHOD:
 * ──────────────────────────────
 * 
 * calculateWorkingDays(start: Date, end: Date): number {
 * └─ Calculate number of working days (Mon-Fri, excluding holidays)
 * └─ NOT counting weekends or company holidays
 * 
 *   let count = 0;
 *   └─ Start with 0 days
 * 
 *   let current = new Date(start);
 *   └─ Copy start date (don't modify original)
 * 
 * 
 *   while (current <= end) {
 *   └─ Loop until we reach end date
 * 
 *     const dayOfWeek = current.getDay();
 *     └─ getDay() returns: 0=Sunday, 1=Monday, ..., 6=Saturday
 * 
 *     // Check if it's a weekday
 *     const isWeekday = dayOfWeek !== 0 && dayOfWeek !== 6;
 *     └─ If dayOfWeek is NOT 0 (Sun) and NOT 6 (Sat) → It's a weekday
 * 
 *     // Check if it's not a holiday
 *     const isNotHoliday = !this.holidayService.isHoliday(current);
 *     └─ Call holidayService: "Is this date a holiday?"
 *     └─ ! = NOT, so isNotHoliday = true if it's NOT a holiday
 * 
 *     // If weekday AND not holiday, count it
 *     if (isWeekday && isNotHoliday) {
 *     └─ Both conditions true?
 * 
 *       count++;
 *       └─ Add 1 to count
 *     }
 * 
 *     // Move to next day
 *     current.setDate(current.getDate() + 1);
 *     └─ getDate() = day of month (1-31)
 *     └─ +1 = tomorrow
 *   }
 * 
 *   return count;
 *   └─ Return total working days
 * }
 * 
 * 
 * EXAMPLE CALCULATION:
 * ───────────────────
 * User selects: 26-Mar (Wednesday) to 28-Mar (Friday)
 * 
 * Day 1: 26-Mar = Wednesday ✓ (weekday, not holiday) → count = 1
 * Day 2: 27-Mar = Thursday ✓ (weekday, not holiday) → count = 2
 * Day 3: 28-Mar = Friday ✓ (weekday, not holiday) → count = 3
 * 
 * Result: 3 working days
 */

/**
 * APPLY LEAVE USER FLOW:
 * ─────────────────────
 * 
 * ┌─────────────────────────────────────┐
 * │   USER OPENS "APPLY LEAVE"          │
 * └─────────────────────────────────────┘
 *            ↓
 * ┌─────────────────────────────────────┐
 * │  FORM LOADS:                        │
 * │  [Start Date: ______] 📅            │
 * │  [End Date: ______] 📅              │
 * │  [Reason: __________]  📝           │
 * │  [Apply Leave] (disabled)           │
 * └─────────────────────────────────────┘
 *            ↓
 * ┌─────────────────────────────────────┐
 * │  USER FILLS:                        │
 * │  26-Mar to 28-Mar                   │
 * │  "Going on vacation"                │
 * │  [Apply Leave] (now enabled!) ✓     │
 * └─────────────────────────────────────┘
 *            ↓
 * ┌─────────────────────────────────────┐
 * │  USER CLICKS "APPLY LEAVE"          │
 * │  onSubmit() runs:                   │
 * │  1. Validate form ✓                 │
 * │  2. Validate dates ✓                │
 * │  3. Calculate working days:         │
 * │     26-Mar (Wed) ✓                  │
 * │     27-Mar (Thu) ✓                  │
 * │     28-Mar (Fri) ✓                  │
 * │     Total: 3 days                   │
 * └─────────────────────────────────────┘
 *            ↓
 * ┌─────────────────────────────────────┐
 * │  SEND TO BACKEND:                   │
 * │  POST /api/leaves/apply             │
 * │  {                                  │
 * │    employeeId: 1,                   │
 * │    startDate: "26-Mar",             │
 * │    endDate: "28-Mar",               │
 * │    reason: "Going on vacation"      │
 * │  }                                  │
 * └─────────────────────────────────────┘
 *            ↓
 * ┌─────────────────────────────────────┐
 * │  BACKEND RESPONSE:                  │
 * │  { leaveId: 123,                    │
 * │    status: "PENDING" }              │
 * └─────────────────────────────────────┘
 *            ↓
 * ┌─────────────────────────────────────┐
 * │  FRONTEND SHOWS:                    │
 * │  "✅ Leave applied successfully!    │
 * │  Redirecting to dashboard..."       │
 * └─────────────────────────────────────┘
 *            ↓
 *   (after 2 seconds)
 *            ↓
 * ┌─────────────────────────────────────┐
 * │  DASHBOARD SHOWS NEW LEAVE:         │
 * │  26-Mar to 28-Mar [PENDING] ⏳      │
 * └─────────────────────────────────────┘
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 8: MANAGER APPROVAL COMPONENT
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * MANAGER APPROVAL - WHERE MANAGERS APPROVE/REJECT LEAVES
 * ────────────────────────────────────────────────────────
 * 
 * @Component({
 *   selector: 'app-manager-approval',
 *   standalone: true,
 *   imports: [CommonModule, FormsModule, DatePipe],
 *   └─ FormsModule = For two-way data binding (ngModel)
 *   └─ When user types in rejection reason, update variable
 * 
 *   templateUrl: './manager-approval.component.html'
 * })
 * export class ManagerApprovalComponent implements OnInit {
 * 
 * 
 * DATA ARRAYS:
 * ────────────
 * 
 * pendingLeaves: Leave[] = [];
 * └─ Array of all PENDING leaves from team
 * └─ [
 *    { leaveId: 1, employeeName: 'John', startDate: '2026-03-26', status: 'PENDING' },
 *    { leaveId: 2, employeeName: 'Jane', startDate: '2026-04-01', status: 'PENDING' }
 *   ]
 * 
 * selectedLeave?: Leave;
 * └─ Which leave is currently selected? (shown on right panel)
 * └─ ? = Optional (might not have one selected)
 * 
 * 
 * FORM & UI VARIABLES:
 * ───────────────────
 * 
 * rejectionReason = '';
 * └─ Text that manager types as rejection reason
 * └─ Example: "You didn't request earlier"
 * 
 * loading = false;
 * └─ Page loading?
 * 
 * actionLoading = false;
 * └─ Is approve/reject button processing?
 * └─ Different from loading because:
 *    - loading = page loading (show spinner)
 *    - actionLoading = button processing (disable button)
 * 
 * error = '';
 * └─ Error message
 * 
 * successMessage = '';
 * └─ Success message
 * 
 * showRejectionForm = false;
 * └─ Should we show the "Rejection Reason" textarea?
 * 
 * 
 * CONSTRUCTOR:
 * ───────────
 * 
 * constructor(private leaveService: LeaveService) {}
 * 
 * 
 * NG ON INIT:
 * ──────────
 * 
 * ngOnInit(): void {
 * └─ Load all pending leaves when component opens
 * 
 *   this.loading = true;
 * 
 *   this.leaveService.getPendingLeaves(managerId).subscribe({
 *   └─ Get all leaves with status = 'PENDING'
 * 
 *     next: (data) => {
 *     └─ data = Array of pending leaves
 * 
 *       this.pendingLeaves = data;
 *       └─ Store in component
 * 
 *       this.loading = false;
 *       └─ Hide spinner
 *     },
 * 
 *     error: (err) => {
 *     └─ If API fails
 * 
 *       this.error = 'Failed to load pending leaves';
 *       this.loading = false;
 *     }
 *   });
 * }
 * 
 * 
 * SELECT LEAVE METHOD:
 * ────────────────────
 * 
 * selectLeave(leave: Leave): void {
 * └─ Manager clicks on a leave in the list
 * 
 *   this.selectedLeave = leave;
 *   └─ Set it as currently selected
 *   └─ HTML will display its details on the right panel
 * 
 *   this.showRejectionForm = false;
 *   └─ Hide rejection reason form (reset)
 * 
 *   this.rejectionReason = '';
 *   └─ Clear rejection reason field
 * }
 * 
 * 
 * APPROVE LEAVE METHOD:
 * ────────────────────
 * 
 * approveLeave(): void {
 * └─ Manager clicks "Approve" button
 * 
 *   if (!this.selectedLeave) {
 *   └─ No leave selected?
 *     return;
 *   }
 * 
 *   this.actionLoading = true;
 *   └─ Disable button, show loading
 * 
 *   this.leaveService.approveLeave(this.selectedLeave.leaveId).subscribe({
 *   └─ Backend: Update leave status to 'APPROVED'
 * 
 *     next: () => {
 *     └─ Success!
 * 
 *       this.successMessage = '✅ Leave approved!';
 * 
 *       // Remove from list
 *       this.pendingLeaves = this.pendingLeaves.filter(
 *       └─ Keep all leaves EXCEPT the one we just approved
 * 
 *         (leave) => leave.leaveId !== this.selectedLeave!.leaveId
 *         └─ ! = Tell TypeScript that selectedLeave exists (we checked above)
 *       );
 * 
 *       this.selectedLeave = undefined;
 *       └─ Deselect
 * 
 *       this.actionLoading = false;
 *       └─ Re-enable button
 * 
 *       // Clear success message after 3 seconds
 *       setTimeout(() => {
 *         this.successMessage = '';
 *       }, 3000);
 *     },
 * 
 *     error: (err) => {
 *     └─ API call failed
 * 
 *       this.error = 'Failed to approve leave';
 *       this.actionLoading = false;
 *     }
 *   });
 * }
 * 
 * 
 * REJECT LEAVE METHOD:
 * ────────────────────
 * 
 * rejectLeave(): void {
 * └─ Manager clicks "Reject" button
 * 
 *   if (!this.selectedLeave) {
 *     return;
 *   }
 * 
 *   if (!this.rejectionReason.trim()) {
 *   └─ If rejection reason is empty
 * 
 *     this.error = 'Please provide a rejection reason';
 *     return;
 *   }
 * 
 *   this.actionLoading = true;
 * 
 *   const rejectRequest = {
 *   └─ Create object with details
 * 
 *     leaveId: this.selectedLeave.leaveId,
 *     status: 'REJECTED',
 *     rejectionReason: this.rejectionReason
 *     └─ Reason: "You didn't request earlier"
 *   };
 * 
 *   this.leaveService.rejectLeave(rejectRequest).subscribe({
 *   └─ Backend: Update leave status to 'REJECTED' with reason
 * 
 *     next: () => {
 *     └─ Success!
 * 
 *       this.successMessage = '✅ Leave rejected!';
 * 
 *       // Remove from list
 *       this.pendingLeaves = this.pendingLeaves.filter(
 *         (leave) => leave.leaveId !== this.selectedLeave!.leaveId
 *       );
 * 
 *       this.selectedLeave = undefined;
 *       this.rejectionReason = '';
 * 
 *       this.actionLoading = false;
 * 
 *       setTimeout(() => {
 *         this.successMessage = '';
 *       }, 3000);
 *     },
 * 
 *     error: (err) => {
 *     └─ API failed
 * 
 *       this.error = 'Failed to reject leave';
 *       this.actionLoading = false;
 *     }
 *   });
 * }
 */

/**
 * MANAGER APPROVAL UI LAYOUT:
 * ──────────────────────────
 * 
 * ┌─────────────────────────────────────────────────────────────────┐
 * │                    MANAGE LEAVE APPROVALS                       │
 * ├─────────────────────────────────────────────────────────────────┤
 * │                                                                 │
 * │  LEFT PANEL                          RIGHT PANEL                │
 * │  ┌──────────────────────┐           ┌──────────────────────┐   │
 * │  │ PENDING LEAVES:      │           │ LEAVE DETAILS:       │   │
 * │  │                      │           │ (When you click ↓)   │   │
 * │  │ ┌────────────────┐   │           │                      │   │
 * │  │ │ 1. John Doe    │←──┼─────────┐ │ > Employee: John Doe│   │
 * │  │ │ 26-Mar to 28-Mar│  │        │ │ > Dates: 26-Mar to  │   │
 * │  │ │ [CLICK ME] ✋   │  │        │ │        28-Mar        │   │
 * │  │ └────────────────┘   │        │ │ > Days: 3            │   │
 * │  │                      │        │ │ > Reason: Vacation   │   │
 * │  │ ┌────────────────┐   │        │ │                      │   │
 * │  │ │ 2. Jane Smith  │   │        │ │ [✓ APPROVE] [✗ REJECT]
 * │  │ │ 01-Apr to 02-Apr│  │        │ │                      │   │
 * │  │ │                 │  │        └─┤ (When reject) ↓      │   │
 * │  │ └────────────────┘   │          │ [Reason textarea]    │   │
 * │  │                      │          │ [Submit Rejection]   │   │
 * │  └──────────────────────┘          └──────────────────────┘   │
 * │                                                                 │
 * └─────────────────────────────────────────────────────────────────┘
 * 
 * FLOW:
 * 1. List loads with all PENDING leaves
 * 2. Manager clicks a leave (selectedLeave = that leave)
 * 3. Details show on right (shown because *ngIf="selectedLeave")
 * 4. Manager clicks Approve/Reject
 * 5. Leave removed from list
 * 6. Success message shows
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 9: LEAVE SERVICE
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * LEAVE SERVICE - ALL API CALLS FOR LEAVE OPERATIONS
 * ───────────────────────────────────────────────────
 * 
 * @Injectable({
 *   providedIn: 'root'
 * })
 * export class LeaveService {
 * 
 * private apiUrl = environment.apiUrl;
 * └─ API base URL: http://localhost:8080/api
 * 
 * 
 * CONSTRUCTOR:
 * ───────────
 * 
 * constructor(private http: HttpClient) {}
 * └─ Inject HTTP tool
 * 
 * 
 * METHOD 1: APPLY LEAVE
 * ────────────────────
 * 
 * applyLeave(data: LeaveRequest): Observable<Leave> {
 * └─ LeaveRequest = { employeeId, startDate, endDate, reason }
 * └─ Observable<Leave> = Returns Leave object when done
 * 
 *   return this.http.post<Leave>(
 *   └─ this.http.post() = Send POST request
 *   └─ <Leave> = Return type is Leave
 * 
 *     `${this.apiUrl}/leaves/apply`,
 *     └─ URL: http://localhost:8080/api/leaves/apply
 * 
 *     data
 *     └─ Request body: { employeeId: 1, startDate: '...', ... }
 *   );
 * └─ Backend returns: { leaveId: 123, status: 'PENDING', ... }
 * }
 * 
 * 
 * METHOD 2: GET EMPLOYEE LEAVES
 * ──────────────────────────────
 * 
 * getEmployeeLeaves(employeeId: number): Observable<Leave[]> {
 * └─ employeeId = Employee's ID (number)
 * └─ Observable<Leave[]> = Returns array of Leave objects
 * 
 *   return this.http.get<Leave[]>(
 *   └─ this.http.get() = Send GET request
 *   └─ <Leave[]> = Return type is array of Leaves
 * 
 *     `${this.apiUrl}/leaves/employee/${employeeId}`
 *     └─ URL: http://localhost:8080/api/leaves/employee/1
 *     └─ Gets all leaves for employee with ID 1
 *   );
 * └─ Backend returns: [
 *      { leaveId: 1, status: 'PENDING' },
 *      { leaveId: 2, status: 'APPROVED' }
 *    ]
 * }
 * 
 * 
 * METHOD 3: CANCEL LEAVE
 * ─────────────────────
 * 
 * cancelLeave(leaveId: number) {
 * └─ Employee cancels their own leave
 * 
 *   return this.http.put(
 *   └─ this.http.put() = Send PUT request (update)
 * 
 *     `${this.apiUrl}/leaves/cancel/${leaveId}`,
 *     └─ URL: http://localhost:8080/api/leaves/cancel/123
 * 
 *     {}
 *     └─ Empty body (just the ID is enough)
 *   );
 * └─ Backend: Sets status to 'CANCELLED'
 * }
 * 
 * 
 * METHOD 4: GET PENDING LEAVES (FOR MANAGER)
 * ───────────────────────────────────────────
 * 
 * getPendingLeaves(managerId: number): Observable<Leave[]> {
 * └─ Get all PENDING leaves from manager's team
 * 
 *   return this.http.get<Leave[]>(
 *     `${this.apiUrl}/managers/${managerId}/pending-leaves`
 *     └─ URL: http://localhost:8080/api/managers/5/pending-leaves
 *   );
 * }
 * 
 * 
 * METHOD 5: APPROVE LEAVE
 * ──────────────────────
 * 
 * approveLeave(leaveId: number) {
 * └─ Manager approves a leave
 * 
 *   return this.http.put(
 *     `${this.apiUrl}/managers/approve/${leaveId}`,
 *     └─ URL: http://localhost:8080/api/managers/approve/123
 * 
 *     {},
 *     └─ Empty body
 * 
 *     { responseType: 'text' }
 *     └─ responseType: 'text' = Backend returns plain text, not JSON
 *     └─ Why not JSON? Sometimes backend just returns "OK" or "Success"
 *     └─ If we expect JSON but get text, it will error
 *   );
 * }
 * 
 * 
 * METHOD 6: REJECT LEAVE
 * ─────────────────────
 * 
 * rejectLeave(rejectRequest: LeaveApprovalRequest): Observable<any> {
 * └─ rejectRequest = { leaveId, status: 'REJECTED', rejectionReason }
 * 
 *   return this.http.put(
 *     `${this.apiUrl}/leaves/reject/${rejectRequest.leaveId}`,
 *     └─ URL: http://localhost:8080/api/leaves/reject/123
 * 
 *     rejectRequest
 *     └─ Body: { leaveId: 123, status: 'REJECTED', rejectionReason: '...' }
 *   );
 * }
 * 
 * 
 * METHOD 7: GET LEAVE BALANCE
 * ───────────────────────────
 * 
 * getLeaveBalance(employeeId: number): Observable<LeaveBalance> {
 * └─ Get remaining leaves for employee
 * 
 *   return this.http.get<LeaveBalance>(
 *     `${this.apiUrl}/leaves/balance/${employeeId}`
 *     └─ URL: http://localhost:8080/api/leaves/balance/1
 *   );
 * └─ Backend returns: {
 *      annualLeaves: 20,      (20 days per year)
 *      usedLeaves: 5,         (already used 5)
 *      remainingLeaves: 15    (15 left)
 *    }
 * }
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 10: HTTP REQUEST/RESPONSE CYCLE
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * HOW DATA FLOWS FROM FRONTEND → BACKEND → FRONTEND
 * ─────────────────────────────────────────────────
 * 
 * STEP 1: COMPONENT MAKES REQUEST
 * ───────────────────────────────
 * 
 * this.leaveService.applyLeave(formData).subscribe(
 * └─ subscribe() = "Listen for response"
 * └─ Like a food order: "Call me when it's ready"
 * 
 *   (response) => {
 *     // When successful, do this
 *   },
 * 
 *   (error) => {
 *     // When fails, do this
 *   }
 * );
 * 
 * 
 * STEP 2: SERVICE CREATES HTTP REQUEST
 * ────────────────────────────────────
 * 
 * this.http.post(url, data)
 * //            ↓     ↓
 * //            URL   Body (what to send)
 * 
 * 
 * STEP 3: INTERCEPTOR ADDS TOKEN
 * ──────────────────────────────
 * 
 * Request Header Before:
 * ├── Content-Type: application/json
 * 
 * Interceptor runs (withInterceptorsFromDi):
 * ├── "Add auth token to request"
 * │
 * Request Header After:
 * ├── Content-Type: application/json
 * ├── Authorization: Bearer eyJhbGc...xyz
 * 
 * 
 * STEP 4: REQUEST SENT TO BACKEND
 * ───────────────────────────────
 * 
 * METHOD: POST
 * URL: http://localhost:8080/api/leaves/apply
 * HEADERS: { Authorization: Bearer eyJ... }
 * BODY: {
 *   "employeeId": 1,
 *   "startDate": "2026-03-26",
 *   "endDate": "2026-03-27",
 *   "reason": "Vacation"
 * }
 * 
 * 
 * STEP 5: BACKEND PROCESSES
 * ──────────────────────────
 * 
 * Backend receives request
 *     ↓
 * Check Authorization header (is token valid?)
 *     ↓
 * Extract data from request body
 *     ↓
 * Validate: dates, employee exists, reason not empty
 *     ↓
 * Check holidays/weekends (calculate leave days)
 *     ↓
 * Check if employee has remaining balance
 *     ↓
 * IF ALL OK → Save to database
 * IF ERROR  → Return error response
 * 
 * 
 * STEP 6: BACKEND SENDS RESPONSE
 * ──────────────────────────────
 * 
 * Success Response (HTTP 200):
 * {
 *   "leaveId": 123,
 *   "employeeId": 1,
 *   "status": "PENDING",
 *   "startDate": "2026-03-26",
 *   "endDate": "2026-03-27",
 *   "leaveDays": 2
 * }
 * 
 * OR
 * 
 * Error Response (HTTP 400/401/500):
 * {
 *   "error": "Invalid dates",
 *   "message": "Start date cannot be in past",
 *   "statusCode": 400
 * }
 * 
 * 
 * STEP 7: FRONTEND RECEIVES RESPONSE
 * ───────────────────────────────────
 * 
 * subscribe((response) => {})
 *     ↓
 * typeof response = object (from JSON)
 * ↓
 * If HTTP 200-299 → next() called
 * If HTTP 400+ → error() called
 * 
 * 
 * STEP 8: COMPONENT HANDLES
 * ─────────────────────────
 * 
 * SUCCESS CASE:
 * next: (response) => {
 *   this.leaves.push(response);  // Add to list
 *   this.success = 'Leave applied!';
 *   this.router.navigate(['/dashboard']);
 * }
 * 
 * ERROR CASE:
 * error: (err) => {
 *   this.error = err.error.message;  // Show error
 *   this.loading = false;
 * }
 * 
 * 
 * STEP 9: UI UPDATES
 * ──────────────────
 * 
 * Angular detects changes
 *     ↓
 * Re-renders component
 *     ↓
 * User sees new data/message
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 11: DATA MODELS (INTERFACES)
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * DATA MODELS - EVERY PIECE OF DATA HAS A STRUCTURE
 * ─────────────────────────────────────────────────
 * 
 * LEAVE INTERFACE:
 * ────────────────
 * 
 * export interface Leave {
 * └─ interface = Contract: "Any Leave object MUST have these properties"
 * 
 *   leaveId: number;
 *   └─ number = Whole number like 1, 2, 3...
 *   └─ Database assigns this when leave is created
 * 
 *   employeeName: string;
 *   └─ string = Text like "John Doe"
 * 
 *   startDate: string;
 *   └─ string = "2026-03-26" (YYYY-MM-DD format)
 * 
 *   endDate: string;
 *   └─ string = "2026-03-27"
 * 
 *   leaveDays: number;
 *   └─ number = How many working days? (calculated)
 * 
 *   status: string;
 *   └─ string = "PENDING", "APPROVED", "REJECTED", or "CANCELLED"
 * }
 * 
 * EXAMPLE OF A LEAVE OBJECT:
 * {
 *   leaveId: 1,
 *   employeeName: "John Doe",
 *   startDate: "2026-03-26",
 *   endDate: "2026-03-27",
 *   leaveDays: 2,
 *   status: "PENDING"
 * }
 * 
 * 
 * LEAVE REQUEST INTERFACE:
 * ────────────────────────
 * 
 * export interface LeaveRequest {
 * └─ Data that EMPLOYEE sends when applying for leave
 * 
 *   employeeId: number;
 *   └─ Who is applying?
 * 
 *   startDate: string;
 *   └─ When does it start?
 * 
 *   endDate: string;
 *   └─ When does it end?
 * 
 *   reason: string;
 *   └─ Why do you need leave?
 * }
 * 
 * EXAMPLE:
 * {
 *   employeeId: 1,
 *   startDate: "2026-03-26",
 *   endDate: "2026-03-27",
 *   reason: "Going on vacation"
 * }
 * 
 * 
 * LEAVE APPROVAL REQUEST INTERFACE:
 * ──────────────────────────────────
 * 
 * export interface LeaveApprovalRequest {
 * └─ Data for MANAGER when approving/rejecting
 * 
 *   leaveId: string;
 *   └─ Which leave? (ID)
 * 
 *   status: LeaveStatus;
 *   └─ New status: 'APPROVED' or 'REJECTED'
 * 
 *   rejectionReason?: string;
 *   └─ ? = Optional (only needed for rejection)
 *   └─ "You didn't request earlier"
 * }
 * 
 * 
 * LEAVE BALANCE INTERFACE:
 * ────────────────────────
 * 
 * export interface LeaveBalance {
 * └─ Employee's leave statistics
 * 
 *   annualLeaves: number;
 *   └─ 20 days per year (company policy)
 * 
 *   usedLeaves: number;
 *   └─ How many already used this year? (5)
 * 
 *   remainingLeaves: number;
 *   └─ How many left? (20 - 5 = 15)
 * }
 * 
 * EXAMPLE:
 * {
 *   annualLeaves: 20,
 *   usedLeaves: 5,
 *   remainingLeaves: 15
 * }
 * 
 * 
 * LEAVE STATUS ENUM:
 * ──────────────────
 * 
 * export enum LeaveStatus {
 * └─ enum = Fixed list of possible values
 * └─ Employee status can ONLY be one of these
 * 
 *   PENDING = 'PENDING',
 *   └─ Waiting for manager approval
 * 
 *   APPROVED = 'APPROVED',
 *   └─ Manager approved
 * 
 *   REJECTED = 'REJECTED',
 *   └─ Manager rejected
 * 
 *   CANCELLED = 'CANCELLED'
 *   └─ Employee cancelled their own request
 * }
 * 
 * USAGE IN CODE:
 * if (leave.status === LeaveStatus.APPROVED) {
 *   // User approved the leave
 * }
 * Instead of: if (leave.status === 'APPROVED') { ... }
 * Why enum? Prevents typos! AAPROVED would error with enum
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 12: OBSERVABLE & SUBSCRIPTION PATTERN
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * OBSERVABLE = A STREAM OF DATA THAT ARRIVES OVER TIME
 * ───────────────────────────────────────────────────
 * 
 * Think of it like a TV channel:
 * - You subscribe to a channel
 * - Data (shows) arrive over time
 * - If error occurs, no more shows
 * - When complete, stream ends
 * 
 * 
 * SIMPLE ANALOGY:
 * ───────────────
 * 
 * Non-Observable (Promise):
 * ─────────────────────────
 * Person: "Get me coffee"
 * ← They wait here...
 * Waiter: "Here's your coffee"
 * ← Now they get it and move forward
 * 
 * Observable:
 * ───────────
 * Person: "Whenever coffee is ready, tell me"
 * Person continues doing other things...
 * Waiter: "Coffee is ready!" ← Person reacts
 * 
 * 
 * EXAMPLE 1: SIMPLE REQUEST
 * ──────────────────────────
 * 
 * // Component makes request
 * this.leaveService.applyLeave(data).subscribe(
 * └─ subscribe() = "Listen for results"
 * 
 *   (response) => {
 *   └─ next callback: When data arrives
 *   └─ Called ONCE (for single response)
 * 
 *     console.log('Leave applied:', response);
 *     └─ response = { leaveId: 123, status: 'PENDING' }
 * 
 *     this.leaves.push(response);
 *     └─ Add new leave to list
 * 
 *     this.showSuccess = true;
 *     └─ Show success message
 *   },
 * 
 *   (error) => {
 *   └─ error callback: When something fails
 *   └─ Called ONCE if error happens
 * 
 *     console.error('Failed:', error);
 *     └─ error = Error object from backend
 * 
 *     this.error = error.message;
 *     └─ Show error message
 *   },
 * 
 *   () => {
 *   └─ complete callback: When stream ends
 *   └─ Called ONCE after next or error
 *   └─ Optional - not always needed
 * 
 *     console.log('Request completed');
 *     └─ Cleanup if needed
 *   }
 * );
 * 
 * 
 * EXAMPLE 2: WITHOUT SUBSCRIBE
 * ────────────────────────────
 * 
 * const result = this.leaveService.applyLeave(data);
 * └─ result = Observable object (NOT the data!)
 * └─ result is JUST A BLUEPRINT - no API call happens yet!
 * 
 * // To actually make request, you MUST subscribe:
 * result.subscribe(...);  // NOW API call happens!
 * 
 * 
 * EXAMPLE 3: MULTIPLE SUBSCRIBERS
 * ─────────────────────────────────
 * 
 * const leaveObs = this.leaveService.applyLeave(data);
 * └─ Just a blueprint
 * 
 * // Subscriber 1:
 * leaveObs.subscribe({
 *   next: (response) => console.log('Subscriber 1:', response)
 * });
 * └─ Makes ONE API call
 * 
 * // Subscriber 2:
 * leaveObs.subscribe({
 *   next: (response) => console.log('Subscriber 2:', response)
 * });
 * └─ Makes ANOTHER API call
 * 
 * // Result: API called TWICE (not efficient!)
 * // Solution: Use shareReplay() operator
 * 
 * 
 * EXAMPLE 4: CHAINING (Operators)
 * ────────────────────────────────
 * 
 * this.leaveService.applyLeave(data)
 *   .pipe(
 *   └─ pipe() = Chain operations
 * 
 *     tap((response) => {
 *     └─ tap() = "Do something, but don't change the data"
 *     └─ Like spying on data
 * 
 *       console.log('Leave applied:', response);
 *       └─ Just log, don't modify
 *     }),
 * 
 *     map((response) => response.leaveId),
 *     └─ map() = "Transform/change the data"
 *     └─ Input: { leaveId: 123, status: 'PENDING' }
 *     └─ Output: 123 (just the ID)
 * 
 *     filter((leaveId) => leaveId > 0)
 *     └─ filter() = "Keep only if condition is true"
 *     └─ If leaveId > 0 → pass it through
 *     └─ If leaveId <= 0 → block it
 *   )
 *   .subscribe((leaveId) => {
 *   └─ Now we get JUST the ID (number), not full object
 * 
 *     console.log('Leave ID:', leaveId);  // 123
 *   });
 * 
 * 
 * EXAMPLE 5: UNSUBSCRIBE (IMPORTANT!)
 * ─────────────────────────────────────
 * 
 * // Every subscribe() creates a "listener"
 * // If not unsubscribed, memory leaks occur!
 * 
 * // Option 1: Manual unsubscribe
 * const subscription = this.leaveService.getEmployeeLeaves(id)
 *   .subscribe(...);
 * 
 * ngOnDestroy() {
 *   subscription.unsubscribe();
 *   └─ Remove listener when component destroyed
 * }
 * 
 * 
 * // Option 2: Automatic (using async pipe)
 * {{ leaves$ | async }}
 * └─ Angular automatically unsubscribes when component destroyed
 * └─ Safe and clean!
 * 
 * 
 * // Option 3: takeUntil pattern
 * private destroy$ = new Subject<void>();
 * 
 * ngOnInit() {
 *   this.leaveService.getEmployeeLeaves(id)
 *     .pipe(
 *       takeUntil(this.destroy$)
 *       └─ Stop listening when destroy$ emits
 *     )
 *     .subscribe(...);
 * }
 * 
 * ngOnDestroy() {
 *   this.destroy$.next();
 *   this.destroy$.complete();
 * }
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 13: KEY KEYWORDS REFERENCE TABLE
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * QUICK REFERENCE: KEY KEYWORDS
 * ─────────────────────────────
 * 
 * | Keyword | Meaning | Example |
 * |---------|---------|---------|
 * | import | Bring in code from other files | import { Login } from './login' |
 * | export | Make code available to other files | export class LoginComponent |
 * | @Injectable | Register service globally | @Injectable({ providedIn: 'root' }) |
 * | private | Only this class can access | private authService |
 * | this. | Access variable/method in same class | this.login() |
 * | new | Create new object/instance | new Date() |
 * | [] | Array (list) | leaves: Leave[] = [] |
 * | {} | Object | { name: 'John', age: 30 } |
 * | => | Arrow function | (x) => x + 1 |
 * | subscribe() | Listen for response | .subscribe(() => { }) |
 * | next: | When success | next: (data) => { } |
 * | error: | When failed | error: (err) => { } |
 * | ? | Optional property | rejectionReason? |
 * | ! | Not/Negation | !isLoading (opposite of isLoading) |
 * | \|\| | OR operator | a \|\| b (if a is falsy, use b) |
 * | && | AND operator | a && b (both must be true) |
 * | if (!) | If NOT true | if (!authenticated) |
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 14: COMPLETE FLOW SUMMARY
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * COMPLETE FLOW - FROM APP START TO USER LOGOUT
 * ──────────────────────────────────────────────
 * 
 * APP START
 * ─────────
 * ├─ main.ts starts
 * ├─ List all routes
 * ├─ Load AppComponent
 * └─ Display in browser
 *       ↓
 * USER VISITS HOME
 * ────────────────
 * ├─ HomeComponent loads
 * ├─ Shows login buttons
 * └─ No authentication needed (public page)
 *       ↓
 * USER CLICKS "LOGIN"
 * ──────────────────
 * ├─ Route /login
 * ├─ LoginComponent loads (no guard needed)
 * ├─ Form validation happens
 * └─ User enters email + password
 *       ↓
 * USER CLICKS "LOGIN BUTTON"
 * ──────────────────────────
 * ├─ onSubmit() runs
 * ├─ Validate form
 * ├─ Call AuthService.login()
 * ├─ POST to /api/auth/login
 * └─ Backend checks credentials
 *       ↓
 * IF VALID:
 * ────────
 * ├─ Backend returns: { token, user }
 * ├─ Frontend saveAuthData():
 * │  ├─ localStorage.setItem('authToken')
 * │  ├─ localStorage.setItem('currentUser')
 * │  └─ Update BehaviorSubject
 * └─ Navigate to /dashboard
 *       ↓
 * DASHBOARD LOADS:
 * ────────────────
 * ├─ AuthGuard runs
 * ├─ Check: Is authenticated?
 * ├─ YES → Load component
 * └─ Component ngOnInit() runs
 *     ├─ Get current user
 *     ├─ If EMPLOYEE → Load employee leaves
 *     ├─ If MANAGER → Load pending leaves
 *     └─ Display data
 *         ↓
 * USER CLICKS "APPLY LEAVE"
 * ───────────────────────────
 * ├─ Navigate to /apply-leave
 * ├─ Form appears
 * ├─ User fills form
 * ├─ Validates form
 * ├─ Calculates working days
 * │  ├─ Exclude weekends
 * │  ├─ Exclude holidays
 * │  └─ Count remaining days
 * ├─ Sends to LeaveService.applyLeave()
 * ├─ POST to /api/leaves/apply
 * └─ Backend saves and returns leaveId
 *       ↓
 * FRONTEND SHOWS SUCCESS
 * ──────────────────────
 * ├─ Message: "Leave applied successfully!"
 * ├─ Redirect to dashboard
 * └─ New leave appears in list [PENDING]
 *       ↓
 * IF MANAGER:
 * ───────────
 * ├─ Open "Manager Approval"
 * ├─ See list of PENDING leaves
 * ├─ Click one to select
 * ├─ See details
 * ├─ Click "Approve" or "Reject"
 * ├─ If reject → Enter reason
 * ├─ Send to backend
 * ├─ Backend updates status
 * └─ List refreshes
 *       ↓
 * USER LOGS OUT
 * ──────────────
 * ├─ Click logout button
 * ├─ localStorage.removeItem('authToken')
 * ├─ localStorage.removeItem('currentUser')
 * ├─ Update BehaviorSubject to null
 * ├─ Navigate to /home
 * └─ User is logged out ✓
 */

// ═══════════════════════════════════════════════════════════════════════════════
// PART 15: FOR INTERVIEW - KEY POINTS TO MENTION
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * IN YOUR INTERVIEW, SAY THIS:
 * ──────────────────────────
 * 
 * "This is a **Leave Management System** built with **Angular 20**. 
 * It has **two user types**: Employees and Managers.
 * 
 * **A user's journey:**
 * 1. They sign up/login → AuthService saves token to browser storage
 * 2. Routes protect pages using AuthGuard
 * 3. If employee → They can apply for leave, see balance, view calendar
 * 4. If manager → They see pending leaves and approve/reject them
 * 
 * **Key technologies:**
 * - **Angular 20** (modern framework)
 * - **Standalone components** (no NgModule needed)
 * - **Reactive Forms** (advanced form validation)
 * - **RxJS Observables** (handle async data)
 * - **HTTP** (communicate with backend API)
 * - **Guards** (protect routes)
 * - **Jest** (unit testing)
 * 
 * **Main flow:**
 * - API calls → Services (LeaveService, AuthService)
 * - Components subscribe to get data
 * - When data arrives → Component re-renders
 * - User sees updated UI"
 * 
 * 
 * ADDITIONAL POINTS:
 * ──────────────────
 * 
 * 1. Authentication:
 *    - JWT token stored in localStorage
 *    - Every API request includes token in header (via interceptor)
 *    - Backend validates token for security
 * 
 * 2. Form Validation:
 *    - ReactiveFormsModule provides real-time validation
 *    - Custom validators for complex rules (email format,password length)
 *    - Form disabled until all validations pass
 * 
 * 3. State Management:
 *    - No NgRx/Redux (simple project, doesn't need it)
 *    - BehaviorSubject for reactive user data
 *    - localStorage for persistence
 * 
 * 4. Leave Calculation:
 *    - Excludes weekends (Sat, Sun)
 *    - Excludes company holidays
 *    - Only counts working days
 * 
 * 5. API Communication:
 *    - Services make HTTP calls
 *    - Components subscribe to services
 *    - Observable pattern for async operations
 * 
 * 6. Guards & Security:
 *    - AuthGuard: Checks if user is logged in
 *    - Routes blocked without token
 *    - Interceptor adds token to requests
 * 
 * 7. Manager Feature:
 *    - Managers see team's pending leaves
 *    - Can approve with one click
 *    - Can reject with reason
 *    - Leaves removed from list after action
 * 
 * 8. Error Handling:
 *    - API errors caught and shown to user
 *    - Loading states for better UX
 *    - Success/error messages
 * 
 * 9. Testing:
 *    - Jest unit tests for components
 *    - Mocks for services
 *    - Tests for form validation, API calls, routing
 * 
 * 10. UI/UX:
 *     - Tailwind CSS for styling
 *     - Loading spinners during API calls
 *     - Responsive design
 *     - Clear error messages
 */

// ═══════════════════════════════════════════════════════════════════════════════
// END OF DOCUMENTATION
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * THIS FILE CONTAINS COMPLETE EXPLANATION OF:
 * ─────────────────────────────────────────
 * ✓ Application bootstrap (main.ts)
 * ✓ Routing system (app.routes.ts)
 * ✓ Authentication service (login, signup, token management)
 * ✓ Auth guard (route protection)
 * ✓ Login component (form validation, API calls)
 * ✓ Dashboard component (displaying leaves)
 * ✓ Apply leave component (form, calculations)
 * ✓ Manager approval component (approve/reject leaves)
 * ✓ Leave service (all API calls)
 * ✓ HTTP request/response cycle
 * ✓ Data models (interfaces)
 * ✓ Observable & subscription pattern
 * ✓ Key keywords reference
 * ✓ Complete flow summary
 * ✓ Interview tips
 * 
 * 
 * TIPS FOR INTERVIEW:
 * ──────────────────
 * 1. Read this file multiple times before interview
 * 2. Understand the FLOW, not just individual components
 * 3. Be able to explain:
 *    - How user logs in
 *    - How leaves are applied
 *    - How managers approve/reject
 *    - How data flows from frontend to backend
 * 4. Have examples ready (25-Mar to 27-Mar = 3 working days)
 * 5. Be ready to explain keywords (Observable, subscribe, map, filter)
 * 6. Mention technologies used and why
 * 7. Show confidence in explaining the flow
 * 8. If stuck on a question, say you'll research it
 * 
 * 
 * GOOD LUCK! 🚀💼
 */

// This file is your interview preparation guide!
// Study it carefully and practice explaining out loud.
// The more you understand the system, the better you'll perform in the interview!
