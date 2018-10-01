/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Effects;

import com.Gr8aX.RighteousKnight.Entities.Mob;
import com.Gr8aX.RighteousKnight.Entities.Player;
import com.Gr8aX.RighteousKnight.Game;

/**
 *
 * @author tiago
 */
public class SlowEffect implements Effect {

    private int counter;
    private int maxcounter;
    private boolean infinite, running;
    private double effectDelay = 1, effectStrength;
    private double inicial;

    public SlowEffect(int maxcounter, double effectStrength, boolean infinite) {
        this.maxcounter = maxcounter;
        this.counter = 0;
        this.infinite = infinite;
        this.effectStrength = effectStrength;
    }

    @Override
    public void doEffect(Mob p) {
        if (counter % (effectDelay * Game.tpsT) == 0) {

        }
    }

    @Override
    public void exitEffect(Mob p) {
        
            p.addDelayX(inicial);
        
        counter = 0;
        running = false;
        p.removeEffect(this);
    }

    @Override
    public void enterEffect(Mob p) {
        running = true;
        inicial = -p.getVxDelay()/effectStrength;
            p.addDelayX(p.getVxDelay()/effectStrength);
        
    }

    @Override
    public void tick(Mob p) {
        if (running) {
            counter++;
            if (counter < (maxcounter * Game.tpsT) || infinite) {
                doEffect(p);
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Acabou o efeito chamado: " + getName() + ", que durou: " + counter);
                exitEffect(p);
            }
        }
    }

    @Override
    public String getName() {
        return "Lentidão";
    }

}
