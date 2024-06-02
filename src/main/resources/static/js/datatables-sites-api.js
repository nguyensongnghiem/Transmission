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
    var contextPath = window.location.origin
    console.log(contextPath)

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
            url: "/site/api/list",
            dataSrc: ''           
        },
        columns: [
            {data: 'province.name'},
            {
                data: 'siteId',
                className: 'dt-center editor-edit',
                render: function (data,type,row) {
                    return `<a href="${contextPath}/site/detail?siteId=${row.siteId}" >${row.siteId}</a>`
                },
                orderable: false
            },
            {data: 'siteId2'},
            {data: 'siteName'},
            {data: 'latitude'},
            {data: 'longitude'},
            {data: 'siteOwner.name'},
            {data: 'siteTransmissionType.name'},
            {data: 'transmissionOwner.name'},
            {data: 'note'},
            {
                data: 'id',
                className: 'dt-center editor-edit',
                render: function (data,type,row) {
                    return `<a href="${contextPath}/site/edit/${row.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil fa-sm"/></a>`
                },
                orderable: false
            },
            {
                data: 'id',
                className: 'dt-center editor-edit',
                render: function (data,type,row) {
                    return `<button class="btn btn-danger btn-edit btn-sm"
                   onclick="deleteSite(${row.id},\'${row.siteId}\')"
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
