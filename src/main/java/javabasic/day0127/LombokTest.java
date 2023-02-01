package javabasic.day0127;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LombokTest {
    private final int SIZE = 4;

    @Getter
    @Setter
    private int id;
    private int prop1;
    private String title;
    private String something;


}
