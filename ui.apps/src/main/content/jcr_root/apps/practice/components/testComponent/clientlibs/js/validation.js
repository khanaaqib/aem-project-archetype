(function($) {

    var REGEX_SELECTOR = "firstname-validation";
    var REGEX_SELECTOR_NAME = "lastname-validation";
    var REGEX_SELECTOR_STATE = "state-validation";
    foundationReg = $(window).adaptTo("foundation-registry");

    foundationReg.register("foundation.validation.validator", {
        selector: "[data-validation='" + REGEX_SELECTOR + "']",
        validate: function(el) {
            let regex_pattern = /[0-9]/;
            var error_message = "The format must be letter";
            var result = el.value;

            if (regex_pattern.test(result)) {
                return error_message;
            }
        }
    });

    foundationReg.register("foundation.validation.validator", {
        selector: "[data-validation='" + REGEX_SELECTOR_NAME + "']",
        validate: function(el) {
            var regex_pattern = /[0-9]/;
            let error_message = "The format must be letter";
            var result = el.value;

            if (regex_pattern.test(result)) {
                return error_message;
            }
        }
    });


    foundationReg.register("foundation.validation.validator", {
        selector: "[data-validation='" + REGEX_SELECTOR_STATE + "']",
        validate: function(el) {
            var regex_pattern = /[0-9]/;
            let error_message = "The format must be letter";
            var result = el.value;

            if (regex_pattern.test(result)) {
                return error_message;
            }
        }
    });

}(jQuery));
