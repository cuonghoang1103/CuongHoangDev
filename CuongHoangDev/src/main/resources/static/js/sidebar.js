/**
 * Sidebar Navigation JavaScript
 * Handles sidebar toggle, responsive behavior, and navigation interactions
 */

$(document).ready(function() {
    
    // Initialize sidebar functionality
    initSidebar();
    
    // Initialize responsive behavior
    initResponsive();
    
    // Initialize navigation highlighting
    initNavigation();
    
    // Initialize loading spinner
    initLoadingSpinner();
    
});

/**
 * Initialize sidebar functionality
 */
function initSidebar() {
    const $sidebar = $('#sidebar');
    const $sidebarToggle = $('#sidebar-toggle');
    const $sidebarOverlay = $('#sidebar-overlay');
    const $contentWrapper = $('#content-wrapper');
    
    // Sidebar toggle functionality
    $sidebarToggle.on('click', function(e) {
        e.preventDefault();
        toggleSidebar();
    });
    
    // Close sidebar when clicking overlay
    $sidebarOverlay.on('click', function() {
        closeSidebar();
    });
    
    // Close sidebar when pressing Escape key
    $(document).on('keydown', function(e) {
        if (e.key === 'Escape' && $sidebar.hasClass('show')) {
            closeSidebar();
        }
    });
    
    // Prevent sidebar from closing when clicking inside it
    $sidebar.on('click', function(e) {
        e.stopPropagation();
    });
    
    // Auto-close sidebar on mobile when clicking nav links
    $sidebar.find('.nav-link').on('click', function() {
        if (window.innerWidth < 768) {
            setTimeout(closeSidebar, 150);
        }
    });
}

/**
 * Toggle sidebar visibility
 */
function toggleSidebar() {
    const $sidebar = $('#sidebar');
    const $overlay = $('#sidebar-overlay');
    
    if ($sidebar.hasClass('show')) {
        closeSidebar();
    } else {
        openSidebar();
    }
}

/**
 * Open sidebar
 */
function openSidebar() {
    const $sidebar = $('#sidebar');
    const $overlay = $('#sidebar-overlay');
    
    $sidebar.addClass('show slide-in-left');
    $overlay.addClass('show');
    $('body').addClass('sidebar-open');
    
    // Remove animation class after animation completes
    setTimeout(() => {
        $sidebar.removeClass('slide-in-left');
    }, 300);
}

/**
 * Close sidebar
 */
function closeSidebar() {
    const $sidebar = $('#sidebar');
    const $overlay = $('#sidebar-overlay');
    
    $sidebar.removeClass('show');
    $overlay.removeClass('show');
    $('body').removeClass('sidebar-open');
}

/**
 * Initialize responsive behavior
 */
function initResponsive() {
    let resizeTimer;
    
    $(window).on('resize', function() {
        clearTimeout(resizeTimer);
        resizeTimer = setTimeout(function() {
            handleResize();
        }, 250);
    });
    
    // Initial check
    handleResize();
}

/**
 * Handle window resize
 */
function handleResize() {
    const $sidebar = $('#sidebar');
    const $overlay = $('#sidebar-overlay');
    
    if (window.innerWidth >= 768) {
        // Desktop: ensure sidebar is visible and overlay is hidden
        $sidebar.removeClass('show');
        $overlay.removeClass('show');
        $('body').removeClass('sidebar-open');
    } else {
        // Mobile: ensure sidebar is hidden unless explicitly shown
        if (!$sidebar.hasClass('show')) {
            closeSidebar();
        }
    }
}

/**
 * Initialize navigation highlighting
 */
function initNavigation() {
    const currentPath = window.location.pathname;
    const $navLinks = $('.sidebar .nav-link');
    
    // Remove all active classes
    $navLinks.removeClass('active');
    
    // Find and highlight current page
    $navLinks.each(function() {
        const $link = $(this);
        const href = $link.attr('href');
        
        if (href && (currentPath === href || currentPath.startsWith(href + '/'))) {
            $link.addClass('active');
        }
    });
    
    // If no exact match, highlight home for root path
    if (currentPath === '/' || currentPath === '') {
        $('.sidebar .nav-link[href="/"]').addClass('active');
    }
    
    // Add hover effects
    $navLinks.on('mouseenter', function() {
        $(this).addClass('hover');
    }).on('mouseleave', function() {
        $(this).removeClass('hover');
    });
}

