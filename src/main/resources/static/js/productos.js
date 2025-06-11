document.addEventListener('DOMContentLoaded', function () {
  initTabs();
  initEvents();
  initModal();
  checkIfEditing();
});

function initTabs() {
  const tabItems = document.querySelectorAll('.tab-item-JE');
  const tabPanes = document.querySelectorAll('.tab-pane-JE');

  tabItems.forEach(item => {
    item.addEventListener('click', function () {
      tabItems.forEach(tab => tab.classList.remove('active-tab-JE'));
      tabPanes.forEach(pane => pane.classList.remove('active-pane-JE'));

      this.classList.add('active-tab-JE');

      const tabId = this.getAttribute('data-tab');
      const targetPane = document.getElementById(tabId);

      if (targetPane) {
        targetPane.classList.add('active-pane-JE');
      }
    });
  });
}

function initEvents() {
  const addProductBtn = document.getElementById('addProductBtn');
  if (addProductBtn) {
    addProductBtn.addEventListener('click', function () {
      showTab('form');
      resetForm();
    });
  }

  const importProductsBtn = document.getElementById('importProductsBtn');
  if (importProductsBtn) {
    importProductsBtn.addEventListener('click', function () {
      showTab('products');
      resetForm();
    });
  }

  const cancelBtn = document.getElementById('cancelForm');
  if (cancelBtn) {
    cancelBtn.addEventListener('click', function () {
      showTab('products');
    });
  }

  const resetBtn = document.getElementById('resetForm');
  if (resetBtn) {
    resetBtn.addEventListener('click', function () {
      resetForm();
    });
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
  const productIdField = document.querySelector('input[name="id"]');

  if (productIdField && productIdField.value && productIdField.value.trim() !== '') {
    showTab('form');

    const formTitle = document.getElementById('formTitle');
    if (formTitle) {
      formTitle.textContent = 'Editando un Producto';
    }
  }
}

function showTab(tabName) {
  const tabItems = document.querySelectorAll('.tab-item-JE');
  const tabPanes = document.querySelectorAll('.tab-pane-JE');

  tabItems.forEach(tab => tab.classList.remove('active-tab-JE'));
  tabPanes.forEach(pane => pane.classList.remove('active-pane-JE'));

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
  const form = document.getElementById('productForm');
  if (form) {
    form.reset();
  }
}

function initModal() {
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