/**
 * Created by wrichtsfeld on 12.01.18.
 */
//!function(a){"use strict";function b(){function a(a,d,e){if(d.on("click",function(){var a=document.getElementById(e.printElementId);a&&c(a)}),window.matchMedia){var f=window.matchMedia("print");f.addListener(function(a){a.matches||b()})}window.onafterprint=b}function b(){d.innerHTML=""}function c(a){var b=a.cloneNode(!0);d.innerHTML="",d.appendChild(b),window.print()}var d=document.getElementById("printSection");return d||(d=document.createElement("div"),d.id="printSection",document.body.appendChild(d)),{link:a,restrict:"A"}}var c=a.module("ngPrint",[]);c.directive("ngPrint",[b])}(window.angular);
(function (angular) {
    'use strict';
    angular.module('apieval.directives', [

    ]);
    function printDirective() {
        var printSection = document.getElementById('printSection');
        // if there is no printing section, create one
        if (!printSection) {
            printSection = document.createElement('div');
            printSection.id = 'printSection';
            document.body.appendChild(printSection);
        }
        function link(scope, element, attrs) {
            element.on('click', function () {
                var elemToPrint = document.getElementById(attrs.printElementId);
                if (elemToPrint) {
                    printElement(elemToPrint);
                }
            });
            window.onafterprint = function () {
                // clean the print section before adding new content
                printSection.innerHTML = '';
            };
        }
        function printElement(elem) {
            // clones the element you want to print
            var domClone = elem.cloneNode(true);
            printSection.appendChild(domClone);
            window.print();
        }
        return {
            link: link,
            restrict: 'A'
        };
    }
    angular.module('apieval.directives').directive('ngPrint', [printDirective]);
})(angular);