// window.addEventListener('DOMContentLoaded', event => {
//     // Simple-DataTables
//     // https://github.com/fiduswriter/Simple-DataTables/wiki
//
//     const sitesDatatable = document.getElementById('siteDatatables');
//     if (sitesDatatable) {
//         new simpleDatatables.DataTable(sitesDatatable);
//     }
// });

$(document).ready(function() {
    let $fileName = 'Danh sách trạm'
    // site info table
    $('#site-info-table').DataTable({
        info: false,
        ordering: false,
        paging: false,
        searching: false,
        responsive: true,
    });
// router info table
    $('#router-info-table').DataTable({
        info: false,
        ordering: false,
        paging: false,
        responsive: true,
        searching: false
    });

    // site info table
    $('#leaseline-info-table').DataTable({
        info: false,
        ordering: false,
        paging: false,
        responsive: true,
        searching: false
    });
});
