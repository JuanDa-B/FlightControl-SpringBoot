document.addEventListener("DOMContentLoaded", function () {
    const flightsContainer = document.getElementById("flights-container");

    // Obtener los datos de los vuelos desde el API externo
    fetch('/api/flight/findAll')
        .then(response => response.json())
        .then(data => {
            // Mostrar cada vuelo en la tabla utilizando Bootstrap
            data.forEach(flight => {
                const flightElement = document.createElement("tr");
                flightElement.innerHTML = `
            <td>${flight.airplane.company}</td>
            <td>${flight.departure}</td>
            <td>${flight.arrival}</td>
            <td>${flight.date}</td>
            <td>${flight.departureTime} </td>
            <td>${flight.duration} min</td>
            <td><button class="btn btn-primary select-flight" data-flight-id="${flight.id}">Seleccionar Vuelo</button></td>
          `;
                flightsContainer.appendChild(flightElement);
            });

            // Agregar un evento de clic a cada botón de selección de vuelo
            const selectFlightButtons = document.querySelectorAll(".select-flight");
            selectFlightButtons.forEach(button => {
                button.addEventListener("click", function () {
                    const flightId = this.getAttribute("data-flight-id");
                    // Almacenar la información del vuelo seleccionado en el almacenamiento local
                    localStorage.setItem("selectedFlightId", flightId);

                    redireccionAsientos();

                });
            });
        })
        .catch(error => console.error('Error al obtener los datos de los vuelos:', error));
});

function redireccionAsientos() {
    $('#notificacionModal').modal('show');

    // Redirigir al usuario a la página de selección de asientos
    setTimeout(() => {
        // Ocultar el modal después de 3 segundos y redirigir a asientos.html
        $('#notificacionModal').modal('hide');
        window.location.href = "asientos";
    }, 2000);
}
