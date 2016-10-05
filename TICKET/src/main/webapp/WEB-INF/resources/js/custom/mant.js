$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consMant([{name : 'codiMantPara', value : row.id.trim()}]);
        });
        return false;
    };        
    INIT_OBJE_MANT();
});

function INIT_OBJE_MANT()
{
    $("#TablMant").initBootTable();
}