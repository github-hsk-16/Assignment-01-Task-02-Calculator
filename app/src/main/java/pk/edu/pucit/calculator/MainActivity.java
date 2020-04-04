package pk.edu.pucit.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity{

    String input_equation = "";
    String output_result = "";
    String eq = "0";

    Map<Integer, String> idToStringMap = new HashMap<Integer, String>();

    TextView o, i;
    Boolean isDecimalAllow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        o = findViewById(R.id.tv_result);
        i = findViewById(R.id.tv_equation);

        idToStringMap.put(R.id.Double_Zero_Num, "00");
        idToStringMap.put(R.id.Zero_Num, "0");
        idToStringMap.put(R.id.One_Num, "1");
        idToStringMap.put(R.id.Two_Num, "2");
        idToStringMap.put(R.id.Three_Num, "3");
        idToStringMap.put(R.id.Four_Num, "4");
        idToStringMap.put(R.id.Five_Num, "5");
        idToStringMap.put(R.id.Six_Num, "6");
        idToStringMap.put(R.id.Seven_Num, "7");
        idToStringMap.put(R.id.Eight_Num, "8");
        idToStringMap.put(R.id.Nine_Num, "9");

        idToStringMap.put(R.id.Dot_Operator, ".");
        idToStringMap.put(R.id.Equal_Operator, "=");
        idToStringMap.put(R.id.Minus_Operator, "-");
        idToStringMap.put(R.id.Plus_Operator, "+");
        idToStringMap.put(R.id.Mul_Operator, "*");
        idToStringMap.put(R.id.Del_Operator, "del");
        idToStringMap.put(R.id.AC_Operator, "AC");
        idToStringMap.put(R.id.Percent_Operator, "%");
        idToStringMap.put(R.id.Div_Operator, "/");


        View.OnClickListener addElement = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ch = idToStringMap.get(v.getId());
                if (ch == "1" || ch == "2" || ch == "3" || ch == "4" || ch == "5" || ch == "6" || ch == "7" || ch == "8" || ch == "9" || ch == "0" || ch == "00" || ch == ".")
                {
                    char lastCh = eq.charAt(eq.length() - 1);
                    if (lastCh == '=')
                    {
                        eq = "0";
                        output_result = "";
                        o.setText(output_result);
                    }
                    if (ch == ".")
                    {
                        if (isDecimalAllow == true)
                        {
                            if (lastCh == '+' || lastCh == '-' || lastCh == '*' || lastCh == '/' || lastCh == '%')
                            {
                                eq += "0";
                            }
                            if (eq == "0")
                            {
                                eq = "0.";
                            }
                            else
                            {
                                eq += ch;
                            }
                            isDecimalAllow = false;
                        }
                    }
                    else
                    {
                        if (eq == "0" && ch != "0")
                        {
                            eq = ch;
                        }
                        else
                        {
                            eq += ch;
                        }
                    }
                }
                else if (ch == "+" || ch == "-" || ch == "*" || ch == "/" || ch == "%")
                {
                    char lastCh = eq.charAt(eq.length() - 1);
                    if (lastCh == '=')
                    {
                        eq = output_result;
                        output_result = "";
                        o.setText(output_result);
                    }

                    if (lastCh == '+' || lastCh == '-' || lastCh == '*' || lastCh == '/' || lastCh == '%')
                    {
                        char secLastCh = eq.charAt(eq.length() - 2);
                        if (lastCh == '-' && (secLastCh == '*' || secLastCh == '/'))
                        {
                            if (ch == "+" || ch == "-" || ch == "*" || ch == "/")
                            {
                                eq = eq.substring(0, eq.length() - 2) + ch;
                            }
                        }
                        else if (lastCh == '%')
                        {
                            if (ch == "+" || ch == "-" || ch == "*" || ch == "/")
                            {
                                eq += ch;
                            }
                        }
                        else if (lastCh == '/' || lastCh == '*' && ch == "-")
                        {
                            eq += ch;
                        }
                        else
                        {
                            eq = eq.substring(0, eq.length() - 1) + ch;
                        }
                    }
                    else
                    {
                        eq += ch;
                    }
                    if (isDecimalAllow == false)
                    {
                        isDecimalAllow = true;
                    }
                }
                else if (ch == "del" || ch == "AC" || ch == "=")
                {
                    if (ch == "del" && eq.length() > 0)
                    {
                        eq = eq.substring(0, eq.length() - 1);
                        if (eq.length() == 0)
                        {
                            eq = "0";
                        }
                    }
                    else if (ch == "AC")
                    {
                        eq = "0";
                        output_result = "";
                        o.setText(output_result);
                    }
                    else
                    {
                        Expression e = new Expression(eq);
                        output_result = String.valueOf(e.calculate());
                        o.setText(output_result);
                        eq += ch;
                    }

                    if (isDecimalAllow == false)
                    {
                        isDecimalAllow = true;
                    }
                }

                if (ch == "=")
                {
                    i.setText(eq.substring(0, eq.length() - 1));
                }
                else
                {
                    i.setText(eq);
                }
            }
        };

        findViewById(R.id.Double_Zero_Num).setOnClickListener(addElement);
        findViewById(R.id.Zero_Num).setOnClickListener(addElement);
        findViewById(R.id.One_Num).setOnClickListener(addElement);
        findViewById(R.id.Two_Num).setOnClickListener(addElement);
        findViewById(R.id.Three_Num).setOnClickListener(addElement);
        findViewById(R.id.Four_Num).setOnClickListener(addElement);
        findViewById(R.id.Five_Num).setOnClickListener(addElement);
        findViewById(R.id.Six_Num).setOnClickListener(addElement);
        findViewById(R.id.Seven_Num).setOnClickListener(addElement);
        findViewById(R.id.Eight_Num).setOnClickListener(addElement);
        findViewById(R.id.Nine_Num).setOnClickListener(addElement);

        findViewById(R.id.Dot_Operator).setOnClickListener(addElement);
        findViewById(R.id.Plus_Operator).setOnClickListener(addElement);
        findViewById(R.id.Minus_Operator).setOnClickListener(addElement);
        findViewById(R.id.Mul_Operator).setOnClickListener(addElement);
        findViewById(R.id.Div_Operator).setOnClickListener(addElement);
        findViewById(R.id.Percent_Operator).setOnClickListener(addElement);
        findViewById(R.id.Equal_Operator).setOnClickListener(addElement);
        findViewById(R.id.AC_Operator).setOnClickListener(addElement);
        findViewById(R.id.Del_Operator).setOnClickListener(addElement);
    }
}