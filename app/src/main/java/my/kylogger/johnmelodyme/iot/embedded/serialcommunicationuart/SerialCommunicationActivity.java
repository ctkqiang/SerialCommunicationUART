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
 * @Class: SeialCommunicationActivity.class
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.ligl.android.widget.iosdialog.IOSDialog;
import com.physicaloid.lib.Physicaloid;
import com.physicaloid.lib.usb.driver.uart.ReadLisener;
import java.util.Arrays;


public class SerialCommunicationActivity extends AppCompatActivity {
    public static final String TAG = "SerialCommunicationUART";
    public Physicaloid physicaloid;
    private TextView OutPut;
    private CheckBox AutoScroll;
    private Spinner Baud;
    private Button Send_Btn, Clear_btn, OpenBtn, CloseBtn;
    private EditText Input;

    public void DeclarationInit() {
        Input = findViewById(R.id.input);
        OpenBtn = findViewById(R.id.open);
        CloseBtn = findViewById(R.id.close);
        Send_Btn = findViewById(R.id.send);
        Clear_btn = findViewById(R.id.Clear);
        OutPut = findViewById(R.id.out);
        Baud = findViewById(R.id.baud);
        AutoScroll = findViewById(R.id.autoscroll);
        physicaloid = new Physicaloid(this);
        setEnabledUi(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: " + SerialCommunicationActivity.class.getSimpleName());
        DeclarationInit();

        OpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BaudText = Baud.getSelectedItem().toString();
                switch (BaudText) {
                    case "300 baud":
                        physicaloid.setBaudrate(300);
                        break;

                    case "1200 baud":
                        physicaloid.setBaudrate(1200);
                        break;

                    case "2400 baud":
                        physicaloid.setBaudrate(2400);
                        break;

                    case "4800 baud":
                        physicaloid.setBaudrate(4800);
                        break;

                    case "9600 baud":
                        physicaloid.setBaudrate(9600);
                        break;

                    case "19200 baud":
                        physicaloid.setBaudrate(19200);
                        break;

                    case "38400 baud":
                        physicaloid.setBaudrate(38400);
                        break;

                    case "576600 baud":
                        physicaloid.setBaudrate(576600);
                        break;

                    case "7448800 baud":
                        physicaloid.setBaudrate(744880);
                        break;

                    case "115200 baud":
                        physicaloid.setBaudrate(115200);
                        break;

                    case "230400 baud":
                        physicaloid.setBaudrate(230400);
                        break;

                    case "250000 baud":
                        physicaloid.setBaudrate(250000);
                        break;

                    default:
                        physicaloid.setBaudrate(9600);
                        break;
                }

                if (AutoScroll.isChecked()){
                    OutPut.setMovementMethod(new ScrollingMovementMethod());
                    Log.d(TAG, "Auto Scroll isChecked()");
                }

                physicaloid.addReadListener(new ReadLisener() {
                    @Override
                    public void onRead(int size) {
                        byte [] buffer = new byte[size];
                        physicaloid.read(buffer, size);
                        OutputAppend(OutPut, Html.fromHtml("<font color=blue>" + new String(buffer) + "</font>"));
                        Log.d(TAG, "onRead: " + Arrays.toString(buffer));
                    }
                });
            }
        });

        CloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (physicaloid.close()) {
                    physicaloid.clearReadListener();
                    setEnabledUi(false);
                }
            }
        });

        Send_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String out;
                out = Input.getText().toString()+"\r\n";
                if (out.length() > 0) {
                    byte [] buffer = out.getBytes();
                    physicaloid.write(buffer, buffer.length);
                }
            }
        });
    }

    public void setEnabledUi(boolean on) {
        if (on) {
            OpenBtn.setEnabled(false);
            Baud.setEnabled(false);
            AutoScroll.setEnabled(false);
            CloseBtn.setEnabled(true);
            Input.setEnabled(true);
            OutPut.setEnabled(true);
        } else {
            OpenBtn.setEnabled(true);
            Baud.setEnabled(true);
            AutoScroll.setEnabled(true);
            CloseBtn.setEnabled(false);
            Input.setEnabled(false);
            OutPut.setEnabled(false);
        }
    }

    Handler handler = new Handler();
    private void OutputAppend(TextView tv, CharSequence text) {
        final TextView ftv = tv;
        final CharSequence sequence = text;
        handler.post(new Runnable() {
            @Override
            public void run() {
                ftv.append(sequence);
                Log.d(TAG, "Sequence " + sequence);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.About) {
            new IOSDialog.Builder(SerialCommunicationActivity.this)
                    .setTitle("About")
                    .setMessage(getResources().getString(R.string.aboutdev) +
                            "\n" +
                            getResources().getString(R.string.Johnmelody) +
                            "\n" +
                            getResources().getString(R.string.SinDee))
                    .setPositiveButton("Ok", null)
                    .show();
            return false;
        }

        if (menuItem.getItemId() == R.id.arduinocode){
            Intent ToArduino;
            ToArduino = new Intent(SerialCommunicationActivity.this, ArduinoCode.class);
            startActivity(ToArduino);
            return false;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}