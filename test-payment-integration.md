# Payment Integration Test Guide

## Quick Test Steps

### 1. Start the Application
```bash
./mvnw spring-boot:run
```

### 2. Access the Application
- Open browser: http://localhost:8080
- Login with test credentials or register new account

### 3. Test Payment Flow

#### Option A: Real Cashfree Integration
1. Add products to cart
2. Proceed to checkout
3. Click "Pay ₹[amount]" button
4. Complete payment on Cashfree page
5. Verify order status updates

#### Option B: Test Payment Simulation
1. Add products to cart
2. Proceed to checkout
3. Click "Test Payment (Student Project)" button
4. Click "Simulate Success" or "Simulate Failure"
5. Verify order status updates

### 4. Expected Results

#### Successful Payment:
- ✅ Order status: COMPLETED
- ✅ Payment status: COMPLETED
- ✅ Success message displayed
- ✅ Order visible in "My Orders"

#### Failed Payment:
- ❌ Order status: FAILED
- ❌ Payment status: FAILED
- ❌ Error message displayed
- ❌ Option to retry payment

### 5. Test Cards (Sandbox Mode)

**Successful Payment:**
- Card: 4111 1111 1111 1111
- Expiry: 12/25
- CVV: 123
- Name: Test User

**Failed Payment:**
- Card: 4000 0000 0000 0002
- Expiry: 12/25
- CVV: 123
- Name: Test User

### 6. Debug Information

Check application logs for:
- Payment session creation
- API calls to Cashfree
- Payment verification
- Order status updates

### 7. Troubleshooting

If payment fails:
1. Check internet connection
2. Verify Cashfree credentials
3. Check application logs
4. Use Test Payment option
5. Verify database connection

## Success Criteria

Your payment integration is working if:
- ✅ Application starts without errors
- ✅ Payment page loads correctly
- ✅ Cashfree checkout opens
- ✅ Payment can be completed
- ✅ Order status updates
- ✅ Success/failure messages show

## For College Project Demo

Use the **Test Payment** option for reliable demonstration:
1. More predictable results
2. No external API dependencies
3. Faster testing
4. Better for presentations

---

**Ready for testing!** 🚀
