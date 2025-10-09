# Razorpay Production Setup Guide

## 🚀 Step-by-Step Production Setup

### 1. **Get Razorpay Live Keys**

#### Step 1: Complete KYC
1. Go to [Razorpay Dashboard](https://dashboard.razorpay.com)
2. Complete KYC verification:
   - Upload business documents
   - Provide bank account details
   - Submit identity verification
3. Wait for approval (1-2 business days)

#### Step 2: Get Live API Keys
1. In Razorpay Dashboard → Settings → API Keys
2. Switch from "Test Mode" to "Live Mode"
3. Copy your **Live Key ID** (starts with `rzp_live_`)
4. Copy your **Live Secret Key**

### 2. **Update Configuration**

#### Update Environment Variables
```bash
# Set your live Razorpay keys
export RAZORPAY_KEY_ID=rzp_live_your_actual_live_key_id
export RAZORPAY_KEY_SECRET=your_actual_live_secret_key
```

#### Or update production.env file:
```bash
RAZORPAY_KEY_ID=rzp_live_your_actual_live_key_id
RAZORPAY_KEY_SECRET=your_actual_live_secret_key
```

### 3. **Configure Webhooks (Important!)**

#### In Razorpay Dashboard:
1. Go to Settings → Webhooks
2. Add new webhook:
   - **URL**: `https://yourdomain.com/checkout/webhook/razorpay`
   - **Events**: Select these events:
     - `payment.captured`
     - `payment.failed`
     - `order.paid`
3. Copy the webhook secret

### 4. **Test Production Integration**

#### Test Payment Flow:
1. **Start with small amount** (₹1-10)
2. **Use real payment methods**:
   - Credit/Debit cards
   - UPI
   - Net Banking
   - Wallets
3. **Verify payment processing**:
   - Check order status updates
   - Verify webhook notifications
   - Confirm database updates

### 5. **Production Deployment**

#### Using Docker:
```bash
# Build with production profile
docker build -t ecommerce:prod .

# Run with environment variables
docker run -d \
  -e RAZORPAY_KEY_ID=rzp_live_your_key \
  -e RAZORPAY_KEY_SECRET=your_secret \
  -e SPRING_PROFILES_ACTIVE=prod \
  -p 8080:8080 \
  ecommerce:prod
```

#### Using Docker Compose:
```bash
# Update production.env with your keys
# Then run:
docker-compose -f docker-compose.yml up -d
```

### 6. **Security Checklist**

#### ✅ Production Security:
- [ ] Use HTTPS (SSL certificate)
- [ ] Set strong admin password
- [ ] Use environment variables for secrets
- [ ] Enable webhook signature verification
- [ ] Set up proper logging
- [ ] Configure firewall rules
- [ ] Regular security updates

### 7. **Monitoring & Alerts**

#### Set up monitoring for:
- Payment success/failure rates
- Webhook delivery status
- Application health
- Database performance
- Error rates

#### Razorpay Dashboard Monitoring:
- Go to Dashboard → Analytics
- Monitor payment trends
- Check failed payments
- Review webhook logs

### 8. **Common Issues & Solutions**

#### Issue 1: Payment Not Processing
**Solution:**
- Check if live keys are correct
- Verify webhook URL is accessible
- Check application logs
- Ensure HTTPS is enabled

#### Issue 2: Webhook Not Working
**Solution:**
- Verify webhook URL in Razorpay dashboard
- Check webhook signature verification
- Ensure server is accessible from internet
- Check firewall settings

#### Issue 3: Order Status Not Updating
**Solution:**
- Check webhook endpoint logs
- Verify database connection
- Check order service implementation
- Review payment verification logic

### 9. **Testing Checklist**

#### Before Going Live:
- [ ] Test with small amounts (₹1-10)
- [ ] Test all payment methods
- [ ] Verify webhook notifications
- [ ] Check order status updates
- [ ] Test payment failures
- [ ] Verify refund process
- [ ] Check admin panel functionality
- [ ] Test on mobile devices

### 10. **Production URLs**

#### Important URLs to configure:
- **Webhook URL**: `https://yourdomain.com/checkout/webhook/razorpay`
- **Return URL**: `https://yourdomain.com/orders`
- **Cancel URL**: `https://yourdomain.com/cart`

### 11. **Backup & Recovery**

#### Database Backup:
```bash
# Daily backup script
mysqldump -u ecommerce_user -p ECommerceDB > backup_$(date +%Y%m%d).sql
```

#### Application Backup:
```bash
# Backup application and logs
tar -czf app_backup_$(date +%Y%m%d).tar.gz /opt/ecommerce/
```

### 12. **Performance Optimization**

#### For High Traffic:
- Use connection pooling
- Enable caching
- Optimize database queries
- Use CDN for static assets
- Set up load balancing

### 13. **Support & Documentation**

#### Razorpay Support:
- **Documentation**: [Razorpay Docs](https://razorpay.com/docs/)
- **Support**: [Razorpay Support](https://razorpay.com/support/)
- **Status Page**: [Razorpay Status](https://status.razorpay.com/)

#### Your Application:
- Monitor application logs
- Set up error tracking
- Configure alerts
- Document troubleshooting steps

## 🎯 Quick Production Checklist

### Pre-Launch:
- [ ] Razorpay account approved
- [ ] Live API keys configured
- [ ] Webhooks set up
- [ ] HTTPS enabled
- [ ] Test payments working
- [ ] Monitoring configured
- [ ] Backup strategy in place

### Post-Launch:
- [ ] Monitor payment success rates
- [ ] Check webhook delivery
- [ ] Review error logs
- [ ] Monitor performance
- [ ] Update documentation
- [ ] Train support team

## 🚨 Important Notes

1. **Never use test keys in production**
2. **Always use HTTPS for webhooks**
3. **Test with small amounts first**
4. **Monitor webhook delivery**
5. **Keep backups updated**
6. **Set up proper logging**
7. **Have rollback plan ready**

Your Razorpay integration will work perfectly in production with these steps! 🎉
