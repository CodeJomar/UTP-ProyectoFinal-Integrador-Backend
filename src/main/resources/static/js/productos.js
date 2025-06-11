document.addEventListener('DOMContentLoaded', function () {
  console.log('Inicializando productos.js');
  initTabs();
  initEvents();
  initModal();
  checkIfEditing();
});

function initTabs() {
  const tabItems = document.querySelectorAll('.tab-item-JE');
  const tabPanes = document.querySelectorAll('.tab-pane-JE');

  console.log('Pestañas encontradas:', tabItems.length);
  console.log('Paneles encontrados:', tabPanes.length);

  tabItems.forEach(item => {
    item.addEventListener('click', function () {
      console.log('Pestaña clickeada:', this.getAttribute('data-tab'));

      tabItems.forEach(tab => tab.classList.remove('active-tab-JE'));
      tabPanes.forEach(pane => pane.classList.remove('active-pane-JE'));

      this.classList.add('active-tab-JE');

      const tabId = this.getAttribute('data-tab');
      const targetPane = document.getElementById(tabId);

      if (targetPane) {
        targetPane.classList.add('active-pane-JE');
        console.log('Panel activado:', tabId);
      } else {
        console.error('Panel no encontrado:', tabId);
      }
    });
  });
}

function initEvents() {
  const addProductBtn = document.getElementById('addProductBtn');
  if (addProductBtn) {
    addProductBtn.addEventListener('click', function () {
      console.log('Botón agregar producto clickeado');
      showTab('form');
      resetForm();
    });
  }

  const importProductsBtn = document.getElementById('importProductsBtn');
  if (importProductsBtn) {
    importProductsBtn.addEventListener('click', function () {
      showTab('products');
      resetForm();
    })
  }

  const cancelBtn = document.getElementById('cancelForm');
  if (cancelBtn) {
    cancelBtn.addEventListener('click', function () {
      console.log('Botón cancelar clickeado');
      showTab('products');
    });
  } else {
    console.error('Botón cancelForm no encontrado');
  }

  const resetBtn = document.getElementById('resetForm');
  if (resetBtn) {
    resetBtn.addEventListener('click', function () {
      console.log('Botón reset clickeado');
      resetForm();
    });
  } else {
    console.error('Botón resetForm no encontrado');
  }

  const exportBtn = document.getElementById('exportProductsBtn');
  if (exportBtn) {
    exportBtn.addEventListener('click', function () {
      confirm('¿Estás seguro de exportar datos?');
    });
  }
  initMobileMenu();
  initTheme();
}

function checkIfEditing() {
  // Verificar si hay un producto cargado para editar
  const productIdField = document.querySelector('input[name="id"]');

  // Si el campo ID tiene valor, significa que estamos editando
  if (productIdField && productIdField.value && productIdField.value.trim() !== '') {
    console.log('Producto en edición detectado, mostrando formulario');
    showTab('form');

    // Opcional: cambiar el título del formulario
    const formTitle = document.getElementById('formTitle');
    if (formTitle) {
      formTitle.textContent = 'Editando un Producto';
    }
  }
}

function showTab(tabName) {
  console.log('Mostrando pestaña:', tabName);

  const tabItems = document.querySelectorAll('.tab-item-JE');
  const tabPanes = document.querySelectorAll('.tab-pane-JE');

  tabItems.forEach(tab => tab.classList.remove('active-tab-JE'));
  tabPanes.forEach(pane => pane.classList.remove('active-pane-JE'));

  const targetTab = document.querySelector(`[data-tab="${tabName}"]`);
  const targetPane = document.getElementById(tabName);

  if (targetTab) {
    targetTab.classList.add('active-tab-JE');
  } else {
    console.error('Pestaña no encontrada:', tabName);
  }

  if (targetPane) {
    targetPane.classList.add('active-pane-JE');
  } else {
    console.error('Panel no encontrado:', tabName);
  }
}

function resetForm() {
  const form = document.getElementById('productForm');
  if (form) {
    form.reset();
  } else {
    console.error('Formulario productForm no encontrado');
  }
}

function initModal() {
  // Modal de confirmación
  const modalClose = document.getElementById('modalClose');
  const modalCancel = document.getElementById('modalCancel');

  if (modalClose) {
    modalClose.addEventListener('click', closeModal);
  }

  if (modalCancel) {
    modalCancel.addEventListener('click', closeModal);
  }
}

function showModal(title, message, onConfirm) {
  const modalTitle = document.getElementById('modalTitle');
  const modalMessage = document.getElementById('modalMessage');
  const confirmModal = document.getElementById('confirmModal');
  const modalConfirm = document.getElementById('modalConfirm');

  if (modalTitle) modalTitle.textContent = title;
  if (modalMessage) modalMessage.textContent = message;
  if (confirmModal) confirmModal.style.display = 'flex';
  if (modalConfirm) modalConfirm.onclick = onConfirm;
}

function closeModal() {
  const confirmModal = document.getElementById('confirmModal');
  if (confirmModal) {
    confirmModal.style.display = 'none';
  }
}

function initMobileMenu() {
  const menuBtn = document.getElementById('menu-btn-JE');
  const closeBtn = document.getElementById('close-btn-JE');
  const sidebar = document.querySelector('.aside-JE');

  if (menuBtn && sidebar) {
    menuBtn.addEventListener('click', function () {
      sidebar.style.display = 'block';
      sidebar.classList.add('show-menu-JE');
    });
  }

  if (closeBtn && sidebar) {
    closeBtn.addEventListener('click', function () {
      sidebar.style.display = 'none';
      sidebar.classList.remove('show-menu-JE');
    });
  }
}

function initTheme() {
  const themeToggler = document.querySelector('.theme-toggler-JE');

  if (themeToggler) {
    themeToggler.addEventListener('click', function () {
      document.body.classList.toggle('dark-theme-variables-JE');

      const icons = themeToggler.querySelectorAll('span');
      icons.forEach(icon => {
        icon.classList.toggle('active-JE');
      });
    });
  }
}