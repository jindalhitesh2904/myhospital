var properties = [
    'a',
    'b',
    'c',
    'd',
    'e',
    'f',
    'g'
];
var properties1 = [
    'h','i','j','k','l','m','n'
];
var properties2 = [
    'o','p','q','r','s','t','u'
];

$.each( properties, function( i, val ) {

    var orderClass = '';

    $("#" + val).click(function(e){
        console.log("clicked");
        e.preventDefault();
        $('.filter__link.filter__link--active').not(this).removeClass('filter__link--active');
        $(this).toggleClass('filter__link--active');
        $('.filter__link').removeClass('asc desc');

        if(orderClass == 'desc' || orderClass == '') {
            $(this).addClass('asc');
            orderClass = 'asc';
        } else {
            $(this).addClass('desc');
            orderClass = 'desc';
        }

        var parent = $(this).closest('.header__item');
        var index = $(".header__item").index(parent);
        var $table = $('.table-content');
        var rows = $table.find('.table-row').get();
        var isSelected = $(this).hasClass('filter__link--active');
        var isNumber = $(this).hasClass('filter__link--number');

        rows.sort(function(a, b){

            var x = $(a).find('.table-data').eq(index).text();
            var y = $(b).find('.table-data').eq(index).text();

            if(isNumber == true) {

                if(isSelected) {
                    return x - y;
                } else {
                    return y - x;
                }

            } else {

                if(isSelected) {
                    if(x < y) return -1;
                    if(x > y) return 1;
                    return 0;
                } else {
                    if(x > y) return -1;
                    if(x < y) return 1;
                    return 0;
                }
            }
        });

        $.each(rows, function(index,row) {
            $table.append(row);
        });

        return false;
    });

});
$.each( properties1, function( i, val ) {

    var orderClass = '';

    $("#" + val).click(function(e){
        console.log("clicked");
        e.preventDefault();
        $('.filter__link.filter__link--active').not(this).removeClass('filter__link--active');
        $(this).toggleClass('filter__link--active');
        $('.filter__link').removeClass('asc desc');

        if(orderClass == 'desc' || orderClass == '') {
            $(this).addClass('asc');
            orderClass = 'asc';
        } else {
            $(this).addClass('desc');
            orderClass = 'desc';
        }

        var parent = $(this).closest('.header__item');
        var index = $(".header__item").index(parent);
        var $table1 = $('.table-content1');
        var rows = $table1.find('.table-row1').get();
        console.log(rows.size);
        var isSelected = $(this).hasClass('filter__link--active');
        var isNumber = $(this).hasClass('filter__link--number');

        rows.sort(function(a, b){

            var x = $(a).find('.table-data1').eq(index).text();
            var y = $(b).find('.table-data1').eq(index).text();

            if(isNumber == true) {

                if(isSelected) {
                    return x - y;
                } else {
                    return y - x;
                }

            } else {

                if(isSelected) {
                    if(x < y) return -1;
                    if(x > y) return 1;
                    return 0;
                } else {
                    if(x > y) return -1;
                    if(x < y) return 1;
                    return 0;
                }
            }
        });

        $.each(rows, function(index,row) {
            $table1.append(row);
        });

        return false;
    });

});
$.each( properties2, function( i, val ) {

    var orderClass = '';

    $("#" + val).click(function(e){
        console.log("clicked");
        e.preventDefault();
        $('.filter__link.filter__link--active').not(this).removeClass('filter__link--active');
        $(this).toggleClass('filter__link--active');
        $('.filter__link').removeClass('asc desc');

        if(orderClass == 'desc' || orderClass == '') {
            $(this).addClass('asc');
            orderClass = 'asc';
        } else {
            $(this).addClass('desc');
            orderClass = 'desc';
        }

        var parent = $(this).closest('.header__item');
        var index = $(".header__item").index(parent);
        var $table2 = $('.table-content2');
        var rows = $table2.find('.table-row2').get();
        var isSelected = $(this).hasClass('filter__link--active');
        var isNumber = $(this).hasClass('filter__link--number');

        rows.sort(function(a, b){

            var x = $(a).find('.table-data2').eq(index).text();
            var y = $(b).find('.table-data2').eq(index).text();

            if(isNumber == true) {

                if(isSelected) {
                    return x - y;
                } else {
                    return y - x;
                }

            } else {

                if(isSelected) {
                    if(x < y) return -1;
                    if(x > y) return 1;
                    return 0;
                } else {
                    if(x > y) return -1;
                    if(x < y) return 1;
                    return 0;
                }
            }
        });

        $.each(rows, function(index,row) {
            $table2.append(row);
        });

        return false;
    });

});