package com.mobile.cpt.cpt_mobileapp;

public class Constant {

    // INTENT EXTRAS
    public static final String LAYOUT = "LAYOUT";
    public static final String USER_DATA = "userData";

    // REQUEST CODES
    public static final int INFO_REQUEST_CODE = 6000;

    // LAYOUTS
    public static final int MAIN_LAYOUT = R.layout.activity_main;
    public static final int LOGIN_LAYOUT = R.layout.activity_login;
    public static final int CONTACT_LAYOUT = R.layout.activity_contact;
    public static final int ABOUT_LAYOUT = R.layout.activity_about;
    public static final int EMERGENCY_LAYOUT = R.layout.activity_emergency;
    public static final int ALL_PROBLEMS_LAYOUT = R.layout.activity_all_problems;
    public static final int USER_PROBLEMS_LAYOUT = R.layout.activity_present_faults;
    public static final int EDIT_LAYOUT = R.layout.activity_edit_problem;
    public static final int SHORT_FAULT_LAYOUT = R.layout.short_fault_present;
    public static final int ADD_LAYOUT = R.layout.activity_add_problem;

    // ERRORS
    public static final String DATA_ERROR = "Nie można wczytać danych";
    public static final String SERVER_OUT_OF_CONNECTION = "Błąd połączenia z serwerem";
    public static final String NOT_CONNECTED_TO_INTERNET = "Brak połączenia z internetem";
    public static final String INSERT_LOGIN_DATA = "Wpisz dane logowania";
    public static final String LOGGED_AS = "Zalogowany jako: ";
    public static final String FILL_ALL_FIELDS = "Uzupełnij wszystkie pola";
    public static final String WRONG_LOGIN_DATA = "Złe dane logowania";

    // BUTTONS
    public static final int BTN_LOGOUT = R.id.btn_logout;
    public static final int BTN_CONTACT = R.id.btn_contact;
    public static final int BTN_ABOUT = R.id.btn_about;
    public static final int BTN_ALARMS = R.id.btn_alarms;
    public static final int BTN_LOCAL_FAULTS = R.id.btn_local_faults;
    public static final int BTN_SHOW_FAULTS = R.id.btn_show_faults;
    public static final int BTN_EDIT_FAULT = R.id.btn_edit_fault;
    public static final int BTN_TO_LOG = R.id.btn_login;
    public static final int BTN_REPORT_FAULT = R.id.btn_report_fault;

    // EDIT TEXT
    public static final int ET_LOGIN = R.id.et_login;
    public static final int ET_PASSWORD = R.id.et_password;

    // CODES
    public static final int REPORT_REQUEST_CODE = 5000;
    public static final int EDIT_REQUEST_CODE = 6500;
    public static final int MAIN_ACTIVITY_REQ_CODE = 10;

    // LOGGERS
    public static final String IS_LOGGED = "islogged";
    public static final String JSON = "JSON";
    public static final String URL = "URL";
    public static final String LINK = "link";

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

    // TIMEOUT
    public static final int TIMEOUT = 1000;

    // API LINK
    public static final String HTTP_LOGIN = "http://cpt4cti.000webhostapp.com/login.php";
    public static final String HTTP_REPORT = "https://cpt4cti.000webhostapp.com/add_fault.php";
    public static final String HTTP_PRESENT_USER_FAULTS = "http://cpt4cti.000webhostapp.com/select_fault.php";
    public static final String HTTP_PRESENT_ALL_FAULTS = "http://cpt4cti.000webhostapp.com/select_all_faults.php";

    // FOR API LINK
    public static final String ASK = "?";
    public static final String AND = "&";
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
    public static final String FAULT = "fault";
    public static final int LIST_FAULT = R.id.list_fault;
    public static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";



}
