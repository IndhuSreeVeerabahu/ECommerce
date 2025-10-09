# 🔧 Razorpay Setup Guide for Student Projects

## 🚨 **Why Payment Gateway is Not Available**

The current test keys in the application are **placeholder keys**, not real Razorpay keys. That's why you're getting the "Payment gateway not available" message.

## 🚀 **Quick Fix - Get Real Test Keys**

### **Step 1: Create Razorpay Account (Free)**
1. Go to [Razorpay Dashboard](https://dashboard.razorpay.com)
2. Click "Sign Up" (it's completely free)
3. Use your email and create a password
4. Verify your email

### **Step 2: Get Test API Keys**
1. After login, go to **Settings** → **API Keys**
2. You'll see **Test Mode** keys (these are free and safe)
3. Copy your **Key ID** (starts with `rzp_test_`)
4. Copy your **Key Secret**

### **Step 3: Update Your Application**
Replace the keys in `src/main/resources/application.properties`:

```properties
# Replace with your actual test keys
razorpay.key.id=rzp_test_YOUR_ACTUAL_KEY_ID
razorpay.key.secret=YOUR_ACTUAL_SECRET_KEY
```

### **Step 4: Restart Application**
```bash
mvn spring-boot:run
```

## 🎯 **Alternative: Use Test Payment Simulation**

If you don't want to create a Razorpay account, you can use the **Test Payment Simulation**:

1. **Complete checkout** (add products, fill addresses)
2. **Click "Test Payment (Student Project)"** instead of "Pay ₹X.XX"
3. **Choose scenario**:
   - "Pay Successfully" → Simulates successful payment
   - "Payment Failed" → Simulates failed payment
4. **Order status updates** automatically

## 💳 **Test Card Details (When Using Real Razorpay)**

Once you have real test keys, use these test cards:

```
Card Number: 4111 1111 1111 1111
Expiry: 12/25
CVV: 123
Name: Test User
```

## 🔍 **Why This Happens**

- **Placeholder Keys**: The current keys are just examples
- **Real Integration**: Razorpay requires real API keys (even for test mode)
- **Free Test Mode**: Razorpay test mode is completely free
- **No Real Money**: Test mode never charges real money

## 🎓 **For Student Projects**

### **Option 1: Real Razorpay Test Mode (Recommended)**
- ✅ **Real Integration**: Actual Razorpay SDK
- ✅ **Professional**: Shows real payment integration skills
- ✅ **Free**: No charges or fees
- ✅ **Safe**: No real money involved

### **Option 2: Test Payment Simulation**
- ✅ **No Setup**: Works immediately
- ✅ **Safe**: No external dependencies
- ✅ **Learning**: Shows payment flow logic
- ✅ **Demo Ready**: Perfect for presentations

## 🚀 **Quick Start (Choose One)**

### **For Real Razorpay Integration:**
1. Create free Razorpay account
2. Get test API keys
3. Update application.properties
4. Restart application
5. Use test card details

### **For Simulation Mode:**
1. Use existing application
2. Click "Test Payment (Student Project)"
3. Choose success/failure scenario
4. Verify order updates

## 📱 **Both Options Work Perfectly**

- **Real Razorpay**: Shows actual payment gateway integration
- **Simulation**: Shows payment flow and order management
- **Both**: Demonstrate complete e-commerce functionality

## 🎯 **Recommendation**

For a **student project**, I recommend:

1. **Use Test Payment Simulation** for immediate demo
2. **Get real Razorpay test keys** for complete integration
3. **Show both options** in your presentation

This way you can demonstrate:
- ✅ Complete payment flow
- ✅ Real payment gateway integration
- ✅ Professional development skills
- ✅ Understanding of payment systems

## 🔧 **Troubleshooting**

### **If Real Razorpay Still Doesn't Work:**
1. Check if keys are correct
2. Verify internet connection
3. Check application logs
4. Use Test Payment Simulation instead

### **If Test Payment Simulation Doesn't Work:**
1. Make sure you're logged in as customer
2. Check if order exists
3. Verify cart has items
4. Check application logs

**Both payment methods will work perfectly for your student project!** 🎉

