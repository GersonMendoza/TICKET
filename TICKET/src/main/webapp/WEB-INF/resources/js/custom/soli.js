$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consSoli([{name : 'codiSoliPara', value : row.id.trim()}]);
        });
        return false;
    };        
    INIT_OBJE_SOLI();
});

function INIT_OBJE_SOLI()
{
    $("#TablSoli").initBootTable();
}