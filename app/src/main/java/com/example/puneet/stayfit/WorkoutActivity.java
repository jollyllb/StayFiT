package com.example.puneet.stayfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class WorkoutActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    private TextView textViewWish;
    private TextView textViewWorkout;
    private Button dashboard;
    private Button fitnesssearch;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser fbuser = firebaseAuth.getCurrentUser();
        textViewWish=(TextView) findViewById(R.id.textViewWish);
        textViewWorkout=(TextView) findViewById(R.id.textViewWorkout);
        textViewWorkout.setMovementMethod(new ScrollingMovementMethod());
        dashboard=(Button) findViewById(R.id.dashboard);
        dashboard.setOnClickListener(this);
        fitnesssearch=(Button) findViewById(R.id.fitnesssearch);
        fitnesssearch.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users/"+fbuser.getUid());
        databaseReference.addValueEventListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == fitnesssearch){

            finish();
            //starting login activity
            startActivity(new Intent(this, MapsActivity.class));
        }

        else if (view == dashboard){
            Intent i=new Intent(getApplicationContext(),DashboardActivity.class);
            startActivity(i);
        }

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        try {
            HashMap<String, Object> objData = (HashMap<String, Object>) dataSnapshot.getValue();
            Calendar c = Calendar.getInstance();
            String today;
            double hour = c.get(Calendar.HOUR_OF_DAY);
            int day = c.get(Calendar.DAY_OF_WEEK);


            // String mycategory = objData.get("category").toString();

            textViewWish.setText("Good luck " + objData.get("name").toString());

            if (objData.get("category").toString().equals("UnderWeight")) {

                if (day == 1) {
                    today = "Sunday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Rest up and get ready to build it again next week.");
                }

                else if (day == 2) {
                    today = "Monday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Do Total-Body Strength Training");
                }

                else if (day == 3) {
                    today = "Tuesday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "And it is your rest day");
                }

                else if (day == 4) {
                    today = "Wednesday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Do Total-Body Strength Training");
                }

                else if (day == 5) {
                    today = "Thursday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "And it is your rest day");
                }

                else if (day == 6) {
                    today = "Friday";
                    textViewWorkout.setText("Today is " + today + "\n" + "It is Yoga, Zumba or Aerobics day");
                }

                else {
                    today = "Saturday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "And it is your party day");
                }

            }

            else if (objData.get("category").toString().equals("Normal")) {

                if (day == 1) {
                    today = "Sunday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Rest up and get ready to build it again next week.");
                }

                else if (day == 2) {
                    today = "Monday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Do Total-Body Strength Training");
                }

                else if (day == 3) {
                    today = "Tuesday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "It is Yoga Day");
                }

                else if (day == 4) {
                    today = "Wednesday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Do Total-Body Strength Training");
                }

                else if (day == 5) {
                    today = "Thursday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "And it is Zumba Day");
                }

                else if (day == 6) {
                    today = "Friday";
                    textViewWorkout.setText("Today is " + today + "\n" + "It is Yoga And Aerobics day");
                }

                else {
                    today = "Saturday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "And it is your party day");
                }

            }

            else if (objData.get("category").toString().equals("Over Weight")) {

                if (day == 1) {
                    today = "Sunday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Rest up and get ready to crush it again next week.");
                }

                else if (day == 2) {
                    today = "Monday";
                    textViewWorkout.setText("Today is " + today + "\n" + "1. Leg Raise\n" +
                            "2. Plank\n" +
                            "3. Hip Extensions left side\n" +
                            "4. Hip Extensions right side\n" +
                            "5. March in Place");
                }

                else if (day == 3) {
                    today = "Tuesday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "1. Military Press\n" +
                            "2. Plié/Sumo Squat\n" +
                            "3. Stiff Legged Deadlift with Dumbbells\n" +
                            "4. Foot to Foot Crunches\n" +
                            "5. High Knees in Place");
                }

                else if (day == 4) {
                    today = "Wednesday";
                    textViewWorkout.setText("Today is " + today + "\n" + "1. Goblet Squat\n" +
                            "2. Knee Touches in Place\n" +
                            "3. Tricep Kickbacks\n" +
                            "4. Rear Leg Extension left leg\n" +
                            "5. Rear Leg Extension right leg");
                }

                else if (day == 5) {
                    today = "Thursday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "Rest Day – Take a brisk 30 minute walk");
                }

                else if (day == 6) {
                    today = "Friday";
                    textViewWorkout.setText("Today is " + today + "\n" + "1. March in Place\n" +
                            "2. Traditional Crunch\n" +
                            "3. Chair Squat\n" +
                            "4. Wall Push-Up\n" +
                            "5. Bodyweight Glute Bridge");
                }

                else {
                    today = "Saturday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "1. Toe Reach\n" +
                            "2. Alternating Lunges\n" +
                            "3. Lying Oblique Twist\n" +
                            "4. Body Weight Squat\n" +
                            "5. High Knees in Place");
                }

            }

            else if (objData.get("category").toString().equals("Obese")) {

                if (day == 1) {
                    today = "Sunday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Rest up and get ready to crush it again next week.");
                }

                else if (day == 2) {
                    today = "Monday";
                    textViewWorkout.setText("Today is " + today + "\n" + "10 high knees\n" +
                            "10 heel kicks\n" +
                            "10 calf raises\n" +
                            "10 tuck jumps\n" +
                            "10 walking lunges\n" +
                            "10 squats\n" +
                            "10 push ups\n" +
                            "10 leg raises\n" +
                            "10 crunches");
                }

                else if (day == 3) {
                    today = "Tuesday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "Jumping Jacks (Total Body): 20\n" +
                            "Wall sit (Lower Body): 30 seconds\n" +
                            "Push-up (Upper Body): 10\n" +
                            "Abdominal crunch (Core): 20\n" +
                            "Step-up onto chair (Total Body): 10\n" +
                            "Squat (Lower Body): 10\n" +
                            "Triceps dip on chair (Upper Body): 10\n" +
                            "Plank (Core): 30 seconds\n" +
                            "High knees running in place (Total Body): 20");
                }

                else if (day == 4) {
                    today = "Wednesday";
                    textViewWorkout.setText("Today is " + today + "\n" + "Warm up with a short jog / jogging on the spot for a few minutes to get a warmed up.\n" +
                            "\n" +
                            "20 calf raises\n" +
                            "20 push ups\n" +
                            "20 back extensions\n" +
                            "30 second plank\n" +
                            "20 mountain climbers");
                }

                else if (day == 5) {
                    today = "Thursday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "Rest Day – Take a brisk 30 minute walk");
                }

                else if (day == 6) {
                    today = "Friday";
                    textViewWorkout.setText("Today is " + today + "\n" + "20 jumping jacks\n" +
                            "20 high knees\n" +
                            "20 heel kicks\n" +
                            "20 squats\n" +
                            "10 push ups\n" +
                            "10 back extensions\n" +
                            "10 leg raises\n" +
                            "10 bicycle crunches");
                }

                else {
                    today = "Saturday";
                    textViewWorkout.setText("Today is " + today+ "\n" + "Today’s workout will be simple, just two exercises. But these exercises will be done 200 times. Burpees and crunches!\n" +
                            "\n" +
                            "10 Burpees (with a push up)\n" +
                            "10 Crunches");
                }

            }
        }
        catch(NullPointerException exnu){
            exnu.printStackTrace();
        }
        catch(Exception ex){

        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
