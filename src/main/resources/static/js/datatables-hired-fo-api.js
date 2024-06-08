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
    let $fileName = 'Chi tiết hợp đồng thuê FO'
    var contextPath = window.location.origin
    $('#hiredFoDataTablesApi').DataTable({
        // scrollY:        400,
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
            "className": "dt-center",
            "targets": "_all",
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
            url: `/api/contracts/${contractId}/hired-fo`,
            dataSrc: ''
        },
        columns: [
            {
                className: 'dt-center editor-edit',
                render: function (data, type, row) {
                    let nearSite = row.nearSite!=null?row.nearSite.siteId:'UnknownSite'
                    let farSite = row.farSite!=null?row.farSite.siteId:'UnknownSite'
                    return `${nearSite} - ${farSite}`
                },
                orderable: false
            },
            {data: 'coreQuantity'},
            {data: 'finalDistance'},
            {data: 'cost'},
            {data: 'note'},
            {
                data: 'id',
                className: 'dt-center editor-edit',
                render: function (data, type, row) {
                    return `<a href="${contextPath}/hired-fo/edit/${row.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil fa-sm"/></a>`
                },
                orderable: false
            },
            {
                data: 'id',
                className: 'dt-center editor-edit',
                render: function (data, type, row) {
                    let nearSite = row.nearSite!=null?row.nearSite.siteId:'UnknownSite'
                    let farSite = row.farSite!=null?row.farSite.siteId:'UnknownSite'
                    let foLine = `${nearSite} - ${farSite}`
                    return `<button class="btn btn-danger btn-edit btn-sm"
                   onclick="deleteHiredFoLine(${row.id},\'${foLine}\')"                    
                   data-bs-toggle="modal" 
                   data-bs-target="#deleteModal">
                   <i class="fa fa-trash fa-sm"></i>
                </button>`;
                },
                orderable: false
            }
        ]
    });
});
