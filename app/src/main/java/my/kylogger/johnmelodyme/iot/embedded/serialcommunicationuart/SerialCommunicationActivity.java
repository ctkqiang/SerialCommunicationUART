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
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.hoho.*;
import com.hoho.android.usbserial.driver.UsbSerialDriver;

public class SerialCommunicationActivity extends AppCompatActivity {
    public static final String TAG = "SerialCommunicationUART";
    public UsbSerialDriver usbSerialDriver;
    private TextView RX,TX;
    private Button Send_Btn;

    public void DeclarationInit() {
        RX = findViewById(R.id.RX);
        TX = findViewById(R.id.TX);
        Send_Btn = findViewById(R.id.send);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: " + SerialCommunicationActivity.class.getSimpleName());
        DeclarationInit();
    }
}
