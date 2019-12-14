package com.classes;

public class Size {
    public short szx;
    public short szy;
    public short[] size;

    public Size(short szx, short szy) {
        this.szx = szx;
        this.szy = szy;
        this.size = new short[]{szx, szy};
    }

    public Size(short[] size) {
        this.size = size;
        this.szx = size[0];
        this.szy = size[1];
    }
}
