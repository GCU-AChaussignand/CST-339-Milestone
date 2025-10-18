// Search functionality
function handleSearch() {
    const searchInput = document.getElementById('searchInput');
    const searchTerm = searchInput.value.trim();
    
    if (searchTerm) {
        // You can redirect to a search results page or handle the search here
        console.log('Searching for:', searchTerm);
        alert('Searching for: ' + searchTerm);
        // Example: window.location.href = 'search.html?q=' + encodeURIComponent(searchTerm);
    } else {
        alert('Please enter a search term');
    }
}

// Allow search on Enter key press
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    
    if (searchInput) {
        searchInput.addEventListener('keypress', function(event) {
            if (event.key === 'Enter') {
                handleSearch();
            }
        });
    }
});