document.addEventListener("DOMContentLoaded", function () {

    actualizar();

});

function prueba(){
    window.location.href = "reservas";
}

function actualizar() {
    const flightsContainer = document.getElementById("flights-container");

    const documentNumber = localStorage.getItem("document");

    // Obtener los datos de las reservaciones del pasajero desde el API externo
    fetch('/api/passenger/find/document/' + documentNumber)
        .then(response => response.json())
        .then(data => {
            // Obtener la lista de reservaciones del pasajero
            const reservations = data.reservationList;

            // Mostrar cada vuelo en la tabla utilizando Bootstrap
            reservations.forEach(reservation => {
                const flight = reservation.flight;
                const seat = reservation.seat;
                const passenger = reservation.passenger;
                const flightElement = document.createElement("tr");
                flightElement.innerHTML = `
            <td>${reservation.reservationDate}</td>
            <td>${flight.departure}</td>
            <td>${flight.arrival}</td>
            <td>${flight.date}</td>
            <td>${flight.departureTime}</td>
            <td>${flight.duration} min</td>
            <td>${seat.seatClass}</td>
            <td>${seat.location}</td>
            <td>${seat.seatType}</td>
            <td>
                <button class="btn btn-danger cancel-reservation" data-reservation-id="${reservation.id}">Cancelar Reserva</button>
            </td>
          `;
                flightsContainer.appendChild(flightElement);
            });

            // Agregar un evento de clic a cada botón de cancelación de reserva
            const cancelReservationButtons = document.querySelectorAll(".cancel-reservation");
            cancelReservationButtons.forEach(button => {
                button.addEventListener("click", function () {
                    const reservationId = this.getAttribute("data-reservation-id");
                    // Realizar una solicitud DELETE a la API para cancelar la reserva
                    fetch(`/api/reservation/delete/${reservationId}`, {
                        method: 'DELETE'
                    })
                        .then(response => {
                            if (response.ok) {
                                $('#registroExitosoModal').modal('show');
                                // Si la solicitud se completó con éxito, actualizar la interfaz o mostrar un mensaje

                                // Aquí puedes actualizar la interfaz para reflejar que la reserva fue cancelada
                            } else {
                                // Si la solicitud falla, mostrar un mensaje de error
                                console.error('Error al cancelar la reserva');
                            }
                        })
                        .catch(error => {
                            console.error('Error al cancelar la reserva:', error);
                        });
                });
            });
        })
        .catch(error => console.error('Error al obtener los datos de las reservaciones:', error));
}

function redireccionAsientos() {
    $('#notificacionModal').modal('show');


    // Redirigir al usuario a la página de selección de asientos
    setTimeout(() => {
        // Ocultar el modal después de 3 segundos y redirigir a asientos.html
        $('#notificacionModal').modal('hide');
        window.location.href = "asientos";
    }, 2000);
}
