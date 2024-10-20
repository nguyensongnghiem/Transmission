// window.addEventListener('DOMContentLoaded', event => {
//     // Simple-DataTables
//     // https://github.com/fiduswriter/Simple-DataTables/wiki
//
//     const sitesDatatable = document.getElementById('siteDatatables');
//     if (sitesDatatable) {
//         new simpleDatatables.DataTable(sitesDatatable);
//     }
// });

$(document).ready(function () {
    let $fileName = 'Danh sách kênh thuê'
    $('#leaseLineDataTables').DataTable({
        scrollY:        400,
        deferRender:    true,
        scroller:       true,
        fixedHeader: true,
        colReorder: true,
        responsive: true,
        lengthMenu: [
            [10, 25, 50, -1],
            [10, 25, 50, 'All']
        ],
        dom: 'Blfrtip',
        buttons: [
            {
                extend: 'csv',
                filename: $fileName
            },
            {
                extend: 'excel',
                filename: $fileName
            },
            {
                extend: 'pdf',
                filename: $fileName
            }
        ],
        initComplete: function () {
            this.api()
                .columns()
                .every(function () {
                    let column = this;
                    let title = column.footer().textContent;

                    // Create input element
                    let input = document.createElement('input');
                    input.placeholder = title;
                    column.footer().replaceChildren(input);
                    input.classList.add('form-control')

                    // Event listener for user input
                    input.addEventListener('keyup', () => {
                        if (column.search() !== this.value) {
                            column.search(input.value).draw();
                        }
                    });
                });
        }
    });
});
