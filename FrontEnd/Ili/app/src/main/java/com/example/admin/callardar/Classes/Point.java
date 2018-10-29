package com.example.admin.callardar.Classes;

public class Point
{
    public final int x;
    public final int y;
    private Point next;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void revise_Next_Point(Point p2)
    {
        next = p2;
    }

    public Point Next()
    {
        return next;
    }

    /**
     *  up down left right
     * @param point_Check
     * @return
     * 	是否获取
     */

    public boolean[] getFunction(Point point_Check)
    {
        boolean[] bArr = new boolean[4];

        double k = 0;
        double b = 0;
        double position_X = 0;
        double position_Y = 0;

        //优先处理是一条直线的情况
        if( x - next.x == 0)
        {
            position_X = x;
        }
        else if( y - next.y == 0)
        {
            position_Y = y;
        }
        else
        {
            k = (double)( y - next.y ) / (double)( x - next.x );
            b = (double)y - (double)x * k;
            position_X = ( point_Check.y - b ) / k;
            position_Y = k * point_Check.x + b;
        }

        boolean bound_X = (x <= point_Check.x && point_Check.x <= next.x) || (next.x <= point_Check.x && point_Check.x <= x);
        boolean bound_Y = (y <= point_Check.y && point_Check.y <= next.y) || (next.y <= point_Check.y && point_Check.y <= y);

        //如果在线上则直接返回true
        if(point_Check.x == position_X && bound_Y || point_Check.y == position_Y && bound_X)
        {
            return new boolean[]{true,true,true,true};
        }

        if(point_Check.x < position_X && bound_Y)
        {
            bArr[3] = true;// →
        }
        else if(point_Check.x > position_X && bound_Y)
        {
            bArr[2] = true;// ←
        }

        if(point_Check.y > position_Y && bound_X)
        {
            bArr[1] = true;// ↑
        }
        else if(point_Check.y < position_Y && bound_X)
        {
            bArr[0] = true;// ↓
        }

        return bArr;
    }

    @Override
    public boolean equals(Object o)
    {
        Point p2 = (Point) o;

        return x == p2.x && y == p2.y;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}