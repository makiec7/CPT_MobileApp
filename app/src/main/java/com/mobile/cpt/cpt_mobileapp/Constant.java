package com.mobile.cpt.cpt_mobileapp;

import android.content.Context;
import android.os.StrictMode;
import android.os.Vibrator;
import android.widget.Toast;

public class Constant {

    // INTENT EXTRAS
    public static final String USER_DATA = "USER_DATA";
    public static final String FAULT = "FAULT";
    public static final String TYPE = "TYPE";
    public static final String STRING = "STRING";
    public static final String MANUAL = "MANUAL";
    public static final String AUTO = "AUTO";
    public static final String FINNISH = "FINNISH";

    // LAYOUTS
    public static final int MAIN_LAYOUT = R.layout.activity_main;
    public static final int LOGIN_LAYOUT = R.layout.activity_login;
    public static final int CONTACT = R.string.administration_contact;
    public static final int ABOUT = R.string.about_project;
    public static final int INFO_STRING = R.id.info_string;
    public static final int EMERGENCY = R.string.emergency_contact;
    public static final int USER_PROBLEMS_LAYOUT = R.layout.activity_present_faults;
    public static final int EDIT_LAYOUT = R.layout.activity_edit_problem;
    public static final int SHORT_FAULT_LAYOUT = R.layout.short_fault_present;
    public static final int ACTIVITY_INFO = R.layout.activity_info;
    public static final int ACTIVITY_ADD_PROBLEM_AUTO = R.layout.activity_add_problem_auto;
    public static final int ACTIVITY_ADD_PROBLEM_MANUAL = R.layout.activity_add_problem_manual;

    // ALERTS
    public static final String DATA_ERROR = "Nie można wczytać danych";
    public static final String BAD_PHONE_NUMBER = "Niepoprawny numer telefonu";
    public static final String SERVER_OUT_OF_CONNECTION = "Błąd połączenia z serwerem";
    public static final String NOT_CONNECTED_TO_INTERNET = "Brak połączenia z Internetem";
    public static final String INSERT_LOGIN_DATA = "Wpisz dane logowania";
    public static final String LOGGED_AS = "Zalogowany jako: ";
    public static final String FILL_ALL_FIELDS = "Uzupełnij wszystkie pola";
    public static final String WRONG_LOGIN_DATA = "Złe dane logowania";
    public static final String CANNOT_DETECT_CAMERA = "Nie wykryto aparatu";
    public static final String REPORT_ERROR = "Błąd dodawania";
    public static final String REPORT_SUCCESS = "Zgłoszenie dodane";
    public static final String EDIT_SUCCESS = "Edytowano poprawnie";
    public static final String DELETE_SUCCESS = "Usunięto poprawnie";
    public static final String BARCODE_NOT_DETECTED = "Nie wykryto kodu kreskowego.";
    public static final String DETECTED_BARCODE = "Wykryty kod: ";
    public static final String REPORTING_FAULT = "Dodawanie zgłoszenia";
    public static final String SET_REPORT_TYPE = "Wybierz rodzaj dodawania";
    public static final String FIND_FAULT = "Wyszukaj zgłoszenie";


    // BUTTONS
    public static final int BTN_LOGOUT = R.id.btn_logout;
    public static final int BTN_CONTACT = R.id.btn_contact;
    public static final int BTN_ABOUT = R.id.btn_about;
    public static final int BTN_ALARMS = R.id.btn_alarms;
    public static final int BTN_EDIT = R.id.btn_edit;
    public static final int BTN_DELETE = R.id.btn_delete;
    public static final int BTN_SEARCH_FAULT = R.id.btn_search_fault;
    public static final int BTN_SHOW_FAULTS = R.id.btn_show_faults;
    public static final int BTN_EDIT_FAULT = R.id.btn_edit_fault;
    public static final int BTN_TO_LOG = R.id.btn_login;
    public static final int BTN_REPORT_FAULT = R.id.btn_report_fault;
    public static final int BTN_MANUAL = R.id.btn_manual;
    public static final int BTN_AUTO = R.id.btn_auto;
    public static final int BTN_SCAN = R.id.btn_scan;
    public static final int BTN_ADD = R.id.btn_add;

