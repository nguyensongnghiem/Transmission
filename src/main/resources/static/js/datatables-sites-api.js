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
    let $fileName = 'Danh sách trạm'
    $('#siteDatatablesApi').DataTable({
        // scrollY: 400,
        deferRender: true,
        scroller: true,
        fixedHeader: true,
        colReorder: true,
        responsive: true,
        lengthMenu: [
            [10, 25, 50, -1],
            [10, 25, 50, 'All']
        ],
        columnDefs: [{
            "defaultContent": "",
            "targets": "_all"
        }],
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
        },
        ajax: {
            url: "/site/api/list",
            dataSrc: ''
        },
        columns: [
            {data: 'province.name'},
            {data: 'siteId'},
            {data: 'siteName'},
            {data: 'latitude'},
            {data: 'longitude'},
            {data: 'address'},
            {data: 'siteOwner.name'},
            {data: 'siteTransmissionType.name'},
            {data: 'transmissionOwner.name'},
            {data: 'note'},
            {
                data: 'null',
                className: 'dt-center editor-edit',
                defaultContent: '<button class="btn btn-warning "><i class="fa fa-pencil"/></button>',
                orderable: false
            },
            {
                data: 'null',
                className: 'dt-center editor-edit',
                defaultContent: '<button class="btn btn-danger"><i class="fa fa-trash"/></button>',
                orderable: false
            }
        ]
    });
});
