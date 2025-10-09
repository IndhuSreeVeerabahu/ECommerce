# Cashfree Payment Test Guide

## 🧪 Testing the Payment Integration

### Prerequisites
1. Application is running on `http://localhost:8080`
2. Database is set up and running
3. User account is created

### Test Steps

#### 1. Start the Application
```bash
# Using Maven wrapper (if Java is configured)
.\mvnw.cmd spring-boot:run

# Or using the provided batch file
run-app.bat
```

#### 2. Access the Application
- Open browser: `http://localhost:8080`
- Login with admin credentials:
  - Username: `admin`
  - Password: `admin123`

#### 3. Create Test Products (Admin)
1. Go to Admin Dashboard
2. Add some test products
3. Set prices and stock

#### 4. Test Payment Flow (Customer)
1. **Register/Login as Customer**
   - Create new customer account
   - Or login with existing customer

2. **Add Products to Cart**
   - Browse products
   - Add items to cart
   - Go to cart page

3. **Checkout Process**
   - Click "Proceed to Checkout"
   - Fill shipping/billing address
   - Click "Place Order"

4. **Payment Page**
   - You'll see two options:
     - **Real Cashfree Payment**: Uses actual Cashfree test API
     - **Test Payment**: Simulates payment for demo

#### 5. Test Real Cashfree Payment
1. Click "Pay ₹[amount]" button
2. You'll be redirected to Cashfree payment page
3. Use test card details:
   ```
   Card: 4111 1111 1111 1111
   Expiry: 12/25
   CVV: 123
   Name: Test User
   ```
4. Complete payment
5. You'll be redirected back to success page

#### 6. Test Payment Simulation
1. Click "Test Payment (Student Project)" button
2. Choose "Pay Successfully" or "Payment Failed"
3. Order status will be updated accordingly

### Expected Results

#### Successful Payment
- Order status: `COMPLETED`
- Payment status: `COMPLETED`
- Order appears in "My Orders"
- Admin can see order in admin panel

#### Failed Payment
- Order status: `PENDING` or `FAILED`
- Payment status: `FAILED`
- User can retry payment

### Troubleshooting

#### If Cashfree API Fails
- System automatically falls back to test mode
- Use "Test Payment" option
- Check console logs for error details

#### If Payment Page Doesn't Load
- Check if application is running
- Verify database connection
- Check browser console for errors

### Demo Script for Presentations

1. **Show Homepage**: Browse products, search functionality
2. **User Registration**: Create new customer account
3. **Add to Cart**: Add multiple products
4. **Checkout**: Fill shipping/billing address
5. **Payment Options**: Show both real and test payment
6. **Test Payment**: Demonstrate test payment flow
7. **Order Management**: Show order status updates
8. **Admin Panel**: Show admin capabilities

### Key Features to Highlight

- ✅ **Real Cashfree Integration**: Actual SDK integration
- ✅ **Test Environment**: Safe for demonstrations
- ✅ **Complete Flow**: End-to-end payment processing
- ✅ **Error Handling**: Graceful fallbacks
- ✅ **Mobile Responsive**: Works on all devices
- ✅ **Security**: Proper validation and verification

## 🎉 Success!
Your Cashfree payment integration is ready for college project demonstration!
