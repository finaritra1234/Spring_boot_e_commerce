$(document).ready(function() {
	$(".minusButton").on("click", function(evt) {
		evt.preventDefault();

		
		qtyInput = $("#qte_com").val();
		newQty = parseInt(qtyInput) - 1;
	
		if (newQty > 0) $("#qte_com").val(newQty);
	});
	$(".plusButton").on("click", function(evt) {
		evt.preventDefault();

		
		qtyInput = $("#qte_com").val();
		newQty = parseInt(qtyInput) + 1;
	
		if (newQty > 0) $("#qte_com").val(newQty);
	});
});