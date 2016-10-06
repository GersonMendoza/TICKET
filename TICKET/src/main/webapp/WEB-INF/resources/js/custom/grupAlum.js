$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consGrupAlum([{name : 'codiGrupAlumPara', value : row.id.trim()}]);
        });
        return false;
    };
    //Eliminar
    $.fn.funcElimGrupAlum = function() {
        $(this).confirmation(
        {
            popout: true,
            onConfirm: function() {
                elimGrupAlum();
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
    
    INIT_OBJE_GRUP_ALUM();
});

function INIT_OBJE_GRUP_ALUM()
{
    $("#TablGrupAlum").initBootTable();
    $("#FormGrupAlum\\:btonElim").funcElimGrupAlum();
}