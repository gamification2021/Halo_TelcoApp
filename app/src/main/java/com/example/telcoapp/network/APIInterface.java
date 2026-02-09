package com.example.telcoapp.network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

//    @FormUrlEncoded  TODO: Change it later (For reference)
//    @POST(APIs.GET_APP_CONFIG)
//    Call<String> getAppConfigs(@Field(Params.ID) int id
//            , @Field(Params.TOKEN) String token);

    @GET
    Call<String> getNearByRestaurants(@Url String url, @Header(APIConstants.Params.USER_KEY) String apiKey);

    @GET
    Call<String> getCurrentWeatherResponse(@Url String url,
                                           @Query(APIConstants.Params.LAT) String latitude,
                                           @Query(APIConstants.Params.LON) String longitude,
                                           @Header(APIConstants.Params.X_RAPIDAPI_HOST) String rapidHost,
                                           @Header(APIConstants.Params.X_RAPIDAPI_KEY) String rapidKey);

    @POST
    @Headers({"App-Id: com.app.gamification",
            "App-Version: 1.0",
            "Tran-Id: 12356",
            "Accepts-Version: 1.0",
            "OS-Version: 10"})
    Call<String> sendFCMToken(@Url String url,
                              @Body String params);

    @GET
    Call<String> sendFCMToken(@Url String url);

    @GET
    Call<String> manageDevices(@Url String url,
                               @Query(APIConstants.Params.DEVICE_ID) String deviceId,
                               @Query(APIConstants.Params.FCM_TOKEN) String fcmToken,
                               @Query(APIConstants.Params.STATUS) String status,
                               @Query(APIConstants.Params.PLATFORM) String platform
                               );

    @GET
    Call<String> googleFit1000Steps(@Url String url,
                                    @Query(APIConstants.Params.DEVICE_ID) String deviceId,
                                    @Query(APIConstants.Params.EVENT_MSG_TYPE) String eventMessageType
    );


    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @GET
    Call<String> getSpinOffers(@HeaderMap HashMap<String, String> map, @Url String url);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @POST
    Call<String> getListGames(@Url String url, @Body String params);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @GET
    Call<String> getPredictList(@HeaderMap HashMap<String, String> map, @Url String url);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @POST
    Call<String> getPredictResult(@HeaderMap HashMap<String, String> map, @Url String url, @Body String params);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @POST
    Call<String> getTransactionHistory(@Url String url,@Body String params);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @POST
    Call<String> getUserProfile(@Url String url,@Body String params);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @POST
    Call<String> getTelcoPackages(@Url String url,@Body String params);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @POST
    Call<String> redeemTelco(@Url String url,@Body String params);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @GET
    Call<String> getPredictHistory(@HeaderMap HashMap<String, String> map, @Url String url);


    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @GET
    Call<String> getReferralCode(@HeaderMap HashMap<String, String> map, @Url String url);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + APIConstants.Params.AUTHORIZATION_TOKEN})
    @POST
    Call<String> getReferralNotification(@HeaderMap HashMap<String, String> map, @Url String url, @Body String params);

    @GET
    Call<String> getRechargeResponse(@Url String url);

    @GET
    Call<String> getCampaignHistory(@HeaderMap HashMap<String, String> map, @Url String url);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + "Bearer J0eWAiOiPQS1QiLCJhbGciOiJIUzI1NiJ1"})
    @GET
    Call<String> getPoints(@HeaderMap HashMap<String, String> map, @Url String url);

    @Headers({"Content-Type:application/json", APIConstants.Params.AUTHORIZATION + ":" + "Bearer J0eWAiOiPQS1QiLCJhbGciOiJIUzI1NiJ1"})
    @POST
    Call<String> postBuyPoints(@HeaderMap HashMap<String, String> map, @Url String url,@Body String params);


}
