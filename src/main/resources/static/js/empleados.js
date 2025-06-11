document.addEventListener('DOMContentLoaded', function () {
  // Inicializar pestañas
  initTabs();

  // Inicializar eventos
  initEvents();
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
      document.getElementById(tabId).classList.add('active-pane-JE');
    });
  });
}

function initEvents() {
  // Botón agregar empleado
  document.getElementById('addEmployeeBtn').addEventListener('click', function () {
    showTab('form');
    resetForm();
  });

  // Botón cancelar formulario
  document.getElementById('cancelForm').addEventListener('click', function () {
    showTab('employees');
  });

  // Botón limpiar formulario
  document.getElementById('resetForm').addEventListener('click', resetForm);

  // Botones de acciones rápidas
  document.getElementById('importEmployeesBtn').addEventListener('click', function () {
    alert('Funcionalidad de importación en desarrollo...');
  });

  document.getElementById('exportEmployeesBtn').addEventListener('click', function () {
    alert('Funcionalidad de exportación en desarrollo...');
  });
}

function showTab(tabName) {
  const tabItems = document.querySelectorAll('.tab-item-JE');
  const tabPanes = document.querySelectorAll('.tab-pane-JE');

  tabItems.forEach(tab => tab.classList.remove('active-tab-JE'));
  tabPanes.forEach(pane => pane.classList.remove('active-pane-JE'));

  document.querySelector(`[data-tab="${tabName}"]`).classList.add('active-tab-JE');
  document.getElementById(tabName).classList.add('active-pane-JE');
}

function resetForm() {
  document.getElementById('employeeForm').reset();
}

// Modal de confirmación
function showModal(title, message, onConfirm) {
  document.getElementById('modalTitle').textContent = title;
  document.getElementById('modalMessage').textContent = message;
  document.getElementById('confirmModal').style.display = 'flex';

  document.getElementById('modalConfirm').onclick = onConfirm;
}

function closeModal() {
  document.getElementById('confirmModal').style.display = 'none';
}

// Inicializar modal
document.getElementById('modalClose').addEventListener('click', closeModal);
document.getElementById('modalCancel').addEventListener('click', closeModal);