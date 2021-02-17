var products = null;

$.get("products", function(data) {
	if (data !== '') {
		products = data;
	}
}).done(function() {

	var cardsContent = "";
	jQuery.each(products, function(i, value) {

		cardsContent += "<div class='col'>" +
			"<div class='card'>" +
			"<div class='card-body'>" +
			"<h5 class='card-title'>" + value.name + "</h5>" +
			"<p class='card-text'>" + value.description + "</p>" +
			"<h6 class='card-subtitle mb-2 text-muted'>" + value.price + " $</h6>" +
			"<a href='product?id=" + value.id + "' class='card-link productCardElement'>info / buy</a>" +
			"</div>" +
			"</div>" +
			"</div>"
	});

	$('#productCards').html(cardsContent);

}).done(function() {
	$.get("user-role", function(data) {
		if (data !== '') {
			userRole = data;
		}
	}).done(function() {
		if (userRole === 'ADMINISTRATOR') {
			$('a.productCardElement').hide();
		}
	});
});