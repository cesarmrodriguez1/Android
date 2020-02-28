package mx.itesm.sqliteexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.widget.ImageView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.UserViewHolder> {

    //this context we will use to inflate the layout
    private Context context;

    //we are storing all the users in a list
    private ArrayList<User> userList;

    public DataAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_userlayout, null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        //getting the user of the specified position
        User user = userList.get(position);

        //binding the data with the viewholder views
        holder.textViewNickname.setText(user.getNickname());
        holder.textViewPassword.setText(user.getPassword());
        holder.textViewDepartment.setText(String.valueOf(user.getDepartment()));

        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icon));

    }
    @Override
    public int getItemCount()
    {
        return userList.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNickname, textViewPassword, textViewDepartment;
        ImageView imageView;

        public UserViewHolder(View itemView) {
            super(itemView);

            textViewNickname = itemView.findViewById(R.id.textViewNickname);
            textViewPassword = itemView.findViewById(R.id.textViewPassword);
            textViewDepartment = itemView.findViewById(R.id.textViewDepartment);

            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}