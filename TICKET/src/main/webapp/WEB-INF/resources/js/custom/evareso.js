$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consEvaReso([{name : 'codiEvaResoPara', value : row.id.trim()}]);
        });
        return false;
    };
    INIT_OBJE_EVARESO();
});

function INIT_OBJE_EVARESO()
{
    $("#TablEvaReso").initBootTable();
}