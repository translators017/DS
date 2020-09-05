const ctx = $('meta[name="context_path"]').attr('content');

function validateForm(){
	var messageDiv = document.querySelector('#messageDiv');
	var username = document.querySelector('#username').value;
	var password = document.querySelector('#password').value;
	console.log();
	if(username == '' || password == ''){
		messageDiv.classList.remove('d-none');
		return false;
	}
}

$(document).ready(function(){
	  //the trigger on hover when cursor directed to this class
	    $(".core-menu li").hover(
	    function(){
	      //i used the parent ul to show submenu
	        $(this).children('ul').slideDown('fast');
	    }, 
	      //when the cursor away 
	    function () {
	        $('ul', this).slideUp('fast');
	    });
	  //this feature only show on 600px device width
	    $(".hamburger-menu").click(function(){
	      $(".burger-1, .burger-2, .burger-3").toggleClass("open");
	        $(".core-menu").slideToggle("fast");
	    });

	$(".toggle-password").hover(function() {
	
	  $(this).toggleClass("fa-eye fa-eye-slash");
	  var input = $($(this).attr("toggle"));
	  if (input.attr("type") == "password") {
	    input.attr("type", "text");
	  } else {
	    input.attr("type", "password");
	  }
	});
});