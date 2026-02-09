package com.example.telcoapp.network;

public class APIConstants {

    public static final long LOCATION_INTERVAL = 5000;
    public static final long FASTEST_LOCATION_INTERVAL = 3000;

    private APIConstants() {

    }

    public static class Constants {

    }


    public static class URLs {
        static final String LIVE_URL = "https://developers.zomato.com/";
        static final String BASE_URL = LIVE_URL;

        static final String ADCB_URL = "https://gameuat.intclstl.com/";
//        static final String MFS_URL = "http://49.206.240.154:8150/";
        static final String MFS_URL = "http://10.0.0.95:8280/";

        public static final String SPIN_OFFERS_LIST = MFS_URL + "Gamification-1.0/Gamification/gameEngine/executeGame/";
        public static final String SPIN_REWARD = MFS_URL+"Gamification-1.0/Gamification/executeEvent";
        public static final String GAME_LIST = MFS_URL+"Gamification-1.0/Gamification/listGames";
        public static final String RECHARGE_API = "http://49.206.240.154:8280/ot/eventreciever?";

        private URLs() {

        }
    }

    public static class ErrorCodes {
        private ErrorCodes() {
        }

    }

    public static class APIs {
        private APIs() {
        }
    }

    public static class Params {
        public static final String NAME = "name";
        public static final String PHOTO_URL = "photos_url";
        public static final String LOCATION = "location";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String AVERAGE_COST = "average_cost_for_two";
        public static final String CURRENCY = "currency";
        public static final String RATING = "aggregate_rating";
        public static final String USER_RATING = "user_rating";
        public static final String VOTES = "votes";
        public static final String CUISINES = "cuisines";
        public static final String THUMB = "thumb";
        public static final String FEATURED_IMAGE = "featured_image";
        public static final String LAT = "lat";
        public static final String LON = "lon";
        public static final String X_RAPIDAPI_HOST = "x-rapidapi-host";
        public static final String X_RAPIDAPI_KEY = "x-rapidapi-key";
        public static final String WEATHER = "weather";
        public static final String DESCRIPTION = "description";
        public static final String TEMP = "temp";
        public static final String MAIN = "main";
        public static final String COORD = "coord";
        public static final String LIST = "list";
        public static final String DT_TXT = "dt_txt";
        public static final String DEVICE_ID = "deviceId";
        public static final String FCM_TOKEN = "fcmToken";
        public static final String STATUS = "status";
        public static final String EVENT_MSG_TYPE = "eventMessageType";
        public static final String PLATFORM = "platform";
        public static final String CURRENT_NUMBER = "1";

        public static final String TRANSACTION_ID = "transactionId";
        public static final String SERVICE_ID = "serviceId";
        public static final String FROM_DATE = "fromDate";
        public static final String TO_DATE = "toDate";
        public static final String CHANNEL = "channel";
        public static final String NO_OF_LAST_TRANSACTION = "noOfLastTransaction";
        public static final String NO_OF_MONTHS = "noOfMonths";
        public static final String OFFSET = "offSet";
        public static final String LIMIT = "limit";
        public static final String LANGUAGE_ID = "languageID";
        public static final String STATUS_CODE = "statusCode";
        public static final String TRANSACTION_DETAILS = "transactionDetails";
        public static final String TYPE = "type";
        public static final String ACCUMULATION = "Accumulation";
        public static final String LOYALTY_POINTS = "loyaltyPoints";
        public static final String ACTIVITY_TRANSACTION = "activity";
        public static final String LOYALTY_DETAILS = "loyaltyDetails";
        public static final String  CUSTOMER_DETAILS = "customerDetails";
        public static final String MALE = "Male";
        public static final String OFFER_TYPE = "offerType";
        public static final String PACKAGES = "packages";
        public static final String PACKAGE_NAME = "packageName";
        public static final String PACKAGE_ID = "packageID";
        public static final String REDEEM_POINTS = "redeemPoints";
        public static final String PACK_ID = "packId";
        public static final String INFO = "info";
        public static final String DATA = "data";
        public static final String VALUE = "value";
        public static final String ENGLISH = "EN";

