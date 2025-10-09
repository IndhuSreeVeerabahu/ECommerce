# 💳 Razorpay Test Card Details

## 🧪 **For Student Project Testing**

### ✅ **Successful Payment Cards**

#### **Visa Card**
```
Card Number: 4111 1111 1111 1111
Expiry Date: 12/25 (or any future date)
CVV: 123 (or any 3 digits)
Name: Test User
```

#### **Mastercard**
```
Card Number: 5555 5555 5555 4444
Expiry Date: 12/25 (or any future date)
CVV: 123 (or any 3 digits)
Name: Test User
```

#### **American Express**
```
Card Number: 3782 822463 10005
Expiry Date: 12/25 (or any future date)
CVV: 1234 (or any 4 digits)
Name: Test User
```

### ❌ **Failed Payment Cards**

#### **Insufficient Funds**
```
Card Number: 4000 0000 0000 0002
Expiry Date: 12/25
CVV: 123
Name: Test User
```

#### **Card Declined**
```
Card Number: 4000 0000 0000 0069
Expiry Date: 12/25
CVV: 123
Name: Test User
```

### 📱 **UPI Test Details**

#### **Successful UPI**
```
UPI ID: test@razorpay
```

#### **Failed UPI**
```
UPI ID: fail@razorpay
```

### 🏦 **Net Banking Test**

#### **Successful Net Banking**
- Select any bank from the list
- Use any credentials (test mode)

#### **Failed Net Banking**
- Select "Test Bank" if available
- Use any credentials

## 🎯 **How to Use These Cards**

### **Step 1: Complete Checkout**
1. Add products to cart
2. Fill shipping/billing address
3. Click "Proceed to Payment"

### **Step 2: Choose Payment Method**
1. Click "Pay ₹X.XX" button
2. Razorpay payment window opens
3. Select "Card" payment method

### **Step 3: Enter Test Card Details**
1. Enter any of the successful card numbers above
2. Use any future expiry date
3. Enter any CVV
4. Enter any name

### **Step 4: Complete Payment**
1. Click "Pay Now"
2. Payment will be processed (test mode)
3. You'll see success message
4. Order status will update

## 🔍 **What Happens in Test Mode**

### ✅ **No Real Money**
- No actual bank transactions
- No money deducted from your account
- All payments are simulated

### ✅ **Real Integration**
- Uses actual Razorpay SDK
- Real payment flow
- Proper signature verification
- Order status updates

### ✅ **Perfect for Learning**
- Test all payment scenarios
- Learn payment integration
- Practice error handling
- Understand payment flow

## 🎓 **For Student Projects**

### **What You Can Demonstrate**
1. **Real Payment Integration**: Not just mock
2. **Complete E-Commerce Flow**: Cart to order
3. **Payment Processing**: Success and failure
4. **Order Management**: Status updates
5. **Admin Features**: Product and order management

### **Interview Points**
- "Integrated real Razorpay SDK"
- "Implemented proper payment verification"
- "Added test mode for safe demonstration"
- "Complete payment flow with error handling"
- "Real-world payment integration experience"

## 🚀 **Quick Test Flow**

1. **Start Application**: `mvn spring-boot:run`
2. **Register User**: Create account
3. **Add Products**: Add to cart
4. **Checkout**: Fill addresses
5. **Payment**: Use test card details
6. **Verify**: Check order status

## 📝 **Important Notes**

- ✅ **Safe to Use**: No real money involved
- ✅ **Free**: No charges or fees
- ✅ **Real Integration**: Actual Razorpay SDK
- ✅ **Learning Purpose**: Perfect for students
- ✅ **Portfolio Ready**: Professional quality

**Use these test cards to safely demonstrate your payment integration skills!** 🎉
