package utils;//package utils;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteException;
//import android.net.Uri;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import org.joda.time.DateTime;
//import org.joda.time.Days;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
////import static com.bm.main.fpl.activities.PreferenceClass;
//
///**
// * Created by sarifhidayat on 1/26/18.
// **/
//
//public class ReadKeyInbox {
//    private static final String TAG = ReadKeyInbox.class.getSimpleName();
//Context mContext;
//
//    private PreferenceClass preferenceClass;
//    public ReadKeyInbox(Context context){
//        this.mContext=context;
////        preferenceClass=new PreferenceClass(context);
//    }
//
//
//    @NonNull
//    public String ReadKey() throws ParseException {
//        //    String getKey=null;
//        try {
//            // Create Inbox box URI
//            Uri inboxURI = Uri.parse("content://sms/inbox");
//
//            String[] reqCols = new String[]{"_id", "thread_id", "address", "person", "body", "date", "type", "read"};
//
//            String BODY = "KEY";
//
//            StringBuilder smsBuilder = new StringBuilder();
//
//            String WHERE_CONDITION = "";
//            String SORT_ORDER = "date DESC";
//
//            String[] filter = new String[]{"%" + BODY + "%"};
//            int count = 0;
//
//            WHERE_CONDITION += "body LIKE ? ";
//
//            Cursor cursor = mContext.getContentResolver().query(
//                    inboxURI,
//                    reqCols,
//                    WHERE_CONDITION,
//                    filter,
//                    SORT_ORDER);
//
//            if (cursor != null) {
////                System.out.println("count " + cursor.getCount());
//                Log.d(TAG, "ReadKey: count " + cursor.getCount());
//                try {
//                    count = cursor.getCount();
//                    if (count > 0) {
//                        cursor.moveToFirst();
//
//
//
//                        int index_Id = cursor.getColumnIndex("_id");
//                        int index_Thread = cursor.getColumnIndex("thread_id");
//                        int index_Address = cursor.getColumnIndex("address");
//
//                        int index_Person = cursor.getColumnIndex("person");
//
//                        int index_Body = cursor.getColumnIndex("body");
//
//                        int index_Date = cursor.getColumnIndex("date");
//
//                        int index_Type = cursor.getColumnIndex("type");
//                        int index_Read = cursor.getColumnIndex("read");
//
//                        //    do {
//                        long messageId = cursor.getLong(index_Id);
//                        long threadId = cursor.getLong(index_Thread);
//
//                        String strAddress = cursor.getString(index_Address);
//
//                        int intPerson = cursor.getInt(index_Person);
//
//                        String strbody = cursor.getString(index_Body);
//
//                        long longDate = cursor.getLong(index_Date);
//                        // String date = cursor.getString(index_Date);
//
//                        int int_Type = cursor.getInt(index_Type);
//                        int int_Read = cursor.getInt(index_Read);
//
//
//
//
//                        SimpleDateFormat     formatter = new SimpleDateFormat("yyyy-MM-dd",new Locale("in", "ID"));
//
//                        Date datex = new Date();
//                        Date datex_ = formatter.parse(formatter.format(datex));
//                        //DateFormat dateFormatx = new SimpleDateFormat("dd-MM-yyyy");
//
//                        Date date = new Date(longDate);
//                        // DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
////                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                       // System.out.println("format 1 " + formatter.format(date));
//                        Log.d(TAG, "ReadKey: format 1 " + formatter.format(date));
//                        formatter.applyPattern("yyyy-MM-dd");
//                       // System.out.println("format 2 " + formatter.format(date));
//                        Log.d(TAG, "ReadKey: format 2 " + formatter.format(date));
//                        Date date_ = formatter.parse(formatter.format(date));
//
////                        String dateInStringStart = SavePref.getInstance(mContext).getString("startDate").toString().equals("") ? formatter.format(datex) : SavePref.getInstance(mContext).getString("startDate");
//                        String dateInStringStart = PreferenceClass.getString("startDate","").equals("") ? formatter.format(datex) : PreferenceClass.getString("startDate","");
//                       // System.out.println("Date start : " + dateInStringStart);
//                        Log.d(TAG, "ReadKey: Date start : " + dateInStringStart);
////
//                        String dateInStringEnd = PreferenceClass.getString("endDate","").equals("") ? formatter.format(datex) :PreferenceClass.getString("endDate","");
//                     //   System.out.println("Date end : " + dateInStringEnd);
//                        Log.d(TAG, "ReadKey: Date end : " + dateInStringEnd);
//                        Date dateEnd = formatter.parse(dateInStringEnd);
//
//                        DateTime dt1 = new DateTime(date_);
//                        DateTime dt2 = new DateTime(datex_);
//
//                        int limit = Days.daysBetween(dt1, dt2).getDays();
//
//                       // Log.d(TAG, "ReadKey: " + limit + " <= " + PreferenceClass.getInt("limit",0));
//                       // Log.d(TAG,"pesan luar "+ strbody.toString() + "  " + cursor.getString(index_Date));
//
//                        if (limit <= PreferenceClass.getInt("limit",0) ) {
//                            markMessageRead( messageId);
//                          //  Log.d(TAG,"pesannya "+ strbody.toString() + "  " + cursor.getString(index_Date));
//
//                            try {
//                                return strbody.split(BODY)[1].trim().substring(0, 6);
//                            }catch (Exception e){// karena ada sms mengandung#
//                                return "";
//                            }
//                        } else {
//                            return ReadKeyLimit();
//                        }
//
//
//                    }else{
//                        return "";
//                    }
//
//                } finally {
//                    cursor.close();
//                }
//
//
//            } else {
//
//                smsBuilder.append("no result!");
//
//                return "";
//
//            } //empty end if
//
//
//        } catch (SQLiteException ex) {
//
//            //Log.d("SQLiteException", ex.getMessage());
//
//        }
//        return "";
//    }
//
//    @NonNull
//    public String ReadKeyLimit() throws ParseException {
//        //    String getKey=null;
//        try {
//            // Create Inbox box URI
//            Uri inboxURI = Uri.parse("content://sms/inbox");
//
//            String[] reqCols = new String[]{"_id", "thread_id", "address", "person", "body", "date", "type", "read"};
//
//            String BODY = "KEY";
//
//            StringBuilder smsBuilder = new StringBuilder();
//
//            String WHERE_CONDITION = "";
//            String SORT_ORDER = "date DESC";
//
//            String[] filter = new String[]{"%" + BODY + "%"};
//            int count = 0;
//
//            WHERE_CONDITION += "body LIKE ? ";
//
//            Cursor cursor = mContext.getContentResolver().query(
//                    inboxURI,
//                    reqCols,
//                    WHERE_CONDITION,
//                    filter,
//                    SORT_ORDER);
//
//            if (cursor != null) {
////                System.out.println("count " + cursor.getCount());
//                Log.d(TAG, "ReadKeyLimit: count " + cursor.getCount());
//                try {
//                    count = cursor.getCount();
//                    if (count > 0) {
//                        cursor.moveToFirst();
//
//
//
//                        int index_Id = cursor.getColumnIndex("_id");
//                        int index_Thread = cursor.getColumnIndex("thread_id");
//                        int index_Address = cursor.getColumnIndex("address");
//
//                        int index_Person = cursor.getColumnIndex("person");
//
//                        int index_Body = cursor.getColumnIndex("body");
//
//                        int index_Date = cursor.getColumnIndex("date");
//
//                        int index_Type = cursor.getColumnIndex("type");
//                        int index_Read = cursor.getColumnIndex("read");
//
//                        //    do {
//                        long messageId = cursor.getLong(index_Id);
//                        long threadId = cursor.getLong(index_Thread);
//
//                        String strAddress = cursor.getString(index_Address);
//
//                        int intPerson = cursor.getInt(index_Person);
//
//                        String strbody = cursor.getString(index_Body);
//
//                        long longDate = cursor.getLong(index_Date);
//                        // String date = cursor.getString(index_Date);
//
//                        int int_Type = cursor.getInt(index_Type);
//                        int int_Read = cursor.getInt(index_Read);
//
//
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",new Locale("in", "ID"));
//                        Date datex = new Date();
//                        Date datex_ = formatter.parse(formatter.format(datex));
//                        //DateFormat dateFormatx = new SimpleDateFormat("dd-MM-yyyy");
//
//                        Date date = new Date(longDate);
//                        // DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
////                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                        System.out.println("format 1 " + formatter.format(date));
//
//                        formatter.applyPattern("yyyy-MM-dd");
//                        System.out.println("format 2 " + formatter.format(date));
//
//                        Date date_ = formatter.parse(formatter.format(date));
//
////                        String dateInStringStart = SavePref.getInstance(mContext).getString("startDate").toString().equals("") ? formatter.format(datex) : SavePref.getInstance(mContext).getString("startDate");
//                        String dateInStringStart = PreferenceClass.getString("startDate","").equals("") ? formatter.format(datex) : PreferenceClass.getString("startDate","");
//                        System.out.println("Date start : " + dateInStringStart);
//
////
//                        String dateInStringEnd =    PreferenceClass.getString("endDate","").equals("") ? formatter.format(datex) :PreferenceClass.getString("endDate","");
//                        System.out.println("Date end : " + dateInStringEnd);
//                        Date dateEnd = formatter.parse(dateInStringEnd);
//
//                        DateTime dt1 = new DateTime(date_);
//                        DateTime dt2 = new DateTime(datex_);
//
//                        int limit = Days.daysBetween(dt1, dt2).getDays();
//
////                        Log.d(TAG, "ReadKeyLimit: " + limit + " <= " + PreferenceClass.getInt("limit",0));
////                        Log.d(TAG,"pesan luar "+ strbody.toString() + "  " + cursor.getString(index_Date));
//
//                       // if (limit <= preferenceClass.getInt("limit",0) ) {
//                           // markMessageRead( messageId);
//                      //      Log.d(TAG,"pesannya "+ strbody.toString() + "  " + cursor.getString(index_Date));
//
//                            try {
//                                return strbody.split(BODY)[1].trim().substring(0, 6);
//                            }catch (Exception e){// karena ada sms mengandung#
//                                return "";
//                            }
////                        } else {
////                            return "";
////                        }
//
//
//                    }else{
//                        return "";
//                    }
//
//                } finally {
//                    cursor.close();
//                }
//
//
//            } else {
//
//                smsBuilder.append("no result!");
//
//                return "";
//
//            } //empty end if
//
//
//        } catch (SQLiteException ex) {
//
//            //Log.d("SQLiteException", ex.getMessage());
//
//        }
//        return "";
//    }
//
//    private void markMessageRead(long id) {
//
//        Uri inboxURI = Uri.parse("content://sms/inbox");
//        String[] reqCols = new String[]{"_id", "thread_id", "address", "person", "body", "date", "type", "read"};
//        String BODY = "KEY";
//        String WHERE_CONDITION = "";
//        String SORT_ORDER = "date DESC";
//
//        WHERE_CONDITION += "body LIKE ? ";
//        String[] filter = new String[]{"%" + BODY + "%"};
////        Cursor cursor = mContext.getContentResolver().query(inboxURI, null, null, null, null);
//
//        try {
//
//            ContentValues values = new ContentValues();
//            values.put("read", true);
//            mContext.getContentResolver().update(Uri.parse("content://sms/inbox"), values, "_id=" + id, null);
//
//        } catch (Exception e) {
//            //Log.d("Mark Read", "Error in Read: " + e.toString());
//        }
//    }
//
//
//    @NonNull
//    public String ReadOTPBNI() throws ParseException {
//        //    String getKey=null;
//        try {
//            // Create Inbox box URI
//            Uri inboxURI = Uri.parse("content://sms/inbox");
//
//            String[] reqCols = new String[]{"_id", "thread_id", "address", "person", "body", "date", "type", "read"};
//
//            String BODY = preferenceClass.getUser().getContent_awal_otp();
//            String ISIBODY = preferenceClass.getUser().getIsi_content_otp();
//
//            StringBuilder smsBuilder = new StringBuilder();
//
//            String WHERE_CONDITION = "";
//            String SORT_ORDER = "date DESC";
//
//            String[] filter = new String[]{"%" + BODY + "%"};
//            int count = 0;
//
//            WHERE_CONDITION += "body LIKE ? ";
//
//            Cursor cursor = mContext.getContentResolver().query(
//                    inboxURI,
//                    reqCols,
//                    WHERE_CONDITION,
//                    filter,
//                    SORT_ORDER);
//
//            if (cursor != null) {
////                System.out.println("count " + cursor.getCount());
//                Log.d(TAG, "ReadKeyLimit: count " + cursor.getCount());
//                try {
//                    count = cursor.getCount();
//                    if (count > 0) {
//                        cursor.moveToFirst();
//
//
//
//                        int index_Id = cursor.getColumnIndex("_id");
//                        int index_Thread = cursor.getColumnIndex("thread_id");
//                        int index_Address = cursor.getColumnIndex("address");
//
//                        int index_Person = cursor.getColumnIndex("person");
//
//                        int index_Body = cursor.getColumnIndex("body");
//
//                        int index_Date = cursor.getColumnIndex("date");
//
//                        int index_Type = cursor.getColumnIndex("type");
//                        int index_Read = cursor.getColumnIndex("read");
//
//                        //    do {
//                        long messageId = cursor.getLong(index_Id);
//                        long threadId = cursor.getLong(index_Thread);
//
//                        String strAddress = cursor.getString(index_Address);
//
//                        int intPerson = cursor.getInt(index_Person);
//
//                        String strbody = cursor.getString(index_Body);
//
//                        long longDate = cursor.getLong(index_Date);
//                        // String date = cursor.getString(index_Date);
//
//                        int int_Type = cursor.getInt(index_Type);
//                        int int_Read = cursor.getInt(index_Read);
//
//
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",new Locale("in", "ID"));
//                        Date datex = new Date();
//                        Date datex_ = formatter.parse(formatter.format(datex));
//                        //DateFormat dateFormatx = new SimpleDateFormat("dd-MM-yyyy");
//
//                        Date date = new Date(longDate);
//                        // DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
////                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                        System.out.println("format 1 " + formatter.format(date));
//
//                        formatter.applyPattern("yyyy-MM-dd");
//                        System.out.println("format 2 " + formatter.format(date));
//
//                        Date date_ = formatter.parse(formatter.format(date));
//
////                        String dateInStringStart = SavePref.getInstance(mContext).getString("startDate").toString().equals("") ? formatter.format(datex) : SavePref.getInstance(mContext).getString("startDate");
//                        String dateInStringStart = PreferenceClass.getString("startDate","").equals("") ? formatter.format(datex) : PreferenceClass.getString("startDate","");
//                        System.out.println("Date start : " + dateInStringStart);
//
////
//                        String dateInStringEnd =    PreferenceClass.getString("endDate","").equals("") ? formatter.format(datex) :PreferenceClass.getString("endDate","");
//                        System.out.println("Date end : " + dateInStringEnd);
//                        Date dateEnd = formatter.parse(dateInStringEnd);
//
//                        DateTime dt1 = new DateTime(date_);
//                        DateTime dt2 = new DateTime(datex_);
//
//                        int limit = Days.daysBetween(dt1, dt2).getDays();
//
////                        Log.d(TAG, "ReadKeyLimit: " + limit + " <= " + PreferenceClass.getInt("limit",0));
////                        Log.d(TAG,"pesan luar "+ strbody.toString() + "  " + cursor.getString(index_Date));
//
//                        // if (limit <= preferenceClass.getInt("limit",0) ) {
//                        markMessageRead( messageId);
//                        //      Log.d(TAG,"pesannya "+ strbody.toString() + "  " + cursor.getString(index_Date));
//
//                        try {
//                            return strbody.split(ISIBODY)[1].trim().substring(0, 6);
//                        }catch (Exception e){// karena ada sms mengandung#
//                            return "";
//                        }
////                        } else {
////                            return "";
////                        }
//
//
//                    }else{
//                        return "";
//                    }
//
//                } finally {
//                    cursor.close();
//                }
//
//
//            } else {
//
//                smsBuilder.append("no result!");
//
//                return "";
//
//            } //empty end if
//
//
//        } catch (SQLiteException ex) {
//
//            //Log.d("SQLiteException", ex.getMessage());
//
//        }
//        return "";
//    }
//
//}
