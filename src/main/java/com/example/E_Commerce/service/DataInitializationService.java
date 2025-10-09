package com.example.E_Commerce.service;

import com.example.E_Commerce.model.Product;
import com.example.E_Commerce.model.ProductCategory;
import com.example.E_Commerce.model.User;
import com.example.E_Commerce.model.Role;
import com.example.E_Commerce.repository.ProductRepository;
import com.example.E_Commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
        initializeProducts();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            // Create admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@ecommerce.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRole(Role.ADMIN);
            admin.setEnabled(true);
            userRepository.save(admin);

            // Create sample customer
            User customer = new User();
            customer.setUsername("customer");
            customer.setEmail("customer@ecommerce.com");
            customer.setPassword(passwordEncoder.encode("customer123"));
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setPhoneNumber("+1234567890");
            customer.setRole(Role.CUSTOMER);
            customer.setEnabled(true);
            userRepository.save(customer);
        }
    }

    public void initializeProducts() {
        // Force reload products for testing - remove the condition to always add products
        // if (productRepository.count() == 0) {
        System.out.println("Initializing products... Current count: " + productRepository.count());
            List<Product> sampleProducts = Arrays.asList(
                // Electronics - 15 products
                createProduct("iPhone 15 Pro", "Latest iPhone with advanced camera system", 
                    new BigDecimal("99999"), "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400", 
                    50, ProductCategory.ELECTRONICS),
                
                createProduct("MacBook Pro 16\"", "Powerful laptop for professionals", 
                    new BigDecimal("249999"), "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=400", 
                    25, ProductCategory.ELECTRONICS),
                
                createProduct("Samsung Galaxy S24", "Premium Android smartphone", 
                    new BigDecimal("89999"), "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=400", 
                    40, ProductCategory.ELECTRONICS),
                
                createProduct("Sony WH-1000XM5", "Noise-cancelling wireless headphones", 
                    new BigDecimal("39999"), "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400", 
                    30, ProductCategory.ELECTRONICS),

                createProduct("iPad Air", "Lightweight tablet with M2 chip", 
                    new BigDecimal("59999"), "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=400", 
                    35, ProductCategory.ELECTRONICS),

                createProduct("Dell XPS 13", "Ultrabook with stunning display", 
                    new BigDecimal("129999"), "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400", 
                    20, ProductCategory.ELECTRONICS),

                createProduct("AirPods Pro", "Wireless earbuds with noise cancellation", 
                    new BigDecimal("24999"), "https://images.unsplash.com/photo-1606220945770-b5b6c2c55bf1?w=400", 
                    60, ProductCategory.ELECTRONICS),

                createProduct("Samsung 4K TV", "55-inch Smart TV with HDR", 
                    new BigDecimal("79999"), "https://images.unsplash.com/photo-1593359677879-a4bb92f829d1?w=400", 
                    15, ProductCategory.ELECTRONICS),

                createProduct("Nintendo Switch", "Gaming console for home and portable play", 
                    new BigDecimal("29999"), "https://images.unsplash.com/photo-1606144042614-b2417e99c4e3?w=400", 
                    25, ProductCategory.ELECTRONICS),

                createProduct("Canon EOS R5", "Professional mirrorless camera", 
                    new BigDecimal("389999"), "https://images.unsplash.com/photo-1502920917128-1aa500764cbd?w=400", 
                    10, ProductCategory.ELECTRONICS),

                createProduct("Gaming Laptop", "High-performance gaming laptop with RTX 4070", 
                    new BigDecimal("189999"), "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400", 
                    12, ProductCategory.ELECTRONICS),

                createProduct("Smart Watch", "Fitness tracking smartwatch with GPS", 
                    new BigDecimal("19999"), "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400", 
                    45, ProductCategory.ELECTRONICS),

                createProduct("Wireless Speaker", "Portable Bluetooth speaker with 360° sound", 
                    new BigDecimal("12999"), "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=400", 
                    30, ProductCategory.ELECTRONICS),

                createProduct("Mechanical Keyboard", "RGB gaming keyboard with blue switches", 
                    new BigDecimal("14999"), "https://images.unsplash.com/photo-1541140532154-b024d705b90a?w=400", 
                    40, ProductCategory.ELECTRONICS),

                createProduct("Gaming Mouse", "High-precision gaming mouse with RGB lighting", 
                    new BigDecimal("7999"), "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=400", 
                    50, ProductCategory.ELECTRONICS),

                // Clothing - 12 products
                createProduct("Nike Air Max 270", "Comfortable running shoes", 
                    new BigDecimal("14999"), "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400", 
                    100, ProductCategory.CLOTHING),
                
                createProduct("Levi's 501 Jeans", "Classic straight-fit jeans", 
                    new BigDecimal("7999"), "https://images.unsplash.com/photo-1542272604-787c3835535d?w=400", 
                    75, ProductCategory.CLOTHING),
                
                createProduct("Adidas T-Shirt", "Comfortable cotton t-shirt", 
                    new BigDecimal("2999"), "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400", 
                    200, ProductCategory.CLOTHING),

                createProduct("Nike Hoodie", "Warm and comfortable hoodie", 
                    new BigDecimal("8999"), "https://images.unsplash.com/photo-1556821840-3a63f95609a7?w=400", 
                    60, ProductCategory.CLOTHING),

                createProduct("Leather Jacket", "Premium genuine leather jacket", 
                    new BigDecimal("29999"), "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400", 
                    25, ProductCategory.CLOTHING),

                createProduct("Summer Dress", "Elegant floral summer dress", 
                    new BigDecimal("6999"), "https://images.unsplash.com/photo-1515372039744-b8f02a3ae446?w=400", 
                    40, ProductCategory.CLOTHING),

                createProduct("Running Shorts", "Lightweight athletic shorts", 
                    new BigDecimal("3999"), "https://images.unsplash.com/photo-1594633312681-425c7b97ccd1?w=400", 
                    80, ProductCategory.CLOTHING),

                createProduct("Winter Coat", "Warm winter coat with hood", 
                    new BigDecimal("19999"), "https://images.unsplash.com/photo-1544022613-e87ca75a784a?w=400", 
                    30, ProductCategory.CLOTHING),

                createProduct("Business Suit", "Professional men's business suit", 
                    new BigDecimal("39999"), "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400", 
                    20, ProductCategory.CLOTHING),

                createProduct("Casual Sneakers", "Comfortable everyday sneakers", 
                    new BigDecimal("7999"), "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=400", 
                    90, ProductCategory.CLOTHING),

                createProduct("Swimwear Set", "Stylish bikini set for summer", 
                    new BigDecimal("4999"), "https://images.unsplash.com/photo-1594633312681-425c7b97ccd1?w=400", 
                    35, ProductCategory.CLOTHING),

                createProduct("Formal Shirt", "Crisp white formal dress shirt", 
                    new BigDecimal("5999"), "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=400", 
                    55, ProductCategory.CLOTHING),

                // Books - 10 products
                createProduct("The Great Gatsby", "Classic American novel by F. Scott Fitzgerald", 
                    new BigDecimal("1299"), "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400", 
                    150, ProductCategory.BOOKS),
                
                createProduct("To Kill a Mockingbird", "Harper Lee's masterpiece", 
                    new BigDecimal("1499"), "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400", 
                    120, ProductCategory.BOOKS),

                createProduct("1984", "George Orwell's dystopian classic", 
                    new BigDecimal("1399"), "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400", 
                    100, ProductCategory.BOOKS),

                createProduct("Pride and Prejudice", "Jane Austen's romantic novel", 
                    new BigDecimal("1199"), "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400", 
                    80, ProductCategory.BOOKS),

                createProduct("The Catcher in the Rye", "J.D. Salinger's coming-of-age story", 
                    new BigDecimal("1299"), "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400", 
                    90, ProductCategory.BOOKS),

                createProduct("Harry Potter Series", "Complete 7-book collection", 
                    new BigDecimal("8999"), "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400", 
                    25, ProductCategory.BOOKS),

                createProduct("Programming Guide", "Learn Java programming from scratch", 
                    new BigDecimal("4999"), "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400", 
                    40, ProductCategory.BOOKS),

                createProduct("Cookbook Collection", "500 recipes from around the world", 
                    new BigDecimal("2999"), "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400", 
                    60, ProductCategory.BOOKS),

                createProduct("History of Art", "Comprehensive art history textbook", 
                    new BigDecimal("7999"), "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400", 
                    30, ProductCategory.BOOKS),

                createProduct("Self-Help Guide", "Transform your life in 30 days", 
                    new BigDecimal("1999"), "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400", 
                    70, ProductCategory.BOOKS),

                // Home & Garden - 10 products
                createProduct("Coffee Maker", "Automatic drip coffee maker", 
                    new BigDecimal("8999"), "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400", 
                    60, ProductCategory.HOME_AND_GARDEN),
                
                createProduct("Garden Tools Set", "Complete gardening tool set", 
                    new BigDecimal("4999"), "https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=400", 
                    40, ProductCategory.HOME_AND_GARDEN),

                createProduct("Smart Home Hub", "Control your smart devices", 
                    new BigDecimal("19999"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400", 
                    20, ProductCategory.HOME_AND_GARDEN),

                createProduct("Kitchen Blender", "High-speed professional blender", 
                    new BigDecimal("14999"), "https://images.unsplash.com/photo-1585515656519-2b4b1b0b0b0b?w=400", 
                    35, ProductCategory.HOME_AND_GARDEN),

                createProduct("Indoor Plants", "Set of 5 air-purifying plants", 
                    new BigDecimal("3999"), "https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=400", 
                    50, ProductCategory.HOME_AND_GARDEN),

                createProduct("LED Light Strips", "Smart RGB LED strips for home", 
                    new BigDecimal("2999"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400", 
                    80, ProductCategory.HOME_AND_GARDEN),

                createProduct("Vacuum Cleaner", "Cordless stick vacuum with HEPA filter", 
                    new BigDecimal("29999"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400", 
                    15, ProductCategory.HOME_AND_GARDEN),

                createProduct("Throw Pillows", "Set of 4 decorative throw pillows", 
                    new BigDecimal("5999"), "https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=400", 
                    25, ProductCategory.HOME_AND_GARDEN),

                createProduct("Candles Set", "Aromatherapy candles with essential oils", 
                    new BigDecimal("2499"), "https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=400", 
                    45, ProductCategory.HOME_AND_GARDEN),

                createProduct("Wall Art", "Modern abstract wall art print", 
                    new BigDecimal("7999"), "https://images.unsplash.com/photo-1541961017774-22349e4a1262?w=400", 
                    20, ProductCategory.HOME_AND_GARDEN),

                // Sports - 10 products
                createProduct("Yoga Mat", "Premium non-slip yoga mat", 
                    new BigDecimal("3999"), "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=400", 
                    80, ProductCategory.SPORTS),
                
                createProduct("Basketball", "Official size basketball", 
                    new BigDecimal("2499"), "https://images.unsplash.com/photo-1546519638-68e109498ffc?w=400", 
                    50, ProductCategory.SPORTS),

                createProduct("Dumbbell Set", "Adjustable dumbbells 5-50 lbs", 
                    new BigDecimal("19999"), "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400", 
                    15, ProductCategory.SPORTS),

                createProduct("Tennis Racket", "Professional tennis racket", 
                    new BigDecimal("12999"), "https://images.unsplash.com/photo-1554068865-24cecd4e34b8?w=400", 
                    25, ProductCategory.SPORTS),

                createProduct("Running Shoes", "Lightweight running shoes", 
                    new BigDecimal("11999"), "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400", 
                    40, ProductCategory.SPORTS),

                createProduct("Cycling Helmet", "Safety cycling helmet with ventilation", 
                    new BigDecimal("7999"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400", 
                    30, ProductCategory.SPORTS),

                createProduct("Swimming Goggles", "Anti-fog swimming goggles", 
                    new BigDecimal("1999"), "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=400", 
                    60, ProductCategory.SPORTS),

                createProduct("Resistance Bands", "Set of 5 resistance bands", 
                    new BigDecimal("2999"), "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400", 
                    70, ProductCategory.SPORTS),

                createProduct("Soccer Ball", "Official FIFA approved soccer ball", 
                    new BigDecimal("3499"), "https://images.unsplash.com/photo-1546519638-68e109498ffc?w=400", 
                    35, ProductCategory.SPORTS),

                createProduct("Fitness Tracker", "Waterproof fitness tracker", 
                    new BigDecimal("8999"), "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400", 
                    45, ProductCategory.SPORTS),

                // Beauty - 8 products
                createProduct("Skincare Set", "Complete skincare routine set", 
                    new BigDecimal("7999"), "https://images.unsplash.com/photo-1570194065650-d99fb4bedf0a?w=400", 
                    35, ProductCategory.BEAUTY),
                
                createProduct("Perfume", "Luxury fragrance for women", 
                    new BigDecimal("12999"), "https://images.unsplash.com/photo-1541643600914-78b084683601?w=400", 
                    25, ProductCategory.BEAUTY),

                createProduct("Makeup Kit", "Professional makeup kit with 20 colors", 
                    new BigDecimal("5999"), "https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=400", 
                    30, ProductCategory.BEAUTY),

                createProduct("Hair Dryer", "Professional ionic hair dryer", 
                    new BigDecimal("8999"), "https://images.unsplash.com/photo-1522337360788-8b13dee7a37e?w=400", 
                    20, ProductCategory.BEAUTY),

                createProduct("Face Mask Set", "Hydrating face masks (pack of 10)", 
                    new BigDecimal("2499"), "https://images.unsplash.com/photo-1570194065650-d99fb4bedf0a?w=400", 
                    50, ProductCategory.BEAUTY),

                createProduct("Nail Polish Set", "Set of 12 nail polish colors", 
                    new BigDecimal("1999"), "https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=400", 
                    40, ProductCategory.BEAUTY),

                createProduct("Shampoo & Conditioner", "Natural hair care set", 
                    new BigDecimal("3499"), "https://images.unsplash.com/photo-1522337360788-8b13dee7a37e?w=400", 
                    60, ProductCategory.BEAUTY),

                createProduct("Lipstick Collection", "Set of 6 matte lipsticks", 
                    new BigDecimal("3999"), "https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=400", 
                    35, ProductCategory.BEAUTY),

                // Toys - 8 products
                createProduct("LEGO Creator Set", "Creative building blocks set", 
                    new BigDecimal("5999"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400", 
                    45, ProductCategory.TOYS),
                
                createProduct("Remote Control Car", "High-speed RC car", 
                    new BigDecimal("8999"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400", 
                    30, ProductCategory.TOYS),

                createProduct("Board Game", "Classic family board game", 
                    new BigDecimal("3499"), "https://images.unsplash.com/photo-1606092195730-5d7b9af1efc5?w=400", 
                    25, ProductCategory.TOYS),

                createProduct("Puzzle Set", "1000-piece jigsaw puzzle", 
                    new BigDecimal("1999"), "https://images.unsplash.com/photo-1606092195730-5d7b9af1efc5?w=400", 
                    40, ProductCategory.TOYS),

                createProduct("Action Figure", "Collectible superhero action figure", 
                    new BigDecimal("2499"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400", 
                    50, ProductCategory.TOYS),

                createProduct("Doll House", "3-story wooden doll house", 
                    new BigDecimal("14999"), "https://images.unsplash.com/photo-1606092195730-5d7b9af1efc5?w=400", 
                    15, ProductCategory.TOYS),

                createProduct("Building Blocks", "Colorful building blocks set", 
                    new BigDecimal("2999"), "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400", 
                    35, ProductCategory.TOYS),

                createProduct("Art Supplies", "Complete art supplies kit", 
                    new BigDecimal("4499"), "https://images.unsplash.com/photo-1606092195730-5d7b9af1efc5?w=400", 
                    20, ProductCategory.TOYS),

                // Food - 8 products
                createProduct("Organic Honey", "Pure organic honey 500g", 
                    new BigDecimal("1999"), "https://images.unsplash.com/photo-1587049352846-4a222e784d38?w=400", 
                    100, ProductCategory.FOOD),
                
                createProduct("Coffee Beans", "Premium roasted coffee beans", 
                    new BigDecimal("2499"), "https://images.unsplash.com/photo-1559056199-641a0ac8b55e?w=400", 
                    80, ProductCategory.FOOD),

                createProduct("Chocolate Box", "Luxury assorted chocolate box", 
                    new BigDecimal("3499"), "https://images.unsplash.com/photo-1511381939415-e44015466834?w=400", 
                    60, ProductCategory.FOOD),

                createProduct("Olive Oil", "Extra virgin olive oil 500ml", 
                    new BigDecimal("2999"), "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?w=400", 
                    45, ProductCategory.FOOD),

                createProduct("Tea Collection", "Premium tea collection (20 bags)", 
                    new BigDecimal("1499"), "https://images.unsplash.com/photo-1559056199-641a0ac8b55e?w=400", 
                    70, ProductCategory.FOOD),

                createProduct("Spice Set", "Gourmet spice collection", 
                    new BigDecimal("3999"), "https://images.unsplash.com/photo-1596040033229-a9821ebd058d?w=400", 
                    30, ProductCategory.FOOD),

                createProduct("Granola Bars", "Healthy granola bars (pack of 12)", 
                    new BigDecimal("1299"), "https://images.unsplash.com/photo-1511381939415-e44015466834?w=400", 
                    90, ProductCategory.FOOD),

                createProduct("Wine Bottle", "Premium red wine 750ml", 
                    new BigDecimal("4999"), "https://images.unsplash.com/photo-1506377247377-2a5b3b417ebb?w=400", 
                    25, ProductCategory.FOOD),

                // Health - 8 products
                createProduct("Multivitamin", "Daily multivitamin supplement", 
                    new BigDecimal("2999"), "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=400", 
                    90, ProductCategory.HEALTH),
                
                createProduct("Protein Powder", "Whey protein powder 2kg", 
                    new BigDecimal("4999"), "https://images.unsplash.com/photo-1593095948071-474c5cc2989d?w=400", 
                    55, ProductCategory.HEALTH),

                createProduct("Fish Oil", "Omega-3 fish oil capsules", 
                    new BigDecimal("2499"), "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=400", 
                    65, ProductCategory.HEALTH),

                createProduct("Vitamin D", "High-potency vitamin D3", 
                    new BigDecimal("1999"), "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=400", 
                    75, ProductCategory.HEALTH),

                createProduct("Probiotics", "Digestive health probiotics", 
                    new BigDecimal("3499"), "https://images.unsplash.com/photo-1593095948071-474c5cc2989d?w=400", 
                    40, ProductCategory.HEALTH),

                createProduct("Massage Oil", "Aromatherapy massage oil", 
                    new BigDecimal("2299"), "https://images.unsplash.com/photo-1606092195730-5d7b9af1efc5?w=400", 
                    50, ProductCategory.HEALTH),

                createProduct("First Aid Kit", "Complete first aid kit", 
                    new BigDecimal("3999"), "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=400", 
                    20, ProductCategory.HEALTH),

                createProduct("Thermometer", "Digital medical thermometer", 
                    new BigDecimal("1499"), "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=400", 
                    35, ProductCategory.HEALTH)
            );

            productRepository.saveAll(sampleProducts);
            System.out.println("Products saved successfully! Total products: " + productRepository.count());
        // }
    }

    private Product createProduct(String name, String description, BigDecimal price, 
                                String imageUrl, Integer stock, ProductCategory category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setStockQuantity(stock);
        product.setCategory(category);
        product.setActive(true);
        return product;
    }
}
