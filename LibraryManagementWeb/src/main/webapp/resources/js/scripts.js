$(document).ready(() => {
	$('#login_form').on('submit', e => {
		e.preventDefault();
		$('#username_error').html("");
		$('#password_error').html("");
		if (validateLoginForm()) {
			$('#login_loading').show();
			ajaxUserRequest();
		}
	});

	$("#book_form").on('submit', e => {
		e.preventDefault();
		$("#bid_error").hide();
		$("#bname_error").hide();

		const updateUrl = '?bid=';

		if (!(window.location.href.includes(updateUrl))) {
			if (validateBookForm()) {
				if (!ajaxFetchBookByID($('#bid').val())) {
					ajaxAddBookRequest();
				} else {
					$('#bid_error').html("Book Code Already Exists in Database!");
					$('#bid_error').show();
				}
			}
		} else {
			if (validateBookForm()) {
				ajaxUpdateBookRequest();
			}
		}
	})
});

let validateLoginForm = () => {
	// Check if username field is not empty
	if (!$('#username').val()) {
		$('#username_error').html("This field cannot be empty");
		return false;
	}

	// Check if username field is not empty
	if (!$('#password').val()) {
		$('#password_error').html("This field cannot be empty");
		return false;
	}

	return true;
}

let validateBookForm = () => {
	// Check if bid field is not empty
	if (!$('#bid').val()) {
		$('#bid_error').html("This field cannot be empty");
		$('#bid_error').show();
		return false;
	}
	// Check if bname field is not empty
	if (!$('#bname').val()) {
		$('#bname_error').html("This field cannot be empty");
		$('#bname_error').show();
		return false;
	}

	return true;
}

let ajaxFetchBookByID = (bid) => {
	let resp = false;
	$.ajax({
		type: "GET",
		url: window.location.origin + "/LibraryManagementWeb/books?bid=" + bid,
		data: { "bid": bid },
		async: false,
		success: (response) => {
			if (response) {
				resp = true;
			}
		},
		error: (error) => {
			return true;
		}
	});

	return resp;
}

let ajaxDeleteBookRequest = (bid) => {
	$.ajax({
		type: "DELETE",
		url: "books/book?bid=" + bid,
		data: { "bid": bid },
		success: () => {
			window.location.reload();
		},
		error: (error) => {
			console.log(error);
		}
	});
}

let ajaxUserRequest = () => {
	$.ajax({
		type: "POST",
		url: $("#login_form").attr('action'),
		data: $("#login_form").serialize(),
		success: (response) => {
			if (!response) {
				$(location).prop('href', './');
			} else {
				let data = $.parseJSON(response);
				if (data.username_error) {
					$('#username_error').html(data.username_error);
				}
				if (data.password_error) {
					$('#password_error').html(data.password_error);
				}
			}
		},
		error: (error) => {
			console.log(error);
		}
	}).done(() => {
		$('#login_loading').hide();
	});
}

let ajaxAddBookRequest = () => {
	$.ajax({
		type: "POST",
		url: $("#book_form").attr('action'),
		dataType: "json",
		contentType: "application/json",
		data: formDataToJSON(),
		success: () => {
			window.location.replace("/LibraryManagementWeb");
		},
		error: (error) => {
			console.log(error);
		}
	}).done(() => {
	});

}

let ajaxUpdateBookRequest = () => {
	$.ajax({
		type: "PUT",
		url: $("#book_form").attr('action'),
		dataType: "json",
		contentType: "application/json",
		data: formDataToJSON(),
		success: () => {
			window.location.replace("/LibraryManagementWeb");
		},
		error: (error) => {
			console.log(error);
		}
	}).done(() => {
	});
}

// Convert Form Data into JSON Format
let formDataToJSON = () => {
	return "{\"bid\" :  " + $("#bid").val() + " , \"bname\" : \"" + $("#bname").val() + "\", \"author\" : { \"aid\": " + $("#author").val() + ",\"aname\" : \"" + $.trim($("#author option:selected").text()) + "\"}, \"addedOn\" : \"" + $("#added_on").val() + "\"}"
}