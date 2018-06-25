package pers.congcong.myBatis2.type;

/**
 * Created by 聪聪 on 2018/6/21 0021.
 */
public enum Enabled {
    disabled(1),
    enabled(0);

    private final int value;

    Enabled(int value) {
        this.value = value;
    }

    public int getValue() {

        return value;
    }
}


