(function($) {
    foundationReg = $(window).adaptTo("foundation-registry");
        foundationReg.register("foundation.validation.validator", {
        selector: "[data-validation=tabtitle-validation]",
        validate: function(ele) {
           var element = ele.value;
           var regex = /[0-9]/;
           var errorMessage = "Number is not allowed";
           if(regex.test(element)){
                    return errorMessage;
            }

        }
    });
}(jQuery));
