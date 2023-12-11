$('.form-buscador .opcion label').click(function() {
    var checkbox = $(this).siblings('input[type="checkbox"]');
    checkbox.prop('checked', !checkbox.prop('checked'));
    $(this).toggleClass('selected');
});

$('#ajustes_toggle').change(function() {
    if ($(this).is(':checked')) {
        $('.ajustes-buscador').removeClass('wrap');
    } else {
        $('.ajustes-buscador').addClass('wrap');
    }
});

