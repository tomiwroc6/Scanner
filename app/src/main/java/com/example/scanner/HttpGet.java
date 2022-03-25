package com.example.scanner;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet extends AsyncTask<String, Void, String>
{
    protected String doInBackground(String...urls)
    {
        HttpURLConnection urlConnection = null;
        StringBuffer readTextBuf = new StringBuffer();

        try
        {
            URL url = new URL( urls[0] );

            //url = new URL ( "http" , "sprawdzarka.astra" , 80 , urls[0] );

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader isReader = new InputStreamReader(inputStream);
            BufferedReader bufReader = new BufferedReader(isReader);

             String line = bufReader.readLine();

            while (  line != null )
            {
                readTextBuf.append(line);
                line = bufReader.readLine();
            }

            bufReader.close();
            isReader.close();
        }
        catch ( Exception e )
        {
            readTextBuf.append(e.toString());

            if ( urlConnection != null )
                urlConnection.disconnect();

            //return readTextBuf.toString();
        }
        finally
        {
            if ( urlConnection != null )
                urlConnection.disconnect();
        }

        return readTextBuf.toString();
    }

    protected void onPostExecute (String result)
    {
        if ( result != null )
        {
            String s[] = result.split ("@");

            if ( s != null && s.length == 2 )
            {
                MainActivity.instance.setTitle(s[0]);
                MainActivity.nazwa.setText(s[1]);
            }
            else
            {
                MainActivity.instance.setTitle(result);
                MainActivity.nazwa.setText(result);
            }

//            MainActivity.text.setText(result);
        }
        else
            MainActivity.instance.setTitle("Błąd");

    }

}
