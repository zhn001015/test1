package com.example.firsttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_0 ;
    Button btn_1;
    Button btn_2;
    Button btn_3 ;
    Button btn_4 ;
    Button btn_5 ;
    Button btn_6 ;  //数字按钮
    Button btn_7 ;
    Button btn_8 ;
    Button btn_9 ;
    Button btn_point ;  //小数点按钮
    Button btn_clear ;
    Button btn_del ;
    Button btn_add ;
    Button btn_sub ;
    Button btn_multiply ;
    Button btn_divide ;
    Button btn_equle ;
    EditText text_1 ;
    boolean clr_flag; ;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity activity = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_clear=(Button) findViewById(R.id.button_clear);
        btn_divide=(Button) findViewById(R.id.button_div);
        btn_multiply=(Button) findViewById(R.id.button_mul);
        btn_del=(Button) findViewById(R.id.button_del);
        btn_7=(Button) findViewById(R.id.button_7);
        btn_8=(Button) findViewById(R.id.button_8);
        btn_9=(Button) findViewById(R.id.button_9);
        btn_sub=(Button) findViewById(R.id.button_sub);
        btn_4=(Button) findViewById(R.id.button_4);
        btn_5=(Button) findViewById(R.id.button_5);
        btn_6=(Button) findViewById(R.id.button_6);
        btn_add=(Button) findViewById(R.id.button_add);
        btn_1=(Button) findViewById(R.id.button_1);
        btn_2=(Button) findViewById(R.id.button_2);
        btn_3=(Button) findViewById(R.id.button_3);

        btn_0=(Button) findViewById(R.id.button_0);
        btn_point=(Button) findViewById(R.id.button_point);
        btn_equle=(Button) findViewById(R.id.button_equ);
        text_1 = (EditText)findViewById(R.id.text_1);

        btn_clear.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);

        btn_0.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equle.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        String str = text_1.getText().toString();
        switch (v.getId()){
            case   R.id.button_0:
            case   R.id.button_1:
            case   R.id.button_2:
            case   R.id.button_3:
            case   R.id.button_9:
            case   R.id.button_4:
            case   R.id.button_8:
            case   R.id.button_5:
            case   R.id.button_6:
            case   R.id.button_7:
            case   R.id.button_point:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    text_1.setText("");
                }
                text_1.setText(str+((Button)v).getText());
                break;
            case R.id.button_add:
            case R.id.button_sub:
            case R.id.button_mul:
            case R.id.button_div:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    text_1.setText("");
                }
                if(str.contains("+")||str.contains("-")||str.contains("*")||str.contains("÷")) {
                    str=str.substring(0,str.indexOf(" "));//index用于查找操作符位置
                }
                text_1.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.button_clear:
                if(clr_flag)
                    clr_flag=false;
                str="";
                text_1.setText("");
                break;
            case R.id.button_del: //判断是否为空，然后在进行删除
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    text_1.setText("");
                }
                else if(str!=null&&!str.equals("")){
                    text_1.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.button_equ: //单独运算最后结果
                getResult();//调用下面的方法
                break;
        }
    }

    private void getResult() {
        String exp=text_1.getText().toString();
        if(exp==null||exp.equals("")) return ;
        //因为没有运算符所以不用运算
        if(!exp.contains(" ")){
            return ;
        }
        if(clr_flag){
            clr_flag=false;
            return;
        }
        clr_flag=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        //转换为char数组类型用于小数点判断
        int point=0;
        for(int i=0;i<chars1.length;i++){
            if(chars1[i]=='.'){
                point++;
                if(point>1){
                    text_1.setText("不规范！");
                }
            }
        }
        for(int i=0;i<chars2.length;i++){
            if(chars2[i]=='.'){
                point++;
                if(point>1){
                    text_1.setText("不规范！");
                }
            }
        }
        double cnt=0;
        //s1、s2都不为空的情况
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("*")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0)
                    text_1.setText("WRONG NUMBER!!!");
                else cnt=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                text_1.setText(res+"");
            }else {
                text_1.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("*")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s1.contains(".")) {
                int res = (int) cnt;
                text_1.setText(res+"");
            }else {
                text_1.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("*")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                text_1.setText(res+"");
            }else {
                text_1.setText(cnt+"");}
        }
        else {
            text_1.setText("");
        }
    }
}



