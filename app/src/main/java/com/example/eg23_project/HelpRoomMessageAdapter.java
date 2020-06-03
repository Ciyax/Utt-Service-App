package com.example.eg23_project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HelpRoomMessageAdapter extends BaseAdapter {

    private List<HelpRoomMessage> helpRoomMessages = new ArrayList<HelpRoomMessage>();
    private Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(HelpRoomMessage helpRoomMessage) {
        this.helpRoomMessages.add(helpRoomMessage);
        notifyDataSetChanged(); // to render the list we need to notify
    }

    @Override
    public int getCount() {
        return helpRoomMessages.size();
    }

    @Override
    public Object getItem(int i) {
        return helpRoomMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        HelpRoomMessage helpRoomMessage = helpRoomMessages.get(i);

        if (helpRoomMessage.isBelongsToCurrentUser()) { // this helpRoomMessage was sent by us so let's create a basic chat bubble on the right
            convertView = messageInflater.inflate(R.layout.fragment_request_my_message, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(helpRoomMessage.getText());
        } else { // this helpRoomMessage was sent by someone else so let's create an advanced chat bubble on the left
            convertView = messageInflater.inflate(R.layout.fragment_request_their_message, null);
            holder.avatar = (View) convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);

            holder.name.setText(helpRoomMessage.getMemberData().getName());
            holder.messageBody.setText(helpRoomMessage.getText());
            GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
            drawable.setColor(Color.parseColor(helpRoomMessage.getMemberData().getColor()));
        }

        return convertView;
    }

}

class MessageViewHolder {
    public View avatar;
    public TextView name;
    public TextView messageBody;
}