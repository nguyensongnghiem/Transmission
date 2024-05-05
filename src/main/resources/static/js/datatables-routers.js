// window.addEventListener('DOMContentLoaded', event => {
//     // Simple-DataTables
//     // https://github.com/fiduswriter/Simple-DataTables/wiki
//
//     const routersDatatable = document.getElementById('routerDatatables');
//     if (routersDatatable) {
//         new simpleDatatables.DataTable(routersDatatable);
//     }
// });
$(document).ready(function() {
    $('#routerDatatables').DataTable({
        responsive: true,
        lengthMenu: [
            [10, 25, 50, -1],
            [10, 25, 50, 'All']
        ],
        dom: 'Blfrtip',
        buttons: [
            'copy', 'excel', 'pdf'
        ]
    });
});