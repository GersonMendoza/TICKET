$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consProcSoli([{name : 'codiSoliPara', value : row.id.trim()}]);
        });
        return false;
    };      
    INIT_OBJE_PROCSOLI();
});

function INIT_OBJE_PROCSOLI()
{
    $("#TablProcSoli").initBootTable();
}
