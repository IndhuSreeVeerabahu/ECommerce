# Cashfree Payment Gateway Setup Guide

## Overview
This guide will help you set up Cashfree payment gateway for your E-Commerce college project.

## Prerequisites
- Java 8 or higher
- Spring Boot application
- MySQL database
- Internet connection for API calls

## Step 1: Get Cashfree Credentials

### For College Project (Sandbox Mode)
1. Visit: https://merchant.cashfree.com/merchant/pg#/app/api-keys
2. Sign up for a free account
3. Navigate to API Keys section
4. Copy your App ID and Secret Key

### Current Configuration
The application is already configured with working sandbox credentials:
- **App ID**: `TEST108283821957fe1153788f32479528382801`
- **Secret Key**: `cfsk_ma_test_dddc2fa7d13c09a0a5f0c9c88f26f678_f1e97d01`
- **Environment**: `SANDBOX`

## Step 2: Configuration

### Application Properties
The following properties are configured in `application.properties`:

```properties
# Cashfree Payment Gateway Configuration
cashfree.app.id=TEST108283821957fe1153788f32479528382801
cashfree.secret.key=cfsk_ma_test_dddc2fa7d13c09a0a5f0c9c88f26f678_f1e97d01
cashfree.environment=SANDBOX
cashfree.api.version=2023-08-01
cashfree.return.url=http://localhost:8080/payment/success
cashfree.notify.url=http://localhost:8080/payment/webhook
```

## Step 3: Payment Flow

### 1. Create Payment Session
When a user clicks "Pay", the system:
- Creates a payment session with Cashfree
- Generates a unique payment session ID
- Redirects user to Cashfree checkout

### 2. Payment Processing
- User completes payment on Cashfree's secure page
- Cashfree processes the payment
- User is redirected back to your application

### 3. Payment Verification
- System verifies payment with Cashfree API
- Updates order status to COMPLETED
- Shows success message to user

## Step 4: Testing

### Test Payment Options
1. **Real Cashfree Integration**: Use the main "Pay" button
2. **Test Payment Simulation**: Use "Test Payment" button for demonstration

### Test Cards (Sandbox Mode)
Use these test card details for testing:

**Successful Payment:**
- Card Number: `4111 1111 1111 1111`
- Expiry: Any future date
- CVV: Any 3 digits
- Name: Any name

**Failed Payment:**
- Card Number: `4000 0000 0000 0002`
- Expiry: Any future date
- CVV: Any 3 digits

## Step 5: Features Implemented

### ✅ Completed Features
- [x] Cashfree API integration
- [x] Payment session creation
- [x] Payment verification
- [x] Webhook handling
- [x] Test payment simulation
- [x] Error handling and logging
- [x] Frontend integration
- [x] Order status updates

### 🔧 Technical Implementation
- **PaymentService**: Handles all Cashfree API calls
- **PaymentController**: Manages payment endpoints
- **Frontend**: Cashfree SDK integration
- **Database**: Order and payment status tracking

## Step 6: Running the Application

1. **Start the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

2. **Access the application:**
   - URL: http://localhost:8080
   - Login with test credentials
   - Add products to cart
   - Proceed to checkout
   - Test payment integration

## Step 7: Troubleshooting

### Common Issues

1. **Payment Session Creation Failed**
   - Check internet connection
   - Verify API credentials
   - Check application logs

2. **Payment Verification Failed**
   - Ensure order exists
   - Check payment ID format
   - Verify API response

3. **Frontend Issues**
   - Check browser console for errors
   - Ensure Cashfree SDK is loaded
   - Verify payment session ID

### Debug Mode
Enable debug logging by setting:
```properties
logging.level.com.example.E_Commerce=DEBUG
```

## Step 8: Production Setup

### For Production Deployment
1. Get production credentials from Cashfree
2. Update environment variables:
   ```properties
   cashfree.environment=PRODUCTION
   cashfree.app.id=YOUR_PRODUCTION_APP_ID
   cashfree.secret.key=YOUR_PRODUCTION_SECRET_KEY
   ```
3. Update return URLs to production domain
4. Enable webhook signature verification

## Step 9: Security Considerations

### For College Project
- Sandbox credentials are safe to use
- No real money transactions
- Test data only

### For Production
- Never expose secret keys
- Use environment variables
- Enable webhook signature verification
- Implement proper error handling

## Support

### Documentation
- Cashfree API Docs: https://docs.cashfree.com/
- Cashfree Dashboard: https://merchant.cashfree.com/

### Test Support
- Use Test Payment option for demonstrations
- Check application logs for debugging
- Verify database order status

## Success Criteria

Your payment integration is working if:
- ✅ Payment session is created successfully
- ✅ User can complete payment on Cashfree page
- ✅ Payment verification works
- ✅ Order status updates to COMPLETED
- ✅ Success message is displayed
- ✅ Test payment simulation works

## Next Steps

1. Test the complete payment flow
2. Verify order status updates
3. Test error scenarios
4. Prepare for project demonstration
5. Document any customizations made

---

**Note**: This setup is specifically designed for college project demonstration. For production use, additional security measures and proper credential management are required.
