// Sample product data
const products = [
    {
        id: 1,
        title: "Introduction to Algorithms",
        author: "Cormen, Leiserson",
        isbn: "978-0262033848",
        price: 89.99,
        stock: 25
    },
    {
        id: 2,
        title: "Calculus Early Transcendentals",
        author: "James Stewart",
        isbn: "978-1285741550",
        price: 120.00,
        stock: 15
    },
    {
        id: 3,
        title: "Chemistry: The Central Science",
        author: "Brown, LeMay",
        isbn: "978-0134414232",
        price: 95.50,
        stock: 8
    },
    {
        id: 4,
        title: "Introduction to Psychology",
        author: "James W. Kalat",
        isbn: "978-1337565691",
        price: 75.99,
        stock: 30
    }
];

// Function to render products table
function renderProducts() {
    const tableBody = document.getElementById('productsTableBody');
    tableBody.innerHTML = '';

    products.forEach(product => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><div class="product-image"></div></td>
            <td>${product.title}</td>
            <td>${product.author}</td>
            <td>${product.isbn}</td>
            <td>$${product.price.toFixed(2)}</td>
            <td>${product.stock}</td>
            <td>
                <button class="view-details-btn" onclick="viewDetails(${product.id})">
                    View<br>Details
                </button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Function to view product details
function viewDetails(productId) {
    const product = products.find(p => p.id === productId);
    if (product) {
        alert(`Product Details:\n\nTitle: ${product.title}\nAuthor: ${product.author}\nISBN: ${product.isbn}\nPrice: $${product.price.toFixed(2)}\nStock: ${product.stock}`);
    }
}

// Event listeners for action buttons
document.getElementById('addProductBtn').addEventListener('click', function() {
    alert('Add Product functionality would be implemented here');
});

document.getElementById('editBtn').addEventListener('click', function() {
    alert('Edit functionality would be implemented here');
});

document.getElementById('deleteBtn').addEventListener('click', function() {
    alert('Delete functionality would be implemented here');
});

// Initialize the page
document.addEventListener('DOMContentLoaded', function() {
    renderProducts();
});