package cpixels.com.firebasedbdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference dbReference;
    private DatabaseReference userReference;

    private Button btnWrite, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWrite = findViewById(R.id.btn_write);
        btnWrite.setOnClickListener(this);

        btnRead = findViewById(R.id.btn_read);
        btnRead.setOnClickListener(this);

        dbReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View v) {
        if (v == btnWrite) {
            User user = new User("Alpesh", "apr.alpesh@gmail.com");
            dbReference.child("users").child("1").setValue(user);
        } else if (v == btnRead) {
            userReference = FirebaseDatabase.getInstance().getReference().child("users").child("1");

            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    Log.w("userName:", user.username);
                    Log.w("email:", user.email);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            userReference.addValueEventListener(postListener);
        }
    }
}
