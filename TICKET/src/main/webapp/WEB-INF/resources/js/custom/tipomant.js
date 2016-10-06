$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consTipoMant([{name : 'codiRTipoMantPara', value : row.id.trim()}]);
        });
        return false;
    };
    INIT_OBJE_TIPOMANT();
});

function INIT_OBJE_TIPOMANT()
{
    $("#TablTipoMant").initBootTable();
}