$(document).ready(function(){
    bindEventCheckAllCheckBox('chackAll')
});
function bindEventCheckAllCheckBox(id){
    $('#'+ id).on('change', function(){
        if((this).checked){
            // enable all checkbox
            // con tim cha dung closest, nguoc lai dung find
            $(this).closest('table').find('input[type=checkbox]').prop('checked',true);
        } else{
            //     disable all checkbox
            $(this).closest('table').find('input[type=checkbox]').prop('checked',false);
        }
    });
}