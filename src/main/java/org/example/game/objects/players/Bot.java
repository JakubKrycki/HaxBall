package org.example.game.objects.players;

import org.example.game.objects.ball.Ball;

import java.awt.*;

public class Bot extends Player{

    public Bot(float x, float y, char side, Color color) {
        super(x, y, side, color);
    }

    @Override
    public void move(Player active, Player enemy, Ball ball, Rectangle boundaries, Rectangle goalBoundaries){
        //bot1(ball); // bot1
        bot2(ball, enemy, goalBoundaries);
        //bot3(ball, enemy, goalBoundaries); // bot2
        if(enemy.distanceFromPoint(getXCoord()+getXVector(), getYCoord()) >= getR() + enemy.getR())
            setXCoord(checkX((int) (getXCoord() + getXVector())));
        if(enemy.distanceFromPoint(getXCoord(), getYCoord()+getYVector()) >= getR() + enemy.getR())
            setYCoord(checkY((int) (getYCoord() + getYVector())));
        checkNotHittingSoccerGoal((int)getXCoord(),(int)getYCoord());
    }

    public void bot1(Ball ball){//always to ball
        toBall(ball);
    }

    public void bot2(Ball ball, Player enemy, Rectangle goalBoundaries){//always near goal line
        toGoalLine(ball, enemy, goalBoundaries);
    }

    public void bot3(Ball ball, Player enemy, Rectangle goalBoundaries){//check distance and decide to go to ball or stay near goal line
        if(distance(ball)-enemy.distance(ball) > 0.5*getR()){//TODO fix
            toGoalLine(ball, enemy, goalBoundaries);
        }else
            toBall(ball);
    }

    public void toBall(Ball ball){
        float x = getSignOfNumber(ball.getXCoord() - getXCoord());
        float y = getSignOfNumber(ball.getYCoord() - getYCoord());
        setXVector(x*5);
        setYVector(y*5);
    }

    public void toGoalLine(Ball ball, Player enemy, Rectangle goalBoundaries){
        float xS = (getSide() == 'L')? goalBoundaries.x : goalBoundaries.x+goalBoundaries.width;
        float yS = (float)(goalBoundaries.y+0.5*goalBoundaries.height);
        double dist = distanceFromPoint(xS, yS);
        float a = (yS - ball.getYCoord())/(xS-ball.getXCoord());
        float b = yS - a*xS;
        float tempY = a*getXCoord() + b;
        if(Math.abs(getYCoord()-tempY) >= 5){
            setXVector(0);
            if(getYCoord() > tempY)
                setYVector(-5);
            else
                setYVector(5);
        }else{
            if(getSide() == 'L' && xS + getR() <= getXCoord())
                setXVector(0);
            else if(getSide() == 'R' && xS - getR() >= getXCoord())
                setXVector(0);
            else{
                float xV = xS - getXCoord();
                setXVector(xV * 5 / (float)dist);
            }
            float yV = yS - getYCoord();
            setYVector(yV * 5 / (float)dist);
        }
    }

    /* TODO fourth algorithm
    ustawia się do strzału jeśli jest w odległości <0.5*R od piłki, czyli
    if(distance(ball) < ball.getR() + 1.5*getR())
    //ustawia się do strzału
    //jeśli jest pomiędzy bramką przeciwnika a piłką to musi przejść na drugą stronę, robi to krótszą trasą
    //jeśli piłka jest już pomiędzy graczem a bramką przeciwnika to dąży do Y takiego żeby był na
    //funkcji liniowej piłka-bramka_przeciwnika
    //jeśli będzie w odległości <5 od tego Y to rusza się po prostej gracz-piłka

     */

    /* TODO fourth bot
    bot 3 aż do odległości <0.5*R od piłki
    wtedy fourth algorithm
     */

}
