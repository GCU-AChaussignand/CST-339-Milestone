// Initial cart data
let cartItems = [
    {
        id: 1,
        title: "Introduction to Algorithms",
        price: 89.99,
        quantity: 1
    },
    {
        id: 2,
        title: "Calculus Early Transcendentals",
        price: 120.00,
        quantity: 2
    },
    {
        id: 3,
        title: "Introduction to Psychology",
        price: 75.99,
        quantity: 1
    }
];

const TAX_RATE = 0.08;
const SHIPPING_FEE = 5.99;

// Initialize cart on page load
document.addEventListener('DOMContentLoaded', function() {
    renderCart();
    updateTotals();
});

// Render cart items
function renderCart() {
    const cartItemsContainer = document.getElementById('cart-items');
    cartItemsContainer.innerHTML = '';

    cartItems.forEach(item => {
        const subtotal = item.price * item.quantity;
        
        const row = document.createElement('tr');
        row.innerHTML = `
            <td class="product-cell"></td>
            <td class="title-cell">${item.title}</td>
            <td class="price-cell">$${item.price.toFixed(2)}</td>
            <td>
                <input type="number" 
                       class="quantity-input" 
                       value="${item.quantity}" 
                       min="1" 
                       data-id="${item.id}"
                       onchange="updateQuantity(${item.id}, this.value)">
            </td>
            <td class="subtotal-cell">$${subtotal.toFixed(2)}</td>
            <td>
                <button class="remove-btn" onclick="removeItem(${item.id})">Remove</button>
            </td>
        `;
        
        cartItemsContainer.appendChild(row);
    });
}

// Update quantity
function updateQuantity(itemId, newQuantity) {
    const quantity = parseInt(newQuantity);
    
    if (quantity < 1) {
        alert('Quantity must be at least 1');
        renderCart();
        return;
    }
    
    const item = cartItems.find(item => item.id === itemId);
    if (item) {
        item.quantity = quantity;
        renderCart();
        updateTotals();
    }
}

// Remove item from cart
function removeItem(itemId) {
    if (confirm('Are you sure you want to remove this item?')) {
        cartItems = cartItems.filter(item => item.id !== itemId);
        renderCart();
        updateTotals();
    }
}

// Calculate and update totals
function updateTotals() {
    const subtotal = cartItems.reduce((sum, item) => {
        return sum + (item.price * item.quantity);
    }, 0);
    
    const tax = subtotal * TAX_RATE;
    const shipping = cartItems.length > 0 ? SHIPPING_FEE : 0;
    const total = subtotal + tax + shipping;
    
    document.getElementById('subtotal').textContent = `$${subtotal.toFixed(2)}`;
    document.getElementById('tax').textContent = `$${tax.toFixed(2)}`;
    document.getElementById('shipping').textContent = `$${shipping.toFixed(2)}`;
    document.getElementById('total').textContent = `$${total.toFixed(2)}`;
}

// Checkout button handler
document.addEventListener('DOMContentLoaded', function() {
    const checkoutBtn = document.querySelector('.checkout-btn');
    if (checkoutBtn) {
        checkoutBtn.addEventListener('click', function() {
            if (cartItems.length === 0) {
                alert('Your cart is empty!');
            } else {
                alert('Proceeding to checkout...');
                // Add checkout logic here
            }
        });
    }
});