"use strict";
/*
 * ATTENTION: An "eval-source-map" devtool has been used.
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file with attached SourceMaps in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
self["webpackHotUpdate_N_E"]("app/mybank/page",{

/***/ "(app-client)/./src/components/yourCards/YourCards.tsx":
/*!************************************************!*\
  !*** ./src/components/yourCards/YourCards.tsx ***!
  \************************************************/
/***/ (function(module, __webpack_exports__, __webpack_require__) {

eval(__webpack_require__.ts("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react/jsx-dev-runtime */ \"(app-client)/./node_modules/next/dist/compiled/react/jsx-dev-runtime.js\");\n/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react */ \"(app-client)/./node_modules/next/dist/compiled/react/index.js\");\n/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_1__);\n/* harmony import */ var _YourCards_module_css__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./YourCards.module.css */ \"(app-client)/./src/components/yourCards/YourCards.module.css\");\n/* harmony import */ var _YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2__);\n/* __next_internal_client_entry_do_not_use__ default auto */ \nvar _s = $RefreshSig$();\n\n\nconst YourCards = (param)=>{\n    let { cardType, cardNumber, initialCardBalance, creditLimit, expirationDate } = param;\n    _s();\n    const [balance, setBalance] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)(initialCardBalance);\n    const spendMoney = (amount)=>{\n        if (balance + amount <= creditLimit) {\n            setBalance((prevBalance)=>prevBalance + amount);\n        } else {\n            alert(\"Credit limit exceeded\");\n        }\n    };\n    return /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.Fragment, {\n        children: [\n            /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"h2\", {\n                className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().cardType),\n                children: cardType\n            }, void 0, false, {\n                fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                lineNumber: 26,\n                columnNumber: 5\n            }, undefined),\n            /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"div\", {\n                className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().card),\n                children: [\n                    /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"div\", {\n                        className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().cardHeader)\n                    }, void 0, false, {\n                        fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                        lineNumber: 28,\n                        columnNumber: 7\n                    }, undefined),\n                    /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"h2\", {\n                        className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().cardNumber),\n                        children: [\n                            \"**** **** **** \",\n                            cardNumber\n                        ]\n                    }, void 0, true, {\n                        fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                        lineNumber: 30,\n                        columnNumber: 9\n                    }, undefined),\n                    /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"h2\", {\n                        className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().expirationDate),\n                        children: [\n                            \"Exp: \",\n                            expirationDate\n                        ]\n                    }, void 0, true, {\n                        fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                        lineNumber: 31,\n                        columnNumber: 9\n                    }, undefined)\n                ]\n            }, void 0, true, {\n                fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                lineNumber: 27,\n                columnNumber: 5\n            }, undefined),\n            /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"div\", {\n                className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().cardInfo)\n            }, void 0, false, {\n                fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                lineNumber: 34,\n                columnNumber: 7\n            }, undefined),\n            /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"div\", {\n                className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().cardLimit),\n                children: /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"h2\", {\n                    className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().creditLimit),\n                    children: [\n                        \"Credit Limit: $\",\n                        creditLimit.toFixed(2)\n                    ]\n                }, void 0, true, {\n                    fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                    lineNumber: 37,\n                    columnNumber: 9\n                }, undefined)\n            }, void 0, false, {\n                fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                lineNumber: 36,\n                columnNumber: 9\n            }, undefined),\n            /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"div\", {\n                className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().cardBalance),\n                children: /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"h2\", {\n                    className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().balance),\n                    children: [\n                        \"Balance: $\",\n                        balance.toFixed(2)\n                    ]\n                }, void 0, true, {\n                    fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                    lineNumber: 40,\n                    columnNumber: 9\n                }, undefined)\n            }, void 0, false, {\n                fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                lineNumber: 39,\n                columnNumber: 9\n            }, undefined),\n            /*#__PURE__*/ (0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(\"button\", {\n                className: (_YourCards_module_css__WEBPACK_IMPORTED_MODULE_2___default().spendButton),\n                onClick: ()=>spendMoney(50),\n                children: \"Spend $50\"\n            }, void 0, false, {\n                fileName: \"C:\\\\Users\\\\Jill\\\\Desktop\\\\230522\\\\Projects\\\\p2BankingSystem\\\\frontend\\\\src\\\\components\\\\yourCards\\\\YourCards.tsx\",\n                lineNumber: 42,\n                columnNumber: 7\n            }, undefined)\n        ]\n    }, void 0, true);\n};\n_s(YourCards, \"OjyySpQBIo82mKfVuWZ0zaaTiyI=\");\n_c = YourCards;\n/* harmony default export */ __webpack_exports__[\"default\"] = (YourCards);\nvar _c;\n$RefreshReg$(_c, \"YourCards\");\n\n\n;\n    // Wrapped in an IIFE to avoid polluting the global scope\n    ;\n    (function () {\n        var _a, _b;\n        // Legacy CSS implementations will `eval` browser code in a Node.js context\n        // to extract CSS. For backwards compatibility, we need to check we're in a\n        // browser context before continuing.\n        if (typeof self !== 'undefined' &&\n            // AMP / No-JS mode does not inject these helpers:\n            '$RefreshHelpers$' in self) {\n            // @ts-ignore __webpack_module__ is global\n            var currentExports = module.exports;\n            // @ts-ignore __webpack_module__ is global\n            var prevExports = (_b = (_a = module.hot.data) === null || _a === void 0 ? void 0 : _a.prevExports) !== null && _b !== void 0 ? _b : null;\n            // This cannot happen in MainTemplate because the exports mismatch between\n            // templating and execution.\n            self.$RefreshHelpers$.registerExportsForReactRefresh(currentExports, module.id);\n            // A module can be accepted automatically based on its exports, e.g. when\n            // it is a Refresh Boundary.\n            if (self.$RefreshHelpers$.isReactRefreshBoundary(currentExports)) {\n                // Save the previous exports on update so we can compare the boundary\n                // signatures.\n                module.hot.dispose(function (data) {\n                    data.prevExports = currentExports;\n                });\n                // Unconditionally accept an update to this module, we'll check if it's\n                // still a Refresh Boundary later.\n                // @ts-ignore importMeta is replaced in the loader\n                module.hot.accept();\n                // This field is set when the previous version of this module was a\n                // Refresh Boundary, letting us know we need to check for invalidation or\n                // enqueue an update.\n                if (prevExports !== null) {\n                    // A boundary can become ineligible if its exports are incompatible\n                    // with the previous exports.\n                    //\n                    // For example, if you add/remove/change exports, we'll want to\n                    // re-execute the importing modules, and force those components to\n                    // re-render. Similarly, if you convert a class component to a\n                    // function, we want to invalidate the boundary.\n                    if (self.$RefreshHelpers$.shouldInvalidateReactRefreshBoundary(prevExports, currentExports)) {\n                        module.hot.invalidate();\n                    }\n                    else {\n                        self.$RefreshHelpers$.scheduleUpdate();\n                    }\n                }\n            }\n            else {\n                // Since we just executed the code for the module, it's possible that the\n                // new exports made it ineligible for being a boundary.\n                // We only care about the case when we were _previously_ a boundary,\n                // because we already accepted this update (accidental side effect).\n                var isNoLongerABoundary = prevExports !== null;\n                if (isNoLongerABoundary) {\n                    module.hot.invalidate();\n                }\n            }\n        }\n    })();\n//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiKGFwcC1jbGllbnQpLy4vc3JjL2NvbXBvbmVudHMveW91ckNhcmRzL1lvdXJDYXJkcy50c3giLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7QUFDaUM7QUFDVztBQVU1QyxNQUFNRSxZQUFpQztRQUFDLEVBQUVDLFFBQVEsRUFBRUMsVUFBVSxFQUFFQyxrQkFBa0IsRUFBRUMsV0FBVyxFQUFFQyxjQUFjLEVBQUU7O0lBQy9HLE1BQU0sQ0FBQ0MsU0FBU0MsV0FBVyxHQUFHVCwrQ0FBUUEsQ0FBQ0s7SUFFdkMsTUFBTUssYUFBYSxDQUFDQztRQUNsQixJQUFHSCxVQUFVRyxVQUFVTCxhQUFhO1lBQ2xDRyxXQUFXRyxDQUFBQSxjQUFlQSxjQUFjRDtRQUMxQyxPQUFPO1lBQ0xFLE1BQU07UUFDUjtJQUNGO0lBRUEscUJBQ0U7OzBCQUNBLDhEQUFDQztnQkFBR0MsV0FBV2QsdUVBQWU7MEJBQUdFOzs7Ozs7MEJBQ2pDLDhEQUFDYTtnQkFBSUQsV0FBV2QsbUVBQVc7O2tDQUN6Qiw4REFBQ2U7d0JBQUlELFdBQVdkLHlFQUFpQjs7Ozs7O2tDQUUvQiw4REFBQ2E7d0JBQUdDLFdBQVdkLHlFQUFpQjs7NEJBQUU7NEJBQWdCRzs7Ozs7OztrQ0FDbEQsOERBQUNVO3dCQUFHQyxXQUFXZCw2RUFBcUI7OzRCQUFFOzRCQUFNTTs7Ozs7Ozs7Ozs7OzswQkFHOUMsOERBQUNTO2dCQUFJRCxXQUFXZCx1RUFBZTs7Ozs7OzBCQUU3Qiw4REFBQ2U7Z0JBQUlELFdBQVdkLHdFQUFnQjswQkFDaEMsNEVBQUNhO29CQUFHQyxXQUFXZCwwRUFBa0I7O3dCQUFFO3dCQUFnQkssWUFBWWUsT0FBTyxDQUFDOzs7Ozs7Ozs7Ozs7MEJBRXZFLDhEQUFDTDtnQkFBSUQsV0FBV2QsMEVBQWtCOzBCQUNsQyw0RUFBQ2E7b0JBQUdDLFdBQVdkLHNFQUFjOzt3QkFBRTt3QkFBV08sUUFBUWEsT0FBTyxDQUFDOzs7Ozs7Ozs7Ozs7MEJBRTVELDhEQUFDRTtnQkFBT1IsV0FBV2QsMEVBQWtCO2dCQUFFd0IsU0FBUyxJQUFNZixXQUFXOzBCQUFLOzs7Ozs7OztBQUc1RTtHQWhDTVI7S0FBQUE7QUFrQ04sK0RBQWVBLFNBQVNBLEVBQUMiLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly9fTl9FLy4vc3JjL2NvbXBvbmVudHMveW91ckNhcmRzL1lvdXJDYXJkcy50c3g/NDZlMiJdLCJzb3VyY2VzQ29udGVudCI6WyIndXNlIGNsaWVudCdcclxuaW1wb3J0IHsgdXNlU3RhdGUgfSBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBzdHlsZXMgZnJvbSAnLi9Zb3VyQ2FyZHMubW9kdWxlLmNzcyc7XHJcblxyXG5pbnRlcmZhY2UgQ2FyZFByb3BzIHtcclxuICBjYXJkVHlwZTogc3RyaW5nO1xyXG4gIGNhcmROdW1iZXI6IHN0cmluZztcclxuICBpbml0aWFsQ2FyZEJhbGFuY2U6IG51bWJlcjtcclxuICBjcmVkaXRMaW1pdDogbnVtYmVyO1xyXG4gIGV4cGlyYXRpb25EYXRlOiBzdHJpbmc7XHJcbn1cclxuXHJcbmNvbnN0IFlvdXJDYXJkczogUmVhY3QuRkM8Q2FyZFByb3BzPiA9ICh7IGNhcmRUeXBlLCBjYXJkTnVtYmVyLCBpbml0aWFsQ2FyZEJhbGFuY2UsIGNyZWRpdExpbWl0LCBleHBpcmF0aW9uRGF0ZSB9KSA9PiB7XHJcbiAgY29uc3QgW2JhbGFuY2UsIHNldEJhbGFuY2VdID0gdXNlU3RhdGUoaW5pdGlhbENhcmRCYWxhbmNlKTtcclxuXHJcbiAgY29uc3Qgc3BlbmRNb25leSA9IChhbW91bnQ6IG51bWJlcikgPT4ge1xyXG4gICAgaWYoYmFsYW5jZSArIGFtb3VudCA8PSBjcmVkaXRMaW1pdCkge1xyXG4gICAgICBzZXRCYWxhbmNlKHByZXZCYWxhbmNlID0+IHByZXZCYWxhbmNlICsgYW1vdW50KTtcclxuICAgIH0gZWxzZSB7XHJcbiAgICAgIGFsZXJ0KFwiQ3JlZGl0IGxpbWl0IGV4Y2VlZGVkXCIpO1xyXG4gICAgfVxyXG4gIH1cclxuXHJcbiAgcmV0dXJuIChcclxuICAgIDw+XHJcbiAgICA8aDIgY2xhc3NOYW1lPXtzdHlsZXMuY2FyZFR5cGV9PntjYXJkVHlwZX08L2gyPlxyXG4gICAgPGRpdiBjbGFzc05hbWU9e3N0eWxlcy5jYXJkfT5cclxuICAgICAgPGRpdiBjbGFzc05hbWU9e3N0eWxlcy5jYXJkSGVhZGVyfT5cclxuICAgICAgPC9kaXY+XHJcbiAgICAgICAgPGgyIGNsYXNzTmFtZT17c3R5bGVzLmNhcmROdW1iZXJ9PioqKiogKioqKiAqKioqIHtjYXJkTnVtYmVyfTwvaDI+XHJcbiAgICAgICAgPGgyIGNsYXNzTmFtZT17c3R5bGVzLmV4cGlyYXRpb25EYXRlfT5FeHA6IHtleHBpcmF0aW9uRGF0ZX08L2gyPlxyXG5cclxuICAgIDwvZGl2PlxyXG4gICAgICA8ZGl2IGNsYXNzTmFtZT17c3R5bGVzLmNhcmRJbmZvfT5cclxuICAgICAgICA8L2Rpdj5cclxuICAgICAgICA8ZGl2IGNsYXNzTmFtZT17c3R5bGVzLmNhcmRMaW1pdH0+XHJcbiAgICAgICAgPGgyIGNsYXNzTmFtZT17c3R5bGVzLmNyZWRpdExpbWl0fT5DcmVkaXQgTGltaXQ6ICR7Y3JlZGl0TGltaXQudG9GaXhlZCgyKX08L2gyPlxyXG4gICAgICAgIDwvZGl2PlxyXG4gICAgICAgIDxkaXYgY2xhc3NOYW1lPXtzdHlsZXMuY2FyZEJhbGFuY2V9PlxyXG4gICAgICAgIDxoMiBjbGFzc05hbWU9e3N0eWxlcy5iYWxhbmNlfT5CYWxhbmNlOiAke2JhbGFuY2UudG9GaXhlZCgyKX08L2gyPlxyXG4gICAgICA8L2Rpdj5cclxuICAgICAgPGJ1dHRvbiBjbGFzc05hbWU9e3N0eWxlcy5zcGVuZEJ1dHRvbn0gb25DbGljaz17KCkgPT4gc3BlbmRNb25leSg1MCl9PlNwZW5kICQ1MDwvYnV0dG9uPlxyXG4gICAgPC8+XHJcbiAgKVxyXG59XHJcblxyXG5leHBvcnQgZGVmYXVsdCBZb3VyQ2FyZHM7XHJcbiJdLCJuYW1lcyI6WyJ1c2VTdGF0ZSIsInN0eWxlcyIsIllvdXJDYXJkcyIsImNhcmRUeXBlIiwiY2FyZE51bWJlciIsImluaXRpYWxDYXJkQmFsYW5jZSIsImNyZWRpdExpbWl0IiwiZXhwaXJhdGlvbkRhdGUiLCJiYWxhbmNlIiwic2V0QmFsYW5jZSIsInNwZW5kTW9uZXkiLCJhbW91bnQiLCJwcmV2QmFsYW5jZSIsImFsZXJ0IiwiaDIiLCJjbGFzc05hbWUiLCJkaXYiLCJjYXJkIiwiY2FyZEhlYWRlciIsImNhcmRJbmZvIiwiY2FyZExpbWl0IiwidG9GaXhlZCIsImNhcmRCYWxhbmNlIiwiYnV0dG9uIiwic3BlbmRCdXR0b24iLCJvbkNsaWNrIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///(app-client)/./src/components/yourCards/YourCards.tsx\n"));

/***/ })

});