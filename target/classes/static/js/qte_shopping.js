$(document).ready(function() {
	$(".minusButton").on("click", function(evt) {
		evt.preventDefault();
		moins();
	});
	$(".plusButton").on("click", function(evt) {
		evt.preventDefault();
		plus();

	});
});

function moins() {


	qtyInput = $("#qte_com").val();
	newQty = parseInt(qtyInput) - 1;

	if (newQty > 0) { 
		$("#qte_com").val(newQty); 
		
	}
}

function plus() {

	qtyInput = $("#qte_com").val();
	newQty = parseInt(qtyInput) + 1;

	if (newQty > 0) $("#qte_com").val(newQty);
}

