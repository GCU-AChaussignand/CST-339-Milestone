// Cart functionality
const cartBtn = document.querySelector('.btn-cart');
const wishlistBtn = document.querySelector('.btn-wishlist');

// Cart state
let cart = [];
let wishlist = [];

// Add to Cart functionality
cartBtn.addEventListener('click', function() {
    const product = {
        title: 'Introduction to Algorithms',
        author: 'Thomas H. Cormen, Charles E. Leiserson',
        isbn: '978-0262033848',
        price: 89.99,
        quantity: 1
    };

    // Check if product already in cart
    const existingItem = cart.find(item => item.isbn === product.isbn);
    
    if (existingItem) {
        existingItem.quantity++;
        alert('Quantity updated in cart!');
    } else {
        cart.push(product);
        alert('Book added to cart successfully!');
    }
    
    console.log('Current cart:', cart);
    updateCartDisplay();
});

// Add to Wishlist functionality
wishlistBtn.addEventListener('click', function() {
    const product = {
        title: 'Introduction to Algorithms',
        author: 'Thomas H. Cormen, Charles E. Leiserson',
        isbn: '978-0262033848',
        price: 89.99
    };

    // Check if already in wishlist
    const existingItem = wishlist.find(item => item.isbn === product.isbn);
    
    if (existingItem) {
        alert('This book is already in your wishlist!');
    } else {
        wishlist.push(product);
        alert('Book added to wishlist successfully!');
    }
    
    console.log('Current wishlist:', wishlist);
});

// Navigation buttons functionality
const navButtons = document.querySelectorAll('.nav-btn');

navButtons.forEach(button => {
    button.addEventListener('click', function() {
        const page = this.textContent;
        console.log(`Navigating to: ${page}`);
        
        // In a real application, this would navigate to different pages
        // For now, we'll just show an alert
        if (page === 'Cart') {
            showCart();
        } else {
            alert(`Navigate to ${page} page`);
        }
    });
});

// Display cart contents
function showCart() {
    if (cart.length === 0) {
        alert('Your cart is empty!');
        return;
    }
    
    let cartContents = 'Cart Contents:\n\n';
    let total = 0;
    
    cart.forEach(item => {
        cartContents += `${item.title}\n`;
        cartContents += `Quantity: ${item.quantity}\n`;
        cartContents += `Price: $${(item.price * item.quantity).toFixed(2)}\n\n`;
        total += item.price * item.quantity;
    });
    
    cartContents += `Total: $${total.toFixed(2)}`;
    alert(cartContents);
}

// Update cart display (could be used to update a cart icon badge)
function updateCartDisplay() {
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    console.log(`Total items in cart: ${totalItems}`);
    // In a real application, this would update a cart badge or counter
}

// Initialize
console.log('Campus Bookstore loaded successfully!');