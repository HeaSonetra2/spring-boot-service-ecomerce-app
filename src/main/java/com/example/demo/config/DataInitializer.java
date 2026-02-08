package com.example.demo.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Category;
import com.example.demo.entity.FeedPost;
import com.example.demo.entity.Product;
import com.example.demo.entity.Promotion;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FeedPostRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PromotionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PromotionRepository promotionRepository;
    private final FeedPostRepository feedPostRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        seedPromotions();
        seedFeedPosts();
        seedProducts();
    }

    private void seedPromotions() {
        if (promotionRepository.count() > 0)
            return;

        List<Promotion> promos = List.of(
                createPromotion(
                        "Big Summer Sale - 50% OFF",
                        "https://picsum.photos/800/400?1",
                        "myapp://products?tag=summer"),
                createPromotion(
                        "New Arrivals - Street Wear",
                        "https://picsum.photos/800/400?2",
                        "myapp://category/streetwear"),
                createPromotion(
                        "Member Exclusive Deals",
                        "https://picsum.photos/800/400?3",
                        "myapp://promotions/member"),
                createPromotion(
                        "Free Shipping Weekend",
                        "https://picsum.photos/800/400?4",
                        "myapp://promo/free-shipping"),
                createPromotion(
                        "Back To School Collection",
                        "https://picsum.photos/800/400?5",
                        "myapp://category/back-to-school"));

        promotionRepository.saveAll(promos);
    }

    private Promotion createPromotion(String title, String imageUrl, String deepLink) {
        Promotion p = new Promotion();
        p.setTitle(title);
        p.setImageUrl(imageUrl);
        p.setDeepLinkUrl(deepLink);
        p.setExpiryDate(LocalDateTime.now().plusDays(30));
        return p;
    }

    private void seedFeedPosts() {
        if (feedPostRepository.count() > 0)
            return;

        List<FeedPost> posts = List.of(
                createFeedPost(
                        "Admin",
                        "https://i.pravatar.cc/150?img=1",
                        "🔥 New sneakers collection just dropped. Check out the best sellers now!",
                        "https://picsum.photos/600/400?11",
                        23L),
                createFeedPost(
                        "Jane",
                        "https://i.pravatar.cc/150?img=2",
                        "Loving the new street wear set. So comfy!",
                        "https://picsum.photos/600/400?12",
                        15L),
                createFeedPost(
                        "Store",
                        "https://i.pravatar.cc/150?img=3",
                        "Weekend flash sale is live for members only.",
                        "https://picsum.photos/600/400?13",
                        40L));

        feedPostRepository.saveAll(posts);
    }

    private FeedPost createFeedPost(
            String authorName,
            String avatar,
            String content,
            String mediaUrl,
            Long likes) {

        FeedPost p = new FeedPost();
        p.setAuthorName(authorName);
        p.setAuthorAvatar(avatar);
        p.setContent(content);
        p.setMediaUrl(mediaUrl);
        p.setTimestamp(LocalDateTime.now().minusHours(2));
        p.setLikesCount(likes);
        return p;
    }

    private void seedProducts() {
        if (productRepository.count() > 0)
            return;

        Category shoes = new Category();
        shoes.setName("Shoes");

        Category street = new Category();
        street.setName("Street Wear");

        Category accessories = new Category();
        accessories.setName("Accessories");

        categoryRepository.saveAll(List.of(shoes, street, accessories));

        List<Product> products = List.of(
                createProduct(
                        "AirFlow Runner",
                        new BigDecimal("79.99"),
                        new BigDecimal("59.99"),
                        4.7,
                        120,
                        "https://picsum.photos/400/400?21",
                        "IN_STOCK",
                        "Lightweight running shoes",
                        "AirFlow"),
                createProduct(
                        "Urban Street Hoodie",
                        new BigDecimal("49.99"),
                        new BigDecimal("39.99"),
                        4.5,
                        80,
                        "https://picsum.photos/400/400?22",
                        "IN_STOCK",
                        "Oversized hoodie for everyday wear",
                        "UrbanX"),
                createProduct(
                        "Classic Leather Sneakers",
                        new BigDecimal("89.99"),
                        new BigDecimal("69.99"),
                        4.8,
                        60,
                        "https://picsum.photos/400/400?23",
                        "LOW_STOCK",
                        "Premium leather sneakers",
                        "Classic Co"),
                createProduct(
                        "Daily Backpack",
                        new BigDecimal("39.99"),
                        new BigDecimal("34.99"),
                        4.3,
                        200,
                        "https://picsum.photos/400/400?24",
                        "IN_STOCK",
                        "Everyday backpack with laptop sleeve",
                        "PackIt"),
                createProduct(
                        "Sport Socks 5-Pack",
                        new BigDecimal("19.99"),
                        new BigDecimal("14.99"),
                        4.1,
                        500,
                        "https://picsum.photos/400/400?25",
                        "IN_STOCK",
                        "Breathable sport socks",
                        "ComfortFit"));

        // Assign categories
        products.get(0).setCategory(shoes);
        products.get(1).setCategory(street);
        products.get(2).setCategory(shoes);
        products.get(3).setCategory(accessories);
        products.get(4).setCategory(accessories);

        productRepository.saveAll(products);
    }

    private Product createProduct(
            String name,
            BigDecimal price,
            BigDecimal discountPrice,
            double rating,
            int stockQty,
            String thumbnailUrl,
            String stockStatus,
            String description,
            String brand) {

        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setDiscountPrice(discountPrice);
        p.setRating(rating);
        p.setStockQuantity(stockQty);
        p.setThumbnailUrl(thumbnailUrl);
        p.setStockStatus(stockStatus);
        p.setDescription(description);
        p.setBrandInfo(brand);
        return p;
    }
}