        public static final String CUSTOMER_ID = "customerId";
        public static final String LANG = "LANG";
        public static final String RESPCODE = "respCode";
        public static final String SUCCESS_RESP = "SC0000";
        public static final String LIST_OFFER_DETAILS = "offers";
        public static final String REWARD_ID = "rewardId";
        public static final String REWARD_TITLE = "rewardTitle";
        public static final String ASSIGN_REWARD = "AssignReward";
        public static final String DEFAULT_REWARD = "defaultReward";
        public static final String RESP_DESC = "respDesc";
        public static final String AUTHORIZATION = "Authorization";
        public static final String AUTHORIZATION_TOKEN = "Bearer J0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9";
        public static final String LOST_GAME = "SC0001";
        public static final String DISPLAY_DETAILS = "displayDetails";
        public static final String RESPONSE_OBJECT = "responseObject";
        public static final String RESPONSE_LIST = "responseList";
        public static final String EXPIRY_DATE = "expiryDate";
        public static final String IMAGE_LIST = "imageList";
        public static final String NO_POINTS = "SC0003";
        public static final String REQUEST_ID = "requestId";
        public static final String TIME_STAMP = "timeStamp";
        public static final String KEYWORD = "keyword";
        public static final String KEY = "key";
        public static final String KEY_TYPE = "keyType";
        public static final String EXECUTE_EVENT = "executeEvent";
        public static final String GAME = "GAME";
        public static final String ACTIVITY = "Activity";
        public static final String MILESTONE = "MILESTONE";
        public static final String QUERY_PARAMS = "queryParams";
        public static final String LANGUAGE = "LANGUAGE";
        public static final String PRECITION_LIST = "predictionList";
        public static final String TORNAMENT_NAME = "tournamentName";
        public static final String TORNAMENTS = "tournaments";
        public static final String EVENT_LIST = "eventList";
        public static final String EVENT_ID = "eventid";
        public static final String OPONENT_NAME_A = "OpponentA";
        public static final String OPONENT_NAME_B = "OpponentB";
        public static final String OPONENT_SYNOPSIS_A = "opponentASynonym";
        public static final String OPONENT_SYNOPSIS_B = "opponentBSynonym";
        public static final String IMAGE_CARD_OPONENT_A = "imageCardOppenentA";
        public static final String IMAGE_CARD_OPONENT_B = "imageCardOppenentB";
        public static final String MATCH_DATE = "predictionLockingTime";
        public static final String QUESTION_LIST = "questionList";
        public static final String QUESTION_ID = "questionId";
        public static final String PRED_OPTIONS = "predOptions";
        public static final String TEXT = "text";
        public static final String ID = "id";
        public static final String QUESTION = "question";
        public static final String PREDICTION_LOCKING_TIME = "predictionLockingTime";
        public static final String OPTION_ID = "optionId";
        public static final String OPTION_SELECTED = "optionSlected";
        public static final String SUBMIT_PREDICTION = "submitPredictions";
        public static final String ANSWER_LIST = "answerList";
        public static final String SHARE_MESSAGE = "Share_Message";
        public  static final  String MSISDN = "msisdn";
        public static final String RESPONSE_DETAILS = "responseDetail";
        public static final String GAMES = "games";
        public static final String GAME_TITLE = "gameTitle";
        public static final String EXECUTION_STATUS = "executionStatus";
        public static final String EXECUTION_PERIOD = "executionPeriod";
        public static final String VALIDITY_PERIOD = "validityPeriod";
        public static final String ACTIVE = "Active";
        public static final String END_DATE = "endDateTime";
        public static final String LOCKED = "Locked";
        public static final String START_DATE = "startDateTime";
        public static final String GAME_ID = "gameId";
        public static final String GAME_TYPE = "gameType";
        public static final String LIST_GAMES = "ListGames";
        public static final String SPINNWIN = "SpinNWin";
        public static final String PREDICTNWIN = "PredictNWin";
        public static final String REFERNWIN = "ReferNWin";
        public static final String FILTER_ON = "filterOn";
        public static final String FILTER_VALUE = "filterValue";
        public static final String SEARCH_FILTER = "searchFilter";
        public static final String PREDICTION_HISTORY = "predictionHistory";
        public static final String REFERRAL_CODE = "referralCode";
        public static final String REFERRAL_MESSAGE = "referralMessage";
        public static final String REWARD_MESSAGE = "rewardMessage";
        public static final String X_CORELATION_ID = "X-CORELATION-ID";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String X_CORRELATION_ID = "X_CORRELATION_ID";
        public static final String X_LANGUAGE = "X_LANGUAGE";
        public static final String CAMPAIGN_DESCRIPTION = "campaignDescription";
        public static final String MESSAGE = "message";
        public static final String SEND_DATE = "sendDate";
        public static final String POINTS = "points";
        public static final String TRANSACTION_TYPE = "transactionType";
        public static final String ACTION_TYPE = "actionType";

        Params() {
        }

        public static final String USER_KEY = "user-key";
        public static final String NEAR_BY_RESTAURANTS = "nearby_restaurants";
        public static final String RESTAURANT = "restaurant";
    }

    public static class STATIC_PAGES {
    }
}
