
function handleInputFocus(inputElement, nameLabelElement) {
    inputElement.focus(function(){
        nameLabelElement.css('top', '30%');
        inputElement.css('border','2px solid #338dbc')
    });
    inputElement.on('keyup', function() {
        if (inputElement.val() !== "") {
            nameLabelElement.css('top', '30%');
        }
    });
}
handleInputFocus($('.payment_infor-name-input'), $('.payment_infor-name-label'));
handleInputFocus($('.payment_infor-phone-input'), $('.payment_infor-phone-label'));
handleInputFocus($('.payment_infor-address-input'), $('.payment_infor-address-label'));
handleInputFocus($('.payment_infor-district-input'), $('.payment_infor-district-label'));
handleInputFocus($('.payment_infor-province-input'), $('.payment_infor-province-label'));
handleInputFocus($('.payment_infor-wards-input'), $('.payment_infor-wards-label'));
