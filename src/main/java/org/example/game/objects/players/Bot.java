package org.example.game.objects.players;

import org.example.game.objects.ball.Ball;

import java.awt.*;

public class Bot extends Player{
    int mode;
    public Bot(float x, float y, char side, Color color, int mode) {
        super(x, y, side, color);
        this.mode = mode;
    }

    @Override
    public void move(Player active, Player enemy, Ball ball, Rectangle boundaries, Rectangle goalBoundaries){
        switch (mode) {
            case 1 -> bot1(ball); // bot1
            case 2 -> bot2(ball, enemy, goalBoundaries);
            case 3 -> bot3(ball, enemy, goalBoundaries); // bot2
            case 4 -> bot4(ball, enemy, goalBoundaries); // bot3
        }
        if(enemy.distanceFromPoint(getXCoord()+getXVector(), getYCoord()) >= getR() + enemy.getR())
            setXCoord(checkX((int) (getXCoord() + getXVector())));
        if(enemy.distanceFromPoint(getXCoord(), getYCoord()+getYVector()) >= getR() + enemy.getR())
            setYCoord(checkY((int) (getYCoord() + getYVector())));
        ridOfCollission(enemy);
        checkNotHittingSoccerGoal((int)getXCoord(),(int)getYCoord());
    }

    public void bot1(Ball ball){//always to ball
        toBall(ball);
    }

    public void bot2(Ball ball, Player enemy, Rectangle goalBoundaries){//always near goal line
        toGoalLine(ball, enemy, goalBoundaries);
    }

    public void bot3(Ball ball, Player enemy, Rectangle goalBoundaries){//check distance and decide to go to ball or stay near goal line
        if(distance(ball)>enemy.distance(ball)){
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
        float xS = (getSide() == 'L')? goalBoundaries.x + getR() : goalBoundaries.x+goalBoundaries.width - getR();
        float yS = (float)(goalBoundaries.y+0.5*goalBoundaries.height);
        double dist = distanceFromPoint(xS, yS);
        float a = (yS - ball.getYCoord())/(xS-ball.getXCoord());
        float b = yS - a*xS;
        float tempY = a*getXCoord() + b;
        if(Math.abs(getYCoord()-tempY) >= 5){
            if(getYCoord() > tempY)
                setYVector(-5);
            else
                setYVector(5);
        }else{
            float yV = yS - getYCoord();
            setYVector(yV * 5 / (float)dist);
        }
        setXVector(5*getSignOfNumber(xS - getXCoord()));
    }

    public void toStrike(Ball ball, Rectangle goalBoundaries) {
        int enemyGoalX = getSide() == 'L' ? goalBoundaries.x + goalBoundaries.width: goalBoundaries.x;

        if (between(getXCoord(), ball.getXCoord(), enemyGoalX))
            makeLineWithBall(ball, goalBoundaries);
        else{
            setXVector(5*getSignOfNumber(ball.getXCoord() - getXCoord()));
            setYVector(0);
        }
    }

    public boolean between(float left, float middle, float right){
        return ((left < middle && middle < right) || (left > middle && middle > right));
    }

    public void makeLineWithBall(Ball ball, Rectangle goalBoundaries){
        //tworzymy prostą w dwóch punktów, piłki i gracza, sprawdzamy czy dla goalX wartość y w tej funkcji znajdzie się pomiędzy bramki
        int enemyGoalX = getSide() == 'L' ? goalBoundaries.x + goalBoundaries.width: goalBoundaries.x;
        float a = (getYCoord()-ball.getYCoord())/(getXCoord() - ball.getXCoord());
        float b = getYCoord() - a*getXCoord();
        float y = a*enemyGoalX + b;
        if(y <= goalBoundaries.y)//strzał by był nad bramkę
            setYVector(-5);
        else if(y >= goalBoundaries.y + goalBoundaries.height)//strzał by był nad bramkę
            setYVector(5);
        else
            toBall(ball);
    }

    public void bot4(Ball ball, Player enemy, Rectangle goalBoundaries){
        if(distance(ball)>enemy.distance(ball))
            toGoalLine(ball, enemy, goalBoundaries);
        else if(distance(ball) < ball.getR() + 1.5*getR())
            toStrike(ball, goalBoundaries);
        else
            toBall(ball);
    }
}
