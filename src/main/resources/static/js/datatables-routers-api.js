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
    let $fileName = 'Danh sách thiết bị'
    var contextPath = window.location.origin
    console.log(contextPath)
    $('#routerDatatablesApi').DataTable({
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
            url: "/router/api/list",
            dataSrc: ''
        },
        columns: [
            {data: 'site.province.name'},
            {
                data: 'site.siteId',
                className: 'dt-center editor-edit',
                render: function (data,type,row) {
                    return `<a href="${contextPath}/site/detail?siteId=${row.site.siteId}" >${row.site.siteId}</a>`
                },
                orderable: false
            },
            {data: 'name'},
            {data: 'routerType.name'},
            {data: 'transmissionDeviceType.name'},
            {data: 'ip'},
            {data: 'note'},
            {
                data: 'id',
                className: 'dt-center editor-edit',
                render: function (data,type,row) {
                    return `<a href="${contextPath}/router/edit/${row.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil fa-sm"/></a>`
                },
                orderable: false
            },
            {
                data: 'id',
                className: 'dt-center editor-edit',
                render: function (data,type,row) {
                    return `<button class="btn btn-danger btn-edit btn-sm"
                   onclick="deleteRouter(${row.id},\'${row.name}\')"
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