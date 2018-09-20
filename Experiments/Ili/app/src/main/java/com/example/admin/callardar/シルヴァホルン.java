package com.example.admin.callardar;

/**
 * This is a possible ex.
 *
 * 	Point p1 = new Point(550,400);
 *	Point p2 = new Point(750, 400);
 *
 *	Point p3 = new Point(700, 600);
 *	Point p4 = new Point(500, 600);
 *
 *	Point[] pArr = {p1,p2,p3,p4};
 *
 *  注意: p1 -> p2 -> p3 -> p4 can NOT be intersect
 *
 *	シルヴァホルン sh = new シルヴァホルン(pArr);
 *
 *	if(sh.if_Exist(new Point(arg0.getX(),arg0.getY())))
 *	{
 *		do something
 *	}
 *
 **/

public class シルヴァホルン
{
    private final Point[] pArr;

    public シルヴァホルン(Point[] pArr)
    {
        this.pArr = pArr;

        for(int i=0;i<pArr.length - 1;i++)
        {
            pArr[i].revise_Next_Point(pArr[i + 1]);
        }

        pArr[pArr.length - 1].revise_Next_Point(pArr[0]);
    }

    public boolean if_Exist(Point point_Check)
    {
        boolean boolean_up = false;
        boolean boolean_down = false;
        boolean boolean_left = false;
        boolean boolean_right = false;

        for(int i=0;i<pArr.length;i++)
        {
            boolean[] boolean_ALL = pArr[i].getFunction(point_Check);

            if(! boolean_up && boolean_ALL[0])
            {
                boolean_up = true;
            }

            if(! boolean_down && boolean_ALL[1])
            {
                boolean_down = true;
            }

            if(! boolean_left && boolean_ALL[2])
            {
                boolean_left = true;
            }

            if(! boolean_right && boolean_ALL[3])
            {
                boolean_right = true;
            }
        }

        return boolean_up && boolean_down && boolean_left && boolean_right;
    }
}
