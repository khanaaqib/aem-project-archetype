(function($) {


    foundationReg = $(window).adaptTo("foundation-registry");
          foundationReg.register("foundation.validation.validator", {
        selector: "[data-validation=fname-validation]",
        validate: function(ele) {
            var element = ele.value;
            console.log("element value",element);
            var regex = /[0-9]/;
            var letter_regex = /[a-z]/
            var error = "Number is not allowed";
            var lettererror = "small letter is not allowed";
            if(regex.test(element)){
                 console.log("error number msg",error)
                 return error;
            } 
            if(letter_regex.test(element)){
                  return lettererror;
            }

        }
    });
    

}(jQuery));
