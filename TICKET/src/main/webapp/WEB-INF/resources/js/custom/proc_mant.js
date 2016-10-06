$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consProcMant([{name : 'codiProcMantPara', value : row.id.trim()}]);
        });
        return false;
    };      
    INIT_OBJE_PROCMANT();
});

function INIT_OBJE_PROCMANT()
{
    $("#TablProcMant").initBootTable();
}
