/* =========================================
   Theme switcher
   ========================================= */
function setTheme(themeName) {
    // Applies the data-theme attribute to the root <html> tag
    document.documentElement.setAttribute('data-theme', themeName);
    
    // Saves the choice to browser memory so it persists across pages
    localStorage.setItem('portfolioTheme', themeName);
}

// Check for saved theme on initial page load
let savedTheme = localStorage.getItem('portfolioTheme');
if (savedTheme) {
    setTheme(savedTheme);
}

/* =========================================
   Interactive Animations & Effects
   ========================================= */
// This runs the exact second the HTML finishes loading
document.addEventListener("DOMContentLoaded", () => {
    
    /* =========================================
       1. The Boot-Up Sequence
       ========================================= */
    // Find every single Windows 95 box on the screen
    const boxes = document.querySelectorAll('.win95-box');
    
    // Hide them all instantly and push them down slightly
    boxes.forEach(box => {
        box.style.opacity = '0';
        box.style.transform = 'translateY(10px)'; 
        box.style.transition = 'opacity 0.1s, transform 0.1s'; // Fast, snappy transition
    });

    // Make them appear one by one with a slight delay (like an old CPU loading)
    boxes.forEach((box, index) => {
        setTimeout(() => {
            box.style.opacity = '1';
            box.style.transform = 'translateY(0)';
        }, index * 150); // Multiplies the delay by the box number (0ms, 150ms, 300ms, etc.)
    });

    /* =========================================
       Glitch Effect
       ========================================= */
    // Find all the artwork images
    const artworks = document.querySelectorAll('.artwork-card img');
    
    artworks.forEach(art => {
        art.addEventListener('mouseover', () => {
            // Apply a crazy color filter and shift the image randomly
            art.style.filter = `contrast(200%) hue-rotate(${Math.random() * 90}deg)`;
            art.style.transform = `translate(${Math.random() * 6 - 3}px, ${Math.random() * 6 - 3}px)`;
            
            // Snap it back to normal after a tiny fraction of a second (100ms)
            setTimeout(() => {
                art.style.filter = 'none';
                art.style.transform = 'none';
            }, 100); 
        });
    });
});