var mdcDialog;
var mdcSnackbar;

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
	
	var mdcSnackbar = new mdc.snackbar.MDCSnackbar(document.querySelector('.mdc-snackbar'));
	
	var snackShow = $("div[snack-show='true']").get(0);
	
	if (snackShow) {
		mdcSnackbar.show(
				{
					message: snackShow.getAttribute( "snack-text"),
					timeout: 7000
				}
		);
	}
});

