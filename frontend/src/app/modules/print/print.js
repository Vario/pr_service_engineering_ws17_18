/**
 * Created by wrichtsfeld on 12.01.18.
 */
!function(a){"use strict";function b(){function a(a,d,e){if(d.on("click",function(){var a=document.getElementById(e.printElementId);a&&c(a)}),window.matchMedia){var f=window.matchMedia("print");f.addListener(function(a){a.matches||b()})}window.onafterprint=b}function b(){d.innerHTML=""}function c(a){var b=a.cloneNode(!0);d.innerHTML="",d.appendChild(b),window.print()}var d=document.getElementById("printSection");return d||(d=document.createElement("div"),d.id="printSection",document.body.appendChild(d)),{link:a,restrict:"A"}}var c=a.module("ngPrint",[]);c.directive("ngPrint",[b])}(window.angular);
//# sourceMappingURL=ngPrint.min.js.map

/*
(function (a) {
    'use strict';
    function b() {
        function a(a, d, e) {
            if (d.on("click", function() {
                    var a = document.getElementById(e.printElementId);
                    a && c(a);
                }), window.matchMedia) {
                var f = window.matchMedia("print");
                f.addListener(function(a) {
                    a.matches || b();
                });
            }
            window.onafterprint = b;
        }

        function b() {
            d.innerHTML = "";
        }

        function c(a) {
            var b = a.cloneNode(!0);
            d.innerHTML = "", d.appendChild(b), window.print();
        }
        var d = document.getElementById("printSection");
        return d || (d = document.createElement("div"), d.id = "printSection", document.body.appendChild(d)), {
            link: a,
            restrict: "A"
        };
    }
    var c = a.module("ngPrint", []);
    c.directive("ngPrint", [b])
})(a);
 */