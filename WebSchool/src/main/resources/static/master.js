var mdcDialog;

$(document).ready(function() {
	window.mdc.autoInit();
	
	var dialogNode = $("aside[role='alertdialog']").get(0);
	if (dialogNode) {
		mdcDialog = new mdc.dialog.MDCDialog(dialogNode);
	}
	
	var dialogOpen = $("aside[dialog-open='true']").get(0);
	
	if (dialogOpen) {
		mdcDialog.show();
	}
	
	$("button[search-button='']").click(function () {
		if (mdcDialog) {
			mdcDialog.show();
		}
	});
});