# Payment Integration Setup for College Prototype

## Overview
This E-Commerce application includes both real Razorpay integration and a test payment system designed for college prototype demonstrations.

## Payment Options

### 1. Test Payment System (Recommended for College Prototype)
The test payment system allows you to simulate payment scenarios without requiring real Razorpay credentials or processing real payments.

**Features:**
- ✅ Simulate successful payments
- ✅ Simulate failed payments
- ✅ No real money involved
- ✅ Perfect for demonstrations
- ✅ No external API dependencies

**How to Use:**
1. Go through the normal checkout process
2. On the payment page, click "Test Payment (College Prototype)"
3. Choose either "Pay Successfully" or "Payment Failed"
4. The system will update the order status accordingly

### 2. Real Razorpay Integration
For production use or if you want to test with real Razorpay.

**Setup Required:**
1. Create a Razorpay account at [razorpay.com](https://razorpay.com)
2. Get your test API keys from the dashboard
3. Update `application.properties` with your keys:
   ```properties
   razorpay.key.id=your_actual_key_id
   razorpay.key.secret=your_actual_secret_key
   ```

## Current Configuration

The application is currently configured with test keys that work for demonstration purposes:

```properties
# Razorpay Configuration (Test Keys for College Prototype)
razorpay.key.id=rzp_test_1DP5mmOlF5G5ag
razorpay.key.secret=thisisasecretkey
```

## Payment Flow

### Test Payment Flow
1. **Checkout** → User fills shipping/billing address
2. **Payment Page** → Shows both real and test payment options
3. **Test Payment** → User chooses success/failure simulation
4. **Order Update** → System updates order status based on choice
5. **Confirmation** → User sees order confirmation or failure message

### Real Payment Flow
1. **Checkout** → User fills shipping/billing address
2. **Payment Page** → Razorpay payment gateway opens
3. **Payment Processing** → User completes payment on Razorpay
4. **Verification** → Backend verifies payment with Razorpay
5. **Order Update** → System updates order status based on verification
6. **Confirmation** → User sees order confirmation

## Testing Scenarios

### Successful Payment Test
1. Add products to cart
2. Proceed to checkout
3. Fill in address details
4. Click "Test Payment (College Prototype)"
5. Click "Pay Successfully"
6. Verify order status changes to "COMPLETED"

### Failed Payment Test
1. Add products to cart
2. Proceed to checkout
3. Fill in address details
4. Click "Test Payment (College Prototype)"
5. Click "Payment Failed"
6. Verify order status changes to "FAILED"

## Order Status Flow

```
CREATED → PROCESSING → CONFIRMED → SHIPPED → DELIVERED
    ↓
CANCELLED (if payment fails or user cancels)
```

## Payment Status Flow

```
PENDING → COMPLETED (successful payment)
    ↓
FAILED (failed payment)
```

## Troubleshooting

### Test Payment Not Working
- Ensure you're logged in as a customer
- Check that the order exists and belongs to the current user
- Verify the order is in "PROCESSING" status

### Real Payment Not Working
- Check Razorpay API keys in `application.properties`
- Ensure internet connectivity
- Verify Razorpay account is active
- Check application logs for error messages

### Order Status Not Updating
- Check database connection
- Verify order service is working
- Check application logs for errors

## Security Notes

### For College Prototype
- Test payment system bypasses real payment verification
- No real money is involved
- Perfect for educational demonstrations

### For Production
- Always use real Razorpay credentials
- Implement proper error handling
- Add payment logging and monitoring
- Use HTTPS for all payment-related pages

## API Endpoints

### Test Payment Endpoints
- `GET /payment/test/{orderId}` - Show test payment page
- `POST /payment/test/success/{orderId}` - Simulate successful payment
- `POST /payment/test/fail/{orderId}` - Simulate failed payment

### Real Payment Endpoints
- `GET /checkout/payment/{orderId}` - Show real payment page
- `POST /checkout/payment/verify` - Verify real payment

## Database Updates

When payments are processed (test or real), the following fields are updated:

### Order Table
- `payment_status` - Updated to COMPLETED/FAILED
- `razorpay_payment_id` - Payment ID from Razorpay
- `status` - Updated to CONFIRMED if payment successful

## Logging

The application logs payment-related activities:
- Payment order creation
- Payment verification attempts
- Payment success/failure
- Error messages

Check logs for debugging payment issues.

## Demo Instructions

For college prototype demonstration:

1. **Setup**: Start the application with the provided configuration
2. **Register**: Create a customer account
3. **Browse**: Add products to cart
4. **Checkout**: Fill in address details
5. **Payment**: Use "Test Payment" option
6. **Demonstrate**: Show both success and failure scenarios
7. **Admin**: Show admin panel for order management

This setup provides a complete e-commerce payment demonstration without requiring real payment processing.
