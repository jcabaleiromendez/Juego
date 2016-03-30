package com.example.jesus.juego;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;


public class GameConfig extends ActionBarActivity {
    private static final int ACTION_PLAY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_config);

        // botón de inicio de partida
        Button btn = (Button) findViewById(R.id.startBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startPlay();
            }
        });
        // control número de celdas horizontales
        SeekBar xTiles = (SeekBar) findViewById(R.id.seekBarX);
        xTiles.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        updateXTiles(seekBar.getProgress());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
        updateXTiles(xTiles.getProgress());

        // barra para las celdas verticales
        SeekBar yTiles = (SeekBar) findViewById(R.id.seekBarY);
        yTiles.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        updateYTiles(seekBar.getProgress());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
        updateYTiles(yTiles.getProgress());
// barra para las tramas
        SeekBar colors = (SeekBar) findViewById(R.id.seekBarColors);
        colors.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        updateColors(seekBar.getProgress());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
        updateColors(colors.getProgress());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m_player:
                showPlayer();
                return true;
            case R.id.m_howto:
                showHowTo();
                return true;
            case R.id.m_about:
                showAbout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void startPlay() {
        Intent i = new Intent(this, GameField.class);
//configurar la partida
        SeekBar sb = (SeekBar) findViewById(R.id.seekBarX);
        i.putExtra("xtiles", sb.getProgress());
        sb = (SeekBar) findViewById(R.id.seekBarY);
        i.putExtra("ytiles", sb.getProgress());

        sb = (SeekBar) findViewById(R.id.seekBarColors);
        i.putExtra("numcolors", sb.getProgress());

        RadioButton r = (RadioButton) findViewById(R.id.radioButtonC);
        i.putExtra("tile", r.isChecked() ? "C" : "N");
        //control del sonido
        CheckBox chSound = (CheckBox) findViewById(R.id.checkBoxSound);
        i.putExtra("hasSound", chSound.isChecked());
        // control de la vibración
        CheckBox chVib = (CheckBox) findViewById(R.id.checkBoxVibrate);
        i.putExtra("hasVibration", chVib.isChecked());
        //comenzar activity
        startActivityForResult(i, ACTION_PLAY);


    }

    private void updateXTiles(int progress) {
        TextView tv = (TextView) findViewById(R.id.seekBarXtxt);
        tv.setText(getString(R.string.num_elem_x) + (progress + 3));
    }

    private void updateYTiles(int progress) {
        TextView tv = (TextView) findViewById(R.id.seekBarYtxt);
        tv.setText(getString(R.string.num_elem_y) + (progress + 3));
    }

    private void updateColors(int progress) {
        TextView tv = (TextView) findViewById(R.id.seekBarColorstxt);
        tv.setText(getString(R.string.num_colors) + (progress + 2));
    }

    private void showAbout() {
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }

    private void showHowTo() {
        Intent i = new Intent(this, HowTo.class);
        startActivity(i);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTION_PLAY:
                    new AlertDialog.Builder(this).setMessage(String.format(getResources().getString(R.string.game_end_1),data.getIntExtra("clicks", 0)) )
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showPlayer() {

    }

}