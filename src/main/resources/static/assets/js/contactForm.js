  $(document).ready(function() {
    $("#myForm").submit(function(event) {
      event.preventDefault();
      var form = $(this);
      var url = form.attr("action");

      $("#loading").show();

      $.ajax({
        type: "POST",
        url: url,
        data: form.serialize(),
        dataType: "json",
        success: function(response) {
          form[0].reset();
          $("#loading").hide();
          $("#popup-success").fadeIn().delay(3000).fadeOut();
        },
        error: function(error) {
          $("#loading").hide();
          $("#popup-error").fadeIn().delay(3000).fadeOut();
        }
      });
    });
  });