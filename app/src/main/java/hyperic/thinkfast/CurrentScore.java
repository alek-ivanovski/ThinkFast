package hyperic.thinkfast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class CurrentScore extends Activity {

    public void initBtn(){
        Button PlayAgain = (Button) findViewById(R.id.PlayAgain);
        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent game = new Intent(CurrentScore.this, game.class);
                startActivity(game);
                finish();
            }
        });

        Button MainMenuButton = (Button) findViewById(R.id.MainMenuButton);
        MainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu = new Intent(CurrentScore.this, MainMenu.class);
                startActivity(menu);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_current_score);

        Integer score = getIntent().getIntExtra("score", -100);
        TextView ScoreValue = (TextView) findViewById(R.id.ScoreValue);
        ScoreValue.setText(score.toString());

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        Integer HighScore = settings.getInt("HIGH_SCORE", 0);

        if(score > HighScore){
            SharedPreferences.Editor editor = settings.edit();
            HighScore = score;
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }

        TextView HighScoreTextView = (TextView) findViewById(R.id.HighScore);
        HighScoreTextView.setText(HighScore.toString());
        initBtn();
    }

}
