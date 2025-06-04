document.addEventListener('DOMContentLoaded', function () {
  // Inicializar funcionalidades
  initFormValidation();
  initFormEvents();
});

function initFormValidation() {
  const form = document.getElementById('reportForm');
  const requiredFields = form.querySelectorAll('[required]');

  requiredFields.forEach(field => {
    field.addEventListener('blur', validateField);
    field.addEventListener('input', clearFieldError);
  });

  function validateField(e) {
    const field = e.target;
    const value = field.value.trim();

    if (!value) {
      showFieldError(field, 'Este campo es obligatorio');
      return false;
    }

    if (field.type === 'email' && !isValidEmail(value)) {
      showFieldError(field, 'Ingresa un email válido');
      return false;
    }

    clearFieldError(field);
    return true;
  }

  function showFieldError(field, message) {
    clearFieldError(field);
    field.style.borderColor = 'var(--color-peligro)';

    const errorDiv = document.createElement('div');
    errorDiv.className = 'field-error-JE';
    errorDiv.style.color = 'var(--color-peligro)';
    errorDiv.style.fontSize = '0.8rem';
    errorDiv.style.marginTop = '0.3rem';
    errorDiv.textContent = message;

    field.parentNode.appendChild(errorDiv);
  }

  function clearFieldError(field) {
    field.style.borderColor = 'var(--color-info-claro)';
    const existingError = field.parentNode.querySelector('.field-error-JE');
    if (existingError) {
      existingError.remove();
    }
  }

  function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
}

function initFormEvents() {
  const form = document.getElementById('reportForm');

  // Envío del formulario
  form.addEventListener('submit', function (e) {
    e.preventDefault();

    if (validateForm()) {
      submitReport();
    }
  });

  function validateForm() {
    const requiredFields = form.querySelectorAll('[required]');
    let isValid = true;

    requiredFields.forEach(field => {
      if (!field.value.trim()) {
        field.focus();
        isValid = false;
        return;
      }
    });

    return isValid;
  }

  function submitReport() {
    // Mostrar loading
    const submitBtn = form.querySelector('button[type="submit"]');
    const originalText = submitBtn.innerHTML;
    submitBtn.innerHTML = '<span class="material-icons-sharp">hourglass_empty</span> Enviando...';
    submitBtn.disabled = true;

    // Simular envío (aquí se conectaría con el backend)
    setTimeout(() => {
      alert('¡Reporte enviado exitosamente! Te contactaremos pronto.');

      // Resetear formulario
      form.reset();

      // Restaurar botón
      submitBtn.innerHTML = originalText;
      submitBtn.disabled = false;
    }, 2000);
  }
}

// Función para exportar datos del reporte (para uso futuro con backend)
function exportReportData() {
  const form = document.getElementById('reportForm');
  const formData = new FormData(form);
  const reportData = {};

  for (let [key, value] of formData.entries()) {
    reportData[key] = value;
  }

  // Agregar información adicional
  reportData.timestamp = new Date().toISOString();
  reportData.userAgent = navigator.userAgent;
  reportData.url = window.location.href;

  return reportData;
}