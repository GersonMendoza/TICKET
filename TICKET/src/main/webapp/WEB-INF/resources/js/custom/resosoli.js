$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consResoSoli([{name : 'codiResoSoliPara', value : row.id.trim()}]);
        });
        return false;
    };
    INIT_OBJE_RESOSOLI();
});

function INIT_OBJE_RESOSOLI()
{
    $("#TablResoSoli").initBootTable();
}