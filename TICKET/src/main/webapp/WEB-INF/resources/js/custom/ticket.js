$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consTicket([{name : 'codiPara', value : row.id.trim()}]);
        });
        return false;
    };
    $.fn.initDatePick = function() {
        $(this).datepicker({
            format: "dd/mm/yyyy",
            language: "es",
            orientation: "top auto",
            autoclose: true,
            todayHighlight: true
        }).on('show.bs.modal', function(e) {
            // Quitar el conflicto con bootstrap modal "show.bs.modal"
            e.stopPropagation();
        }).on('hide.bs.modal', function(e) {
            // Quitar el conflicto con bootstrap modal "hide.bs.modal"
            e.stopPropagation();
        });
    };
    $('#ModaFormTicket').on('show.bs.modal', function() {
        INIT_OBJE_MODA_TICKET();
    });
    $('#ModaFormTicket').on('hide.bs.modal', function() {
        $("#TablTicket").bootstrapTable('uncheckAll');
    });

    
    INIT_OBJE_TICKET();
});

function INIT_OBJE_TICKET()
{
    $("#TablTicket").initBootTable();
    INIT_OBJE_MODA_TICKET();
}

function INIT_OBJE_MODA_TICKET()
{
    $("#FormTicket\\:btonElim").confirmation({container: '#FormTicket'});
}