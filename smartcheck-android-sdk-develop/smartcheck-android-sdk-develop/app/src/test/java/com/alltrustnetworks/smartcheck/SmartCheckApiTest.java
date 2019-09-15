package com.alltrustnetworks.smartcheck;


import com.alltrustnetworks.smartcheck.models.CustomerIdentification;
import com.alltrustnetworks.smartcheck.models.IdVerificationResponse;
import com.alltrustnetworks.smartcheck.models.UpdateEmailProfileRequest;
import com.alltrustnetworks.smartcheck.models.consumer.EnrollmentResponse;
import com.alltrustnetworks.smartcheck.models.consumer.StatusResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SmartCheckApiTest {

    String questionJson = "" +
            "{\"status\": \"passed\"," +
            "  \"challenge_required\": true," +
            "  \"challenge\": {" +
            "    \"questions\": [" +
            "      {" +
            "        \"prompt\": \"What is 2 + 2?\"," +
            "        \"answers\": [" +
            "          \"2\"," +
            "          \"3\"," +
            "          \"4\"," +
            "          \"5\"," +
            "          \"None of the above\"" +
            "        ]" +
            "      }," +
            "      {" +
            "        \"prompt\": \"Who is buried in Grant's tomb?\"," +
            "        \"answers\": [" +
            "          \"No one\"," +
            "          \"Washington\"," +
            "          \"Lincoln\"," +
            "          \"Grant\"," +
            "          \"None of the above\"" +
            "        ]" +
            "      }," +
            "      {" +
            "        \"prompt\": \"What color is the White House?\"," +
            "        \"answers\": [" +
            "          \"Blue\"," +
            "          \"Orange\"," +
            "          \"Red\"," +
            "          \"Purple\"," +
            "          \"None of the above\"" +
            "        ]" +
            "      }," +
            "      {" +
            "        \"prompt\": \"What temperature in Celsius does water freeze?\"," +
            "        \"answers\": [" +
            "          \"32\"," +
            "          \"0\"," +
            "          \"100\"," +
            "          \"273.15\"," +
            "          \"None of the above\"" +
            "        ]" +
            "      }" +
            "    ]" +
            "  }" +
            "  }";

    private String locationId = "location_2dfefccb-f64d-463d-9ce0-410923888b21";
    private String businessId = "business_be8f003a-1072-4ea2-aee7-c1e0f70c8749";
    private String apiToken = "at_key-2df20a76-11e2-4924-9565-2c74372f4e91";
    private String url = "https://atws-stage.herokuapp.com";
    private String urlConsumer = "https://consumer-service-stage.herokuapp.com";
    private String misnapUrl = "https://alltrustuat.rdcselect.com/externalservices";
    private String misnapUsername = "checkuser";
    private String misnapPassword = "P@ych3ck20!8";

    private SmartCheckApi api = new SmartCheckApi(url, apiToken, businessId, locationId, urlConsumer);
    private MisnapApi misnapApi = new MisnapApi(misnapUrl, misnapUsername, misnapPassword);


    @Test
    public void testJson() throws IOException {
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<IdVerificationResponse> idVerificationResponseAdapter = moshi.adapter(IdVerificationResponse.class);
        IdVerificationResponse response = idVerificationResponseAdapter.fromJson(questionJson);
        assertNotNull(response.getChallenge());
    }

    @Test
    public void testSearchCustomerByNumber() throws Exception {
        List<CustomerIdentification> identifications = api.searchCustomerByPhone("3648887948");
        assertNotNull(identifications);
    }

    @Test
    public void testSearchCustomer() throws Exception{
        String str = api.searchCustomer("36488879481", "1284567891111");
        assertNotNull(str);
    }

    @Test
    public void testGetConsumerByCustomerID() throws Exception{
        EnrollmentResponse enroll = api.getConsumerByIDCustomer("customer_6e8d3bb0-3c76-41c2-adc4-1fc2d42a7c3");
        assertNotNull(enroll);
    }

    @Test
    public void testupdateEmail() throws Exception{
        UpdateEmailProfileRequest request = new UpdateEmailProfileRequest();
        request.customerId = "customer_370088dc-b6ba-4c7d-b2dc-24189593784e";
        request.newUsername = "aq321@yopmail.com";
        request.username = "la123456@yopmail.com";
        StatusResponse response = api.updateProfile(request);
        assertEquals(true, response.success);
    }
}
