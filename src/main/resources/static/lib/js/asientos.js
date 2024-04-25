const containerEco = document.querySelector(".container");
const containerBusiness = document.querySelector(".container_business");
const seats = document.querySelectorAll(".row .seat:not(.sold)");
const seatsBusiness = document.querySelectorAll(".row .seat_bs:not(.sold)");
const count = document.getElementById("count");
const total = document.getElementById("total");
const movieSelect = document.getElementById("movie");

populateUI();

function setMovieData(movieIndex, moviePrice) {
  localStorage.setItem("selectedMovieIndex", movieIndex);
  localStorage.setItem("selectedMoviePrice", moviePrice);
}

// Función para actualizar el contador y el total
function updateSelectedCount(selectedSeatType, selectedSeatValue) {

  localStorage.setItem("selectedSeatType", selectedSeatType);
  localStorage.setItem("selectedSeatValue", selectedSeatValue);
}

// Obtener el tipo de asiento y el valor del asiento seleccionado del local storage y actualizar la UI
function populateUI() {
 
  const selectedSeatType = localStorage.getItem("selectedSeatType");
  const selectedSeatValue = localStorage.getItem("selectedSeatValue");

  if (localStorage.getItem("document") && localStorage.getItem("name")) {
    // Obtener los datos del localStorage
    const documentoLocalStorage = localStorage.getItem("document");
    const nombreLocalStorage = localStorage.getItem("name");
  
    // Llenar los campos del documento y nombre con los datos del localStorage
    document.getElementById('passengerDocument').value = documentoLocalStorage;
    document.getElementById('passengerInfo').value = nombreLocalStorage;
  }

  if (selectedSeatType && selectedSeatValue) {
    const selectedSeat = document.querySelector(`.${selectedSeatType}[value="${selectedSeatValue}"]`);
    if (selectedSeat) {
      selectedSeat.classList.add("selected");
    }
  }



  fetch('/api/reservation/findAll/flight/' + localStorage.getItem('selectedFlightId'))
  .then(response => response.json())
  .then(data => {
    // Obtener la lista de reservas del vuelo
    const reservations = data;

    // Actualizar la clase de los asientos vendidos en la página
    reservations.forEach(reservation => {
      let seatElement;
      if (reservation.seat.seatClass === "Ejecutiva") {
        seatElement = document.querySelector(`.seat_bs[value="${reservation.seat.id}"]`);
      } else {
        seatElement = document.querySelector(`.seat[value="${reservation.seat.id}"]`);
      }
      
      if (seatElement) {
        seatElement.classList.add('sold');
      }
    });
  })
  .catch(error => console.error('Error al obtener las reservas:', error));
}
// Seat click event for normal seats
document.querySelector('.containerEco').addEventListener("click", (e) => {
  if (
    e.target.classList.contains("seat") &&
    !e.target.classList.contains("sold")
  ) {
    // Deseleccionar todos los asientos seleccionados de los asientos de negocio
    document.querySelectorAll('.containerEco .seat').forEach(( seat) => {
      seat.classList.remove("selected");
    });

    seatsBusiness.forEach((seatsBusiness) => {
      seatsBusiness.classList.remove("selected");
    });

    // Seleccionar el asiento actual
    e.target.classList.add("selected");

    // Obtener el tipo de asiento y el valor del asiento seleccionado y actualizar el total
    const selectedSeatType = "Economica";
    const selectedSeatValue = e.target.getAttribute("value");
    localStorage.setItem("selectedSeatValue", selectedSeatValue);
    document.getElementById('selectedSeat').value = e.target.innerText;
    updateSelectedCount(selectedSeatType, selectedSeatValue);
  }
});


// Seat click event for business seats
containerBusiness.addEventListener("click", (e) => {
  if (
    e.target.classList.contains("seat_bs") &&
    !e.target.classList.contains("sold")
  ) {
    // Deseleccionar todos los asientos seleccionados de los asientos de negocio
    seatsBusiness.forEach((seat) => {
      seat.classList.remove("selected");
    });

    // Deseleccionar todos los asientos seleccionados de los asientos normales
    seats.forEach((seat) => {
      seat.classList.remove("selected");
    });

    // Seleccionar el asiento actual
    e.target.classList.add("selected");

    // Obtener el tipo de asiento y el valor del asiento seleccionado y actualizar el total
    const selectedSeatType = "Ejecutiva";
    const selectedSeatValue = e.target.getAttribute("value");
    localStorage.setItem("selectedSeatValue", selectedSeatValue);
    document.getElementById('selectedSeat').value = e.target.innerText;
    updateSelectedCount(selectedSeatType, selectedSeatValue);
  }
});



// Inicializar la UI
populateUI();

// Obtener los select de clase de asiento y ubicación del asiento
const seatClassSelect = document.getElementById("seatClass");
const seatLocationSelect = document.getElementById("seatLocation");


  // Agregar evento de clic al botón
  document.querySelector('#reservar').addEventListener('click', function () {
    // Obtener los datos necesarios
    const reservationDate = new Date().toISOString().split('T')[0];
    const passengerId = localStorage.getItem('id');
    const selectedFlightId = localStorage.getItem('selectedFlightId');
    const selectedSeatValue = localStorage.getItem('selectedSeatValue');
  
    // Objeto de datos a enviar a la API
    const data = {
      reservationDate: reservationDate,
      passenger: { id: parseInt(passengerId) },
      flight: { id: parseInt(selectedFlightId) },
      seat: { id: parseInt(selectedSeatValue) }
    };
  
    // Realizar la solicitud POST a la API
    fetch('/api/reservation/save', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      if (response.status === 201) {
        // Si el código de estado es 201 (Created), mostrar el modal de éxito
        document.getElementById('paymentResult').textContent = 'La reserva se ha realizado correctamente.';
        $('#paymentModal').modal('show');
        populateUI();
      } else {
        // Si el código de estado es diferente de 201, mostrar un mensaje de error
        throw new Error('Error al realizar la reserva. Código de estado: ' + response.status);
      }
    })
    .catch(error => {
      console.error('Error al realizar la reserva:', error);
      document.getElementById('paymentResult').textContent = 'Error al realizar la reserva. Por favor, inténtalo de nuevo más tarde.';
      $('#paymentModal').modal('show');
    });
  });

  function menu() {
    $('#notificacionModal #consultaMessageModal').text('Cargando Informacion de Usuario');
    $('#notificacionModal').modal('show');


   

    setTimeout(() => {
        // Ocultar el modal después de 3 segundos y redirigir a vuelos.html
        $('#notificacionModal').modal('hide');
        window.location.href = "menu";
    }, 2000);

}

function salir() {
  $('#notificacionModal #consultaMessageModal').text('Cerrando Sesion');
  $('#notificacionModal').modal('show');
 

  setTimeout(() => {
      // Ocultar el modal después de 3 segundos y redirigir a vuelos.html
      $('#notificacionModal').modal('hide');
      window.location.href = "/";
  }, 2000);

}

  