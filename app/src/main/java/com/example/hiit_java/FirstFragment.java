package com.example.hiit_java;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.hiit_java.databinding.FragmentFirstBinding;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    ImageView imageView1;

private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {

            List exerciseList = new ArrayList() {{
                add(R.drawable.exercise_1);
                add(R.drawable.exercise_2);
                add(R.drawable.exercise_3);
                add(R.drawable.exercise_4);
                add(R.drawable.exercise_5);
                add(R.drawable.exercise_6);
                add(R.drawable.exercise_7);
                add(R.drawable.exercise_8);
                add(R.drawable.exercise_9);
                add(R.drawable.exercise_10);
            }};

            public boolean isRunning = false;
            public int exercise_time = 10;
            public int rest_time = 5;
            public int counter = rest_time;
            public int exercise;
            public String workout;
            public int total_exercises = exerciseList.size();
            public int total_time = (total_exercises + 1) * (exercise_time + rest_time);

            @Override
            public void onClick(View view) {
                //NavHostFragment.findNavController(FirstFragment.this)
                //        .navigate(R.id.action_FirstFragment_to_SecondFragment);

                if (isRunning == false) {

                    imageView1 = getView().findViewById(R.id.imgExercise);
                    TextView myText = getView().findViewById(R.id.textview_first);
                    exercise = 1;

                    workout = "REST \n";
                    isRunning = true;


                    new CountDownTimer(total_time * 1000, 1000) {

                        public void onTick(long millisUntilFinished) {

                            myText.setText(workout + String.valueOf(counter));
                            imageView1.setImageResource((Integer) exerciseList.get((exercise-1)/2));

                            if (counter==0) {
                                exercise++;
                                if (exercise % 2 == 1) {
                                    workout = "REST \n";
                                    counter = rest_time;
                                } else {
                                    workout = "PUSH HARD\nEXERCISE " + (exercise / 2) + "\n";
                                    counter = exercise_time;
                                }
                            } else {
                                counter--;
                            }
                        }
                        public void onFinish() {
                            myText.setText("FINISHED!!");
                            isRunning = false;
                        }
                    }.start();
                }
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}