/**
 * Initialize loading spinner
 */
function initLoadingSpinner() {
    const $spinner = $('#loading-spinner');
    
    // Hide spinner when page is fully loaded
    $(window).on('load', function() {
        setTimeout(() => {
            $spinner.addClass('hidden');
            setTimeout(() => {
                $spinner.hide();
            }, 300);
        }, 500);
    });
    
    // Show spinner on page navigation
    $('a:not([target="_blank"]):not([href^="#"]):not([href^="mailto:"]):not([href^="tel:"])').on('click', function(e) {
        const href = $(this).attr('href');
        if (href && href !== '#' && !href.startsWith('javascript:')) {
            $spinner.removeClass('hidden').show();
        }
    });
}

/**
 * Smooth scroll to top functionality
 */
function scrollToTop() {
    $('html, body').animate({
        scrollTop: 0
    }, 600);
}

/**
 * Add scroll to top button
 */
function initScrollToTop() {
    // Create scroll to top button
    const $scrollBtn = $('<button>', {
        id: 'scroll-to-top',
        class: 'btn btn-primary position-fixed',
        style: 'bottom: 20px; right: 20px; z-index: 1000; border-radius: 50%; width: 50px; height: 50px; display: none;',
        html: '<i class="fas fa-arrow-up"></i>',
        title: 'Scroll to top'
    });
    
    $('body').append($scrollBtn);
    
    // Show/hide button based on scroll position
    $(window).on('scroll', function() {
        if ($(this).scrollTop() > 300) {
            $scrollBtn.fadeIn();
        } else {
            $scrollBtn.fadeOut();
        }
    });
    
    // Scroll to top when clicked
    $scrollBtn.on('click', function() {
        scrollToTop();
    });
}

/**
 * Initialize tooltips
 */
function initTooltips() {
    $('[data-bs-toggle="tooltip"]').tooltip();
}

/**
 * Initialize search functionality
 */
function initSearch() {
    const $searchForm = $('.search-box form');
    const $searchInput = $('.search-box input');
    
    $searchForm.on('submit', function(e) {
        const query = $searchInput.val().trim();
        if (!query) {
            e.preventDefault();
            $searchInput.focus();
        }
    });
    
    // Search suggestions (if needed)
    $searchInput.on('input', function() {
        const query = $(this).val().trim();
        if (query.length >= 2) {
            // Implement search suggestions here
            // showSearchSuggestions(query);
        }
    });
}

/**
 * Initialize all components when DOM is ready
 */
$(document).ready(function() {
    initScrollToTop();
    initTooltips();
    initSearch();
});

/**
 * Utility functions
 */
const SidebarUtils = {
    
    /**
     * Check if sidebar is open
     */
    isOpen: function() {
        return $('#sidebar').hasClass('show');
    },
    
    /**
     * Get current active navigation item
     */
    getActiveNav: function() {
        return $('.sidebar .nav-link.active');
    },
    
    /**
     * Set active navigation item
     */
    setActiveNav: function(href) {
        $('.sidebar .nav-link').removeClass('active');
        $(`.sidebar .nav-link[href="${href}"]`).addClass('active');
    },
    
    /**
     * Show notification in sidebar
     */
    showNotification: function(message, type = 'info') {
        const $notification = $(`
            <div class="alert alert-${type} alert-dismissible fade show position-fixed" 
                 style="top: 20px; right: 20px; z-index: 9999; min-width: 300px;">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        `);
        
        $('body').append($notification);
        
        // Auto-remove after 5 seconds
        setTimeout(() => {
            $notification.alert('close');
        }, 5000);
    }
};

// Export for global use
window.SidebarUtils = SidebarUtils;
