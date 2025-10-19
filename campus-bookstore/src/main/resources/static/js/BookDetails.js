const cartBtn = document.querySelector('.btn-cart');
const wishlistBtn = document.querySelector('.btn-wishlist');

let cart = [];
let wishlist = [];

cartBtn.addEventListener('click', function() {
    const product = {
        title: 'Introduction to Algorithms',
        author: 'Thomas H. Cormen, Charles E. Leiserson',
        isbn: '978-0262033848',
        price: 89.99,
        quantity: 1
    };

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

wishlistBtn.addEventListener('click', function() {
    const product = {
        title: 'Introduction to Algorithms',
        author: 'Thomas H. Cormen, Charles E. Leiserson',
        isbn: '978-0262033848',
        price: 89.99
    };

    const existingItem = wishlist.find(item => item.isbn === product.isbn);
    
    if (existingItem) {
        alert('This book is already in your wishlist!');
    } else {
        wishlist.push(product);
        alert('Book added to wishlist successfully!');
    }
    
    console.log('Current wishlist:', wishlist);
});

const navButtons = document.querySelectorAll('.nav-btn');

navButtons.forEach(button => {
    button.addEventListener('click', function() {
        const page = this.textContent;
        console.log(`Navigating to: ${page}`);
        
        if (page === 'Cart') {
            showCart();
        } else {
            alert(`Navigate to ${page} page`);
        }
    });
});

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

function updateCartDisplay() {
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    console.log(`Total items in cart: ${totalItems}`);
}

console.log('Campus Bookstore loaded successfully!');
