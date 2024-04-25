

document.addEventListener("DOMContentLoaded", function () {
    $('#nombre').text(localStorage.getItem('name'));
})

// Función para manejar el clic en el botón "Ver mis reservas"
function verMisReservas() {
    $('#notificacionModal #consultaMessageModal').text('Cargando Informacion de Reservas');
    $('#notificacionModal').modal('show');


   

    setTimeout(() => {
        // Ocultar el modal después de 3 segundos y redirigir a vuelos.html
        $('#notificacionModal').modal('hide');
        window.location.href = "reservas";
    }, 2000);
}

// Función para manejar el clic en el botón "Realizar una reserva"
function realizarReserva() {
    $('#notificacionModal #consultaMessageModal').text('Cargando Informacion de vuelos');
    $('#notificacionModal').modal('show');


   

    setTimeout(() => {
        // Ocultar el modal después de 3 segundos y redirigir a vuelos.html
        $('#notificacionModal').modal('hide');
        window.location.href = "vuelos";
    }, 2000);

}
