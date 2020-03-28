package my.kylogger.johnmelodyme.iot.embedded.serialcommunicationuart;
/**
 * Copyright 2020 © John Melody Melissa
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @Author : John Melody Melissa
 * @Copyright: John Melody Melissa  © Copyright 2020
 * @INPIREDBYGF : Sin Dee <3
 * @Class: ArduinoCode.class
 */
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import es.dmoral.toasty.Toasty;

public class ArduinoCode extends AppCompatActivity implements View.OnTouchListener{
    public static final String TAG = "SerialCommunicationUART";
    public final static int TOAST_DURATION = Toast.LENGTH_SHORT;
    private StringBuilder text = new StringBuilder();
    private TextView arduino;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_code);
        arduino = findViewById(R.id.arduino);
        arduino.setOnTouchListener(this);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("arduino.txt")));

            String line;
            while ((line = reader.readLine()) != null){
                text.append(line);
                text.append("\n");
            }
        } catch (IOException e) {
            Toasty.error(getApplicationContext(),
                    getResources().getString(R.string.errReading),
                    TOAST_DURATION)
                    .show();
            e.printStackTrace();
        }  finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        arduino.setText((CharSequence) text);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    // TODO onTouch();
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
