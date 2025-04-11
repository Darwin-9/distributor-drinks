// Función para cargar los productos desde la API
function cargarProductos() {
    fetch("http://localhost:8080/api/v1/drinks/")
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener productos');
            }
            return response.json();
        })
        .then(data => {
            mostrarProductos(data);
            actualizarContador(data.length);
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('products-grid').innerHTML = `
                <div class="error-message">
                    <i class="fas fa-exclamation-triangle"></i>
                    <p>No se pudieron cargar los productos. Intente nuevamente más tarde.</p>
                </div>
            `;
        });
}

// Función para mostrar los productos en el grid
function mostrarProductos(productos) {
    const productsGrid = document.getElementById('products-grid');
    productsGrid.innerHTML = '';

    if (productos.length === 0) {
        productsGrid.innerHTML = `
            <div class="no-products">
                <i class="fas fa-box-open"></i>
                <p>No hay productos disponibles</p>
            </div>
        `;
        return;
    }

    productos.forEach(producto => {
        const productCard = document.createElement('article');
        productCard.className = 'product-card';
        
        // Determinar si es un producto nuevo (menos de 7 días)
        const isNew = esProductoNuevo(producto.fechaCreacion); // Asumiendo que tienes este campo
        
        productCard.innerHTML = `
            ${isNew ? '<div class="product-badge">Nuevo</div>' : ''}
            <div class="product-image">
                <img src="img/products/${obtenerImagenProducto(producto.name)}" alt="${producto.name}" loading="lazy">
                <button class="btn-quick-view" onclick="mostrarVistaRapida(${producto.id})">Vista rápida</button>
            </div>
            <div class="product-info">
                <span class="product-category">${obtenerCategoria(producto.name)}</span>
                <h3 class="product-title">${producto.name}</h3>
                <div class="product-rating">
                    ${generarEstrellas(obtenerRatingAleatorio())}
                </div>
                <div class="product-price">
                    <span class="current-price">$${producto.price.toFixed(2)}</span>
                    ${producto.descuento ? `<span class="original-price">$${(producto.price * 1.2).toFixed(2)}</span>` : ''}
                </div>
                <div class="product-stock">
                    <i class="fas fa-${producto.stock > 0 ? 'check-circle' : 'times-circle'}"></i> 
                    Disponible: <span>${producto.stock} unidades</span>
                </div>
                <button class="btn btn-add-to-cart" onclick="agregarAlCarrito(${producto.id})" ${producto.stock <= 0 ? 'disabled' : ''}>
                    <i class="fas fa-cart-plus"></i> Añadir al carrito
                </button>
            </div>
        `;
        
        productsGrid.appendChild(productCard);
    });
}

// Funciones auxiliares
function esProductoNuevo(fechaCreacion) {
    if (!fechaCreacion) return false;
    const fechaProducto = new Date(fechaCreacion);
    const diferenciaDias = (new Date() - fechaProducto) / (1000 * 60 * 60 * 24);
    return diferenciaDias < 7;
}

function obtenerImagenProducto(nombre) {
    // Lógica para asignar imágenes basadas en el nombre del producto
    const imagenes = {
        'agua': 'agua-mineral.jpg',
        'energética': 'bebida-energetica.jpg',
        'refresco': 'refresco.jpg',
        'jugo': 'jugo-natural.jpg',
        'isotónica': 'bebida-isotonica.jpg'
    };
    
    const nombreLower = nombre.toLowerCase();
    for (const [key, value] of Object.entries(imagenes)) {
        if (nombreLower.includes(key)) {
            return value;
        }
    }
    return 'default-product.jpg';
}

function obtenerCategoria(nombre) {
    const nombreLower = nombre.toLowerCase();
    if (nombreLower.includes('agua')) return 'Agua Mineral';
    if (nombreLower.includes('energética') || nombreLower.includes('energetica')) return 'Bebida Energética';
    if (nombreLower.includes('refresco')) return 'Refresco';
    if (nombreLower.includes('jugo') || nombreLower.includes('zumo')) return 'Jugo Natural';
    if (nombreLower.includes('isotónica') || nombreLower.includes('isotonica')) return 'Bebida Isotónica';
    return 'Bebida';
}

function generarEstrellas(rating) {
    let estrellas = '';
    const estrellasLlenas = Math.floor(rating);
    const mediaEstrella = rating % 1 >= 0.5;
    
    for (let i = 0; i < 5; i++) {
        if (i < estrellasLlenas) {
            estrellas += '<i class="fas fa-star"></i>';
        } else if (i === estrellasLlenas && mediaEstrella) {
            estrellas += '<i class="fas fa-star-half-alt"></i>';
        } else {
            estrellas += '<i class="far fa-star"></i>';
        }
    }
    
    return estrellas + `<span class="rating-count">(${Math.floor(Math.random() * 50)})</span>`;
}

