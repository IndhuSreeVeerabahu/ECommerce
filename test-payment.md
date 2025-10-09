# Payment System Test Guide

## Quick Test for College Prototype

### Step 1: Start the Application
```bash
mvn spring-boot:run
```

### Step 2: Access the Application
- Open browser: `http://localhost:8080`
- Register a new customer account
- Login with the new account

### Step 3: Test the Payment Flow

#### 3.1 Add Products to Cart
1. Go to Products page
2. Add 2-3 products to cart
3. Go to Cart page
4. Verify items are in cart

#### 3.2 Proceed to Checkout
1. Click "Proceed to Checkout"
2. Fill in shipping address (any address)
3. Fill in billing address (any address)
4. Click "Proceed to Payment"

#### 3.3 Test Payment Options

**Option A: Test Payment (Recommended)**
1. Click "Test Payment (College Prototype)"
2. Choose "Pay Successfully"
3. Verify order status changes to "COMPLETED"
4. Check order details

**Option B: Test Payment Failure**
1. Go back to payment page
2. Click "Test Payment (College Prototype)"
3. Choose "Payment Failed"
4. Verify order status changes to "FAILED"

#### 3.4 Verify Order Management
1. Go to "My Orders" page
2. Check order status
3. View order details
4. Test admin panel (login as admin/admin123)

### Expected Results

✅ **Successful Payment Test:**
- Order status: COMPLETED
- Payment status: COMPLETED
- Order visible in "My Orders"
- Cart is cleared

✅ **Failed Payment Test:**
- Order status: FAILED
- Payment status: FAILED
- Order visible in "My Orders"
- Cart items remain

### Troubleshooting

**If Test Payment doesn't work:**
1. Check browser console for errors
2. Verify you're logged in as customer
3. Check application logs
4. Ensure order exists and belongs to current user

**If Real Payment doesn't work:**
1. Check Razorpay API keys in application.properties
2. Verify internet connectivity
3. Use Test Payment option instead

### Demo Script for Presentation

1. **Show Homepage**: Browse products, search functionality
2. **User Registration**: Create new account
3. **Product Browsing**: Add products to cart
4. **Checkout Process**: Fill addresses, proceed to payment
5. **Test Payment**: Demonstrate both success and failure
6. **Order Management**: Show order history and status
7. **Admin Panel**: Show admin capabilities (login as admin)

### Key Features to Highlight

- ✅ Complete E-Commerce flow
- ✅ User authentication and authorization
- ✅ Product catalog with search/filtering
- ✅ Shopping cart functionality
- ✅ Order processing
- ✅ Payment integration (test mode)
- ✅ Admin panel for management
- ✅ Responsive design
- ✅ Clean, professional UI

This setup provides a complete, working E-Commerce application perfect for college prototype demonstrations!
