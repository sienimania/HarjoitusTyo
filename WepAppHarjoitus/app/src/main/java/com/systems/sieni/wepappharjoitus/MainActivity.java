package com.systems.sieni.wepappharjoitus;

        import android.os.AsyncTask;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import java.util.ArrayList;
        import java.util.List;

        import org.apache.http.NameValuePair;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.ResponseHandler;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.BasicResponseHandler;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;





public class MainActivity extends ActionBarActivity {

    EditText viesti;
    TextView ulos;
    Button SendBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viesti = (EditText) findViewById(R.id.editTextViesti);
        ulos = (TextView) findViewById(R.id.textViewOutput);

        SendBTN = (Button) findViewById(R.id.buttonSend);
        SendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendBTN.setText("Loading");
                SendBTN.setEnabled(false);
                new HttpAsync().execute();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }









    private class HttpAsync extends AsyncTask<Void,Integer,String>{

        @Override
        protected String doInBackground(Void... voids)
        {
            String output = viesti.getText().toString();
            String retval="";
            try {

               HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://94.22.161.130//HarjoitusTyo/index.php");
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();

                pairs.add(new BasicNameValuePair("d0", "Looppi"));
                pairs.add(new BasicNameValuePair("d1", output));

                post.setEntity(new UrlEncodedFormEntity(pairs));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String responseBody = client.execute(post, responseHandler);
                retval = responseBody;

            }
            catch(Exception e)
            {
                retval=e.toString();
            }
            return retval;
        }
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            ulos.setText("From Server: " + result);

            SendBTN.setText("Send");
            SendBTN.setEnabled(true);
        }
    }
}
