$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consAlum([{name : 'codiSoliPara', value : row.id.trim()}]);
        });
        return false;
    };
    //Eliminar
    $.fn.funcElimSoli = function() {
        $(this).confirmation(
        {
            popout: true,
            onConfirm: function() {
                elimAlum();
                $('[data-toggle="confirmation-popout"]').confirmation('hide');
                return false;
            },
            onCancel: function()
            {
                $('[data-toggle="confirmation-popout"]').confirmation('hide');
                return false;
            }
        });
        return false;
    };
    
    INIT_OBJE_SOLI();
});

function INIT_OBJE_SOLI()
{
    $("#TablSoli").initBootTable();
    $("#FormSoli\\:btonElim").funcElimSoli();
}