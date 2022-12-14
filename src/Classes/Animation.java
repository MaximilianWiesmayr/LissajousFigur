package Classes;

import Interfaces.Globals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Animates multiple Lissajous Figure with the help of a CardLayout
 * @author Sebastian Zettl
 * @version 2022-11-28
 */
public class Animation extends JPanel implements Globals {

    int fx = 4;
    int fy = 4;
    CardLayout cardLayout;
    JPanel cardPanel;

    Figure[] figures = new Figure[360*7*7];

    Timer timer;

    /**
     * Konstruktor für Animation
     */
    public Animation() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        this.setLayout(new BorderLayout());
        this.add(cardPanel);


        for (int phi = 0; phi < figures.length; phi++) {
            figures[phi] = new Figure(phi);
            cardPanel.add(figures[phi]);
        }

        timer = new Timer(Globals.RANGE_SPEED/2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cardPanel);
            }});
        timer.start();
    }

    /**
     * Allows the Waveform to be set
     * @param type The type of Wafeform, this corresponds to the index in the dropdown
     */
    public void setWaveform(int type) {
        LissajousFigur lf = new LissajousFigur();
        switch (type){
            case 1:
                lf = new LissajousFigurSquare();
                break;
            case 2:
                lf = new LissajousFigurTriangle();
                break;
            case 3:
                lf = new LissajousFigurSaw();
                break;
        }
        for (int phi = 0; phi <figures.length; phi++) {
            figures[phi].setWaveform(lf);
        }


    }

    /**
     * Setter for fx
     * @param fx new fx value
     */
    public void setFX(int fx){
        this.fx = fx;
    }

    /**
     * Setter for fy
     * @param fy new fy Value
     */
    public void setFY(int fy){
        this.fy = fy;
    }

    /**
     * Sets the Speed of the Animation
     * @param delay The new Speed (In Milliseconds)
     */
    public void setDelay(int delay){
        timer.setDelay(delay);
        if (delay == Globals.RANGE_SPEED) {
            timer.stop();
        } else {
            timer.restart();
        }
    }
}