function obtenerRatingAleatorio() {
    return 3.5 + Math.random() * 1.5; // Entre 3.5 y 5
}

function actualizarContador(total) {
    document.getElementById('product-count').textContent = total;
    document.getElementById('total-products').textContent = total;
}

// Función para mostrar vista rápida (modal)
function mostrarVistaRapida(id) {
    fetch(`http://localhost:8080/api/v1/drinks/${id}`)
        .then(response => response.json())
        .then(producto => {
            const modal = document.getElementById('quickview-modal');
            const container = document.getElementById('quickview-container');
            
            container.innerHTML = `
                <div class="quickview-product">
                    <div class="quickview-image">
                        <img src="img/products/${obtenerImagenProducto(producto.name)}" alt="${producto.name}">
                    </div>
                    <div class="quickview-details">
                        <h2>${producto.name}</h2>
                        <div class="quickview-meta">
                            <span class="category">${obtenerCategoria(producto.name)}</span>
                            <div class="rating">
                                ${generarEstrellas(obtenerRatingAleatorio())}
                            </div>
                        </div>
                        <div class="quickview-price">
                            <span class="current">$${producto.price.toFixed(2)}</span>
                            ${producto.descuento ? `<span class="original">$${(producto.price * 1.2).toFixed(2)}</span>` : ''}
                        </div>
                        <p class="quickview-description">
                            ${generarDescripcion(producto.name)}
                        </p>
                        <div class="quickview-stock">
                            <i class="fas fa-${producto.stock > 0 ? 'check' : 'times'}"></i>
                            ${producto.stock > 0 ? 'Disponible' : 'Agotado'} (${producto.stock} unidades)
                        </div>
                        <div class="quickview-actions">
                            <button class="btn btn-add-to-cart" onclick="agregarAlCarrito(${producto.id})" ${producto.stock <= 0 ? 'disabled' : ''}>
                                <i class="fas fa-cart-plus"></i> Añadir al carrito
                            </button>
                            <button class="btn btn-close-quickview" onclick="cerrarVistaRapida()">
                                Cerrar
                            </button>
                        </div>
                    </div>
                </div>
            `;
            
            modal.style.display = 'block';
        });
}

function generarDescripcion(nombre) {
    // Descripciones genéricas basadas en el tipo de producto
    const descripciones = {
        'agua': 'Agua mineral natural de manantial, rica en minerales esenciales. Ideal para hidratación diaria.',
        'energética': 'Bebida energética con taurina y vitaminas para aumentar tu energía y concentración.',
        'refresco': 'Refresco carbonatado con un delicioso sabor. Perfecto para acompañar tus comidas.',
        'jugo': 'Jugo 100% natural sin conservantes ni azúcares añadidos. Rico en vitaminas.',
        'isotónica': 'Bebida isotónica que ayuda a reponer electrolitos perdidos durante el ejercicio.'
    };
    
    const nombreLower = nombre.toLowerCase();
    for (const [key, value] of Object.entries(descripciones)) {
        if (nombreLower.includes(key)) {
            return value;
        }
    }
    return 'Bebida refrescante de alta calidad. Perfecta para cualquier ocasión.';
}

function cerrarVistaRapida() {
    document.getElementById('quickview-modal').style.display = 'none';
}

// Función simulada para agregar al carrito
function agregarAlCarrito(id) {
    // Aquí implementarías la lógica real para agregar al carrito
    console.log(`Producto ${id} agregado al carrito`);
    alert('Producto agregado al carrito');
}

// Cargar productos cuando la página esté lista
document.addEventListener('DOMContentLoaded', function() {
    cargarProductos();
    
    // Configurar eventos de filtros
    document.getElementById('category-filter').addEventListener('change', filtrarProductos);
    document.getElementById('price-filter').addEventListener('change', filtrarProductos);
    document.getElementById('sort-by').addEventListener('change', filtrarProductos);
    document.getElementById('hero-search').addEventListener('input', filtrarProductos);
    document.querySelector('.btn-filter-reset').addEventListener('click', resetFiltros);
});

// Funciones para filtros (simplificadas)
function filtrarProductos() {
    // En una implementación real, harías una nueva solicitud a la API con los parámetros
    // o filtrarías los productos ya cargados
    console.log('Aplicando filtros...');
    cargarProductos(); // Recargamos para este ejemplo
}

function resetFiltros() {
    document.getElementById('category-filter').value = '';
    document.getElementById('price-filter').value = '';
    document.getElementById('sort-by').value = 'popular';
    document.getElementById('hero-search').value = '';
    cargarProductos();
}