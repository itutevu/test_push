package com.example.valkyzone.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valkyzone.myapplication.R;
import com.example.valkyzone.myapplication.model.Contact;

import java.util.List;

/**
 * Created by Valkyzone on 3/12/2018.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private List<Contact> arrayContact;

    public ContactAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource=resource;
        this.arrayContact=objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false);
                viewHolder.imgAvatar = (ImageView) convertView.findViewById(R.id.img_avatar);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.txt_name);
                viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.txt_phonenum);

                convertView.setTag(viewHolder);

            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Contact contact = arrayContact.get(position);

            viewHolder.tvName.setText(contact.getName());
            viewHolder.tvNumber.setText(contact.getPhonenum());

            if(contact.isMale()){
                viewHolder.imgAvatar.setBackgroundResource(R.drawable.ic_launcher_background);
            }else {
                viewHolder.imgAvatar.setBackgroundResource(R.drawable.ic_launcher_background);
            }

            return convertView;
        }
    public class ViewHolder{
        ImageView imgAvatar;
        TextView tvName;
        TextView tvNumber;
    }

}