    // EDIT TEXT
    public static final int ET_LOGIN = R.id.et_login;
    public static final int ET_PASSWORD = R.id.et_password;
    public static final int ET_TOPIC = R.id.et_topic;
    public static final int ET_PHONE_NUMBER = R.id.et_phone_number;
    public static final int ET_OBJ_NO = R.id.et_obj_no;
    public static final int ET_DESCRIPTION = R.id.et_description;

    // TEXT VIEWS
    public static final int TV_ID = R.id.tv_id;
    public static final int TV_DESCRIPTION = R.id.tv_description;
    public static final int TV_ISSUER = R.id.tv_issuer;
    public static final int TV_OBJ_NO = R.id.tv_object_number;
    public static final int TV_DATETIME = R.id.tv_datetime;
    public static final int TV_TOPIC = R.id.tv_topic;
    public static final int TV_ISSUER_ID = R.id.tv_issuer_id;

    // IMAGE VIEWS
    public static final int IV_STATUS = R.id.iv_status;

    // CODES
    public static final int REPORT_REQUEST_CODE = 5000;
    public static final int EDIT_REQUEST_CODE = 6500;
    public static final int MAIN_ACTIVITY_REQ_CODE = 10;
    public static final int REPORT_TYPE_REQUEST_CODE = 4321;

    // LOGGERS
    public static final String JSON = "JSON";

    // BOOLEAN STRING
    public static final String TRUE = "true";
    public static final String NULL_STRING = "";
    public static final String FALSE = "false";

    // JSON
    public static final String USER = "user";
    public static final String LOGIN_STATUS = "login_status";
    public static final String QUERY_STATUS = "query_status";
    public static final String ID = "id";
    public static final String ISSUER = "issuer";
    public static final String TOPIC = "topic";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String DESCRIPTION = "description";
    public static final String OBJECT_NUMBER = "object_number";
    public static final String DATE_TIME = "date_time";
    public static final String STATUS = "status";
    public static final String LIST = "list";

    // TIMEOUT
    public static final int TIMEOUT = 1000;

    // API LINK
    public static final String LINK = "http://cpt4cti.24tm.pl/";
    public static final String HTTP_LOGIN = LINK + "login.php";
    public static final String HTTP_REPORT = LINK + "add_fault.php";
    public static final String EDIT_HTTP = LINK + "edit_fault.php";
    public static final String HTTP_PRESENT_USER_FAULTS = LINK + "select_fault.php";
    public static final String HTTP_PRESENT_ALL_FAULTS = LINK + "select_all_faults.php";
    public static final String HTTP_DELETE = LINK + "delete_fault.php";

    // FOR API LINK
    public static final String ASK = "?";
    public static final String AND = "&";
    public static final String ID_EQ = "id=";
    public static final String PASSWORD_EQ = "password=";
    public static final String INDEX_NO_EQ = "index_no=";
    public static final String ISSUER_EQ = "issuer=";
    public static final String TOPIC_EQ = "topic=";
    public static final String OBJECT_NUMBER_EQ = "object_number=";
    public static final String PHONE_NUMBER_EQ = "phone_number=";
    public static final String DATE_TIME_EQ = "date_time=";
    public static final String DESCRIPTION_EQ = "description=";

    // OTHER
    public static final String UTF_8 = "UTF-8";
    public static final int LIST_FAULT = R.id.list_fault;
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String POST = "POST";
    public static final String PLUS = "+";
    public static final int ONE_MILLISECOND = 1;
    public static final int RED = 230;
    public static final int GREEN = 230;
    public static final int BLUE = 230;

    // DRAWABLE
    public static final int CROSS_LOGO = R.drawable.cross_logo;
    public static final int ONGOING_ICON = R.drawable.ongoing_icon;
    public static final int TICK_LOGO = R.drawable.tick_logo;
    public static final int ACTIVITY_SET_REPORT_TYPE = R.layout.activity_set_report_type;
    public static final int HEADER = R.id.app_name;


    // STATIC METHODS
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void vibrate(int millis, Context context){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(millis);
    }
}
