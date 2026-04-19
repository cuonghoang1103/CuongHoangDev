/**
 * Portfolio Filter & Animation System
 * Handles filtering, animations, and modal interactions
 */

document.addEventListener('DOMContentLoaded', function() {
    initPortfolioFilters();
    initLoadMore();
    initPortfolioAnimations();
});

/**
 * Initialize portfolio filtering system
 */
function initPortfolioFilters() {
    const filterBtns = document.querySelectorAll('.filter-btn');
    const portfolioItems = document.querySelectorAll('.portfolio-item');

    filterBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const filter = this.getAttribute('data-filter');
            
            // Update active button
            filterBtns.forEach(b => b.classList.remove('active'));
            this.classList.add('active');
            
            // Filter items
            filterPortfolioItems(portfolioItems, filter);
        });
    });
}

/**
 * Filter portfolio items based on category
 */
function filterPortfolioItems(items, filter) {
    items.forEach(item => {
        const category = item.getAttribute('data-category');
        
        if (filter === 'all' || category === filter) {
            item.classList.remove('hidden');
            // Add entrance animation
            setTimeout(() => {
                item.style.opacity = '1';
                item.style.transform = 'scale(1)';
            }, 100);
        } else {
            item.classList.add('hidden');
            item.style.opacity = '0';
            item.style.transform = 'scale(0.8)';
        }
    });
}

/**
 * Initialize load more functionality
 */
function initLoadMore() {
    const loadMoreBtn = document.getElementById('loadMoreBtn');
    let currentPage = 1;
    const itemsPerPage = 6;

    if (loadMoreBtn) {
        loadMoreBtn.addEventListener('click', function() {
            loadMoreProjects();
        });
    }

    function loadMoreProjects() {
        // Simulate loading more projects
        const portfolioGrid = document.querySelector('.portfolio-grid');
        
        // Show loading state
        loadMoreBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Đang tải...';
        loadMoreBtn.disabled = true;

        // Simulate API call delay
        setTimeout(() => {
            // Add more portfolio items (this would normally come from an API)
            const newItems = generateMorePortfolioItems();
            portfolioGrid.insertAdjacentHTML('beforeend', newItems);
            
            // Reset button
            loadMoreBtn.innerHTML = '<i class="fas fa-plus"></i> Xem thêm dự án';
            loadMoreBtn.disabled = false;
            
            currentPage++;
            
            // Hide button after loading 3 pages
            if (currentPage >= 3) {
                loadMoreBtn.style.display = 'none';
            }
        }, 1500);
    }
}

/**
 * Generate more portfolio items (mock data)
 */
function generateMorePortfolioItems() {
    const mockProjects = [
        {
            category: 'web',
            title: 'Restaurant Website',
            description: 'Website nhà hàng với đặt bàn online và menu tương tác',
            image: '/img/projects/restaurant.jpg',
            tech: ['Vue.js', 'Laravel', 'MySQL', 'Stripe']
        },
        {
            category: 'mobile',
            title: 'Travel Planning App',
            description: 'Ứng dụng lập kế hoạch du lịch với AI recommendations',
            image: '/img/projects/travel-app.jpg',
            tech: ['Flutter', 'Firebase', 'Google Maps', 'ML Kit']
        },
        {
            category: 'design',
            title: 'Brand Identity Design',
            description: 'Thiết kế nhận diện thương hiệu hoàn chỉnh cho startup',
            image: '/img/projects/brand-design.jpg',
            tech: ['Illustrator', 'Photoshop', 'Figma', 'InDesign']
        }
    ];

    return mockProjects.map(project => `
        <div class="portfolio-item" data-category="${project.category}">
            <div class="portfolio-card">
                <div class="portfolio-image">
                    <img src="${project.image}" alt="${project.title}" loading="lazy">
                    <div class="portfolio-overlay">
                        <div class="portfolio-actions">
                            <button class="action-btn" onclick="openModal('${project.title.toLowerCase().replace(/\s+/g, '-')}')">
                                <i class="fas fa-eye"></i>
                            </button>
                            <a href="#" target="_blank" class="action-btn">
                                <i class="fas fa-external-link-alt"></i>
                            </a>
                            <a href="#" target="_blank" class="action-btn">
                                <i class="fab fa-github"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="portfolio-content">
                    <div class="portfolio-category">${project.category === 'web' ? 'Website' : project.category === 'mobile' ? 'Mobile App' : 'Design'}</div>
                    <h3 class="portfolio-title">${project.title}</h3>
                    <p class="portfolio-description">${project.description}</p>
                    <div class="portfolio-tech">
                        ${project.tech.map(tech => `<span class="tech-tag">${tech}</span>`).join('')}
                    </div>
                </div>
            </div>
        </div>
    `).join('');
}

/**
 * Initialize portfolio animations
 */
function initPortfolioAnimations() {
    // Animate portfolio items on scroll
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    });

    // Observe all portfolio items
    document.querySelectorAll('.portfolio-item').forEach(item => {
        item.style.opacity = '0';
        item.style.transform = 'translateY(30px)';
        item.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
        observer.observe(item);
    });

    // Animate tech items
    document.querySelectorAll('.tech-item').forEach((item, index) => {
        item.style.opacity = '0';
        item.style.transform = 'translateX(-20px)';
        item.style.transition = `opacity 0.5s ease ${index * 0.1}s, transform 0.5s ease ${index * 0.1}s`;
        
        setTimeout(() => {
            item.style.opacity = '1';
            item.style.transform = 'translateX(0)';
        }, 500 + (index * 100));
    });
}

/**
 * Open project modal (placeholder function)
 */
function openModal(projectId) {
    // This would open a modal with project details
    console.log('Opening modal for project:', projectId);
    
    // For now, just show an alert
    alert(`Chi tiết dự án: ${projectId}\n\nTính năng modal sẽ được implement trong phiên bản tiếp theo.`);
}

/**
 * Smooth scroll to portfolio section
 */
function scrollToPortfolio() {
    const portfolioSection = document.querySelector('.portfolio-grid');
    if (portfolioSection) {
        portfolioSection.scrollIntoView({
            behavior: 'smooth',
            block: 'start'
        });
    }
}

/**
 * Search functionality (if search input exists)
 */
function initPortfolioSearch() {
    const searchInput = document.getElementById('portfolioSearch');
    if (!searchInput) return;

    searchInput.addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        const portfolioItems = document.querySelectorAll('.portfolio-item');
        
        portfolioItems.forEach(item => {
            const title = item.querySelector('.portfolio-title').textContent.toLowerCase();
            const description = item.querySelector('.portfolio-description').textContent.toLowerCase();
            const tech = Array.from(item.querySelectorAll('.tech-tag')).map(tag => tag.textContent.toLowerCase()).join(' ');
            
            if (title.includes(searchTerm) || description.includes(searchTerm) || tech.includes(searchTerm)) {
                item.style.display = 'block';
            } else {
                item.style.display = 'none';
            }
        });
    });
}

/**
 * Initialize portfolio counter animation
 */
function animatePortfolioStats() {
    const stats = document.querySelectorAll('.stat-number');
    
    stats.forEach(stat => {
        const target = parseInt(stat.textContent);
        let current = 0;
        const increment = target / 50;
        
        const timer = setInterval(() => {
            current += increment;
            if (current >= target) {
                current = target;
                clearInterval(timer);
            }
            stat.textContent = Math.floor(current) + (stat.textContent.includes('+') ? '+' : '');
        }, 50);
    });
}

// Export functions for global use
window.openModal = openModal;
window.scrollToPortfolio = scrollToPortfolio;
