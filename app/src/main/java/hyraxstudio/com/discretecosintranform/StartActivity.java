package hyraxstudio.com.discretecosintranform;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    TextView textView;
    EditText input;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Typeface mono = Typeface.createFromAsset(getAssets(), "consola.ttf");

        input = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.btn_ok);
        textView = (TextView)findViewById(R.id.tv_result);
        input.setTypeface(mono);
        textView.setTypeface(mono);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Matrix Image\n");
                textView.append(new Matrix(input.getText().toString()).print());
                textView.append("\nMatrix T\n");
                textView.append(new Matrix(input.getText().toString()).createMatrixT().printStrict());
                textView.append("\nMatrix T'\n");
                textView.append(new Matrix(input.getText().toString()).createMatrixT().matrixChangePosition().printStrict());
                textView.append("\nDCT\n");
                textView.append(new Matrix(input.getText().toString()).transformDCT().print());
            }
        });
    }
}
