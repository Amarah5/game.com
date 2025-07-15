package com.grouptsaiko;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Joueur {
    private Direction direction;
    private int x, y;

    public void down(){
        direction = Direction.D;
        y++;
    }
    public void up(){
        direction = Direction.U;
        y--;
    }
    public void left(){
        direction = Direction.L;
        x--;
    }
    public void right(){
        direction = Direction.R;
        x++;
    }

}
