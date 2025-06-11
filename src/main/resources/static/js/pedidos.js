document.addEventListener('DOMContentLoaded', function () {
  checkIfEditing();
  initTabs();
  initEvents();
  initModal();
});

function checkIfEditing() {
  // Verificar si hay un pedido cargado para editar
  const orderIdField = document.querySelector('input[name="id"]');

  // Si el campo ID tiene valor, significa que estamos editando
  if (orderIdField && orderIdField.value && orderIdField.value.trim() !== '') {
    showTab('form');

    // Cambiar el título del formulario
    const formTitle = document.getElementById('formTitle');
    if (formTitle) {
      formTitle.textContent = 'Editar Pedido';
    }

    // Cambiar el texto del botón
    const submitText = document.getElementById('submitText');
    if (submitText) {
      submitText.textContent = 'Actualizar Pedido';
    }
  }
}

function initTabs() {
  const tabItems = document.querySelectorAll('.tab-item-JE');
  const tabPanes = document.querySelectorAll('.tab-pane-JE');

  tabItems.forEach(item => {
    item.addEventListener('click', function () {
      // Remover clases activas
      tabItems.forEach(tab => tab.classList.remove('active-tab-JE'));
      tabPanes.forEach(pane => pane.classList.remove('active-pane-JE'));

      // Añadir clase activa a la pestaña
      this.classList.add('active-tab-JE');

      // Activar el panel correspondiente
      const tabId = this.getAttribute('data-tab');
      const targetPane = document.getElementById(tabId);

      if (targetPane) {
        targetPane.classList.add('active-pane-JE');
      }
    });
  });
}

function initEvents() {
  // Botón agregar pedido
  const addOrderBtn = document.getElementById('addOrderBtn');
  if (addOrderBtn) {
    addOrderBtn.addEventListener('click', function () {
      showTab('form');
      resetForm();
    });
  }

  // Botón cancelar formulario
  const cancelBtn = document.getElementById('cancelForm');
  if (cancelBtn) {
    cancelBtn.addEventListener('click', function () {
      showTab('orders');
    });
  }

  // Botón limpiar formulario
  const resetBtn = document.getElementById('resetForm');
  if (resetBtn) {
    resetBtn.addEventListener('click', function () {
      console.log('Botón reset clickeado');
      resetForm();
    });
  } else {
    console.error('Botón resetForm no encontrado');
  }

  // Botones de acciones rápidas
  const importBtn = document.getElementById('importOrdersBtn');
  if (importBtn) {
    importBtn.addEventListener('click', function () {
      showTab('products');
      resetForm();
    });
  }

  const exportBtn = document.getElementById('exportOrdersBtn');
  if (exportBtn) {
    exportBtn.addEventListener('click', function () {
      confirm('¿Estas seguro de exportar los datos?');
    });
  }

  // Inicializar menú móvil
  initMobileMenu();

  // Inicializar tema
  initTheme();
}

function showTab(tabName) {
  console.log('Mostrando pestaña:', tabName);

  const tabItems = document.querySelectorAll('.tab-item-JE');
  const tabPanes = document.querySelectorAll('.tab-pane-JE');

  // Remover clases activas
  tabItems.forEach(tab => tab.classList.remove('active-tab-JE'));
  tabPanes.forEach(pane => pane.classList.remove('active-pane-JE'));

  // Activar pestaña y panel
  const targetTab = document.querySelector(`[data-tab="${tabName}"]`);
  const targetPane = document.getElementById(tabName);

  if (targetTab) {
    targetTab.classList.add('active-tab-JE');
  }

  if (targetPane) {
    targetPane.classList.add('active-pane-JE');
  }
}

function resetForm() {
  const form = document.getElementById('orderForm');
  if (form) {
    form.reset();
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