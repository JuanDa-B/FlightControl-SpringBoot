// Definir una función para limpiar el localStorage
function limpiarLocalStorage() {
  localStorage.clear(); // Limpiar todo el localStorage
}

// Evento que se activa cuando la página se carga completamente
document.addEventListener('DOMContentLoaded', function () {
  // Llamar a la función para limpiar el localStorage
  limpiarLocalStorage();
});

// Función para abrir el modal de registro y prellenar el campo de documento
function abrirModalDeRegistro(documento) {
  // Abrir el modal de registro
  $('#registroModal').modal('show');

  // Poner el valor del documento en el campo de documento del modal de registro
  document.getElementById('documentoRegistro').value = documento;
}
// Event listener para el formulario de consulta
document.getElementById('consultaForm').addEventListener('submit', function (event) {
  event.preventDefault(); // Evitar que el formulario se envíe automáticamente

  // Obtener el número de documento ingresado por el usuario
  const documento = document.getElementById('documento').value;

  // Mostrar el símbolo de carga y el mensaje de consulta
  $('#notificacionModal').modal('show');

  // Hacer la petición GET a la API
  fetch(`/api/passenger/find/document/${documento}`)
    .then(response => {
      if (response.ok) {
        // Si la respuesta es exitosa, redirigir al usuario a "asientos.html"
        setTimeout(() => {
          // Ocultar el modal después de 3 segundos y redirigir a asientos.html
          $('#notificacionModal #consultaMessageModal').text('Cargando Informacion del Usuario');
          setTimeout(() => {
            // Ocultar el modal después de 3 segundos y redirigir a asientos.html
            $('#notificacionModal').modal('hide');
            window.location.href = "menu";
          }, 2000);
        }, 1000);

        

        // Devolver la respuesta en formato JSON
        return response.json(); 
      } else if (response.status === 400) {
        // Si la respuesta es un 400 (bad request), mostrar un popup de error con el mensaje de la respuesta
        setTimeout(() => {
          // Ocultar el modal después de 3 segundos y mostrar el modal de error
          response.text().then(errorMessage => {
            $('#notificacionModal').modal('hide');
            $('#errorModal').modal('show');
          });
        }, 2000);
      } else {
        // Si hay otro tipo de error en la petición, mostrar un mensaje de error genérico
        setTimeout(() => {
          response.text().then(errorMessage => {
            $('#notificacionModal').modal('hide');
            $('#genericErrorModal').modal('show');
          });
        }, 2000);
      }
      // Lanzar una excepción para pasar al siguiente bloque catch si ocurre algún error
      
    })
    .then(data => {
      // Guardar los datos en el localStorage
      localStorage.setItem("id",data.id);
      localStorage.setItem("document", data.document);
      localStorage.setItem("name", data.name);
      // Devolver los datos para poder acceder a ellos en el siguiente bloque then
      return data;
    })
    .catch(error => {
      // Si hay un error en la petición, mostrar un mensaje de error de registro
      // $('#registroErrorModal .modal-body').text('Ha ocurrido un error de comunicacion con el servidor. Por favor contacte a un administrador.');
      // $('#registroErrorModal').modal('show');
    })
    .finally(() => {
      // Ocultar el símbolo de carga y restablecer el mensaje
      $('#consultaMessage').hide();
      $('#notificacionModal').modal('hide');
      $('#consultaMessage').text('');
    });
});


// Event listener para el botón de registrarse en el modal de error
$('#errorModal .btn-primary').on('click', function () {
  // Obtener el valor del documento ingresado por el usuario en el campo de consulta
  const documento = document.getElementById('documento').value;

  // Abrir el modal de registro y prellenar el campo de documento
  abrirModalDeRegistro(documento);
});

// Event listener para el botón de cerrar en el modal de registro exitoso
$('#registroExitosoModal .btn-primary').on('click', function () {
  // Guardar los datos en el localStorage

  $('#notificacionModal').modal('show');

  // Ocultar el modal después de 3 segundos y redirigir a asientos.html
  $('#notificacionModal #consultaMessageModal').text('Cargando Informacion de Usuario');

  setTimeout(() => {
    // Ocultar el modal después de 3 segundos y redirigir a asientos.html
    $('#notificacionModal').modal('hide');
    window.location.href = "menu";
  }, 2000);
});

// Script para el formulario y modal de registro
document.getElementById('registroForm').addEventListener('submit', function (event) {
  event.preventDefault(); // Evitar que el formulario se envíe automáticamente

  // Obtener los datos ingresados por el usuario
  const documentoRegistro = document.getElementById('documentoRegistro').value;
  const nombreRegistro = document.getElementById('nombreRegistro').value;

  // Crear un objeto con los datos del nuevo pasajero
  const nuevoPasajero = {
    document: documentoRegistro,
    name: nombreRegistro
  };

  // Hacer la petición POST a la API para registrar al nuevo pasajero
  fetch('/api/passenger/save', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(nuevoPasajero)
  })
    .then(response => {
      if (response.ok) {
        // Si la respuesta es exitosa, mostrar un mensaje de registro exitoso
        $('#registroModal').modal('hide');
        $('#registroExitosoModal').modal('show');
        localStorage.setItem("document", nuevoPasajero.document);
        localStorage.setItem("name", nuevoPasajero.name);
        
        response.text().then(id => {
          localStorage.setItem("id", id);
        });
    

        // No es necesario guardar los datos en el localStorage aquí, ya se han guardado cuando se cierra el modal de registro exitoso
      } else if (response.status === 400) {
        // Si hay un error en la petición, mostrar un mensaje de error de registro
        response.text().then(errorMessage => {
          // Cerrar el modal de registro
          $('#registroModal').modal('hide');
          // Mostrar el modal de error de registro con el mensaje de error
          $('#registroErrorModal .modal-body').text(errorMessage); // Reemplaza el contenido del modal body con el mensaje de error
          $('#registroErrorModal').modal('show');
        });
      } else {
        // Si hay otro tipo de error en la petición, mostrar un mensaje de error de registro genérico
        $('#registroErrorModal .modal-body').text('Ocurrió un error al intentar registrar al pasajero. Por favor, inténtelo de nuevo.');
        $('#registroErrorModal').modal('show');
      }
    })
    .catch(error => {
      // Si hay un error en la petición, mostrar un mensaje de error de registro
      console.error('Error al registrar el nuevo pasajero:', error);
      $('#registroErrorModal .modal-body').text('Ocurrió un error al intentar registrar al pasajero. Por favor, inténtelo de nuevo.');
      $('#registroErrorModal').modal('show');
    });
});



document.getElementById('documento').addEventListener('input', function (event) {
  const documentoInput = event.target;
  let documentoValue = documentoInput.value.trim(); // Eliminar espacios en blanco al principio y al final

  // Eliminar caracteres no numéricos del valor del documento
  documentoValue = documentoValue.replace(/\D/g, '');

  const validLengths = [8, 10]; // Longitudes válidas para el documento

  if (!validLengths.includes(documentoValue.length)) {
    $('')
    documentoInput.classList.add('is-invalid'); // Agregar clase de Bootstrap para indicar error
    document.querySelector('button[type="submit"]').disabled = true; // Deshabilitar el botón
  } else {
    documentoInput.classList.remove('is-invalid'); // Eliminar clase de Bootstrap si el valor es válido
    document.querySelector('button[type="submit"]').disabled = false; // Habilitar el botón
  }

  // Actualizar el valor del campo con el valor limpio
  documentoInput.value = documentoValue;
});
