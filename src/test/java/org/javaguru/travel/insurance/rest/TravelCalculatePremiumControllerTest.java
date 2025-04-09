package org.javaguru.travel.insurance.rest;

import org.javaguru.travel.insurance.InsuranceApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InsuranceApplication.class)
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    @DisplayName("FirstName is not provided")
    public void shouldReturn1ErrorWhenFirstNameIsNotProvided() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_FirstNameIsNotProvided.json"
                , "rest/TravelCalculatePremiumResponse_FirstNameIsNotProvided.json");
    }

    @Test
    @DisplayName("Lastname is not provided")
    public void shouldReturn1ErrorWhenLastNameIsNotProvided() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_LastNameIsNotProvided.json"
                , "rest/TravelCalculatePremiumResponse_LastNameIsNotProvided.json");
    }

    @Test
    @DisplayName("AgreementDateFrom is not provided")
    public void shouldReturn1ErrorWhenAgreementDateFromNotProvided() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_AgreementDateFromIsNotProvided.json"
                , "rest/TravelCalculatePremiumResponse_AgreementDateFromIsNotProvided.json");
    }

    @Test
    @DisplayName("AgreementDateTo is not provided")
    public void shouldReturn1ErrorWhenAgreementDateToNotProvided() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_AgreementDateToIsNotProvided.json"
                , "rest/TravelCalculatePremiumResponse_AgreementDateToIsNotProvided.json");
    }

    @Test
    @DisplayName("AgreementDateTo < agreementDateFrom")
    public void shouldReturn1ErrorWhenAgreementDateToLessThenAgreementDateFrom() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_AgreementDateToLessAgreementDateFrom.json"
                , "rest/TravelCalculatePremiumResponse_AgreementDateToLessAgreementDateFrom.json");
    }

    @Test
    @DisplayName("AgreementDateFrom is in the past")
    public void agreementDateFromIsInThePast() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_AgreementDateFromIsInThePast.json",
                "rest/TravelCalculatePremiumResponse_AgreementDateFromIsInThePast.json"
        );
    }

    @Test
    @DisplayName("AgreementDateTo is in the past")
    public void agreementDateToIsInThePast() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_AgreementDateToIsInThePast.json",
                "rest/TravelCalculatePremiumResponse_AgreementDateToIsInThePast.json"
        );
    }

    @Test
    @DisplayName("SelectedRisks Is Null")
    public void shouldReturn1ErrorWhenSelectedRisksIsNull() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_SelectedRisksIsNull.json"
                , "rest/TravelCalculatePremiumResponse_SelectedRisksIsNull.json");
    }

    @Test
    @DisplayName("SelectedRisks Is Empty")
    public void shouldReturn1ErrorWhenSelectedRisksIsEmpty() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_SelectedRisksIsEmpty.json"
                , "rest/TravelCalculatePremiumResponse_SelectedRisksIsEmpty.json");
    }


    @Test
    @DisplayName("Success, correct data was provided")
    public void shouldReturnCorrectFilledResponse() throws Exception {
        executeAndCompare("rest/TravelCalculatePremiumRequest_CorrectDataWasProvided.json"
                , "rest/TravelCalculatePremiumResponse_CorrectDataWasProvided.json");
    }

    @Test
    public void allFieldsNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_AllFieldsIsNotProvided.json",
                "rest/TravelCalculatePremiumResponse_AllFieldsIsNotProvided.json"
        );
    }

    private void executeAndCompare(String jsonRequestPath, String jsonResponsePath) throws Exception {
        String requestData = jsonFileReader.readJsonFromFile(jsonRequestPath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(requestData)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseFromServer = result.getResponse().getContentAsString();
        String expectedResponse = jsonFileReader.readJsonFromFile(jsonResponsePath);

        assertJson(responseFromServer)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponse);
    }
}

