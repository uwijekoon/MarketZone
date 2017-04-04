/* AJAX send function */
function sendAjax(method, url, data, success, error){
	$.ajax({
		  method: method,
		  url: url,
		  data: data,
		  success: success,
		  error: error
		});
}

function removeCartItem(index){
	var data = { itemIndex: index};
	sendAjax("POST", "RemoveCartItem", data, removeCartItemSuccess, removeCartItemFailed);
}

function removeCartItemSuccess(data){
    $("#cart_items").html(data);
}

function removeCartItemFailed(){
    window.location = '/MarketZone/';
}

function updateCart(){
    var quantityList = "";
    $("input[id^='item_quantity-']").each(function(index){
        var idSplit = this.id.split("-");
        if(index !== 0){
            quantityList += ",";
        }
        quantityList += idSplit[1]+ "-" + $(this).val();
    });
    var data = {quantityList:quantityList};
    sendAjax("POST", "updateCart", data, updateCartSuccess, updateCartFailed);
}

function updateCartSuccess(data){
    $("#cart_items").html(data);
}

function updateCartFailed(){
    window.location = '/MarketZone/';
}