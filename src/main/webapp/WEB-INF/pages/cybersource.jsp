<html>
<body>
	<h1>${message}</h1>

    <form  method="post" action="${requestaddress}">
        <%--<input type="text" name="access_key" value="${accessKey}"><br>
        <input type="text" name="amount" value="45.94"><br>
        <input type="text" name="bill_to_address_city" value="paris"><br>
        <input type="text" name="bill_to_address_country" value="FR"><br>
        <input type="text" name="bill_to_address_line1" value="address1"><br>
        <input type="text" name="bill_to_address_line2" value="address2"><br>
        <input type="text" name="bill_to_address_postal_co..." value="75008"><br>
        <input type="text" name="bill_to_email" value="testcard@test.com"><br>
        <input type="text" name="bill_to_forename" value="hhhhtest"><br>
        <input type="text" name="bill_to_surname" value="testh"><br>
        <input type="text" name="card_cvn" value="123"><br>
        <input type="text" name="card_expiry_date" value="03-2019"><br>
        <input type="text" name="card_number" value="4111111111111111"><br>
        <input type="text" name="card_type" value="001"><br>
        <input type="text" name="currency" value="EUR"><br>
        <input type="text" name="customer_cookies_accepted" value="true"><br>
        <input type="text" name="customer_ip_address" value="170.248.1.192"><br>
        <input type="text" name="device_fingerprint_id" value="s18933746866080"><br>
        <input type="text" name="item_0_quantity" value="1"><br>
        <input type="text" name="item_0_sku" value="0282974001004"><br>
        <input type="text" name="item_0_unit_price" value="39.99"><br>
        <input type="text" name="item_1_code" value="shipping_only"><br>
        <input type="text" name="item_1_name" value="shipping"><br>
        <input type="text" name="item_1_quantity" value="1"><br>
        <input type="text" name="item_1_sku" value="1"><br>
        <input type="text" name="item_1_unit_price" value="5.95"><br>
        <input type="text" name="line_item_count" value="2"><br>
        <input type="text" name="locale" value="en-us"><br>
        <input type="text" name="merchant_defined_data1" value="testcard@test.com"><br>
        <input type="text" name="merchant_defined_data2" value="0"><br>
        <input type="text" name="merchant_defined_data22" value="Card"><br>
        <input type="text" name="merchant_defined_data8" value="home-delivery"><br>
        <input type="text" name="override_custom_receipt_page" value="https://local.soriana:9002/electronics/en/checkout/multi/sop/response"><br>
        <input type="text" name="payment_method" value="card"><br>
        <input type="text" name="profile_id" value="${profileId}"><br>
        <input type="text" name="reference_number" value="18281005"><br>
        <input type="text" name="ship_to_address_city" value="paris"><br>
        <input type="text" name="ship_to_address_country" value="FR"><br>
        <input type="text" name="ship_to_address_line1" value="address1"><br>
        <input type="text" name="ship_to_address_line2" value="address2"><br>
        <input type="text" name="ship_to_address_postal_code" value="75008"><br>
        <input type="text" name="ship_to_forename" value="hhhhtest"><br>
        <input type="text" name="ship_to_surname" value="testh"><br>
        <input type="text" name="signature" value="${signature}"><br>
        <input type="text" name="signed_date_time" value="${date}"><br>
        <input type="text" name="signed_field_names" value="override_custom_receipt_page,bill_to_email,item_1_code,profile_id,transaction_type,locale,bill_to_address_line1,bill_to_address_line2,transaction_uuid,currency,amount,ship_to_forename,ship_to_address_postal_code,item_1_unit_price,item_1_sku,merchant_defined_data1,access_key,merchant_defined_data2,ship_to_surname,item_1_name,unsigned_field_names,ship_to_address_city,bill_to_address_city,merchant_defined_data8,merchant_defined_data22,signed_date_time,bill_to_surname,customer_ip_address,line_item_count,item_0_unit_price,bill_to_address_country,bill_to_address_postal_code,bill_to_forename,reference_number,item_0_quantity,ship_to_address_line1,customer_cookies_accepted,ship_to_address_line2,item_1_quantity,device_fingerprint_id,item_0_sku,payment_method,ship_to_address_country,signed_field_names"><br>
        <input type="text" name="transaction_type" value="authorization,create_payment_token"><br>
        <input type="text" name="transaction_uuid" value="${uuid}"><br>
        <input type="text" name="unsigned_field_names" value="card_type,card_cvn,card_number,card_expiry_date"><br>--%>
        ${content}
        <input type="submit" name="submit" value="Buy Now">
    </form>
</body>
</html>