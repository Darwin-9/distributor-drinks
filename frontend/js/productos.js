import { urlBase } from './constante.js';

let urlDrinks = `${urlBase}drinks/`;

document.addEventListener('DOMContentLoaded', async () => {
    // Verificar que el input de búsqueda existe
    const buscarInput = document.getElementById('buscarInput');
    if (buscarInput) {
        buscarInput.addEventListener('input', async (e) => {
            await buscarBebidas(e.target.value);
        });
    }

    // Cargar bebidas iniciales
    await cargarBebidas();
});

async function cargarBebidas(pagina = 1, limite = 10) {
    try {
        const response = await fetch(`${urlDrinks}filter?page=${pagina}&limit=${limite}`);
        if (!response.ok) throw new Error(`Error HTTP: ${response.status}`);
        
        const data = await response.json();
        console.log('Datos recibidos:', data); // Para depuración
        
        // Manejar diferentes estructuras de respuesta
        const bebidas = Array.isArray(data) ? data : data.bebidas || data.items || [];
        
        if (!Array.isArray(bebidas)) {
            throw new Error('Formato de bebidas no es un array');
        }
        
        mostrarBebidas(bebidas);
        
        // Mostrar paginación si hay datos de total
        if (data.total) {
            configurarPaginacion(data.total, pagina, limite);
        }
    } catch (error) {
        console.error('Error al cargar bebidas:', error);
        mostrarMensajeError('No se pudieron cargar las bebidas. Intente recargar la página.');
    }
}

async function buscarBebidas(termino) {
    try {
        const response = await fetch(`${urlDrinks}filter?search=${encodeURIComponent(termino)}`);
        if (!response.ok) throw new Error(`Error HTTP: ${response.status}`);
        
        const data = await response.json();
        const bebidas = Array.isArray(data) ? data : data.bebidas || data.items || [];
        
        mostrarBebidas(bebidas);
        
        // Ocultar paginación durante la búsqueda
        const paginacion = document.getElementById('paginacion');
        if (paginacion) paginacion.style.display = 'none';
    } catch (error) {
        console.error('Error al buscar bebidas:', error);
        mostrarBebidas([]);
        mostrarMensajeError('Error al buscar bebidas. Intente nuevamente.');
    }
}

function mostrarBebidas(bebidas) {
    const contenedor = document.getElementById('contenedorProductos');
    if (!contenedor) return;
    
    contenedor.innerHTML = '';

    if (!bebidas || bebidas.length === 0) {
        contenedor.innerHTML = '<div class="producto"><p>No se encontraron bebidas.</p></div>';
        return;
    }

    bebidas.forEach(bebida => {
        const producto = document.createElement('div');
        producto.className = 'producto';
        
        producto.innerHTML = `
            <h3>${bebida.name || 'Nombre no disponible'}</h3>
            <p>Volumen: ${bebida.volume || 'N/A'}ml</p>
            <p>Precio: $${bebida.price ? bebida.price.toLocaleString() : '0'} COP</p>
            <p>Stock: ${bebida.stock || '0'} unidades</p>
        `;
        
        contenedor.appendChild(producto);
    });
}

function mostrarMensajeError(mensaje) {
    const contenedor = document.getElementById('contenedorProductos');
    if (contenedor) {
        contenedor.innerHTML = `<div class="error-mensaje">${mensaje}</div>`;
    }
}

function configurarPaginacion(totalBebidas, paginaActual, limite) {
    const paginacion = document.getElementById('paginacion');
    if (!paginacion) return;
    
    paginacion.style.display = 'flex';
    paginacion.innerHTML = '';
    
    const totalPaginas = Math.ceil(totalBebidas / limite);
    
    // Botón Anterior
    if (paginaActual > 1) {
        const btnAnterior = document.createElement('button');
        btnAnterior.textContent = 'Anterior';
        btnAnterior.addEventListener('click', () => cargarBebidas(paginaActual - 1, limite));
        paginacion.appendChild(btnAnterior);
    }
    
    // Números de página
    const inicio = Math.max(1, paginaActual - 2);
    const fin = Math.min(totalPaginas, paginaActual + 2);
    
    for (let i = inicio; i <= fin; i++) {
        const btnPagina = document.createElement('button');
        btnPagina.textContent = i;
        if (i === paginaActual) {
            btnPagina.className = 'active';
        }
        btnPagina.addEventListener('click', () => cargarBebidas(i, limite));
        paginacion.appendChild(btnPagina);
    }
    
    // Botón Siguiente
    if (paginaActual < totalPaginas) {
        const btnSiguiente = document.createElement('button');
        btnSiguiente.textContent = 'Siguiente';
        btnSiguiente.addEventListener('click', () => cargarBebidas(paginaActual + 1, limite));
        paginacion.appendChild(btnSiguiente);
    }
}