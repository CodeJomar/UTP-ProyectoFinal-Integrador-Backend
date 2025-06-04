document.addEventListener('DOMContentLoaded', function () {
  // Elementos DOM
  const loginBtn = document.getElementById('loginBtn');
  const registerBtn = document.getElementById('registerBtn');
  const loginModal = document.getElementById('loginModal');
  const registerModal = document.getElementById('registerModal');
  const modalOverlay = document.getElementById('modalOverlay');
  const closeButtons = document.querySelectorAll('.modal-close-JE');
  const switchToRegister = document.getElementById('switchToRegister');
  const switchToLogin = document.getElementById('switchToLogin');
  const togglePasswordButtons = document.querySelectorAll('.toggle-password-JE');
  const loginForm = document.getElementById('loginForm');
  const registerForm = document.getElementById('registerForm');

  // Abrir modal de inicio de sesión
  loginBtn.addEventListener('click', function () {
    openModal(loginModal);
  });

  // Abrir modal de registro
  registerBtn.addEventListener('click', function () {
    openModal(registerModal);
  });

  // Cerrar modales
  closeButtons.forEach(button => {
    button.addEventListener('click', function () {
      closeAllModals();
    });
  });

  // Cerrar al hacer clic en el overlay
  modalOverlay.addEventListener('click', function () {
    closeAllModals();
  });

  // Cambiar entre modales
  switchToRegister.addEventListener('click', function (e) {
    e.preventDefault();
    closeModal(loginModal);
    openModal(registerModal);
  });

  switchToLogin.addEventListener('click', function (e) {
    e.preventDefault();
    closeModal(registerModal);
    openModal(loginModal);
  });

  // Mostrar/ocultar contraseña
  togglePasswordButtons.forEach(button => {
    button.addEventListener('click', function () {
      const input = this.previousElementSibling;
      const icon = this.querySelector('.material-icons-sharp');

      if (input.type === 'password') {
        input.type = 'text';
        icon.textContent = 'visibility_off';
      } else {
        input.type = 'password';
        icon.textContent = 'visibility';
      }
    });
  });

  // Envío de formularios
  loginForm.addEventListener('submit', function (e) {
    e.preventDefault();

    // Aquí iría la lógica de autenticación
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    console.log('Iniciando sesión con:', { email, password });

    // Simulación de inicio de sesión exitoso
    alert('Inicio de sesión exitoso. Redirigiendo al dashboard...');
    window.location.href = 'dashboard'; // Redirigir al dashboard
  });

  registerForm.addEventListener('submit', function (e) {
    e.preventDefault();

    // Validar que las contraseñas coincidan
    const password = document.getElementById('registerPassword').value;
    const confirmPassword = document.getElementById('registerConfirmPassword').value;

    if (password !== confirmPassword) {
      alert('Las contraseñas no coinciden');
      return;
    }

    // Aquí iría la lógica de registro
    const username = document.getElementById('registerUsername').value;
    const email = document.getElementById('registerEmail').value;

    console.log('Registrando usuario:', { username, email, password });

    // Simulación de registro exitoso
    alert('Registro exitoso. Redirigiendo al dashboard...');
    window.location.href = 'dashboard'; // Redirigir al dashboard
  });

  // Funciones auxiliares
  function openModal(modal) {
    modalOverlay.style.display = 'block';
    modal.style.display = 'block';
    document.body.style.overflow = 'hidden'; // Evitar scroll
  }

  function closeModal(modal) {
    modal.style.display = 'none';
  }

  function closeAllModals() {
    modalOverlay.style.display = 'none';
    loginModal.style.display = 'none';
    registerModal.style.display = 'none';
    document.body.style.overflow = 'auto'; // Restaurar scroll
  }
});