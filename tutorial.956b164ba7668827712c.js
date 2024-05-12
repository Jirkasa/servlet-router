(()=>{"use strict";const t=function(){function t(e,n){this.rootElement=document.createElement("div"),this.rootElement.classList.add(t.CSS_CODE_CLASS),n.appendChild(this.rootElement),this.codeElement=e;var o=document.createElement("div");o.classList.add(t.CSS_LINE_NUMBERS_CONTAINER_CLASS),this.rootElement.appendChild(o);var a=document.createElement("div");a.classList.add(t.CSS_CODE_OUTER_CONTAINER_CLASS),this.rootElement.appendChild(a);var i=document.createElement("div");i.classList.add(t.CSS_CODE_CONTAINER_CLASS),a.appendChild(i),i.appendChild(e),this.fillLineNumbersContainer(o,e),this.addHighlightBoxes(a,e.dataset),this.hide()}return Object.defineProperty(t.prototype,"visible",{get:function(){return!this.rootElement.classList.contains(t.CSS_CODE_HIDDEN_CLASS)},enumerable:!1,configurable:!0}),t.prototype.show=function(){this.rootElement.classList.remove(t.CSS_CODE_HIDDEN_CLASS)},t.prototype.hide=function(){this.rootElement.classList.add(t.CSS_CODE_HIDDEN_CLASS)},t.prototype.cloneCodeElement=function(){var t=this.codeElement.cloneNode(!0);return delete t.dataset.codeHighlight,delete t.dataset.active,delete t.dataset.javaPackageOpened,t},t.prototype.addHighlightBoxes=function(e,n){var o=n.codeHighlight;if(o)for(var a=0,i=o.split(",");a<i.length;a++){var r=i[a].split("-"),s=void 0,l=void 0;if(r[0]&&(s=parseInt(r[0]),!Number.isNaN(s))){if(s<1&&(s=1),r[1]){if(l=parseInt(r[1]),Number.isNaN(l))continue}else l=s;var c=document.createElement("div");c.classList.add(t.CSS_HIGHLIGHT_BOX_CLASS),c.style.transform="translateY(".concat(t.CODE_LINE_HEIGHT*(s-1)).concat(t.CODE_LINE_HEIGHT_UNITS,")"),c.style.height="".concat(t.CODE_LINE_HEIGHT*(l-s+1)).concat(t.CODE_LINE_HEIGHT_UNITS),e.appendChild(c)}}},t.prototype.fillLineNumbersContainer=function(t,e){for(var n=e.offsetHeight/+getComputedStyle(e).getPropertyValue("line-height").replace("px",""),o=1;o<=n;o++){var a=document.createElement("div");a.innerText=o.toString(),t.appendChild(a)}},t.CSS_CODE_CLASS="code-box__code",t.CSS_CODE_HIDDEN_CLASS="code-box__code--hidden",t.CSS_CODE_CONTAINER_CLASS="code-box__code-container",t.CSS_CODE_OUTER_CONTAINER_CLASS="code-box__code-outer-container",t.CSS_LINE_NUMBERS_CONTAINER_CLASS="code-box__line-numbers",t.CSS_HIGHLIGHT_BOX_CLASS="code-box__code-highlight-box",t.CODE_LINE_HEIGHT=2,t.CODE_LINE_HEIGHT_UNITS="rem",t}(),e=function(){function t(){this.handlers=new Map,this.count=0}return t.prototype.subscribe=function(t){return this.handlers.set(this.count,t),this.count++},t.prototype.unsubscribe=function(t){this.handlers.delete(t)},t.prototype.fire=function(t){this.handlers.forEach((function(e){e(t)}))},t}(),n=function(){function n(t,o){void 0===o&&(o=!1);var a=this;this.codeBoxElement=t,this.noImplicitActiveCode=o,this.codeButtons=[],this.codes=[],this.onCodeButtonClickEventSource=new e,this.activeCodeButton=null,t.classList.add(n.CSS_CODE_BOX_CLASS),this.noCodeElement=document.createElement("div"),this.noCodeElement.innerHTML="<span>".concat(n.NO_CODE_MESSAGE_TEXT,"</span>"),this.noCodeElement.classList.add(n.CSS_NO_CODE_MESSAGE_CLASS),this.onCodeButtonClickEventSource.subscribe((function(t){return a.onCodeButtonClick(t)}))}return n.prototype.init=function(){var e=this;this.codeBoxElement.appendChild(this.noCodeElement),this.codeBoxElement.querySelectorAll("[data-code]").forEach((function(n){if(e.validateCodeElement(n)){var o=new t(n,e.codeBoxElement),a=n.dataset,i=e.createCodeButton(o,a);i.setOnClickEventSource(e.onCodeButtonClickEventSource),n.hasAttribute("data-active")&&(e.activeCodeButton=i),e.codeButtons.push(i),e.codes.push(o)}else n.remove()})),!this.activeCodeButton&&!this.noImplicitActiveCode&&this.codeButtons.length>0&&(this.activeCodeButton=this.codeButtons[0]),this.activeCodeButton&&(this.noCodeElement.classList.add(n.CSS_NO_CODE_MESSAGE_HIDDEN_CLASS),this.activeCodeButton.activate(),this.activeCodeButton.codeBoxCode.show())},n.prototype.onCodeButtonClick=function(t){this.activeCodeButton!==t&&(this.activeCodeButton?(this.activeCodeButton.deactivate(),this.activeCodeButton.codeBoxCode.hide()):this.noCodeElement.classList.add(n.CSS_NO_CODE_MESSAGE_HIDDEN_CLASS),t.activate(),t.codeBoxCode.show(),this.activeCodeButton=t,this.onDisplayedCodeChanged())},n.prototype.validateCodeElement=function(t){return!0},n.prototype.onDisplayedCodeChanged=function(){},n.CSS_CODE_BOX_CLASS="code-box",n.CSS_NO_CODE_MESSAGE_CLASS="code-box__no-code-message",n.CSS_NO_CODE_MESSAGE_HIDDEN_CLASS="code-box__no-code-message--hidden",n.NO_CODE_MESSAGE_TEXT="No file selected",n}(),o=function(){function t(){}var e;return t.create=function(e){return'\n        <svg>\n            <use xlink:href="'.concat(t.PATH_TO_ROOT,"static/icon-sprite.svg#").concat(e,'"></use>\n        </svg>\n        ')},t.PATH_TO_ROOT=(null===(e=document.getElementById("PATH_TO_ROOT"))||void 0===e?void 0:e.value)||"./",t}(),a=function(){function t(e,n,a,i,r){void 0===i&&(i=!1),void 0===r&&(r=""),this.buttonElement=document.createElement("button"),this.buttonElement.classList.add(t.CSS_ITEM_CLASS),this.buttonElement.innerHTML='\n        <div class="'.concat(t.CSS_ITEM_ARROW_ICON_CLASS,'">\n            ').concat(o.create(t.ITEM_ARROW_ICON_NAME),'\n        </div>\n        <div class="').concat(t.CSS_ITEM_ICON_CLASS," ").concat(r,'">\n            ').concat(o.create(e),"\n        </div>\n        "),this.nameElement=document.createElement("span"),this.nameElement.innerText=n,this.buttonElement.appendChild(this.nameElement),this.collapsibleElement=document.createElement("div"),this.collapsibleElement.classList.add(t.CSS_COLLAPSIBLE_CLASS),i&&this.setAsOpenedOnInit(),this.buttonElement.setAttribute("data-hc-control",a),this.collapsibleElement.setAttribute("data-hc-content",a)}return Object.defineProperty(t.prototype,"name",{get:function(){return this.nameElement.innerText},set:function(t){this.nameElement.innerText=t},enumerable:!1,configurable:!0}),t.prototype.setAsOpenedOnInit=function(){this.buttonElement.classList.add(t.CSS_COLLAPSIBLE_ACTIVE_CLASS),this.collapsibleElement.classList.add(t.CSS_COLLAPSIBLE_ACTIVE_CLASS)},t.prototype.appendToElement=function(t){t.appendChild(this.buttonElement),t.appendChild(this.collapsibleElement)},t.prototype.detachFromElement=function(){this.buttonElement.remove(),this.collapsibleElement.remove()},t.prototype.sortFileButtons=function(){for(var t=[],e=this.collapsibleElement.children,n=0;n<e.length;n++){var o=e[n];"BUTTON"!==o.tagName||o.nextElementSibling&&"BUTTON"!=o.nextElementSibling.tagName||t.push(o)}t.sort((function(t,e){return t.innerText.toLowerCase()<e.innerText.toLowerCase()?-1:t.innerText.toLowerCase()==e.innerText.toLowerCase()?0:1}));for(var a=0,i=t;a<i.length;a++){var r=i[a];this.collapsibleElement.appendChild(r)}},t.prototype.sortChildFolders=function(){for(var t=[],e=this.collapsibleElement.children,n=0;n<e.length;n++){var o=e[n];"BUTTON"===o.tagName&&o.nextElementSibling&&"DIV"==o.nextElementSibling.tagName&&t.push({buttonElement:o,collapsibleElement:o.nextElementSibling})}t.sort((function(t,e){return t.buttonElement.innerText.toLowerCase()<e.buttonElement.innerText.toLowerCase()?-1:t.buttonElement.innerText.toLowerCase()==e.buttonElement.innerText.toLowerCase()?0:1}));for(var a=0,i=t;a<i.length;a++){var r=i[a];this.collapsibleElement.appendChild(r.buttonElement),this.collapsibleElement.appendChild(r.collapsibleElement)}},t.CSS_ITEM_CLASS="code-box-project-panel__item",t.CSS_ITEM_ARROW_ICON_CLASS="code-box-project-panel__item-arrow-icon",t.CSS_ITEM_ICON_CLASS="code-box-project-panel__item-icon",t.CSS_COLLAPSIBLE_CLASS="code-box-project-panel__collapsible",t.CSS_COLLAPSIBLE_ACTIVE_CLASS="is-active",t.ITEM_ARROW_ICON_NAME="arrow-right",t}(),i=function(){function t(){}return t.createProjectRoot=function(e,n,o){return void 0===o&&(o=!0),new a(t.PROJECT_ITEM_ICON_NAME,e,"root_project_collapsible_".concat(n),o)},t.createFolder=function(e,n,o,i){return void 0===i&&(i=!1),new a(t.FOLDER_ITEM_ICON_NAME,e,"collapsible_id_".concat(n,"_").concat(o),i,t.CSS_ITEM_ICON_FOLDER_MODIFIER_CLASS)},t.createJavaPackage=function(e,n,o){return void 0===o&&(o=!1),new a(t.PACKAGE_ITEM_ICON_NAME,e,"collapsible_id_java_package_".concat(e,"_").concat(n),o,t.CSS_ITEM_ICON_PACKAGE_MODIFIER_CLASS)},t.createDefaultJavaPackage=function(e,n){return void 0===n&&(n=!1),new a(t.PACKAGE_ITEM_ICON_NAME,"default","collapsible_id_default_java_package_".concat(e),n,t.CSS_ITEM_ICON_DEFAULT_PACKAGE_MODIFIER_CLASS)},t.CSS_ITEM_ICON_FOLDER_MODIFIER_CLASS="code-box-project-panel__item-icon--folder",t.CSS_ITEM_ICON_PACKAGE_MODIFIER_CLASS="code-box-project-panel__item-icon--package",t.CSS_ITEM_ICON_DEFAULT_PACKAGE_MODIFIER_CLASS="code-box-project-panel__item-icon--default-package",t.PROJECT_ITEM_ICON_NAME="inventory",t.FOLDER_ITEM_ICON_NAME="opened-folder",t.PACKAGE_ITEM_ICON_NAME="package",t}(),r=function(){function t(t){this.onClickEventSource=null,this.codeBoxCode=t}return t.prototype.setOnClickEventSource=function(t){this.onClickEventSource=t},t.prototype.removeOnClickEventSource=function(){this.onClickEventSource=null},t}();var s,l=(s=function(t,e){return s=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,e){t.__proto__=e}||function(t,e){for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&(t[n]=e[n])},s(t,e)},function(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Class extends value "+String(e)+" is not a constructor or null");function n(){this.constructor=t}s(t,e),t.prototype=null===e?Object.create(e):(n.prototype=e.prototype,new n)});const c=function(t){function e(e,n,o){var a=t.call(this,o)||this;return a.buttonElement=document.createElement("button"),a.buttonElement.innerText=e,n.appendChild(a.buttonElement),a.buttonElement.addEventListener("click",(function(){return a.onButtonElementClick()})),a}return l(e,t),e.prototype.activate=function(){this.buttonElement.classList.add(this.ACTIVE_CSS_CLASS)},e.prototype.deactivate=function(){this.buttonElement.classList.remove(this.ACTIVE_CSS_CLASS)},e.prototype.onButtonElementClick=function(){this.onClickEventSource&&this.onClickEventSource.fire(this)},e}(r);var d=function(){var t=function(e,n){return t=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,e){t.__proto__=e}||function(t,e){for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&(t[n]=e[n])},t(e,n)};return function(e,n){if("function"!=typeof n&&null!==n)throw new TypeError("Class extends value "+String(n)+" is not a constructor or null");function o(){this.constructor=e}t(e,n),e.prototype=null===n?Object.create(n):(o.prototype=n.prototype,new o)}}();const u=function(t){function e(n,a,i){var r=t.call(this,n,a,i)||this;return r.ACTIVE_CSS_CLASS="code-box-project-panel__item--active",r.buttonElement.classList.add(e.CSS_BUTTON_CLASS),r.buttonElement.innerHTML='\n            <div class="'.concat(e.CSS_BUTTON_ICON_CLASSES,'">\n                ').concat(o.create(e.BUTTON_ICON_NAME),"\n            </div>\n            <span>").concat(r.buttonElement.innerText,"</span>\n        "),r}return d(e,t),e.CSS_BUTTON_CLASS="code-box-project-panel__item",e.CSS_BUTTON_ICON_CLASSES="code-box-project-panel__item-icon code-box-project-panel__item-icon--file",e.BUTTON_ICON_NAME="file",e}(c);var h=function(){var t=function(e,n){return t=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,e){t.__proto__=e}||function(t,e){for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&(t[n]=e[n])},t(e,n)};return function(e,n){if("function"!=typeof n&&null!==n)throw new TypeError("Class extends value "+String(n)+" is not a constructor or null");function o(){this.constructor=e}t(e,n),e.prototype=null===n?Object.create(n):(o.prototype=n.prototype,new o)}}();const p=function(t){function n(n,o,a){var i=t.call(this,a)||this;i.onCodeButtonClickEventSource=new e,i.codeButtons=[];for(var r=0,s=o;r<s.length;r++){var l=s[r],c=new u(n,l,a);c.setOnClickEventSource(i.onCodeButtonClickEventSource),i.codeButtons.push(c)}return i.onCodeButtonClickEventSource.subscribe((function(){return i.onCodeButtonClick()})),i}return h(n,t),n.prototype.activate=function(){for(var t=0,e=this.codeButtons;t<e.length;t++)e[t].activate()},n.prototype.deactivate=function(){for(var t=0,e=this.codeButtons;t<e.length;t++)e[t].deactivate()},n.prototype.onCodeButtonClick=function(){this.onClickEventSource&&this.onClickEventSource.fire(this)},n}(r),_=function(){function t(t){this.value=t,this.children=[]}return t.prototype.addChildren=function(t){this.children.push(t)},t.prototype.findChildrenByValue=function(t){for(var e=0,n=this.children;e<n.length;e++){var o=n[e];if(o.value===t)return o}return null},t}();var E=function(){var t=function(e,n){return t=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,e){t.__proto__=e}||function(t,e){for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&(t[n]=e[n])},t(e,n)};return function(e,n){if("function"!=typeof n&&null!==n)throw new TypeError("Class extends value "+String(n)+" is not a constructor or null");function o(){this.constructor=e}t(e,n),e.prototype=null===n?Object.create(n):(o.prototype=n.prototype,new o)}}(),S=function(t,e,n){if(n||2===arguments.length)for(var o,a=0,i=e.length;a<i;a++)!o&&a in e||(o||(o=Array.prototype.slice.call(e,0,a)),o[a]=e[a]);return t.concat(o||Array.prototype.slice.call(e))};const C=function(t){function e(n,o){void 0===o&&(o=null);var a=t.call(this,n,!0)||this;a.projectId=a.getProjectIdFromDataAttribute(),a.folders=new Map,a.javaPackages=new Map,a.defaultJavaPackage=null,a.javaPackagesFolderPath=null,a.autoGeneratedFolders=new Map,a.usedFilePaths=new Map,a.usedJavaPackagePaths=new Map,n.classList.add(e.CSS_CODE_BOX_MODIFIER_CLASS),a.panelElement=document.createElement("div"),a.panelElement.classList.add(e.CSS_PANEL_CLASS),n.appendChild(a.panelElement),a.panelContentElement=document.createElement("div"),a.panelContentElement.classList.add(e.CSS_PANEL_CONTENT_CLASS),a.panelElement.appendChild(a.panelContentElement),a.panelToggleButton=a.createPanelToggleButton(),a.panelToggleButton.addEventListener("click",(function(){return a.onPanelToggleButtonClick()}));var r=document.createElement("h3");r.classList.add(e.CSS_PANEL_HEADING_CLASS),r.innerText="Folder structure",a.panelContentElement.appendChild(r),o?(a.rootFolder=i.createProjectRoot(o.getProjectName(),a.projectId),a.rootFolder.appendToElement(a.panelContentElement),a.createFoldersStructureFromFoldersTree(o.getInitFolderStructure(),a.rootFolder),a.javaPackagesFolderPath=o.javaPackagesFolderPath):(a.rootFolder=i.createProjectRoot(n.dataset.projectName||"unnamed",a.projectId),a.rootFolder.appendToElement(a.panelContentElement),a.createFoldersStructure(a.rootFolder));var s=document.createElement("hr");s.classList.add(e.CSS_PANEL_HORIZONTAL_RULE_CLASS),a.panelContentElement.appendChild(s);var l=document.createElement("h3");if(l.classList.add(e.CSS_PANEL_HEADING_CLASS),l.innerText="Java packages",a.panelContentElement.appendChild(l),o)for(var c=0,d=o.codes;c<d.length;c++){var u=d[c].cloneCodeElement();n.appendChild(u)}return a.processCommands(),a.init(),a.sortJavaPackages(),a.sortFolders(),a.sortFileButtons(),0!==a.javaPackages.size||a.defaultJavaPackage||(s.remove(),l.remove()),a.processCommandsAfterInit(),a.removeConfigurationElements(),a}return E(e,t),e.prototype.getProjectName=function(){return this.rootFolder.name},e.prototype.getProjectId=function(){return this.projectId},e.prototype.onPanelToggleButtonClick=function(){this.panelToggleButton.classList.toggle(e.CSS_PANEL_TOGGLE_BUTTON_OPENED_MODIFIER_CLASS),this.panelElement.classList.toggle(e.CSS_PANEL_OPENED_MODIFIER_CLASS)},e.prototype.onDisplayedCodeChanged=function(){this.panelToggleButton.classList.remove(e.CSS_PANEL_TOGGLE_BUTTON_OPENED_MODIFIER_CLASS),this.panelElement.classList.remove(e.CSS_PANEL_OPENED_MODIFIER_CLASS)},e.prototype.validateCodeElement=function(t){var e,n,o,a=!0;if(null!==this.javaPackagesFolderPath&&void 0===t.dataset.folder&&void 0!==t.dataset.javaPackage){var i=null===(e=t.dataset.javaPackage)||void 0===e?void 0:e.trim();o=i?this.javaPackagesFolderPath+"/"+i.replace(/\./g,"/")+"/"+(t.dataset.code||"unnamed"):this.javaPackagesFolderPath+"/"+(t.dataset.code||"unnamed")}else{var r=void 0;(r=(r=(null===(n=t.dataset.folder)||void 0===n?void 0:n.split("/"))||[]).filter((function(t){return t.trim().length>0}))).push(t.dataset.code||"unnamed"),o=r.join("/")}if(this.usedFilePaths.has(o)?a=!1:this.usedFilePaths.set(o,!0),void 0!==t.dataset.javaPackage){var s=t.dataset.javaPackage.trim();s.length>0&&(s+="."),s+=t.dataset.code||"unnamed",this.usedJavaPackagePaths.has(s)?a=!1:this.usedJavaPackagePaths.set(s,!0)}return a},e.prototype.createCodeButton=function(t,e){var n,o,a,r=e.folder,s=null===(n=e.javaPackage)||void 0===n?void 0:n.trim(),l=e.code;if(void 0!==s&&(s.length>0?(o=this.javaPackages.get(s))||((o=i.createJavaPackage(s,this.projectId)).appendToElement(this.panelContentElement),this.javaPackages.set(s,o)):(o=this.defaultJavaPackage)||((o=i.createDefaultJavaPackage(this.projectId)).appendToElement(this.panelContentElement),this.defaultJavaPackage=o),void 0!==e.javaPackageOpened&&o.setAsOpenedOnInit(),void 0===r&&this.javaPackagesFolderPath)){var c=s.split(".");r=this.javaPackagesFolderPath;var d=this.folders.get(r);if(d)for(var h=0,_=c;h<_.length;h++){var E=_[h];if(0!==E.trim().length){r+="/".concat(E.trim());var S=this.folders.get(r);S||((S=i.createFolder(E.trim(),r,this.projectId)).appendToElement(d.collapsibleElement),this.folders.set(r,S),this.autoGeneratedFolders.set(r,!0)),d=S}}}return r&&(a=this.folders.get(r))||(a=this.rootFolder),o?new p(l||"unnamed",[a.collapsibleElement,o.collapsibleElement],t):new u(l||"unnamed",a.collapsibleElement,t)},e.prototype.processCommands=function(){var t=this,e=this.codeBoxElement.querySelector("[data-project-commands]");if(e)for(var n=function(n){var a=e.children[n];if(void 0!==a.dataset.commandRemoveFolder){var r=a.innerText.trim(),s=void 0;s=null!==o.javaPackagesFolderPath&&(r.startsWith(o.javaPackagesFolderPath)||o.javaPackagesFolderPath.startsWith(r))?'[data-folder^="'.concat(r,'"], [data-java-package]'):'[data-folder^="'.concat(r,'"]'),o.codeBoxElement.querySelectorAll(s).forEach((function(e){var n=e.dataset.folder;if(void 0===n){var o=e.dataset.javaPackage;if(void 0===o)return;n=t.javaPackagesFolderPath?t.javaPackagesFolderPath+"/"+o.replace(/\./g,"/"):""}n.length>r.length&&"/"!==n.charAt(r.length)||n.length<r.length||e.remove()}));var l=[];o.folders.forEach((function(t,e){if(e.startsWith(r)){if(e.length>r.length&&"/"!==e.charAt(r.length))return;l.push(e)}}));for(var c=0,d=l;c<d.length;c++){var u=d[c];null==(g=o.folders.get(u))||g.detachFromElement(),o.folders.delete(u)}return"continue"}if(void 0!==a.dataset.commandRemoveFile){var h=a.innerText.trim();return s=void 0,s=h.includes("/")?null!==o.javaPackagesFolderPath&&h.startsWith(o.javaPackagesFolderPath)?'[data-folder^="'.concat(h,'"], [data-java-package]'):'[data-folder^="'.concat(h,'"]'):'[data-code="'.concat(h,'"]'),o.codeBoxElement.querySelectorAll(s).forEach((function(e){var n;if(void 0===e.dataset.folder&&void 0!==e.dataset.javaPackage){var o=e.dataset.javaPackage;if(void 0===o)return;n=t.javaPackagesFolderPath?o.length>0?t.javaPackagesFolderPath+"/"+o.replace(/\./g,"/")+"/"+(e.dataset.code||"unnamed"):t.javaPackagesFolderPath+"/"+(e.dataset.code||"unnamed"):e.dataset.code||"unnamed"}else n=e.dataset.folder?e.dataset.folder+"/"+(e.dataset.code||"unnamed"):e.dataset.code||"unnamed";n===h&&e.remove()})),"continue"}if(void 0!==a.dataset.commandRenameFolder){var p,_=a.innerText.trim(),E=a.dataset.commandRenameFolder.trim();if(p=_.includes("/")?_.lastIndexOf("/")+1:0,0===E.length)return"continue";if(null!==o.javaPackagesFolderPath&&o.javaPackagesFolderPath.startsWith(_)){var S=o.javaPackagesFolderPath.indexOf("/",p+1);-1===S&&(S=o.javaPackagesFolderPath.length),o.javaPackagesFolderPath=o.javaPackagesFolderPath.substring(0,p)+E+o.javaPackagesFolderPath.substring(S,o.javaPackagesFolderPath.length)}var C=[];o.folders.forEach((function(t,e){if(e.startsWith(_)&&!(e.length>_.length&&"/"!==e.charAt(_.length))){var n=e.indexOf("/",p+1);-1===n&&(n=e.length),C.push({oldPath:e,newPath:e.substring(0,p)+E+e.substring(n,e.length)})}}));for(var m=0,v=C;m<v.length;m++){var f=v[m],g=o.folders.get(f.oldPath);o.folders.delete(f.oldPath),g&&(f.oldPath===_&&(g.name=E),o.folders.set(f.newPath,g))}return document.querySelectorAll('[data-folder^="'.concat(_,'"]')).forEach((function(t){var e=t.dataset.folder;if(void 0!==e&&e.startsWith(_)&&!(e.length>_.length&&"/"!==e.charAt(_.length))){var n=e.indexOf("/",p+1);-1===n&&(n=e.length),t.dataset.folder=e.substring(0,p)+E+e.substring(n,e.length)}})),"continue"}if(void 0!==a.dataset.commandRenameFile){var A=a.innerText.trim(),O=a.dataset.commandRenameFile.trim();return 0===O.length||o.getCodeElementsByPath(A).forEach((function(t){t.dataset.code=O})),"continue"}if(void 0!==a.dataset.commandOpenFolder){var T=a.innerText.trim();return(f=o.folders.get(T))?(f.setAsOpenedOnInit(),"continue"):"continue"}if(void 0!==a.dataset.commandOpenFolderToRoot){for(T=a.innerText.trim();T.length>0;){(f=o.folders.get(T))&&f.setAsOpenedOnInit();var P=T.lastIndexOf("/");T=-1===P?"":T.substring(0,P)}return"continue"}if(void 0!==a.dataset.commandCodeHighlight){A=a.innerText.trim();var L=a.dataset.commandCodeHighlight;return o.getCodeElementsByPath(A).forEach((function(t){t.dataset.codeHighlight=L})),"continue"}if(void 0!==a.dataset.commandSetActiveFile)return A=a.innerText.trim(),o.getCodeElementsByPath(A).forEach((function(t){t.dataset.active=""})),"continue";if(void 0!==a.dataset.commandCreateFolder)for(var b=(u=a.innerText.trim()).split("/"),I=[],y=o.rootFolder,N=0,j=b;N<j.length;N++){var B=j[N];I.push(B),T=I.join("/"),(f=o.folders.get(T))||((f=i.createFolder(B,T,o.projectId)).appendToElement(y.collapsibleElement),o.folders.set(T,f)),y=f}void 0!==a.dataset.commandSetJavaPackagesFolder&&(o.javaPackagesFolderPath=a.innerText.trim())},o=this,a=0;a<e.children.length;a++)n(a)},e.prototype.processCommandsAfterInit=function(){var t=this,e=this.codeBoxElement.querySelector("[data-project-commands]");e&&e.querySelectorAll("[data-command-open-java-package]").forEach((function(e){var n=e.innerText.trim();if(0===n.length)t.defaultJavaPackage&&t.defaultJavaPackage.setAsOpenedOnInit();else{var o=t.javaPackages.get(n);o&&o.setAsOpenedOnInit()}}))},e.prototype.removeConfigurationElements=function(){var t=this.codeBoxElement.querySelector("[data-project-commands]"),e=this.codeBoxElement.querySelector("[data-project-folders]");t&&t.remove(),e&&e.remove()},e.prototype.getCodeElementsByPath=function(t){var e,n=t.split("/"),o=n.pop(),a=n.join("/");if(e=a?'[data-folder="'.concat(a,'"][data-code="').concat(o,'"]'):':not([data-folder])[data-code="'.concat(o,'"],[data-folder=""][data-code="').concat(o,'"]'),null===this.javaPackagesFolderPath||!a.startsWith(this.javaPackagesFolderPath)||a.length>this.javaPackagesFolderPath.length&&"/"!==a.charAt(this.javaPackagesFolderPath.length))a||null===this.javaPackagesFolderPath||""===this.javaPackagesFolderPath||(e=":not([data-java-package])"+e+":not([data-java-package])");else{for(var i=[],r=this.javaPackagesFolderPath.split("/").length;r<n.length;r++)i.push(n[r]);i.length>0?e+=',[data-java-package="'.concat(i.join("."),'"][data-code="').concat(o,'"]'):e+=',[data-java-package=""][data-code="'.concat(o,'"]')}return document.querySelectorAll(e)},e.prototype.getInitFolderStructure=function(){var t=this,e=new _("root");return this.folders.forEach((function(n,o){if(!t.autoGeneratedFolders.has(o))for(var a=o.split("/"),i=e,r=0,s=a;r<s.length;r++){var l=s[r],c=i.findChildrenByValue(l);c||(c=new _(l),i.addChildren(c)),i=c}})),e.children},e.prototype.getProjectIdFromDataAttribute=function(){var t=this.codeBoxElement.dataset.project;if(!t)throw new Error("No project ID assigned.");if(e.occupiedProjectIds.has(t))throw new Error("Project with this ID already exists.");return e.occupiedProjectIds.set(t,!0),t},e.prototype.sortFolders=function(){this.rootFolder.sortChildFolders(),this.folders.forEach((function(t){return t.sortChildFolders()}))},e.prototype.sortFileButtons=function(){var t;this.rootFolder.sortFileButtons(),this.folders.forEach((function(t){return t.sortFileButtons()})),null===(t=this.defaultJavaPackage)||void 0===t||t.sortFileButtons(),this.javaPackages.forEach((function(t){return t.sortFileButtons()}))},e.prototype.sortJavaPackages=function(){var t=[];this.javaPackages.forEach((function(e){t.push(e)})),t.sort((function(t,e){return t.name<e.name?-1:t.name==e.name?0:1}));for(var e=0,n=t;e<n.length;e++){var o=n[e];this.panelContentElement.appendChild(o.buttonElement),this.panelContentElement.appendChild(o.collapsibleElement)}},e.prototype.createPanelToggleButton=function(){var t=document.createElement("button");return t.classList.add(e.CSS_PANEL_TOGGLE_BUTTON_CLASS),t.innerHTML="\n        ".concat(e.PANEL_TOGGLE_BUTTON_TEXT,"\n        ").concat(o.create(e.PANEL_TOGGLE_BUTTON_ICON_NAME),"\n        "),this.panelElement.appendChild(t),t},e.prototype.createFoldersStructure=function(t){var e=this.codeBoxElement.querySelector("[data-project-folders]");e&&this.createFoldersStructureTraverse(e,t)},e.prototype.createFoldersStructureTraverse=function(t,e,n){void 0===n&&(n=[]);for(var o=function(o){var r,s,l=t.children[o];if("LI"!==l.tagName)return"continue";if(l.childNodes.forEach((function(t){var e;if(t instanceof Text){var n=null===(e=t.textContent)||void 0===e?void 0:e.trim();n&&n.length>0&&(r=n)}else t instanceof Element&&"UL"===t.tagName&&(s=t)})),r){var c=S(S([],n,!0),[r],!1).join("/"),d=l instanceof HTMLElement&&l.hasAttribute("data-opened"),u=i.createFolder(r,c,a.projectId,d);u.appendToElement(e.collapsibleElement),a.folders.set(c,u),l.hasAttribute("data-java-packages-folder")&&(a.javaPackagesFolderPath=c),s&&a.createFoldersStructureTraverse(s,u,S(S([],n,!0),[r],!1))}},a=this,r=0;r<t.children.length;r++)o(r)},e.prototype.createFoldersStructureFromFoldersTree=function(t,e,n){void 0===n&&(n=[]);for(var o=0,a=t;o<a.length;o++){var r=a[o],s=r.value,l=S(S([],n,!0),[s],!1).join("/"),c=i.createFolder(r.value,l,this.projectId);c.appendToElement(e.collapsibleElement),this.folders.set(l,c),r.children.length>0&&this.createFoldersStructureFromFoldersTree(r.children,c,S(S([],n,!0),[s],!1))}},e.CSS_CODE_BOX_MODIFIER_CLASS="code-box--project",e.CSS_PANEL_CLASS="code-box-project-panel",e.CSS_PANEL_OPENED_MODIFIER_CLASS="code-box-project-panel--opened",e.CSS_PANEL_CONTENT_CLASS="code-box-project-panel__content",e.CSS_PANEL_TOGGLE_BUTTON_CLASS="code-box-project-panel__toggle-button",e.CSS_PANEL_TOGGLE_BUTTON_OPENED_MODIFIER_CLASS="code-box-project-panel__toggle-button--opened",e.CSS_PANEL_HEADING_CLASS="code-box-project-panel__heading",e.CSS_PANEL_HORIZONTAL_RULE_CLASS="code-box-project-panel__horizontal-rule",e.PANEL_TOGGLE_BUTTON_ICON_NAME="double-arrow-right",e.PANEL_TOGGLE_BUTTON_TEXT="Open/close side panel",e.occupiedProjectIds=new Map,e}(n);var m=function(){var t=function(e,n){return t=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,e){t.__proto__=e}||function(t,e){for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&(t[n]=e[n])},t(e,n)};return function(e,n){if("function"!=typeof n&&null!==n)throw new TypeError("Class extends value "+String(n)+" is not a constructor or null");function o(){this.constructor=e}t(e,n),e.prototype=null===n?Object.create(n):(o.prototype=n.prototype,new o)}}();const v=function(t){function e(n,a,i){var r=t.call(this,n,a,i)||this;return r.ACTIVE_CSS_CLASS="code-box__file-button--active",r.buttonElement.classList.add(e.CSS_BUTTON_CLASS),r.buttonElement.innerHTML="\n            ".concat(o.create(e.BUTTON_ICON_NAME),"\n            <span>").concat(r.buttonElement.innerText,"</span>\n        "),r}return m(e,t),e.CSS_BUTTON_CLASS="code-box__file-button",e.BUTTON_ICON_NAME="file",e}(c);var f=function(){var t=function(e,n){return t=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,e){t.__proto__=e}||function(t,e){for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&(t[n]=e[n])},t(e,n)};return function(e,n){if("function"!=typeof n&&null!==n)throw new TypeError("Class extends value "+String(n)+" is not a constructor or null");function o(){this.constructor=e}t(e,n),e.prototype=null===n?Object.create(n):(o.prototype=n.prototype,new o)}}();const g=function(t){function e(n){var o=t.call(this,n,n.hasAttribute("data-no-implicit-active"))||this;return n.classList.add(e.CSS_CODE_BOX_MODIFIER_CLASS),o.buttonsContainer=document.createElement("div"),o.buttonsContainer.classList.add(e.CSS_BUTTONS_CONTAINER_CLASS),n.appendChild(o.buttonsContainer),n.hasAttribute("data-no-buttons")&&(o.buttonsContainer.classList.add(e.CSS_BUTTONS_CONTAINER_HIDDEN_CLASS),n.classList.remove(e.CSS_CODE_BOX_MODIFIER_CLASS)),o.init(),o}return f(e,t),e.prototype.createCodeButton=function(t,e){return new v(e.code||"unnamed",this.buttonsContainer,t)},e.CSS_CODE_BOX_MODIFIER_CLASS="code-box--simple",e.CSS_BUTTONS_CONTAINER_CLASS="code-box__files",e.CSS_BUTTONS_CONTAINER_HIDDEN_CLASS="code-box__files--hidden",e}(n);var A=Object.defineProperty,O=(t,e,n)=>(((t,e,n)=>{e in t?A(t,e,{enumerable:!0,configurable:!0,writable:!0,value:n}):t[e]=n})(t,"symbol"!=typeof e?e+"":e,n),n);class T{constructor(t={}){O(this,"toggleContentEls"),O(this,"toggleButtonEls"),O(this,"itemsState",{}),O(this,"options");const e="object"==typeof t&&"nameSpace"in t?t.nameSpace:"hc",n={nameSpace:"hc",toggleButtonAttr:`data-${e}-control`,toggleContentAttr:`data-${e}-content`,activeClass:"is-active",isAnimation:!0,closeOthers:!0,animationSpeed:400,cssEasing:"ease-in-out",onSlideStart:()=>{},onSlideEnd:()=>{}};this.options={...n,...t},this.toggleContentEls=[].slice.call(document.querySelectorAll(`[${this.options.toggleContentAttr}]`)),this.toggleButtonEls=[].slice.call(document.querySelectorAll(`[${this.options.toggleButtonAttr}]`)),0!==this.toggleContentEls.length&&this.initContentsState(this.toggleContentEls),0!==this.toggleButtonEls.length&&this.handleButtonsEvent(this.toggleButtonEls)}initContentsState(t){this.itemsState={},t.forEach((t=>{t.style.overflow="hidden",t.style.maxHeight="none";const e=t.classList.contains(this.options.activeClass),n=t.getAttribute(this.options.toggleContentAttr);!n||(this.setItemState(n,e),e?this.open(n,!1,!1):this.close(n,!1,!1))}))}handleButtonsEvent(t){t.forEach((t=>{const e=t.getAttribute(this.options.toggleButtonAttr);e&&t.addEventListener("click",(t=>{t.preventDefault(),this.toggleSlide(e,!0)}),!1)}))}setItemState(t,e){this.itemsState[t]={isOpen:e,isAnimating:!1}}toggleSlide(t,e=!0){var n,o;null!=(n=this.itemsState[t])&&n.isAnimating||(!1===(null==(o=this.itemsState[t])?void 0:o.isOpen)?this.open(t,e,this.options.isAnimation):this.close(t,e,this.options.isAnimation))}open(t,e=!0,n=!0){if(!t)return;Object.prototype.hasOwnProperty.call(this.itemsState,t)||this.setItemState(t,!1);const o=document.querySelector(`[${this.options.toggleContentAttr}='${t}']`);if(!o)return;this.itemsState[t].isAnimating=!0,this.options.closeOthers&&[].slice.call(this.toggleContentEls).forEach((e=>{const o=e.getAttribute(this.options.toggleContentAttr);o&&o!==t&&this.close(o,!1,n)})),!1!==e&&this.options.onSlideStart(!0,t);const a=this.getTargetHeight(o);o.style.visibility="visible",o.classList.add(this.options.activeClass);const i=document.querySelectorAll(`[${this.options.toggleButtonAttr}='${t}']`);i.length>0&&[].slice.call(i).forEach((t=>{t.classList.add(this.options.activeClass),t.hasAttribute("aria-expanded")&&t.setAttribute("aria-expanded","true")})),n?(o.style.overflow="hidden",o.style.transition=`${this.options.animationSpeed}ms ${this.options.cssEasing}`,o.style.maxHeight=(a||"1000")+"px",setTimeout((()=>{!1!==e&&this.options.onSlideEnd(!0,t),o.style.maxHeight="none",o.style.transition="",o.style.overflow="",this.itemsState[t].isAnimating=!1}),this.options.animationSpeed)):(o.style.maxHeight="none",o.style.overflow="",this.itemsState[t].isAnimating=!1),this.itemsState[t].isOpen=!0,o.hasAttribute("aria-hidden")&&o.setAttribute("aria-hidden","false")}close(t,e=!0,n=!0){if(!t)return;Object.prototype.hasOwnProperty.call(this.itemsState,t)||this.setItemState(t,!1),this.itemsState[t].isAnimating=!0,!1!==e&&this.options.onSlideStart(!1,t);const o=document.querySelector(`[${this.options.toggleContentAttr}='${t}']`);o.style.overflow="hidden",o.classList.remove(this.options.activeClass),o.style.maxHeight=o.clientHeight+"px",setTimeout((()=>{o.style.maxHeight="0px"}),5);const a=document.querySelectorAll(`[${this.options.toggleButtonAttr}='${t}']`);a.length>0&&[].slice.call(a).forEach((t=>{t.classList.remove(this.options.activeClass),t.hasAttribute("aria-expanded")&&t.setAttribute("aria-expanded","false")})),n?(o.style.transition=`${this.options.animationSpeed}ms ${this.options.cssEasing}`,setTimeout((()=>{!1!==e&&this.options.onSlideEnd(!1,t),o.style.transition="",this.itemsState[t].isAnimating=!1,o.style.visibility="hidden"}),this.options.animationSpeed)):(this.options.onSlideEnd(!1,t),this.itemsState[t].isAnimating=!1,o.style.visibility="hidden"),Object.prototype.hasOwnProperty.call(this.itemsState,t)&&(this.itemsState[t].isOpen=!1),o.hasAttribute("aria-hidden")&&o.setAttribute("aria-hidden","true")}getTargetHeight(t){if(!t)return;const e=t.cloneNode(!0),n=t.parentNode;if(!n)return;const o=[].slice.call(e.querySelectorAll("input[name]"));if(0!==o.length){const t="-"+(new Date).getTime();o.forEach((e=>{e.name+=t}))}e.style.maxHeight="none",e.style.opacity="0",n.appendChild(e);const a=e.clientHeight;return n.removeChild(e),a}}var P=!1;!function(){if(P)throw new Error("Code boxes are already initialized.");var t=document.querySelectorAll("[data-code-box], [data-project]"),e=new Map,n=[];t.forEach((function(t){if(t.hasAttribute("data-project")){var o=void 0;void 0!==t.dataset.projectExtends?e.has(t.dataset.projectExtends)&&(o=new C(t,e.get(t.dataset.projectExtends))):o=new C(t),o?e.set(o.getProjectId(),o):n.push(t)}else new g(t)}));for(var o=0;n.length>0;){for(var a=0;a<n.length;a++){var i=n[a];if(void 0===i.dataset.projectExtends)throw new Error("Something went wrong when initializing code boxes.");if(e.has(i.dataset.projectExtends)){var r=new C(i,e.get(i.dataset.projectExtends));e.set(r.getProjectId(),r),n.splice(a,1),a--,o++}}if(0===o)break;o=0}new T({closeOthers:!1,animationSpeed:140,cssEasing:"linear"}),P=!0}()})();