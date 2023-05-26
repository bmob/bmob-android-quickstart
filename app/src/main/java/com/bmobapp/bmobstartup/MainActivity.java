package com.bmobapp.bmobstartup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    Button addBt,getBt,editBt,delBt;

    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //把这里的application id变更为你自己的application id
        //Bmob.initialize方法最好放在新建的继承自Application的类中，这样可以在整个项目的所有地方使用
        Bmob.initialize(this,"3315840d184f17036594d2f265ba39e0");

        addBt = findViewById(R.id.add_bt);
        getBt = findViewById(R.id.get_bt);
        editBt = findViewById(R.id.edit_bt);
        delBt = findViewById(R.id.del_bt);

        //添加数据
        addBt.setOnClickListener(v->{
            Person p = new Person();
            p.name = "Bmob后端云";
            p.address = "广州番禺区小谷围青蓝街22号创业楼5楼比目科技";
            p.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if(e==null){
                        id = objectId;
                        Toast.makeText(v.getContext(),"添加成功，这条数据的objectId是："+objectId,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        });

        //查询数据
        getBt.setOnClickListener(v->{
            BmobQuery<Person> bmobQuery =new BmobQuery<>();
            bmobQuery.getObject(id, new QueryListener<Person>() {
                @Override
                public void done(Person person, BmobException e) {
                    if(e==null){
                        Toast.makeText(v.getContext(),"name:"+person.name + " address:"+person.address,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        });

        //编辑数据
        editBt.setOnClickListener(v->{
            Person p = new Person();
            p.name = "比目科技";
            p.update(id, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        Toast.makeText(v.getContext(),"修改成功",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        });

        //删除数据
        delBt.setOnClickListener(v->{
            Person p = new Person();
            p.setObjectId(id);
            p.delete(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        Toast.makeText(v.getContext(),"删除成功",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        });
    }
}