package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Date;
import java.util.Random;

import barqsoft.footballscores.service.myFetchService;


/**
 * Implementation of App Widget functionality.
 */
public class NewGamesWidgetProvider extends AppWidgetProvider {

    private final String LOG_TAG = NewGamesWidgetProvider.class.getSimpleName();


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context)
    {
        // start alarm
        AppWidgetAlarm appWidgetAlarm = new AppWidgetAlarm(context.getApplicationContext());
        appWidgetAlarm.startAlarm();
    }

    @Override
    public void onDisabled(Context context)
    {
        // TODO: alarm should be stopped only if all widgets has been disabled

        // stop alarm
        AppWidgetAlarm appWidgetAlarm = new AppWidgetAlarm(context.getApplicationContext());
        appWidgetAlarm.stopAlarm();
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        int number = (new Random().nextInt(100));

        Log.w("MainAc WidgetExample", String.valueOf(number));


        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_games_widget_provider);
//        views.setTextViewText(R.id.appwidget_text, widgetText);
//        views.setTextViewText(R.id.appwidget_text, String.valueOf(number));

        // Create an Intent to launch MainActivity
        Intent launchIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setOnClickPendingIntent(R.id.widget, pendingIntent);
        views.setOnClickPendingIntent(R.id.appwidget_layout, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


        if(intent.getAction().equals(Constants.ACTION_AUTO_UPDATE))
        {
            Intent service_start = new Intent(context , myFetchService.class);
            context.startService(service_start);
        }
        else {
            int insertedData = intent.getIntExtra(Constants.TOTAL_INSERTED_DATA, 0);


/*
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_games_widget_provider);
            views.setTextViewText(R.id.appwidget_text, String.valueOf(insertedData ));

            Date now = new Date();
            String dateString = now.toString();

            views.setTextViewText(R.id.appwidget_text_date, dateString);




            int ids[] = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            final int N = ids.length;
            for (int i = 0; i < N; i++) {
                appWidgetManager.updateAppWidget(ids[i] , views);
            }
*/



            if (insertedData > 0 ){
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_games_widget_provider);
//                views.setTextViewText(R.id.appwidget_text, String.valueOf(insertedData ) + " match results ");

                Date now = new Date();
                String dateString = now.toString();

               // views.setTextViewText(R.id.appwidget_text_date, dateString);

                String mDate = null;
                String mTime = null;

/*
                SimpleDateFormat new_date = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
//                new_date.setTimeZone(TimeZone.getDefault());
                mDate = new_date.format(dateString);
                mTime = mDate.substring(mDate.indexOf(":") + 1);
*/

                String mOutput = String.valueOf(insertedData ) + " match results(" + dateString + ")";
                Log.d(LOG_TAG, mOutput);

                views.setTextViewText(R.id.appwidget_text, mOutput);

                int ids[] = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                final int N = ids.length;
                for (int i = 0; i < N; i++) {
                    appWidgetManager.updateAppWidget(ids[i] , views);
                }
 //               Log.v(LOG_TAG, "Succesfully Inserted : " + String.valueOf(insertedData));
            }
            else {

/*
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_games_widget_provider);
                views.setTextViewText(R.id.appwidget_text, "0 matches");

                Date now = new Date();
                String dateString = now.toString();

                views.setTextViewText(R.id.appwidget_text_date, dateString);

                int ids[] = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                final int N = ids.length;
                for (int i = 0; i < N; i++) {
                    appWidgetManager.updateAppWidget(ids[i] , views);
                }
*/

            }
        }
    }
}

