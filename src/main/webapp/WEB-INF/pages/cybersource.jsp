<html>
<body>
	<h1>${message}</h1>

    <form  method="post" action="${requestaddress}">
        <input type="hidden" id="unsigned_field_names" name="unsigned_field_names" value="card_type,card_cvn,card_number,card_expiry_date"/>
        <input type="hidden" id="card_number" name="card_number" value="4111111111111111"/>
        <input type="hidden" id="card_cvn" name="card_cvn" value="123"/>
        <input type="hidden" id="card_type" name="card_type" value="001"/>
        <input type="hidden" id="card_expiry_date" name="card_expiry_date" value="03-2019"/>
    ${content}
        <input type="submit" name="submit" value="Buy Now">
    </form>
</body>
</html>