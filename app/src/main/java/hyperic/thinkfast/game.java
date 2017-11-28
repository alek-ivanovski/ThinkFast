package hyperic.thinkfast;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class game extends Activity {

    public Integer sekundi = 60;
    private int c = 300;
    private Integer counter = 0;

    public void setPoeni(){
        TextView score = (TextView) findViewById(R.id.Score);
        score.setText(counter.toString());
    }

    public void playSound(boolean soundType){
        if(soundType){
            final MediaPlayer correctSound = MediaPlayer.create(getBaseContext(), R.raw.correct);
            correctSound.start();
            correctSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    correctSound.release();
                }
            });
        }
        else{
            final MediaPlayer wrongSound = MediaPlayer.create(getBaseContext(), R.raw.wrong);
            wrongSound.start();
            wrongSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    wrongSound.release();
                }
            });
        }
    }

    public void novProblem(int countdown) {

        if (countdown == 1) {
            setPoeni();
        }
        else {
            c = countdown-1;
            Button button = (Button) findViewById(R.id.button);
            TextView zad = (TextView) findViewById(R.id.zad);
            final Brojki tmp = new Brojki();
            zad.setText(tmp.toString());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tmp.getTocnost()) {
                        playSound(true);
                        counter += 2;
                    }
                    else {
                        playSound(false);
                        counter -= 2;
                    }
                        setPoeni();
                        novProblem(c);
                }
            });
            Button no = (Button) findViewById(R.id.no);
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tmp.getTocnost()) {
                        playSound(false);
                        counter -= 2;
                    }
                    else {
                        playSound(true);
                        counter += 2;
                    }
                    setPoeni();
                    novProblem(c);

                }
            });
        }
    }

    private CountDownTimer cdTimer;
    private void kreirajTimer(){
        final TextView time = (TextView) findViewById(R.id.time);
        sekundi = 60;
        cdTimer = new CountDownTimer(60000, 1000){
            public void onTick(long milisUntilFinished){
                sekundi = (int) milisUntilFinished/1000;
                time.setText(sekundi.toString());
            }

            public void onFinish(){
                sekundi = 60;
                cancel();
                finish();
                Intent intent = new Intent(game.this, CurrentScore.class);
                intent.putExtra("score", counter);
                startActivity(intent);
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        counter = 0;
        sekundi = 60;
        c = 300;
        kreirajTimer();
        final MediaPlayer sound = MediaPlayer.create(getBaseContext(), R.raw.correct);
        GenerateNumbers g = new GenerateNumbers();
        Button button = (Button) findViewById(R.id.button);
        TextView zad = (TextView) findViewById(R.id.zad);
        final Brojki tmp = new Brojki();
        zad.setText(tmp.toString());
        novProblem(1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cdTimer.cancel();
    }
}
