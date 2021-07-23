package by.startandroid.weatherforecastdemo.ui

import android.content.Context
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.widget.RemoteViews
import by.startandroid.weatherforecastdemo.Key
import by.startandroid.weatherforecastdemo.R
import by.startandroid.weatherforecastdemo.data.WeatherForecast
import by.startandroid.weatherforecastdemo.data.remote.Remote
import by.startandroid.weatherforecastdemo.di.AppModule.provideDatabase
import by.startandroid.weatherforecastdemo.di.AppModule.provideLocal
import by.startandroid.weatherforecastdemo.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WidgetProvider(): AppWidgetProvider() {

    private var weather: WeatherForecast? = null

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        val remote = Remote()
        val repository = context?.let { Repository(remote, provideLocal(provideDatabase(it))) }

        CoroutineScope(Dispatchers.IO).launch {
            val city = repository?.selectName()

            //  Once you have your key, replace the "Key().key" string.
            weather = city?.let { remote.getWeather(it, Key().key, "metric", "ru") }

            val widgetView = RemoteViews(context?.packageName, R.layout.widget_layout)
            widgetView.setTextViewText(R.id.textView, weather?.name)
            widgetView.setTextViewText(R.id.textView1, "${weather?.main?.temp} C")
            widgetView.setTextViewText(R.id.textView2, weather?.wind?.speed.toString())

            appWidgetManager?.updateAppWidget(appWidgetIds, widgetView)
        }
    }
}