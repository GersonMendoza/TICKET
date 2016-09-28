$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consDepa([{name : 'codiDepaPara', value : row.id.trim()}]);
        });
        return false;
    };        
    INIT_OBJE_DEPA();
});

function INIT_OBJE_DEPA()
{
    $("#TablDepa").initBootTable();
}