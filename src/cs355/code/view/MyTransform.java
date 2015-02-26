package cs355.code.view;

import java.awt.geom.AffineTransform;

/**
 * Created by Andrew on 2/25/2015.
 */
public class MyTransform extends AffineTransform {

//    Such a coordinate transformation can be represented by a 3 row by 3 column matrix with an implied last row of [ 0 0 1 ].
//    This matrix transforms source coordinates (x,y) into destination coordinates (x',y') by considering them to be a
//    column vector and multiplying the coordinate vector by the matrix according to the following process:
//
//            [ x']   [  m00  m01  m02  ] [ x ]   [ m00x + m01y + m02 ]
//            [ y'] = [  m10  m11  m12  ] [ y ] = [ m10x + m11y + m12 ]
//            [ 1 ]   [   0    0    1   ] [ 1 ]   [         1         ]


//    setTransform(double m00, double m10, double m01, double m11, double m02, double m12)
//    Sets this transform to the matrix specified by the 6 double precision values.

//            [   1    0    tx  ]
//            [   0    1    ty  ]
//            [   0    0    1   ]
    @Override
    public void translate(double x, double y) {
        double[] m = new double[6];
//        getMatrix(double[] flatmatrix)
//        Retrieves the 6 specifiable values in the 3x3 affine transformation matrix and places them into an array of double precisions values.
        this.getMatrix(m);

        this.setTransform(
                //m00
                //m10
                //
                (m[0]),
                (m[1]),
                //m01
                //m11
                //
                (m[2]),
                (m[3]),
                //m02
                //m12
                //
                (m[0] * x + m[2] * y + m[4]),
                (m[1] * x + m[3] * y + m[5])
        );
    }

//            [   cos(theta)    -sin(theta)    0   ]
//            [   sin(theta)     cos(theta)    0   ]
//            [       0              0         1   ]
    @Override
    public void rotate(double theta){
        double[] m = new double[6];
//        getMatrix(double[] flatmatrix)
//        Retrieves the 6 specifiable values in the 3x3 affine transformation matrix and places them into an array of double precisions values.
        this.getMatrix(m);

        this.setTransform(
                //m00
                //m10
                //
                (m[0] * Math.cos(-theta) + m[2] * -Math.sin(-theta)),
                (m[1] * Math.cos(-theta) + m[3] * -Math.sin(-theta)),
                //m01
                //m11
                //
                (m[0] * Math.sin(-theta) + m[2] * Math.cos(-theta)),
                (m[1] * Math.sin(-theta) + m[3] * Math.cos(-theta)),
                //m02
                //m12
                //
                (m[4]),
                (m[5])
        );
    }

//            [   sx   0    0   ]
//            [   0    sy   0   ]
//            [   0    0    1   ]
    @Override
    public void scale(double x,double y){
        double[] m = new double[6];
//        getMatrix(double[] flatmatrix)
//        Retrieves the 6 specifiable values in the 3x3 affine transformation matrix and places them into an array of double precisions values.
        this.getMatrix(m);

        this.setTransform(
                //m00
                //m10
                //
                (m[0] * x),
                (m[1] * x),
                //m01
                //m11
                //
                (m[2] * y),
                (m[3] * y),
                //m02
                //m12
                //
                (m[4]),
                (m[5])
        );
    }

}
