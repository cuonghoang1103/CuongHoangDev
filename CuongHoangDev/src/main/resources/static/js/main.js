// Main JavaScript file for Cuong Thai Portfolio
console.log('Cuong Thai Portfolio loaded successfully!');

document.addEventListener('DOMContentLoaded', function() {
    // ===== LOADING SPINNER HIDE =====
    window.addEventListener('load', () => {
        const spinner = document.getElementById('loading-spinner');
        if (spinner) spinner.style.display = 'none';
    });

    // ===== SIDEBAR TOGGLE =====
    const sidebar = document.querySelector('.main-sidebar');
    const overlay = document.getElementById('sidebarOverlay');
    const body = document.body;
    const mobileMenuToggle = document.getElementById('mobileMenuToggle');
    const mobileMenuBtn = document.querySelector('.mobile-menu-btn');

    function toggleSidebarOpen() {
        if (!sidebar || !overlay) return;
        sidebar.classList.toggle('active');
        overlay.classList.toggle('active');
        body.classList.toggle('sidebar-open');
    }

    if (mobileMenuToggle) {
        mobileMenuToggle.addEventListener('click', toggleSidebarOpen);
    }
    if (mobileMenuBtn) {
        mobileMenuBtn.addEventListener('click', toggleSidebarOpen);
    }
    if (overlay) {
        overlay.addEventListener('click', function() {
            sidebar?.classList.remove('active');
            overlay.classList.remove('active');
            body.classList.remove('sidebar-open');
        });
    }

    // ===== NAVBAR SCROLL EFFECT =====
    const header = document.querySelector('.main-header');
    let lastScrollY = window.scrollY;
    if (header) {
        window.addEventListener('scroll', () => {
            if (window.scrollY > 100) {
                header.classList.add('scrolled');
            } else {
                header.classList.remove('scrolled');
            }
            lastScrollY = window.scrollY;
        });
    }

    // ===== BACK TO TOP =====
    const backToTopBtn = document.getElementById('backToTop');
    if (backToTopBtn) {
        window.addEventListener('scroll', () => {
            if (window.pageYOffset > 300) {
                backToTopBtn.style.display = 'block';
            } else {
                backToTopBtn.style.display = 'none';
            }
        });
        backToTopBtn.addEventListener('click', () => {
            window.scrollTo({top: 0, behavior: 'smooth'});
        });
    }

    // ===== SMOOTH SCROLL FOR ANCHORS =====
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                e.preventDefault();
                target.scrollIntoView({behavior: 'smooth', block: 'start'});
            }
        });
    });

    // ===== ANIMATION ON SCROLL =====
    if (window.AOS) {
        AOS.init({
            duration: 800,
            easing: 'ease-in-out',
            once: true,
            offset: 50
        });
    }

    // ===== THEME TOGGLE =====
    const themeToggle = document.getElementById('themeToggle');
    const themeIcon = document.getElementById('themeIcon');
    const themeToggleMobile = document.getElementById('themeToggleMobile');
    const themeIconMobile = document.getElementById('themeIconMobile');
    function updateTheme(newTheme) {
        document.documentElement.setAttribute('data-theme', newTheme);
        localStorage.setItem('theme', newTheme);
        const icon = newTheme === 'dark' ? 'fa-sun' : 'fa-moon';
        const oldIcon = newTheme === 'dark' ? 'fa-moon' : 'fa-sun';
        if (themeIcon) {
            themeIcon.classList.remove(oldIcon);
            themeIcon.classList.add(icon);
        }
        if (themeIconMobile) {
            themeIconMobile.classList.remove(oldIcon);
            themeIconMobile.classList.add(icon);
        }
    }
    // Load saved theme
    const savedTheme = localStorage.getItem('theme') || 'auto';
    updateTheme(savedTheme);
    // Theme toggle handlers
    if (themeToggle) {
        themeToggle.addEventListener('click', () => {
            const current = document.documentElement.getAttribute('data-theme') || 'auto';
            const newTheme = current === 'light' ? 'dark' : 'light';
            updateTheme(newTheme);
        });
    }
    if (themeToggleMobile) {
        themeToggleMobile.addEventListener('click', () => {
            const current = document.documentElement.getAttribute('data-theme') || 'auto';
            const newTheme = current === 'light' ? 'dark' : 'light';
            updateTheme(newTheme);
        });
    }
    // Auto theme detection
    if (savedTheme === 'auto') {
        const mediaQuery = window.matchMedia('(prefers-color-scheme: dark)');
        const handleChange = (e) => {
            updateTheme(e.matches ? 'dark' : 'light');
        };
        mediaQuery.addEventListener('change', handleChange);
        handleChange(mediaQuery);
    }

    // ===== TYPING EFFECT FOR HERO =====
    const typingIntro = document.getElementById('typingIntro');
    if (typingIntro) {
        const texts = [
            'Full-stack Developer | Đam mê sáng tạo sản phẩm hữu ích',
            'Yêu thích chia sẻ kiến thức & trải nghiệm thực tế',
            'Luôn học hỏi và phát triển mỗi ngày'
        ];
        let textIndex = 0;
        let charIndex = 0;
        let isDeleting = false;
        function type() {
            let current = texts[textIndex];
            if (isDeleting) {
                typingIntro.textContent = current.substring(0, charIndex--);
                if (charIndex < 0) {
                    isDeleting = false;
                    textIndex = (textIndex + 1) % texts.length;
                    setTimeout(type, 600);
                } else {
                    setTimeout(type, 30);
                }
            } else {
                typingIntro.textContent = current.substring(0, charIndex++);
                if (charIndex > current.length) {
                    isDeleting = true;
                    setTimeout(type, 1200);
                } else {
                    setTimeout(type, 60);
                }
            }
        }
        type();
    }

    // ===== TOOLTIP INIT (nếu dùng Bootstrap tooltip) =====
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Animation on scroll (AOS đơn giản)
    function animateOnScroll() {
        const elements = document.querySelectorAll('[data-aos]');
        const trigger = window.innerHeight * 0.95;
        elements.forEach(el => {
            const rect = el.getBoundingClientRect();
            if (rect.top < trigger) {
                el.style.opacity = 1;
                el.style.transform = 'translateY(0)';
            } else {
                el.style.opacity = 0;
                el.style.transform = 'translateY(40px)';
            }
        });
    }
    window.addEventListener('scroll', animateOnScroll);
    window.addEventListener('load', animateOnScroll);

    // Khởi tạo style cho các phần tử data-aos
    document.querySelectorAll('[data-aos]').forEach(el => {
        el.style.transition = 'all 0.7s cubic-bezier(.4,2,.3,1)';
        el.style.opacity = 0;
        el.style.transform = 'translateY(40px)';
    });
    
    // ===== RESPONSIVE HANDLING =====
    function handleResize() {
        if (window.innerWidth <= 991) {
            // Mobile view
            if (sidebar) sidebar.classList.remove('active');
            if (overlay) overlay.classList.remove('active');
            if (body) body.classList.remove('sidebar-open');
        }
    }
    
    window.addEventListener('resize', handleResize);
    handleResize(); // Run once on page load
}); 