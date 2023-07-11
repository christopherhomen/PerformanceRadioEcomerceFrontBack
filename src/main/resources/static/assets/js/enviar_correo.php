<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nombre = $_POST["name"];
    $email = $_POST["email"];
    $asunto = $_POST["subject"];
    $mensaje = $_POST["message"];

    $destinatario = "chris.a.d.97@gmail.com";
    $cabeceras = "From: $nombre <$email>" . "\r\n" . "Reply-To: $email" . "\r\n" . "X-Mailer: PHP/" . phpversion();

    if (mail($destinatario, $asunto, $mensaje, $cabeceras)) {
        echo "success";
    } else {
        echo "error";
    }
}
?>
